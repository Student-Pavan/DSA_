# 📌 Minimum Remove to Make Valid Parentheses

---

# 📝 Problem Statement

Given a string `s` consisting of lowercase English letters and parentheses `'('` and `')'`, remove the **minimum number** of parentheses to make the resulting string **valid**.

A string is considered **valid** if:
- Every opening parenthesis `'('` has a corresponding closing parenthesis `')'`.
- Parentheses are properly nested.

**Objective**: Return any valid string with the **minimum removals**.

**Constraints**:
- `1 <= s.length <= 10^5`
- `s[i]` is either a lowercase English letter, `'('`, or `')'`.

**Examples**:
- Input: `"lee(t(c)o)de)"`
  Output: `"lee(t(c)o)de"` (Remove the last `')'`)
- Input: `"a)b(c)d"`
  Output: `"ab(c)d"` (Remove the first `')'`)
- Input: `"))(("`
  Output: `""` (Remove all parentheses)

---

# 💡 Intuition

The core challenge is identifying **unmatched parentheses**—those that don't have a valid counterpart. We can use a **stack** to track the positions of opening parentheses and match them with closing ones. Any unmatched parentheses (either `'('` or `')'`) must be removed.

The key insight:
- Use a stack to track indices of `'('`.
- When encountering `')'`, pop from the stack if possible (matching pair).
- Any remaining indices in the stack or unmatched `')'` are candidates for removal.
- Build the result by skipping these indices.

This approach ensures **minimum removals** and **linear time complexity**.

---

# 🐌 Brute Force Approach

## 🔹 Approach

A naive strategy involves:
1. Iterating through the string and counting open/close parentheses.
2. If at any point the count of `')'` exceeds `'('`, remove the excess `')'`.
3. After processing, if there are unmatched `'('`, remove them from the end.

However, this approach fails to track **positions** of unmatched parentheses, leading to incorrect removals (e.g., removing the wrong `'('`).

Instead, we use a **stack** to track indices of `'('` and mark invalid `')'` as we go. After processing, any remaining `'('` in the stack are also invalid. We then build the result by skipping these indices.

---

## 🔹 Algorithm

1. Initialize a stack to store indices of `'('`.
2. Initialize a set to store indices of invalid parentheses.
3. Traverse the string:
   - If `'('`, push its index onto the stack.
   - If `')'`, pop from the stack if not empty (valid pair). If empty, mark this `')'` as invalid.
4. After traversal, add all remaining indices in the stack to the invalid set (unmatched `'('`).
5. Build the result by skipping characters at invalid indices.

---

## 🔹 Code

```java
import java.util.*;

class Solution {
    public String minRemoveToMakeValid(String s) {
        Stack<Integer> stack = new Stack<>();
        Set<Integer> invalidIndices = new HashSet<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                stack.push(i);
            } else if (c == ')') {
                if (stack.isEmpty()) {
                    invalidIndices.add(i); // Unmatched ')'
                } else {
                    stack.pop(); // Matched pair
                }
            }
        }

        // Add remaining unmatched '('
        while (!stack.isEmpty()) {
            invalidIndices.add(stack.pop());
        }

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (!invalidIndices.contains(i)) {
                result.append(s.charAt(i));
            }
        }

        return result.toString();
    }
}
```

---

## 🔹 Dry Run

**Input**: `"a)b(c)d"`

| Step | Character | Stack (Indices) | Invalid Indices | Action |
|------|-----------|------------------|------------------|--------|
| 0    | 'a'       | []               | {}               | Skip   |
| 1    | ')'       | []               | {1}              | Unmatched `')'` |
| 2    | 'b'       | []               | {1}              | Skip   |
| 3    | '('       | [3]              | {1}              | Push   |
| 4    | 'c'       | [3]              | {1}              | Skip   |
| 5    | ')'       | []               | {1}              | Pop (matched) |
| 6    | 'd'       | []               | {1}              | Skip   |

**Final Invalid Indices**: {1}
**Result**: `"ab(c)d"`

---

## 🔹 Complexity Analysis

| Complexity       | Value       |
|------------------|-------------|
| Time Complexity  | O(n)        |
| Space Complexity | O(n)        |

---

# ⚡ Optimal Approach

## 🔹 Approach

The brute force approach is already optimal in time complexity (`O(n)`), but we can optimize **space** by using a **two-pass** method:
1. **First Pass (Left to Right)**: Remove unmatched `')'` by tracking balance.
2. **Second Pass (Right to Left)**: Remove unmatched `'('` from the end.

This avoids using a stack and reduces space complexity to `O(1)` (excluding output storage).

---

## 🔹 Why This Works

- **First Pass**: Ensures no `')'` appears before its matching `'('`.
- **Second Pass**: Ensures no `'('` remains unmatched by removing excess from the end.
- The order of passes guarantees **minimum removals** and **valid nesting**.

---

## 🔹 Algorithm

1. **First Pass (Left to Right)**:
   - Track `balance` (increment for `'('`, decrement for `')'`).
   - If `balance < 0`, skip this `')'` (unmatched).
   - Reset `balance` to `0` if negative.
