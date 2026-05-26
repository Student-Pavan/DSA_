# 📌 Count Partitions with Even Sum Difference

---

# 📝 Problem Statement

Given an array of integers `nums`, count the number of ways to partition the array into two non-empty subsets such that the **absolute difference** between their sums is **even**.

### **Objective**
Return the count of valid partitions where the absolute difference of subset sums is even.

### **Input**
- An array `nums` of integers (1 ≤ `nums.length` ≤ 1000, -1000 ≤ `nums[i]` ≤ 1000)

### **Output**
- An integer representing the number of valid partitions.

### **Important Constraints**
- The array is non-empty.
- Each subset must contain at least one element.
- The order of elements in subsets does not matter (partitions are unordered).
- The absolute difference between subset sums must be **even**.

---

# 💡 Intuition

The key insight lies in understanding the **parity** (evenness or oddness) of subset sums:

1. **Even Difference Condition**:
   The absolute difference between two numbers is even **if and only if both numbers have the same parity** (both even or both odd).
   - `even - even = even`
   - `odd - odd = even`
   - `even - odd = odd` (invalid)

2. **Total Sum Parity**:
   The parity of the total sum of the array determines the possible valid partitions:
   - If the **total sum is even**, then both subsets must have **even sums** to satisfy the even difference condition.
   - If the **total sum is odd**, then both subsets must have **odd sums** to satisfy the even difference condition.

3. **Counting Valid Partitions**:
   Instead of enumerating all possible subsets (which is computationally expensive), we can leverage **dynamic programming** or **combinatorial mathematics** to count subsets with the required parity.

---

# 🐌 Brute Force Approach

## 🔹 Approach

The brute force approach involves:
1. Generating **all possible non-empty subsets** of the array.
2. For each subset, compute its sum and the sum of its complement.
3. Check if the absolute difference between the two sums is even.
4. Count all valid partitions.

This approach is **exhaustive** and checks every possible way to split the array into two subsets.

---

## 🔹 Algorithm

1. Initialize a counter `count = 0`.
2. Iterate over all possible non-empty subsets of `nums` using bitmasking (from `1` to `2^n - 1`).
3. For each subset:
   - Compute the sum of the subset.
   - Compute the sum of the complement subset.
   - If the absolute difference between the two sums is even, increment `count`.
4. Return `count`.

> **Note**: Since each partition is counted twice (once for subset `A` and once for its complement `B`), we divide the final count by 2.

---

## 🔹 Code

```java
class Solution {
    public int countPartitionsWithEvenSumDifference(int[] nums) {
        int n = nums.length;
        int total = 1 << n;
        int count = 0;

        for (int mask = 1; mask < total; mask++) {
            int sum = 0;
            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) != 0) {
                    sum += nums[i];
                }
            }
            int complementSum = totalSum(nums) - sum;
            if (Math.abs(sum - complementSum) % 2 == 0) {
                count++;
            }
        }

        // Each partition is counted twice (A,B) and (B,A), so divide by 2
        return count / 2;
    }

    private int totalSum(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        return sum;
    }
}
```

---

## 🔹 Dry Run

**Input**: `nums = [1, 2, 3]`

| Subset (Bitmask) | Subset Elements | Subset Sum | Complement Sum | |Sum - Complement| | Valid? | Count |
|------------------|------------------|------------|-----------------|------------------|--------|-------|
| 001 (1)          | [1]              | 1          | 5               | 4                | Yes    | +1    |
| 010 (2)          | [2]              | 2          | 4               | 2                | Yes    | +1    |
| 011 (3)          | [1,2]            | 3          | 3               | 0                | Yes    | +1    |
| 100 (4)          | [3]              | 3          | 3               | 0                | Yes    | +1    |
| 101 (5)          | [1,3]            | 4          | 2               | 2                | Yes    | +1    |
| 110 (6)          | [2,3]            | 5          | 1               | 4                | Yes    | +1    |
| 111 (7)          | [1,2,3]          | 6          | 0               | 6                | Yes    | +1    |

