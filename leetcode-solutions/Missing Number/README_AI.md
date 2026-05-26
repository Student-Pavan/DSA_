# 📌 Missing Number

---

# 📝 Problem Statement

Given an array `nums` containing `n` distinct numbers in the range `[0, n]`, return the **only number** in the range that is missing from the array.

### **Objective**
Find the missing number in the sequence from `0` to `n` (inclusive) that is not present in the given array.

### **Input/Output Expectations**
- **Input:** `nums = [3, 0, 1]`
  **Output:** `2`
  **Explanation:** The numbers in the range `[0, 3]` are `0, 1, 2, 3`. The missing number is `2`.

- **Input:** `nums = [0, 1]`
  **Output:** `2`
  **Explanation:** The numbers in the range `[0, 2]` are `0, 1, 2`. The missing number is `2`.

### **Constraints**
- `n == nums.length`
- `1 <= n <= 10^4`
- `0 <= nums[i] <= n`
- All numbers in `nums` are **distinct**.

---

# 💡 Intuition

The problem requires identifying the missing number in a sequence from `0` to `n`. A naive approach would involve checking every number in the range and verifying its presence in the array. However, this would be inefficient for large inputs.

A key insight is that the sum of the first `n` natural numbers (from `0` to `n`) can be computed using the formula:
**`sum = n * (n + 1) / 2`**.
By comparing this expected sum with the actual sum of the array, the missing number can be found in **O(n)** time with **O(1)** space.

Alternatively, **bitwise XOR** can be used to find the missing number without arithmetic operations, leveraging the property that `x ^ x = 0` and `x ^ 0 = x`.

---

# 🐌 Brute Force Approach

## 🔹 Approach
The brute force approach involves iterating through every number in the range `[0, n]` and checking if it exists in the array. If a number is not found, it is the missing number.

### **Steps:**
1. Iterate from `0` to `n` (inclusive).
2. For each number, check if it exists in the array.
3. If a number is not found, return it as the missing number.

## 🔹 Algorithm
1. For `i` from `0` to `n`:
   - If `i` is not in `nums`, return `i`.
2. Return `-1` (should never reach here due to constraints).

## 🔹 Code

```java
class Solution {
    public int missingNumber(int[] nums) {
        int n = nums.length;
        for (int i = 0; i <= n; i++) {
            boolean found = false;
            for (int num : nums) {
                if (num == i) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                return i;
            }
        }
        return -1;
    }
}
```

## 🔹 Dry Run

**Input:** `nums = [3, 0, 1]`
**Expected Output:** `2`

| Iteration | Current Number (`i`) | Found in Array? | Action                     |
|-----------|----------------------|-----------------|----------------------------|
| 1         | 0                    | Yes             | Continue                   |
| 2         | 1                    | Yes             | Continue                   |
| 3         | 2                    | No              | Return `2` (missing number) |

**Input:** `nums = [0, 1]`
**Expected Output:** `2`

| Iteration | Current Number (`i`) | Found in Array? | Action                     |
|-----------|----------------------|-----------------|----------------------------|
| 1         | 0                    | Yes             | Continue                   |
| 2         | 1                    | Yes             | Continue                   |
| 3         | 2                    | No              | Return `2` (missing number) |

## 🔹 Complexity Analysis

| Complexity      | Value       |
|-----------------|-------------|
| Time Complexity | O(n²)       |
| Space Complexity| O(1)        |

---

# ⚡ Optimal Approach

## 🔹 Approach
The optimal approach leverages the **mathematical sum formula** or **bitwise XOR** to find the missing number in **O(n)** time with **O(1)** space.

### **Mathematical Sum Approach:**
1. Compute the expected sum of numbers from `0` to `n` using the formula `n * (n + 1) / 2`.
2. Compute the actual sum of the array.
3. The missing number is the difference between the expected sum and the actual sum.

### **Bitwise XOR Approach:**
1. Initialize `xor = 0`.
2. XOR all numbers from `0` to `n` with `xor`.
3. XOR all elements in the array with `xor`.
4. The result is the missing number (since `x ^ x = 0` and `x ^ 0 = x`).

## 🔹 Why This Works
- **Sum Formula:** The missing number is the only value that disrupts the expected sum.
- **XOR:** XORing all numbers cancels out duplicates, leaving only the missing number.

