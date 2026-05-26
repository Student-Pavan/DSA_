# 📌 Power of Four

---

# 📝 Problem Statement

Given an integer `n`, return `true` if it is a power of four. Otherwise, return `false`.

An integer `n` is a power of four if there exists an integer `x` such that:
**n = 4ˣ**

### **Constraints:**
- `-2³¹ <= n <= 2³¹ - 1`

### **Examples:**
| Input | Output | Explanation |
|-------|--------|-------------|
| `16`  | `true` | 4² = 16     |
| `5`   | `false`| No such `x` |
| `1`   | `true` | 4⁰ = 1      |

---

# 💡 Intuition

A number is a power of four if:
1. It is a power of two (since 4ˣ = (2²)ˣ = 2²ˣ).
2. The single set bit in its binary representation is in an **odd position** (1-based index).

For example:
- `16` (4²) → `10000` (bit at position 5, which is odd).
- `8` (2³) → `1000` (bit at position 4, which is even → not a power of four).

This insight allows us to use **bit manipulation** for an optimal solution.

---

# 🐌 Brute Force Approach

## 🔹 Approach
Repeatedly divide `n` by 4 until it becomes 1. If at any point `n` is not divisible by 4 or becomes less than 1, it is not a power of four.

## 🔹 Algorithm
1. Handle edge cases (`n <= 0`).
2. While `n > 1`:
   - If `n % 4 != 0`, return `false`.
   - Divide `n` by 4.
3. Return `true`.

## 🔹 Code
```java
class Solution {
    public boolean isPowerOfFour(int n) {
        if (n <= 0) {
            return false;
        }
        while (n > 1) {
            if (n % 4 != 0) {
                return false;
            }
            n /= 4;
        }
        return true;
    }
}
```

## 🔹 Dry Run
**Input:** `n = 16`

| Step | n  | Action          | Result  |
|------|----|-----------------|---------|
| 1    | 16 | 16 % 4 == 0     | Continue|
| 2    | 4  | 4 % 4 == 0      | Continue|
| 3    | 1  | Loop exits      | `true`  |

**Input:** `n = 5`

| Step | n  | Action          | Result  |
|------|----|-----------------|---------|
| 1    | 5  | 5 % 4 != 0      | `false` |

## 🔹 Complexity Analysis
| Complexity      | Value       |
|-----------------|-------------|
| Time Complexity | O(log₄ n)   |
| Space Complexity| O(1)        |

---

# ⚡ Optimal Approach

## 🔹 Approach
Use **bit manipulation** to check:
1. `n` is a power of two (`n > 0 && (n & (n - 1)) == 0`).
2. The single set bit is in an odd position (`n & 0x55555555 != 0`).

**Why `0x55555555`?**
- Binary: `0101 0101 0101 0101 0101 0101 0101 0101`
- Ensures the set bit is in an odd position (1-based).

## 🔹 Why This Works
- Powers of four have exactly one set bit in an odd position.
- The mask `0x55555555` filters out all even-positioned bits.

## 🔹 Algorithm
1. Check if `n > 0`.
2. Check if `n` is a power of two (`(n & (n - 1)) == 0`).
3. Check if the set bit is in an odd position (`n & 0x55555555 != 0`).

## 🔹 Code
```java
class Solution {
    public boolean isPowerOfFour(int n) {
        return n > 0 && (n & (n - 1)) == 0 && (n & 0x55555555) != 0;
    }
}
```

## 🔹 Detailed Dry Run
**Input:** `n = 16` (Binary: `10000`)

| Step | Check                     | Result  |
|------|---------------------------|---------|
| 1    | `n > 0`                   | `true`  |
| 2    | `(16 & 15) == 0` → `0 == 0`| `true`  |
| 3    | `(16 & 0x55555555) != 0`  | `true`  |

**Input:** `n = 8` (Binary: `1000`)

| Step | Check                     | Result  |
|------|---------------------------|---------|
| 1    | `n > 0`                   | `true`  |
| 2    | `(8 & 7) == 0` → `0 == 0` | `true`  |
| 3    | `(8 & 0x55555555) == 0`   | `false` |

## 🔹 Complexity Analysis
| Complexity      | Value       |
|-----------------|-------------|
| Time Complexity | O(1)        |
| Space Complexity| O(1)        |

---

# 🔍 Edge Cases
| Case               | Expected Output | Explanation                     |
|--------------------|-----------------|---------------------------------|
| `n = 1`            | `true`          | 4⁰ = 1                          |
| `n = 0`            | `false`         | Not a power of four             |
| `n = -16`          | `false`         | Negative numbers are invalid    |
| `n = 2`            | `false`         | Power of two but not four       |
| `n = 64`           | `true`          | 4³ = 64                         |

---

# 📚 Key Takeaways
1. **Powers of four are a subset of powers of two** (with additional constraints).
2. **Bit manipulation** is efficient for power checks.
3. **Masking (`0x55555555`)** helps verify bit positions.
4. **Logarithmic division** is intuitive but slower than bitwise operations.

---

# 🚀 Interview Tips
- **Follow-up:** Can you solve this without loops or recursion?
  *(Answer: Yes, using bit manipulation.)*
- **Common Pitfall:** Forgetting to handle `n <= 0`.
- **Alternative Approach:** Use logarithms (`log₄ n` is integer).
- **Optimization Insight:** Bitwise operations are **O(1)** and hardware-optimized.

---

# ✅ Conclusion
The **optimal solution** leverages bit manipulation for **O(1) time and space complexity**, making it superior to the brute-force approach. The key insight is recognizing that powers of four have a single set bit in an odd position, which can be checked using a bitmask.

**Key Learning:**
- Bitwise operations are powerful for mathematical checks.
- Understanding binary representations unlocks efficient solutions.