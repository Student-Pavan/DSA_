# 📌 Length of Last Word

---

# 📝 Problem Statement

Given a string `s` consisting of words separated by spaces, return the length of the **last word** in the string.

A **word** is defined as a maximal substring consisting of non-space characters only.

### Constraints:
- `1 <= s.length <= 10^4`
- `s` consists of only English letters and spaces `' '`
- There will be at least one word in `s`

### Examples:
**Example 1:**
```
Input: s = "Hello World"
Output: 5
Explanation: The last word is "World" with length 5.
```

**Example 2:**
```
Input: s = "   fly me   to   the moon  "
Output: 4
Explanation: The last word is "moon" with length 4.
```

**Example 3:**
```
Input: s = "luffy is still joyboy"
Output: 6
Explanation: The last word is "joyboy" with length 6.
```

---

# 💡 Intuition

The key insight is that the last word is the substring of non-space characters that appears **after the last space** in the string. However, trailing spaces can complicate this, as they appear after the last word.

Instead of processing the string from the start (which may involve unnecessary work), we can **start from the end** and skip any trailing spaces. Once we encounter a non-space character, we count until we hit a space or the start of the string. This approach avoids unnecessary iterations and handles edge cases like trailing spaces efficiently.

---

# 🐌 Brute Force Approach

## 🔹 Approach

The brute force approach involves:
1. Splitting the string into words using spaces as delimiters.
2. Taking the last non-empty word from the resulting array.
3. Returning its length.

This approach is straightforward but involves:
- Creating an array of words (extra space).
- Iterating through the entire string to split it.
- Handling multiple spaces between words.

## 🔹 Algorithm

1. Split the string `s` into an array of words using `" "` as the delimiter.
2. Iterate from the end of the array to find the first non-empty word.
3. Return the length of that word.

---

## 🔹 Code

```java
class Solution {
    public int lengthOfLastWord(String s) {
        String[] words = s.split(" ");
        for (int i = words.length - 1; i >= 0; i--) {
            if (!words[i].isEmpty()) {
                return words[i].length();
            }
        }
        return 0;
    }
}
```

---

## 🔹 Dry Run

**Input:** `s = "   fly me   to   the moon  "`

| Step | Action | Words Array | Current Word | Result |
|------|--------|-------------|--------------|--------|
| 1 | Split string by spaces | `["", "", "", "fly", "me", "", "", "to", "", "", "the", "moon", "", ""]` | - | - |
| 2 | Start from last index (13) | - | `""` | Skip |
| 3 | Move to index 12 | - | `""` | Skip |
| 4 | Move to index 11 | - | `"moon"` | Return `4` |

**Output:** `4`

---

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(n) |
| Space Complexity | O(n) |

**Explanation:**
- Splitting the string takes O(n) time and O(n) space (where `n` is the length of the string).
- Iterating through the words array takes O(m) time (where `m` is the number of words), but in the worst case, `m` can be O(n).

---

# ⚡ Optimal Approach

## 🔹 Approach

The optimal approach avoids extra space and unnecessary iterations by:
1. Starting from the **end of the string**.
2. Skipping all trailing spaces.
3. Counting the length of the last word until a space or the start of the string is encountered.

This approach:
- Uses O(1) space.
- Processes the string in a single pass from the end.
- Handles trailing spaces naturally.

## 🔹 Why This Works

- By starting from the end, we directly target the last word.
- Trailing spaces are skipped efficiently.
- The loop terminates as soon as the last word is fully counted, avoiding unnecessary work.

## 🔹 Algorithm

1. Initialize `length = 0`.
2. Start from the end of the string (`i = s.length() - 1`).
3. Skip all trailing spaces by decrementing `i` until a non-space character is found.
4. Count the length of the last word by incrementing `length` until a space or the start of the string is encountered.
5. Return `length`.

---

## 🔹 Code

```java
class Solution {
    public int lengthOfLastWord(String s) {
        int length = 0;
        int i = s.length() - 1;

        // Skip trailing spaces
        while (i >= 0 && s.charAt(i) == ' ') {
            i--;
        }

        // Count the length of the last word
        while (i >= 0 && s.charAt(i) != ' ') {
            length++;
            i--;
        }

        return length;
    }
}
```

---

## 🔹 Detailed Dry Run

**Input:** `s = "   fly me   to   the moon  "`

| Step | `i` | `s.charAt(i)` | Action | `length` |
|------|-----|---------------|--------|----------|
| 1 | 22 | `' '` | Skip space | 0 |
| 2 | 21 | `' '` | Skip space | 0 |
| 3 | 20 | `'n'` | Start counting | 1 |
| 4 | 19 | `'o'` | Increment | 2 |
| 5 | 18 | `'o'` | Increment | 3 |
| 6 | 17 | `'m'` | Increment | 4 |
| 7 | 16 | `' '` | Stop counting | 4 |

**Output:** `4`

---

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(n) |
| Space Complexity | O(1) |

**Explanation:**
- The algorithm processes each character at most once (from the end), resulting in O(n) time.
- Only a few variables (`length`, `i`) are used, resulting in O(1) space.

---

# 🔍 Edge Cases

| Edge Case | Input | Expected Output | Explanation |
|-----------|-------|-----------------|-------------|
| Trailing spaces | `"Hello World   "` | `5` | Last word is `"World"` |
| Leading spaces | `"   Hello"` | `5` | Last word is `"Hello"` |
| Single word | `"Hello"` | `5` | Only one word |
| Multiple spaces between words | `"a   b"` | `1` | Last word is `"b"` |
| Single character | `"a"` | `1` | Only one character |
| All spaces (invalid per constraints) | `"     "` | (Invalid) | Constraints ensure at least one word |

---

# 📚 Key Takeaways

1. **String Traversal:** Starting from the end is often more efficient for problems involving the last occurrence of a pattern.
2. **Space Optimization:** Avoid unnecessary data structures (like splitting the string) when possible.
3. **Edge Case Handling:** Trailing spaces are a common pitfall; always account for them.
4. **Two-Pointer Technique:** The optimal solution effectively uses a single pointer to traverse the string.

---

# 🚀 Interview Tips

1. **Clarify Constraints:** Ask if trailing spaces are possible (though constraints here guarantee at least one word).
2. **Discuss Trade-offs:** Compare the brute force (split) vs. optimal (traversal) approaches in terms of time and space.
3. **Follow-up Questions:**
   - How would you handle Unicode characters?
   - What if the string contains tabs or newlines instead of spaces?
4. **Optimization Insight:** Highlight that the optimal solution avoids extra space and processes the string in a single pass.

---

# ✅ Conclusion

The optimal solution efficiently computes the length of the last word by traversing the string from the end, skipping trailing spaces, and counting the last word's length. This approach ensures **O(n) time and O(1) space**, making it both time and space optimal. The key insight is to **target the last word directly** rather than processing the entire string unnecessarily. This problem is a great example of how **pointer manipulation** and **traversal direction** can significantly impact efficiency.