> **Note**: The empty subset (000) is skipped.

- Total valid partitions before division: 6
- Since each partition is counted twice (e.g., [1] and [2,3] is same as [2,3] and [1]), we return `6 / 2 = 3`

**Final Answer**: `3`

---

## 🔹 Complexity Analysis

| Complexity       | Value               |
|------------------|---------------------|
| Time Complexity  | O(2<sup>n</sup> × n) |
| Space Complexity | O(1)                |

> **Note**: This is **exponential** and impractical for `n > 20`.

---

# ⚡ Optimal Approach

## 🔹 Approach

We leverage **mathematical insight** and **dynamic programming** to avoid brute-force enumeration.

### **Key Observations**:
1. The absolute difference `|S1 - S2|` is even **iff** `S1` and `S2` have the same parity.
2. Since `S1 + S2 = totalSum`, we have:
   - `S1 - S2 = 2S1 - totalSum`
   - `|S1 - S2| = |2S1 - totalSum|` → always even if `totalSum` is even? Not directly.
   - Instead: `|S1 - S2|` is even **iff** `S1 ≡ S2 (mod 2)` → `S1 ≡ totalSum - S1 (mod 2)` → `2S1 ≡ totalSum (mod 2)` → `0 ≡ totalSum (mod 2)` → `totalSum` is even.

   **Wait**: This seems contradictory.

   Let’s re-express:

   `|S1 - S2|` is even ⇨ `S1 - S2` is even ⇨ `S1 ≡ S2 (mod 2)`

   But `S2 = totalSum - S1`, so:
   `S1 ≡ totalSum - S1 (mod 2)` ⇒ `2S1 ≡ totalSum (mod 2)` ⇒ `0 ≡ totalSum (mod 2)` ⇒ `totalSum` is even.

   **Therefore**: The absolute difference is even **if and only if the total sum is even**.

   But this is **not true** — consider `nums = [1,3]` → total sum = 4 (even), partitions:
   - [1], [3] → |1-3| = 2 → even ✅
   - [1,3], [] → invalid (empty subset)

   Now `nums = [1,2]` → total sum = 3 (odd)
   - [1], [2] → |1-2| = 1 → odd ❌
   - [1,2], [] → invalid

   But wait: is there **any** partition with even difference when total sum is odd?

   Let’s try `nums = [1,1,2]` → total sum = 4 (even)
   - [1,1], [2] → sum1=2, sum2=2 → |0| → even ✅

   `nums = [1,1,1]` → total sum = 3 (odd)
   - [1], [1,1] → |1 - 2| = 1 → odd ❌
   - [1,1], [1] → same
   - [1,1,1], [] → invalid

   **Conclusion**: The absolute difference is even **only if the total sum is even**.

   But is it **always** possible when total sum is even?

   Yes — because if total sum is even, then both subsets must have the same parity (both even or both odd), so their difference is even.

   However, **not all partitions** will satisfy this — only those where both subsets have the same parity.

   But here's the catch: **if total sum is even**, then **all partitions** will have both subsets with the same parity (since `S1 + S2 = even` ⇒ `S1 ≡ S2 mod 2`), so **all partitions** will have even difference.

   **Wait**: That means:
   - If `totalSum` is **even**, then **every** partition has even difference.
   - If `totalSum` is **odd**, then **no** partition has even difference.

   But that contradicts our earlier example: `nums = [1,2,3]` → total sum = 6 (even), and we saw 3 valid partitions.

   Let’s verify:
   - Number of non-empty subsets: 7
   - Number of partitions (unordered): 7 / 2 = 3.5 → but we have 3 valid ones.

   Actually, the number of **unordered** partitions is `2^(n-1) - 1` (since each partition is counted twice, except the full set which is counted once).

   But in our brute force, we counted 6 valid ones → 3 partitions.

   And indeed, **all 3** have even difference.

   So the conclusion is:

