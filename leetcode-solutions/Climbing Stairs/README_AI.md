# 📌 Climbing Stairs

---

# 📝 Problem Statement

You are climbing a staircase. It takes `n` steps to reach the top.

Each time you can either climb **1** or **2** steps. In how many distinct ways can you climb to the top?

### **Objective**
Return the number of distinct ways to climb `n` stairs using steps of 1 or 2.

### **Input/Output Expectations**
- **Input:** An integer `n` (1 ≤ `n` ≤ 45)
- **Output:** An integer representing the number of distinct ways to climb the stairs.

### **Constraints**
- The input `n` is guaranteed to be a positive integer.
- The solution must efficiently handle the upper constraint (`n = 45`).

---

# 💡 Intuition

This problem is a classic example of the **Fibonacci sequence** in disguise. The key insight is recognizing that the number of ways to reach the `n-th` step is the sum of the ways to reach the `(n-1)-th` step (from which you take a single step) and the `(n-2)-th` step (from which you take a double step).

This recursive relationship suggests that dynamic programming (DP) can be used to optimize the solution by avoiding redundant calculations.

---

# 🐌 Brute Force Approach

## 🔹 Approach

The brute force approach directly translates the problem into a recursive solution:
- To reach step `n`, you can come from step `n-1` or `n-2`.
- The total ways to reach `n` is the sum of ways to reach `n-1` and `n-2`.
- Base cases:
  - `n = 0`: 1 way (doing nothing)
  - `n = 1`: 1 way (single step)

This approach recalculates the same subproblems repeatedly, leading to exponential time complexity.

---

## 🔹 Algorithm

1. If `n == 0` or `n == 1`, return `1`.
2. Recursively compute the number of ways for `n-1` and `n-2`.
3. Return the sum of the two recursive calls.

---

## 🔹 Code

```java
class Solution {
    public int climbStairs(int n) {
        if (n == 0 || n == 1) {
            return 1;
        }
        return climbStairs(n - 1) + climbStairs(n - 2);
    }
}
```

---

## 🔹 Dry Run

Let’s dry run `n = 4`:

| Step | Recursive Call       | Return Value | Explanation                          |
|------|----------------------|--------------|--------------------------------------|
| 1    | `climbStairs(4)`     | -            | Calls `climbStairs(3) + climbStairs(2)` |
| 2    | `climbStairs(3)`     | -            | Calls `climbStairs(2) + climbStairs(1)` |
| 3    | `climbStairs(2)`     | -            | Calls `climbStairs(1) + climbStairs(0)` |
| 4    | `climbStairs(1)`     | 1            | Base case                            |
| 5    | `climbStairs(0)`     | 1            | Base case                            |
| 6    | `climbStairs(2)`     | 2            | `1 + 1 = 2`                          |
| 7    | `climbStairs(1)`     | 1            | Base case                            |
| 8    | `climbStairs(3)`     | 3            | `2 + 1 = 3`                          |
| 9    | `climbStairs(2)`     | -            | Calls `climbStairs(1) + climbStairs(0)` |
| 10   | `climbStairs(1)`     | 1            | Base case                            |
| 11   | `climbStairs(0)`     | 1            | Base case                            |
| 12   | `climbStairs(2)`     | 2            | `1 + 1 = 2`                          |
| 13   | `climbStairs(4)`     | 5            | `3 + 2 = 5`                          |

**Final Answer:** `5`

---

## 🔹 Complexity Analysis

| Complexity       | Value       |
|------------------|-------------|
| Time Complexity  | O(2ⁿ)       |
| Space Complexity | O(n)        |

**Explanation:**
- The recursion tree has a height of `n`, and each node branches into 2, leading to exponential time.
- The space complexity is due to the recursion stack depth.

---

# ⚡ Optimal Approach

## 🔹 Approach

The optimal approach uses **dynamic programming (DP)** to store intermediate results and avoid redundant calculations. We can either:
1. Use **memoization** (top-down DP) to cache recursive results.
2. Use **tabulation** (bottom-up DP) to iteratively compute results.

