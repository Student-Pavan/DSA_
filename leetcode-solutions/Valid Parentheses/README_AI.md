# 📌 Valid Parentheses

---

# 📝 Problem Statement

Given a string `s` containing just the characters `'('`, `')'`, `'{'`, `'}'`, `'['`, `']'`, determine if the input string is valid.

An input string is valid if:
1. Open brackets must be closed by the same type of brackets.
2. Open brackets must be closed in the correct order.
3. Every close bracket has a corresponding open bracket of the same type.

### Constraints:
- `1 <= s.length <= 10^4`
- `s` consists of parentheses only `'()[]{}'`.

### Examples:
**Input:** `"()"`
**Output:** `true`

**Input:** `"()[]{}"`
**Output:** `true`

**Input:** `"(]"`
**Output:** `false`

---

# 💡 Intuition

The problem requires checking for balanced parentheses in a string. The key insight is that **a valid string must close the most recently opened bracket first** (Last-In-First-Out order). This naturally suggests using a **stack data structure** to track open brackets and ensure they are closed in the correct order.

A brute-force approach might involve scanning the string multiple times or using complex conditional logic, but this would be inefficient. The optimal solution leverages the stack's LIFO property to achieve linear time complexity.

---

# 🐌 Brute Force Approach

## 🔹 Approach

The brute-force approach involves scanning the string and keeping track of open brackets using a list or array. For every closing bracket encountered, we search the list for the most recent matching open bracket. If found, we remove it; otherwise, the string is invalid.

This approach is inefficient because it requires searching the list for each closing bracket, leading to O(n²) time complexity in the worst case.

---

## 🔹 Algorithm

1. Initialize an empty list to track open brackets.
2. Iterate through each character in the string:
   - If the character is an open bracket (`(`, `{`, `[`), add it to the list.
   - If the character is a closing bracket (`)`, `}`, `]`):
     - Check if the list is empty. If yes, return `false`.
     - Search the list from the end to find the most recent matching open bracket.
     - If found, remove it from the list; otherwise, return `false`.
3. After processing all characters, if the list is empty, return `true`; otherwise, return `false`.

---

## 🔹 Code

```java
import java.util.ArrayList;
import java.util.List;

class Solution {
    public boolean isValid(String s) {
        List<Character> openBrackets = new ArrayList<>();

        for (char c : s.toCharArray()) {
            if (c == '(' || c == '{' || c == '[') {
                openBrackets.add(c);
            } else {
                if (openBrackets.isEmpty()) {
                    return false;
                }
                char lastOpen = openBrackets.get(openBrackets.size() - 1);
                if ((c == ')' && lastOpen == '(') ||
                    (c == '}' && lastOpen == '{') ||
                    (c == ']' && lastOpen == '[')) {
                    openBrackets.remove(openBrackets.size() - 1);
                } else {
                    return false;
                }
            }
        }

        return openBrackets.isEmpty();
    }
}
```

---

## 🔹 Dry Run

**Input:** `"()[]{}"`

| Step | Character | Open Brackets List | Action                          | Result  |
|------|-----------|--------------------|---------------------------------|---------|
| 1    | `(`       | `['(']`            | Add `(` to list                 | Continue|
| 2    | `)`       | `['(']`            | Match `)` with `(` → remove `(` | Continue|
| 3    | `[`       | `[]`               | Add `[` to list                 | Continue|
| 4    | `]`       | `['[']`            | Match `]` with `[` → remove `[` | Continue|
| 5    | `{`       | `[]`               | Add `{` to list                 | Continue|
| 6    | `}`       | `['{']`            | Match `}` with `{` → remove `{` | Continue|
| End  | -         | `[]`               | List is empty → valid           | `true`  |

**Input:** `"(]"` (Invalid)

| Step | Character | Open Brackets List | Action                          | Result  |
|------|-----------|--------------------|---------------------------------|---------|
| 1    | `(`       | `['(']`            | Add `(` to list                 | Continue|
| 2    | `]`       | `['(']`            | `]` does not match `(`          | `false` |

---

## 🔹 Complexity Analysis

| Complexity       | Value       |
|------------------|-------------|
| Time Complexity  | O(n²)       |
| Space Complexity | O(n)        |

**Explanation:**
- **Time:** O(n²) because for each closing bracket, we may scan the entire list (O(n) per operation).
- **Space:** O(n) to store open brackets in the worst case (e.g., `"((({{["`).

---

# ⚡ Optimal Approach

## 🔹 Approach

The optimal approach uses a **stack** to track open brackets. For every closing bracket encountered, we check if it matches the top of the stack (most recent open bracket). If it matches, we pop the stack; otherwise, the string is invalid. This approach ensures O(n) time complexity because stack operations (push/pop) are O(1).

---

## 🔹 Why This Works

