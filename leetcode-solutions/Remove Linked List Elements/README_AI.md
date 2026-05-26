# 📌 Remove Linked List Elements

---

# 📝 Problem Statement

Given the `head` of a linked list and an integer `val`, remove all nodes from the linked list that have `Node.val == val`, and return the **new head** of the modified linked list.

### **Objective**
Remove all occurrences of nodes with the given value from the linked list while maintaining the relative order of the remaining nodes.

### **Input**
- `head`: The head node of a singly linked list.
- `val`: The integer value to be removed from the linked list.

### **Output**
- The head of the modified linked list after removal.

### **Constraints**
- The number of nodes in the list is in the range `[0, 10⁴]`.
- `1 <= Node.val <= 50`
- `0 <= val <= 50`

---

# 💡 Intuition

The problem requires traversing a linked list and removing nodes that match a given value. The key challenge is handling edge cases, such as removing the head node or consecutive nodes with the target value.

A **dummy node** can simplify edge cases by providing a consistent starting point before the actual head. This avoids special logic for head removal and ensures uniform traversal.

---

# 🐌 Brute Force Approach

## 🔹 Approach
The brute force approach involves:
1. **Traversing the linked list** while checking each node's value.
2. **Removing nodes** that match the target value by adjusting the `next` pointer of the previous node.
3. **Handling edge cases** (e.g., empty list, removing the head node) explicitly.

This approach requires careful pointer manipulation to avoid null pointer exceptions.

---

## 🔹 Algorithm
1. Handle the case where the list is empty.
2. If the head node matches the target value, remove it and update the head.
3. Traverse the list while checking the next node's value.
4. If the next node matches the target value, skip it by adjusting the current node's `next` pointer.
5. Return the modified head.

---

## 🔹 Code

```java
class Solution {
    public ListNode removeElements(ListNode head, int val) {
        // Handle empty list
        if (head == null) {
            return null;
        }

        // Remove leading nodes with target value
        while (head != null && head.val == val) {
            head = head.next;
        }

        ListNode current = head;
        while (current != null && current.next != null) {
            if (current.next.val == val) {
                current.next = current.next.next;
            } else {
                current = current.next;
            }
        }
        return head;
    }
}
```

---

## 🔹 Dry Run

**Input:** `head = [1, 2, 6, 3, 4, 5, 6]`, `val = 6`

| Step | Current Node | Action | List State |
|------|--------------|--------|------------|
| 1    | `1`          | Check `1 != 6` → move to `2` | `[1, 2, 6, 3, 4, 5, 6]` |
| 2    | `2`          | Check `2 != 6` → move to `6` | `[1, 2, 6, 3, 4, 5, 6]` |
| 3    | `6`          | `6 == 6` → skip `6` | `[1, 2, 3, 4, 5, 6]` |
| 4    | `2`          | Now points to `3` → move to `3` | `[1, 2, 3, 4, 5, 6]` |
| 5    | `3`          | Check `3 != 6` → move to `4` | `[1, 2, 3, 4, 5, 6]` |
| 6    | `4`          | Check `4 != 6` → move to `5` | `[1, 2, 3, 4, 5, 6]` |
| 7    | `5`          | Check `5 != 6` → move to `6` | `[1, 2, 3, 4, 5, 6]` |
| 8    | `6`          | `6 == 6` → skip `6` | `[1, 2, 3, 4, 5]` |

**Final Output:** `[1, 2, 3, 4, 5]`

---

## 🔹 Complexity Analysis

| Complexity      | Value       |
|-----------------|-------------|
| Time Complexity | O(n)        |
| Space Complexity| O(1)        |

---

# ⚡ Optimal Approach

## 🔹 Approach
The optimal approach uses a **dummy node** to simplify edge cases:
1. **Create a dummy node** that points to the head of the list.
2. **Traverse the list** while checking the next node's value.
3. **Skip nodes** that match the target value by adjusting the `next` pointer.
4. **Return `dummy.next`**, which is the new head of the modified list.

This avoids special logic for head removal and ensures uniform traversal.

---

## 🔹 Why This Works
- The dummy node acts as a stable starting point, eliminating the need to handle head removal separately.
- The traversal logic remains consistent, reducing code complexity.
- It efficiently handles all edge cases (empty list, consecutive removals, etc.).

