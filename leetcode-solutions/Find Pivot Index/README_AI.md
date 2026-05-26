# 📌 Find Pivot Index

---

# 📝 Problem Statement

Given an array of integers `nums`, calculate the **pivot index** of this array.

The **pivot index** is the index where the sum of all the numbers **strictly to the left** of the index is equal to the sum of all the numbers **strictly to the right** of the index.

If no such index exists, return `-1`. If there are multiple pivot indices, return the **leftmost** pivot index.

### Constraints:
- `1 <= nums.length <= 10⁴`
- `-1000 <= nums[i] <= 1000`

---

# 💡 Intuition

The key insight is recognizing that the pivot index divides the array into two parts with equal sums. Instead of recalculating left and right sums for every index (which is inefficient), we can leverage the **total sum** of the array.

If we know the total sum `S`, then at any index `i`, the right sum is `S - leftSum - nums[i]`. For `i` to be a pivot, `leftSum` must equal `S - leftSum - nums[i]`, which simplifies to `2 * leftSum + nums[i] == S`.

This allows us to compute the pivot in a single pass, making the solution optimal.

---

# 🐌 Brute Force Approach

## 🔹 Approach

The brute force approach involves checking every index in the array to see if it is a pivot index. For each index, we calculate the sum of elements to the left and the sum of elements to the right, then compare them.

This approach is straightforward but inefficient because it recalculates sums repeatedly.

---

## 🔹 Algorithm

1. Iterate through each index `i` in the array.
2. For each `i`, compute the sum of elements to the left of `i`.
3. Compute the sum of elements to the right of `i`.
4. If the left sum equals the right sum, return `i`.
5. If no such index is found after checking all indices, return `-1`.

---

## 🔹 Code

```java
class Solution {
    public int pivotIndex(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            int leftSum = 0;
            int rightSum = 0;

            // Calculate left sum
            for (int j = 0; j < i; j++) {
                leftSum += nums[j];
            }

            // Calculate right sum
            for (int j = i + 1; j < nums.length; j++) {
                rightSum += nums[j];
            }

            if (leftSum == rightSum) {
                return i;
            }
        }
        return -1;
    }
}
```

---

## 🔹 Dry Run

**Input:** `nums = [1, 7, 3, 6, 5, 6]`

| Iteration | Index (i) | Left Sum Calculation | Right Sum Calculation | Left Sum | Right Sum | Pivot? |
|-----------|-----------|----------------------|-----------------------|----------|-----------|--------|
| 1         | 0         | `[]` → 0             | `[7,3,6,5,6]` → 27    | 0        | 27        | ❌     |
| 2         | 1         | `[1]` → 1            | `[3,6,5,6]` → 20      | 1        | 20        | ❌     |
| 3         | 2         | `[1,7]` → 8          | `[6,5,6]` → 17        | 8        | 17        | ❌     |
| 4         | 3         | `[1,7,3]` → 11       | `[5,6]` → 11          | 11       | 11        | ✅     |

**Result:** `3` (pivot index found)

---

## 🔹 Complexity Analysis

| Complexity       | Value       |
|------------------|-------------|
| Time Complexity  | O(n²)       |
| Space Complexity | O(1)        |

---

# ⚡ Optimal Approach

## 🔹 Approach

The optimal approach avoids recalculating sums by maintaining a running `leftSum`. We first compute the total sum `S` of the array. Then, for each index `i`, we check if `leftSum == S - leftSum - nums[i]`. If true, `i` is the pivot index. Otherwise, we add `nums[i]` to `leftSum` and continue.

This reduces the time complexity to O(n) with a single pass through the array.

---

## 🔹 Why This Works

- The total sum `S` is constant.
- At any index `i`, the right sum is `S - leftSum - nums[i]`.
- For `i` to be a pivot, `leftSum == rightSum` → `leftSum == S - leftSum - nums[i]` → `2 * leftSum + nums[i] == S`.
- This condition can be checked in constant time for each index.

---

## 🔹 Algorithm

