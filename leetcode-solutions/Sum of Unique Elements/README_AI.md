# 📌 Sum of Unique Elements

---

# 📝 Problem Statement

Given an integer array `nums`, return the sum of all **unique** elements in the array.

A unique element is defined as an element that appears **exactly once** in the array.

### **Objective**
Calculate the sum of elements that occur only once in the input array.

### **Input**
- An integer array `nums` of size `n` (1 ≤ `n` ≤ 100)
- Each element in `nums` satisfies: -100 ≤ `nums[i]` ≤ 100

### **Output**
- An integer representing the sum of all unique elements.

### **Examples**
| Input | Output | Explanation |
|-------|--------|-------------|
| `[1,2,3,2]` | `4` | Unique elements: `1` and `3`. Sum = `1 + 3 = 4` |
| `[1,1,1,1,1]` | `0` | No unique elements. Sum = `0` |
| `[1,2,3,4,5]` | `15` | All elements are unique. Sum = `1 + 2 + 3 + 4 + 5 = 15` |

### **Constraints**
- The solution must efficiently handle the upper constraint (`n = 100`).
- Negative numbers are allowed.

---

# 💡 Intuition

The core idea is to **identify elements that appear exactly once** in the array and sum them. The challenge lies in efficiently determining uniqueness without excessive computation.

- **Brute Force Approach**: For each element, scan the entire array to count its occurrences. If the count is 1, include it in the sum. This is straightforward but inefficient.
- **Optimal Approach**: Use a **frequency map** (hash map) to count occurrences of each element in a single pass. Then, sum elements with a frequency of 1. This reduces time complexity significantly.

---

# 🐌 Brute Force Approach

## 🔹 Approach
For each element in the array, iterate through the entire array to count how many times it appears. If the count is exactly 1, add the element to the sum.

This approach is **intuitive** but **inefficient** due to nested loops.

---

## 🔹 Algorithm
1. Initialize `sum = 0`.
2. For each element `num` in `nums`:
   - Initialize `count = 0`.
   - For each element `x` in `nums`:
     - If `x == num`, increment `count`.
   - If `count == 1`, add `num` to `sum`.
3. Return `sum`.

---

## 🔹 Code

```java
class Solution {
    public int sumOfUnique(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            int count = 0;
            for (int x : nums) {
                if (x == num) {
                    count++;
                }
            }
            if (count == 1) {
                sum += num;
            }
        }
        return sum;
    }
}
```

---

## 🔹 Dry Run

**Input**: `nums = [1, 2, 3, 2]`

| Step | Current `num` | Inner Loop (`x`) | `count` | `sum` | Action |
|------|---------------|------------------|---------|-------|--------|
| 1    | 1             | 1                | 1       | 0     |        |
|      |               | 2                | 1       |       |        |
|      |               | 3                | 1       |       |        |
|      |               | 2                | 1       |       |        |
|      |               |                  | 1       | 1     | Add `1` to `sum` |
| 2    | 2             | 1                | 0       | 1     |        |
|      |               | 2                | 1       |       |        |
|      |               | 3                | 1       |       |        |
|      |               | 2                | 2       |       |        |
|      |               |                  | 2       | 1     | Skip (count ≠ 1) |
| 3    | 3             | 1                | 0       | 1     |        |
|      |               | 2                | 0       |       |        |
|      |               | 3                | 1       |       |        |
|      |               | 2                | 1       |       |        |
|      |               |                  | 1       | 4     | Add `3` to `sum` |
| 4    | 2             | 1                | 0       | 4     |        |
|      |               | 2                | 1       |       |        |
|      |               | 3                | 1       |       |        |
|      |               | 2                | 2       |       |        |
|      |               |                  | 2       | 4     | Skip (count ≠ 1) |

**Final `sum`**: `4`

---

## 🔹 Complexity Analysis

| Complexity       | Value       |
|------------------|-------------|
| Time Complexity  | O(n²)       |
| Space Complexity | O(1)        |

---

# ⚡ Optimal Approach

## 🔹 Approach
Use a **frequency map** (hash map) to count occurrences of each element in a single pass. Then, iterate through the map and sum elements with a frequency of 1.