Here, we’ll use **tabulation** for efficiency and constant space optimization.

---

## 🔹 Why This Works

- The problem exhibits **optimal substructure** (solutions to subproblems can be used to solve larger problems).
- It also has **overlapping subproblems** (the same subproblems are solved multiple times).
- By storing results of subproblems, we avoid recalculating them, reducing time complexity to linear.

---

## 🔹 Algorithm

1. Initialize `dp[0] = 1` and `dp[1] = 1`.
2. For `i` from `2` to `n`:
   - `dp[i] = dp[i-1] + dp[i-2]`
3. Return `dp[n]`.

**Space Optimization:**
- Instead of storing the entire `dp` array, use two variables to track the last two values.

---

## 🔹 Code

```java
class Solution {
    public int climbStairs(int n) {
        if (n == 0 || n == 1) {
            return 1;
        }
        int prev1 = 1; // Represents dp[i-1]
        int prev2 = 1; // Represents dp[i-2]
        for (int i = 2; i <= n; i++) {
            int current = prev1 + prev2;
            prev2 = prev1;
            prev1 = current;
        }
        return prev1;
    }
}
```

---

## 🔹 Detailed Dry Run

Let’s dry run `n = 4` using the optimal approach:

| Iteration | `i` | `prev2` | `prev1` | `current` | Explanation                     |
|-----------|-----|---------|---------|-----------|---------------------------------|
| 1         | 2   | 1       | 1       | 2         | `1 + 1 = 2`                     |
| 2         | 3   | 1       | 2       | 3         | `2 + 1 = 3`                     |
| 3         | 4   | 2       | 3       | 5         | `3 + 2 = 5`                     |

**Final Answer:** `5`

---

## 🔹 Complexity Analysis

| Complexity       | Value       |
|------------------|-------------|
| Time Complexity  | O(n)        |
| Space Complexity | O(1)        |

**Explanation:**
- We iterate from `2` to `n` once, leading to linear time.
- Only two variables are used, leading to constant space.

---

# 🔍 Edge Cases

| Edge Case          | Expected Output | Explanation                          |
|--------------------|-----------------|--------------------------------------|
| `n = 1`            | 1               | Only one way: take a single step.    |
| `n = 2`            | 2               | Two ways: (1+1) or (2).              |
| `n = 3`            | 3               | Three ways: (1+1+1), (1+2), (2+1).   |
| `n = 45`           | 1836311903      | Upper constraint; must handle efficiently. |

---

# 📚 Key Takeaways

1. **Fibonacci Pattern:** The problem reduces to the Fibonacci sequence, a common DSA pattern.
2. **Dynamic Programming:** Optimal substructure and overlapping subproblems make DP a natural fit.
3. **Space Optimization:** The tabulation approach can be optimized to use constant space.
4. **Recursion vs. Iteration:** Recursion is intuitive but inefficient; iteration is optimal.

---

# 🚀 Interview Tips

1. **Follow-Up Questions:**
   - What if you could take 1, 2, or 3 steps at a time?
   - How would you handle very large `n` (e.g., `n = 1000`)?
2. **Common Pitfalls:**
   - Forgetting base cases in recursion.
   - Not optimizing space in DP solutions.
3. **Alternative Approaches:**
   - Memoization (top-down DP) is another valid approach but uses O(n) space.
   - Matrix exponentiation can solve this in O(log n) time (advanced).
4. **Optimization Discussion:**
   - Always ask if space optimization is possible (e.g., using variables instead of arrays).

---

# ✅ Conclusion

The **optimal solution** leverages dynamic programming to efficiently compute the number of ways to climb stairs in **O(n) time and O(1) space**. Recognizing the Fibonacci pattern is key to solving this problem and similar ones (e.g., "Decode Ways," "House Robber").

**Key Insight:** The problem’s recursive nature makes it a perfect candidate for DP, and space optimization is achievable by tracking only the last two values.

**Final Takeaway:** Always look for overlapping subproblems and optimal substructure in recursive problems to apply DP effectively.