- **LIFO Property:** The stack ensures that the most recently opened bracket is closed first, which is required for valid parentheses.
- **Efficiency:** Stack operations are O(1), making the overall algorithm O(n).
- **Correctness:** The stack naturally enforces the correct order of bracket closure.

---

## 🔹 Algorithm

1. Initialize an empty stack.
2. Iterate through each character in the string:
   - If the character is an open bracket (`(`, `{`, `[`), push it onto the stack.
   - If the character is a closing bracket (`)`, `}`, `]`):
     - If the stack is empty, return `false`.
     - Pop the top of the stack and check if it matches the closing bracket.
     - If it doesn’t match, return `false`.
3. After processing all characters, if the stack is empty, return `true`; otherwise, return `false`.

---

## 🔹 Code

```java
import java.util.Stack;

class Solution {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();

        for (char c : s.toCharArray()) {
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }
                char top = stack.pop();
                if ((c == ')' && top != '(') ||
                    (c == '}' && top != '{') ||
                    (c == ']' && top != '[')) {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }
}
```

---

## 🔹 Detailed Dry Run

**Input:** `"()[]{}"`

| Step | Character | Stack State | Action                          | Result  |
|------|-----------|-------------|---------------------------------|---------|
| 1    | `(`       | `['(']`     | Push `(`                        | Continue|
| 2    | `)`       | `[]`        | Pop `(` → matches `)`           | Continue|
| 3    | `[`       | `['[']`     | Push `[`                        | Continue|
| 4    | `]`       | `[]`        | Pop `[` → matches `]`           | Continue|
| 5    | `{`       | `['{']`     | Push `{`                        | Continue|
| 6    | `}`       | `[]`        | Pop `{` → matches `}`           | Continue|
| End  | -         | `[]`        | Stack is empty → valid          | `true`  |

**Input:** `"(]"` (Invalid)

| Step | Character | Stack State | Action                          | Result  |
|------|-----------|-------------|---------------------------------|---------|
| 1    | `(`       | `['(']`     | Push `(`                        | Continue|
| 2    | `]`       | `['(']`     | Pop `(` → `]` does not match `(`| `false` |

---

## 🔹 Complexity Analysis

| Complexity       | Value       |
|------------------|-------------|
| Time Complexity  | O(n)        |
| Space Complexity | O(n)        |

**Explanation:**
- **Time:** O(n) because we process each character exactly once.
- **Space:** O(n) in the worst case (e.g., `"((({{["`).

---

# 🔍 Edge Cases

1. **Empty String:** `""` → Valid (edge case, but constraints say `1 <= s.length`).
2. **Single Bracket:** `"("` → Invalid.
3. **All Open Brackets:** `"((("` → Invalid.
4. **All Close Brackets:** `")))"` → Invalid.
5. **Mixed Order:** `"([)]"` → Invalid.
6. **Large Input:** `"((((((((((())))))))))"` → Valid (stress test for stack).
7. **Nested Brackets:** `"{[]}"` → Valid.

---

# 📚 Key Takeaways

1. **Stack for LIFO Problems:** The stack is the ideal data structure for problems requiring Last-In-First-Out order, such as balanced parentheses.
2. **Early Termination:** Return `false` as soon as an invalid closing bracket is encountered to optimize performance.
3. **Space Optimization:** The stack’s space usage is O(n) in the worst case, but this is unavoidable for correctness.
4. **Pattern Recognition:** This problem is a classic example of using stacks to validate nested structures (e.g., HTML tags, code blocks).

---

# 🚀 Interview Tips

1. **Follow-Up Questions:**
   - How would you handle additional bracket types (e.g., `<>`)?
   - Can you solve this without a stack? (Hint: Use a counter for each bracket type, but this complicates order validation.)
   - What if the input contains non-bracket characters? (Modify to ignore them or return `false`.)

2. **Common Pitfalls:**
   - Forgetting to check if the stack is empty before popping (causes `EmptyStackException`).
   - Mismatching bracket types (e.g., `(` with `]`).
   - Not handling edge cases like `"("` or `")"`.

3. **Alternative Approaches:**
   - **Recursion:** Can be used but is less efficient and harder to debug.
   - **String Replacement:** Repeatedly replace `()`, `{}`, `[]` with empty strings until the string is empty or no more replacements are possible. This is O(n²) and less efficient.

4. **Optimization Discussion:**
   - The stack-based solution is optimal for this problem. No further optimization is possible without sacrificing correctness.

---

# ✅ Conclusion

The **optimal solution** uses a stack to validate parentheses in **O(n) time and O(n) space**, which is the best possible approach for this problem. The brute-force approach, while correct, is inefficient due to its O(n²) time complexity.

**Key Insight:** The stack’s LIFO property perfectly matches the requirement that the most recently opened bracket must be closed first. This problem is a foundational example of how stacks can be used to solve nested structure validation problems efficiently.