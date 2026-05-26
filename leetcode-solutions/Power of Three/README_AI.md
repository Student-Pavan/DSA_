# 📌 Power of Three

---

# 📝 Problem Statement

Given an integer `n`, return `true` if it is a power of three. Otherwise, return `false`.

An integer `n` is a power of three if there exists an integer `k` such that `n = 3^k`.

### **Constraints:**
- `-2³¹ <= n <= 2³¹ - 1`

### **Examples:**
| Input | Output | Explanation |
|-------|--------|-------------|
| 27    | true   | 3³ = 27     |
| 0     | false  | No power of 3 equals 0 |
| 9     | true   | 3² = 9      |
| 45    | false  | No power of 3 equals 45 |

---

# 💡 Intuition

A number is a power of three if it can be expressed as `3^k` where `k` is a non-negative integer. The key insight is recognizing that powers of three grow exponentially, and we can leverage mathematical properties to determine if a number fits this pattern without brute-force computation.

The brute-force approach involves repeatedly dividing the number by 3 until it either becomes 1 (indicating it's a power of three) or no longer divisible by 3 (indicating it's not). However, this approach can be inefficient for very large numbers.

The optimal approach leverages the mathematical property that the largest power of three representable in a 32-bit signed integer is `3^19 = 1162261467`. If this number is divisible by `n`, then `n` must be a power of three. This approach is both time and space efficient.

---

# 🐌 Brute Force Approach

## 🔹 Approach

The brute-force method involves repeatedly dividing the number by 3 while checking if the result remains an integer. If we eventually reach 1, the number is a power of three. If at any point the division does not yield an integer or the number becomes less than 1, it is not a power of three.

### **Steps:**
1. Handle edge cases (e.g., `n <= 0`).
2. While `n` is divisible by 3, divide `n` by 3.
3. If `n` becomes 1, return `true`; otherwise, return `false`.

---

## 🔹 Algorithm

1. If `n <= 0`, return `false` (since powers of three are positive).
2. While `n % 3 == 0`, update `n = n / 3`.
3. If `n == 1`, return `true`; else, return `false`.

---

## 🔹 Code

```java
class Solution {
    public boolean isPowerOfThree(int n) {
        if (n <= 0) {
            return false;
        }
        while (n % 3 == 0) {
            n /= 3;
        }
        return n == 1;
    }
}
```

---

## 🔹 Dry Run

**Input:** `n = 27`

| Step | n (Before Division) | n % 3 == 0 | n (After Division) | Action                     |
|------|---------------------|------------|--------------------|----------------------------|
| 1    | 27                  | true       | 9                  | Divide by 3                |
| 2    | 9                   | true       | 3                  | Divide by 3                |
| 3    | 3                   | true       | 1                  | Divide by 3                |
| 4    | 1                   | false      | -                  | Check if `n == 1` → `true` |

**Output:** `true`

---

**Input:** `n = 45`

| Step | n (Before Division) | n % 3 == 0 | n (After Division) | Action                     |
|------|---------------------|------------|--------------------|----------------------------|
| 1    | 45                  | true       | 15                 | Divide by 3                |
| 2    | 15                  | true       | 5                  | Divide by 3                |
| 3    | 5                   | false      | -                  | Check if `n == 1` → `false`|

**Output:** `false`

---

## 🔹 Complexity Analysis

| Complexity      | Value       |
|-----------------|-------------|
| Time Complexity | O(log₃ n)   |
| Space Complexity| O(1)        |

---

# ⚡ Optimal Approach

## 🔹 Approach

The optimal approach leverages the fact that the largest power of three representable in a 32-bit signed integer is `3^19 = 1162261467`. If `n` is a positive divisor of this number, then `n` must be a power of three. This approach avoids loops and runs in constant time.

### **Why This Works:**
- All powers of three up to `3^19` are divisors of `1162261467`.
- If `1162261467 % n == 0`, then `n` must be a power of three.

---

## 🔹 Algorithm

1. If `n <= 0`, return `false`.
2. Check if `1162261467 % n == 0`.
3. If true, return `true`; else, return `false`.

---

## 🔹 Code

```java
class Solution {
    public boolean isPowerOfThree(int n) {
        return n > 0 && 1162261467 % n == 0;
    }
}
```

---

## 🔹 Detailed Dry Run

**Input:** `n = 27`

1. `n > 0` → `true`
2. `1162261467 % 27 == 0` → `true` (since `27 * 43046721 = 1162261467`)
3. **Output:** `true`

---

**Input:** `n = 45`

1. `n > 0` → `true`
2. `1162261467 % 45 == 0` → `false` (since `45 * 25828032 = 1162261440` and `1162261467 - 1162261440 = 27`)
3. **Output:** `false`

---

## 🔹 Complexity Analysis

| Complexity      | Value       |
|-----------------|-------------|
| Time Complexity | O(1)        |
| Space Complexity| O(1)        |

---

# 🔍 Edge Cases

| Edge Case          | Expected Output | Explanation                          |
|--------------------|-----------------|--------------------------------------|
| `n = 0`            | `false`         | 0 is not a power of three.           |
| `n = 1`            | `true`          | 3⁰ = 1.                              |
| `n = 3`            | `true`          | 3¹ = 3.                              |
| `n = -3`           | `false`         | Negative numbers are not powers of 3.|
| `n = 2147483647`   | `false`         | Largest 32-bit integer, not a power of 3. |
| `n = 1162261467`   | `true`          | 3¹⁹ = 1162261467.                    |

---

# 📚 Key Takeaways

1. **Brute Force vs. Optimal:** The brute-force approach is intuitive but less efficient for very large numbers. The optimal approach leverages mathematical properties for constant-time performance.
2. **Mathematical Insight:** Recognizing that all powers of three are divisors of the largest 32-bit power of three (`3^19`) is key to the optimal solution.
3. **Edge Cases Matter:** Always handle edge cases like `n <= 0` and `n = 1` explicitly.
4. **Efficiency:** The optimal solution is preferred in interviews due to its O(1) time and space complexity.

---

# 🚀 Interview Tips

1. **Follow-Up Questions:**
   - How would you handle this problem if the input were a `long` instead of an `int`?
   - Can you generalize this solution to check if a number is a power of `k` (where `k` is any integer)?
   - What is the largest power of three representable in a 64-bit integer?

2. **Common Pitfalls:**
   - Forgetting to handle negative numbers or zero.
   - Assuming the brute-force approach is always sufficient (it may not be for very large inputs).
   - Incorrectly calculating the largest power of three for the optimal approach.

3. **Alternative Approaches:**
   - **Logarithmic Approach:** Compute `log₃(n)` and check if it is an integer. However, this may introduce floating-point precision errors.
   - **String Conversion:** Convert the number to base 3 and check if it matches the pattern `100...0`. This is less efficient and not recommended.

4. **Optimization Discussion:**
   - The optimal approach is ideal for constrained environments (e.g., embedded systems) where loops are expensive.
   - Discuss trade-offs between readability and performance with the interviewer.

---

# ✅ Conclusion

The **optimal approach** is the clear winner for this problem due to its **O(1) time and space complexity**, making it highly efficient even for the upper constraint limits. The key insight—leveraging the largest power of three within the 32-bit integer range—demonstrates how mathematical properties can lead to elegant and efficient solutions.

For interviews, always prioritize **clarity, correctness, and efficiency**. The brute-force approach is a great starting point to demonstrate problem-solving skills, but the optimal solution showcases your ability to think beyond the obvious and optimize effectively.