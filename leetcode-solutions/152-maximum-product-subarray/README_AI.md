# Maximum Product Subarray

---

# 📝 Problem Statement

Given an integer array `nums`, find a contiguous subarray within it that has the largest product.

**Constraints:**
- `1 <= nums.length <= 2 * 10^4`
- `-10 <= nums[i] <= 10`
- The product of any prefix or suffix of `nums` is guaranteed to fit in a 32-bit integer.

---

# 💡 Intuition

The challenge is to find the maximum product of any contiguous subarray. The key insight is that the maximum product can be obtained either by multiplying positive numbers or by multiplying two negative numbers (which results in a positive product). Therefore, we need to keep track of both the maximum and minimum products at each step, as a minimum product can become maximum if multiplied by a negative number.

---

# 🐌 Brute Force Approach

## 🔹 Approach

The brute force approach involves checking all possible subarrays and calculating their products to find the maximum product.

1. Initialize `maxProduct` to the smallest possible integer value.
2. Iterate through each element in the array.
3. For each element, initialize a temporary product to 1.
4. Iterate through the remaining elements to the right of the current element.
5. Multiply the temporary product by the current element and update `maxProduct` if the temporary product is greater.
6. Return `maxProduct`.

---

## 🔹 Algorithm

1. Initialize `maxProduct` to `Integer.MIN_VALUE`.
2. For each element `nums[i]` in the array:
   - Initialize `tempProduct` to 1.
   - For each element `nums[j]` from `i` to the end of the array:
     - Multiply `tempProduct` by `nums[j]`.
     - Update `maxProduct` to the maximum of `maxProduct` and `tempProduct`.
3. Return `maxProduct`.

---

## 🔹 Code

```java
class Solution {
    public int maxProduct(int[] nums) {
        int maxProduct = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            int tempProduct = 1;
            for (int j = i; j < nums.length; j++) {
                tempProduct *= nums[j];
                maxProduct = Math.max(maxProduct, tempProduct);
            }
        }
        return maxProduct;
    }
}
```

---

## 🔹 Dry Run

Let's dry run the algorithm with the input `[2, 3, -2, 4]`.

| i | j | tempProduct | maxProduct |
|---|---|-------------|------------|
| 0 | 0 | 2           | 2          |
| 0 | 1 | 6           | 6          |
| 0 | 2 | -12         | 6          |
| 0 | 3 | -48         | 6          |
| 1 | 1 | 3           | 6          |
| 1 | 2 | -6          | 6          |
| 1 | 3 | -24         | 6          |
| 2 | 2 | -2          | 6          |
| 2 | 3 | -8          | 6          |
| 3 | 3 | 4           | 6          |

The maximum product is `6`.

---

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(n^2) |
| Space Complexity | O(1) |

---

# ⚡ Optimal Approach

## 🔹 Approach

The optimal approach involves iterating through the array while keeping track of the maximum and minimum products up to the current element. This allows us to handle negative numbers efficiently.

1. Initialize `maxProduct`, `minProduct`, and `result` to the first element of the array.
2. Iterate through the array starting from the second element.
3. For each element, calculate the new `maxProduct` and `minProduct` considering the current element and the previous `maxProduct` and `minProduct`.
4. Update `result` with the maximum value encountered so far.
5. Return `result`.

---

## 🔹 Why This Works

By keeping track of both the maximum and minimum products, we ensure that we can handle negative numbers correctly. Multiplying two negative numbers results in a positive number, which could be the maximum product. Therefore, we need to consider both possibilities at each step.

---

## 🔹 Algorithm

1. Initialize `maxProduct`, `minProduct`, and `result` to `nums[0]`.
2. For each element `nums[i]` from index 1 to the end of the array:
   - If `nums[i]` is negative, swap `maxProduct` and `minProduct`.
   - Update `maxProduct` to the maximum of `nums[i]` and `maxProduct * nums[i]`.
   - Update `minProduct` to the minimum of `nums[i]` and `minProduct * nums[i]`.
   - Update `result` to the maximum of `result` and `maxProduct`.
3. Return `result`.

---

## 🔹 Code

```java
class Solution {
    public int maxProduct(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int maxProduct = nums[0];
        int minProduct = nums[0];
        int result = nums[0];

        for (int i = 1; i < nums.length; i++) {
            int temp = maxProduct;
            maxProduct = Math.max(nums[i], Math.max(maxProduct * nums[i], minProduct * nums[i]));
            minProduct = Math.min(nums[i], Math.min(temp * nums[i], minProduct * nums[i]));
            result = Math.max(result, maxProduct);
        }

        return result;
    }
}
```

---

## 🔹 Detailed Dry Run

Let's dry run the algorithm with the input `[2, 3, -2, 4]`.

| i | nums[i] | maxProduct | minProduct | result |
|---|---------|------------|------------|--------|
| 0 | 2       | 2          | 2          | 2      |
| 1 | 3       | 6          | 3          | 6      |
| 2 | -2      | -2         | -12        | 6      |
| 3 | 4       | 4          | -48        | 6      |

The maximum product is `6`.

---

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(n) |
| Space Complexity | O(1) |

---

# 🔍 Edge Cases

- **Single Element:** `[2]` → `2`
- **All Negative Numbers:** `[-2, -3, -4]` → `12`
- **Zero in Array:** `[2, 3, 0, 4]` → `6`
- **Large Input:** `[1, 2, 3, 4, 5, 6, 7, 8, 9, 10]` → `3628800`
- **Negative and Positive Numbers:** `[-4, -3, -2]` → `12`

---

# 📚 Key Takeaways

- The optimal approach efficiently handles negative numbers by tracking both the maximum and minimum products.
- The brute force approach is simple but inefficient for large inputs.
- The optimal approach runs in linear time, making it suitable for large input sizes.

---

# 🚀 Interview Tips

- **Follow-up Questions:**
  - What if the array contains zeros?
  - How would you handle very large arrays?
- **Common Pitfalls:**
  - Forgetting to track both the maximum and minimum products.
  - Not handling negative numbers correctly.
- **Alternative Approaches:**
  - Using dynamic programming to store intermediate results.
- **Optimization Discussions:**
  - The optimal approach ensures that we only traverse the array once, making it efficient.

---

# ✅ Conclusion

The optimal approach is preferred because it efficiently handles all cases, including negative numbers, and runs in linear time. The key insight is to track both the maximum and minimum products at each step to ensure correctness. This approach is both time and space efficient, making it suitable for large input sizes.