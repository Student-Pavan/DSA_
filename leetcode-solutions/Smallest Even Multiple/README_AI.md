# 📌 Smallest Even Multiple

---

# 📝 Problem Statement

Given a positive integer `n`, return the smallest positive integer that is a multiple of both `2` and `n`.

### **Objective**
Find the smallest even number that is divisible by `n`.

### **Input**
- A single integer `n` (1 ≤ `n` ≤ 150)

### **Output**
- The smallest positive even multiple of `n`

### **Constraints**
- The solution must be efficient and correct for all valid inputs.
- The result must be the smallest such number.

---

# 💡 Intuition

The smallest even multiple of `n` is the smallest number that satisfies two conditions:
1. It is even (divisible by 2).
2. It is divisible by `n`.

Since the smallest even number is 2, we can observe:
- If `n` is even, then `n` itself is already even, so `n` is the answer.
- If `n` is odd, then the smallest even multiple must be `2 * n`, because `n` is not even, and `2n` is the smallest number divisible by both 2 and `n`.

This leads to a simple mathematical insight: the answer is either `n` (if `n` is even) or `2n` (if `n` is odd).

---

# 🐌 Brute Force Approach

## 🔹 Approach

A naive approach would be to iterate through all even numbers starting from 2 and check if each is divisible by `n`. The first such number found would be the answer.

This approach is simple but inefficient, especially for large `n`, as it may require up to `n/2` iterations in the worst case.

---

## 🔹 Algorithm

1. Start from the smallest even number (2).
2. For each even number, check if it is divisible by `n`.
3. If yes, return that number.
4. If not, move to the next even number.
5. Repeat until the correct multiple is found.

---

## 🔹 Code

```java
class Solution {
    public int smallestEvenMultiple(int n) {
        int multiple = 2;
        while (true) {
            if (multiple % n == 0) {
                return multiple;
            }
            multiple += 2;
        }
    }
}
```

---

## 🔹 Dry Run

Let’s dry run the brute force approach for `n = 5`:

| Iteration | Current Multiple | Divisible by `n`? | Action |
|-----------|------------------|-------------------|--------|
| 1         | 2                | 2 % 5 = 2 ≠ 0     | Continue |
| 2         | 4                | 4 % 5 = 4 ≠ 0     | Continue |
| 3         | 6                | 6 % 5 = 1 ≠ 0     | Continue |
| 4         | 8                | 8 % 5 = 3 ≠ 0     | Continue |
| 5         | 10               | 10 % 5 = 0        | Return 10 |

The algorithm returns `10` after 5 iterations.

---

## 🔹 Complexity Analysis

| Complexity       | Value       |
|------------------|-------------|
| Time Complexity  | O(n)        |
| Space Complexity | O(1)        |

- **Time:** In the worst case (when `n` is odd), the loop runs `n/2` times.
- **Space:** Only a constant amount of extra space is used.

---

# ⚡ Optimal Approach

## 🔹 Approach

The optimal approach leverages the mathematical insight that the smallest even multiple of `n` is:
- `n` if `n` is even.
- `2n` if `n` is odd.

This avoids any iteration and directly computes the result in constant time.

---

## 🔹 Why This Works

- If `n` is even, it is already divisible by 2, so `n` is the smallest even multiple.
- If `n` is odd, the smallest even number divisible by `n` is `2n` (since `n` is not even, and `2n` is the smallest number divisible by both 2 and `n`).

This approach is optimal because it reduces the problem to a single conditional check.

---

## 🔹 Algorithm

1. Check if `n` is even.
2. If yes, return `n`.
3. If no, return `2 * n`.

---

## 🔹 Code

```java
class Solution {
    public int smallestEvenMultiple(int n) {
        return (n % 2 == 0) ? n : 2 * n;
    }
}
```

---

## 🔹 Detailed Dry Run

Let’s dry run the optimal approach for `n = 5` and `n = 4`:

### **Case 1: `n = 5` (odd)**
- `n % 2 = 1` (odd) → return `2 * 5 = 10`

### **Case 2: `n = 4` (even)**
- `n % 2 = 0` (even) → return `4`

No iterations are needed; the result is computed instantly.

---

## 🔹 Complexity Analysis

| Complexity       | Value       |
|------------------|-------------|
| Time Complexity  | O(1)        |
| Space Complexity | O(1)        |

- **Time:** The solution involves a single conditional check and arithmetic operation.
- **Space:** No additional space is used beyond the input.

---

# 🔍 Edge Cases

| Case               | Input (`n`) | Expected Output | Explanation |
|--------------------|-------------|-----------------|-------------|
| Smallest `n`       | 1           | 2               | `2 * 1 = 2` |
| Even `n`           | 2           | 2               | `n` is even |
| Odd `n`            | 3           | 6               | `2 * 3 = 6` |
| Largest `n`        | 150         | 150             | `n` is even |
| Odd `n` (large)    | 149         | 298             | `2 * 149 = 298` |

---

# 📚 Key Takeaways

1. **Mathematical Insight > Brute Force:** Always look for mathematical patterns before resorting to iteration.
2. **Even/Odd Properties:** The parity of `n` (even or odd) often simplifies problems involving multiples.
3. **Constant Time Solutions:** Problems with simple mathematical properties can often be solved in O(1) time.
4. **Interview Efficiency:** Optimal solutions stand out in interviews and demonstrate strong problem-solving skills.

---

# 🚀 Interview Tips

1. **Follow-Up Questions:**
   - What if `n` could be negative? (The problem specifies positive integers, but this tests edge-case thinking.)
   - How would you generalize this to find the smallest multiple divisible by `k` numbers?
2. **Common Pitfalls:**
   - Forgetting that `n` itself could be even.
   - Overcomplicating the solution with unnecessary loops.
3. **Alternative Approaches:**
   - Using LCM (Least Common Multiple): The answer is `LCM(2, n)`, which simplifies to `2n / GCD(2, n)`. Since `GCD(2, n)` is 2 if `n` is even and 1 if `n` is odd, this also yields `n` or `2n`.
4. **Optimization Discussion:**
   - The optimal solution is already O(1), but discussing LCM/GCD can showcase deeper mathematical knowledge.

---

# ✅ Conclusion

The **optimal solution** is preferred because it:
- Runs in **constant time (O(1))**, making it highly efficient.
- Leverages a simple mathematical insight to avoid unnecessary computation.
- Is **clean, concise, and interview-ready**, demonstrating strong algorithmic thinking.

**Key Insight:** The smallest even multiple of `n` is either `n` (if `n` is even) or `2n` (if `n` is odd). This reduces the problem to a single conditional check.