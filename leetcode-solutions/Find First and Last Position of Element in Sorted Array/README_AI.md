# 📌 Find First and Last Position of Element in Sorted Array

---

# 📝 Problem Statement

Given an array of integers `nums` sorted in non-decreasing order, find the starting and ending position of a given target value.

If the target is not found in the array, return `[-1, -1]`.

**Constraints:**
- `0 <= nums.length <= 10^5`
- `-10^9 <= nums[i] <= 10^9`
- `nums` is a non-decreasing array
- `-10^9 <= target <= 10^9`

**Example 1:**
```
Input: nums = [5,7,7,8,8,10], target = 8
Output: [3,4]
```

**Example 2:**
```
Input: nums = [5,7,7,8,8,10], target = 6
Output: [-1,-1]
```

**Example 3:**
```
Input: nums = [], target = 0
Output: [-1,-1]
```

---

# 💡 Intuition

The problem requires finding the **first** and **last** occurrence of a target value in a **sorted** array. Since the array is sorted, we can leverage **binary search** to achieve efficient O(log n) time complexity.

The key insight is that standard binary search finds **any** occurrence of the target, but we need to find the **boundaries** of the target's range. We can modify binary search to:
- Search for the **first occurrence** by continuing to search the left half even after finding the target
- Search for the **last occurrence** by continuing to search the right half even after finding the target

This approach efficiently narrows down the search space while maintaining logarithmic time complexity.

---

# 🐌 Brute Force Approach

## 🔹 Approach

The brute force approach involves scanning the entire array from left to right to find the first occurrence of the target, then continuing to scan to find the last occurrence.

- Traverse the array until the target is found (first occurrence)
- Continue traversing until the target is no longer found (last occurrence)
- Return the indices if found, otherwise `[-1, -1]`

This approach is simple but inefficient for large arrays due to O(n) time complexity.

---

## 🔹 Algorithm

1. Initialize `first = -1` and `last = -1`
2. Traverse the array from index `0` to `n-1`:
   - If `nums[i] == target` and `first == -1`, set `first = i`
   - If `nums[i] == target`, update `last = i`
3. Return `[first, last]`

---

## 🔹 Code

```java
class Solution {
    public int[] searchRange(int[] nums, int target) {
        int first = -1;
        int last = -1;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                if (first == -1) {
                    first = i;
                }
                last = i;
            }
        }

        return new int[]{first, last};
    }
}
```

---

## 🔹 Dry Run

**Input:** `nums = [5,7,7,8,8,10]`, `target = 8`

| Step | Index | Value | Action | First | Last |
|------|-------|-------|--------|-------|------|
| 1    | 0     | 5     | Skip   | -1    | -1   |
| 2    | 1     | 7     | Skip   | -1    | -1   |
| 3    | 2     | 7     | Skip   | -1    | -1   |
| 4    | 3     | 8     | Found  | 3     | 3    |
| 5    | 4     | 8     | Found  | 3     | 4    |
| 6    | 5     | 10    | Skip   | 3     | 4    |

**Output:** `[3, 4]`

---

## 🔹 Complexity Analysis

| Complexity       | Value  |
|------------------|--------|
| Time Complexity  | O(n)   |
| Space Complexity | O(1)   |

---

# ⚡ Optimal Approach

## 🔹 Approach

We use **two modified binary searches**:
1. **First Occurrence Search**: When `nums[mid] == target`, continue searching the left half to find the earliest occurrence.
2. **Last Occurrence Search**: When `nums[mid] == target`, continue searching the right half to find the latest occurrence.

This approach ensures O(log n) time complexity, which is optimal for large input sizes.

---

## 🔹 Why This Works

- The array is **sorted**, so binary search is applicable.
- By adjusting the search range based on whether we've found the target, we can efficiently locate the boundaries.
- The first occurrence search ensures we don't miss earlier instances of the target.
- The last occurrence search ensures we don't miss later instances of the target.

---

## 🔹 Algorithm

1. **Find First Occurrence**:
   - Initialize `left = 0`, `right = nums.length - 1`
   - While `left <= right`:
     - Calculate `mid`
     - If `nums[mid] == target`, update `first = mid` and search left half (`right = mid - 1`)
     - If `nums[mid] < target`, search right half (`left = mid + 1`)
     - Else, search left half (`right = mid - 1`)

