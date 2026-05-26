# 📌 Move Zeroes

---

# 📝 Problem Statement

Given an integer array `nums`, move all `0`'s to the end of it while maintaining the relative order of the non-zero elements.

**Objective:**
Modify the input array **in-place** without returning anything. The solution must preserve the original order of non-zero elements.

**Constraints:**
- `1 <= nums.length <= 10^4`
- `-2^31 <= nums[i] <= 2^31 - 1`

**Example:**
```
Input: nums = [0,1,0,3,12]
Output: [1,3,12,0,0]
```

---

# 💡 Intuition

The key insight is to **separate non-zero and zero elements** while preserving their relative order. Instead of moving zeros to the end, we can **shift non-zero elements to the front** and fill the remaining positions with zeros. This avoids unnecessary swaps and reduces time complexity.

The optimal approach uses a **two-pointer technique**:
- One pointer (`nonZeroPtr`) tracks where the next non-zero element should be placed.
- Another pointer (`current`) iterates through the array to find non-zero elements.

This ensures we only traverse the array once, making the solution efficient.

---

# 🐌 Brute Force Approach

## 🔹 Approach

The brute force approach involves:
1. Collecting all non-zero elements in a temporary list.
2. Copying them back to the original array.
3. Filling the remaining positions with zeros.

This approach requires extra space and two passes over the array.

---

## 🔹 Algorithm

1. Initialize an empty list `nonZeroElements`.
2. Traverse the input array:
   - If the current element is non-zero, add it to `nonZeroElements`.
3. Copy all elements from `nonZeroElements` back to the original array.
4. Fill the remaining positions in the array with zeros.

---

## 🔹 Code

```java
class Solution {
    public void moveZeroes(int[] nums) {
        if (nums == null || nums.length == 0) return;

        // Step 1: Collect non-zero elements
        java.util.List<Integer> nonZeroElements = new java.util.ArrayList<>();
        for (int num : nums) {
            if (num != 0) {
                nonZeroElements.add(num);
            }
        }

        // Step 2: Copy non-zero elements back
        for (int i = 0; i < nonZeroElements.size(); i++) {
            nums[i] = nonZeroElements.get(i);
        }

        // Step 3: Fill remaining positions with zeros
        for (int i = nonZeroElements.size(); i < nums.length; i++) {
            nums[i] = 0;
        }
    }
}
```

---

## 🔹 Dry Run

**Input:** `nums = [0, 1, 0, 3, 12]`

| Step | Action | `nonZeroElements` | `nums` State |
|------|--------|-------------------|--------------|
| 1    | Initialize `nonZeroElements` | `[]` | `[0, 1, 0, 3, 12]` |
| 2    | Traverse `nums` | | |
|      | `nums[0] = 0` (skip) | `[]` | `[0, 1, 0, 3, 12]` |
|      | `nums[1] = 1` (add) | `[1]` | `[0, 1, 0, 3, 12]` |
|      | `nums[2] = 0` (skip) | `[1]` | `[0, 1, 0, 3, 12]` |
|      | `nums[3] = 3` (add) | `[1, 3]` | `[0, 1, 0, 3, 12]` |
|      | `nums[4] = 12` (add) | `[1, 3, 12]` | `[0, 1, 0, 3, 12]` |
| 3    | Copy `nonZeroElements` back | | |
|      | `nums[0] = 1` | | `[1, 1, 0, 3, 12]` |
|      | `nums[1] = 3` | | `[1, 3, 0, 3, 12]` |
|      | `nums[2] = 12` | | `[1, 3, 12, 3, 12]` |
| 4    | Fill remaining with zeros | | |
|      | `nums[3] = 0` | | `[1, 3, 12, 0, 12]` |
|      | `nums[4] = 0` | | `[1, 3, 12, 0, 0]` |

**Final Output:** `[1, 3, 12, 0, 0]`

---

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(n) |
| Space Complexity | O(n) |

---

# ⚡ Optimal Approach

## 🔹 Approach

The optimal approach uses **two pointers** to move non-zero elements to the front in a single pass:
- `nonZeroPtr`: Tracks the position where the next non-zero element should be placed.
- `current`: Iterates through the array to find non-zero elements.

When a non-zero element is found, it is swapped with the element at `nonZeroPtr`, and `nonZeroPtr` is incremented. This ensures all non-zero elements are moved to the front while preserving their order.

---

## 🔹 Why This Works

