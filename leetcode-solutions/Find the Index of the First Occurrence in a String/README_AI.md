# 📌 Find the Index of the First Occurrence in a String

---

# 📝 Problem Statement

Given two strings `haystack` and `needle`, return the index of the first occurrence of `needle` in `haystack`, or `-1` if `needle` is not part of `haystack`.

### **Objective**
Find the starting position where `needle` appears as a substring in `haystack`.

### **Input/Output Expectations**
- **Input:** `haystack` (String), `needle` (String)
- **Output:** Integer representing the first index of `needle` in `haystack`, or `-1` if not found.

### **Constraints**
- `1 <= haystack.length <= 10^4`
- `1 <= needle.length <= 10^4`
- `haystack` and `needle` consist of lowercase English letters.

---

# 💡 Intuition

The problem requires finding the first occurrence of a substring (`needle`) within a larger string (`haystack`). The key insight is that we need to check every possible starting position in `haystack` where `needle` could begin and verify if the subsequent characters match `needle`.

A brute-force approach checks every possible starting index, while an optimized approach (like the Knuth-Morris-Pratt algorithm) avoids unnecessary re-checking of characters by leveraging pattern preprocessing.

---

# 🐌 Brute Force Approach

## 🔹 Approach
The brute-force method involves checking every possible starting index in `haystack` for a match with `needle`. For each index `i` in `haystack`, we compare the substring starting at `i` with `needle`. If a full match is found, we return `i`; otherwise, we continue checking the next index.

## 🔹 Algorithm
1. Iterate over each possible starting index `i` in `haystack` from `0` to `haystack.length() - needle.length()`.
2. For each `i`, compare the substring `haystack.substring(i, i + needle.length())` with `needle`.
3. If a match is found, return `i`.
4. If no match is found after all iterations, return `-1`.

## 🔹 Code

```java
class Solution {
    public int strStr(String haystack, String needle) {
        int n = haystack.length();
        int m = needle.length();

        for (int i = 0; i <= n - m; i++) {
            boolean match = true;
            for (int j = 0; j < m; j++) {
                if (haystack.charAt(i + j) != needle.charAt(j)) {
                    match = false;
                    break;
                }
            }
            if (match) {
                return i;
            }
        }
        return -1;
    }
}
```

## 🔹 Dry Run

**Example:** `haystack = "sadbutsad"`, `needle = "sad"`

| Step | Starting Index (i) | Substring Checked | Match? | Action |
|------|---------------------|-------------------|--------|--------|
| 1    | 0                   | "sad"             | ✅     | Return 0 |
| (Terminates early) |

**Example:** `haystack = "leetcode"`, `needle = "leeto"`

| Step | Starting Index (i) | Substring Checked | Match? | Action |
|------|---------------------|-------------------|--------|--------|
| 1    | 0                   | "leet"            | ❌     | Continue |
| 2    | 1                   | "eeto"            | ❌     | Continue |
| 3    | 2                   | "etoc"            | ❌     | Continue |
| 4    | 3                   | "toco"            | ❌     | Continue |
| 5    | 4                   | "ocod"            | ❌     | Continue |
| (No match) | - | - | - | Return -1 |

## 🔹 Complexity Analysis

| Complexity       | Value               |
|------------------|---------------------|
| Time Complexity  | O((n - m + 1) * m)  |
| Space Complexity | O(1)                |

---

# ⚡ Optimal Approach (Knuth-Morris-Pratt Algorithm)

## 🔹 Approach
The KMP algorithm optimizes substring search by preprocessing `needle` to create a **Longest Prefix Suffix (LPS)** array. This array helps skip unnecessary comparisons by leveraging previously matched characters.

### **Key Insight**
- If a mismatch occurs after partial matching, we can use the LPS array to determine the next position in `needle` to resume matching without re-checking already matched characters.

## 🔹 Why This Works
- The LPS array stores the length of the longest proper prefix which is also a suffix for every substring of `needle`.
- This allows us to skip re-checking characters that are guaranteed to match, reducing the time complexity to **O(n + m)**.

## 🔹 Algorithm
1. **Preprocess `needle` to build the LPS array:**
   - Initialize `lps[0] = 0`.
   - Use two pointers (`len` and `i`) to fill the LPS array.
2. **Search for `needle` in `haystack`:**
   - Use two pointers (`i` for `haystack`, `j` for `needle`).
   - If characters match, move both pointers forward.
   - If a mismatch occurs, use the LPS array to update `j` without backtracking `i`.

## 🔹 Code