2. **Find Last Occurrence**:
   - Initialize `left = 0`, `right = nums.length - 1`
   - While `left <= right`:
     - Calculate `mid`
     - If `nums[mid] == target`, update `last = mid` and search right half (`left = mid + 1`)
     - If `nums[mid] < target`, search right half (`left = mid + 1`)
     - Else, search left half (`right = mid - 1`)

3. Return `[first, last]`

---

## 🔹 Code

```java
class Solution {
    public int[] searchRange(int[] nums, int target) {
        int first = findFirst(nums, target);
        int last = findLast(nums, target);
        return new int[]{first, last};
    }

    private int findFirst(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        int first = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                first = mid;
                right = mid - 1; // Continue searching left half
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return first;
    }

    private int findLast(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        int last = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                last = mid;
                left = mid + 1; // Continue searching right half
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return last;
    }
}
```

---

## 🔹 Detailed Dry Run

**Input:** `nums = [5,7,7,8,8,10]`, `target = 8`

### First Occurrence Search

| Step | Left | Right | Mid | nums[mid] | Action               | First |
|------|------|-------|-----|-----------|----------------------|-------|
| 1    | 0    | 5     | 2   | 7         | Search right (left=3)| -1    |
| 2    | 3    | 5     | 4   | 8         | Update first, search left (right=3) | 4 |
| 3    | 3    | 3     | 3   | 8         | Update first, search left (right=2) | 3 |

**First Occurrence:** `3`

### Last Occurrence Search

| Step | Left | Right | Mid | nums[mid] | Action               | Last  |
|------|------|-------|-----|-----------|----------------------|-------|
| 1    | 0    | 5     | 2   | 7         | Search right (left=3)| -1    |
| 2    | 3    | 5     | 4   | 8         | Update last, search right (left=5) | 4 |
| 3    | 5    | 5     | 5   | 10        | Search left (right=4)| 4     |

**Last Occurrence:** `4`

**Output:** `[3, 4]`

---

## 🔹 Complexity Analysis

| Complexity       | Value  |
|------------------|--------|
| Time Complexity  | O(log n) |
| Space Complexity | O(1)   |

---

# 🔍 Edge Cases

| Edge Case                     | Expected Output       |
|-------------------------------|-----------------------|
| Empty array (`nums = []`)     | `[-1, -1]`            |
| Single element (`nums = [5]`) | `[0, 0]` (if target=5)|
| All elements same (`nums = [2,2,2]`) | `[0, 2]` (if target=2) |
| Target not present (`nums = [1,3,5]`, target=2) | `[-1, -1]` |
| Target at start (`nums = [1,2,2,3]`, target=1) | `[0, 0]` |
| Target at end (`nums = [1,2,2,3]`, target=3) | `[3, 3]` |
| Large input size (`nums.length = 10^5`) | Efficient O(log n) solution required |

---

# 📚 Key Takeaways

- **Binary search can be modified** to find boundaries in sorted arrays.
- **Two separate searches** are needed for first and last occurrences.
- **Early termination** in brute force is not possible, making it inefficient for large inputs.
- **Optimal solution** leverages the sorted property to achieve O(log n) time.
- **Edge cases** must be handled carefully (empty array, single element, all duplicates).

---

# 🚀 Interview Tips

- **Follow-up:** How would you handle this if the array was **not sorted**?
  - *Answer:* Brute force O(n) is the only option, or sort first (O(n log n)) and then use binary search.

- **Common Pitfall:** Forgetting to reset the search range after finding the target in boundary searches.
- **Alternative Approach:** Use `Arrays.binarySearch` with custom logic, but it's less intuitive.
- **Optimization Insight:** The optimal solution **avoids scanning the entire array** by leveraging binary search.

---

# ✅ Conclusion

The **optimal solution** using **two modified binary searches** is the preferred approach due to its **O(log n) time complexity**, which is crucial for large input sizes. The brute force approach, while simple, is inefficient for large arrays and should only be considered for small inputs or as a fallback.

**Key Insight:** Binary search can be adapted to find **boundaries** in sorted arrays by continuing the search in the appropriate half after finding the target. This problem is a great example of how **algorithmic patterns** can be applied to solve real-world interview questions efficiently.