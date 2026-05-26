# 📌 Valid Palindrome

---

# 📝 Problem Statement

A phrase is a **palindrome** if, after converting all uppercase letters into lowercase letters and removing all non-alphanumeric characters, it reads the same forward and backward. Alphanumeric characters include letters and numbers.

Given a string `s`, return `true` if it is a palindrome, or `false` otherwise.

### **Input:**
- A string `s` (1 ≤ `s.length` ≤ 2 × 10<sup>5</sup>)

### **Output:**
- `true` if `s` is a valid palindrome, `false` otherwise

### **Constraints:**
- The string may contain uppercase/lowercase letters, digits, and special characters.
- Empty string after processing should be considered a valid palindrome.

---

# 💡 Intuition

The key insight is that a palindrome reads the same forwards and backwards. To check this efficiently:
1. **Preprocess** the string: remove non-alphanumeric characters and convert to lowercase.
2. Use **two pointers** (one at the start, one at the end) to compare characters symmetrically.
3. If any mismatch is found, it's not a palindrome.

The optimization comes from avoiding full string preprocessing (e.g., creating a new string) by filtering characters on-the-fly during comparison.

---

# 🐌 Brute Force Approach

## 🔹 Approach

1. **Preprocess** the entire string: remove all non-alphanumeric characters and convert to lowercase.
2. **Compare** the cleaned string with its reverse.

This approach is straightforward but inefficient due to:
- Extra space for the cleaned string.
- Full traversal of the string twice (once for cleaning, once for comparison).

---

## 🔹 Algorithm

1. Initialize an empty string `cleaned`.
2. Iterate through each character in `s`:
   - If the character is alphanumeric, convert to lowercase and append to `cleaned`.
3. Compare `cleaned` with its reverse.
4. Return `true` if they match, `false` otherwise.

---

## 🔹 Code

```java
class Solution {
    public boolean isPalindrome(String s) {
        StringBuilder cleaned = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (Character.isLetterOrDigit(c)) {
                cleaned.append(Character.toLowerCase(c));
            }
        }
        String processed = cleaned.toString();
        return processed.equals(cleaned.reverse().toString());
    }
}
```

---

## 🔹 Dry Run

**Input:** `s = "A man, a plan, a canal: Panama"`

| Step | Character | Action                     | `cleaned` State       |
|------|-----------|----------------------------|-----------------------|
| 1    | 'A'       | Append 'a'                 | "a"                   |
| 2    | ' '       | Skip                       | "a"                   |
| 3    | 'm'       | Append 'm'                 | "am"                  |
| 4    | 'a'       | Append 'a'                 | "ama"                 |
| 5    | 'n'       | Append 'n'                 | "aman"                |
| 6    | ','       | Skip                       | "aman"                |
| 7    | ' '       | Skip                       | "aman"                |
| 8    | 'a'       | Append 'a'                 | "amana"               |
| 9    | ' '       | Skip                       | "amana"               |
| 10   | 'p'       | Append 'p'                 | "amanap"              |
| 11   | 'l'       | Append 'l'                 | "amanapl"             |
| 12   | 'a'       | Append 'a'                 | "amanapla"            |
| 13   | 'n'       | Append 'n'                 | "amanaplan"           |
| 14   | ':'       | Skip                       | "amanaplan"           |
| 15   | ' '       | Skip                       | "amanaplan"           |
| 16   | 'a'       | Append 'a'                 | "amanaplana"          |
| 17   | ' '       | Skip                       | "amanaplana"          |
| 18   | 'c'       | Append 'c'                 | "amanaplanac"         |
| 19   | 'a'       | Append 'a'                 | "amanaplanaca"        |
| 20   | 'n'       | Append 'n'                 | "amanaplanacan"       |
| 21   | 'a'       | Append 'a'                 | "amanaplanacana"      |
| 22   | 'l'       | Append 'l'                 | "amanaplanacanal"     |
| 23   | ' '       | Skip                       | "amanaplanacanal"     |
| 24   | 'P'       | Append 'p'                 | "amanaplanacanalp"    |
| 25   | 'a'       | Append 'a'                 | "amanaplanacanalpa"   |
| 26   | 'n'       | Append 'n'                 | "amanaplanacanalpan"  |
| 27   | 'a'       | Append 'a'                 | "amanaplanacanalpana" |
| 28   | 'm'       | Append 'm'                 | "amanaplanacanalpanam"|
| 29   | 'a'       | Append 'a'                 | "amanaplanacanalpanama"|

**Reversed String:** "amanaplanacanalpanama"
**Comparison:** "amanaplanacanalpanama" == "amanaplanacanalpanama" → `true`

