# 📌 Power of Two

---

# 📝 Problem Statement

Given an integer `n`, return `true` if it is a power of two. Otherwise, return `false`.

An integer `n` is a power of two if there exists an integer `x` such that `n == 2ˣ`.

**Constraints:**
- `-2³¹ <= n <= 2³¹ - 1`

**Examples:**
1. Input: `n = 1`
   Output: `true`
   Explanation: `2⁰ = 1`

2. Input: `n = 16`
   Output: `true`
   Explanation: `2⁴ = 16`

3. Input: `n = 3`
   Output: `false`

4. Input: `n = 0`
   Output: `false`

---

# 💡 Intuition

A number is a power of two if it is positive and has exactly one bit set in its binary representation. For example:
- `1` → `1` (binary)
- `2` → `10` (binary)
- `4` → `100` (binary)
- `8` → `1000` (binary)

The key insight is that powers of two in binary form have a single `1` followed by zeros. This property can be exploited using bitwise operations for an efficient solution.

---

# 🐌 Brute Force Approach

## 🔹 Approach

The brute force approach involves repeatedly dividing the number by 2 until it becomes 1. If at any point the division does not yield an integer or the number becomes less than 1, it is not a power of two.

**Steps:**
1. Handle edge cases (n ≤ 0).
2. While `n > 1`, divide `n` by 2.
3. If `n` becomes 1, return `true`; otherwise, return `false`.

---

## 🔹 Algorithm

1. If `n <= 0`, return `false`.
2. While `n % 2 == 0`:
   - Divide `n` by 2.
3. If `n == 1`, return `true`; else, return `false`.

---

## 🔹 Code

```java
class Solution {
    public boolean isPowerOfTwo(int n) {
        if (n <= 0) {
            return false;
        }
        while (n % 2 == 0) {
            n /= 2;
        }
        return n == 1;
    }
}
```

---

## 🔹 Dry Run

**Example:** `n = 16`

| Step | n (Before) | Action       | n (After) |
|------|------------|--------------|-----------|
| 1    | 16         | 16 % 2 == 0  | 8         |
| 2    | 8          | 8 % 2 == 0   | 4         |
| 3    | 4          | 4 % 2 == 0   | 2         |
| 4    | 2          | 2 % 2 == 0   | 1         |
| 5    | 1          | Terminate    | `true`    |

**Example:** `n = 3`

| Step | n (Before) | Action       | n (After) |
|------|------------|--------------|-----------|
| 1    | 3          | 3 % 2 != 0   | Terminate |
| 2    | 3          | Return `false` | -         |

---

## 🔹 Complexity Analysis

| Complexity       | Value       |
|------------------|-------------|
| Time Complexity  | O(log n)    |
| Space Complexity | O(1)        |

---

# ⚡ Optimal Approach

## 🔹 Approach

The optimal approach leverages bitwise operations. A power of two in binary has exactly one `1` followed by zeros. For example:
- `4` → `100`
- `8` → `1000`

If we subtract 1 from a power of two, all bits after the single `1` flip to `1`:
- `4 - 1 = 3` → `011`
- `8 - 1 = 7` → `0111`

Performing a bitwise AND between `n` and `n - 1` will yield `0` if `n` is a power of two:
- `4 & 3` → `100 & 011` = `000`
- `8 & 7` → `1000 & 0111` = `0000`

**Edge Case:** `n = 0` must be handled separately since `0 & -1` is not `0`.

---

## 🔹 Why This Works

- Powers of two have a single `1` in their binary representation.
- Subtracting 1 flips all bits after the `1` to `1`.
- The bitwise AND of `n` and `n - 1` will be `0` only if `n` is a power of two (or `0`, which is handled separately).

---

## 🔹 Algorithm

1. If `n <= 0`, return `false`.
2. Return `(n & (n - 1)) == 0`.

---

## 🔹 Code

```java
class Solution {
    public boolean isPowerOfTwo(int n) {
        if (n <= 0) {
            return false;
        }
        return (n & (n - 1)) == 0;
    }
}
```

---

## 🔹 Detailed Dry Run

**Example:** `n = 16`

| Step | n (Binary) | n - 1 (Binary) | n & (n - 1) | Result |
|------|------------|----------------|-------------|--------|
| 1    | `10000`    | `01111`        | `00000`     | `true` |

**Example:** `n = 3`

| Step | n (Binary) | n - 1 (Binary) | n & (n - 1) | Result |
|------|------------|----------------|-------------|--------|
| 1    | `0011`     | `0010`         | `0010`      | `false`|

**Example:** `n = 0`

| Step | n (Binary) | Action         | Result |
|------|------------|----------------|--------|
| 1    | `0000`     | `n <= 0`       | `false`|

---

## 🔹 Complexity Analysis

| Complexity       | Value       |
|------------------|-------------|
| Time Complexity  | O(1)        |
| Space Complexity | O(1)        |

---

# 🔍 Edge Cases

| Edge Case          | Explanation                                                                 |
|--------------------|-----------------------------------------------------------------------------|
| `n = 0`            | Not a power of two.                                                         |
| `n = 1`            | `2⁰ = 1` → Valid.                                                           |
| `n = -16`          | Negative numbers cannot be powers of two.                                   |
| `n = 2³¹`          | Outside the 32-bit signed integer range (max is `2³¹ - 1`).                 |
| `n = 536870912`    | `2²⁹` → Valid.                                                              |
| `n = 1073741825`   | Not a power of two (`2³⁰ + 1`).                                             |

---

# 📚 Key Takeaways

1. **Bitwise Operations:** Efficiently solve problems involving powers of two using bit manipulation.
2. **Logarithmic vs. Constant Time:** The brute force approach runs in `O(log n)`, while the optimal approach runs in `O(1)`.
3. **Binary Representation:** Understanding binary patterns is crucial for optimizing bitwise solutions.
4. **Edge Cases:** Always handle `n = 0` and negative numbers explicitly.

---

# 🚀 Interview Tips

1. **Follow-Up Questions:**
   - How would you handle very large numbers (e.g., `2¹⁰⁰`)?
   - Can you solve this without using division or bitwise operations?
   - How would you extend this to check for powers of three or four?

2. **Common Pitfalls:**
   - Forgetting to handle `n = 0`.
   - Assuming all positive numbers with a single `1` in binary are powers of two (e.g., `5` is not a power of two).
   - Incorrectly handling negative numbers.

3. **Alternative Approaches:**
   - Using logarithms (though floating-point precision can cause issues).
   - Precomputing all powers of two up to `2³¹ - 1` and checking membership (not space-efficient).

4. **Optimization Insight:**
   - Bitwise operations are among the fastest operations a CPU can perform. Always prefer them for problems involving binary representations.

---

# ✅ Conclusion

The optimal solution leverages bitwise operations to achieve constant time complexity, making it highly efficient. The key insight is recognizing that powers of two have a unique binary representation with a single `1` followed by zeros. This approach is not only optimal but also elegant and interview-friendly, demonstrating a strong understanding of bit manipulation.

**Key Learning:** Always explore bitwise solutions for problems involving powers, binary representations, or parity checks. They are often the most efficient and impressive in technical interviews.