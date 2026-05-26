# 📌 Contains Duplicate

---

# 📝 Problem Statement

Given an integer array `nums`, return `true` if any value appears **at least twice** in the array, and return `false` if every element is distinct.

### **Objective**
Determine whether the input array contains duplicate elements.

### **Input**
- An integer array `nums` (1 ≤ `nums.length` ≤ 10⁵)
- Each element in `nums` is an integer (-10⁹ ≤ `nums[i]` ≤ 10⁹)

### **Output**
- `true` if duplicates exist, `false` otherwise.

### **Constraints**
- The solution must be efficient for large input sizes (up to 10⁵ elements).
- The array may contain negative numbers and zeros.

---

# 💡 Intuition

The problem requires checking for duplicate values in an array. The key insight is that **duplicate detection is fundamentally a membership-checking problem**—we need to verify if an element has been seen before.

The naive approach would involve comparing every element with every other element, but this is inefficient for large arrays. A more optimized approach leverages **hash-based data structures** (like `HashSet`) to track seen elements in constant time, reducing the overall time complexity from O(n²) to O(n).

---

# 🐌 Brute Force Approach

## 🔹 Approach
The brute force solution checks every element against every subsequent element in the array. If any pair of elements is equal, we return `true`. If no duplicates are found after all comparisons, we return `false`.

This approach is straightforward but inefficient due to its quadratic time complexity.

---

## 🔹 Algorithm
1. Iterate through each element in the array using index `i`.
2. For each `i`, iterate through all subsequent elements using index `j` (where `j > i`).
3. If `nums[i] == nums[j]`, return `true` immediately.
4. If no duplicates are found after all iterations, return `false`.

---

## 🔹 Code

```java
class Solution {
    public boolean containsDuplicate(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] == nums[j]) {
                    return true;
                }
            }
        }
        return false;
    }
}
```

---

## 🔹 Dry Run

**Input:** `nums = [1, 2, 3, 1]`

| Step | i | j | nums[i] | nums[j] | Comparison | Result |
|------|---|---|---------|---------|------------|--------|
| 1    | 0 | 1 | 1       | 2       | 1 == 2?    | false  |
| 2    | 0 | 2 | 1       | 3       | 1 == 3?    | false  |
| 3    | 0 | 3 | 1       | 1       | 1 == 1?    | **true** → return `true` |

**Output:** `true`

---

**Input:** `nums = [1, 2, 3, 4]`

| Step | i | j | nums[i] | nums[j] | Comparison | Result |
|------|---|---|---------|---------|------------|--------|
| 1    | 0 | 1 | 1       | 2       | 1 == 2?    | false  |
| 2    | 0 | 2 | 1       | 3       | 1 == 3?    | false  |
| 3    | 0 | 3 | 1       | 4       | 1 == 4?    | false  |
| 4    | 1 | 2 | 2       | 3       | 2 == 3?    | false  |
| 5    | 1 | 3 | 2       | 4       | 2 == 4?    | false  |
| 6    | 2 | 3 | 3       | 4       | 3 == 4?    | false  |

**Output:** `false`

---

## 🔹 Complexity Analysis

| Complexity       | Value       |
|------------------|-------------|
| Time Complexity  | O(n²)       |
| Space Complexity | O(1)        |

---

# ⚡ Optimal Approach

## 🔹 Approach
The optimal solution uses a **`HashSet`** to track seen elements. As we iterate through the array, we check if the current element exists in the set. If it does, we return `true`. Otherwise, we add the element to the set. If the loop completes without finding duplicates, we return `false`.

This approach reduces the time complexity to **O(n)** by leveraging the average O(1) time complexity of `HashSet` operations.

---

## 🔹 Why This Works
- **HashSet** provides O(1) average-time complexity for `contains` and `add` operations.
- By checking for membership before insertion, we ensure that duplicates are detected in a single pass.
- The space complexity is O(n) in the worst case (all elements are unique), but this is a necessary trade-off for efficiency.

---

## 🔹 Algorithm
1. Initialize an empty `HashSet`.
2. Iterate through each element in `nums`.
3. If the current element exists in the set, return `true`.
4. Otherwise, add the element to the set.
5. If the loop completes without finding duplicates, return `false`.

---

## 🔹 Code

