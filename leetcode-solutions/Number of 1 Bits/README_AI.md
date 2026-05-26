# 📌 Number of 1 Bits

**Write a function that takes an unsigned integer and returns the number of '1' bits it has (also known as the Hamming weight).**

---

# 📝 Problem Statement

### **Objective**
Count the number of set bits (bits with value `1`) in the binary representation of a given unsigned integer.

### **Input**
- An unsigned 32-bit integer `n`.

### **Output**
- An integer representing the count of `1` bits in `n`.

### **Constraints**
- The input is guaranteed to be a 32-bit unsigned integer (range: `0` to `2³² - 1`).
- You **must not** use any built-in functions that directly count bits (e.g., `Integer.bitCount()`).

### **Examples**
| Input (n) | Binary Representation | Output |
|-----------|-----------------------|--------|
| `00000000000000000000000000001011` (11) | `1011` | `3` |
| `00000000000000000000000010000000` (128) | `10000000` | `1` |
| `11111111111111111111111111111101` (4294967293) | `11111111111111111111111111111101` | `31` |

---

# 💡 Intuition

The problem requires counting the number of `1` bits in the binary representation of an integer. The key insight is that:

- **Every integer can be represented in binary**, and each bit can be checked individually.
- **Bitwise operations** (like `&` and `>>`) are efficient for manipulating individual bits.
- **Optimization Opportunity**: Instead of checking all 32 bits, we can stop early once all remaining bits are `0`.

---

# 🐌 Brute Force Approach

## 🔹 Approach
Check each of the 32 bits in the integer one by one. For each bit, use a bitmask (`1`) to determine if it is set (`1`). If it is, increment the count.

### **Key Steps**
1. Initialize a counter to `0`.
2. Iterate 32 times (for each bit in a 32-bit integer).
3. For each iteration, check if the least significant bit (LSB) is `1` using `n & 1`.
4. Right-shift `n` by 1 to process the next bit.
5. Return the counter.

---

## 🔹 Algorithm
1. Initialize `count = 0`.
2. For `i = 0` to `31`:
   - If `(n & 1) == 1`, increment `count`.
   - Right-shift `n` by 1 (`n = n >> 1`).
3. Return `count`.

---

## 🔹 Code

```java
class Solution {
    public int hammingWeight(int n) {
        int count = 0;
        for (int i = 0; i < 32; i++) {
            if ((n & 1) == 1) {
                count++;
            }
            n = n >> 1;
        }
        return count;
    }
}
```

---

## 🔹 Dry Run

**Input:** `n = 11` (binary: `1011`)

| Iteration | n (Binary) | n & 1 | Action | Count |
|-----------|------------|-------|--------|-------|
| 0         | `1011`     | `1`   | Increment count | `1` |
| 1         | `0101`     | `1`   | Increment count | `2` |
| 2         | `0010`     | `0`   | No change | `2` |
| 3         | `0001`     | `1`   | Increment count | `3` |
| 4         | `0000`     | `0`   | No change | `3` |
| ...       | `0000`     | `0`   | No change | `3` |

**Output:** `3`

---

## 🔹 Complexity Analysis

| Complexity      | Value       |
|-----------------|-------------|
| Time Complexity | O(32) = O(1) |
| Space Complexity | O(1)       |

---

# ⚡ Optimal Approach

## 🔹 Approach
Instead of checking all 32 bits, we can **stop early** once `n` becomes `0`. Additionally, we can use **Brian Kernighan’s Algorithm**, which cleverly skips over `0` bits by flipping the least significant `1` bit to `0` in each iteration.

### **Key Insight**
- `n & (n - 1)` flips the least significant `1` bit to `0`.
- This allows us to count **only the set bits** without iterating through all 32 bits.

---

## 🔹 Why This Works
- Each iteration removes the rightmost `1` bit.
- The loop runs **exactly as many times as there are `1` bits**, making it highly efficient for numbers with few set bits.

---

## 🔹 Algorithm
1. Initialize `count = 0`.
2. While `n != 0`:
   - Set `n = n & (n - 1)` (flips the least significant `1` bit to `0`).
   - Increment `count`.
3. Return `count`.

---

## 🔹 Code

```java
class Solution {
    public int hammingWeight(int n) {
        int count = 0;
        while (n != 0) {
            n = n & (n - 1);
            count++;
        }
        return count;
    }
}
```

---

## 🔹 Detailed Dry Run

**Input:** `n = 11` (binary: `1011`)

| Iteration | n (Binary) | n & (n - 1) | Action | Count |
|-----------|------------|-------------|--------|-------|
| 1         | `1011`     | `1010`      | Increment count | `1` |
| 2         | `1010`     | `1000`      | Increment count | `2` |
| 3         | `1000`     | `0000`      | Increment count | `3` |

**Output:** `3`

---

## 🔹 Complexity Analysis

| Complexity      | Value       |
|-----------------|-------------|
| Time Complexity | O(k), where `k` is the number of `1` bits |
| Space Complexity | O(1)       |

**Best Case:** O(1) (if `n = 0`).
**Worst Case:** O(32) = O(1) (if all bits are `1`).

---

# 🔍 Edge Cases

| Edge Case | Input (n) | Expected Output | Explanation |
|-----------|-----------|-----------------|-------------|
| Zero | `0` | `0` | No `1` bits. |
| All bits set | `0xFFFFFFFF` (4294967295) | `32` | All 32 bits are `1`. |
| Single `1` bit | `128` (`10000000`) | `1` | Only the 8th bit is set. |
| Alternating bits | `0x55555555` (1431655765) | `16` | Every other bit is `1`. |
| Large number | `2147483647` (`0x7FFFFFFF`) | `31` | Maximum 31-bit positive integer. |

---

# 📚 Key Takeaways

1. **Bitwise operations** (`&`, `>>`) are efficient for bit manipulation.
2. **Brian Kernighan’s Algorithm** is optimal for counting set bits.
3. **Early termination** improves performance for numbers with few `1` bits.
4. **Edge cases** matter—always test `0`, `1`, and maximum values.

---

# 🚀 Interview Tips

### **Follow-Up Questions**
- How would you modify the solution for a 64-bit integer?
- Can you solve this problem without using bitwise operations?
- What is the time complexity if the input is a very large number (e.g., `2^1000`)?

### **Common Pitfalls**
- **Sign Extension in Java**: Right-shifting a negative number fills with `1`s (use `>>>` for unsigned shift).
- **Infinite Loop**: Forgetting to update `n` in the loop (e.g., `while (n != 0)` without modifying `n`).
- **Off-by-One Errors**: Iterating 31 times instead of 32 for 32-bit integers.

### **Alternative Approaches**
- **Lookup Table**: Precompute bit counts for all 8-bit numbers and use them to count bits in larger numbers.
- **Parallel Counting**: Use SIMD instructions (advanced, not typically expected in interviews).

---

# ✅ Conclusion

The **optimal solution** (Brian Kernighan’s Algorithm) is preferred because:
- It runs in **O(k)** time, where `k` is the number of `1` bits (faster for sparse bit patterns).
- It avoids unnecessary iterations over `0` bits.
- It is **clean, efficient, and interview-friendly**.

**Key Insight:** `n & (n - 1)` flips the least significant `1` bit to `0`, allowing us to count set bits in **exactly `k` steps**.

This problem is a **classic example** of how bitwise operations can lead to elegant and efficient solutions. Mastering it will help in tackling more complex bit manipulation problems in interviews.