# 📌 Binary Prefix Divisible By 5

---

# 📝 Problem Statement

Given a binary array `nums`, return a boolean array `result` where `result[i]` is `true` if the binary number formed by the prefix `nums[0..i]` (from left to right) is divisible by 5, and `false` otherwise.

### **Key Details:**
- **Input:** `nums` (array of 0s and 1s, `0 <= nums.length <= 30,000`)
- **Output:** `result` (boolean array of same length as `nums`)
- **Constraints:**
  - Binary number can be very large (up to 30,000 bits), so direct conversion to integer is infeasible.
  - Must compute divisibility without constructing the full number.

---

# 💡 Intuition

The core insight is recognizing that **divisibility by 5** can be checked using modular arithmetic. Instead of building the entire binary number (which could overflow), we track the **remainder** of the current prefix when divided by 5.

- Start with `remainder = 0`.
- For each bit, update the remainder as `(remainder * 2 + current_bit) % 5`.
- If the remainder is `0`, the prefix is divisible by 5.

This avoids overflow and runs in **O(n)** time with **O(1)** space.

---

# 🐌 Brute Force Approach

## 🔹 Approach

Convert each prefix into its decimal equivalent and check divisibility by 5. However, this fails for large inputs due to integer overflow.

### **Why It Fails:**
- Binary numbers with > 32 bits exceed `Integer.MAX_VALUE`.
- Even `long` fails for > 64 bits.

---

## 🔹 Algorithm

1. Initialize an empty list `result`.
2. For each index `i` from `0` to `n-1`:
   - Convert `nums[0..i]` to decimal.
   - Check if divisible by 5.
   - Append `true`/`false` to `result`.
3. Return `result`.

---

## 🔹 Code

```java
class Solution {
    public List<Boolean> prefixesDivBy5(int[] nums) {
        List<Boolean> result = new ArrayList<>();
        long current = 0;
        for (int num : nums) {
            current = (current * 2 + num) % 5; // Modulo to prevent overflow
            result.add(current == 0);
        }
        return result;
    }
}
```

> **Note:** Even with `long`, this is technically a brute-force approach because it still computes the full number (though modulo prevents overflow). The **optimal** solution avoids this entirely.

---

## 🔹 Dry Run

**Input:** `nums = [1, 0, 1, 1]`

| Step | Current Bit | Current Value (Mod 5) | Divisible by 5? | Result |
|------|-------------|-----------------------|------------------|--------|
| 0    | 1           | (0 * 2 + 1) % 5 = 1   | No               | false  |
| 1    | 0           | (1 * 2 + 0) % 5 = 2   | No               | false  |
| 2    | 1           | (2 * 2 + 1) % 5 = 0   | Yes              | true   |
| 3    | 1           | (0 * 2 + 1) % 5 = 1   | No               | false  |

**Output:** `[false, false, true, false]`

---

## 🔹 Complexity Analysis

| Complexity      | Value       |
|-----------------|-------------|
| Time Complexity | O(n)        |
| Space Complexity| O(n) (output) |

---

# ⚡ Optimal Approach

## 🔹 Approach

Use **modular arithmetic** to track the remainder of the current prefix when divided by 5. This avoids overflow and runs in **O(n)** time with **O(1)** auxiliary space.

### **Key Insight:**
- `(a * 2 + b) % 5 = ((a % 5) * 2 + b) % 5`
- This allows us to compute the remainder incrementally.

---

## 🔹 Why This Works

- **No Overflow:** We only store the remainder, not the full number.
- **Efficiency:** Each step is O(1), making the total time O(n).
- **Correctness:** Modular arithmetic preserves divisibility.

---

## 🔹 Algorithm

1. Initialize `remainder = 0`.
2. For each bit in `nums`:
   - Update `remainder = (remainder * 2 + bit) % 5`.
   - Append `remainder == 0` to the result.
3. Return the result.

---

## 🔹 Code

```java
class Solution {
    public List<Boolean> prefixesDivBy5(int[] nums) {
        List<Boolean> result = new ArrayList<>();
        int remainder = 0;
        for (int num : nums) {
            remainder = (remainder * 2 + num) % 5;
            result.add(remainder == 0);
        }
        return result;
    }
}
```

---

## 🔹 Detailed Dry Run

**Input:** `nums = [0, 1, 1, 0, 1]`

| Step | Bit | Remainder Calculation       | Remainder | Divisible by 5? | Result  |
|------|-----|-----------------------------|-----------|------------------|---------|
| 0    | 0   | (0 * 2 + 0) % 5 = 0         | 0         | Yes              | true    |
| 1    | 1   | (0 * 2 + 1) % 5 = 1         | 1         | No               | false   |
| 2    | 1   | (1 * 2 + 1) % 5 = 3         | 3         | No               | false   |
| 3    | 0   | (3 * 2 + 0) % 5 = 1         | 1         | No               | false   |
| 4    | 1   | (1 * 2 + 1) % 5 = 3         | 3         | No               | false   |

**Output:** `[true, false, false, false, false]`

---

## 🔹 Complexity Analysis

| Complexity      | Value       |
|-----------------|-------------|
| Time Complexity | O(n)        |
| Space Complexity| O(n) (output) |

---

# 🔍 Edge Cases

| Case                     | Expected Output                     | Explanation                          |
|--------------------------|-------------------------------------|--------------------------------------|
| `nums = []`              | `[]`                                | Empty input → empty output.          |
| `nums = [0]`             | `[true]`                            | Single 0 is divisible by 5.          |
| `nums = [1]`             | `[false]`                           | 1 is not divisible by 5.             |
| `nums = [1, 0, 1]`       | `[false, false, true]`              | 101 (5 in decimal) is divisible by 5.|
| `nums = [1, 1, 1, 1, 1]` | `[false, false, false, false, true]`| 11111 (31) is not divisible by 5.    |

---

# 📚 Key Takeaways

1. **Modular Arithmetic** is crucial for handling large numbers without overflow.
2. **Incremental Updates** allow O(1) per-step computation.
3. **Divisibility by 5** can be checked using `(current * 2 + bit) % 5`.
4. **Brute Force Fails** for large inputs due to overflow.

---

# 🚀 Interview Tips

- **Follow-Up:** How would you handle divisibility by 3 or 7? (Hint: Same approach, but modulo changes.)
- **Pitfall:** Forgetting to use modulo can lead to overflow.
- **Alternative:** Using strings to represent binary numbers is inefficient (O(n²) time).
- **Optimization:** The optimal solution is already O(n) time and O(1) space (excluding output).

---

# ✅ Conclusion

The **optimal solution** leverages modular arithmetic to avoid overflow and compute divisibility in **O(n)** time. This approach is both **efficient** and **interview-ready**, making it the preferred choice for handling large binary prefixes.

**Key Insight:** Always use modular arithmetic when dealing with large numbers in divisibility problems.