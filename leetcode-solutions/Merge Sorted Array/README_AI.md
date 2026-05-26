# 📌 Merge Sorted Array

---

# 📝 Problem Statement

You are given two integer arrays `nums1` and `nums2`, sorted in **non-decreasing order**, and two integers `m` and `n`, representing the number of elements in `nums1` and `nums2` respectively.

**Objective**:
Merge `nums2` into `nums1` as one sorted array in-place. The final sorted array should be stored inside `nums1`.

**Constraints**:
- `nums1` has a length of `m + n`, where the first `m` elements denote the elements that should be merged, and the last `n` elements are set to `0` and should be ignored.
- `nums2` has a length of `n`.
- Both arrays are sorted in non-decreasing order.
- You must modify `nums1` in-place without returning anything.

**Example**:
```
Input: nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
Output: [1,2,2,3,5,6]
```

---

# 💡 Intuition

The key insight is that both arrays are already sorted. Instead of merging them from the start (which would require shifting elements in `nums1`), we can merge them from the **end** to avoid overwriting unprocessed elements in `nums1`.

This approach leverages the extra space at the end of `nums1` (initially filled with zeros) to build the merged array in reverse order, ensuring we don’t lose any data during the merge.

---

# 🐌 Brute Force Approach

## 🔹 Approach

The naive approach involves:
1. Copying all elements from `nums2` into the extra space at the end of `nums1`.
2. Sorting the entire `nums1` array using a standard sorting algorithm.

This approach is simple but inefficient due to the sorting step, which doesn’t take advantage of the existing sorted order.

---

## 🔹 Algorithm

1. Copy all elements from `nums2` into the end of `nums1` (starting at index `m`).
2. Sort the entire `nums1` array in non-decreasing order.

---

## 🔹 Code

```java
class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        // Copy nums2 into the end of nums1
        for (int i = 0; i < n; i++) {
            nums1[m + i] = nums2[i];
        }
        // Sort the entire array
        Arrays.sort(nums1);
    }
}
```

---

## 🔹 Dry Run

**Input**: `nums1 = [1,2,3,0,0,0]`, `m = 3`, `nums2 = [2,5,6]`, `n = 3`

| Step | Action | `nums1` State |
|------|--------|---------------|
| 1    | Copy `nums2` into `nums1` | `[1,2,3,2,5,6]` |
| 2    | Sort `nums1` | `[1,2,2,3,5,6]` |

---

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O((m + n) log(m + n)) |
| Space Complexity | O(1) |

---

# ⚡ Optimal Approach

## 🔹 Approach

The optimal approach merges the two arrays **from the end** to avoid overwriting unprocessed elements in `nums1`. We use three pointers:
- `i` for the last valid element in `nums1` (index `m - 1`).
- `j` for the last element in `nums2` (index `n - 1`).
- `k` for the last position in `nums1` (index `m + n - 1`).

We compare elements from `nums1` and `nums2` starting from the end and place the larger element at position `k`, then decrement the respective pointers.

---

## 🔹 Why This Works

- **In-place merging**: By starting from the end, we ensure that we don’t overwrite any unprocessed elements in `nums1`.
- **Efficiency**: We only traverse each array once, making the algorithm linear in time.
- **No extra space**: The merge is done in-place, so no additional memory is required.

---

## 🔹 Algorithm

1. Initialize three pointers:
   - `i = m - 1` (last valid element in `nums1`).
   - `j = n - 1` (last element in `nums2`).
   - `k = m + n - 1` (last position in `nums1`).
2. While `i >= 0` and `j >= 0`:
   - If `nums1[i] > nums2[j]`, place `nums1[i]` at `nums1[k]` and decrement `i` and `k`.
   - Else, place `nums2[j]` at `nums1[k]` and decrement `j` and `k`.
3. If there are remaining elements in `nums2` (i.e., `j >= 0`), copy them to the front of `nums1`.

---

## 🔹 Code

```java
class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1;  // Pointer for nums1
        int j = n - 1;  // Pointer for nums2
        int k = m + n - 1;  // Pointer for merged array

        while (i >= 0 && j >= 0) {
            if (nums1[i] > nums2[j]) {
                nums1[k--] = nums1[i--];
            } else {
                nums1[k--] = nums2[j--];
            }
        }

        // Copy remaining elements from nums2 if any
        while (j >= 0) {
            nums1[k--] = nums2[j--];
        }
    }
}
```

---

## 🔹 Detailed Dry Run

**Input**: `nums1 = [1,2,3,0,0,0]`, `m = 3`, `nums2 = [2,5,6]`, `n = 3`

| Iteration | `i` | `j` | `k` | `nums1[i]` | `nums2[j]` | Action | `nums1` State |
|-----------|-----|-----|-----|------------|------------|--------|---------------|
| 1         | 2   | 2   | 5   | 3          | 6          | Place `6` at `k` | `[1,2,3,0,0,6]` |
| 2         | 2   | 1   | 4   | 3          | 5          | Place `5` at `k` | `[1,2,3,0,5,6]` |
| 3         | 2   | 0   | 3   | 3          | 2          | Place `3` at `k` | `[1,2,3,3,5,6]` |
| 4         | 1   | 0   | 2   | 2          | 2          | Place `2` at `k` | `[1,2,2,3,5,6]` |

---

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(m + n) |
| Space Complexity | O(1) |

---

# 🔍 Edge Cases

| Edge Case | Description |
|-----------|-------------|
| Empty `nums1` | `nums1 = [0]`, `m = 0`, `nums2 = [1]`, `n = 1` → `[1]` |
| Empty `nums2` | `nums1 = [1]`, `m = 1`, `nums2 = []`, `n = 0` → `[1]` |
| All elements in `nums1` are smaller | `nums1 = [1,2,3,0,0,0]`, `m = 3`, `nums2 = [4,5,6]`, `n = 3` → `[1,2,3,4,5,6]` |
| All elements in `nums2` are smaller | `nums1 = [4,5,6,0,0,0]`, `m = 3`, `nums2 = [1,2,3]`, `n = 3` → `[1,2,3,4,5,6]` |
| Duplicates | `nums1 = [1,2,2,0,0,0]`, `m = 3`, `nums2 = [2,3,3]`, `n = 3` → `[1,2,2,2,3,3]` |

---

# 📚 Key Takeaways

1. **Two-pointer technique**: Merging from the end is a classic example of using two pointers to optimize in-place operations.
2. **In-place merging**: Avoids extra space by leveraging the existing array structure.
3. **Efficiency**: The optimal approach reduces time complexity from O((m + n) log(m + n)) to O(m + n).
4. **Edge case handling**: Always consider empty inputs, duplicates, and cases where one array dominates the other.

---

# 🚀 Interview Tips

1. **Follow-up questions**:
   - How would you handle this if `nums1` didn’t have extra space?
   - Can you solve this with O(1) space and O(m + n) time?
   - What if the arrays were sorted in descending order?

2. **Common pitfalls**:
   - Forgetting to handle remaining elements in `nums2` after the main loop.
   - Overwriting unprocessed elements in `nums1` by merging from the start.
   - Off-by-one errors in pointer initialization.

3. **Alternative approaches**:
   - Using a temporary array to merge and then copying back to `nums1` (O(m + n) space).
   - Recursive merging (not recommended due to stack space).

---

# ✅ Conclusion

The optimal solution efficiently merges two sorted arrays in-place by leveraging the two-pointer technique from the end. This approach ensures **O(m + n) time complexity** and **O(1) space complexity**, making it the preferred choice for interviews and production code.

**Key insight**: Always look for opportunities to merge sorted data from the end to avoid overwriting and reduce space usage.