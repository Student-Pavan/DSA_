```markdown
# 📌 N-Repeated Element in Size 2N Array

---

# 📝 Problem Statement

You are given an integer array `nums` with the following properties:
- The array has a length of `2N`.
- Exactly `N + 1` unique elements exist in the array.
- Exactly one element is repeated `N` times.

**Objective:**
Return the element that is repeated `N` times.

**Input:**
- An integer array `nums` of length `2N`.

**Output:**
- The integer that appears `N` times in the array.

**Constraints:**
- `2 <= nums.length <= 50,000`
- `0 <= nums[i] <= 10^4`
- `nums.length` is even.

---

# 💡 Intuition

The problem guarantees that exactly one element is repeated `N` times in an array of size `2N`. This means the repeated element occupies half of the array. The key insight is that the repeated element must appear at least twice within any four consecutive elements due to the pigeonhole principle. This allows us to check small windows (e.g., 4 elements) to find the duplicate efficiently without scanning the entire array.

---

# 🐌 Brute Force Approach

## 🔹 Approach

The brute force approach involves counting the frequency of each element in the array using a hash map. Once we identify an element that appears `N` times, we return it. This approach is straightforward but requires extra space to store frequencies.

---

## 🔹 Algorithm

1. Initialize a hash map to store the frequency of each element.
2. Iterate through the array and update the frequency count for each element.
3. For each element, check if its frequency equals `N`.
4. If found, return the element immediately.
5. If no element is found (though the problem guarantees one), return `-1` as a fallback.

---

## 🔹 Code

```java
import java.util.HashMap;
import java.util.Map;

class Solution {
    public int repeatedNTimes(int[] nums) {
        Map<Integer, Integer> frequencyMap = new HashMap<>();

        for (int num : nums) {
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
            if (frequencyMap.get(num) == nums.length / 2) {
                return num;
            }
        }

        return -1; // Fallback (problem guarantees a solution)
    }
}
```

---

## 🔹 Dry Run

**Input:** `nums = [1, 2, 3, 3]`
**N:** `2` (since `nums.length = 4`)

| Step | Current Element | Frequency Map State | Action |
|------|-----------------|---------------------|--------|
| 1    | 1               | `{1: 1}`            | Update frequency of 1 |
| 2    | 2               | `{1: 1, 2: 1}`      | Update frequency of 2 |
| 3    | 3               | `{1: 1, 2: 1, 3: 1}`| Update frequency of 3 |
| 4    | 3               | `{1: 1, 2: 1, 3: 2}`| Frequency of 3 equals `N` (2), return 3 |

---

## 🔹 Complexity Analysis

| Complexity       | Value       |
|------------------|-------------|
| Time Complexity  | O(N)        |
| Space Complexity | O(N)        |

---

# ⚡ Optimal Approach

## 🔹 Approach

The optimal approach leverages the pigeonhole principle. Since the repeated element appears `N` times in an array of size `2N`, it must appear at least twice in any four consecutive elements. Thus, we only need to check the first four elements to find the duplicate. If not found, we check the next four, and so on. This reduces the problem to a constant-time check in the worst case.

---

## 🔹 Why This Works

- The array has `2N` elements with `N + 1` unique elements, meaning one element is repeated `N` times.
- In any four consecutive elements, at least two must be the repeated element (pigeonhole principle).
- Thus, the repeated element will always be found within the first four elements or in overlapping windows.

---

## 🔹 Algorithm

1. Iterate through the array in steps of 2 (to cover overlapping windows).
2. Check the current element and the next three elements for duplicates.
3. If a duplicate is found, return it immediately.
4. If no duplicate is found in the first four elements, continue checking the next window.

---

## 🔹 Code

```java
class Solution {
    public int repeatedNTimes(int[] nums) {
        for (int i = 0; i < nums.length - 3; i++) {
            if (nums[i] == nums[i + 1] || nums[i] == nums[i + 2] || nums[i] == nums[i + 3]) {
                return nums[i];
            }
            if (nums[i + 1] == nums[i + 2] || nums[i + 1] == nums[i + 3]) {
                return nums[i + 1];
            }
            if (nums[i + 2] == nums[i + 3]) {
                return nums[i + 2];
            }
        }
        return nums[nums.length - 1]; // Fallback (problem guarantees a solution)
    }
}
```

---

## 🔹 Detailed Dry Run

**Input:** `nums = [1, 2, 3, 3]`

| Step | Window Checked | Comparison | Result |
|------|----------------|------------|--------|
| 1    | [1, 2, 3, 3]   | 1 == 2? No | Continue |
|      |                | 1 == 3? No | Continue |
|      |                | 1 == 3? No | Continue |
|      |                | 2 == 3? No | Continue |
|      |                | 2 == 3? No | Continue |
|      |                | 3 == 3? Yes | Return 3 |

---

## 🔹 Complexity Analysis

| Complexity       | Value       |
|------------------|-------------|
| Time Complexity  | O(1)        |
| Space Complexity | O(1)        |

---

# 🔍 Edge Cases

- **Single Window:** `[1, 1, 2, 3]` → Duplicate found in first window.
- **Overlapping Windows:** `[1, 2, 1, 3]` → Duplicate found in overlapping check.
- **Large Array:** `[1, 2, ..., N, 1]` → Duplicate found in first or second window.
- **Minimum Size:** `[1, 1]` → Duplicate found immediately.
- **Negative Values:** `[-1, -2, -1, -3]` → Duplicate found in first window.

---

# 📚 Key Takeaways

1. **Pigeonhole Principle:** In an array of size `2N` with `N + 1` unique elements, the repeated element must appear in any four consecutive elements.
2. **Early Termination:** The optimal approach allows early termination, reducing unnecessary checks.
3. **Space Efficiency:** The optimal solution avoids extra space, making it more efficient for large inputs.
4. **Pattern Recognition:** Identifying mathematical guarantees (like the pigeonhole principle) is crucial for optimization.

---

# 🚀 Interview Tips

- **Follow-Up:** What if the array size is `3N` with `N` duplicates? How would you modify the approach?
- **Pitfall:** Avoid assuming the duplicate is always in the first four elements without checking overlapping windows.
- **Alternative:** Discuss the trade-offs between brute force (space) and optimal (time) approaches.
- **Optimization:** Highlight how mathematical insights can lead to constant-time solutions.

---

# ✅ Conclusion

The optimal approach efficiently solves the problem by leveraging the pigeonhole principle, ensuring constant-time performance with minimal space usage. This demonstrates the importance of recognizing mathematical patterns in algorithm design, a key skill for technical interviews at top-tier companies.
```