```java
import java.util.HashSet;
import java.util.Set;

class Solution {
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> seen = new HashSet<>();
        for (int num : nums) {
            if (seen.contains(num)) {
                return true;
            }
            seen.add(num);
        }
        return false;
    }
}
```

---

## 🔹 Detailed Dry Run

**Input:** `nums = [1, 2, 3, 1]`

| Iteration | Current Element | Set State       | Action                     | Result       |
|-----------|-----------------|-----------------|----------------------------|--------------|
| 1         | 1               | `{}`            | Add 1 to set               | `{1}`        |
| 2         | 2               | `{1}`           | Add 2 to set               | `{1, 2}`     |
| 3         | 3               | `{1, 2}`        | Add 3 to set               | `{1, 2, 3}`  |
| 4         | 1               | `{1, 2, 3}`     | 1 exists in set → return `true` | **true** |

**Output:** `true`

---

**Input:** `nums = [1, 2, 3, 4]`

| Iteration | Current Element | Set State       | Action                     | Result       |
|-----------|-----------------|-----------------|----------------------------|--------------|
| 1         | 1               | `{}`            | Add 1 to set               | `{1}`        |
| 2         | 2               | `{1}`           | Add 2 to set               | `{1, 2}`     |
| 3         | 3               | `{1, 2}`        | Add 3 to set               | `{1, 2, 3}`  |
| 4         | 4               | `{1, 2, 3}`     | Add 4 to set               | `{1, 2, 3, 4}` | Loop ends → return `false` |

**Output:** `false`

---

## 🔹 Complexity Analysis

| Complexity       | Value       |
|------------------|-------------|
| Time Complexity  | O(n)        |
| Space Complexity | O(n)        |

---

# 🔍 Edge Cases

| Edge Case                     | Expected Output | Explanation                                  |
|-------------------------------|-----------------|----------------------------------------------|
| Empty array `[]`              | `false`         | No elements → no duplicates.                 |
| Single element `[1]`          | `false`         | Only one element → no duplicates.            |
| All duplicates `[1, 1, 1]`    | `true`          | All elements are the same.                   |
| Negative numbers `[-1, 0, -1]`| `true`          | Duplicates exist regardless of sign.         |
| Large input (10⁵ elements)    | Depends on data | Must handle efficiently (optimal approach).  |
| Sorted input `[1, 2, 2, 3]`   | `true`          | Duplicates may be adjacent.                  |
| Reverse sorted `[3, 2, 2, 1]` | `true`          | Duplicates exist regardless of order.        |

---

# 📚 Key Takeaways

1. **Brute Force vs. HashSet Trade-off**:
   - Brute force is simple but inefficient (O(n²) time).
   - HashSet provides O(n) time at the cost of O(n) space.

2. **Membership Checking**:
   - `HashSet` is ideal for tracking seen elements in O(1) time.

3. **Early Termination**:
   - Both approaches can return early upon finding the first duplicate.

4. **Interview Insight**:
   - Always consider time/space trade-offs when optimizing.

---

# 🚀 Interview Tips

1. **Follow-up Questions**:
   - *"What if the array is sorted?"* → Use two-pointer technique (O(n) time, O(1) space).
   - *"Can you solve it in O(1) space?"* → Only possible if input is sorted (two-pointer approach).

2. **Common Pitfalls**:
   - Forgetting to handle empty arrays or single-element arrays.
   - Using brute force for large inputs (will time out).

3. **Alternative Approaches**:
   - **Sorting**: Sort the array and check adjacent elements (O(n log n) time, O(1) space).
   - **Two-Pointer (if sorted)**: Compare `nums[i]` and `nums[i+1]` (O(n) time, O(1) space).

4. **Optimization Discussion**:
   - HashSet is the most efficient for unsorted arrays.
   - Sorting is better if space is constrained.

---

# ✅ Conclusion

The **optimal solution** using a `HashSet` is the preferred approach for this problem due to its **O(n) time complexity** and **simplicity**. While it uses O(n) space, this trade-off is justified for large input sizes. The brute force approach serves as a good starting point for understanding the problem but is impractical for real-world constraints.

**Key Insight**: *Efficient duplicate detection relies on fast membership checking, which `HashSet` provides.* This pattern is widely applicable in problems involving uniqueness or frequency counting.