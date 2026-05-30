# 📌 Longest Substring Without Repeating Characters

---

# 📝 Problem Statement

Given a string `s`, find the length of the longest substring without repeating characters.

**Objective**: Find the maximum length of a substring where all characters are unique.

**Input**: A string `s` consisting of lowercase English letters, digits, symbols, and spaces.

**Output**: An integer representing the length of the longest substring without repeating characters.

**Constraints**:
- `0 <= s.length <= 5 * 10^4`
- The string may contain any printable ASCII characters.

---

# 💡 Intuition

The problem requires finding the longest substring where all characters are unique. The key insight is to recognize that a repeating character invalidates the current substring, and we need to start a new substring from the next character after the first occurrence of the duplicate.

This is a classic sliding window problem where we maintain a window of characters that haven't been repeated yet. The window expands as we encounter new characters and contracts when we encounter a duplicate.

---

# 🐌 Brute Force Approach

## 🔹 Approach

The brute force approach involves checking all possible substrings of the given string to find the longest one without repeating characters. For each starting index, we expand the substring to the right until we encounter a repeating character.

## 🔹 Algorithm

1. Initialize `maxLength` to 0.
2. Iterate over each character in the string using `i` as the starting index.
3. For each starting index `i`, initialize a set to keep track of characters in the current substring.
4. Iterate over the string starting from `i` to the end using `j` as the ending index.
5. If the character at `j` is already in the set, break out of the loop.
6. Otherwise, add the character to the set and update `maxLength` if the current substring length (`j - i + 1`) is greater than `maxLength`.
7. After the inner loop, return `maxLength`.

## 🔹 Code

```java
class Solution {
    public int lengthOfLongestSubstring(String s) {
        int maxLength = 0;
        for (int i = 0; i < s.length(); i++) {
            Set<Character> set = new HashSet<>();
            for (int j = i; j < s.length(); j++) {
                if (set.contains(s.charAt(j))) {
                    break;
                }
                set.add(s.charAt(j));
                maxLength = Math.max(maxLength, j - i + 1);
            }
        }
        return maxLength;
    }
}
```

## 🔹 Dry Run

Let's dry run the algorithm with the string `"abcabcbb"`.

| i | j | Current Character | Set | Action | maxLength |
|---|---|-------------------|-----|--------|-----------|
| 0 | 0 | 'a' | {'a'} | Add 'a' | 1 |
| 0 | 1 | 'b' | {'a', 'b'} | Add 'b' | 2 |
| 0 | 2 | 'c' | {'a', 'b', 'c'} | Add 'c' | 3 |
| 0 | 3 | 'a' | {'a', 'b', 'c'} | Break | 3 |
| 1 | 1 | 'b' | {'b'} | Add 'b' | 3 |
| 1 | 2 | 'c' | {'b', 'c'} | Add 'c' | 3 |
| 1 | 3 | 'a' | {'b', 'c'} | Break | 3 |
| 2 | 2 | 'c' | {'c'} | Add 'c' | 3 |
| 2 | 3 | 'a' | {'c', 'a'} | Add 'a' | 3 |
| 2 | 4 | 'b' | {'c', 'a', 'b'} | Add 'b' | 3 |
| 2 | 5 | 'c' | {'c', 'a', 'b'} | Break | 3 |
| 3 | 3 | 'a' | {'a'} | Add 'a' | 3 |
| 3 | 4 | 'b' | {'a', 'b'} | Add 'b' | 3 |
| 3 | 5 | 'c' | {'a', 'b', 'c'} | Add 'c' | 3 |
| 3 | 6 | 'b' | {'a', 'b', 'c'} | Break | 3 |
| 4 | 4 | 'b' | {'b'} | Add 'b' | 3 |
| 4 | 5 | 'c' | {'b', 'c'} | Add 'c' | 3 |
| 4 | 6 | 'b' | {'b', 'c'} | Break | 3 |
| 5 | 5 | 'c' | {'c'} | Add 'c' | 3 |
| 5 | 6 | 'b' | {'c', 'b'} | Add 'b' | 3 |
| 5 | 7 | 'b' | {'c', 'b'} | Break | 3 |
| 6 | 6 | 'b' | {'b'} | Add 'b' | 3 |
| 6 | 7 | 'b' | {'b'} | Break | 3 |

Final `maxLength` is 3.

## 🔹 Complexity Analysis

| Complexity | Value |
|---|---|
| Time Complexity | O(n^2) |
| Space Complexity | O(min(m, n)) |