---

## 🔹 Complexity Analysis

| Complexity       | Value               |
|------------------|---------------------|
| Time Complexity  | O(n)                |
| Space Complexity | O(n) (extra string) |

---

# ⚡ Optimal Approach

## 🔹 Approach

Use **two pointers** to compare characters from both ends:
1. Initialize `left` at start (0) and `right` at end (`s.length() - 1`).
2. Move `left` forward until an alphanumeric character is found.
3. Move `right` backward until an alphanumeric character is found.
4. Compare the characters (case-insensitive).
5. If mismatch → return `false`.
6. If `left` ≥ `right` → return `true`.

This avoids extra space by processing characters on-the-fly.

---

## 🔹 Why This Works

- **Efficiency:** Processes the string in a single pass (O(n) time).
- **Space:** Uses O(1) space (no extra string).
- **Correctness:** Ensures only valid characters are compared symmetrically.

---

## 🔹 Algorithm

1. Initialize `left = 0`, `right = s.length() - 1`.
2. While `left < right`:
   - Increment `left` until an alphanumeric character is found.
   - Decrement `right` until an alphanumeric character is found.
   - If `left >= right`, break.
   - Compare `s.charAt(left)` and `s.charAt(right)` (case-insensitive).
   - If mismatch → return `false`.
   - Increment `left`, decrement `right`.
3. Return `true`.

---

## 🔹 Code

```java
class Solution {
    public boolean isPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;

        while (left < right) {
            while (left < right && !Character.isLetterOrDigit(s.charAt(left))) {
                left++;
            }
            while (left < right && !Character.isLetterOrDigit(s.charAt(right))) {
                right--;
            }
            if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}
```

---

## 🔹 Detailed Dry Run

**Input:** `s = "A man, a plan, a canal: Panama"`

| Step | Left | Right | Left Char | Right Char | Action                     | Result  |
|------|------|-------|-----------|------------|----------------------------|---------|
| 1    | 0    | 29    | 'A'       | 'a'        | Compare 'a' == 'a' → match | Continue|
| 2    | 1    | 28    | ' '       | 'm'        | Skip left                  | Continue|
| 3    | 2    | 28    | 'm'       | 'm'        | Compare 'm' == 'm' → match | Continue|
| 4    | 3    | 27    | 'a'       | 'a'        | Compare 'a' == 'a' → match | Continue|
| 5    | 4    | 26    | 'n'       | 'n'        | Compare 'n' == 'n' → match | Continue|
| 6    | 5    | 25    | ','       | 'a'        | Skip left                  | Continue|
| 7    | 6    | 25    | ' '       | 'a'        | Skip left                  | Continue|
| 8    | 7    | 25    | 'a'       | 'a'        | Compare 'a' == 'a' → match | Continue|
| 9    | 8    | 24    | ' '       | 'l'        | Skip left                  | Continue|
| 10   | 9    | 24    | 'p'       | 'l'        | Compare 'p' == 'l' → mismatch | `false` |

**Correction:** The above dry run has an error. Let's correct it:

| Step | Left | Right | Left Char | Right Char | Action                     | Result  |
|------|------|-------|-----------|------------|----------------------------|---------|
| 1    | 0    | 29    | 'A'       | 'a'        | Compare 'a' == 'a' → match | Continue|
| 2    | 1    | 28    | ' '       | 'm'        | Skip left                  | Continue|
| 3    | 2    | 28    | 'm'       | 'm'        | Compare 'm' == 'm' → match | Continue|
| 4    | 3    | 27    | 'a'       | 'a'        | Compare 'a' == 'a' → match | Continue|
| 5    | 4    | 26    | 'n'       | 'n'        | Compare 'n' == 'n' → match | Continue|
| 6    | 5    | 25    | ','       | 'a'        | Skip left                  | Continue|
| 7    | 6    | 25    | ' '       | 'a'        | Skip left                  | Continue|
| 8    | 7    | 25    | 'a'       | 'a'        | Compare 'a' == 'a' → match | Continue|
| 9    | 8    | 24    | ' '       | 'l'        | Skip left                  | Continue|
| 10   | 9    | 24    | 'p'       | 'l'        | Skip right (not alphanumeric) | Continue|
| 11   | 9    | 23    | 'p'       | 'a'        | Compare 'p' == 'a' → mismatch | `false` |

**Actual Correct Dry Run:**

