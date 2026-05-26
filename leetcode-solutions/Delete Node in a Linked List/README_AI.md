# 📌 Delete Node in a Linked List

---

# 📝 Problem Statement

There is a singly-linked list `head`, and we want to delete a node `node` in it.

You are given the node to be deleted `node`. You will **not** be given access to the first node of the linked list, `head`.

All the values of the linked list are **unique**, and it is guaranteed that the given node `node` is not the last node in the linked list.

**Objective:**
Delete the given node from the linked list.

**Input:**
- `node`: The node to be deleted (not the head, not the tail)

**Output:**
- No explicit return value. The linked list should be modified in-place.

**Constraints:**
- The number of nodes in the list is in the range `[2, 1000]`.
- `-1000 <= Node.val <= 1000`
- The value of each node in the list is **unique**.
- The given `node` is **not** the tail.

---

# 💡 Intuition

In a typical linked list deletion, we need access to the **previous node** of the node to be deleted so we can update its `next` pointer. However, in this problem, we **only have access to the node to be deleted**, not the head or previous node.

The key insight is that we **cannot delete the node directly** (since we don’t have access to its predecessor), but we **can copy the value from the next node** into the current node and then **delete the next node** instead. This effectively removes the current node’s value from the list while preserving the structure.

This approach works because:
- The problem guarantees the node is **not the tail**, so `node.next` exists.
- All values are unique, so copying a value doesn’t introduce duplicates.
- We are allowed to modify the list in-place.

---

# 🐌 Brute Force Approach

## 🔹 Approach

Since we don’t have access to the head, we cannot traverse from the beginning to find the previous node. However, if we **assume we could traverse from the head**, we could find the previous node and update its `next` pointer to skip the given node.

But this approach **violates the problem constraints** because:
- We are **not given the head**.
- It requires traversing the entire list, leading to O(n) time.

While this is not feasible under the problem constraints, it’s included for educational purposes to contrast with the optimal solution.

---

## 🔹 Algorithm

1. Traverse from the head to find the node **before** the given node.
2. Update the previous node’s `next` pointer to skip the given node.
3. Delete the given node.

> ⚠️ **Note:** This approach is **not valid** for this problem due to missing `head`. It is shown only for conceptual completeness.

---

## 🔹 Code

```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public void deleteNode(ListNode node) {
        // This brute force approach is INVALID for the problem
        // because we don't have access to head.
        // It is included only for educational comparison.

        // We cannot implement this without head.
        // So we skip this and show the optimal approach below.
        throw new UnsupportedOperationException("Cannot delete node without head access.");
    }
}
```

---

## 🔹 Dry Run

Since this approach **cannot be implemented** under the problem constraints, a dry run is not applicable.

---

## 🔹 Complexity Analysis

| Complexity | Value |
|-----------|-------|
| Time Complexity | O(n) — requires traversal from head |
| Space Complexity | O(1) |

> ❌ This approach is **invalid** for the given problem.

---

# ⚡ Optimal Approach

## 🔹 Approach

Instead of trying to delete the given node directly (which requires access to its predecessor), we **copy the value from the next node** into the current node, then **delete the next node**.

This achieves the same result: the value of the given node is removed from the list, and the structure remains valid.

### Why This Works:
- We are allowed to modify node values.
- The next node exists (guaranteed not tail).
- Copying the next node’s value into the current node effectively "replaces" the current node.
- Then we skip and delete the next node.

This is an **O(1) time and space** solution.

---

## 🔹 Why This Works

- The problem only requires **removing the value** of the given node, not necessarily the node object itself.
- By copying the next node’s value, we simulate deletion without needing the previous node.
- The next node is then removed, maintaining the list integrity.
- This is a clever **in-place modification** that bypasses the need for head access.

---

## 🔹 Algorithm

1. Copy the value from `node.next` into `node`.
2. Set `node.next = node.next.next`.
3. The original `node.next` is now effectively removed.

---

## 🔹 Code

```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public void deleteNode(ListNode node) {
        // Copy the value from the next node
        node.val = node.next.val;

        // Skip the next node
        node.next = node.next.next;
    }
}
```

---

## 🔹 Detailed Dry Run

**Example:**
List: `4 → 5 → 1 → 9`
Node to delete: `5` (i.e., `node` points to the node with value `5`)

| Step | Action | List State |
|------|--------|------------|
| 1 | Start | `4 → 5 → 1 → 9` |
| 2 | `node.val = node.next.val` → `5 = 1` | `4 → 1 → 1 → 9` |
| 3 | `node.next = node.next.next` → skip second `1` | `4 → 1 → 9` |

**Result:** The list becomes `4 → 1 → 9`, effectively removing the original `5`.

---

## 🔹 Complexity Analysis

| Complexity | Value |
|-----------|-------|
| Time Complexity | **O(1)** — constant time operations |
| Space Complexity | **O(1)** — no extra space used |

---

# 🔍 Edge Cases

| Edge Case | Explanation |
|---------|-------------|
| Node is the second last node | Valid — guaranteed not tail, so `node.next` exists |
| List has exactly 2 nodes | Valid — delete the first (non-head) node |
| All values are unique | Ensures no ambiguity after value copy |
| Large list (1000 nodes) | Still O(1) time — efficient |

> ❌ **Invalid Edge Case (per constraints):** Node is the tail — **not possible** per problem guarantee.

---

# 📚 Key Takeaways

- You **don’t always need access to the head** to modify a linked list.
- **Copying values** can simulate deletion when direct removal is impossible.
- **Constraints matter** — use them to simplify the problem (e.g., not tail, unique values).
- **In-place modification** is powerful in linked lists.
- This problem teaches **indirect deletion** — a key pattern in linked list manipulation.

---

# 🚀 Interview Tips

### ✅ Common Follow-up Questions:
- *What if the node is the tail?* → Not possible per problem, but in real life, you’d need to handle it (return false, throw exception, etc.).
- *What if values are not unique?* → Copying could cause duplicates; would need a different approach.
- *Can you delete a node without modifying its value?* → No, unless you have access to the previous node.

### ⚠️ Common Pitfalls:
- Trying to find the previous node — impossible without head.
- Forgetting to update `next` pointer after copying value.
- Assuming you can delete the node object — in Java, you can’t truly delete an object, but you can remove it from the list.

### 🔁 Alternative Approach:
If allowed to modify the `ListNode` class, you could add a `prev` pointer to make deletion O(1) — but that changes the data structure.

---

# ✅ Conclusion

The optimal solution to **Delete Node in a Linked List** leverages a clever **value copy and skip** technique to achieve O(1) time and space complexity. It bypasses the need for head access by **replacing the node’s value** and **removing the next node**, effectively deleting the target in-place.

This problem highlights the importance of **working within constraints** and **thinking creatively** about linked list manipulation. It’s a classic example of how **indirect modification** can solve seemingly impossible problems.

> 💡 **Key Insight:** Sometimes, you don’t delete the node — you **replace its value and delete the next one**.