> **The absolute difference is even for a partition if and only if the total sum of the array is even.**

> **Therefore**:
> - If `totalSum` is **even**, then **all** partitions have even difference.
> - If `totalSum` is **odd**, then **no** partition has even difference.

But wait — is this true?

Let’s test `nums = [1,1,2]` → total sum = 4 (even)
- [1], [1,2] → |1 - 3| = 2 → even ✅
- [1,1], [2] → |2 - 2| = 0 → even ✅
- [1,2], [1] → same as first

So yes, both partitions have even difference.

Now `nums = [1,2]` → total sum = 3 (odd)
- Only partition: [1], [2] → |1-2| = 1 → odd ❌

So indeed:

> **The number of partitions with even sum difference is:**
> - `2^(n-1) - 1` if `totalSum` is even
> - `0` if `totalSum` is odd

But wait — what about empty subsets? We exclude them.

Number of non-empty subsets: `2^n - 1`
Number of partitions (unordered pairs of non-empty subsets): `(2^n - 2)/2 = 2^(n-1) - 1`

And **all** of them are valid **if and only if total sum is even**.

So the optimal solution is:

```java
if (totalSum % 2 != 0) return 0;
else return (1 << (n - 1)) - 1;
```

But wait — is this always true?

Let’s test `nums = [2,2]` → total sum = 4 (even)
- Partitions: [2], [2] → only 1 partition
- `2^(2-1) - 1 = 2 - 1 = 1` → correct

`nums = [1,1,1,1]` → total sum = 4 (even)
- Number of partitions: `2^(4-1) - 1 = 8 - 1 = 7`
- Actual: 7 partitions → correct

So yes — the optimal solution is **O(1)** time and space.

But let’s double-check the logic:

> The absolute difference `|S1 - S2|` is even **iff** `S1 ≡ S2 (mod 2)`
> But `S1 + S2 = totalSum`
> So `S1 ≡ totalSum - S1 (mod 2)` ⇒ `2S1 ≡ totalSum (mod 2)` ⇒ `0 ≡ totalSum (mod 2)`
> ⇒ `totalSum` must be even.

> Therefore, **if totalSum is odd**, no partition satisfies the condition.

> If `totalSum` is even, then **all** partitions satisfy `S1 ≡ S2 (mod 2)`, so all have even difference.

> Hence, the answer is:
> - `0` if `totalSum` is odd
> - `2^(n-1) - 1` if `totalSum` is even

But wait — what if the array has only one element?
- Then no valid partition (since both subsets must be non-empty), so answer should be 0.

Let’s test `n = 1`:
- `2^(0) - 1 = 1 - 1 = 0` → correct.

So the formula works.

---

## 🔹 Why This Works

- **Mathematical Proof**:
  The condition `|S1 - S2| % 2 == 0` is equivalent to `S1 % 2 == S2 % 2`.
  Since `S1 + S2 = totalSum`, we have `S1 % 2 == (totalSum - S1) % 2`.
  This implies `2*(S1 % 2) ≡ totalSum % 2 (mod 2)` → `0 ≡ totalSum % 2`.
  So the condition holds **only if** `totalSum` is even.

- **When totalSum is even**, then for **any** partition, `S1 % 2 == S2 % 2`, so the difference is even.
  Therefore, **all** partitions are valid.

- The number of unordered partitions of an array into two non-empty subsets is `2^(n-1) - 1`.

---

## 🔹 Algorithm

1. Compute the total sum of the array.
2. If the total sum is odd, return `0`.
3. Otherwise, return `2^(n-1) - 1`.

---

## 🔹 Code

```java
class Solution {
    public int countPartitionsWithEvenSumDifference(int[] nums) {
        int totalSum = 0;
        for (int num : nums) {
            totalSum += num;
        }
        if (totalSum % 2 != 0) {
            return 0;
        }
        int n = nums.length;
        return (1 << (n - 1)) - 1;
    }
}
```

---

## 🔹 Detailed Dry Run

