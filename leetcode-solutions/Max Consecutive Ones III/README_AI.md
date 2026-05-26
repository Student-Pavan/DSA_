# 📌 Max Consecutive Ones III

---

# 📝 Problem Statement

Given a binary array `nums` and an integer `k`, return the maximum number of consecutive `1`'s in the array if you can flip at most `k` `0`'s to `1`'s.

### **Objective**
Find the longest subarray containing only `1`'s after performing at most `k` flips (converting `0` to `1`).

### **Input**
- `nums`: A binary array (elements are `0` or `1`).
- `k`: Maximum number of `0`'s that can be flipped to `1`'s.

### **Output**
- An integer representing the length of the longest subarray of `1`'s after flipping at most `k` `0`'s.

### **Constraints**
- `1 <= nums.length <= 10^5`
- `nums[i]` is either `0` or `1`.
- `0 <= k <= nums.length`

---

# 💡 Intuition

The problem requires finding the longest subarray where the number of `0`'s does not exceed `k`. This is a classic **sliding window** problem where we maintain a window `[left, right]` such that the count of `0`'s in the window is ≤ `k`.

- **Key Insight**: The optimal window will have exactly `k` `0`'s (or fewer if the array doesn't contain enough `0`'s).
- **Pattern Recognition**: Sliding window is ideal for problems involving subarrays with constraints on element counts.
- **Optimization**: Instead of recalculating the number of `0`'s in every window, we can dynamically adjust the window size by moving the left pointer when the count exceeds `k`.

---

# 🐌 Brute Force Approach

## 🔹 Approach

The brute force approach involves checking all possible subarrays and counting the number of `0`'s in each. If the count of `0`'s is ≤ `k`, we update the maximum length.

- **Strategy**: Nested loops where the outer loop fixes the start of the subarray, and the inner loop expands the end.
- **Inefficiency**: This approach has a time complexity of **O(n²)**, which is too slow for large inputs (e.g., `n = 10^5`).

---

## 🔹 Algorithm

1. Initialize `maxLength = 0`.
2. For each starting index `i` (from `0` to `n-1`):
   - Initialize `zeroCount = 0`.
   - For each ending index `j` (from `i` to `n-1`):
     - If `nums[j] == 0`, increment `zeroCount`.
     - If `zeroCount > k`, break the inner loop.
     - Update `maxLength = max(maxLength, j - i + 1)`.
3. Return `maxLength`.

---

## 🔹 Code

```java
class Solution {
    public int longestOnes(int[] nums, int k) {
        int maxLength = 0;
        int n = nums.length;

        for (int i = 0; i < n; i++) {
            int zeroCount = 0;
            for (int j = i; j < n; j++) {
                if (nums[j] == 0) {
                    zeroCount++;
                }
                if (zeroCount > k) {
                    break;
                }
                maxLength = Math.max(maxLength, j - i + 1);
            }
        }
        return maxLength;
    }
}
```

---

## 🔹 Dry Run

**Input**: `nums = [1,1,1,0,0,0,1,1,1,1,0]`, `k = 2`

| Step | `i` | `j` | `nums[j]` | `zeroCount` | `maxLength` | Action                     |
|------|-----|-----|-----------|-------------|-------------|----------------------------|
| 1    | 0   | 0   | 1         | 0           | 1           | Update `maxLength`         |
| 2    | 0   | 1   | 1         | 0           | 2           | Update `maxLength`         |
| 3    | 0   | 2   | 1         | 0           | 3           | Update `maxLength`         |
| 4    | 0   | 3   | 0         | 1           | 4           | Update `maxLength`         |
| 5    | 0   | 4   | 0         | 2           | 5           | Update `maxLength`         |
| 6    | 0   | 5   | 0         | 3           | 5           | Break (zeroCount > k)      |
| 7    | 1   | 1   | 1         | 0           | 5           | Update `maxLength`         |
| ...  | ... | ... | ...       | ...         | ...         | ...                        |
| 20   | 6   | 9   | 1         | 1           | 6           | Update `maxLength` (final) |