2. **Second Pass (Right to Left)**:
   - Track `balance` (increment for `')'`, decrement for `'('`).
   - If `balance < 0`, skip this `'('` (unmatched).
   - Reset `balance` to `0` if negative.
3. Build the result by including only valid characters.

---

## 🔹 Code

```java
class Solution {
    public String minRemoveToMakeValid(String s) {
        StringBuilder sb = new StringBuilder();
        int balance = 0;

        // First pass: remove unmatched ')'
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                balance++;
                sb.append(c);
            } else if (c == ')') {
                if (balance > 0) {
                    balance--;
                    sb.append(c);
                }
            } else {
                sb.append(c);
            }
        }

        // Second pass: remove unmatched '(' from the end
        StringBuilder result = new StringBuilder();
        balance = 0;
        for (int i = sb.length() - 1; i >= 0; i--) {
            char c = sb.charAt(i);
            if (c == ')') {
                balance++;
                result.append(c);
            } else if (c == '(') {
                if (balance > 0) {
                    balance--;
                    result.append(c);
                }
            } else {
                result.append(c);
            }
        }

        return result.reverse().toString();
    }
}
```

---

## 🔹 Detailed Dry Run

**Input**: `"a)b(c)d"`

### First Pass (Left to Right)
| Index | Char | Balance | Action       | Result So Far |
|-------|------|---------|--------------|---------------|
| 0     | 'a'  | 0       | Append       | "a"           |
| 1     | ')'  | -1      | Skip         | "a"           |
| 2     | 'b'  | 0       | Append       | "ab"          |
| 3     | '('  | 1       | Append       | "ab("         |
| 4     | 'c'  | 1       | Append       | "ab(c"        |
| 5     | ')'  | 0       | Append       | "ab(c)"       |
| 6     | 'd'  | 0       | Append       | "ab(c)d"      |

**Intermediate String**: `"ab(c)d"`

### Second Pass (Right to Left)
| Index | Char | Balance | Action       | Result So Far |
|-------|------|---------|--------------|---------------|
| 5     | 'd'  | 0       | Append       | "d"           |
| 4     | ')'  | 1       | Append       | "d)"          |
| 3     | 'c'  | 1       | Append       | "d)c"         |
| 2     | '('  | 0       | Append       | "d)c("        |
| 1     | 'b'  | 0       | Append       | "d)c(b"       |
| 0     | 'a'  | 0       | Append       | "d)c(ba"      |

**Reversed Result**: `"ab(c)d"`

---

## 🔹 Complexity Analysis

| Complexity       | Value       |
|------------------|-------------|
| Time Complexity  | O(n)        |
| Space Complexity | O(n)        |

> **Note**: Space is `O(n)` due to output storage, but auxiliary space is `O(1)`.

---

# 🔍 Edge Cases

| Case                     | Input          | Expected Output |
|--------------------------|----------------|-----------------|
| Empty string             | `""`           | `""`            |
| No parentheses           | `"abc"`        | `"abc"`         |
| All invalid              | `"))(("`       | `""`            |
| Single unmatched `')'`   | `")a"`         | `"a"`           |
| Single unmatched `'('`   | `"a("`         | `"a"`           |
| Nested valid             | `"(a(b)c)"`    | `"(a(b)c)"`     |
| Large input              | `"a".repeat(1e5) + ")"` | `"a".repeat(1e5)` |

---

# 📚 Key Takeaways

1. **Stack-Based Tracking**: Use a stack to track indices of `'('` and match with `')'`.
2. **Two-Pass Optimization**: Eliminate the stack by processing left-to-right and right-to-left.
3. **Balance Tracking**: Maintain a balance counter to identify unmatched parentheses.
4. **Minimum Removals**: Always remove the **minimum** number of parentheses to ensure validity.
5. **Interview Insight**: This problem tests **stack usage** and **greedy removal strategies**.

---

# 🚀 Interview Tips

### Follow-Up Questions:
- Can you solve it in **one pass**? *(Yes, but requires more complex tracking.)*
- How would you handle **multiple types of brackets** (e.g., `{}`, `[]`)? *(Extend the stack to track types.)*
- Can you return **all possible valid strings** with minimum removals? *(Use backtracking or BFS.)*

### Common Pitfalls:
- Forgetting to handle **unmatched `'('`** after the first pass.
- Using **brute-force removal** (e.g., removing all `')'` first) leads to incorrect results.
- Not considering **space optimization** (e.g., stack vs. two-pass).

### Alternative Approaches:
- **Recursive Backtracking**: Generate all possible valid strings and pick the longest. *(Inefficient for large inputs.)*
- **BFS with State Tracking**: Explore all possible removals level by level. *(High space complexity.)*

---

# ✅ Conclusion

The **optimal two-pass solution** is preferred due to its **linear time complexity** and **constant auxiliary space**. It efficiently identifies unmatched parentheses by leveraging **balance tracking** and ensures **minimum removals** while maintaining correctness.

**Key Insight**: Parentheses validity can be determined by **directional balance checks**, avoiding the need for a stack in the optimal approach. This problem is a classic example of **greedy removal** and **stack-based validation** in string processing.