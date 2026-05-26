# 📌 Smallest Subsequence of Distinct Characters

**LeetCode 1081 | Medium**

---

# 📝 Problem Statement

Given a string `s`, return the **lexicographically smallest subsequence** of `s` that contains **all distinct characters** of `s` exactly once.

### Key Requirements:
- The subsequence must include **every distinct character** from `s` exactly once.
- The result must be the **lexicographically smallest** possible such subsequence.
- A **subsequence** is derived by deleting zero or more characters without changing the order of the remaining characters.

### Example:
**Input:** `s = "bcabc"`
**Output:** `"abc"`
**Explanation:** The smallest lexicographical subsequence containing all distinct characters is `"abc"`.

### Constraints:
- `1 <= s.length <= 1000`
- `s` consists of lowercase English letters.

---

# 💡 Intuition

To construct the lexicographically smallest subsequence containing all distinct characters, we need to:
1. **Preserve order** — characters must appear in the same order as in the original string.
2. **Include all distinct characters** — no character can be omitted.
3. **Minimize lexicographical order** — prioritize smaller characters as early as possible.

The key insight is to use a **greedy approach with a stack** to build the result:
- We want to include a character as early as possible (to keep the result small), but only if we can still include all remaining distinct characters later.
- We can **remove larger characters from the stack** if:
  - They appear later in the string (so we can add them again).
  - Removing them allows us to place a smaller character earlier.

This resembles the **"Remove Duplicate Letters"** problem and uses a **monotonic stack** pattern.

---

# 🐌 Brute Force Approach

## 🔹 Approach

A naive approach would involve:
- Generating **all possible subsequences** that contain all distinct characters.
- Selecting the **lexicographically smallest** one.

However, this is **computationally infeasible** due to exponential time complexity (O(2^n)), especially since `n` can be up to 1000.

Instead, we simulate a **recursive backtracking** approach that:
- Tracks which characters have been used.
- Ensures all distinct characters are included.
- Tries to build the smallest lexicographical result by choosing the smallest available character at each step.

This is still **inefficient** but conceptually simple.

---

## 🔹 Algorithm

1. Use a recursive function to build the result.
2. For each position in the string:
   - If the character is not yet used:
     - Try including it (if it helps in forming a smaller result).
     - Mark it as used.
     - Recurse on the remaining string.
     - Backtrack (unmark it).
3. Keep track of the smallest valid result found.

---

## 🔹 Code

```java
import java.util.*;

class Solution {
    public String smallestSubsequence(String s) {
        Set<Character> used = new HashSet<>();
        StringBuilder result = new StringBuilder();
        // We'll use backtracking to find the smallest subsequence
        // But this is too slow for large inputs, so we simulate a smarter version
        // This is a conceptual brute-force using recursion with pruning
        String[] ans = {null};
        backtrack(s, 0, new StringBuilder(), used, ans);
        return ans[0];
    }

    private void backtrack(String s, int index, StringBuilder current, Set<Character> used, String[] ans) {
        if (current.length() == used.size() && used.size() == getDistinctCount(s)) {
            String candidate = current.toString();
            if (ans[0] == null || candidate.compareTo(ans[0]) < 0) {
                ans[0] = candidate;
            }
            return;
        }

        if (index >= s.length()) {
            return;
        }

        char c = s.charAt(index);
        if (!used.contains(c)) {
            // Option 1: include c
            used.add(c);
            current.append(c);
            backtrack(s, index + 1, current, used, ans);
            current.deleteCharAt(current.length() - 1);
            used.remove(c);
        }

        // Option 2: skip c
        backtrack(s, index + 1, current, used, ans);
    }

    private int getDistinctCount(String s) {
        Set<Character> set = new HashSet<>();
        for (char c : s.toCharArray()) {
            set.add(c);
        }
        return set.size();
    }
}
```

> ⚠️ **Note:** This brute-force approach is **not efficient** and will **time out** on larger inputs. It is provided for **educational purposes only** to illustrate the problem logic.

