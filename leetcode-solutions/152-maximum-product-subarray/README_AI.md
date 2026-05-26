# 📌 152. Maximum Product Subarray

---

# 📝 Problem Statement

Given an integer array `nums`, find a contiguous subarray (containing at least one number) that has the largest product, and return *the product*.

### **Objective**
Return the maximum product of any contiguous subarray within `nums`.

### **Input/Output Expectations**
- **Input:** `int[] nums` (length between 1 and 2 × 10⁴)
- **Output:** `int` (maximum product of any contiguous subarray)

### **Constraints**
- `1 <= nums.length <= 2 * 10⁴`
- `-10 <= nums[i] <= 10`
- The product of any subarray will fit in a 32-bit integer.

### **Key Observations**
- The array can contain **negative numbers**, which can turn a minimum product into a maximum product when multiplied by another negative.
- The array can contain **zeros**, which reset the product.
- The optimal solution must track **both maximum and minimum products** at each step.

---

# 💡 Intuition

The problem requires finding the maximum product of any contiguous subarray. Unlike the maximum subarray sum problem (Kadane's Algorithm), the product can be maximized by either:
- Multiplying the current number with the previous maximum product (if the number is positive).
- Multiplying the current number with the previous minimum product (if the number is negative, since two negatives make a positive).

This insight leads us to maintain **two variables** at each step:
- `maxProd`: Tracks the maximum product ending at the current index.
- `minProd`: Tracks the minimum product ending at the current index (to handle negative numbers).

---

# 🐌 Brute Force Approach

## 🔹 Approach
The brute force approach involves checking **all possible subarrays** and computing their products. For each starting index `i`, we compute the product of all subarrays starting at `i` and ending at `j` (where `j >= i`), keeping track of the maximum product encountered.

### **Why Brute Force?**
- Simple to implement.
- Guarantees correctness by exhaustively checking all possibilities.
- Serves as a baseline for optimization.

### **Drawbacks**
- **Time Complexity:** O(n²) — inefficient for large arrays.
- **Space Complexity:** O(1) — only a few variables are used.

---

## 🔹 Algorithm
1. Initialize `maxProduct = Integer.MIN_VALUE`.
2. Iterate over each starting index `i` from `0` to `n-1`.
3. For each `i`, initialize `currentProduct = 1`.
4. Iterate over each ending index `j` from `i` to `n-1`.
5. Multiply `currentProduct` by `nums[j]`.
6. Update `maxProduct` if `currentProduct > maxProduct`.
7. Return `maxProduct`.

---

## 🔹 Code

```java
class Solution {
    public int maxProduct(int[] nums) {
        int maxProduct = Integer.MIN_VALUE;
        int n = nums.length;

        for (int i = 0; i < n; i++) {
            int currentProduct = 1;
            for (int j = i; j < n; j++) {
                currentProduct *= nums[j];
                if (currentProduct > maxProduct) {
                    maxProduct = currentProduct;
                }
            }
        }
        return maxProduct;
    }
}
```

---

## 🔹 Dry Run

**Input:** `nums = [2, 3, -2, 4]`

| Step | i | j | currentProduct | maxProduct | Action                     |
|------|---|---|----------------|------------|----------------------------|
| 1    | 0 | 0 | 2              | 2          | Start subarray at i=0      |
| 2    | 0 | 1 | 6              | 6          | Multiply by 3              |
| 3    | 0 | 2 | -12            | 6          | Multiply by -2             |
| 4    | 0 | 3 | -48            | 6          | Multiply by 4              |
| 5    | 1 | 1 | 3              | 6          | Start subarray at i=1      |
| 6    | 1 | 2 | -6             | 6          | Multiply by -2             |
| 7    | 1 | 3 | -24            | 6          | Multiply by 4              |
| 8    | 2 | 2 | -2             | 6          | Start subarray at i=2      |
| 9    | 2 | 3 | -8             | 6          | Multiply by 4              |
| 10   | 3 | 3 | 4              | 6          | Start subarray at i=3      |

**Result:** `6` (from subarray `[2, 3]`)

---

## 🔹 Complexity Analysis

| Complexity       | Value     |
|------------------|-----------|
| Time Complexity  | O(n²)     |
| Space Complexity | O(1)      |

---

# ⚡ Optimal Approach

## 🔹 Approach
The optimal approach uses **dynamic programming** to track both the **maximum and minimum products** at each step. This allows us to handle negative numbers efficiently.

### **Key Insight**
- If the current number is **positive**, multiplying it with the previous maximum product yields the new maximum.
- If the current number is **negative**, multiplying it with the previous minimum product (which could be negative) may yield a new maximum.
- If the current number is **zero**, both `maxProd` and `minProd` reset to `1` (or the number itself).

---

## 🔹 Why This Works
- **Handles Negatives:** By tracking both `maxProd` and `minProd`, we ensure that a negative number can flip the minimum into a maximum.
- **Efficiency:** Single pass through the array (O(n) time).
- **Space Optimization:** Uses O(1) space by reusing variables.

---

## 🔹 Algorithm
1. Initialize `maxProd`, `minProd`, and `result` to `nums[0]`.
2. Iterate from `i = 1` to `n-1`:
   - If `nums[i] < 0`, swap `maxProd` and `minProd` (since multiplying by a negative flips the order).
   - Update `maxProd` as the maximum of `nums[i]` or `maxProd * nums[i]`.
   - Update `minProd` as the minimum of `nums[i]` or `minProd * nums[i]`.
   - Update `result` with the maximum of `result` and `maxProd`.
3. Return `result`.

---

## 🔹 Code

```java
class Solution {
    public int maxProduct(int[] nums) {
        if (nums.length == 0) return 0;

        int maxProd = nums[0];
        int minProd = nums[0];
        int result = nums[0];

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < 0) {
                // Swap max and min if current number is negative
                int temp = maxProd;
                maxProd = minProd;
                minProd = temp;
            }

            maxProd = Math.max(nums[i], maxProd * nums[i]);
            minProd = Math.min(nums[i], minProd * nums[i]);

            result = Math.max(result, maxProd);
        }

        return result;
    }
}
```

---

## 🔹 Detailed Dry Run

**Input:** `nums = [2, 3, -2, 4]`

| i | nums[i] | maxProd (before) | minProd (before) | Swap? | maxProd (after) | minProd (after) | result |
|---|---------|------------------|------------------|-------|-----------------|-----------------|--------|
| 0 | 2       | 2                | 2                | No    | 2               | 2               | 2      |
| 1 | 3       | 2                | 2                | No    | 6               | 3               | 6      |
| 2 | -2      | 6                | 3                | Yes   | -2              | -12             | 6      |
| 3 | 4       | -2               | -12              | No    | 4               | -48             | 6      |

**Result:** `6` (from subarray `[2, 3]`)

---

## 🔹 Complexity Analysis

| Complexity       | Value     |
|------------------|-----------|
| Time Complexity  | O(n)      |
| Space Complexity | O(1)      |

---

# 🔍 Edge Cases

| Edge Case                     | Expected Output | Explanation                                                                 |
|-------------------------------|-----------------|-----------------------------------------------------------------------------|
| `[0]`                         | `0`             | Single zero.                                                                |
| `[-2, 0, -1]`                 | `0`             | Zero resets the product.                                                    |
| `[-2, 3, -4]`                 | `24`            | Product of entire array (`-2 * 3 * -4 = 24`).                               |
| `[2, -5, -2, -4, 3]`          | `24`            | Subarray `[-5, -2, -4, 3]` gives `24`.                                      |
| `[1, 2, 3, 4, 5]`             | `120`           | Entire array has the maximum product.                                       |
| `[-1, -2, -3, -4]`            | `24`            | Subarray `[-4, -3, -2, -1]` gives `24`.                                     |

---

# 📚 Key Takeaways

1. **Dynamic Programming for Products:**
   - Unlike sums, products require tracking **both max and min** due to negative numbers.
2. **Handling Negatives:**
   - Swapping `maxProd` and `minProd` when encountering a negative number is crucial.
3. **Efficiency Matters:**
   - The optimal solution reduces time complexity from **O(n²) → O(n)**.
4. **Edge Cases:**
   - Always test with **zeros, negatives, and single-element arrays**.

---

# 🚀 Interview Tips

### **Follow-Up Questions**
- How would you modify the solution if the array contains **floating-point numbers**?
- Can you solve this problem in **O(n) time and O(n) space**? (Hint: Use prefix products.)
- What if the array is **streamed** (numbers arrive one by one)?

### **Common Pitfalls**
- Forgetting to **swap `maxProd` and `minProd`** when encountering a negative number.
- Not handling **zeros** correctly (resetting `maxProd` and `minProd`).
- Using **integer overflow** (though LeetCode constraints prevent this).

### **Alternative Approaches**
- **Prefix Product + Suffix Product:** Compute prefix and suffix products, then find the maximum product in between.
- **Divide and Conquer:** Recursively split the array and combine results (less efficient for this problem).

---

# ✅ Conclusion

The **optimal solution** efficiently tracks both the **maximum and minimum products** at each step, allowing it to handle negative numbers and zeros gracefully. This approach achieves **O(n) time and O(1) space**, making it suitable for large input sizes.

### **Key Insight**
> *"The maximum product subarray can end at the current index, or it can be formed by multiplying the current number with the previous maximum or minimum product."*

This problem is a **classic example** of how **dynamic programming** can optimize brute force solutions by leveraging **state tracking**. Mastering this pattern will help in solving similar problems like **maximum subarray sum (Kadane's Algorithm)** and **minimum product subarray**.