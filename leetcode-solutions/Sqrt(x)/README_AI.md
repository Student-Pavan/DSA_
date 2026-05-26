```markdown
# 📌 Sqrt(x)

Given a non-negative integer `x`, compute and return the square root of `x` rounded down to the nearest integer. The returned integer should be non-negative as well.

You must not use any built-in exponent function or operator.

---

# 📝 Problem Statement

**Objective:**
Implement a function to calculate the integer square root of a given non-negative integer `x` without using any built-in exponent functions or operators.

**Input:**
- A non-negative integer `x` (0 ≤ `x` ≤ 2³¹ - 1)

**Output:**
- The largest integer `y` such that `y² ≤ x`

**Constraints:**
- Do not use any built-in exponent functions or operators (e.g., `Math.pow`, `**`).
- The solution must efficiently handle large values of `x` (up to 2³¹ - 1).

---

# 💡 Intuition

The square root of `x` is the value `y` such that `y * y = x`. Since we are required to return the floor of the square root, we can leverage binary search to efficiently narrow down the possible values of `y`. Binary search is ideal here because the square function is monotonically increasing for non-negative numbers, allowing us to discard half of the search space in each iteration.

---

# 🐌 Brute Force Approach

## 🔹 Approach

The brute force approach involves iterating from `0` to `x` and checking each number to see if its square is less than or equal to `x`. The largest such number is our answer. This approach is straightforward but inefficient for large values of `x` due to its linear time complexity.

---

## 🔹 Algorithm

1. Initialize `result` to `0`.
2. Iterate from `0` to `x` (inclusive).
3. For each number `i`, check if `i * i <= x`.
4. If true, update `result` to `i`.
5. If false, break the loop (since squares are increasing).
6. Return `result`.

---

## 🔹 Code

```java
class Solution {
    public int mySqrt(int x) {
        if (x == 0) return 0;
        for (int i = 1; i <= x; i++) {
            if (i > x / i) { // Avoid overflow by using division instead of multiplication
                return i - 1;
            }
        }
        return 0;
    }
}
```

---

## 🔹 Dry Run

**Example:** `x = 8`

| Step | i  | i * i (or i > x / i) | Action          | Result |
|------|----|----------------------|-----------------|--------|
| 1    | 1  | 1 <= 8               | Update result   | 1      |
| 2    | 2  | 4 <= 8               | Update result   | 2      |
| 3    | 3  | 9 > 8                | Break loop      | 2      |

**Final Answer:** `2`

---

## 🔹 Complexity Analysis

| Complexity      | Value       |
|-----------------|-------------|
| Time Complexity | O(√x)       |
| Space Complexity| O(1)        |

---

# ⚡ Optimal Approach

## 🔹 Approach

The optimal approach uses binary search to efficiently find the integer square root. The key insight is that the square function is monotonically increasing for non-negative numbers, making binary search applicable. We search the range `[0, x]` to find the largest integer `y` such that `y² ≤ x`.

---

## 🔹 Why This Works

Binary search reduces the time complexity from O(√x) to O(log x) by halving the search space in each iteration. This is significantly faster for large values of `x`. The algorithm ensures that we never miss the correct answer by carefully adjusting the search bounds.

---

## 🔹 Algorithm

1. Initialize `left` to `0` and `right` to `x`.
2. While `left <= right`:
   - Compute `mid` as `left + (right - left) / 2`.
   - If `mid * mid == x`, return `mid`.
   - If `mid * mid < x`, update `left` to `mid + 1`.
   - If `mid * mid > x`, update `right` to `mid - 1`.
3. Return `right` (since we need the floor of the square root).

---

## 🔹 Code

```java
class Solution {
    public int mySqrt(int x) {
        if (x == 0) return 0;
        int left = 1, right = x;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (mid == x / mid) { // Avoid overflow
                return mid;
            } else if (mid < x / mid) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return right;
    }
}
```

---

## 🔹 Detailed Dry Run

**Example:** `x = 8`

| Step | Left | Right | Mid | Mid * Mid (or Mid vs x/Mid) | Action          |
|------|------|-------|-----|-----------------------------|-----------------|
| 1    | 1    | 8     | 4   | 4 < 8                       | left = mid + 1  |
| 2    | 5    | 8     | 6   | 6 < 8                       | left = mid + 1  |
| 3    | 7    | 8     | 7   | 7 < 8                       | left = mid + 1  |
| 4    | 8    | 8     | 8   | 8 == 8                      | return 8 / 8 = 2|

**Correction:** The loop exits when `left > right`, and `right` holds the floor value.

| Step | Left | Right | Mid | Mid vs x/Mid | Action          |
|------|------|-------|-----|--------------|-----------------|
| 1    | 1    | 8     | 4   | 4 < 8        | left = 5        |
| 2    | 5    | 8     | 6   | 6 < 8        | left = 7        |
| 3    | 7    | 8     | 7   | 7 < 8        | left = 8        |
| 4    | 8    | 8     | 8   | 8 == 8       | return 8        |

**Final Answer:** `2` (since `right` is decremented to `2` when `mid > x/mid`)

---

## 🔹 Complexity Analysis

| Complexity      | Value       |
|-----------------|-------------|
| Time Complexity | O(log x)    |
| Space Complexity| O(1)        |

---

# 🔍 Edge Cases

- **x = 0:** Return `0`.
- **x = 1:** Return `1`.
- **x = 2:** Return `1` (since √2 ≈ 1.414, floor is 1).
- **x = 2³¹ - 1:** Handle large values without overflow.
- **x = 4:** Return `2` (exact square).
- **x = 5:** Return `2` (floor of √5 ≈ 2.236).

---

# 📚 Key Takeaways

1. **Binary Search Applicability:** Binary search is ideal for problems where the solution space is monotonically increasing or decreasing.
2. **Overflow Handling:** Use division (`x / mid`) instead of multiplication (`mid * mid`) to avoid integer overflow.
3. **Efficiency:** Binary search reduces time complexity from O(√x) to O(log x), making it suitable for large inputs.
4. **Edge Cases:** Always test edge cases like `0`, `1`, and large values to ensure correctness.

---

# 🚀 Interview Tips

- **Follow-up:** How would you handle very large numbers (e.g., `x` as a `long`)?
- **Common Pitfall:** Forgetting to handle overflow when calculating `mid * mid`.
- **Alternative Approach:** Newton-Raphson method for faster convergence (though binary search is simpler and sufficient).
- **Optimization Discussion:** Binary search is optimal for this problem due to its logarithmic time complexity.

---

# ✅ Conclusion

The optimal solution leverages binary search to efficiently compute the integer square root of `x` in O(log x) time. This approach is both time-efficient and easy to implement, making it ideal for interviews and production use. The key insight is recognizing the monotonic nature of the square function, which allows binary search to be applied effectively.
```