---

## 🔹 Dry Run

**Input:** `s = "bcabc"`

We simulate a simplified version of the backtracking:

| Step | Action | Current String | Used Characters | Notes |
|------|--------|----------------|------------------|-------|
| 1 | Start | "" | {} | |
| 2 | Include 'b' | "b" | {b} | |
| 3 | Skip 'c' | "b" | {b} | |
| 4 | Include 'a' | "ba" | {a,b} | |
| 5 | Include 'b' → already used | skip | | |
| 6 | Include 'c' | "bac" | {a,b,c} | All distinct included |
| 7 | Compare: "bac" vs best → update best | | | |
| ... | Continue exploring | | | Other paths yield larger results |
| Final | Best result: "abc" | | | ✅ |

> This is a **simplified trace** — actual backtracking explores many invalid paths.

---

## 🔹 Complexity Analysis

| Complexity | Value |
|-----------|-------|
| Time Complexity | O(2^n) — exponential due to backtracking |
| Space Complexity | O(n) — recursion stack and storage |

---

# ⚡ Optimal Approach

## 🔹 Approach

We use a **greedy algorithm with a stack** and **frequency tracking** to build the result in **O(n)** time.

### Key Insights:
1. **Monotonic Stack**: We maintain a stack that keeps characters in **increasing order** (lex smallest).
2. **Frequency Map**: Track how many times each character appears **after** the current position.
3. **Used Set**: Ensure no character is added more than once.

### Algorithm:
- Traverse the string.
- For each character:
  - Decrement its frequency.
  - If already in stack, skip.
  - While stack is not empty, last character is greater than current, and that character appears later:
    - Pop it from stack and mark as unused.
  - Push current character onto stack and mark as used.

This ensures:
- All distinct characters are included.
- The result is lexicographically smallest.
- Order is preserved.

---

## 🔹 Why This Works

- By removing larger characters from the stack when they appear later, we allow smaller characters to come first.
- We only remove a character if it appears again later — ensuring it can be re-added.
- The stack naturally maintains the smallest valid subsequence at each step.

---

## 🔹 Algorithm

1. Count frequency of each character.
2. Use a boolean array `used` to track if a character is in the stack.
3. Use a stack to build the result.
4. For each character in `s`:
   - Decrement its frequency.
   - If already in stack, skip.
   - While stack is not empty, top > current char, and top appears later:
     - Pop from stack and mark as unused.
   - Push current char onto stack and mark as used.
5. Convert stack to string and return.

---

## 🔹 Code

```java
import java.util.*;

class Solution {
    public String smallestSubsequence(String s) {
        int[] freq = new int[26];
        boolean[] used = new boolean[26];
        Stack<Character> stack = new Stack<>();

        // Count frequency of each character
        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        for (char c : s.toCharArray()) {
            freq[c - 'a']--; // Decrement frequency as we process

            if (used[c - 'a']) continue; // Already in stack

            // While stack is not empty, last char > current, and last char appears later
            while (!stack.isEmpty() && stack.peek() > c && freq[stack.peek() - 'a'] > 0) {
                used[stack.pop() - 'a'] = false;
            }

            stack.push(c);
            used[c - 'a'] = true;
        }

        StringBuilder sb = new StringBuilder();
        for (char c : stack) {
            sb.append(c);
        }
        return sb.toString();
    }
}
```

---

## 🔹 Detailed Dry Run

**Input:** `s = "bcabc"`

Initialize:
- `freq = [1,1,2,0,...]` (a:1, b:2, c:2)
- `used = [false, false, false, ...]`
- `stack = []`

