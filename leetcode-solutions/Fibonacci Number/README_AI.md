# 📌 Fibonacci Number

---

# 📝 Problem Statement

The Fibonacci sequence is a classic mathematical sequence where each number is the sum of the two preceding ones, starting from 0 and 1. The sequence is defined as:

- F(0) = 0
- F(1) = 1
- F(n) = F(n-1) + F(n-2) for n > 1

**Objective:**
Given an integer `n`, compute the nth Fibonacci number.

**Input:**
- An integer `n` (0 ≤ n ≤ 30)

**Output:**
- The nth Fibonacci number

**Constraints:**
- The solution must efficiently compute the result for the given constraints.
- Avoid recalculating values unnecessarily.

---

# 💡 Intuition

The Fibonacci sequence is a fundamental example of recursion and dynamic programming. The key insight is recognizing that the problem can be broken down into smaller subproblems, where the solution to the larger problem depends on solutions to these subproblems. This makes it a perfect candidate for optimization using techniques like memoization or iterative computation to avoid redundant calculations.

---

# 🐌 Brute Force Approach

## 🔹 Approach

The brute force approach directly translates the mathematical definition of the Fibonacci sequence into a recursive function. This method is straightforward but highly inefficient because it recalculates the same Fibonacci numbers multiple times, leading to exponential time complexity.

---

## 🔹 Algorithm

1. **Base Case:** If `n` is 0, return 0.
2. **Base Case:** If `n` is 1, return 1.
3. **Recursive Case:** Return the sum of `fib(n-1)` and `fib(n-2)`.

---

## 🔹 Code

```java
class Solution {
    public int fib(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        return fib(n - 1) + fib(n - 2);
    }
}
```

---

## 🔹 Dry Run

Let’s compute `fib(4)` using the brute force approach:

| Step | Function Call | Return Value | Explanation                                                                 |
|------|---------------|--------------|-----------------------------------------------------------------------------|
| 1    | fib(4)        | fib(3) + fib(2) | fib(4) calls fib(3) and fib(2)                                             |
| 2    | fib(3)        | fib(2) + fib(1) | fib(3) calls fib(2) and fib(1)                                             |
| 3    | fib(2)        | fib(1) + fib(0) | fib(2) calls fib(1) and fib(0)                                             |
| 4    | fib(1)        | 1             | Base case: fib(1) returns 1                                                |
| 5    | fib(0)        | 0             | Base case: fib(0) returns 0                                                |
| 6    | fib(2)        | 1             | fib(2) = fib(1) + fib(0) = 1 + 0 = 1                                       |
| 7    | fib(1)        | 1             | Base case: fib(1) returns 1                                                |
| 8    | fib(3)        | 2             | fib(3) = fib(2) + fib(1) = 1 + 1 = 2                                       |
| 9    | fib(2)        | fib(1) + fib(0) | fib(2) calls fib(1) and fib(0) (recomputed)                                |
| 10   | fib(1)        | 1             | Base case: fib(1) returns 1                                                |
| 11   | fib(0)        | 0             | Base case: fib(0) returns 0                                                |
| 12   | fib(2)        | 1             | fib(2) = fib(1) + fib(0) = 1 + 0 = 1                                       |
| 13   | fib(4)        | 3             | fib(4) = fib(3) + fib(2) = 2 + 1 = 3                                       |

---

## 🔹 Complexity Analysis

| Complexity       | Value       |
|------------------|-------------|
| Time Complexity  | O(2ⁿ)       |
| Space Complexity | O(n)        |

**Explanation:**
- **Time Complexity:** The brute force approach recalculates the same Fibonacci numbers repeatedly, leading to exponential time complexity.
- **Space Complexity:** The space complexity is linear due to the recursion stack depth, which goes up to `n`.

---

# ⚡ Optimal Approach

## 🔹 Approach

The optimal approach leverages dynamic programming to store previously computed Fibonacci numbers, avoiding redundant calculations. This can be achieved using either memoization (top-down) or an iterative (bottom-up) approach. Here, we use the iterative approach for its constant space complexity and efficiency.

---

## 🔹 Why This Works

The iterative approach computes Fibonacci numbers in a bottom-up manner, starting from the base cases and building up to the desired `n`. By storing only the last two Fibonacci numbers at each step, we reduce the space complexity to O(1) while maintaining linear time complexity. This method ensures that each Fibonacci number is computed exactly once.

---

## 🔹 Algorithm

