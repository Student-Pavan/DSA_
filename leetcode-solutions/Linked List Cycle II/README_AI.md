```markdown
# 🔗 Linked List Cycle II

---

# 📝 Problem Statement

Given the head of a linked list, return the node where the cycle begins. If there is no cycle, return `null`.

**Objective:**
- Detect the presence of a cycle in a linked list.
- Identify the starting node of the cycle if it exists.

**Input:**
- `head`: The head node of a singly linked list.

**Output:**
- The node where the cycle begins, or `null` if no cycle exists.

**Constraints:**
- The number of nodes in the list is in the range `[0, 10^4]`.
- `-10^5 <= Node.val <= 10^5`
- `pos` is `-1` or a valid index in the linked list (used to denote the tail's `next` pointer in LeetCode's representation).

---

# 💡 Intuition

The problem requires detecting the start of a cycle in a linked list. The key insight is recognizing that if a cycle exists, a fast pointer (moving two steps at a time) will eventually meet a slow pointer (moving one step at a time) inside the cycle. Once they meet, we can use mathematical reasoning to find the cycle's starting node by resetting one pointer to the head and moving both pointers one step at a time until they meet again.

This approach leverages Floyd's Tortoise and Hare algorithm, which is efficient in both time and space.

---

# 🐌 Brute Force Approach

## 🔹 Approach

The brute force approach involves using a hash set to keep track of visited nodes. As we traverse the linked list, we check if the current node has been visited before. If it has, we return the node as the start of the cycle. If we reach the end of the list without finding a cycle, we return `null`.

**Key Idea:**
- Use a hash set to store visited nodes.
- Traverse the list and check for duplicates in the set.

---

## 🔹 Algorithm

1. Initialize an empty hash set to store visited nodes.
2. Traverse the linked list starting from the head.
3. For each node:
   - If the node is already in the set, return the node (cycle detected).
   - Otherwise, add the node to the set.
4. If the traversal completes without finding a cycle, return `null`.

---

## 🔹 Code

```java
import java.util.HashSet;
import java.util.Set;