- **In-place modification:** No extra space is used.
- **Single pass:** The array is traversed only once.
- **Order preservation:** Non-zero elements retain their relative order.
- **Efficiency:** Each element is processed in constant time.

---

## 🔹 Algorithm

1. Initialize `nonZeroPtr = 0`.
2. Traverse the array with `current` from `0` to `nums.length - 1`:
   - If `nums[current] != 0`, swap `nums[current]` with `nums[nonZeroPtr]` and increment `nonZeroPtr`.
3. The zeros will automatically be moved to the end.

---

## 🔹 Code

```java
class Solution {
    public void moveZeroes(int[] nums) {
        if (nums == null || nums.length == 0) return;

        int nonZeroPtr = 0;
        for (int current = 0; current < nums.length; current++) {
            if (nums[current] != 0) {
                // Swap non-zero element with the position at nonZeroPtr
                int temp = nums[nonZeroPtr];
                nums[nonZeroPtr] = nums[current];
                nums[current] = temp;
                nonZeroPtr++;
            }
        }
    }
}
```

---

## 🔹 Detailed Dry Run

**Input:** `nums = [0, 1, 0, 3, 12]`

| Iteration | `current` | `nums[current]` | `nonZeroPtr` | Action | `nums` State |
|-----------|-----------|-----------------|--------------|--------|--------------|
| 0         | 0         | 0               | 0            | Skip   | `[0, 1, 0, 3, 12]` |
| 1         | 1         | 1               | 0            | Swap `nums[0]` and `nums[1]` | `[1, 0, 0, 3, 12]` |
|           |           |                 | 1            | Increment `nonZeroPtr` | |
| 2         | 2         | 0               | 1            | Skip   | `[1, 0, 0, 3, 12]` |
| 3         | 3         | 3               | 1            | Swap `nums[1]` and `nums[3]` | `[1, 3, 0, 0, 12]` |
|           |           |                 | 2            | Increment `nonZeroPtr` | |
| 4         | 4         | 12              | 2            | Swap `nums[2]` and `nums[4]` | `[1, 3, 12, 0, 0]` |
|           |           |                 | 3            | Increment `nonZeroPtr` | |

**Final Output:** `[1, 3, 12, 0, 0]`

---

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(n) |
| Space Complexity | O(1) |

---

# 🔍 Edge Cases

| Edge Case | Expected Output | Explanation |
|-----------|-----------------|-------------|
| `nums = []` | `[]` | Empty array remains unchanged. |
| `nums = [0]` | `[0]` | Single zero remains unchanged. |
| `nums = [1]` | `[1]` | Single non-zero remains unchanged. |
| `nums = [0, 0, 0]` | `[0, 0, 0]` | All zeros remain in place. |
| `nums = [1, 2, 3]` | `[1, 2, 3]` | No zeros to move. |
| `nums = [0, 1, 0, 0, 2]` | `[1, 2, 0, 0, 0]` | Multiple zeros at different positions. |
| `nums = [-1, 0, -2, 0]` | `[-1, -2, 0, 0]` | Negative numbers are treated as non-zero. |

---

# 📚 Key Takeaways

1. **Two-pointer technique** is powerful for in-place array modifications.
2. **Preserving order** is crucial in problems like this.
3. **Space optimization** can be achieved by reusing the input array.
4. **Single-pass solutions** are often optimal for linear data structures.
5. **Edge cases** must be handled explicitly to avoid runtime errors.

---

# 🚀 Interview Tips

1. **Clarify constraints:** Ask if the solution must be in-place or if extra space is allowed.
2. **Discuss trade-offs:** Compare brute force and optimal approaches in terms of time and space.
3. **Optimize for readability:** The optimal solution should be clean and easy to explain.
4. **Test edge cases:** Always verify with empty, single-element, and all-zero arrays.
5. **Follow-up questions:**
   - Can you solve it without swapping?
   - What if the problem required moving all even numbers to the end?
   - How would you handle a stream of numbers instead of an array?

---

# ✅ Conclusion

The **optimal solution** using the two-pointer technique is preferred because:
- It operates **in-place** with **O(1) space**.
- It processes the array in **a single pass (O(n) time)**.
- It **preserves the order** of non-zero elements efficiently.

The key insight is to **focus on moving non-zero elements to the front** rather than explicitly moving zeros to the end. This approach minimizes operations and maximizes efficiency, making it ideal for technical interviews and production code.