1. **Base Case:** If `n` is 0, return 0.
2. **Base Case:** If `n` is 1, return 1.
3. **Iterative Case:**
   - Initialize `prev2 = 0` (F(0)) and `prev1 = 1` (F(1)).
   - Iterate from 2 to `n`:
     - Compute `current = prev1 + prev2`.
     - Update `prev2 = prev1` and `prev1 = current`.
   - Return `prev1`.

---

## 🔹 Code

```java
class Solution {
    public int fib(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        int prev2 = 0; // F(0)
        int prev1 = 1; // F(1)
        int current = 0;
        for (int i = 2; i <= n; i++) {
            current = prev1 + prev2;
            prev2 = prev1;
            prev1 = current;
        }
        return current;
    }
}
```

---

## 🔹 Detailed Dry Run

Let’s compute `fib(4)` using the optimal approach:

| Iteration | i  | prev2 (F(i-2)) | prev1 (F(i-1)) | current (F(i)) | Explanation                     |
|-----------|----|----------------|----------------|----------------|---------------------------------|
| 1         | 2  | 0              | 1              | 1              | current = 1 + 0 = 1             |
| 2         | 3  | 1              | 1              | 2              | current = 1 + 1 = 2             |
| 3         | 4  | 1              | 2              | 3              | current = 2 + 1 = 3             |

**Final Result:** 3

---

## 🔹 Complexity Analysis

| Complexity       | Value       |
|------------------|-------------|
| Time Complexity  | O(n)        |
| Space Complexity | O(1)        |

**Explanation:**
- **Time Complexity:** The algorithm iterates from 2 to `n` exactly once, resulting in linear time complexity.
- **Space Complexity:** Only a constant amount of additional space is used to store the last two Fibonacci numbers, resulting in O(1) space complexity.

---

# 🔍 Edge Cases

| Edge Case          | Expected Output | Explanation                                  |
|--------------------|-----------------|----------------------------------------------|
| `n = 0`            | 0               | Base case: F(0) = 0                          |
| `n = 1`            | 1               | Base case: F(1) = 1                          |
| `n = 2`            | 1               | F(2) = F(1) + F(0) = 1 + 0 = 1               |
| `n = 10`           | 55              | F(10) = 55                                   |
| `n = 30`           | 832040          | Maximum constraint: F(30) = 832040           |

---

# 📚 Key Takeaways

1. **Recursion vs. Iteration:** While recursion is intuitive, it can lead to inefficiencies due to repeated calculations. Iterative approaches often provide better performance.
2. **Dynamic Programming:** Storing intermediate results (memoization) or building up solutions iteratively can drastically improve efficiency.
3. **Space Optimization:** For problems like Fibonacci, it’s possible to reduce space complexity to O(1) by only storing necessary previous values.
4. **Trade-offs:** Understand the trade-offs between time and space complexity when choosing an approach.

---

# 🚀 Interview Tips

1. **Follow-up Questions:**
   - How would you modify the solution to handle very large `n` (e.g., `n = 1000`)?
   - Can you implement the solution using memoization? How does it compare to the iterative approach?
   - What is the time and space complexity of your solution?

2. **Common Pitfalls:**
   - Forgetting base cases (e.g., `n = 0` or `n = 1`).
   - Recomputing values in the brute force approach, leading to exponential time complexity.
   - Off-by-one errors in iterative loops.

3. **Alternative Approaches:**
   - **Memoization:** Store computed Fibonacci numbers in an array to avoid redundant calculations.
   - **Matrix Exponentiation:** Use matrix multiplication to compute Fibonacci numbers in O(log n) time.
   - **Closed-form Formula:** Use Binet’s formula for an O(1) time solution (though it may have precision issues for large `n`).

4. **Optimization Discussions:**
   - Discuss the trade-offs between time and space complexity.
   - Explain how dynamic programming can be applied to similar problems (e.g., climbing stairs, coin change).

---

# ✅ Conclusion

The Fibonacci sequence is a foundational problem that highlights the importance of choosing the right approach for efficiency. While the brute force recursive solution is simple and intuitive, it suffers from exponential time complexity due to redundant calculations. The optimal iterative approach, on the other hand, computes the result in linear time with constant space, making it far more efficient and scalable.

**Key Insight:** Recognizing overlapping subproblems and leveraging dynamic programming techniques can transform an inefficient solution into an optimal one. This problem serves as a great introduction to dynamic programming and the power of iterative computation.