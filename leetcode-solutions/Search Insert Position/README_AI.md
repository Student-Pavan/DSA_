# 📌 Search Insert Position

---

# 📝 Problem Statement

Given a **sorted array** of distinct integers and a **target value**, return the **index** where the target is found. If the target is not found, return the **index where it would be inserted** to maintain the sorted order.

### **Objective**
Find the correct position for the target in the array with **O(log n)** time complexity.

### **Input**
- `nums`: A sorted array of distinct integers (ascending order)
- `target`: An integer value to search for or insert

### **Output**
- The index of the target if found, otherwise the insertion position

### **Constraints**
- `1 <= nums.length <= 10⁴`
- `-10⁴ <= nums[i] <= 10⁴`
- `nums` contains **distinct** values sorted in **ascending** order
- `-10⁴ <= target <= 10⁴`

---

# 💡 Intuition

Since the array is **sorted**, we can leverage **binary search** to achieve **O(log n)** time complexity. The key insight is that binary search not only finds the target but also naturally identifies the correct insertion position when the target is absent.

The insertion position is determined by the point where the search loop terminates — specifically, the `left` pointer will point to the correct index where the target should be inserted to maintain order.

---

# 🐌 Brute Force Approach

## 🔹 Approach

A naive approach involves **linear search** through the array until we find the target or a value greater than the target. This works but has **O(n)** time complexity, which is inefficient for large arrays.

---

## 🔹 Algorithm

1. Iterate through each element in the array.
2. If the current element equals the target, return its index.
3. If the current element is greater than the target, return the current index (insertion position).
4. If the loop completes without finding the target, return the length of the array (insert at the end).

---

## 🔹 Code

```java
class Solution {
    public int searchInsert(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target || nums[i] > target) {
                return i;
            }
        }
        return nums.length;
    }
}
```

---

## 🔹 Dry Run

**Example:** `nums = [1, 3, 5, 6]`, `target = 2`

| Iteration | Current Index | Current Value | Action | Result |
|-----------|---------------|---------------|--------|--------|
| 1         | 0             | 1             | 1 < 2 → continue | -      |
| 2         | 1             | 3             | 3 > 2 → return index 1 | **1**  |

**Final Output:** `1`

---

## 🔹 Complexity Analysis

| Complexity       | Value   |
|------------------|---------|
| Time Complexity  | O(n)    |
| Space Complexity | O(1)    |

---

# ⚡ Optimal Approach

## 🔹 Approach

Use **binary search** to achieve **O(log n)** time complexity. The algorithm narrows down the search space by half in each iteration. When the loop ends, `left` will point to the correct insertion position.

---

## 🔹 Why This Works

- The array is **sorted**, so binary search is applicable.
- When the target is not found, `left` and `right` cross each other, and `left` points to the first element greater than the target (or the end of the array).
- This position is exactly where the target should be inserted.

---

## 🔹 Algorithm

1. Initialize `left = 0` and `right = nums.length - 1`.
2. While `left <= right`:
   - Compute `mid = left + (right - left) / 2`.
   - If `nums[mid] == target`, return `mid`.
   - If `nums[mid] < target`, move `left = mid + 1`.
   - If `nums[mid] > target`, move `right = mid - 1`.
3. Return `left` (insertion position).

---

## 🔹 Code

```java
class Solution {
    public int searchInsert(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }
}
```

---

## 🔹 Detailed Dry Run

**Example:** `nums = [1, 3, 5, 6]`, `target = 2`

| Step | Left | Right | Mid | nums[mid] | Action | State |
|------|------|-------|-----|-----------|--------|-------|
| 1    | 0    | 3     | 1   | 3         | 3 > 2 → right = mid - 1 | left=0, right=0 |
| 2    | 0    | 0     | 0   | 1         | 1 < 2 → left = mid + 1  | left=1, right=0 |
| Loop ends → return left | | | | | | **1** |

**Final Output:** `1`

---

## 🔹 Complexity Analysis

| Complexity       | Value   |
|------------------|---------|
| Time Complexity  | O(log n)|
| Space Complexity | O(1)    |

---

# 🔍 Edge Cases

| Case | Input | Expected Output | Explanation |
|------|-------|-----------------|-------------|
| Empty array | `[]`, `5` | `0` | Insert at start |
| Single element (match) | `[5]`, `5` | `0` | Found at index 0 |
| Single element (no match) | `[5]`, `2` | `0` | Insert before 5 |
| Target smaller than all | `[1, 3, 5]`, `0` | `0` | Insert at start |
| Target larger than all | `[1, 3, 5]`, `6` | `3` | Insert at end |
| Target in middle | `[1, 3, 5, 7]`, `4` | `2` | Insert between 3 and 5 |
| Duplicate values (not possible per constraints) | N/A | N/A | Constraints ensure distinct values |

---

# 📚 Key Takeaways

- **Binary search** is optimal for sorted arrays.
- The `left` pointer naturally points to the insertion position when the target is not found.
- Always check edge cases: empty array, single element, and boundary values.
- Binary search reduces time complexity from **O(n)** to **O(log n)**.

---

# 🚀 Interview Tips

- **Follow-up:** What if the array contains duplicates? (Answer: Still works, but insertion position may vary.)
- **Common Pitfall:** Forgetting to return `left` after the loop.
- **Alternative Approach:** Use `Arrays.binarySearch()` in Java, but implement manually for interviews.
- **Optimization Insight:** Binary search is **always** preferred for sorted data.

---

# ✅ Conclusion

The **optimal solution** uses **binary search** to achieve **O(log n)** time complexity, which is significantly faster than the brute force **O(n)** approach. The key insight is that the `left` pointer in binary search naturally converges to the correct insertion position when the target is not found. This problem is a classic example of how binary search can be adapted beyond simple lookup to solve insertion problems efficiently.

Mastering this pattern is crucial for technical interviews at top-tier companies.