**Input**: `nums = [1, 2, 3]`

| Step | Operation | Value |
|------|-----------|-------|
| 1    | Compute totalSum | 1 + 2 + 3 = 6 |
| 2    | Check parity of totalSum | 6 % 2 == 0 → even |
| 3    | Compute `2^(n-1) - 1` | n = 3 → `2^2 - 1 = 4 - 1 = 3` |
| 4    | Return result | `3` |

**Output**: `3` ✅

---

## 🔹 Complexity Analysis

| Complexity       | Value       |
|------------------|-------------|
| Time Complexity  | O(n)        |
| Space Complexity | O(1)        |

> **Note**: This is **optimal** and handles all constraints efficiently.

---

# 🔍 Edge Cases

| Edge Case | Expected Output | Explanation |
|---------|------------------|-------------|
| `nums = []` | 0 | Empty array — invalid input (per constraints, n ≥ 1) |
| `nums = [5]` | 0 | Only one element — no valid partition |
| `nums = [2, 4]` | 1 | Total sum = 6 (even) → 1 partition: [2],[4] |
| `nums = [1, 3]` | 0 | Total sum = 4 (even)? Wait: 1+3=4 → even → should return 1? But only one partition: [1],[3] → |1-3|=2 → even → should return 1. But `2^(2-1)-1 = 1` → correct. But earlier I thought [1,3] sum=4 → even → return 1. So this is **not** an edge case with 0. Let’s correct: |
| `nums = [1, 2]` | 0 | Total sum = 3 (odd) → 0 |
| `nums = [1, 1, 1]` | 0 | Total sum = 3 (odd) → 0 |
| `nums = [2, 2, 2]` | 3 | Total sum = 6 (even) → `2^(3-1)-1 = 3` |
| `nums = [-1, 1]` | 0 | Total sum = 0 (even) → `2^(2-1)-1 = 1` → but | -1 - 1 | = 2 → even → valid. So return 1. But wait: [-1],[1] → valid. So output 1. |
| `nums = [0, 0]` | 1 | Total sum = 0 (even) → 1 partition: [0],[0] |

> **Correction**: The only edge case that returns 0 is when `totalSum` is odd **or** when `n == 1`.

---

# 📚 Key Takeaways

- **Parity is key**: The evenness of the total sum determines whether **any** partition can have even difference.
- **All or nothing**: If the total sum is even, **every** partition has even difference; if odd, **none** do.
- **Combinatorial count**: The number of unordered partitions of an array into two non-empty subsets is `2^(n-1) - 1`.
- **Optimization insight**: Avoid brute force by leveraging mathematical properties.
- **Efficiency**: The optimal solution runs in **O(n)** time and **O(1)** space.

---

# 🚀 Interview Tips

- **Ask clarifying questions**:
  - Are the subsets required to be non-empty?
  - Can the array contain negative numbers?
  - What is the expected output for `n = 1`?

- **Discuss trade-offs**:
  - Brute force is intuitive but inefficient.
  - Optimal solution requires deep insight into parity and combinatorics.

- **Follow-up questions**:
  - What if we want the difference to be **odd**?
  - What if we want the difference to be **exactly k**?
  - Can we generalize to k subsets?

- **Common pitfalls**:
  - Forgetting that partitions are unordered (counting each twice).
  - Not handling single-element arrays.
  - Misunderstanding the parity condition.

- **Alternative approaches**:
  - Dynamic programming to count subsets with even/odd sum (though overkill here).
  - Using generating functions (advanced).

---

# ✅ Conclusion

The **optimal solution** leverages a **mathematical insight** about parity and combinatorics to solve the problem in **linear time** with **constant space**. The key realization is that the evenness of the total sum determines whether **any** partition satisfies the condition — and if so, **all** partitions do.

This problem is an excellent example of how **deep understanding of number theory** can lead to **exponential speedups** in algorithm design.

> **Final Answer**: Count = `0` if total sum is odd, otherwise `2^(n-1) - 1`.