# Valid Palindrome

---

# 📝 Problem Statement

A phrase is a **palindrome** if, after converting all uppercase letters into lowercase letters and removing all non-alphanumeric characters, it reads the same forward and backward. Alphanumeric characters include letters and numbers.

Given a string `s`, return `true` if it is a palindrome, or `false` otherwise.

**Constraints:**
- `1 <= s.length <= 2 * 10^5`
- `s` consists only of printable ASCII characters.

---

# 💡 Intuition

The key insight is that we need to compare characters from both ends of the string while ignoring non-alphanumeric characters. This can be efficiently done using a two-pointer approach where we move the pointers towards each other until they meet or cross each other.

---

# 🐌 Brute Force Approach

## 🔹 Approach

1. Create a new string by removing all non-alphanumeric characters and converting all letters to lowercase.
2. Compare the new string with its reverse to check if it's a palindrome.

## 🔹 Algorithm

1. Initialize an empty string `cleaned`.
2. Iterate through each character in the input string:
   - If the character is alphanumeric, convert it to lowercase and append to `cleaned`.
3. Compare `cleaned` with its reverse to determine if it's a palindrome.

## 🔹 Code

```java
class Solution {
    public boolean isPalindrome(String s) {
        String cleaned = s.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
        return cleaned.equals(new StringBuilder(cleaned).reverse().toString());
    }
}
```

## 🔹 Dry Run

Let's dry run the algorithm with the input string `s = "A man, a plan, a canal: Panama"`.

1. **Cleaning the string:**
   - Remove non-alphanumeric characters: `"A man, a plan, a canal: Panama"` → `"AmanaplanacanalPanama"`
   - Convert to lowercase: `"amanaplanacanalpanama"`

2. **Checking palindrome:**
   - Reverse of `"amanaplanacanalpanama"` is `"amanaplanacanalpanama"`.
   - Since the string equals its reverse, return `true`.

## 🔹 Complexity Analysis

| Complexity | Value |
|---|---|
| Time Complexity | O(n) |
| Space Complexity | O(n) |

---

# ⚡ Optimal Approach

## 🔹 Approach

Instead of creating a new string, we can use two pointers to compare characters from both ends of the original string, skipping non-alphanumeric characters on the fly.

## 🔹 Why This Works

This approach avoids the extra space used for creating a new string and directly compares characters in place, making it more space-efficient.

## 🔹 Algorithm

1. Initialize two pointers, `left` at the start and `right` at the end of the string.
2. While `left` is less than `right`:
   - Skip non-alphanumeric characters from the left.
   - Skip non-alphanumeric characters from the right.
   - Compare the characters at `left` and `right` (case-insensitive).
   - If they don't match, return `false`.
   - Move `left` forward and `right` backward.
3. If the loop completes without mismatches, return `true`.

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

## 🔹 Detailed Dry Run

Let's dry run the algorithm with the input string `s = "A man, a plan, a canal: Panama"`.

| Step | Left | Right | Left Char | Right Char | Action | Result |
|---|---|---|---|---|---|---|
| 1 | 0 | 21 | 'A' | 'a' | Compare | Match |
| 2 | 1 | 20 | ' ' | 'm' | Skip left | - |
| 3 | 2 | 20 | 'm' | 'm' | Compare | Match |
| 4 | 3 | 19 | 'a' | 'a' | Compare | Match |
| 5 | 4 | 18 | 'n' | 'n' | Compare | Match |
| 6 | 5 | 17 | ',' | 'a' | Skip left | - |
| 7 | 6 | 17 | ' ' | 'a' | Skip left | - |
| 8 | 7 | 17 | 'a' | 'a' | Compare | Match |
| 9 | 8 | 16 | ' ' | 'n' | Skip left | - |
| 10 | 9 | 16 | 'p' | 'n' | Compare | Match |
| 11 | 10 | 15 | 'l' | 'a' | Compare | Match |
| 12 | 11 | 14 | 'a' | 'n' | Compare | Match |
| 13 | 12 | 13 | 'n' | 'a' | Compare | Match |
| 14 | 13 | 12 | 'a' | 'c' | Compare | Match |
| 15 | 14 | 11 | ' ' | 'a' | Skip left | - |
| 16 | 15 | 11 | 'c' | 'a' | Compare | Match |
| 17 | 16 | 10 | 'a' | 'l' | Compare | Match |
| 18 | 17 | 9 | 'n' | 'p' | Compare | Match |
| 19 | 18 | 8 | 'a' | ' ' | Skip right | - |
| 20 | 18 | 7 | 'a' | 'a' | Compare | Match |
| 21 | 19 | 6 | 'l' | ' ' | Skip right | - |
| 22 | 19 | 5 | 'l' | ',' | Skip right | - |
| 23 | 19 | 4 | 'l' | ' ' | Skip right | - |
| 24 | 19 | 3 | 'l' | 'a' | Compare | Match |
| 25 | 20 | 2 | 'a' | 'm' | Compare | Match |
| 26 | 21 | 1 | 'a' | ' ' | Skip right | - |
| 27 | 21 | 0 | 'a' | 'A' | Compare | Match |

Since all comparisons match, the algorithm returns `true`.

## 🔹 Complexity Analysis

| Complexity | Value |
|---|---|
| Time Complexity | O(n) |
| Space Complexity | O(1) |

---

# 🔍 Edge Cases

- Empty string: `""` → `true`
- Single character: `"a"` → `true`
- All non-alphanumeric characters: `" , . !"` → `true`
- Mixed case and non-alphanumeric characters: `"A man, a plan, a canal: Panama"` → `true`
- Non-palindrome: `"race a car"` → `false`

---

# 📚 Key Takeaways

- **Two-pointer technique** is efficient for problems involving string traversal.
- **In-place comparison** avoids extra space usage.
- **Character validation** is crucial for ignoring non-alphanumeric characters.
- **Case insensitivity** requires converting characters to the same case before comparison.

---

# 🚀 Interview Tips

- Discuss the trade-offs between the brute force and optimal approaches.
- Ask if the input string is guaranteed to be alphanumeric to simplify the problem.
- Consider if the solution needs to handle Unicode characters, which might require additional handling.

---

# ✅ Conclusion

The optimal approach using two pointers is preferred for its O(n) time complexity and O(1) space complexity, making it efficient and scalable for large input sizes. The key insight is to compare characters from both ends while skipping non-alphanumeric characters, ensuring optimal performance.