---

## 🔹 Algorithm
1. Create a dummy node and set its `next` to `head`.
2. Initialize a `current` pointer to the dummy node.
3. While `current.next` is not null:
   - If `current.next.val == val`, skip `current.next` by setting `current.next = current.next.next`.
   - Otherwise, move `current` to `current.next`.
4. Return `dummy.next`.

---

## 🔹 Code

```java
class Solution {
    public ListNode removeElements(ListNode head, int val) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode current = dummy;

        while (current.next != null) {
            if (current.next.val == val) {
                current.next = current.next.next;
            } else {
                current = current.next;
            }
        }
        return dummy.next;
    }
}
```

---

## 🔹 Detailed Dry Run

**Input:** `head = [1, 2, 6, 3, 4, 5, 6]`, `val = 6`

| Step | Current Node | Action | List State |
|------|--------------|--------|------------|
| 1    | `dummy`      | Check `1 != 6` → move to `1` | `[1, 2, 6, 3, 4, 5, 6]` |
| 2    | `1`          | Check `2 != 6` → move to `2` | `[1, 2, 6, 3, 4, 5, 6]` |
| 3    | `2`          | `6 == 6` → skip `6` | `[1, 2, 3, 4, 5, 6]` |
| 4    | `2`          | Now points to `3` → move to `3` | `[1, 2, 3, 4, 5, 6]` |
| 5    | `3`          | Check `4 != 6` → move to `4` | `[1, 2, 3, 4, 5, 6]` |
| 6    | `4`          | Check `5 != 6` → move to `5` | `[1, 2, 3, 4, 5, 6]` |
| 7    | `5`          | `6 == 6` → skip `6` | `[1, 2, 3, 4, 5]` |

**Final Output:** `[1, 2, 3, 4, 5]`

---

## 🔹 Complexity Analysis

| Complexity      | Value       |
|-----------------|-------------|
| Time Complexity | O(n)        |
| Space Complexity| O(1)        |

---

# 🔍 Edge Cases

| Edge Case | Description | Expected Output |
|-----------|-------------|-----------------|
| Empty list | `head = []`, `val = 1` | `[]` |
| Single node (match) | `head = [1]`, `val = 1` | `[]` |
| Single node (no match) | `head = [1]`, `val = 2` | `[1]` |
| All nodes match | `head = [1, 1, 1]`, `val = 1` | `[]` |
| No nodes match | `head = [1, 2, 3]`, `val = 4` | `[1, 2, 3]` |
| Consecutive matches | `head = [1, 2, 2, 3]`, `val = 2` | `[1, 3]` |
| Remove head | `head = [1, 2, 3]`, `val = 1` | `[2, 3]` |

---

# 📚 Key Takeaways

1. **Dummy Node Technique**: Simplifies edge cases by providing a stable starting point.
2. **Pointer Manipulation**: Adjusting `next` pointers is key to removing nodes without breaking the list.
3. **Uniform Traversal**: The optimal approach ensures consistent logic for all nodes.
4. **Edge Case Handling**: Always consider empty lists, single nodes, and consecutive removals.

---

# 🚀 Interview Tips

1. **Follow-Up Questions**:
   - How would you handle a doubly linked list?
   - Can you solve this recursively?
   - What if the list is circular?

2. **Common Pitfalls**:
   - Forgetting to handle the head node separately (brute force).
   - Not updating the `next` pointer correctly, leading to lost nodes.
   - Off-by-one errors in traversal.

3. **Alternative Approaches**:
   - **Recursive Solution**: Elegant but may cause stack overflow for large lists.
   - **Two-Pointer Technique**: Useful for in-place modifications but not necessary here.

4. **Optimization Insight**:
   - The dummy node eliminates the need for special head-handling logic, reducing code complexity.

---

# ✅ Conclusion

The **optimal approach** using a dummy node is preferred because:
- It simplifies edge case handling.
- It ensures uniform traversal logic.
- It maintains O(n) time and O(1) space complexity.

**Key Insight**: The dummy node acts as a "sentinel" that streamlines the removal process, making the solution both efficient and easy to understand. This technique is widely applicable in linked list problems involving node removal.