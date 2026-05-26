# 📌 Sort Colors

**LeetCode 75 | Medium | Dutch National Flag Problem**

---

# 📝 Problem Statement

Given an array `nums` with `n` objects colored red, white, or blue, sort them **in-place** so that objects of the same color are adjacent, with the colors in the order red, white, and blue.

We will use the integers `0`, `1`, and `2` to represent the color red, white, and blue, respectively.

You must solve this problem **without using the library's sort function**.

### **Constraints**
- `n == nums.length`
- `1 <= n <= 300`
- `nums[i]` is either `0`, `1`, or `2`.

### **Input/Output**
- **Input**: `int[] nums`
- **Output**: `void` (sort the array in-place)

---

# 💡 Intuition

The problem is a classic variation of the **Dutch National Flag problem**, where we need to partition an array into three sections in a single pass.

The key insight is to maintain **three pointers** that divide the array into three regions:
- **0s (red)**: from `0` to `low - 1`
- **1s (white)**: from `low` to `mid - 1`
- **2s (blue)**: from `high + 1` to `n - 1`

We traverse the array with the `mid` pointer and adjust the regions based on the current value.

---

# 🐌 Brute Force Approach

## 🔹 Approach
A straightforward approach is to **count the occurrences** of `0`, `1`, and `2`, then overwrite the array in the correct order. This requires two passes:
1. Count the frequency of each color.
2. Overwrite the array based on the counts.

While simple, this approach uses **extra space** for counting and does not leverage the in-place requirement efficiently.

---

## 🔹 Algorithm
1. Initialize counters for `0`, `1`, and `2`.
2. Traverse the array and count occurrences of each color.
3. Overwrite the array:
   - First, place all `0`s.
   - Then, place all `1`s.
   - Finally, place all `2`s.

---

## 🔹 Code

```java
class Solution {
    public void sortColors(int[] nums) {
        int count0 = 0, count1 = 0, count2 = 0;

        // Count occurrences of each color
        for (int num : nums) {
            if (num == 0) count0++;
            else if (num == 1) count1++;
            else count2++;
        }

        // Overwrite the array
        int index = 0;
        while (count0-- > 0) nums[index++] = 0;
        while (count1-- > 0) nums[index++] = 1;
        while (count2-- > 0) nums[index++] = 2;
    }
}
```

---

## 🔹 Dry Run

**Input**: `nums = [2, 0, 2, 1, 1, 0]`

### **Step 1: Counting Phase**
| Iteration | Current Value | count0 | count1 | count2 |
|-----------|---------------|--------|--------|--------|
| 1         | 2             | 0      | 0      | 1      |
| 2         | 0             | 1      | 0      | 1      |
| 3         | 2             | 1      | 0      | 2      |
| 4         | 1             | 1      | 1      | 2      |
| 5         | 1             | 1      | 2      | 2      |
| 6         | 0             | 2      | 2      | 2      |

### **Step 2: Overwriting Phase**
| Step | count0 | count1 | count2 | nums (after write) |
|------|--------|--------|--------|---------------------|
| 1    | 2      | 2      | 2      | `[0, 0, 2, 1, 1, 0]` |
| 2    | 1      | 2      | 2      | `[0, 0, 2, 1, 1, 0]` |
| 3    | 0      | 2      | 2      | `[0, 0, 2, 1, 1, 0]` |
| 4    | 0      | 1      | 2      | `[0, 0, 1, 1, 1, 0]` |
| 5    | 0      | 0      | 2      | `[0, 0, 1, 1, 2, 0]` |
| 6    | 0      | 0      | 1      | `[0, 0, 1, 1, 2, 2]` |
| 7    | 0      | 0      | 0      | `[0, 0, 1, 1, 2, 2]` |

**Final Array**: `[0, 0, 1, 1, 2, 2]`

---

## 🔹 Complexity Analysis

| Complexity      | Value       |
|-----------------|-------------|
| Time Complexity | O(n)        |
| Space Complexity| O(1)        |

---

# ⚡ Optimal Approach

## 🔹 Approach
The **Dutch National Flag algorithm** sorts the array in a **single pass** using **three pointers**:
- `low`: Tracks the boundary of the `0`s region.
- `mid`: Current element being processed.
- `high`: Tracks the boundary of the `2`s region.

### **Rules**:
1. If `nums[mid] == 0`, swap with `nums[low]` and increment both `low` and `mid`.
2. If `nums[mid] == 1`, just increment `mid`.
3. If `nums[mid] == 2`, swap with `nums[high]` and decrement `high`.

This ensures the array is partitioned into three regions in **O(n) time** with **O(1) space**.

---

## 🔹 Why This Works
- **0s** are moved to the front, **2s** to the end, and **1s** naturally stay in the middle.
- Swapping with `high` ensures that `2`s are placed correctly without disturbing the `0`s region.
- The algorithm guarantees **in-place sorting** with minimal swaps.

---

