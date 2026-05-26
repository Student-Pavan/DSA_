# 📌 Reverse Integer

---

# 📝 Problem Statement

Given a 32-bit signed integer `x`, return `x` with its digits reversed. If reversing `x` causes the value to go outside the 32-bit signed integer range `[-2³¹, 2³¹ - 1]`, then return `0`.

**Objective:**
Reverse the digits of the given integer while handling overflow conditions.

**Input:**
- A single integer `x` (32-bit signed integer).

**Output:**
- The reversed integer, or `0` if the reversed integer overflows.

**Constraints:**
- `-2³¹ <= x <= 2³¹ - 1`

---

# 💡 Intuition

Reversing an integer involves repeatedly extracting the last digit of the number and building the reversed number digit by digit. The key challenge is handling overflow: if the reversed number exceeds the 32-bit signed integer range, we must return `0`.

A naive approach would convert the integer to a string, reverse it, and convert back, but this is inefficient and doesn't handle overflow elegantly. A more efficient approach processes digits mathematically, checking for overflow at each step.

---

# 🐌 Brute Force Approach

## 🔹 Approach

1. Convert the integer to a string.
2. Reverse the string.
3. Convert the reversed string back to an integer.
4. Check if the reversed integer is within the 32-bit signed integer range.
5. Return the result or `0` if overflow occurs.

This approach is straightforward but inefficient due to string operations and lacks direct control over overflow handling.

---

## 🔹 Algorithm

1. Convert the integer `x` to a string.
2. Reverse the string.
3. Convert the reversed string back to a long (to handle potential overflow during conversion).
4. Check if the long value is within `[-2³¹, 2³¹ - 1]`.
5. Return the value as an integer if valid, otherwise return `0`.

---

## 🔹 Code

```java
class Solution {
    public int reverse(int x) {
        String s = Integer.toString(x);
        StringBuilder reversed = new StringBuilder();

        if (x < 0) {
            reversed.append("-");
            s = s.substring(1);
        }

        for (int i = s.length() - 1; i >= 0; i--) {
            reversed.append(s.charAt(i));
        }

        try {
            return Integer.parseInt(reversed.toString());
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}
```

---

## 🔹 Dry Run

**Input:** `x = 123`

| Step | Action | String State | Reversed String | Result |
|------|--------|--------------|-----------------|--------|
| 1    | Convert to string | `"123"` | `""` | - |
| 2    | Append digits in reverse | `"123"` | `"3"` → `"32"` → `"321"` | - |
| 3    | Parse to integer | - | `"321"` | `321` |

**Input:** `x = -123`

| Step | Action | String State | Reversed String | Result |
|------|--------|--------------|-----------------|--------|
| 1    | Convert to string | `"-123"` | `""` | - |
| 2    | Remove `-` and reverse | `"123"` | `"3"` → `"32"` → `"321"` | - |
| 3    | Append `-` | - | `"-321"` | - |
| 4    | Parse to integer | - | `"-321"` | `-321` |

**Input:** `x = 1534236469`

| Step | Action | String State | Reversed String | Result |
|------|--------|--------------|-----------------|--------|
| 1    | Convert to string | `"1534236469"` | `""` | - |
| 2    | Reverse string | `"1534236469"` | `"9646324351"` | - |
| 3    | Parse to integer | - | `"9646324351"` | Overflow → `0` |

---

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(n), where `n` is the number of digits in `x` |
| Space Complexity | O(n), due to string storage |

---

# ⚡ Optimal Approach

## 🔹 Approach

1. Initialize a variable `reversed` to `0`.
2. While `x` is not `0`:
   - Extract the last digit of `x` using `x % 10`.
   - Check if appending this digit to `reversed` would cause overflow:
     - If `reversed > Integer.MAX_VALUE / 10` or `reversed < Integer.MIN_VALUE / 10`, return `0`.
   - Update `reversed` as `reversed * 10 + digit`.
   - Remove the last digit from `x` using `x /= 10`.
3. Return `reversed`.

This approach avoids string operations and checks for overflow at each step, ensuring efficiency and correctness.

---

## 🔹 Why This Works

- **Mathematical Reversal:** By repeatedly extracting the last digit and building the reversed number, we avoid unnecessary string conversions.
- **Overflow Handling:** By checking if `reversed * 10 + digit` would exceed `Integer.MAX_VALUE` or `Integer.MIN_VALUE` before performing the operation, we prevent overflow.
- **Efficiency:** The algorithm processes each digit exactly once, resulting in linear time complexity relative to the number of digits.

---

## 🔹 Algorithm

1. Initialize `reversed = 0`.
2. While `x != 0`:
   - Extract the last digit: `digit = x % 10`.
   - Check for overflow:
     - If `reversed > Integer.MAX_VALUE / 10` or (`reversed == Integer.MAX_VALUE / 10` and `digit > 7`), return `0`.
     - If `reversed < Integer.MIN_VALUE / 10` or (`reversed == Integer.MIN_VALUE / 10` and `digit < -8`), return `0`.
   - Update `reversed = reversed * 10 + digit`.
   - Update `x = x / 10`.
