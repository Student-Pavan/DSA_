# 📌 Number of Ways to Split Array

---

# 📝 Problem Statement

Given an integer array `nums`, return the number of ways to split the array into two non-empty parts such that the sum of the first part is **greater than or equal to** the sum of the second part.

A split is defined by an index `i` where:
- The first part contains elements from `nums[0]` to `nums[i]` (inclusive)
- The second part contains elements from `nums[i+1]` to `nums[n-1]` (inclusive)
- Both parts must be non-empty

**Constraints:**
- `2 <= nums.length <= 10^5`
- `-10^5 <= nums[i] <= 10^5`

**Example:**
```
Input: nums = [10,4,-8,7]
Output: 2
Explanation:
There are two valid splits:
- Split at index 0: [10] and [4,-8,7]. Sum of first part (10) >= sum of second part (3)
- Split at index 1: [10,4] and [-8,7]. Sum of first part (14) >= sum of second part (-1)
```

---

# 💡 Intuition

The key insight is recognizing that we need to count all valid split points where the prefix sum up to index `i` is at least half of the total sum of the array.

Instead of recalculating the suffix sum for every possible split (which would be O(n²)), we can:
1. Compute the total sum of the array
2. Track the running prefix sum as we iterate
3. For each index, check if the prefix sum is ≥ (total sum - prefix sum)
4. Count valid splits

This reduces the problem to a single pass through the array after computing the total sum, achieving O(n) time complexity.

---

# 🐌 Brute Force Approach

## 🔹 Approach

The naive approach involves:
1. Iterating through each possible split point
2. For each split, calculating the sum of the left part and the sum of the right part
3. Counting splits where left sum ≥ right sum

This requires nested loops: the outer loop for split points, and inner loops to compute left and right sums.

## 🔹 Algorithm

1. Initialize a counter for valid splits
2. For each possible split index `i` from `0` to `n-2`:
   - Compute sum of elements from `0` to `i` (left sum)
   - Compute sum of elements from `i+1` to `n-1` (right sum)
   - If left sum ≥ right sum, increment counter
3. Return the counter

## 🔹 Code

```java
class Solution {
    public int waysToSplitArray(int[] nums) {
        int count = 0;
        int n = nums.length;

        for (int i = 0; i < n - 1; i++) {
            long leftSum = 0;
            long rightSum = 0;

            // Calculate left sum
            for (int j = 0; j <= i; j++) {
                leftSum += nums[j];
            }

            // Calculate right sum
            for (int j = i + 1; j < n; j++) {
                rightSum += nums[j];
            }

            if (leftSum >= rightSum) {
                count++;
            }
        }

        return count;
    }
}
```

## 🔹 Dry Run

**Input:** `nums = [10, 4, -8, 7]`

| Split Index (i) | Left Part | Left Sum | Right Part | Right Sum | Valid Split? | Count |
|-----------------|-----------|----------|------------|-----------|--------------|-------|
| 0               | [10]      | 10       | [4,-8,7]   | 3         | Yes (10 ≥ 3) | 1     |
| 1               | [10,4]    | 14       | [-8,7]     | -1        | Yes (14 ≥ -1)| 2     |
| 2               | [10,4,-8] | 6        | [7]        | 7         | No (6 < 7)   | 2     |

**Final Count:** 2

## 🔹 Complexity Analysis

| Complexity      | Value     |
|-----------------|-----------|
| Time Complexity | O(n²)     |
| Space Complexity| O(1)      |

---

# ⚡ Optimal Approach

## 🔹 Approach

The optimized approach leverages prefix sums and a single pass:
1. Compute the total sum of the array
2. Initialize a running prefix sum
3. For each index, check if `prefixSum >= (totalSum - prefixSum)`
4. Count valid splits

This avoids recalculating sums repeatedly by using the prefix sum pattern.

## 🔹 Why This Works

- The total sum is constant
- The right sum at any split is `totalSum - prefixSum`
- We only need to compare `prefixSum` with `totalSum - prefixSum`
- This reduces the problem to a single O(n) pass

## 🔹 Algorithm

1. Compute total sum of the array
2. Initialize `prefixSum = 0` and `count = 0`
3. Iterate through the array from index `0` to `n-2`:
   - Add current element to `prefixSum`
   - If `prefixSum >= (totalSum - prefixSum)`, increment `count`
4. Return `count`

## 🔹 Code

```java
class Solution {
    public int waysToSplitArray(int[] nums) {
        int n = nums.length;
        long totalSum = 0;

        // Compute total sum
        for (int num : nums) {
            totalSum += num;
        }

        long prefixSum = 0;
        int count = 0;

        // Iterate through possible split points
        for (int i = 0; i < n - 1; i++) {
            prefixSum += nums[i];
            if (prefixSum >= totalSum - prefixSum) {
                count++;
            }
        }

        return count;
    }
}
```

## 🔹 Detailed Dry Run

**Input:** `nums = [10, 4, -8, 7]`

| Index (i) | Current Element | Prefix Sum | Total Sum | Right Sum (total - prefix) | Valid Split? | Count |
|-----------|-----------------|------------|-----------|----------------------------|--------------|-------|
| 0         | 10              | 10         | 13        | 3                          | Yes (10 ≥ 3) | 1     |
| 1         | 4               | 14         | 13        | -1                         | Yes (14 ≥ -1)| 2     |
| 2         | -8              | 6          | 13        | 7                          | No (6 < 7)   | 2     |

**Final Count:** 2

## 🔹 Complexity Analysis

| Complexity      | Value     |
|-----------------|-----------|
| Time Complexity | O(n)      |
| Space Complexity| O(1)      |

---

# 🔍 Edge Cases

- **Single valid split:** `[1, 1]` → 1 way
- **All positive numbers:** `[5, 2, 3]` → 2 ways
- **Negative numbers:** `[10, -5, 3]` → careful with sum comparisons
- **Large array:** Handle integer overflow with `long`
- **All elements equal:** `[2, 2, 2]` → 2 ways
- **First element dominates:** `[100, 1, 1]` → 2 ways
- **Last element dominates:** `[1, 1, 100]` → 0 ways

---

# 📚 Key Takeaways

- **Prefix Sum Pattern:** Essential for optimizing sum-based problems
- **Single Pass Efficiency:** O(n) vs O(n²) makes a huge difference for large inputs
- **Integer Overflow:** Use `long` to handle large sums
- **Problem Decomposition:** Break down into prefix and suffix sums
- **Early Termination:** Not applicable here, but useful in similar problems

---

# 🚀 Interview Tips

- **Follow-up:** What if we need the first part to be strictly greater than the second?
- **Optimization Insight:** Always look for ways to avoid nested loops with prefix sums
- **Common Pitfall:** Forgetting to use `long` for sums, causing overflow
- **Alternative Approach:** Could use suffix sums, but prefix is more natural here
- **Time-Space Tradeoff:** This problem has no tradeoff - optimal solution is both time and space efficient

---

# ✅ Conclusion

The optimal solution leverages the prefix sum pattern to achieve O(n) time complexity with O(1) space, making it highly efficient for large inputs. The key insight is recognizing that the right sum can be derived from the total sum and prefix sum, eliminating the need for nested loops.

This problem demonstrates the power of prefix sums in array manipulation and serves as an excellent example of optimizing from O(n²) to O(n) with careful algorithm design.