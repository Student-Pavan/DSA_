# 📌 Permutation Sequence

---

# 📝 Problem Statement

The set `[1, 2, 3, ..., n]` contains a total of `n!` unique permutations. Given two integers `n` and `k`, return the `k-th` permutation sequence in lexicographical order.

### **Objective**
Find the `k-th` permutation of numbers from `1` to `n` without generating all permutations.

### **Input**
- `n`: An integer between `1` and `9` (inclusive).
- `k`: An integer between `1` and `n!` (inclusive).

### **Output**
- A string representing the `k-th` permutation sequence.

### **Constraints**
- `1 <= n <= 9`
- `1 <= k <= n!`

### **Examples**
| Input (n, k) | Output |
|--------------|--------|
| `3, 3`       | `"213"` |
| `4, 9`       | `"2314"` |

---

# 💡 Intuition

Generating all permutations and selecting the `k-th` one is inefficient for large `n` (e.g., `n = 9` results in `362880` permutations). Instead, we can leverage **factorial number system** properties to construct the permutation directly.

**Key Insight**:
- The first `(n-1)!` permutations start with `1`, the next `(n-1)!` start with `2`, and so on.
- We can determine each digit of the permutation by dividing `k` by the factorial of the remaining digits, adjusting `k` accordingly.

---

# 🐌 Brute Force Approach

## 🔹 Approach
Generate all permutations of `[1, 2, ..., n]` in lexicographical order and return the `k-th` one.

### **Steps**
1. Generate all permutations using backtracking.
2. Sort the permutations lexicographically.
3. Return the `(k-1)-th` index (0-based).

---

## 🔹 Algorithm
1. Initialize an empty list to store permutations.
2. Use backtracking to generate all permutations of `[1, 2, ..., n]`.
3. Sort the list lexicographically.
4. Return the `(k-1)-th` permutation as a string.

---

## 🔹 Code

```java
import java.util.ArrayList;
import java.util.List;

class Solution {
    public String getPermutation(int n, int k) {
        List<String> permutations = new ArrayList<>();
        backtrack(permutations, new StringBuilder(), new boolean[n + 1]);
        return permutations.get(k - 1);
    }

    private void backtrack(List<String> permutations, StringBuilder current, boolean[] used) {
        if (current.length() == used.length - 1) {
            permutations.add(current.toString());
            return;
        }

        for (int i = 1; i < used.length; i++) {
            if (!used[i]) {
                used[i] = true;
                current.append(i);
                backtrack(permutations, current, used);
                current.deleteCharAt(current.length() - 1);
                used[i] = false;
            }
        }
    }
}
```

---

## 🔹 Dry Run

**Input**: `n = 3, k = 3`

| Step | Current Permutation | Permutations List |
|------|---------------------|-------------------|
| 1    | `""`                | `[]`              |
| 2    | `"1"`               | `[]`              |
| 3    | `"12"`              | `[]`              |
| 4    | `"123"`             | `["123"]`         |
| 5    | `"13"`              | `["123"]`         |
| 6    | `"132"`             | `["123", "132"]`  |
| 7    | `"2"`               | `["123", "132"]`  |
| 8    | `"21"`              | `["123", "132"]`  |
| 9    | `"213"`             | `["123", "132", "213"]` |
| 10   | `"23"`              | `["123", "132", "213"]` |
| 11   | `"231"`             | `["123", "132", "213", "231"]` |
| 12   | `"3"`               | `["123", "132", "213", "231"]` |
| 13   | `"31"`              | `["123", "132", "213", "231"]` |
| 14   | `"312"`             | `["123", "132", "213", "231", "312"]` |
| 15   | `"32"`              | `["123", "132", "213", "231", "312"]` |
| 16   | `"321"`             | `["123", "132", "213", "231", "312", "321"]` |

**Result**: `permutations.get(2) = "213"`

---

## 🔹 Complexity Analysis

| Complexity       | Value               |
|------------------|---------------------|
| Time Complexity  | O(n! * n)           |
| Space Complexity | O(n! * n)           |

---

# ⚡ Optimal Approach

## 🔹 Approach
Construct the permutation digit by digit using factorial number system properties.

