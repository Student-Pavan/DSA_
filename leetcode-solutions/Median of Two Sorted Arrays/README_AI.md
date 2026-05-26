# 📌 Median of Two Sorted Arrays

---

# 📝 Problem Statement

Given two sorted arrays `nums1` and `nums2` of size `m` and `n` respectively, return **the median** of the two sorted arrays.

The overall run time complexity should be **O(log (m+n))**.

### **Objective**
Find the median value of the combined sorted array formed by merging `nums1` and `nums2` without actually merging them (to achieve optimal time complexity).

### **Input**
- `nums1`: First sorted array of integers (ascending order)
- `nums2`: Second sorted array of integers (ascending order)

### **Output**
- A `double` representing the median of the combined sorted array

### **Constraints**
- `nums1.length == m`
- `nums2.length == n`
- `0 <= m <= 1000`
- `0 <= n <= 1000`
- `1 <= m + n <= 2000`
- `-10^6 <= nums1[i], nums2[i] <= 10^6`

> **Note**: The median is the middle value in an ordered integer list. If the size of the list is even, the median is the average of the two middle numbers.

---

# 💡 Intuition

The median divides a sorted array into two equal halves. For two sorted arrays, we need to find a partition such that:
- The left half contains elements from both arrays, and all are ≤ the right half.
- The number of elements in the left half equals (or is one more than) the right half.

Instead of merging both arrays (O(m+n) time), we can use binary search on the **smaller array** to find the correct partition in O(log(min(m,n))) time. This ensures we meet the O(log(m+n)) requirement.

The key insight is to **partition both arrays** such that the combined left partitions contain exactly half (or half+1) of the total elements, and all left elements ≤ all right elements.

---

# 🐌 Brute Force Approach

## 🔹 Approach

1. **Merge the two sorted arrays** into a single sorted array.
2. **Find the median** of the merged array:
   - If the total length is odd, return the middle element.
   - If even, return the average of the two middle elements.

This approach is straightforward but inefficient due to the O(m+n) time and space complexity from merging.

---

## 🔹 Algorithm

1. Initialize an empty list `merged`.
2. Use two pointers, `i` and `j`, starting at 0 for `nums1` and `nums2`.
3. Traverse both arrays:
   - If `nums1[i] <= nums2[j]`, append `nums1[i]` to `merged` and increment `i`.
   - Else, append `nums2[j]` to `merged` and increment `j`.
4. If one array is exhausted, append the remaining elements of the other.
5. Compute the median:
   - If `merged.length` is odd: return `merged[mid]`
   - Else: return `(merged[mid-1] + merged[mid]) / 2.0`

---

## 🔹 Code

```java
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int[] merged = new int[m + n];

        int i = 0, j = 0, k = 0;

        while (i < m && j < n) {
            if (nums1[i] <= nums2[j]) {
                merged[k++] = nums1[i++];
            } else {
                merged[k++] = nums2[j++];
            }
        }

        while (i < m) {
            merged[k++] = nums1[i++];
        }

        while (j < n) {
            merged[k++] = nums2[j++];
        }

        int total = m + n;
        if (total % 2 == 1) {
            return merged[total / 2];
        } else {
            return (merged[total / 2 - 1] + merged[total / 2]) / 2.0;
        }
    }
}
```

---

## 🔹 Dry Run

**Example**:
`nums1 = [1, 3]`, `nums2 = [2]`

| Step | i | j | k | merged Array | Action |
|------|---|---|---|--------------|--------|
| 1    | 0 | 0 | 0 | []           | Compare nums1[0]=1 and nums2[0]=2 → append 1 |
| 2    | 1 | 0 | 1 | [1]          | Compare nums1[1]=3 and nums2[0]=2 → append 2 |
| 3    | 1 | 1 | 2 | [1,2]        | j == n → append remaining nums1 |
| 4    | 2 | 1 | 3 | [1,2,3]      | i == m → done |

Total length = 3 (odd) → median = merged[1] = 2

---

## 🔹 Complexity Analysis

| Complexity       | Value       |
|------------------|-------------|
| Time Complexity  | O(m + n)    |
| Space Complexity | O(m + n)    |

---

# ⚡ Optimal Approach

## 🔹 Approach

Use **binary search on the smaller array** to find the correct partition such that:
- The left partitions of both arrays contain half (or half+1) of the total elements.
- All elements in the left partition ≤ all elements in the right partition.

We define:
- `partitionX`: number of elements taken from `nums1` into the left half
- `partitionY`: number of elements taken from `nums2` into the left half

We ensure:
- `partitionX + partitionY = (m + n + 1) / 2`
- `maxLeftX <= minRightY` and `maxLeftY <= minRightX`

If not, adjust the binary search range.

---

## 🔹 Why This Works

By partitioning both arrays such that the left half contains the correct number of elements and all left values ≤ right values, we ensure the median is either:
- The maximum of the left partitions (if total length is odd)
- The average of the max of left and min of right (if even)

Binary search on the smaller array ensures O(log(min(m,n))) time.

---

## 🔹 Algorithm

1. Ensure `nums1` is the smaller array.
2. Perform binary search on `nums1`:
   - `partitionX`: mid of current search range
   - `partitionY = (m + n + 1) / 2 - partitionX`