## 🔹 Algorithm (Sum Formula)
1. Compute `expectedSum = n * (n + 1) / 2`.
2. Compute `actualSum` by summing all elements in `nums`.
3. Return `expectedSum - actualSum`.

## 🔹 Code (Sum Formula)

```java
class Solution {
    public int missingNumber(int[] nums) {
        int n = nums.length;
        int expectedSum = n * (n + 1) / 2;
        int actualSum = 0;
        for (int num : nums) {
            actualSum += num;
        }
        return expectedSum - actualSum;
    }
}
```

## 🔹 Code (XOR Approach)

```java
class Solution {
    public int missingNumber(int[] nums) {
        int xor = 0;
        int n = nums.length;
        for (int i = 0; i <= n; i++) {
            xor ^= i;
        }
        for (int num : nums) {
            xor ^= num;
        }
        return xor;
    }
}
```

## 🔹 Detailed Dry Run (Sum Formula)

**Input:** `nums = [3, 0, 1]`
**Expected Output:** `2`

| Step | Variable       | Value | Explanation                     |
|------|----------------|-------|---------------------------------|
| 1    | `n`            | 3     | Length of array                 |
| 2    | `expectedSum`  | 6     | `3 * 4 / 2 = 6`                 |
| 3    | `actualSum`    | 4     | `3 + 0 + 1 = 4`                 |
| 4    | `missing`      | 2     | `6 - 4 = 2`                     |

**Input:** `nums = [0, 1]`
**Expected Output:** `2`

| Step | Variable       | Value | Explanation                     |
|------|----------------|-------|---------------------------------|
| 1    | `n`            | 2     | Length of array                 |
| 2    | `expectedSum`  | 3     | `2 * 3 / 2 = 3`                 |
| 3    | `actualSum`    | 1     | `0 + 1 = 1`                     |
| 4    | `missing`      | 2     | `3 - 1 = 2`                     |

## 🔹 Complexity Analysis

| Complexity      | Value       |
|-----------------|-------------|
| Time Complexity | O(n)        |
| Space Complexity| O(1)        |

---

# 🔍 Edge Cases

| Edge Case               | Expected Output | Explanation                          |
|-------------------------|-----------------|--------------------------------------|
| `nums = [0]`            | `1`             | Only `0` is present, missing `1`.    |
| `nums = [1]`            | `0`             | Only `1` is present, missing `0`.    |
| `nums = [0, 1, 2]`      | `3`             | All numbers except `3` are present.  |
| `nums = [1, 2, 3]`      | `0`             | All numbers except `0` are present.  |
| `nums = []`             | `0`             | Empty array, missing `0`.            |

---

# 📚 Key Takeaways

1. **Mathematical Insight:** The sum formula provides an efficient way to find missing numbers in a sequence.
2. **Bitwise XOR:** A clever alternative that avoids arithmetic operations and works in constant space.
3. **Optimization:** The optimal approach reduces time complexity from **O(n²)** to **O(n)**.
4. **Edge Cases:** Always verify behavior for `0`, `n`, and empty inputs.

---

# 🚀 Interview Tips

1. **Follow-Up Questions:**
   - Can you solve this problem in **O(1) space**?
   - What if the array contains duplicates?
   - How would you handle negative numbers?

2. **Common Pitfalls:**
   - Forgetting that the range includes `0`.
   - Off-by-one errors in loop bounds.
   - Integer overflow in sum calculations (unlikely for `n ≤ 10^4`).

3. **Alternative Approaches:**
   - **Sorting:** Sort the array and check for gaps (O(n log n) time).
   - **Hash Set:** Store all numbers and check for missing values (O(n) time, O(n) space).

4. **Optimization Discussion:**
   - The sum formula is **faster** for small `n`.
   - The XOR approach is **more robust** against integer overflow.

---

# ✅ Conclusion

The **optimal solution** using the **sum formula** or **XOR** is preferred due to its **O(n) time and O(1) space** complexity. The key insight is recognizing that the missing number disrupts the expected sum or XOR result, allowing efficient computation without extra space.

This problem is a great example of how **mathematical patterns** and **bitwise operations** can optimize brute-force solutions. Always consider edge cases and verify correctness for all possible inputs.