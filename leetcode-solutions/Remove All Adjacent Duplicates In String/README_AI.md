# 📌 Remove All Adjacent Duplicates In String

---

# 📝 Problem Statement

Given a string `s` consisting of lowercase English letters, repeatedly remove adjacent duplicate characters until no more adjacent duplicates can be removed.

**Objective**: Return the final string after all possible adjacent duplicate removals.

**Input**:
- A string `s` (1 ≤ s.length ≤ 10⁵)

**Output**:
- The resulting string after all adjacent duplicates have been removed

**Important Constraints**:
- The string may contain multiple layers of adjacent duplicates (e.g., `"abbaca"` → `"aaca"` → `"ca"`)
- The removal process must be repeated until no more adjacent duplicates exist
- The solution must efficiently handle the upper constraint (10⁵ characters)

---

# 💡 Intuition

The core challenge is efficiently handling **nested adjacent duplicates** without repeatedly scanning the entire string. A naive approach would repeatedly scan and remove duplicates, but this leads to O(n²) time complexity in the worst case.

The key insight is recognizing that **adjacent duplicate removal behaves like a stack operation**: when a new character matches the top of the stack, we pop (remove) the top, effectively removing the adjacent duplicate. This allows us to process the string in a single pass with O(n) time complexity.

---

# 🐌 Brute Force Approach

## 🔹 Approach

The brute force strategy involves repeatedly scanning the string and removing adjacent duplicates until no more duplicates are found. This is done by:
1. Iterating through the string
2. Checking each character against its neighbor
3. Removing adjacent duplicates when found
4. Repeating the process until no more duplicates exist

This approach is simple but inefficient due to repeated full-string scans.

---

## 🔹 Algorithm

1. Initialize a flag `changed` to `true`
2. While `changed` is `true`:
   - Set `changed` to `false`
   - Iterate through the string from index `0` to `length - 2`
   - If `s[i] == s[i+1]`:
     - Remove both characters
     - Set `changed` to `true`
     - Break the current iteration to restart scanning from the beginning
3. Return the resulting string

---

## 🔹 Code

```java
class Solution {
    public String removeDuplicates(String s) {
        boolean changed;
        StringBuilder sb = new StringBuilder(s);

        do {
            changed = false;
            for (int i = 0; i < sb.length() - 1; i++) {
                if (sb.charAt(i) == sb.charAt(i + 1)) {
                    sb.delete(i, i + 2);
                    changed = true;
                    break; // Restart scanning from beginning after modification
                }
            }
        } while (changed);

        return sb.toString();
    }
}
```

---

## 🔹 Dry Run

**Input**: `"abbaca"`

| Iteration | Current String | Comparison | Action | Changed | Result |
|-----------|----------------|------------|--------|---------|--------|
| 1         | "abbaca"       | 'a' vs 'b' | No     | false   | -      |
| 1         | "abbaca"       | 'b' vs 'b' | Remove "bb" | true | "aaca" |
| 2         | "aaca"         | 'a' vs 'a' | Remove "aa" | true | "ca"   |
| 3         | "ca"           | 'c' vs 'a' | No     | false   | -      |

**Final Output**: `"ca"`

---

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(n²) in worst case (e.g., "abababab...") |
| Space Complexity | O(n) for StringBuilder |

---

# ⚡ Optimal Approach

## 🔹 Approach

The optimal approach uses a **stack** to simulate the adjacent duplicate removal in a single pass. As we iterate through the string:
- If the current character matches the top of the stack, we pop the stack (removing the adjacent duplicate)
- Otherwise, we push the character onto the stack

This approach efficiently handles nested duplicates in O(n) time with O(n) space.

---

## 🔹 Why This Works

The stack naturally handles the **last-in-first-out** behavior required for adjacent duplicate removal. When a new character matches the top of the stack, it forms an adjacent duplicate with the most recent character, which is exactly what we want to remove. This avoids the need for repeated full-string scans and handles nested duplicates seamlessly.

---

## 🔹 Algorithm

1. Initialize an empty stack
2. Iterate through each character in the string:
   - If the stack is not empty and the top of the stack equals the current character:
     - Pop the stack (removing the adjacent duplicate)
   - Else:
     - Push the current character onto the stack
3. Convert the stack to a string and return it

---

## 🔹 Code

```java
class Solution {
    public String removeDuplicates(String s) {
        StringBuilder stack = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (stack.length() > 0 && stack.charAt(stack.length() - 1) == c) {
                stack.deleteCharAt(stack.length() - 1);
            } else {
                stack.append(c);
            }
        }

        return stack.toString();
    }
}
```

---

## 🔹 Detailed Dry Run

**Input**: `"abbaca"`

| Step | Current Char | Stack State | Action |
|------|--------------|-------------|--------|
| 1    | 'a'          | "" → "a"    | Push   |
| 2    | 'b'          | "a" → "ab"  | Push   |
| 3    | 'b'          | "ab" → "a"  | Pop (duplicate) |
| 4    | 'a'          | "a" → ""    | Pop (duplicate) |
| 5    | 'c'          | "" → "c"    | Push   |
| 6    | 'a'          | "c" → "ca"  | Push   |

**Final Stack**: `"ca"`
**Output**: `"ca"`

---

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(n) - single pass through the string |
| Space Complexity | O(n) - stack storage in worst case |

---

# 🔍 Edge Cases

- **Empty string**: `""` → `""`
- **Single character**: `"a"` → `"a"`
- **No duplicates**: `"abc"` → `"abc"`
- **All duplicates**: `"aaaa"` → `""`
- **Nested duplicates**: `"abba"` → `""` (removes "bb" first, then "aa")
- **Large input**: `"a" * 100000` → `""` (must handle efficiently)
- **Alternating duplicates**: `"ababab"` → `"ababab"` (no adjacent duplicates)

---

# 📚 Key Takeaways

1. **Stack Pattern**: Adjacent duplicate removal is a classic stack problem
2. **Single Pass Efficiency**: The optimal solution processes the string in O(n) time
3. **Nested Handling**: Stacks naturally handle nested/recursive duplicate removal
4. **StringBuilder vs Stack**: Using `StringBuilder` as a stack is memory-efficient in Java
5. **Brute Force Pitfall**: Repeated full-string scans lead to O(n²) time complexity

---

# 🚀 Interview Tips

- **Follow-up**: What if we need to remove duplicates of length `k` instead of 2? (Use a stack of character-count pairs)
- **Common Pitfall**: Forgetting to break/restart scanning in brute force approach
- **Alternative Approach**: Two-pointer technique (less intuitive for nested cases)
- **Optimization Discussion**: Why O(n) time is necessary for large inputs (10⁵ constraint)
- **Space Trade-off**: Can we do better than O(n) space? (No, the result itself may require O(n) space)

---

# ✅ Conclusion

The optimal solution leverages a **stack-based approach** to efficiently remove adjacent duplicates in a single pass, achieving O(n) time and space complexity. This is significantly more efficient than the brute force approach, which requires O(n²) time in the worst case.

The key insight is recognizing that **adjacent duplicate removal follows a last-in-first-out pattern**, making a stack the perfect data structure for this problem. This approach is both elegant and efficient, making it ideal for technical interviews and production use.