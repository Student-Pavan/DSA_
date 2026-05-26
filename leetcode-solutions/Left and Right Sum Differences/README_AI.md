# 📌 Left and Right Sum Differences

---

# 📝 Problem Statement

Given a **0-indexed** integer array `nums`, compute an array `answer` where:

- `answer[i] = |leftSum[i] - rightSum[i]|`
- `leftSum[i]` is the sum of all elements to the **left** of index `i` (exclusive)
- `rightSum[i]` is the sum of all elements to the **right** of index `i` (exclusive)

**Objective**: Return the `answer` array.

### Input
- `nums`: Integer array of length `n` where `1 <= n <= 1000`
- `-1000 <= nums[i] <= 1000`

### Output
- `answer`: Integer array of length `n`

### Constraints
- Time and space complexity should be efficient.
- The solution must handle edge cases like single-element arrays.

---

# 💡 Intuition

The problem requires computing the absolute difference between left and right sums for each index. A naive approach would compute left and right sums separately for every index, leading to **O(n²)** time. However, we can optimize by recognizing that:

- The **total sum** of the array can be computed once.
- The **left sum** at index `i` can be built incrementally.
- The **right sum** at index `i` can be derived as `totalSum - leftSum[i] - nums[i]`.

This reduces the problem to a **single pass** with **O(n)** time and **O(1)** auxiliary space (excluding output).

---

# 🐌 Brute Force Approach

## 🔹 Approach

For each index `i`:
1. Compute `leftSum[i]` by summing all elements before `i`.
2. Compute `rightSum[i]` by summing all elements after `i`.
3. Store `answer[i] = |leftSum[i] - rightSum[i]|`.

This approach is straightforward but inefficient due to repeated summation.

---

## 🔹 Algorithm

1. Initialize an empty `answer` array of size `n`.
2. For each index `i` from `0` to `n-1`:
   - Compute `leftSum` by iterating from `0` to `i-1`.
   - Compute `rightSum` by iterating from `i+1` to `n-1`.
   - Set `answer[i] = |leftSum - rightSum|`.
3. Return `answer`.

---

## 🔹 Code

```java
class Solution {
    public int[] leftRightDifference(int[] nums) {
        int n = nums.length;
        int[] answer = new int[n];

        for (int i = 0; i < n; i++) {
            int leftSum = 0;
            for (int j = 0; j < i; j++) {
                leftSum += nums[j];
            }

            int rightSum = 0;
            for (int j = i + 1; j < n; j++) {
                rightSum += nums[j];
            }

            answer[i] = Math.abs(leftSum - rightSum);
        }

        return answer;
    }
}
```

---

## 🔹 Dry Run

**Input**: `nums = [10, 4, 8, 3]`

| Step | Index (i) | Left Sum Calculation | Right Sum Calculation | `answer[i]` |
|------|-----------|----------------------|-----------------------|-------------|
| 1    | 0         | `leftSum = 0` (no left elements) | `rightSum = 4 + 8 + 3 = 15` | `|0 - 15| = 15` |
| 2    | 1         | `leftSum = 10` | `rightSum = 8 + 3 = 11` | `|10 - 11| = 1` |
| 3    | 2         | `leftSum = 10 + 4 = 14` | `rightSum = 3` | `|14 - 3| = 11` |
| 4    | 3         | `leftSum = 10 + 4 + 8 = 22` | `rightSum = 0` (no right elements) | `|22 - 0| = 22` |

**Final Answer**: `[15, 1, 11, 22]`

---

## 🔹 Complexity Analysis

| Complexity       | Value       |
|------------------|-------------|
| Time Complexity  | O(n²)       |
| Space Complexity | O(n) (output) |

---

# ⚡ Optimal Approach

## 🔹 Approach

1. Compute the **total sum** of the array.
2. Traverse the array while maintaining a **running left sum**.
3. For each index `i`:
   - `rightSum = totalSum - leftSum - nums[i]`
   - `answer[i] = |leftSum - rightSum|`
   - Update `leftSum += nums[i]` for the next iteration.

This avoids recomputing sums and reduces time complexity to **O(n)**.

---

## 🔹 Why This Works

