```markdown
# 📌 Squares of a Sorted Array

---

# 📝 Problem Statement

Given an integer array `nums` sorted in **non-decreasing order**, return an array of the **squares of each number** sorted in non-decreasing order.

### **Objective**
Compute the squares of all elements in `nums` and return them in sorted order.

### **Input**
- An integer array `nums` of length `n` where `1 <= n <= 10^4`
- Each element in `nums` satisfies `-10^4 <= nums[i] <= 10^4`
- The array is **sorted in non-decreasing order**

### **Output**
- An integer array of the squares of `nums` sorted in non-decreasing order

### **Constraints**
- Must run in **O(n)** time complexity
- Must use **O(1)** extra space (excluding output array)

---

# 💡 Intuition

The key observation here is that the input array is **sorted**, but it may contain **negative numbers**. When squared, negative numbers can become larger than positive numbers (e.g., `-3² = 9` vs `2² = 4`). This means the largest squared values could be at **either end** of the array, not just the right side.

Instead of squaring all elements and sorting (which would take O(n log n) time), we can leverage the **two-pointer technique** to merge the squares from both ends in **O(n)** time.

---

# 🐌 Brute Force Approach

## 🔹 Approach

1. **Square all elements** in the array.
2. **Sort the squared array** in non-decreasing order.

This approach is straightforward but inefficient due to the sorting step.

---

## 🔹 Algorithm

1. Iterate through each element in `nums` and compute its square.
2. Store the squared values in a new array.
3. Sort the new array in ascending order.
4. Return the sorted array.

---

## 🔹 Code

```java
import java.util.Arrays;

class Solution {
    public int[] sortedSquares(int[] nums) {
        int n = nums.length;
        int[] squares = new int[n];

        // Square each element
        for (int i = 0; i < n; i++) {
            squares[i] = nums[i] * nums[i];
        }

        // Sort the squared array
        Arrays.sort(squares);

        return squares;
    }
}
```

---

## 🔹 Dry Run

**Input:** `nums = [-4, -1, 0, 3, 10]`

| Step | Action | Current Array State |
|------|--------|---------------------|
| 1 | Square all elements | `[16, 1, 0, 9, 100]` |
| 2 | Sort the array | `[0, 1, 9, 16, 100]` |

**Output:** `[0, 1, 9, 16, 100]`

---

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(n log n) |
| Space Complexity | O(n) (for output array) |

---

# ⚡ Optimal Approach

## 🔹 Approach

We use a **two-pointer technique** to merge the squares from both ends of the array in **O(n)** time.

1. Initialize two pointers: `left` at the start and `right` at the end of the array.
2. Compare the squares of `nums[left]` and `nums[right]`.
3. Place the larger square at the end of the result array and move the corresponding pointer inward.
4. Repeat until all elements are processed.

This approach efficiently merges the squares without sorting.

---

## 🔹 Why This Works

- The input array is **sorted**, so the largest squared values are at the **ends** (either `nums[left]` or `nums[right]`).
- By comparing the squares of the two ends, we can place the larger value at the correct position in the result array.
- This avoids the need for sorting, reducing time complexity to **O(n)**.

---

## 🔹 Algorithm

1. Initialize `left = 0`, `right = n - 1`, and `result` array of size `n`.
2. Iterate from `i = n - 1` down to `0`:
   - If `nums[left]² > nums[right]²`, place `nums[left]²` at `result[i]` and increment `left`.
   - Else, place `nums[right]²` at `result[i]` and decrement `right`.
3. Return `result`.

---

## 🔹 Code

```java
class Solution {
    public int[] sortedSquares(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        int left = 0;
        int right = n - 1;

        for (int i = n - 1; i >= 0; i--) {
            int leftSquare = nums[left] * nums[left];
            int rightSquare = nums[right] * nums[right];

            if (leftSquare > rightSquare) {
                result[i] = leftSquare;
                left++;
            } else {
                result[i] = rightSquare;
                right--;
            }
        }

        return result;
    }
}
```

---

## 🔹 Detailed Dry Run

**Input:** `nums = [-4, -1, 0, 3, 10]`

| Iteration | Left | Right | Left Square | Right Square | Action | Result Array |
|-----------|------|-------|-------------|--------------|--------|--------------|
| 1 | 0 | 4 | 16 | 100 | Place 100 at index 4 | `[ , , , , 100]` |
| 2 | 0 | 3 | 16 | 9 | Place 16 at index 3 | `[ , , , 16, 100]` |
| 3 | 1 | 3 | 1 | 9 | Place 9 at index 2 | `[ , , 9, 16, 100]` |
| 4 | 1 | 2 | 1 | 0 | Place 1 at index 1 | `[ , 1, 9, 16, 100]` |
| 5 | 2 | 2 | 0 | 0 | Place 0 at index 0 | `[0, 1, 9, 16, 100]` |

**Output:** `[0, 1, 9, 16, 100]`

---

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(n) |
| Space Complexity | O(n) (for output array) |

---

# 🔍 Edge Cases

| Edge Case | Input | Expected Output |
|-----------|-------|-----------------|
| Empty array | `[]` | `[]` |
| Single element | `[5]` | `[25]` |
| All negative | `[-3, -2, -1]` | `[1, 4, 9]` |
| All positive | `[1, 2, 3]` | `[1, 4, 9]` |
| Mixed signs | `[-4, -1, 0, 3, 10]` | `[0, 1, 9, 16, 100]` |
| Large input | `[-10000, ..., 10000]` | Sorted squares |

---

# 📚 Key Takeaways

✅ **Two-pointer technique** is powerful for sorted arrays.
✅ **Negative numbers** can produce larger squares than positives.
✅ **Brute force (sorting)** is simple but inefficient (O(n log n)).
✅ **Optimal solution** leverages the sorted property for O(n) time.
✅ **Space complexity** is O(n) due to output array (cannot be reduced further).

---

# 🚀 Interview Tips

❓ **Follow-up:** Can you solve it in **O(1)** space (excluding output)?
✅ **Answer:** No, because the output itself requires O(n) space.

❓ **Common Pitfall:** Forgetting that negative numbers squared can be larger than positives.
✅ **Solution:** Always consider both ends of the array.

❓ **Alternative Approach:** Could we use a **min-heap**?
✅ **Answer:** Yes, but it would take O(n log n) time, which is worse than the optimal solution.

---

# ✅ Conclusion

The **optimal solution** efficiently computes the squares of a sorted array in **O(n)** time using the **two-pointer technique**. This approach is **interview-ready** and demonstrates a deep understanding of **array manipulation** and **algorithmic optimization**.

**Key Insight:** The largest squared values are at the **ends** of the array, not necessarily the right side.

**Final Takeaway:** Always leverage the **sorted property** of input arrays to optimize solutions.
```