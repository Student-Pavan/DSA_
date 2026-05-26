# 📌 Binary Tree Postorder Traversal

---

# 📝 Problem Statement

Given the `root` of a binary tree, return the **postorder traversal** of its nodes' values.

**Postorder Traversal Order**: Left → Right → Root

### Input:
- `root`: The root node of a binary tree (`TreeNode`)

### Output:
- `List<Integer>`: A list containing the node values in postorder traversal order

### Constraints:
- The number of nodes in the tree is in the range `[0, 100]`.
- `-100 <= Node.val <= 100`

### Example:
```
Input: root = [1,null,2,3]
Output: [3,2,1]
Explanation:
    1
     \
      2
     /
    3
Postorder: Left → Right → Root → [3,2,1]
```

---

# 💡 Intuition

Postorder traversal visits nodes in the order: **Left subtree → Right subtree → Root node**. This is a classic depth-first traversal pattern.

The key insight is that we must process both subtrees completely before processing the root. This naturally lends itself to **recursion** due to the tree's recursive structure. However, recursion uses implicit stack space, which may not be optimal for very deep trees.

An **iterative approach** using an explicit stack allows us to avoid recursion and manage memory more efficiently, especially in interview settings where recursion depth might be a concern.

---

# 🐌 Brute Force Approach

## 🔹 Approach

The brute force approach uses **recursion** to perform postorder traversal. This is the most straightforward method:
1. Traverse the left subtree recursively.
2. Traverse the right subtree recursively.
3. Visit the root node.

This approach leverages the call stack to keep track of nodes, making it simple but potentially inefficient in terms of space for very deep trees.

---

## 🔹 Algorithm

1. If the current node is `null`, return.
2. Recursively traverse the left subtree.
3. Recursively traverse the right subtree.
4. Add the current node's value to the result list.

---

## 🔹 Code

```java
import java.util.ArrayList;
import java.util.List;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        postorderHelper(root, result);
        return result;
    }

    private void postorderHelper(TreeNode node, List<Integer> result) {
        if (node == null) {
            return;
        }
        postorderHelper(node.left, result);   // Traverse left subtree
        postorderHelper(node.right, result);  // Traverse right subtree
        result.add(node.val);                 // Visit root
    }
}
```

---

## 🔹 Dry Run

**Input Tree**:
```
    1
     \
      2
     /
    3
```

**Dry Run Table**:

| Step | Node Visited | Action | Result List |
|------|--------------|--------|-------------|
| 1    | 1            | Call `postorderHelper(1)` | [] |
| 2    | 1 → left (null) | Return from left | [] |
| 3    | 1 → right (2) | Call `postorderHelper(2)` | [] |
| 4    | 2 → left (3)  | Call `postorderHelper(3)` | [] |
| 5    | 3 → left (null) | Return from left | [] |
| 6    | 3 → right (null) | Return from right | [] |
| 7    | 3             | Add 3 to result | [3] |
| 8    | 2 → right (null) | Return from right | [3] |
| 9    | 2             | Add 2 to result | [3, 2] |
| 10   | 1             | Add 1 to result | [3, 2, 1] |

**Final Result**: `[3, 2, 1]`

---

## 🔹 Complexity Analysis

| Complexity | Value |
|-----------|-------|
| Time Complexity | O(n) |
| Space Complexity | O(h) |

> **n**: Number of nodes in the tree
> **h**: Height of the tree (worst case: O(n) for skewed tree)

---

# ⚡ Optimal Approach

## 🔹 Approach

The optimal approach uses an **iterative method with a stack** to simulate the postorder traversal. This avoids recursion and potential stack overflow issues.

However, postorder is tricky iteratively because we need to process root **after** both subtrees. We can use **two stacks** or a **single stack with clever ordering**.

Here, we use **two stacks**:
1. Push root onto `stack1`.
2. While `stack1` is not empty:
   - Pop from `stack1` and push to `stack2`.
   - Push left child, then right child to `stack1`.
3. The result is the reverse of `stack2`'s order.

This works because `stack2` collects nodes in **Root → Right → Left** order, and reversing gives **Left → Right → Root**.

---

## 🔹 Why This Works

- `stack1` processes nodes in **preorder-like** fashion (Root → Left → Right).
- By pushing left before right, we ensure right is processed first in `stack2`.
- `stack2` collects nodes in **Root → Right → Left**.
- Popping from `stack2` gives **Left → Right → Root**, which is postorder.

This approach efficiently avoids recursion and handles all cases in O(n) time and space.

---

## 🔹 Algorithm

