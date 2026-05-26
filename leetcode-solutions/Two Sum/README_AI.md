# 📌 Two Sum

---

# 📝 Problem Statement

Given an array of integers `nums` and an integer `target`, return **indices** of the two numbers such that they add up to `target`.

**Assumptions:**
- Each input has **exactly one** valid solution.
- You may not use the same element twice.
- The answer can be returned in any order.

**Constraints:**
- `2 <= nums.length <= 10⁴`
- `-10⁹ <= nums[i] <= 10⁹`
- `-10⁹ <= target <= 10⁹`

**Example:**
```
Input: nums = [2,7,11,15], target = 9
Output: [0,1]
Explanation: nums[0] + nums[1] = 2 + 7 = 9
```

---

# 💡 Intuition

The core challenge is to find two distinct elements in an array that sum to a given target. A naive approach would check every possible pair, but this is inefficient. The key insight is to **trade space for time** by using a hash map to remember previously seen values. This allows us to check for the complement (`target - current number`) in constant time, reducing the overall complexity from O(n²) to O(n).

---

# 🐌 Brute Force Approach

## 🔹 Approach

The brute force method involves checking every possible pair of elements in the array to see if their sum equals the target. For each element at index `i`, we iterate through all subsequent elements at index `j > i` and check if `nums[i] + nums[j] == target`.

---

## 🔹 Algorithm

1. Iterate through each element in the array using index `i`.
2. For each `i`, iterate through all elements after `i` using index `j`.
3. If `nums[i] + nums[j] == target`, return `[i, j]`.
4. If no pair is found after all iterations, return an empty array (though problem guarantees a solution).

---

## 🔹 Code

```java
class Solution {
    public int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[0]; // as per problem, this line is theoretically unreachable
    }
}
```

---

## 🔹 Dry Run

**Input:** `nums = [2, 7, 11, 15]`, `target = 9`

| Iteration (i) | Current Value (nums[i]) | j Loop Range | nums[j] | Sum Check | Result |
|---------------|-------------------------|--------------|---------|-----------|--------|
| 0             | 2                       | 1 to 3       | 7       | 2 + 7 = 9 ✅ | Return [0,1] |
| 0             | 2                       | 2            | 11      | 2 + 11 = 13 ❌ | — |
| 0             | 2                       | 3            | 15      | 2 + 15 = 17 ❌ | — |
| 1             | 7                       | 2 to 3       | 11      | 7 + 11 = 18 ❌ | — |
| 1             | 7                       | 3            | 15      | 7 + 15 = 22 ❌ | — |
| 2             | 11                      | 3            | 15      | 11 + 15 = 26 ❌ | — |

> ✅ **Algorithm terminates at i=0, j=1** and returns `[0, 1]`.

---

## 🔹 Complexity Analysis

| Complexity       | Value       |
|------------------|-------------|
| Time Complexity  | O(n²)       |
| Space Complexity | O(1)        |

---

# ⚡ Optimal Approach

## 🔹 Approach

We use a **hash map** to store each number's value and its index as we iterate through the array. For each number, we compute its complement (`target - current number`). If this complement exists in the map, we have found our pair. This approach ensures we only traverse the array once, making it highly efficient.

---

## 🔹 Why This Works

By storing each number in a hash map as we go, we can instantly check if the required complement (which would form the target sum) has already been seen. This eliminates the need for nested loops and reduces time complexity to linear time. The hash map provides O(1) average-time lookups, making the solution optimal.

---

## 🔹 Algorithm

1. Create an empty hash map to store value-to-index mappings.
2. Iterate through the array using index `i`.
3. For each element `nums[i]`, compute `complement = target - nums[i]`.
4. If `complement` exists in the map, return `[map.get(complement), i]`.
5. Otherwise, store `nums[i]` and its index `i` in the map.
6. If no pair is found (though problem guarantees one), return an empty array.

---

## 🔹 Code

```java
import java.util.HashMap;
import java.util.Map;

class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[]{map.get(complement), i};
            }
            map.put(nums[i], i);
        }
        return new int[0]; // as per problem, unreachable
    }
}
```

---

## 🔹 Detailed Dry Run

**Input:** `nums = [2, 7, 11, 15]`, `target = 9`

| Step | Current Index (i) | Current Value (nums[i]) | Complement (9 - nums[i]) | Map State (before update) | Action |
|------|-------------------|-------------------------|--------------------------|----------------------------|--------|
| 1    | 0                 | 2                       | 7                        | {}                         | 7 not in map → add {2:0} |
| 2    | 1                 | 7                       | 2                        | {2:0}                      | 2 found in map → return [0,1] |

> ✅ **Algorithm terminates at i=1** and returns `[0, 1]`.

---

## 🔹 Complexity Analysis

| Complexity       | Value       |
|------------------|-------------|
| Time Complexity  | O(n)        |
| Space Complexity | O(n)        |

---

# 🔍 Edge Cases

| Edge Case | Description | Expected Output |
|---------|-------------|-----------------|
| Smallest array | `nums = [3, 3]`, `target = 6` | `[0, 1]` |
| Negative numbers | `nums = [-1, -2, -3, -4]`, `target = -5` | `[1, 2]` |
| Large numbers | `nums = [1000000000, 999999999]`, `target = 1999999999` | `[0, 1]` |
| Duplicates | `nums = [3, 2, 4]`, `target = 6` | `[1, 2]` |
| Zero and negative | `nums = [0, 4, 3, 0]`, `target = 0` | `[0, 3]` |

---

# 📚 Key Takeaways

- **Brute force** is simple but inefficient for large inputs.
- **Hash maps** are powerful for O(1) lookups and are ideal for complement-based problems.
- **Trade-off**: Use extra space to reduce time complexity.
- **Pattern**: One-pass hash map is a common optimization in array problems involving sums or frequency counts.

---

# 🚀 Interview Tips

- **Follow-up**: What if the input is sorted? Can you solve it in O(1) space?
  > Yes! Use two pointers (left and right) and adjust based on sum comparison.
- **Common pitfall**: Forgetting to check `j > i` in brute force, leading to using the same element twice.
- **Optimization insight**: Always consider if a hash map can eliminate nested loops.
- **Alternative**: Sorting + two pointers (but loses original indices, so not suitable here).

---

# ✅ Conclusion

The **optimal solution** using a hash map is the preferred approach due to its **O(n) time complexity** and **elegant one-pass logic**. The key insight is recognizing that storing seen values allows constant-time complement checks, transforming a quadratic problem into a linear one. This pattern is widely applicable in problems involving sums, differences, or frequency counts, making it a must-know for technical interviews.