1. Compute the total sum `S` of the array.
2. Initialize `leftSum = 0`.
3. Iterate through each index `i`:
   - If `2 * leftSum + nums[i] == S`, return `i`.
   - Otherwise, add `nums[i]` to `leftSum`.
4. If no pivot is found, return `-1`.

---

## 🔹 Code

```java
class Solution {
    public int pivotIndex(int[] nums) {
        int totalSum = 0;
        for (int num : nums) {
            totalSum += num;
        }

        int leftSum = 0;
        for (int i = 0; i < nums.length; i++) {
            if (2 * leftSum + nums[i] == totalSum) {
                return i;
            }
            leftSum += nums[i];
        }
        return -1;
    }
}
```

---

## 🔹 Detailed Dry Run

**Input:** `nums = [1, 7, 3, 6, 5, 6]`

| Iteration | Index (i) | `nums[i]` | `leftSum` (before) | `2*leftSum + nums[i]` | `totalSum` | Pivot? | `leftSum` (after) |
|-----------|-----------|-----------|--------------------|-----------------------|------------|--------|-------------------|
| 1         | 0         | 1         | 0                  | 1                     | 28         | ❌     | 1                 |
| 2         | 1         | 7         | 1                  | 9                     | 28         | ❌     | 8                 |
| 3         | 2         | 3         | 8                  | 19                    | 28         | ❌     | 11                |
| 4         | 3         | 6         | 11                 | 28                    | 28         | ✅     | -                 |

**Result:** `3` (pivot index found)

---

## 🔹 Complexity Analysis

| Complexity       | Value       |
|------------------|-------------|
| Time Complexity  | O(n)        |
| Space Complexity | O(1)        |

---

# 🔍 Edge Cases

| Edge Case                     | Expected Output | Explanation                                                                 |
|-------------------------------|-----------------|-----------------------------------------------------------------------------|
| `nums = [1, 2, 3]`            | `-1`            | No pivot index exists.                                                     |
| `nums = [2, 1, -1]`           | `0`             | Left sum is `0` (no elements), right sum is `1 + (-1) = 0`.                |
| `nums = [1]`                  | `0`             | Single element is trivially a pivot (left and right sums are `0`).         |
| `nums = [1, 1, 1, 1, 1]`      | `2`             | Middle index is the pivot.                                                 |
| `nums = [-1, -1, -1, -1, -1]` | `2`             | Negative values do not affect the logic.                                   |
| `nums = [1, -1, 1]`           | `0`             | Left sum is `0`, right sum is `-1 + 1 = 0`.                                 |

---

# 📚 Key Takeaways

- **Prefix Sum Optimization:** The optimal solution leverages the total sum to avoid recalculating left/right sums repeatedly.
- **Single Pass Efficiency:** The optimal approach computes the pivot in O(n) time with O(1) space.
- **Mathematical Insight:** The condition `2 * leftSum + nums[i] == totalSum` is derived from the pivot definition.
- **Edge Case Handling:** Always consider single-element arrays, negative values, and no-pivot scenarios.

---

# 🚀 Interview Tips

- **Follow-Up Questions:**
  - How would you handle very large arrays (e.g., `nums.length = 10⁶`)?
  - Can you modify the solution to return all pivot indices (not just the leftmost)?
  - How would you solve this problem if the array is circular?

- **Common Pitfalls:**
  - Forgetting to handle the case where the pivot is at index `0` or the last index.
  - Recalculating sums unnecessarily (brute force approach).
  - Off-by-one errors in left/right sum calculations.

- **Alternative Approaches:**
  - **Two-Pass Prefix Sum:** Compute prefix sums in one pass, then check pivot condition in a second pass.
  - **Early Termination:** The optimal solution already terminates early when a pivot is found.

---

# ✅ Conclusion

The optimal solution efficiently finds the pivot index by leveraging the total sum of the array and maintaining a running left sum. This reduces the time complexity from O(n²) (brute force) to O(n) (optimal), making it suitable for large input sizes.

**Key Insight:** The pivot index condition can be checked in constant time for each index using the total sum, eliminating the need for nested loops. This is a classic example of **prefix sum optimization** in array problems.