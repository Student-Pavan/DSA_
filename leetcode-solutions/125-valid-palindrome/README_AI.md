# Valid Palindrome

---

# 📝 Problem Statement

A phrase is a palindrome if, after converting all uppercase letters into lowercase letters and removing all non-alphanumeric characters, it reads the same forward and backward. Alphanumeric characters include letters and numbers.

Given a string `s`, return `true` if it is a palindrome, or `false` otherwise.

**Constraints:**

- `1 <= s.length <= 2 * 10^5`
- `s` consists only of printable ASCII characters.

---

# 💡 Intuition

The key insight is that we need to check if the string is a palindrome after normalizing it (converting to lowercase and removing non-alphanumeric characters). The optimal approach involves using two pointers starting from both ends of the string, moving towards the center while comparing characters.

---

# 🐌 Brute Force Approach

## 🔹 Approach

1. Create a new string by filtering out all non-alphanumeric characters and converting the remaining characters to lowercase.
2. Compare the filtered string with its reverse to check if it's a palindrome.

## 🔹 Algorithm

1. Initialize an empty string `filtered`.
2. Iterate through each character in the input string `s`:
   - If the character is alphanumeric, convert it to lowercase and append it to `filtered`.
3. Compare `filtered` with its reverse to determine if it's a palindrome.

## 🔹 Code

```java
class Solution {
    public boolean isPalindrome(String s) {
        String filtered = s.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
        String reversed = new StringBuilder(filtered).reverse().toString();
        return filtered.equals(reversed);
    }
}
```

## 🔹 Dry Run

| Step | Action | Result |
|---|---|---|
| 1 | Input: "A man, a plan, a canal: Panama" | filtered = "amanaplanacanalpanama" |
| 2 | Reverse filtered | reversed = "amanaplanacanalpanama" |
| 3 | Compare filtered and reversed | true |

## 🔹 Complexity Analysis

| Complexity | Value |
|---|---|
| Time Complexity | O(n) |
| Space Complexity | O(n) |

---

# ⚡ Optimal Approach

## 🔹 Approach

Use two pointers starting from the beginning and end of the string, moving towards the center while skipping non-alphanumeric characters and comparing characters case-insensitively.

## 🔹 Why This Works

This approach avoids creating a new string, thus optimizing space complexity. It directly compares characters in place, reducing the need for additional storage.

## 🔹 Algorithm

1. Initialize two pointers, `left` at the start and `right` at the end of the string.
2. While `left` is less than `right`:
   - Skip non-alphanumeric characters from the left.
   - Skip non-alphanumeric characters from the right.
   - Compare the characters at `left` and `right` case-insensitively.
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

| Step | Action | Result |
|---|---|---|
| 1 | Input: "A man, a plan, a canal: Panama" | left = 0, right = 27 |
| 2 | Skip non-alphanumeric | left = 0, right = 27 |
| 3 | Compare 'A' and 'a' | Match |
| 4 | Increment left, decrement right | left = 1, right = 26 |
| 5 | Skip non-alphanumeric | left = 4, right = 26 |
| 6 | Compare 'm' and 'a' | No match |
| 7 | Return false | |

## 🔹 Complexity Analysis

| Complexity | Value |
|---|---|
| Time Complexity | O(n) |
| Space Complexity | O(1) |

---

# 🔍 Edge Cases

- Empty string: `""` → `true`
- Single character: `"a"` → `true`
- All non-alphanumeric: `".,! "` → `true`
- Mixed case: `"RaceCar"` → `true`
- Long string with many non-alphanumeric characters: `"A man, a plan, a canal: Panama"` → `true`

---

# 📚 Key Takeaways

- **String Normalization**: Understanding how to normalize strings for comparison.
- **Two Pointer Technique**: Efficiently using two pointers to solve problems in linear time with constant space.
- **Character Handling**: Properly handling and comparing characters, especially case-insensitively.
- **Edge Cases**: Considering various edge cases to ensure robustness.

---

# 🚀 Interview Tips

- **Follow-up Questions**:
  - What if you can't use extra space?
  - How would you handle Unicode characters?
- **Common Pitfalls**:
  - Forgetting to skip non-alphanumeric characters.
  - Not handling case sensitivity properly.
- **Alternative Approaches**:
  - Using regular expressions for filtering.
  - Using a stack to reverse the string.

---

# ✅ Conclusion

The optimal approach using two pointers is preferred because it operates in O(n) time with O(1) space, making it efficient for large input sizes. The key insight is efficiently comparing characters in place without additional storage.