```java
class Solution {
    public int strStr(String haystack, String needle) {
        int n = haystack.length();
        int m = needle.length();
        if (m == 0) return 0;

        // Preprocess needle to build LPS array
        int[] lps = new int[m];
        int len = 0;
        int i = 1;
        while (i < m) {
            if (needle.charAt(i) == needle.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            } else {
                if (len != 0) {
                    len = lps[len - 1];
                } else {
                    lps[i] = 0;
                    i++;
                }
            }
        }

        // Search for needle in haystack
        i = 0; // index for haystack
        int j = 0; // index for needle
        while (i < n) {
            if (haystack.charAt(i) == needle.charAt(j)) {
                i++;
                j++;
                if (j == m) {
                    return i - j;
                }
            } else {
                if (j != 0) {
                    j = lps[j - 1];
                } else {
                    i++;
                }
            }
        }
        return -1;
    }
}
```

## 🔹 Detailed Dry Run

**Example:** `haystack = "aabaaabaaac"`, `needle = "aabaaac"`

### **Step 1: Build LPS Array for `needle`**
| Index | Character | LPS Value | Explanation |
|-------|-----------|-----------|-------------|
| 0     | 'a'       | 0         | No proper prefix/suffix |
| 1     | 'a'       | 1         | "a" matches "a" |
| 2     | 'b'       | 0         | Mismatch, reset `len` |
| 3     | 'a'       | 1         | "a" matches "a" |
| 4     | 'a'       | 2         | "aa" matches "aa" |
| 5     | 'a'       | 2         | Mismatch, use `lps[len-1]` |
| 6     | 'c'       | 0         | Mismatch, reset `len` |

**LPS Array:** `[0, 1, 0, 1, 2, 2, 0]`

### **Step 2: Search in `haystack`**
| Step | i (haystack) | j (needle) | Character Match? | Action |
|------|--------------|------------|------------------|--------|
| 1    | 0            | 0          | 'a' == 'a'       | i=1, j=1 |
| 2    | 1            | 1          | 'a' == 'a'       | i=2, j=2 |
| 3    | 2            | 2          | 'b' == 'b'       | i=3, j=3 |
| 4    | 3            | 3          | 'a' == 'a'       | i=4, j=4 |
| 5    | 4            | 4          | 'a' == 'a'       | i=5, j=5 |
| 6    | 5            | 5          | 'a' != 'c'       | j = lps[4] = 2 |
| 7    | 5            | 2          | 'a' == 'b'       | i=6, j=3 |
| 8    | 6            | 3          | 'a' == 'a'       | i=7, j=4 |
| 9    | 7            | 4          | 'a' == 'a'       | i=8, j=5 |
| 10   | 8            | 5          | 'a' == 'a'       | i=9, j=6 |
| 11   | 9            | 6          | 'c' == 'c'       | i=10, j=7 → Return i-j = 3 |

## 🔹 Complexity Analysis

| Complexity       | Value      |
|------------------|------------|
| Time Complexity  | O(n + m)   |
| Space Complexity | O(m)       |

---

# 🔍 Edge Cases

| Case | Example | Expected Output |
|------|---------|-----------------|
| `needle` is empty | `haystack = "abc"`, `needle = ""` | `0` |
| `needle` not in `haystack` | `haystack = "abc"`, `needle = "d"` | `-1` |
| `needle` is `haystack` | `haystack = "abc"`, `needle = "abc"` | `0` |
| `needle` appears multiple times | `haystack = "aaaaa"`, `needle = "aa"` | `0` |
| `needle` longer than `haystack` | `haystack = "abc"`, `needle = "abcd"` | `-1` |

---

# 📚 Key Takeaways

1. **Brute Force** is simple but inefficient for large inputs (O(n*m)).
2. **KMP Algorithm** optimizes substring search using pattern preprocessing (O(n + m)).
3. **LPS Array** is the key to KMP’s efficiency, avoiding unnecessary re-checks.
4. **Edge Cases** like empty strings or mismatched lengths must be handled explicitly.

---

# 🚀 Interview Tips

- **Follow-up:** How would you modify KMP to find all occurrences of `needle` in `haystack`?
- **Common Pitfall:** Forgetting to handle cases where `needle` is empty or longer than `haystack`.
- **Alternative Approaches:** Rabin-Karp (rolling hash) is another efficient method for substring search.
- **Optimization Discussion:** KMP is optimal for worst-case scenarios, while brute force may work better for small inputs due to lower constant factors.

---

# ✅ Conclusion

The **Knuth-Morris-Pratt (KMP) algorithm** is the optimal solution for this problem, offering linear time complexity by leveraging pattern preprocessing. While the brute-force approach is easier to implement, KMP is significantly more efficient for large inputs, making it the preferred choice in production and interview settings.

**Key Insight:** The LPS array allows us to skip unnecessary comparisons, transforming the problem into a linear-time solution. This demonstrates the power of preprocessing in algorithm design.