class Solution {
    public ListNode detectCycle(ListNode head) {
        Set<ListNode> visited = new HashSet<>();
        ListNode current = head;

        while (current != null) {
            if (visited.contains(current)) {
                return current;
            }
            visited.add(current);
            current = current.next;
        }

        return null;
    }
}
```

---

## 🔹 Dry Run

**Example:**
Linked List: `3 -> 2 -> 0 -> -4 -> (points back to 2)`
Cycle starts at node with value `2`.

| Step | Current Node | Visited Set (Node Values) | Action                     |
|------|--------------|---------------------------|----------------------------|
| 1    | 3            | {3}                       | Add 3 to set               |
| 2    | 2            | {3, 2}                    | Add 2 to set               |
| 3    | 0            | {3, 2, 0}                 | Add 0 to set               |
| 4    | -4           | {3, 2, 0, -4}             | Add -4 to set              |
| 5    | 2            | {3, 2, 0, -4}             | 2 already in set → return 2|

---

## 🔹 Complexity Analysis

| Complexity      | Value       |
|-----------------|-------------|
| Time Complexity | O(n)        |
| Space Complexity| O(n)        |

---

# ⚡ Optimal Approach

## 🔹 Approach

The optimal approach uses Floyd's Tortoise and Hare algorithm to detect the cycle and find its starting node. The algorithm involves two phases:

1. **Cycle Detection:** Use two pointers, `slow` (moves one step at a time) and `fast` (moves two steps at a time). If they meet, a cycle exists.
2. **Finding the Start:** Reset one pointer to the head and move both pointers one step at a time until they meet again. The meeting point is the start of the cycle.

**Key Insight:**
- The distance from the head to the cycle start is equal to the distance from the meeting point to the cycle start when moving around the cycle.

---

## 🔹 Why This Works

When the `slow` and `fast` pointers meet, the `slow` pointer has traveled `d + p` steps, where `d` is the distance from the head to the cycle start, and `p` is the distance from the cycle start to the meeting point. The `fast` pointer has traveled `d + p + k * c` steps, where `c` is the cycle length and `k` is an integer. Since the `fast` pointer moves twice as fast, we have:

`2 * (d + p) = d + p + k * c`

Simplifying, we get:

`d = k * c - p`

This means the distance from the head to the cycle start (`d`) is equal to the distance from the meeting point to the cycle start when moving around the cycle (`k * c - p`).

---

## 🔹 Algorithm

1. Initialize `slow` and `fast` pointers at the head.
2. Move `slow` one step and `fast` two steps at a time until they meet or `fast` reaches the end.
3. If `fast` reaches the end, return `null` (no cycle).
4. Reset `slow` to the head and move both `slow` and `fast` one step at a time until they meet.
5. Return the meeting node (start of the cycle).

---

## 🔹 Code

```java
class Solution {
    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }

        ListNode slow = head;
        ListNode fast = head;

        // Phase 1: Detect cycle
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) {
                break; // Cycle detected
            }
        }

        // No cycle
        if (fast == null || fast.next == null) {
            return null;
        }

        // Phase 2: Find cycle start
        slow = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }

        return slow;
    }
}
```

---

## 🔹 Detailed Dry Run

**Example:**
Linked List: `3 -> 2 -> 0 -> -4 -> (points back to 2)`
Cycle starts at node with value `2`.

### Phase 1: Cycle Detection

| Step | Slow | Fast | Action                     |
|------|------|------|----------------------------|
| 1    | 3    | 3    | Initialize                 |
| 2    | 2    | 0    | Move slow (1), fast (2)    |
| 3    | 0    | 2    | Move slow (1), fast (2)    |
| 4    | -4   | -4   | Move slow (1), fast (2)    |
| 5    | 2    | 0    | Move slow (1), fast (2)    |
| 6    | 0    | -4   | Move slow (1), fast (2)    |
| 7    | -4   | 2    | Move slow (1), fast (2)    |
| 8    | 2    | 0    | **Slow and fast meet at 2**|

### Phase 2: Find Cycle Start

| Step | Slow | Fast | Action                     |
|------|------|------|----------------------------|
| 1    | 3    | 2    | Reset slow to head         |
| 2    | 2    | 0    | Move both one step         |
| 3    | 0    | -4   | Move both one step         |
| 4    | 2    | 2    | **Slow and fast meet at 2**|

---

## 🔹 Complexity Analysis

| Complexity      | Value       |
|-----------------|-------------|
| Time Complexity | O(n)        |
| Space Complexity| O(1)        |

---

# 🔍 Edge Cases

- **Empty List:** `head = null` → Return `null`.
- **Single Node, No Cycle:** `1 -> null` → Return `null`.
- **Single Node, Cycle:** `1 -> (points to itself)` → Return node `1`.
- **Large List:** List with `10^4` nodes → Ensure algorithm handles efficiently.
- **Cycle at Head:** `1 -> 2 -> 3 -> (points to 1)` → Return node `1`.
- **No Cycle:** `1 -> 2 -> 3 -> null` → Return `null`.

---

# 📚 Key Takeaways

1. **Floyd's Algorithm:** Efficiently detects cycles and finds the cycle start in O(n) time and O(1) space.
2. **Two-Pointer Technique:** Useful for cycle detection and other linked list problems.
3. **Mathematical Insight:** The distance from the head to the cycle start equals the distance from the meeting point to the cycle start when moving around the cycle.
4. **Edge Cases:** Always consider empty lists, single nodes, and cycles at the head or tail.

---

# 🚀 Interview Tips

- **Follow-Up:** Can you solve this problem without extra space? (Answer: Yes, using Floyd's algorithm.)
- **Common Pitfall:** Forgetting to handle edge cases like an empty list or a single node.
- **Alternative Approach:** Using a hash set is straightforward but uses O(n) space.
- **Optimization Discussion:** Floyd's algorithm is optimal for both time and space complexity.
- **Visualization:** Drawing the linked list and cycle helps in understanding the pointer movements.

---

# ✅ Conclusion

The optimal solution using Floyd's Tortoise and Hare algorithm is preferred due to its O(n) time complexity and O(1) space complexity. The key insight is leveraging the mathematical relationship between the distances traveled by the slow and fast pointers to pinpoint the cycle's starting node. This approach is both efficient and elegant, making it a valuable tool for solving cycle detection problems in linked lists.
```