1. If `root` is `null`, return empty list.
2. Initialize two stacks: `stack1` and `stack2`.
3. Push `root` onto `stack1`.
4. While `stack1` is not empty:
   - Pop a node from `stack1` and push it onto `stack2`.
   - Push left child of the node onto `stack1` (if exists).
   - Push right child of the node onto `stack1` (if exists).
5. Pop all nodes from `stack2` and add to result list.

---

## 🔹 Code

```java
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Stack<TreeNode> stack1 = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();
        stack1.push(root);

        while (!stack1.isEmpty()) {
            TreeNode node = stack1.pop();
            stack2.push(node);

            // Push left first, then right so that right is processed first in stack2
            if (node.left != null) {
                stack1.push(node.left);
            }
            if (node.right != null) {
                stack1.push(node.right);
            }
        }

        // Pop from stack2 to get postorder
        while (!stack2.isEmpty()) {
            result.add(stack2.pop().val);
        }

        return result;
    }
}
```

---

## 🔹 Detailed Dry Run

**Input Tree**:
```
    1
     \
      2
     /
    3
```

**Dry Run Table**:

| Step | stack1 (top → bottom) | stack2 (top → bottom) | Action | Result |
|------|------------------------|------------------------|--------|--------|
| 1    | [1]                    | []                     | Push 1 to stack1 | [] |
| 2    | []                     | [1]                    | Pop 1 → push to stack2 | [] |
| 3    | [2]                    | [1]                    | Push 1's right (2) | [] |
| 4    | [2,3]                  | [1]                    | Push 1's left (null) → skip; then 2's left (3) | [] |
| 5    | [2]                    | [1,3]                  | Pop 3 → push to stack2 | [] |
| 6    | [2,null]               | [1,3]                  | Push 3's left (null) → skip | [] |
| 7    | [2]                    | [1,3]                  | Push 3's right (null) → skip | [] |
| 8    | []                     | [1,3,2]                | Pop 2 → push to stack2 | [] |
| 9    | [null]                 | [1,3,2]                | Push 2's left (null) → skip | [] |
| 10   | []                     | [1,3,2]                | Push 2's right (null) → skip | [] |
| 11   | []                     | []                     | Pop from stack2: 2, 3, 1 → add to result | [3,2,1] |

**Final Result**: `[3, 2, 1]`

---

## 🔹 Complexity Analysis

| Complexity | Value |
|-----------|-------|
| Time Complexity | O(n) |
| Space Complexity | O(n) |

> **n**: Number of nodes in the tree

---

# 🔍 Edge Cases

| Edge Case | Expected Output | Explanation |
|---------|------------------|-----------|
| Empty tree (`root = null`) | `[]` | No nodes to traverse |
| Single node (`root = [1]`) | `[1]` | Only root exists |
| Left-skewed tree (`[1,2,null,null,3]`) | `[3,2,1]` | All nodes on left |
| Right-skewed tree (`[1,null,2,null,3]`) | `[3,2,1]` | All nodes on right |
| Full binary tree (`[1,2,3,4,5,6,7]`) | `[4,5,2,6,7,3,1]` | Balanced tree |
| All nodes same value (`[1,1,1]`) | `[1,1,1]` | Duplicates allowed |

---

# 📚 Key Takeaways

- **Postorder traversal** follows: **Left → Right → Root**.
- **Recursion** is simple but uses implicit stack space.
- **Iterative approach** with two stacks avoids recursion and is more memory-efficient in practice.
- **Stack ordering** is crucial: push left before right to ensure right is processed first in the second stack.
- **Time complexity** is always O(n) for traversal.
- **Space complexity** depends on tree height (O(h)) for recursion, O(n) for iterative.

---

# 🚀 Interview Tips

- **Follow-up**: Can you do it with **one stack**? (Hint: Use a `prev` pointer to track last visited node.)
- **Common Pitfall**: Forgetting to push left before right in the iterative approach.
- **Alternative**: Reverse of **preorder (Root → Right → Left)** gives postorder.
- **Optimization Insight**: Iterative methods are preferred in interviews for large trees to avoid stack overflow.
- **Visualization**: Always draw the tree and trace the traversal order.

---

# ✅ Conclusion

The **iterative two-stack approach** is optimal for postorder traversal in interview settings. It avoids recursion, handles deep trees gracefully, and maintains clarity. While both recursive and iterative methods have O(n) time complexity, the iterative version is more robust and preferred in production environments.

**Key Insight**: Postorder can be derived by reversing a modified preorder traversal (Root → Right → Left). This duality between traversal orders is a powerful pattern in tree algorithms.