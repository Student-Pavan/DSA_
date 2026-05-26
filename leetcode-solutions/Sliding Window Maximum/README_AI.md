# 📌 Sliding Window Maximum

---

# 📝 Problem Statement

Given an integer array `nums` and an integer `k`, return the maximum value in every contiguous subarray of size `k`.

### **Objective**
Find the maximum element in each sliding window of size `k` as it moves from the beginning to the end of the array.

### **Input**
- `nums`: An array of integers (may contain duplicates, negatives, or be empty).
- `k`: An integer representing the size of the sliding window (1 ≤ `k` ≤ `nums.length`).

### **Output**
- An array containing the maximum value for each sliding window.

### **Constraints**
- `1 <= nums.length <= 10^5`
- `-10^4 <= nums[i] <= 10^4`
- `1 <= k <= nums.length`

---

# 💡 Intuition

The problem requires finding the maximum in every window of size `k`. A naive approach would involve scanning each window independently, leading to O(n*k) time. However, this is inefficient for large inputs.

The key insight is to **reuse computations** from previous windows. Instead of recalculating the maximum for every window, we can maintain a data structure that efficiently tracks the maximum element in the current window while allowing quick updates as the window slides.

A **monotonic deque** (double-ended queue) is ideal for this purpose. It maintains elements in decreasing order, ensuring the front always holds the current maximum. As the window slides, we remove elements that are no longer in the window and add new elements while preserving the deque's order.

---

# 🐌 Brute Force Approach

## 🔹 Approach
For each window of size `k`, iterate through all elements in the window and find the maximum. This approach checks every possible window independently, leading to redundant computations.

## 🔹 Algorithm
1. Initialize an empty result list.
2. Iterate through the array from index `0` to `nums.length - k`.
3. For each starting index `i`, scan the subarray `nums[i]` to `nums[i + k - 1]`.
4. Find the maximum in this subarray and add it to the result.
5. Return the result list.

## 🔹 Code

```java
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        int[] result = new int[n - k + 1];

        for (int i = 0; i <= n - k; i++) {
            int max = nums[i];
            for (int j = i + 1; j < i + k; j++) {
                if (nums[j] > max) {
                    max = nums[j];
                }
            }
            result[i] = max;
        }

        return result;
    }
}
```

## 🔹 Dry Run

**Input:** `nums = [1, 3, -1, -3, 5, 3, 6, 7]`, `k = 3`

| Window Start | Window Elements | Max in Window | Result |
|--------------|-----------------|---------------|--------|
| 0            | [1, 3, -1]      | 3             | [3]    |
| 1            | [3, -1, -3]     | 3             | [3, 3] |
| 2            | [-1, -3, 5]     | 5             | [3, 3, 5] |
| 3            | [-3, 5, 3]      | 5             | [3, 3, 5, 5] |
| 4            | [5, 3, 6]       | 6             | [3, 3, 5, 5, 6] |
| 5            | [3, 6, 7]       | 7             | [3, 3, 5, 5, 6, 7] |

**Final Result:** `[3, 3, 5, 5, 6, 7]`

## 🔹 Complexity Analysis

| Complexity      | Value       |
|-----------------|-------------|
| Time Complexity | O(n * k)    |
| Space Complexity| O(n - k + 1)|

---

# ⚡ Optimal Approach

## 🔹 Approach
Use a **monotonic deque** to maintain the maximum element in the current window efficiently. The deque stores indices of elements in decreasing order of their values. As the window slides, we:
1. Remove indices that are outside the current window.
2. Remove indices of elements smaller than the new element (they cannot be the maximum in any future window).
3. Add the new element's index to the deque.
4. The front of the deque always holds the maximum for the current window.

## 🔹 Why This Works
- The deque ensures that the front always points to the maximum in the current window.
- Elements smaller than the new element are removed because they cannot be the maximum in any future window containing the new element.
- The deque's size is bounded by `k`, ensuring efficient operations.

## 🔹 Algorithm
1. Initialize a deque to store indices and a result list.
2. Iterate through the array:
   - Remove indices from the front that are outside the current window.
   - Remove indices from the back if their values are smaller than the current element.
   - Add the current index to the deque.
   - If the window has reached size `k`, add the front of the deque to the result.
3. Return the result list.

## 🔹 Code