## 🔹 Algorithm
1. Initialize `low = 0`, `mid = 0`, `high = nums.length - 1`.
2. While `mid <= high`:
   - If `nums[mid] == 0`, swap with `nums[low]` and increment `low` and `mid`.
   - If `nums[mid] == 1`, increment `mid`.
   - If `nums[mid] == 2`, swap with `nums[high]` and decrement `high`.
3. Terminate when `mid > high`.

---

## 🔹 Code

```java
class Solution {
    public void sortColors(int[] nums) {
        int low = 0, mid = 0, high = nums.length - 1;

        while (mid <= high) {
            if (nums[mid] == 0) {
                swap(nums, low, mid);
                low++;
                mid++;
            } else if (nums[mid] == 1) {
                mid++;
            } else {
                swap(nums, mid, high);
                high--;
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
```

---

## 🔹 Detailed Dry Run

**Input**: `nums = [2, 0, 2, 1, 1, 0]`

| Step | low | mid | high | nums (before swap) | Action                     | nums (after swap)  |
|------|-----|-----|------|--------------------|----------------------------|--------------------|
| 1    | 0   | 0   | 5    | `[2, 0, 2, 1, 1, 0]` | `nums[mid] == 2` → swap with `high` | `[0, 0, 2, 1, 1, 2]` |
| 2    | 0   | 0   | 4    | `[0, 0, 2, 1, 1, 2]` | `nums[mid] == 0` → swap with `low` | `[0, 0, 2, 1, 1, 2]` |
| 3    | 1   | 1   | 4    | `[0, 0, 2, 1, 1, 2]` | `nums[mid] == 0` → swap with `low` | `[0, 0, 2, 1, 1, 2]` |
| 4    | 2   | 2   | 4    | `[0, 0, 2, 1, 1, 2]` | `nums[mid] == 2` → swap with `high` | `[0, 0, 1, 1, 2, 2]` |
| 5    | 2   | 2   | 3    | `[0, 0, 1, 1, 2, 2]` | `nums[mid] == 1` → increment `mid`  | `[0, 0, 1, 1, 2, 2]` |
| 6    | 2   | 3   | 3    | `[0, 0, 1, 1, 2, 2]` | `nums[mid] == 1` → increment `mid`  | `[0, 0, 1, 1, 2, 2]` |

**Termination**: `mid > high` → **Final Array**: `[0, 0, 1, 1, 2, 2]`

---

## 🔹 Complexity Analysis

| Complexity      | Value       |
|-----------------|-------------|
| Time Complexity | O(n)        |
| Space Complexity| O(1)        |

---

# 🔍 Edge Cases

| Edge Case               | Expected Output                     |
|-------------------------|-------------------------------------|
| Empty array             | `[]`                                |
| Single element (`0`)    | `[0]`                               |
| Single element (`1`)    | `[1]`                               |
| Single element (`2`)    | `[2]`                               |
| All `0`s                | `[0, 0, 0]`                         |
| All `1`s                | `[1, 1, 1]`                         |
| All `2`s                | `[2, 2, 2]`                         |
| Already sorted          | `[0, 1, 2]`                         |
| Reverse sorted          | `[2, 1, 0]` → `[0, 1, 2]`           |
| Large input (n=300)     | Correctly sorted array              |

---

# 📚 Key Takeaways

✅ **Dutch National Flag Algorithm** is optimal for **three-way partitioning**.
✅ **Three pointers** (`low`, `mid`, `high`) efficiently partition the array in **O(n) time**.
✅ **In-place sorting** avoids extra space, making it **O(1) space**.
✅ **Single-pass traversal** is more efficient than counting-based approaches.
✅ **Swapping strategy** ensures correct placement of `0`s and `2`s without disturbing `1`s.

---

# 🚀 Interview Tips

🔹 **Follow-up Questions**:
- Can you generalize this for `k` colors?
- How would you modify the algorithm if the order was `2, 1, 0`?
- What if the input contains invalid values (e.g., `3`)?

🔹 **Common Pitfalls**:
- Forgetting to **increment `mid` after swapping `0`s** (leads to infinite loop).
- Swapping `2`s without **decrementing `high`** (may place `2`s in the wrong region).
- Not handling **edge cases** (empty array, single element).

🔹 **Alternative Approaches**:
- **Counting Sort** (works but uses extra space).
- **Two-pass approach** (first pass for `0`s, second for `2`s).

🔹 **Optimization Insight**:
- The **Dutch National Flag algorithm** is **optimal** for this problem due to its **O(n) time and O(1) space** complexity.

---

# ✅ Conclusion

The **optimal solution** leverages the **Dutch National Flag algorithm**, which efficiently sorts the array in **a single pass** using **three pointers**. This approach is **in-place**, **time-optimal**, and **space-efficient**, making it the best choice for this problem.

**Key Insight**: By maintaining three regions (`0`s, `1`s, `2`s), we ensure that each element is placed in its correct position with minimal swaps.

This problem is a **must-know** for technical interviews, as it tests **pointer manipulation**, **in-place sorting**, and **algorithmic optimization**.