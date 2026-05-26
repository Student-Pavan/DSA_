# 📌 Find the Duplicate Number

---

# 📝 Problem Statement

Given an array of integers `nums` containing `n + 1` integers where each integer is in the range `[1, n]` inclusive, there is exactly **one duplicate number**. Your task is to find this duplicate number **without modifying the array** and using only **constant extra space**.

### Constraints:
- `1 <= n <= 10^5`
- `nums.length == n + 1`
- `1 <= nums[i] <= n`
- There is **only one duplicate number**, but it may appear **more than twice**.

### Example:
**Input:** `nums = [1,3,4,2,2]`
**Output:** `2`

**Input:** `nums = [3,1,3,4,2]`
**Output:** `3`

---

# 💡 Intuition

The problem resembles the **"Floyd’s Tortoise and Hare"** algorithm used in cycle detection in linked lists. Since the array values are constrained to `[1, n]` and the array size is `n + 1`, we can treat the array as a **linked list** where each value points to an index. The presence of a duplicate creates a **cycle** in this list. The duplicate number is the **entry point** of the cycle.

This insight allows us to solve the problem in **O(n) time** and **O(1) space** without modifying the input array.

---

# 🐌 Brute Force Approach

## 🔹 Approach

A straightforward approach is to use a **hash set** to track seen numbers. As we iterate through the array, we check if the current number has been seen before. If it has, we return it as the duplicate.

### Limitations:
- Uses **O(n) extra space**, violating the constant space constraint.
- Simple but inefficient for large inputs.

---

## 🔹 Algorithm

1. Initialize an empty set to track seen numbers.
2. Iterate through each number in the array.
3. If the number is already in the set, return it as the duplicate.
4. Otherwise, add the number to the set.
5. If no duplicate is found (shouldn’t happen per problem constraints), return `-1`.

---

## 🔹 Code

```java
import java.util.HashSet;
import java.util.Set;

class Solution {
    public int findDuplicate(int[] nums) {
        Set<Integer> seen = new HashSet<>();
        for (int num : nums) {
            if (seen.contains(num)) {
                return num;
            }
            seen.add(num);
        }
        return -1; // Should not reach here per problem constraints
    }
}
```

---

## 🔹 Dry Run

**Input:** `nums = [1, 3, 4, 2, 2]`

| Iteration | Current Number | Seen Set State | Action |
|-----------|----------------|----------------|--------|
| 1         | 1              | `{1}`          | Add 1  |
| 2         | 3              | `{1, 3}`       | Add 3  |
| 3         | 4              | `{1, 3, 4}`    | Add 4  |
| 4         | 2              | `{1, 3, 4, 2}` | Add 2  |
| 5         | 2              | `{1, 3, 4, 2}` | **Return 2** (duplicate found) |

---

## 🔹 Complexity Analysis

| Complexity       | Value       |
|------------------|-------------|
| Time Complexity  | O(n)        |
| Space Complexity | O(n)        |

---

# ⚡ Optimal Approach

## 🔹 Approach

We use **Floyd’s Tortoise and Hare (Cycle Detection)** algorithm. The key insight is to treat the array as a linked list where each value points to the next index. The duplicate number creates a cycle, and the duplicate is the **entry point** of this cycle.

### Steps:
1. **Detect the cycle** using two pointers (`slow` and `fast`).
2. **Find the entry point** of the cycle (the duplicate number) by resetting one pointer to the start and moving both at the same speed.

---

## 🔹 Why This Works

- The array can be viewed as a linked list: `index -> nums[index]`.
- A duplicate value means two indices point to the same location, creating a cycle.
- The duplicate number is the **first node** where the cycle begins.
- Floyd’s algorithm efficiently finds the cycle entry point in **O(n) time** and **O(1) space**.

---

## 🔹 Algorithm

1. Initialize `slow = nums[0]` and `fast = nums[0]`.
2. Move `slow` by 1 step and `fast` by 2 steps until they meet (`slow == fast`).
3. Reset `slow` to `nums[0]` and move both `slow` and `fast` by 1 step until they meet again.
4. The meeting point is the duplicate number.

---

## 🔹 Code

```java
class Solution {
    public int findDuplicate(int[] nums) {
        // Phase 1: Detect cycle
        int slow = nums[0];
        int fast = nums[0];

        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (slow != fast);

        // Phase 2: Find entry point (duplicate)
        slow = nums[0];
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }

        return slow;
    }
}
```

---

## 🔹 Detailed Dry Run

**Input:** `nums = [1, 3, 4, 2, 2]`

### Phase 1: Cycle Detection
- `slow = 1`, `fast = 1`
- **Step 1:** `slow = nums[1] = 3`, `fast = nums[nums[1]] = nums[3] = 2`
- **Step 2:** `slow = nums[3] = 2`, `fast = nums[nums[2]] = nums[4] = 2`
- **Step 3:** `slow = nums[2] = 4`, `fast = nums[nums[2]] = nums[4] = 2`
- **Step 4:** `slow = nums[4] = 2`, `fast = nums[nums[2]] = nums[4] = 2`
- **Cycle detected:** `slow == fast == 2`

### Phase 2: Find Entry Point
- Reset `slow = nums[0] = 1`, `fast = 2`
- **Step 1:** `slow = nums[1] = 3`, `fast = nums[2] = 4`
- **Step 2:** `slow = nums[3] = 2`, `fast = nums[4] = 2`
- **Meeting point:** `slow == fast == 2` → **Duplicate found!**

---

## 🔹 Complexity Analysis

| Complexity       | Value       |
|------------------|-------------|
| Time Complexity  | O(n)        |
| Space Complexity | O(1)        |

---

# 🔍 Edge Cases

| Edge Case                     | Expected Output | Explanation |
|-------------------------------|-----------------|-------------|
| `nums = [1, 1]`               | `1`             | Smallest possible input with duplicate. |
| `nums = [2, 2, 2, 2, 2]`      | `2`             | All elements are the same. |
| `nums = [1, 2, 3, 4, 4]`      | `4`             | Duplicate at the end. |
| `nums = [3, 1, 3, 4, 2]`      | `3`             | Duplicate at the start. |
| `nums = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10]` | `10` | Large input with duplicate at the end. |

---

# 📚 Key Takeaways

- **Cycle Detection** is a powerful pattern for problems involving duplicates or linked lists.
- **Floyd’s Algorithm** is optimal for finding cycles in **O(n) time** and **O(1) space**.
- The problem constraints (`1 <= nums[i] <= n`, `nums.length == n + 1`) enable the linked list analogy.
- Always consider **space constraints** in interview problems.

---

# 🚀 Interview Tips

- **Follow-up:** What if the array can be modified? (Answer: Sorting + linear scan, O(n log n) time, O(1) space.)
- **Pitfall:** Avoid using extra space unless explicitly allowed.
- **Alternative:** Binary search on values (not indices) can also solve this in O(n log n) time and O(1) space.
- **Optimization Insight:** The optimal solution leverages **mathematical properties** of cycles.

---

# ✅ Conclusion

The **optimal solution** using **Floyd’s Tortoise and Hare** is the most efficient approach, meeting all constraints with **O(n) time** and **O(1) space**. It transforms the problem into a **cycle detection** scenario, demonstrating the power of algorithmic patterns in solving complex problems elegantly.

**Key Insight:** The duplicate number is the **entry point of the cycle** in the array-as-linked-list representation. This approach is both **interview-friendly** and **production-ready**.