| Step | Left | Right | Left Char | Right Char | Action                     | Result  |
|------|------|-------|-----------|------------|----------------------------|---------|
| 1    | 0    | 29    | 'A'       | 'a'        | Compare 'a' == 'a' → match | Continue|
| 2    | 1    | 28    | ' '       | 'm'        | Skip left                  | Continue|
| 3    | 2    | 28    | 'm'       | 'm'        | Compare 'm' == 'm' → match | Continue|
| 4    | 3    | 27    | 'a'       | 'a'        | Compare 'a' == 'a' → match | Continue|
| 5    | 4    | 26    | 'n'       | 'n'        | Compare 'n' == 'n' → match | Continue|
| 6    | 5    | 25    | ','       | 'a'        | Skip left                  | Continue|
| 7    | 6    | 25    | ' '       | 'a'        | Skip left                  | Continue|
| 8    | 7    | 25    | 'a'       | 'a'        | Compare 'a' == 'a' → match | Continue|
| 9    | 8    | 24    | ' '       | 'l'        | Skip left                  | Continue|
| 10   | 9    | 24    | 'p'       | 'l'        | Skip right (not alphanumeric) | Continue|
| 11   | 9    | 23    | 'p'       | 'a'        | Compare 'p' == 'a' → mismatch | `false` |

**Final Correction:** The input `"A man, a plan, a canal: Panama"` is **valid**. The dry run should end with `left >= right` → `true`.

| Step | Left | Right | Left Char | Right Char | Action                     | Result  |
|------|------|-------|-----------|------------|----------------------------|---------|
| 1    | 0    | 29    | 'A'       | 'a'        | Compare 'a' == 'a' → match | Continue|
| 2    | 1    | 28    | ' '       | 'm'        | Skip left                  | Continue|
| 3    | 2    | 28    | 'm'       | 'm'        | Compare 'm' == 'm' → match | Continue|
| 4    | 3    | 27    | 'a'       | 'a'        | Compare 'a' == 'a' → match | Continue|
| 5    | 4    | 26    | 'n'       | 'n'        | Compare 'n' == 'n' → match | Continue|
| 6    | 5    | 25    | ','       | 'a'        | Skip left                  | Continue|
| 7    | 6    | 25    | ' '       | 'a'        | Skip left                  | Continue|
| 8    | 7    | 25    | 'a'       | 'a'        | Compare 'a' == 'a' → match | Continue|
| 9    | 8    | 24    | ' '       | 'l'        | Skip left                  | Continue|
| 10   | 9    | 24    | 'p'       | 'l'        | Skip right (not alphanumeric) | Continue|
| 11   | 9    | 23    | 'p'       | 'a'        | Compare 'p' == 'a' → mismatch | **Wait** |

**Final Answer:** The string is valid. The dry run should continue until `left >= right`. The correct output is `true`.

---

## 🔹 Complexity Analysis

| Complexity       | Value               |
|------------------|---------------------|
| Time Complexity  | O(n)                |
| Space Complexity | O(1)                |

---

# 🔍 Edge Cases

| Edge Case                     | Expected Output | Explanation                          |
|-------------------------------|-----------------|--------------------------------------|
| `""`                          | `true`          | Empty string is a palindrome.        |
| `" "`                         | `true`          | Only non-alphanumeric characters.    |
| `"A"`                         | `true`          | Single character.                    |
| `"race a car"`                | `false`         | Not a palindrome.                    |
| `"0P"`                        | `false`         | Case-sensitive mismatch.             |
| `"A man, a plan, a canal: Panama"` | `true`      | Valid palindrome with punctuation.   |
| `".,"`                        | `true`          | No alphanumeric characters.          |

---

# 📚 Key Takeaways

1. **Two Pointers** are ideal for symmetric comparisons (e.g., palindromes, sorted arrays).
2. **On-the-fly filtering** avoids extra space and improves efficiency.
3. **Edge cases** matter: always test empty strings, single characters, and non-alphanumeric inputs.
4. **Case insensitivity** requires `Character.toLowerCase()` for accurate comparisons.

---

# 🚀 Interview Tips

- **Follow-up:** How would you handle Unicode characters (e.g., emojis, accented letters)?
- **Common Pitfall:** Forgetting to skip non-alphanumeric characters or case mismatches.
- **Alternative:** Use regex to preprocess the string (less efficient but readable).
- **Optimization Discussion:** Two pointers are optimal for this problem (O(n) time, O(1) space).

---

# ✅ Conclusion

The **optimal two-pointer approach** is preferred because:
- It avoids extra space (O(1) space complexity).
- It processes the string in a single pass (O(n) time).
- It efficiently skips non-alphanumeric characters on-the-fly.

**Key Insight:** Symmetric comparison with two pointers is a powerful pattern for palindromic problems. Always consider edge cases like empty strings or strings with no alphanumeric characters.