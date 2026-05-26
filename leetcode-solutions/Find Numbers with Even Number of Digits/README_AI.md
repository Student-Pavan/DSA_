# 📌 Find Numbers with Even Number of Digits

---

# 📝 Problem Statement

Given an array `nums` of integers, return how many of them contain an **even number of digits**.

### **Objective**
Count the numbers in the array that have an even number of digits (e.g., `12` has 2 digits → even, `123` has 3 digits → odd).

### **Input**
- `nums`: An array of integers (`int[]`), where `1 <= nums.length <= 500`.
- Each element in `nums` satisfies `1 <= nums[i] <= 10^5`.

### **Output**
- An integer representing the count of numbers with an even number of digits.

### **Constraints**
- The input array may contain duplicates.
- All numbers are positive integers.
- The solution must efficiently handle the upper constraint (`10^5`).

---

# 💡 Intuition

The core challenge is determining the number of digits in each number. A naive approach would convert each number to a string and check its length, but this introduces overhead. A more efficient method involves using **mathematical operations** (logarithms or division) to count digits without string conversion.

The key insight:
- **Logarithmic approach**: The number of digits in a positive integer `n` is `⌊log₁₀(n)⌋ + 1`.
- **Division approach**: Repeatedly divide the number by 10 until it becomes 0, counting the divisions.

The optimal solution avoids string conversion and leverages **logarithmic properties** for constant-time digit counting.

---

# 🐌 Brute Force Approach

## 🔹 Approach
Convert each number to a string and check if its length is even. This is straightforward but inefficient due to string conversion overhead.

### **Steps**
1. Iterate through each number in the array.
2. Convert the number to a string.
3. Check if the string's length is even.
4. Increment the count if true.

---

## 🔹 Algorithm
1. Initialize `count = 0`.
2. For each `num` in `nums`:
   - Convert `num` to a string.
   - If `string.length() % 2 == 0`, increment `count`.
3. Return `count`.

---

## 🔹 Code

```java
class Solution {
    public int findNumbers(int[] nums) {
        int count = 0;
        for (int num : nums) {
            String numStr = Integer.toString(num);
            if (numStr.length() % 2 == 0) {
                count++;
            }
        }
        return count;
    }
}
```

---

## 🔹 Dry Run

**Input:** `nums = [12, 345, 2, 6, 7896]`

| Iteration | Current Number | String Representation | Length | Even? | Count |
|-----------|----------------|-----------------------|--------|-------|-------|
| 1         | 12             | "12"                  | 2      | Yes   | 1     |
| 2         | 345            | "345"                 | 3      | No    | 1     |
| 3         | 2              | "2"                   | 1      | No    | 1     |
| 4         | 6              | "6"                   | 1      | No    | 1     |
| 5         | 7896           | "7896"                | 4      | Yes   | 2     |

**Output:** `2`

---

## 🔹 Complexity Analysis

| Complexity      | Value       |
|-----------------|-------------|
| Time Complexity | O(N * D)    |
| Space Complexity| O(D)        |

- **N**: Number of elements in `nums`.
- **D**: Average number of digits per number (due to string conversion).

---

# ⚡ Optimal Approach

## 🔹 Approach
Use **logarithmic properties** to count digits in constant time. The number of digits in `n` is `⌊log₁₀(n)⌋ + 1`. This avoids string conversion and reduces time complexity.

### **Why This Works**
- Logarithms provide a mathematical way to count digits without iteration.
- For `n = 123`, `log₁₀(123) ≈ 2.089` → `⌊2.089⌋ + 1 = 3` digits.
- This method is **O(1)** per number, making the overall solution **O(N)**.

---

## 🔹 Algorithm
1. Initialize `count = 0`.
2. For each `num` in `nums`:
   - Calculate `digits = (int) (Math.log10(num)) + 1`.
   - If `digits % 2 == 0`, increment `count`.
3. Return `count`.

---

## 🔹 Code

```java
class Solution {
    public int findNumbers(int[] nums) {
        int count = 0;
        for (int num : nums) {
            int digits = (int) (Math.log10(num)) + 1;
            if (digits % 2 == 0) {
                count++;
            }
        }
        return count;
    }
}
```

---

## 🔹 Detailed Dry Run

**Input:** `nums = [12, 345, 2, 6, 7896]`

| Iteration | Current Number | log₁₀(num) | Digits (⌊log₁₀⌋ + 1) | Even? | Count |
|-----------|----------------|------------|------------------------|-------|-------|
| 1         | 12             | 1.079      | 2                      | Yes   | 1     |
| 2         | 345            | 2.538      | 3                      | No    | 1     |
| 3         | 2              | 0.301      | 1                      | No    | 1     |
| 4         | 6              | 0.778      | 1                      | No    | 1     |
| 5         | 7896           | 3.897      | 4                      | Yes   | 2     |

**Output:** `2`

---

## 🔹 Complexity Analysis

| Complexity      | Value       |
|-----------------|-------------|
| Time Complexity | O(N)        |
| Space Complexity| O(1)        |

- **N**: Number of elements in `nums`.
- **Logarithmic operations** are **O(1)** per number.

---

# 🔍 Edge Cases

| Case                     | Input               | Expected Output | Explanation                          |
|--------------------------|---------------------|-----------------|--------------------------------------|
| Empty array              | `[]`                | `0`             | No numbers to check.                 |
| Single even-digit number | `[12]`              | `1`             | Only one number with 2 digits.       |
| Single odd-digit number  | `[7]`               | `0`             | Only one number with 1 digit.        |
| All even-digit numbers   | `[12, 34, 5678]`    | `3`             | All numbers have even digits.        |
| All odd-digit numbers    | `[1, 345, 789]`     | `0`             | No numbers with even digits.         |
| Maximum constraint       | `[100000]`          | `0`             | 6 digits → even, but `100000` has 6. |
| Duplicates               | `[12, 12, 12]`      | `3`             | All duplicates have even digits.     |

---

# 📚 Key Takeaways

1. **Digit Counting**: Logarithms provide an efficient way to count digits without string conversion.
2. **Time Complexity**: The optimal solution reduces time complexity from **O(N * D)** to **O(N)**.
3. **Edge Cases**: Always test for single-element inputs, empty arrays, and duplicates.
4. **Mathematical Insight**: Leveraging logarithmic properties is a powerful optimization technique.

---

# 🚀 Interview Tips

### **Follow-Up Questions**
- How would you handle negative numbers? *(Take absolute value first.)*
- Can you solve this without logarithms? *(Use division-based digit counting.)*
- What if the input contains `0`? *(Special case: `0` has 1 digit.)*

### **Common Pitfalls**
- **String Conversion Overhead**: Brute-force string conversion is inefficient for large inputs.
- **Logarithm Precision**: Floating-point inaccuracies may occur for very large numbers (e.g., `10^5`).
- **Edge Cases**: Forgetting to handle `0` or single-digit numbers.

### **Alternative Approaches**
- **Division-Based Digit Counting**: Repeatedly divide by 10 until the number becomes 0.
- **Precomputed Ranges**: Use a lookup table for digit counts (e.g., `1-9`: 1 digit, `10-99`: 2 digits, etc.).

---

# ✅ Conclusion

The **optimal solution** leverages logarithmic properties to count digits in **O(1)** time per number, resulting in an overall **O(N)** time complexity. This approach is **efficient, scalable, and interview-ready**, making it the preferred choice for FAANG-level interviews.

**Key Insight**: Mathematical operations (like logarithms) can often replace brute-force methods, leading to significant performance improvements.

**Final Answer**: The optimal solution is **clean, efficient, and handles all edge cases gracefully**. 🚀