---

# ⚡ Optimal Approach

## 🔹 Approach

The optimal approach uses a sliding window technique to maintain a window of characters that haven't been repeated yet. We use a set to keep track of characters in the current window. If a character is repeated, we move the left boundary of the window to the right of the first occurrence of the duplicate character.

## 🔹 Why This Works

This approach ensures that we only traverse the string once, making it more efficient than the brute force approach. The sliding window technique allows us to maintain the current substring without repeating characters, and we only need to traverse the string once.

## 🔹 Algorithm

1. Initialize `maxLength` to 0 and `left` to 0.
2. Iterate over the string using `right` as the ending index.
3. If the character at `right` is already in the set, remove the character at `left` from the set and increment `left`.
4. Add the character at `right` to the set.
5. Update `maxLength` if the current substring length (`right - left + 1`) is greater than `maxLength`.
6. After the loop, return `maxLength`.

## 🔹 Code

```java
class Solution {
    public int lengthOfLongestSubstring(String s) {
        Set<Character> set = new HashSet<>();
        int left = 0, maxLength = 0;

        for (int right = 0; right < s.length(); right++) {
            char currentChar = s.charAt(right);
            while (set.contains(currentChar)) {
                set.remove(s.charAt(left));
                left++;
            }
            set.add(currentChar);
            maxLength = Math.max(maxLength, right - left + 1);
        }
        return maxLength;
    }
}
```

## 🔹 Detailed Dry Run

Let's dry run the algorithm with the string `"abcabcbb"`.

| Step | Left | Right | Current Character | Set | Action | maxLength |
|---|---|---|-------------------|-----|--------|-----------|
| 1 | 0 | 0 | 'a' | {'a'} | Add 'a' | 1 |
| 2 | 0 | 1 | 'b' | {'a', 'b'} | Add 'b' | 2 |
| 3 | 0 | 2 | 'c' | {'a', 'b', 'c'} | Add 'c' | 3 |
| 4 | 0 | 3 | 'a' | {'a', 'b', 'c'} | Remove 'a' | 3 |
| 5 | 1 | 3 | 'a' | {'b', 'c', 'a'} | Add 'a' | 3 |
| 6 | 1 | 4 | 'b' | {'b', 'c', 'a'} | Remove 'b' | 3 |
| 7 | 2 | 4 | 'b' | {'c', 'a', 'b'} | Add 'b' | 3 |
| 8 | 2 | 5 | 'c' | {'c', 'a', 'b'} | Remove 'c' | 3 |
| 9 | 3 | 5 | 'c' | {'a', 'b', 'c'} | Add 'c' | 3 |
| 10 | 3 | 6 | 'b' | {'a', 'b', 'c'} | Remove 'a' | 3 |
| 11 | 4 | 6 | 'b' | {'b', 'c'} | Add 'b' | 3 |
| 12 | 4 | 7 | 'b' | {'b', 'c'} | Remove 'b' | 3 |
| 13 | 5 | 7 | 'b' | {'c', 'b'} | Add 'b' | 3 |

Final `maxLength` is 3.

## 🔹 Complexity Analysis

| Complexity | Value |
|---|---|
| Time Complexity | O(n) |
| Space Complexity | O(min(m, n)) |

---

# 🔍 Edge Cases

- Empty string: `""` → Output: `0`
- Single character string: `"a"` → Output: `1`
- All unique characters: `"abcdef"` → Output: `6`
- All repeating characters: `"aaaaa"` → Output: `1`
- Mixed characters with repeating sequences: `"pwwkew"` → Output: `3`
- String with spaces and symbols: `"a b c a b c"` → Output: `6`

---

# 📚 Key Takeaways

- The sliding window technique is efficient for problems involving substrings or subarrays.
- Using a set to track characters in the current window helps in maintaining uniqueness.
- The optimal approach reduces the time complexity from O(n^2) to O(n) by avoiding nested loops.

---

# 🚀 Interview Tips

- Discuss the trade-offs between the brute force and optimal approaches.
- Ask about follow-up questions like finding the actual substring instead of just its length.
- Be prepared to explain the time and space complexity of both approaches.

---

# ✅ Conclusion

The optimal solution using the sliding window technique is preferred due to its efficiency and clarity. The key insight is maintaining a window of unique characters and adjusting the window when a duplicate is encountered. This approach ensures optimal performance with a time complexity of O(n).