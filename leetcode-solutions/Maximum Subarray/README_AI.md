```markdown
# 📌 Maximum Subarray

---

# 📝 Problem Statement

Given an integer array `nums`, find the **contiguous subarray** (containing at least one number) which has the **largest sum** and return its sum.

### **Objective**
Identify the contiguous subarray within `nums` that yields the maximum possible sum.

### **Input**
- An integer array `nums` of size `n` where `1 <= n <= 10^5`
- Each element in `nums` can be positive, negative, or zero

### **Output**
- An integer representing the maximum subarray sum

### **Constraints**
- The solution must efficiently handle the upper constraint (`n = 10^5`)
- The array may contain negative numbers

### **Example**
**Input:** `nums = [-2,1,-3,4,-1,2,1,-5,4]`
**Output:** `6`
**Explanation:** The contiguous subarray `[4,-1,2,1]` has the largest sum = `6`.

---

# 💡 Intuition

The problem requires finding a contiguous segment of the array that sums to the maximum possible value. The key insight is recognizing that **a negative prefix reduces the sum of any subarray that includes it**. Therefore, we can **reset our subarray** whenever the current sum becomes negative, as starting fresh from the next element would yield a better result.

This leads to **Kadane's Algorithm**, which efficiently computes the maximum subarray sum in **O(n)** time by maintaining a running sum and resetting it when it becomes negative.

---

# 🐌 Brute Force Approach

## 🔹 Approach

The brute force approach involves checking **all possible contiguous subarrays** and computing their sums. For each starting index `i`, we compute the sum of subarrays starting at `i` and ending at every possible `j >= i`, keeping track of the maximum sum encountered.

This approach is straightforward but **highly inefficient** due to its **O(n²)** time complexity, making it unsuitable for large inputs.

---

## 🔹 Algorithm

1. Initialize `maxSum` to the smallest possible integer value (`Integer.MIN_VALUE`).
2. Iterate over each starting index `i` from `0` to `n-1`.
3. For each `i`, initialize `currentSum` to `0`.
4. Iterate over each ending index `j` from `i` to `n-1`.
5. Add `nums[j]` to `currentSum`.
6. Update `maxSum` if `currentSum` is greater.
7. Return `maxSum` after all iterations.

---

## 🔹 Code

```java
class Solution {
    public int maxSubArray(int[] nums) {
        int maxSum = Integer.MIN_VALUE;
        int n = nums.length;

        for (int i = 0; i < n; i++) {
            int currentSum = 0;
            for (int j = i; j < n; j++) {
                currentSum += nums[j];
                if (currentSum > maxSum) {
                    maxSum = currentSum;
                }
            }
        }

        return maxSum;
    }
}
```

---

## 🔹 Dry Run

**Input:** `nums = [-2, 1, -3, 4, -1, 2, 1, -5, 4]`

| Iteration (i) | Subarray (j) | Current Sum | maxSum |
|---------------|--------------|-------------|--------|
| 0             | [-2]         | -2          | -2     |
| 0             | [-2, 1]      | -1          | -1     |
| 0             | [-2, 1, -3]  | -4          | -1     |
| 0             | [-2, 1, -3, 4] | 0        | 0      |
| 0             | [-2, 1, -3, 4, -1] | -1   | 0      |
| 0             | [-2, 1, -3, 4, -1, 2] | 1 | 1      |
| 0             | [-2, 1, -3, 4, -1, 2, 1] | 2 | 2      |
| 0             | [-2, 1, -3, 4, -1, 2, 1, -5] | -3 | 2 |
| 0             | [-2, 1, -3, 4, -1, 2, 1, -5, 4] | 1 | 2 |
| 1             | [1]          | 1           | 2      |
| 1             | [1, -3]      | -2          | 2      |
| 1             | [1, -3, 4]   | 2           | 2      |
| 1             | [1, -3, 4, -1] | 1        | 2      |
| 1             | [1, -3, 4, -1, 2] | 3    | 3      |
| 1             | [1, -3, 4, -1, 2, 1] | 4 | 4      |
| 1             | [1, -3, 4, -1, 2, 1, -5] | -1 | 4 |
| 1             | [1, -3, 4, -1, 2, 1, -5, 4] | 3 | 4 |
| 2             | [-3]         | -3          | 4      |
| 2             | [-3, 4]      | 1           | 4      |
| 2             | [-3, 4, -1]  | 0           | 4      |
| 2             | [-3, 4, -1, 2] | 2        | 4      |
| 2             | [-3, 4, -1, 2, 1] | 3    | 4      |
| 2             | [-3, 4, -1, 2, 1, -5] | -2 | 4 |
| 2             | [-3, 4, -1, 2, 1, -5, 4] | 2 | 4 |
| 3             | [4]          | 4           | 4      |
| 3             | [4, -1]      | 3           | 4      |
| 3             | [4, -1, 2]   | 5           | 5      |
| 3             | [4, -1, 2, 1] | 6         | 6      |
| 3             | [4, -1, 2, 1, -5] | 1    | 6      |
| 3             | [4, -1, 2, 1, -5, 4] | 5 | 6 |
| ...           | ...          | ...         | 6      |

**Final Result:** `6`

---

## 🔹 Complexity Analysis

| Complexity      | Value       |
|-----------------|-------------|
| Time Complexity | O(n²)       |
| Space Complexity| O(1)        |

---

# ⚡ Optimal Approach (Kadane's Algorithm)

## 🔹 Approach

Kadane's Algorithm efficiently computes the maximum subarray sum in **O(n)** time by maintaining a running sum of the current subarray. If the running sum becomes negative, it resets to zero, as a negative sum would only reduce the sum of any subsequent subarray. The algorithm keeps track of the maximum sum encountered during the traversal.

This approach leverages the **greedy property** that the maximum subarray ending at a given position can be derived from the maximum subarray ending at the previous position.

---

## 🔹 Why This Works

- **Negative Sum Reset:** If the current sum becomes negative, starting a new subarray from the next element is better than carrying forward a negative prefix.
- **Local vs Global Maximum:** The algorithm maintains both a local maximum (current subarray sum) and a global maximum (best sum found so far), ensuring correctness.
- **Single Pass:** The array is traversed only once, making it optimal for large inputs.

---

## 🔹 Algorithm

1. Initialize `maxSum` and `currentSum` to `nums[0]`.
2. Iterate through the array starting from the second element.
3. For each element, update `currentSum` to be the maximum of the current element itself or the sum of `currentSum` and the current element.
4. Update `maxSum` if `currentSum` is greater.
5. Return `maxSum` after the iteration completes.

---

## 🔹 Code

```java
class Solution {
    public int maxSubArray(int[] nums) {
        int maxSum = nums[0];
        int currentSum = nums[0];

        for (int i = 1; i < nums.length; i++) {
            currentSum = Math.max(nums[i], currentSum + nums[i]);
            maxSum = Math.max(maxSum, currentSum);
        }

        return maxSum;
    }
}
```

---

## 🔹 Detailed Dry Run

**Input:** `nums = [-2, 1, -3, 4, -1, 2, 1, -5, 4]`

| Iteration | Current Element | currentSum | maxSum |
|-----------|-----------------|------------|--------|
| 0         | -2              | -2         | -2     |
| 1         | 1               | max(1, -2 + 1) = 1 | max(-2, 1) = 1 |
| 2         | -3              | max(-3, 1 + -3) = -2 | max(1, -2) = 1 |
| 3         | 4               | max(4, -2 + 4) = 4 | max(1, 4) = 4 |
| 4         | -1              | max(-1, 4 + -1) = 3 | max(4, 3) = 4 |
| 5         | 2               | max(2, 3 + 2) = 5 | max(4, 5) = 5 |
| 6         | 1               | max(1, 5 + 1) = 6 | max(5, 6) = 6 |
| 7         | -5              | max(-5, 6 + -5) = 1 | max(6, 1) = 6 |
| 8         | 4               | max(4, 1 + 4) = 5 | max(6, 5) = 6 |

**Final Result:** `6`

---

## 🔹 Complexity Analysis

| Complexity      | Value       |
|-----------------|-------------|
| Time Complexity | O(n)        |
| Space Complexity| O(1)        |

---

# 🔍 Edge Cases

| Edge Case                     | Expected Output | Explanation |
|-------------------------------|-----------------|-------------|
| `nums = []`                   | Undefined (handle as per problem constraints) | Empty array (though constraints say `n >= 1`) |
| `nums = [5]`                  | `5`             | Single element |
| `nums = [-1, -2, -3]`         | `-1`            | All negative numbers |
| `nums = [1, 2, 3, 4]`         | `10`            | All positive numbers |
| `nums = [1, -2, 3, -4, 5]`    | `5`             | Mixed positive and negative |
| `nums = [1000000]`            | `1000000`       | Large single element |
| `nums = [-2, -1]`             | `-1`            | Two negative numbers |

---

# 📚 Key Takeaways

1. **Kadane's Algorithm** is the optimal solution for the maximum subarray problem, achieving **O(n)** time and **O(1)** space.
2. **Negative Prefix Insight:** Resetting the current sum when it becomes negative is crucial for correctness.
3. **Greedy Property:** The maximum subarray ending at position `i` depends on the maximum subarray ending at `i-1`.
4. **Efficiency Matters:** The brute force approach is **O(n²)**, which is impractical for large inputs (`n = 10^5`).
5. **Interview Focus:** This problem is a classic example of **dynamic programming** and **greedy algorithms**.

---

# 🚀 Interview Tips

1. **Follow-Up Questions:**
   - Can you modify the solution to return the **indices** of the maximum subarray?
   - How would you handle the problem if the array is **circular** (i.e., the subarray can wrap around)?
   - What if the problem required the **minimum subarray sum** instead?

2. **Common Pitfalls:**
   - Forgetting to handle **all-negative arrays** (initialize `maxSum` to `nums[0]` instead of `0`).
   - Using **O(n²)** approaches for large inputs (will time out).
   - Misunderstanding the **contiguous** requirement (subarray must be consecutive elements).

3. **Alternative Approaches:**
   - **Divide and Conquer:** Solve in **O(n log n)** time by recursively dividing the array.
   - **Prefix Sum:** Compute prefix sums and use a sliding window to find the maximum subarray.

4. **Optimization Discussion:**
   - Kadane's Algorithm is already optimal for this problem, but understanding its **dynamic programming** roots is valuable.
   - Space optimization (using **O(1)** space) is critical for large inputs.

---

# ✅ Conclusion

The **Maximum Subarray** problem is a fundamental question in algorithms and interviews, testing **dynamic programming**, **greedy strategies**, and **optimization skills**. While the brute force approach is intuitive, **Kadane's Algorithm** provides an elegant and efficient **O(n)** solution by leveraging the insight that a negative prefix should be discarded.

**Key Insight:** The maximum subarray ending at any position is either the current element itself or the sum of the current element and the maximum subarray ending at the previous position.

This problem is a must-know for technical interviews at top-tier companies, and mastering it will significantly boost your problem-solving confidence.