This approach **eliminates nested loops**, reducing time complexity to **O(n)**.

---

## 🔹 Why This Works
- **Efficiency**: Counting frequencies in one pass is optimal.
- **Correctness**: The frequency map accurately tracks uniqueness.
- **Scalability**: Handles the upper constraint (`n = 100`) effortlessly.

---

## 🔹 Algorithm
1. Initialize a frequency map (`HashMap<Integer, Integer>`).
2. Traverse `nums` and populate the frequency map.
3. Initialize `sum = 0`.
4. Traverse the frequency map:
   - If the frequency of an element is `1`, add it to `sum`.
5. Return `sum`.

---

## 🔹 Code

```java
import java.util.HashMap;
import java.util.Map;

class Solution {
    public int sumOfUnique(int[] nums) {
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        for (int num : nums) {
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }

        int sum = 0;
        for (Map.Entry<Integer, Integer> entry : frequencyMap.entrySet()) {
            if (entry.getValue() == 1) {
                sum += entry.getKey();
            }
        }
        return sum;
    }
}
```

---

## 🔹 Detailed Dry Run

**Input**: `nums = [1, 2, 3, 2]`

### **Step 1: Build Frequency Map**
| `num` | Frequency Map State |
|-------|---------------------|
| 1     | `{1=1}`             |
| 2     | `{1=1, 2=1}`        |
| 3     | `{1=1, 2=1, 3=1}`   |
| 2     | `{1=1, 2=2, 3=1}`   |

### **Step 2: Sum Unique Elements**
| Key | Value | Action          | `sum` |
|-----|-------|-----------------|-------|
| 1   | 1     | Add `1` to `sum` | 1     |
| 2   | 2     | Skip            | 1     |
| 3   | 1     | Add `3` to `sum` | 4     |

**Final `sum`**: `4`

---

## 🔹 Complexity Analysis

| Complexity       | Value       |
|------------------|-------------|
| Time Complexity  | O(n)        |
| Space Complexity | O(n)        |

---

# 🔍 Edge Cases

| Edge Case               | Input               | Output | Explanation |
|-------------------------|---------------------|--------|-------------|
| Empty Array             | `[]`                | `0`    | No elements to sum. |
| Single Element          | `[5]`               | `5`    | Only one unique element. |
| All Duplicates          | `[1,1,1]`           | `0`    | No unique elements. |
| All Unique              | `[1,2,3]`           | `6`    | All elements are unique. |
| Negative Numbers        | `[-1,2,-1,3]`       | `5`    | Unique: `2` and `3`. |
| Large Input (n=100)     | `[1,1,...,1]` (100) | `0`    | All duplicates. |
| Mixed Positive/Negative | `[-2,-1,0,1,2]`     | `0`    | All elements appear once (if no duplicates). |

---

# 📚 Key Takeaways

1. **Frequency Maps**: A powerful tool for counting occurrences in linear time.
2. **Avoid Nested Loops**: Optimal solutions often replace nested loops with hash maps.
3. **Trade-offs**: The optimal solution uses extra space for significant time savings.
4. **Edge Cases Matter**: Always test for empty inputs, duplicates, and negative numbers.

---

# 🚀 Interview Tips

- **Follow-up Questions**:
  - *What if the input is a stream of numbers?* (Use a sliding window or online algorithm.)
  - *Can you solve it in O(1) space?* (Not possible without sorting, which is O(n log n).)
- **Common Pitfalls**:
  - Forgetting to handle negative numbers.
  - Misinterpreting "unique" as "distinct" (sum all distinct elements).
- **Alternative Approaches**:
  - **Sorting**: Sort the array and check adjacent elements (O(n log n) time, O(1) space).
  - **Set + Sum**: Sum all elements and subtract duplicates (requires tracking duplicates).

---

# ✅ Conclusion

The **optimal solution** using a frequency map is the **preferred approach** due to its **linear time complexity** and **scalability**. It efficiently handles the problem constraints while maintaining clarity and correctness.

**Key Insight**: Leveraging hash maps to count frequencies is a **fundamental DSA pattern** applicable to many problems involving uniqueness or duplicates. Mastering this technique is essential for technical interviews.