```java
import java.util.Deque;
import java.util.LinkedList;

class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        int[] result = new int[n - k + 1];
        Deque<Integer> deque = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            // Remove indices outside the current window
            while (!deque.isEmpty() && deque.peekFirst() <= i - k) {
                deque.pollFirst();
            }

            // Remove indices of elements smaller than the current element
            while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) {
                deque.pollLast();
            }

            deque.offerLast(i);

            // Add the maximum to the result once the window is formed
            if (i >= k - 1) {
                result[i - k + 1] = nums[deque.peekFirst()];
            }
        }

        return result;
    }
}
```

## 🔹 Detailed Dry Run

**Input:** `nums = [1, 3, -1, -3, 5, 3, 6, 7]`, `k = 3`

| Iteration | Current Element | Deque State (Indices) | Action                                                                 | Result       |
|-----------|-----------------|-----------------------|------------------------------------------------------------------------|--------------|
| 0         | 1               | [0]                   | Add 0 to deque.                                                        | []           |
| 1         | 3               | [1]                   | Remove 0 (3 > 1), add 1.                                               | []           |
| 2         | -1              | [1, 2]                | Add 2 (-1 < 3).                                                        | [3]          |
| 3         | -3              | [1, 2, 3]             | Add 3 (-3 < -1).                                                       | [3, 3]       |
| 4         | 5               | [4]                   | Remove 1, 2, 3 (5 > -3, -1, 3), add 4.                                 | [3, 3, 5]    |
| 5         | 3               | [4, 5]                | Add 5 (3 < 5).                                                         | [3, 3, 5, 5] |
| 6         | 6               | [6]                   | Remove 4, 5 (6 > 3, 5), add 6.                                         | [3, 3, 5, 5, 6] |
| 7         | 7               | [7]                   | Remove 6 (7 > 6), add 7.                                               | [3, 3, 5, 5, 6, 7] |

**Final Result:** `[3, 3, 5, 5, 6, 7]`

## 🔹 Complexity Analysis

| Complexity      | Value       |
|-----------------|-------------|
| Time Complexity | O(n)        |
| Space Complexity| O(k)        |

---

# 🔍 Edge Cases

| Edge Case                     | Expected Output                     | Explanation                                                                 |
|-------------------------------|-------------------------------------|-----------------------------------------------------------------------------|
| `nums = []`, `k = 0`          | `[]`                                | Empty input array.                                                          |
| `nums = [1]`, `k = 1`         | `[1]`                               | Single-element array.                                                       |
| `nums = [1, -1]`, `k = 1`     | `[1, -1]`                           | Window size 1.                                                              |
| `nums = [7, 7, 7, 7]`, `k = 2`| `[7, 7, 7]`                         | All elements are the same.                                                  |
| `nums = [1, 3, 1, 2, 0, 5]`, `k = 3` | `[3, 3, 2, 5]`          | Contains duplicates and negative values.                                   |
| `nums = [1, 2, 3, 4, 5]`, `k = 5` | `[5]`                     | Window size equals array length.                                            |

---

# 📚 Key Takeaways

1. **Sliding Window Pattern**: Recognize problems where a window slides over an array, and computations can be reused.
2. **Monotonic Deque**: A powerful tool for maintaining maximum/minimum in a sliding window efficiently.
3. **Time Complexity Matters**: Brute force is O(n*k), while the deque approach is O(n) — crucial for large inputs.
4. **Edge Cases**: Always test with empty arrays, single elements, duplicates, and large inputs.

---

# 🚀 Interview Tips

1. **Clarify Constraints**: Ask if `k` can be larger than `nums.length` (though the problem states `1 <= k <= nums.length`).
2. **Discuss Trade-offs**: Mention the brute force approach first, then explain why the deque is optimal.
3. **Follow-up Questions**:
   - How would you handle a dynamic window size?
   - Can you modify the solution to return the minimum instead of the maximum?
4. **Common Pitfalls**:
   - Forgetting to remove indices outside the current window.
   - Not maintaining the deque in decreasing order.
5. **Alternative Approaches**:
   - Segment Trees (O(n log n) time, O(n) space) — overkill for this problem but useful for dynamic queries.

---

# ✅ Conclusion

The **Sliding Window Maximum** problem is a classic example of optimizing a brute force solution using a **monotonic deque**. While the brute force approach is straightforward, it fails to scale for large inputs. The deque-based solution efficiently maintains the maximum in each window by leveraging the properties of a monotonic queue, reducing the time complexity from O(n*k) to O(n).

**Key Insight**: The deque ensures that the front always holds the maximum for the current window, while smaller elements are discarded as they cannot be the maximum in any future window containing larger elements. This approach is both elegant and efficient, making it a must-know for technical interviews.