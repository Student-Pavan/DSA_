# 📌 First Letter to Appear Twice

---

# 📝 Problem Statement

Given a string `s`, return the first character that appears **twice** in the string. If no character appears twice, return a null character (`'\0'`).

### **Objective**
Find the first repeated character in the string efficiently.

### **Input/Output Expectations**
- **Input:** A string `s` consisting of lowercase English letters.
- **Output:** The first character that appears twice, or `'\0'` if no duplicates exist.

### **Constraints**
- `1 <= s.length <= 100`
- `s` consists of lowercase English letters.

---

# 💡 Intuition

The problem requires identifying the first character that repeats in a string. The key insight is that we need to track characters as we iterate through the string and detect the first occurrence of a duplicate.

A brute-force approach would involve checking every character against all subsequent characters, but this is inefficient. An optimized approach uses a hash set to track seen characters, allowing us to detect duplicates in a single pass.

---

# 🐌 Brute Force Approach

## 🔹 Approach
The brute-force approach involves iterating through each character in the string and checking if it appears again later in the string. This requires nested loops:
1. Outer loop: Iterate through each character.
2. Inner loop: Check if the current character appears again in the remaining substring.

This approach is straightforward but inefficient due to its quadratic time complexity.

---

## 🔹 Algorithm
1. Iterate through each character in the string using index `i`.
2. For each character at index `i`, iterate through the remaining characters (from `i+1` to end).
3. If a duplicate is found, return the character immediately.
4. If no duplicates are found after all iterations, return `'\0'`.

---

## 🔹 Code

```java
class Solution {
    public char repeatedCharacter(String s) {
        for (int i = 0; i < s.length(); i++) {
            char current = s.charAt(i);
            for (int j = i + 1; j < s.length(); j++) {
                if (s.charAt(j) == current) {
                    return current;
                }
            }
        }
        return '\0';
    }
}
```

---

## 🔹 Dry Run

**Input:** `s = "abccbaacz"`

| Step | Outer Index (i) | Current Char | Inner Index (j) | Checked Char | Action                     | Result |
|------|-----------------|--------------|-----------------|--------------|----------------------------|--------|
| 1    | 0               | 'a'          | 1               | 'b'          | No match                   | -      |
| 2    | 0               | 'a'          | 2               | 'c'          | No match                   | -      |
| 3    | 0               | 'a'          | 3               | 'c'          | No match                   | -      |
| 4    | 0               | 'a'          | 4               | 'b'          | No match                   | -      |
| 5    | 0               | 'a'          | 5               | 'a'          | Match found                | 'a'    |

**Output:** `'a'`

---

## 🔹 Complexity Analysis

| Complexity       | Value       |
|------------------|-------------|
| Time Complexity  | O(n²)       |
| Space Complexity | O(1)        |

---

# ⚡ Optimal Approach

## 🔹 Approach
The optimal approach leverages a hash set to track characters as we iterate through the string. This allows us to detect duplicates in a single pass, reducing the time complexity to linear time.

### **Key Insight**
- Use a set to store characters we have already seen.
- If a character is already in the set, it is the first duplicate.

---

## 🔹 Why This Works
- The set provides O(1) average-time complexity for insertions and lookups.
- By checking the set before inserting, we ensure the first duplicate encountered is returned immediately.

---

## 🔹 Algorithm
1. Initialize an empty set to track seen characters.
2. Iterate through each character in the string.
3. If the character is already in the set, return it immediately.
4. Otherwise, add the character to the set.
5. If no duplicates are found, return `'\0'`.

---

## 🔹 Code

```java
import java.util.HashSet;
import java.util.Set;

class Solution {
    public char repeatedCharacter(String s) {
        Set<Character> seen = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            char current = s.charAt(i);
            if (seen.contains(current)) {
                return current;
            }
            seen.add(current);
        }
        return '\0';
    }
}
```

---

## 🔹 Detailed Dry Run

**Input:** `s = "abccbaacz"`

| Iteration | Current Char | Set State       | Action                     | Result |
|-----------|--------------|-----------------|----------------------------|--------|
| 1         | 'a'          | {}              | Add 'a' to set             | -      |
| 2         | 'b'          | {'a'}           | Add 'b' to set             | -      |
| 3         | 'c'          | {'a', 'b'}      | Add 'c' to set             | -      |
| 4         | 'c'          | {'a', 'b', 'c'} | Duplicate found            | 'c'    |

**Output:** `'c'`

---

## 🔹 Complexity Analysis

| Complexity       | Value       |
|------------------|-------------|
| Time Complexity  | O(n)        |
| Space Complexity | O(1)        |

*(Space is O(1) because the set size is bounded by the number of unique lowercase letters, which is constant.)*

---

# 🔍 Edge Cases

| Edge Case               | Expected Output | Explanation                                  |
|-------------------------|-----------------|----------------------------------------------|
| `s = "abc"`             | `'\0'`          | No duplicates                                |
| `s = "a"`               | `'\0'`          | Single character                             |
| `s = "aa"`              | `'a'`           | Duplicate at the start                       |
| `s = "abca"`            | `'a'`           | Duplicate at the end                         |
| `s = "aabbcc"`          | `'a'`           | Multiple duplicates, return first occurrence |

---

# 📚 Key Takeaways

1. **Brute Force vs. Optimal:** The brute-force approach is easy to implement but inefficient for large inputs. The optimal approach leverages a hash set for O(n) time complexity.
2. **Early Termination:** The optimal solution terminates as soon as the first duplicate is found, improving efficiency.
3. **Space-Time Tradeoff:** The optimal solution uses O(1) extra space (bounded by the alphabet size) to achieve linear time complexity.
4. **Pattern Recognition:** This problem is a classic example of using a hash set to track seen elements in a single pass.

---

# 🚀 Interview Tips

1. **Follow-Up Questions:**
   - How would you modify the solution to return the **last** duplicate instead of the first?
   - Can you solve this problem without using extra space? (Hint: Bit manipulation for lowercase letters.)
   - How would you handle case sensitivity or Unicode characters?

2. **Common Pitfalls:**
   - Forgetting to return `'\0'` when no duplicates exist.
   - Using a list instead of a set, which increases lookup time to O(n).
   - Not considering the bounded nature of lowercase letters (space complexity is O(1)).

3. **Alternative Approaches:**
   - **Bitmasking:** Use an integer as a bitmask to track seen characters (only works for lowercase letters).
   - **Frequency Array:** Use an array of size 26 to count occurrences (similar to the set approach but with fixed space).

---

# ✅ Conclusion

The optimal solution efficiently solves the problem in **O(n) time and O(1) space** by leveraging a hash set to track seen characters. This approach is both intuitive and scalable, making it ideal for interviews and production use. The key takeaway is recognizing when to trade a small amount of space for significant time savings, a common theme in algorithmic problem-solving.