- The **total sum** is constant.
- The **left sum** grows incrementally.
- The **right sum** is derived from the total and left sum, eliminating the need for nested loops.

---

## 🔹 Algorithm

1. Compute `totalSum` of `nums`.
2. Initialize `leftSum = 0` and `answer` array.
3. For each `i` from `0` to `n-1`:
   - `rightSum = totalSum - leftSum - nums[i]`
   - `answer[i] = |leftSum - rightSum|`
   - `leftSum += nums[i]`
4. Return `answer`.

---

## 🔹 Code

```java
class Solution {
    public int[] leftRightDifference(int[] nums) {
        int n = nums.length;
        int[] answer = new int[n];
        int totalSum = 0;

        // Compute total sum
        for (int num : nums) {
            totalSum += num;
        }

        int leftSum = 0;
        for (int i = 0; i < n; i++) {
            int rightSum = totalSum - leftSum - nums[i];
            answer[i] = Math.abs(leftSum - rightSum);
            leftSum += nums[i];
        }

        return answer;
    }
}
```

---

## 🔹 Detailed Dry Run

**Input**: `nums = [10, 4, 8, 3]`

| Iteration | `i` | `leftSum` (before update) | `rightSum` | `answer[i]` | `leftSum` (after update) |
|-----------|-----|---------------------------|------------|-------------|--------------------------|
| 1         | 0   | 0                         | 15         | 15          | 10                       |
| 2         | 1   | 10                        | 11         | 1           | 14                       |
| 3         | 2   | 14                        | 3          | 11          | 22                       |
| 4         | 3   | 22                        | 0          | 22          | 25                       |

**Final Answer**: `[15, 1, 11, 22]`

---

## 🔹 Complexity Analysis

| Complexity       | Value       |
|------------------|-------------|
| Time Complexity  | O(n)        |
| Space Complexity | O(n) (output) |

---

# 🔍 Edge Cases

| Case                     | Expected Output          | Explanation                          |
|--------------------------|--------------------------|--------------------------------------|
| `nums = []`              | `[]`                     | Empty input                          |
| `nums = [5]`             | `[0]`                    | Single element (no left/right sums)  |
| `nums = [1, 1, 1, 1]`    | `[3, 1, 1, 3]`           | Uniform values                       |
| `nums = [-1, -2, -3]`    | `[5, 3, 2]`              | Negative values                      |
| `nums = [1000, -1000]`   | `[1000, 1000]`           | Large values                         |

---

# 📚 Key Takeaways

- **Prefix Sum Optimization**: Recognize that left/right sums can be derived from a running total.
- **Single Pass Efficiency**: Avoid nested loops by leveraging cumulative sums.
- **Absolute Difference**: Use `Math.abs()` to handle negative differences.
- **Edge Handling**: Always consider single-element and empty arrays.

---

# 🚀 Interview Tips

### Follow-Up Questions
- How would you handle **very large arrays** (e.g., `n = 10^6`)?
  - The optimal approach is already efficient (O(n) time).
- Can you solve this **without extra space** (excluding output)?
  - Yes, the optimal solution uses O(1) auxiliary space.
- What if the problem required **modulo 10^9 + 7**?
  - Use `long` to avoid overflow before applying modulo.

### Common Pitfalls
- **Off-by-One Errors**: Ensure left/right sums exclude the current element.
- **Integer Overflow**: Use `long` for `totalSum` if constraints allow large values.
- **Edge Cases**: Forgetting to handle `n = 1` or empty arrays.

### Alternative Approaches
- **Two-Pass Prefix Sum**:
  - First pass: Compute left sums.
  - Second pass: Compute right sums using a suffix array.
  - Still O(n) time but uses O(n) extra space.

---

# ✅ Conclusion

The **optimal solution** leverages the **prefix sum technique** to compute left and right sums in **O(n)** time with **O(1)** auxiliary space. This is significantly more efficient than the brute-force approach and is the preferred method for interviews. The key insight is recognizing that the right sum can be derived from the total sum and left sum, eliminating the need for nested loops.

**Key Learning**: Always look for opportunities to **reuse computations** (e.g., cumulative sums) to optimize time complexity.