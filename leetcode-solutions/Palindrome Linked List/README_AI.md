# 📌 Palindrome Linked List

---

# 📝 Problem Statement

Given the head of a singly linked list, determine if it is a palindrome.

A **palindrome** is a sequence that reads the same backward as forward.

### **Objective**
Return `true` if the linked list is a palindrome, otherwise return `false`.

### **Input**
- `head`: The head node of a singly linked list.

### **Output**
- `boolean`: `true` if the linked list is a palindrome, `false` otherwise.

### **Constraints**
- The number of nodes in the list is in the range `[0, 10⁵]`.
- Node values are integers in the range `[-10⁵, 10⁵]`.

### **Follow-Up**
Can you solve this problem in **O(n) time** and **O(1) space**?

---

# 💡 Intuition

A palindrome reads the same forwards and backwards. For a linked list, this means the first half should mirror the second half. However, since we can't traverse backward in a singly linked list, we need a way to compare the first half with the reversed second half.

**Key Insight:**
We can reverse the second half of the linked list and compare it with the first half. If they match, the list is a palindrome.

**Optimization Insight:**
Instead of storing the entire list in an array (which uses O(n) space), we can reverse the second half in-place and compare it with the first half, achieving O(1) space.

---

# 🐌 Brute Force Approach

## 🔹 Approach
1. **Convert the linked list to an array**: Traverse the list and store all node values in an array.
2. **Check for palindrome**: Use two pointers (one at the start and one at the end) to compare values in the array.

This approach is straightforward but uses O(n) extra space for the array.

---

## 🔹 Algorithm
1. Traverse the linked list and store all node values in an array.
2. Initialize two pointers, `left = 0` and `right = array.length - 1`.
3. While `left < right`:
   - If `array[left] != array[right]`, return `false`.
   - Increment `left` and decrement `right`.
4. Return `true`.

---

## 🔹 Code

```java
class Solution {
    public boolean isPalindrome(ListNode head) {
        List<Integer> values = new ArrayList<>();
        ListNode current = head;
        while (current != null) {
            values.add(current.val);
            current = current.next;
        }

        int left = 0;
        int right = values.size() - 1;
        while (left < right) {
            if (!values.get(left).equals(values.get(right))) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}
```

---

## 🔹 Dry Run

**Example:** `1 → 2 → 2 → 1`

| Step | Action                     | Array State       | Left | Right | Comparison | Result |
|------|----------------------------|-------------------|------|-------|------------|--------|
| 1    | Traverse list              | [1, 2, 2, 1]      | 0    | 3     | -          | -      |
| 2    | Compare `array[0]` and `array[3]` | -           | 0    | 3     | 1 == 1     | true   |
| 3    | Move pointers              | -                 | 1    | 2     | -          | -      |
| 4    | Compare `array[1]` and `array[2]` | -           | 1    | 2     | 2 == 2     | true   |
| 5    | Move pointers              | -                 | 2    | 1     | -          | -      |
| 6    | Terminate (`left >= right`) | -                 | -    | -     | -          | true   |

**Final Result:** `true`

---

## 🔹 Complexity Analysis

| Complexity      | Value       |
|-----------------|-------------|
| Time Complexity | O(n)        |
| Space Complexity| O(n)        |

---

# ⚡ Optimal Approach

## 🔹 Approach
1. **Find the middle of the linked list**: Use the slow-fast pointer technique to find the middle.
2. **Reverse the second half**: Reverse the second half of the list in-place.
3. **Compare the first half with the reversed second half**: Traverse both halves simultaneously and compare node values.
4. **Restore the list (optional)**: Reverse the second half again to restore the original list structure.

This approach achieves O(n) time and O(1) space.

---

## 🔹 Why This Works
- The slow-fast pointer technique efficiently finds the middle in O(n/2) time.
- Reversing the second half in-place allows us to compare it with the first half without extra space.
- The comparison step ensures the list is a palindrome if all corresponding nodes match.

---