3. Define:
   - `maxLeftX = (partitionX == 0) ? Integer.MIN_VALUE : nums1[partitionX - 1]`
   - `minRightX = (partitionX == m) ? Integer.MAX_VALUE : nums1[partitionX]`
   - `maxLeftY = (partitionY == 0) ? Integer.MIN_VALUE : nums2[partitionY - 1]`
   - `minRightY = (partitionY == n) ? Integer.MAX_VALUE : nums2[partitionY]`
4. If `maxLeftX <= minRightY` and `maxLeftY <= minRightX`:
   - If total length is odd: return `max(maxLeftX, maxLeftY)`
   - Else: return `(max(maxLeftX, maxLeftY) + min(minRightX, minRightY)) / 2.0`
5. Else if `maxLeftX > minRightY`: move left (`right = partitionX - 1`)
6. Else: move right (`left = partitionX + 1`)

---

## 🔹 Code

```java
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }

        int m = nums1.length;
        int n = nums2.length;
        int left = 0, right = m;
        int total = m + n;

        while (left <= right) {
            int partitionX = (left + right) / 2;
            int partitionY = (total + 1) / 2 - partitionX;

            int maxLeftX = (partitionX == 0) ? Integer.MIN_VALUE : nums1[partitionX - 1];
            int minRightX = (partitionX == m) ? Integer.MAX_VALUE : nums1[partitionX];

            int maxLeftY = (partitionY == 0) ? Integer.MIN_VALUE : nums2[partitionY - 1];
            int minRightY = (partitionY == n) ? Integer.MAX_VALUE : nums2[partitionY];

            if (maxLeftX <= minRightY && maxLeftY <= minRightX) {
                if (total % 2 == 0) {
                    return (Math.max(maxLeftX, maxLeftY) + Math.min(minRightX, minRightY)) / 2.0;
                } else {
                    return Math.max(maxLeftX, maxLeftY);
                }
            } else if (maxLeftX > minRightY) {
                right = partitionX - 1;
            } else {
                left = partitionX + 1;
            }
        }

        throw new IllegalArgumentException("Input arrays are not sorted or invalid.");
    }
}
```

---

## 🔹 Detailed Dry Run

**Example**:
`nums1 = [1, 3]`, `nums2 = [2]`

- m = 2, n = 1 → total = 3 → (3+1)/2 = 2 → need 2 elements in left half
- Binary search on `nums1` (smaller array)

| Iteration | left | right | partitionX | partitionY | maxLeftX | minRightX | maxLeftY | minRightY | Condition | Action |
|---------|------|-------|------------|------------|----------|-----------|----------|-----------|-----------|--------|
| 1       | 0    | 2     | 1          | 1          | 1        | 3         | 2        | ∞         | 1 ≤ ∞ and 2 ≤ 3 → true | Found |

- total % 2 == 1 → return max(1, 2) = 2

---

## 🔹 Complexity Analysis

| Complexity       | Value               |
|------------------|---------------------|
| Time Complexity  | O(log(min(m, n)))   |
| Space Complexity | O(1)                |

---

# 🔍 Edge Cases

| Edge Case | Description |
|---------|-------------|
| One array empty | `nums1 = []`, `nums2 = [1,2,3]` → median is 2 |
| Both arrays single element | `nums1 = [1]`, `nums2 = [2]` → median = 1.5 |
| All elements same | `nums1 = [2,2]`, `nums2 = [2,2]` → median = 2 |
| Negative numbers | `nums1 = [-3,-1]`, `nums2 = [-2,0]` → median = -1.5 |
| Large arrays | Ensure binary search handles bounds correctly |
| Odd total length | `nums1 = [1,2]`, `nums2 = [3,4,5]` → median = 3 |
| Even total length | `nums1 = [1,3]`, `nums2 = [2]` → median = 2 |

---

# 📚 Key Takeaways

- **Median** divides a sorted array into two equal halves.
- **Brute force** is intuitive but inefficient for large inputs.
- **Binary search on partition** enables O(log(min(m,n))) time.
- **Partition logic** ensures left half ≤ right half without merging.
- Always **search on the smaller array** to minimize binary search steps.
- Handle **edge cases** like empty arrays and single elements carefully.

---

# 🚀 Interview Tips

- **Clarify constraints**: Are arrays non-empty? Can they have duplicates?
- **Discuss trade-offs**: Why avoid merging? Time vs space.
- **Follow-up**: Can you solve it in O(1) space? (Yes, with binary search)
- **Alternative**: Use recursion with binary search (less common).
- **Common pitfall**: Off-by-one errors in partition calculation.
- **Optimization insight**: Binary search reduces search space exponentially.

---

# ✅ Conclusion

The **optimal solution** using binary search on the smaller array is the gold standard for this problem. It achieves **O(log(min(m,n)))** time and **O(1)** space, meeting the problem's strict requirements.

The key insight is to **partition both arrays** such that the left half contains the correct number of elements and all left values are ≤ right values. This avoids the need to merge, enabling logarithmic time complexity.

Mastering this approach not only solves the problem efficiently but also demonstrates deep understanding of **binary search**, **partitioning**, and **divide-and-conquer** strategies — essential for FAANG-level interviews.