3. Return `reversed`.

---

## 🔹 Code

```java
class Solution {
    public int reverse(int x) {
        int reversed = 0;

        while (x != 0) {
            int digit = x % 10;

            // Check for overflow before updating reversed
            if (reversed > Integer.MAX_VALUE / 10 || (reversed == Integer.MAX_VALUE / 10 && digit > 7)) {
                return 0;
            }
            if (reversed < Integer.MIN_VALUE / 10 || (reversed == Integer.MIN_VALUE / 10 && digit < -8)) {
                return 0;
            }

            reversed = reversed * 10 + digit;
            x /= 10;
        }

        return reversed;
    }
}
```

---

## 🔹 Detailed Dry Run

**Input:** `x = 123`

| Iteration | x | digit | reversed | Action |
|-----------|---|-------|----------|--------|
| 1         | 123 | 3 | 0 → 3 | `reversed = 0 * 10 + 3 = 3` |
| 2         | 12 | 2 | 3 → 32 | `reversed = 3 * 10 + 2 = 32` |
| 3         | 1 | 1 | 32 → 321 | `reversed = 32 * 10 + 1 = 321` |
| 4         | 0 | - | 321 | Exit loop, return `321` |

**Input:** `x = -123`

| Iteration | x | digit | reversed | Action |
|-----------|---|-------|----------|--------|
| 1         | -123 | -3 | 0 → -3 | `reversed = 0 * 10 + (-3) = -3` |
| 2         | -12 | -2 | -3 → -32 | `reversed = -3 * 10 + (-2) = -32` |
| 3         | -1 | -1 | -32 → -321 | `reversed = -32 * 10 + (-1) = -321` |
| 4         | 0 | - | -321 | Exit loop, return `-321` |

**Input:** `x = 1534236469`

| Iteration | x | digit | reversed | Action |
|-----------|---|-------|----------|--------|
| 1         | 1534236469 | 9 | 0 → 9 | `reversed = 0 * 10 + 9 = 9` |
| 2         | 153423646 | 6 | 9 → 96 | `reversed = 9 * 10 + 6 = 96` |
| 3         | 15342364 | 4 | 96 → 964 | `reversed = 96 * 10 + 4 = 964` |
| 4         | 1534236 | 6 | 964 → 9646 | `reversed = 964 * 10 + 6 = 9646` |
| 5         | 153423 | 3 | 9646 → 96463 | `reversed = 9646 * 10 + 3 = 96463` |
| 6         | 15342 | 2 | 96463 → 964632 | `reversed = 96463 * 10 + 2 = 964632` |
| 7         | 1534 | 4 | 964632 → 9646324 | `reversed = 964632 * 10 + 4 = 9646324` |
| 8         | 153 | 3 | 9646324 → 96463243 | `reversed = 9646324 * 10 + 3 = 96463243` |
| 9         | 15 | 5 | 96463243 → 964632435 | `reversed = 96463243 * 10 + 5 = 964632435` |
| 10        | 1 | 1 | 964632435 → Overflow | `reversed * 10 + 1 = 9646324351 > Integer.MAX_VALUE` → Return `0` |

---

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(n), where `n` is the number of digits in `x` |
| Space Complexity | O(1), as we use a constant amount of extra space |

---

# 🔍 Edge Cases

- **Zero:** `x = 0` → Returns `0`.
- **Single Digit:** `x = 5` → Returns `5`.
- **Negative Number:** `x = -123` → Returns `-321`.
- **Trailing Zeros:** `x = 120` → Returns `21`.
- **Overflow Positive:** `x = 1534236469` → Returns `0`.
- **Overflow Negative:** `x = -2147483648` → Returns `0`.
- **Minimum 32-bit Integer:** `x = -2147483412` → Returns `-2143847412`.

---

# 📚 Key Takeaways

1. **Mathematical Digit Extraction:** Use modulo and division to process digits without string conversion.
2. **Overflow Handling:** Check for overflow before updating the reversed number to ensure correctness.
3. **Efficiency:** The optimal approach processes each digit exactly once, making it linear in time complexity.
4. **Edge Cases:** Always consider zero, single-digit, negative, and overflow cases in integer reversal problems.

---

# 🚀 Interview Tips

- **Follow-up Questions:**
  - How would you handle 64-bit integers?
  - Can you optimize the overflow check further?
  - How would you reverse a floating-point number?
- **Common Pitfalls:**
  - Forgetting to handle negative numbers.
  - Incorrect overflow checks leading to wrong results.
  - Using string operations unnecessarily.
- **Alternative Approaches:**
  - Using a stack to reverse digits (less efficient).
  - Converting to a string and reversing (brute force).
- **Optimization Discussions:**
  - The optimal approach avoids string operations and checks overflow at each step, making it both time and space efficient.

---

# ✅ Conclusion

The optimal solution efficiently reverses an integer while handling overflow by processing digits mathematically and checking for overflow at each step. This approach ensures correctness and efficiency, making it ideal for interviews and production use. The key insight is to avoid string operations and handle overflow proactively, ensuring the solution is both robust and performant.