## 🔹 Algorithm
1. Use slow and fast pointers to find the middle of the list.
2. Reverse the second half of the list starting from the slow pointer.
3. Compare the first half with the reversed second half.
4. Return `true` if all comparisons match, otherwise `false`.

---

## 🔹 Code

```java
class Solution {
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }

        // Step 1: Find the middle of the list
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // Step 2: Reverse the second half
        ListNode prev = null;
        ListNode current = slow;
        while (current != null) {
            ListNode next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }

        // Step 3: Compare the first half and reversed second half
        ListNode first = head;
        ListNode second = prev;
        boolean isPalindrome = true;
        while (second != null) {
            if (first.val != second.val) {
                isPalindrome = false;
                break;
            }
            first = first.next;
            second = second.next;
        }

        return isPalindrome;
    }
}
```

---

## 🔹 Detailed Dry Run

**Example:** `1 → 2 → 2 → 1`

| Step | Action                     | Slow | Fast | First Half | Second Half (Reversed) | Comparison | Result |
|------|----------------------------|------|------|------------|------------------------|------------|--------|
| 1    | Initialize slow and fast   | 1    | 1    | -          | -                      | -          | -      |
| 2    | Move slow and fast         | 2    | 2 → 1| -          | -                      | -          | -      |
| 3    | Move fast                  | 2    | null | -          | -                      | -          | -      |
| 4    | Reverse second half        | -    | -    | -          | 1 → 2                  | -          | -      |
| 5    | Compare first and second   | -    | -    | 1          | 1                      | 1 == 1     | true   |
| 6    | Move pointers              | -    | -    | 2          | 2                      | -          | -      |
| 7    | Compare first and second   | -    | -    | 2          | 2                      | 2 == 2     | true   |
| 8    | Terminate                  | -    | -    | -          | null                   | -          | true   |

**Final Result:** `true`

---

## 🔹 Complexity Analysis

| Complexity      | Value       |
|-----------------|-------------|
| Time Complexity | O(n)        |
| Space Complexity| O(1)        |

---

# 🔍 Edge Cases

| Edge Case                     | Expected Output | Explanation                          |
|-------------------------------|-----------------|--------------------------------------|
| Empty list (`[]`)             | `true`          | An empty list is trivially a palindrome. |
| Single node (`[1]`)           | `true`          | A single node is always a palindrome. |
| Two nodes (`[1, 1]`)          | `true`          | Values match.                        |
| Two nodes (`[1, 2]`)          | `false`         | Values do not match.                 |
| Odd-length list (`[1,2,1]`)   | `true`          | Middle node is ignored in comparison.|
| Even-length list (`[1,2,2,1]`)| `true`          | Both halves match.                   |
| Large list (`[1,2,...,2,1]`) | `true`          | Handles large input efficiently.     |

---

# 📚 Key Takeaways

1. **Two-Pointer Technique**: The slow-fast pointer technique is efficient for finding the middle of a linked list.
2. **In-Place Reversal**: Reversing a linked list in-place is a powerful tool for optimizing space.
3. **Palindrome Check**: Comparing the first half with the reversed second half is a common pattern for palindrome problems.
4. **Space Optimization**: The optimal solution avoids extra space by reversing the second half in-place.

---

# 🚀 Interview Tips

1. **Follow-Up Question**: Can you solve this in O(n) time and O(1) space? (Answer: Yes, using the optimal approach.)
2. **Common Pitfall**: Forgetting to handle edge cases like empty lists or single-node lists.
3. **Alternative Approach**: Using a stack to store the first half and comparing it with the second half (O(n) space).
4. **Optimization Discussion**: Discuss the trade-offs between time and space complexity.

---

# ✅ Conclusion

The **optimal approach** efficiently checks if a linked list is a palindrome by:
- Using the slow-fast pointer technique to find the middle.
- Reversing the second half in-place to avoid extra space.
- Comparing the first half with the reversed second half.

This approach achieves **O(n) time** and **O(1) space**, making it suitable for large input sizes and ideal for technical interviews. The key insight is leveraging in-place reversal to optimize space while maintaining linear time complexity.