**Final `maxLength`**: `6` (subarray `[1,1,1,0,0,1,1,1]` after flipping two `0`'s).

---

## 🔹 Complexity Analysis

| Complexity      | Value       |
|-----------------|-------------|
| Time Complexity | O(n²)       |
| Space Complexity| O(1)        |

---

# ⚡ Optimal Approach

## 🔹 Approach

The optimal solution uses a **sliding window** technique to maintain a window `[left, right]` where the number of `0`'s is ≤ `k`. The window expands by moving `right` and contracts by moving `left` when the count of `0`'s exceeds `k`.

- **Efficiency**: This approach runs in **O(n)** time with **O(1)** space.
- **Optimization Insight**: The window size is dynamically adjusted to ensure the constraint (`zeroCount ≤ k`) is always satisfied.

---

## 🔹 Why This Works

- The sliding window ensures that we only traverse the array once.
- By tracking the count of `0`'s, we can efficiently determine when to move the `left` pointer.
- The maximum window size encountered during the traversal is the answer.

---

## 🔹 Algorithm

1. Initialize `left = 0`, `maxLength = 0`, and `zeroCount = 0`.
2. Iterate over the array with `right` from `0` to `n-1`:
   - If `nums[right] == 0`, increment `zeroCount`.
   - While `zeroCount > k`, move `left` forward and decrement `zeroCount` if `nums[left] == 0`.
   - Update `maxLength = max(maxLength, right - left + 1)`.
3. Return `maxLength`.

---

## 🔹 Code

```java
class Solution {
    public int longestOnes(int[] nums, int k) {
        int left = 0;
        int maxLength = 0;
        int zeroCount = 0;

        for (int right = 0; right < nums.length; right++) {
            if (nums[right] == 0) {
                zeroCount++;
            }

            while (zeroCount > k) {
                if (nums[left] == 0) {
                    zeroCount--;
                }
                left++;
            }

            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }
}
```

---

## 🔹 Detailed Dry Run

**Input**: `nums = [1,1,1,0,0,0,1,1,1,1,0]`, `k = 2`

| Step | `right` | `nums[right]` | `zeroCount` | `left` | Window (`[left, right]`) | `maxLength` |
|------|---------|---------------|-------------|--------|--------------------------|-------------|
| 1    | 0       | 1             | 0           | 0      | [1]                      | 1           |
| 2    | 1       | 1             | 0           | 0      | [1,1]                    | 2           |
| 3    | 2       | 1             | 0           | 0      | [1,1,1]                  | 3           |
| 4    | 3       | 0             | 1           | 0      | [1,1,1,0]                | 4           |
| 5    | 4       | 0             | 2           | 0      | [1,1,1,0,0]              | 5           |
| 6    | 5       | 0             | 3           | 1      | [1,1,0,0,0]              | 5           |
| 7    | 6       | 1             | 2           | 3      | [0,0,0,1]                | 4           |
| 8    | 7       | 1             | 2           | 3      | [0,0,0,1,1]              | 5           |
| 9    | 8       | 1             | 2           | 3      | [0,0,0,1,1,1]            | 6           |
| 10   | 9       | 1             | 2           | 3      | [0,0,0,1,1,1,1]          | 7           |
| 11   | 10      | 0             | 3           | 4      | [0,0,1,1,1,1,0]          | 7           |

**Final `maxLength`**: `6` (subarray `[1,1,1,0,0,1,1,1]` after flipping two `0`'s).

---

## 🔹 Complexity Analysis

| Complexity      | Value       |
|-----------------|-------------|
| Time Complexity | O(n)        |
| Space Complexity| O(1)        |

---

# 🔍 Edge Cases

| Edge Case                     | Expected Output | Explanation                                  |
|-------------------------------|-----------------|----------------------------------------------|
| `nums = []`, `k = 0`          | 0               | Empty array has no elements.                 |
| `nums = [1]`, `k = 0`         | 1               | Single `1` with no flips allowed.            |
| `nums = [0]`, `k = 1`         | 1               | Single `0` flipped to `1`.                   |
| `nums = [0,0,0]`, `k = 2`     | 2               | Only two `0`'s can be flipped.               |
| `nums = [1,1,1]`, `k = 0`     | 3               | All `1`'s, no flips needed.                  |
| `nums = [1,0,1,0,1]`, `k = 2` | 5               | All `0`'s can be flipped.                    |

---

# 📚 Key Takeaways

1. **Sliding Window Pattern**: Ideal for subarray problems with constraints on element counts.
2. **Dynamic Window Adjustment**: Move the `left` pointer when the constraint is violated.
3. **Efficiency**: The optimal solution reduces time complexity from **O(n²)** to **O(n)**.
4. **Interview Insight**: Always consider edge cases (empty array, single element, all `1`'s or `0`'s).

---

# 🚀 Interview Tips

- **Follow-up Questions**:
  - What if the array contains values other than `0` and `1`?
  - How would you modify the solution if `k` is very large (e.g., `k = n`)?
- **Common Pitfalls**:
  - Forgetting to move the `left` pointer when `zeroCount > k`.
  - Not handling edge cases (e.g., `k = 0` or empty array).
- **Alternative Approaches**:
  - Prefix sum + binary search (less efficient for this problem).
  - Two-pointer technique (similar to sliding window but less intuitive).

---

# ✅ Conclusion

The **sliding window** approach is the most efficient solution for this problem, achieving **O(n)** time complexity with **O(1)** space. The key insight is to dynamically adjust the window size to maintain the constraint (`zeroCount ≤ k`), ensuring optimal performance even for large inputs.

**Final Answer**: Always prefer the sliding window technique for subarray problems with constraints on element counts. It provides a clean, efficient, and interview-ready solution.