| Step | Char | Action | freq (a,b,c) | Stack | used (a,b,c) | Notes |
|------|------|--------|--------------|-------|--------------|-------|
| 1 | 'b' | freq[b]-- → 1 | [1,1,2] | [] | [f,f,f] | |
|    |      | push 'b' | | ["b"] | [f,t,f] | |
| 2 | 'c' | freq[c]-- → 1 | [1,1,1] | ["b"] | [f,t,f] | |
|    |      | 'c' > 'b'? Yes, but freq[b]=1 > 0 → no pop | | | | |
|    |      | push 'c' | | ["b","c"] | [f,t,t] | |
| 3 | 'a' | freq[a]-- → 0 | [0,1,1] | ["b","c"] | [f,t,t] | |
|    |      | 'a' < 'c' and freq[c]=1 > 0 → pop 'c' | | ["b"] | [f,t,f] | |
|    |      | 'a' < 'b' and freq[b]=1 > 0 → pop 'b' | | [] | [f,f,f] | |
|    |      | push 'a' | | ["a"] | [t,f,f] | |
| 4 | 'b' | freq[b]-- → 0 | [0,0,1] | ["a"] | [t,f,f] | |
|    |      | 'b' > 'a' and freq[a]=0 → no pop | | | | |
|    |      | push 'b' | | ["a","b"] | [t,t,f] | |
| 5 | 'c' | freq[c]-- → 0 | [0,0,0] | ["a","b"] | [t,t,f] | |
|    |      | 'c' > 'b' and freq[b]=0 → no pop | | | | |
|    |      | push 'c' | | ["a","b","c"] | [t,t,t] | |

**Final Stack:** `['a', 'b', 'c']` → `"abc"`

---

## 🔹 Complexity Analysis

| Complexity | Value |
|-----------|-------|
| Time Complexity | O(n) — each character is pushed and popped at most once |
| Space Complexity | O(1) — fixed-size arrays and stack (max 26 chars) |

---

# 🔍 Edge Cases

| Case | Input | Expected Output | Explanation |
|------|-------|-----------------|-----------|
| Empty string | `""` | `""` | No characters to include |
| Single character | `"a"` | `"a"` | Only one option |
| All same characters | `"aaaa"` | `"a"` | Only one distinct character |
| Already smallest | `"abc"` | `"abc"` | Already optimal |
| Reverse order | `"cba"` | `"abc"` | Must reorder to smallest |
| Repeated characters | `"cbacdcbc"` | `"acdb"` | Complex case requiring optimal removal |
| Large input | `"a" * 1000` | `"a"` | All same, return once |

---

# 📚 Key Takeaways

- **Greedy + Stack** is a powerful pattern for lexicographical ordering problems.
- **Frequency tracking** enables smart removal of characters that can be added later.
- **Monotonic stack** ensures the result is as small as possible at each step.
- This problem is **identical** to "Remove Duplicate Letters" (LeetCode 316).
- The optimal solution runs in **O(n)** time and **O(1)** space.

---

# 🚀 Interview Tips

### Common Follow-ups:
- What if the string contains uppercase letters?
  → Convert to lowercase or extend frequency array.
- What if we want the largest lexicographical subsequence?
  → Reverse the comparison and stack order.
- Can we solve this without a stack?
  → Possible with recursion + memo, but less efficient.
- How would you modify this for Unicode?
  → Use `HashMap` instead of fixed-size arrays.

### Pitfalls:
- Forgetting to decrement frequency before checking.
- Not checking if a character is already in the stack.
- Popping characters that don’t appear later.
- Not preserving order of characters.

### Alternative Approaches:
- **Recursive DFS with pruning** — too slow for large inputs.
- **Dynamic Programming** — overkill and complex for this problem.

---

# ✅ Conclusion

The **optimal solution** using a **greedy stack with frequency tracking** is the most efficient and elegant approach. It ensures:
- All distinct characters are included.
- The result is lexicographically smallest.
- Time complexity is linear.

**Key Insight:** You can **safely remove larger characters** from the result if they appear later in the string — this allows smaller characters to come first and reduces the overall lexicographical order.

This pattern is **highly reusable** in problems involving **subsequence construction with ordering constraints** and is a **must-know** for technical interviews.