### **Key Steps**
1. Precompute factorials up to `n`.
2. Maintain a list of available digits.
3. For each position, determine the current digit by dividing `k` by the factorial of remaining digits.
4. Adjust `k` and update the list of available digits.

---

## 🔹 Why This Works
- Each digit in the permutation can be determined independently by leveraging factorial division.
- Avoids generating all permutations, reducing time complexity to **O(n²)**.

---

## 🔹 Algorithm
1. Compute factorials from `0!` to `(n-1)!`.
2. Initialize a list of digits `[1, 2, ..., n]`.
3. For each position `i` from `0` to `n-1`:
   - Compute the index of the current digit: `index = k / factorial[n - 1 - i]`.
   - Append the digit at `index` to the result.
   - Remove the digit from the list.
   - Update `k = k % factorial[n - 1 - i]`.

---

## 🔹 Code

```java
import java.util.ArrayList;
import java.util.List;

class Solution {
    public String getPermutation(int n, int k) {
        int[] factorial = new int[n + 1];
        factorial[0] = 1;
        for (int i = 1; i <= n; i++) {
            factorial[i] = factorial[i - 1] * i;
        }

        List<Integer> digits = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            digits.add(i);
        }

        StringBuilder result = new StringBuilder();
        k--; // Convert to 0-based index

        for (int i = 0; i < n; i++) {
            int index = k / factorial[n - 1 - i];
            result.append(digits.get(index));
            digits.remove(index);
            k %= factorial[n - 1 - i];
        }

        return result.toString();
    }
}
```

---

## 🔹 Detailed Dry Run

**Input**: `n = 4, k = 9`

| Step | i | k (0-based) | Factorial[n-1-i] | Index (k / fact) | Digit | Remaining Digits | Result |
|------|---|-------------|------------------|------------------|-------|------------------|--------|
| 1    | 0 | 8           | 6                | 1                | 2     | [1, 3, 4]        | "2"    |
| 2    | 1 | 2           | 2                | 1                | 3     | [1, 4]           | "23"   |
| 3    | 2 | 0           | 1                | 0                | 1     | [4]              | "231"  |
| 4    | 3 | 0           | 1                | 0                | 4     | []               | "2314" |

**Result**: `"2314"`

---

## 🔹 Complexity Analysis

| Complexity       | Value               |
|------------------|---------------------|
| Time Complexity  | O(n²)               |
| Space Complexity | O(n)                |

---

# 🔍 Edge Cases

| Case               | Description                          | Example (n, k) | Output  |
|--------------------|--------------------------------------|----------------|---------|
| Single digit       | `n = 1`                              | `1, 1`         | `"1"`   |
| First permutation  | `k = 1`                              | `3, 1`         | `"123"` |
| Last permutation   | `k = n!`                             | `3, 6`         | `"321"` |
| Middle permutation | `k` not at boundary                  | `4, 9`         | `"2314"`|
| Maximum `n`        | `n = 9`                              | `9, 1`         | `"123456789"` |

---

# 📚 Key Takeaways

1. **Factorial Number System**: Enables direct construction of permutations without generating all possibilities.
2. **Efficiency**: Optimal approach reduces time complexity from **O(n!)** to **O(n²)**.
3. **Digit Selection**: Each digit is determined by dividing `k` by the factorial of remaining digits.
4. **Index Adjustment**: Converting `k` to 0-based simplifies modular arithmetic.

---

# 🚀 Interview Tips

### **Follow-Up Questions**
- How would you handle duplicate digits in the input?
- Can you optimize space further by avoiding the digits list?
- What if `k` exceeds `n!`?

### **Common Pitfalls**
- Forgetting to convert `k` to 0-based index.
- Incorrect factorial precomputation.
- Off-by-one errors in digit selection.

### **Alternative Approaches**
- **Recursive Backtracking with Pruning**: Early termination once the `k-th` permutation is found.
- **Iterative Construction**: Using a stack to simulate backtracking.

---

# ✅ Conclusion

The **optimal approach** leverages factorial division to construct the `k-th` permutation in **O(n²)** time, making it highly efficient for the given constraints. The brute force method, while intuitive, is impractical for larger `n` due to its **O(n!)** complexity. Understanding the factorial number system is key to solving permutation problems efficiently in interviews.