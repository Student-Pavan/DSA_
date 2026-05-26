# 📌 Binary Tree Preorder Traversal

---

# 📝 Problem Statement

Given the `root` of a binary tree, return the **preorder traversal** of its nodes' values.

**Preorder traversal** follows the order: **Root → Left → Right**.

### Input:
- `root`: The root node of a binary tree (`TreeNode` class).

### Output:
- A list of integers representing the preorder traversal of the tree.

### Constraints:
- The number of nodes in the tree is in the range `[0, 100]`.
- `-100 <= Node.val <= 100`

---

# 💡 Intuition

Preorder traversal is a fundamental tree traversal technique where we visit the root node first, followed by the left subtree, and then the right subtree. This approach naturally follows a depth-first search (DFS) pattern.

The key insight is that the traversal order is **root-first**, meaning we must process the current node before exploring its children. This can be achieved using either **recursion** (which leverages the call stack) or **iteration** (using an explicit stack to simulate recursion).

---

# 🐌 Brute Force Approach

## 🔹 Approach

The brute force approach uses **recursion** to traverse the tree in preorder. Recursion is a natural fit for tree traversals because it inherently follows the tree's hierarchical structure. The base case checks if the current node is `null`, and if not, we process the node, then recursively traverse the left and right subtrees.

---

## 🔹 Algorithm

1. **Base Case**: If the current node is `null`, return.
2. **Process Node**: Add the current node's value to the result list.
3. **Recurse Left**: Traverse the left subtree recursively.
4. **Recurse Right**: Traverse the right subtree recursively.

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
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        preorderHelper(root, result);
        return result;
    }

    private void preorderHelper(TreeNode node, List<Integer> result) {
        if (node == null) {
            return;
        }
        result.add(node.val);          // Process the current node
        preorderHelper(node.left, result);  // Traverse left subtree
        preorderHelper(node.right, result); // Traverse right subtree
    }
}
```

---

## 🔹 Dry Run

Consider the following binary tree:

```
      1
       \
        2
       /
      3
```

**Step-by-Step Execution:**

| Step | Node Visited | Action                     | Result List       |
|------|--------------|----------------------------|-------------------|
| 1    | 1            | Add 1 to result            | [1]               |
| 2    | 1 → left (null) | Return (base case)        | [1]               |
| 3    | 1 → right (2)  | Add 2 to result            | [1, 2]            |
| 4    | 2 → left (3)   | Add 3 to result            | [1, 2, 3]         |
| 5    | 3 → left (null) | Return (base case)        | [1, 2, 3]         |
| 6    | 3 → right (null) | Return (base case)        | [1, 2, 3]         |
| 7    | 2 → right (null) | Return (base case)        | [1, 2, 3]         |

**Final Result:** `[1, 2, 3]`

---

## 🔹 Complexity Analysis

| Complexity       | Value       |
|------------------|-------------|
| Time Complexity  | O(n)        |
| Space Complexity | O(h)        |

- **Time Complexity**: O(n), where `n` is the number of nodes in the tree. Each node is visited exactly once.
- **Space Complexity**: O(h), where `h` is the height of the tree. This accounts for the recursion stack. In the worst case (skewed tree), `h = n`.

---

# ⚡ Optimal Approach

## 🔹 Approach

The optimal approach uses an **iterative method with an explicit stack** to simulate the recursive preorder traversal. This avoids the overhead of recursion and is more efficient in languages where recursion depth is limited. The stack helps keep track of nodes to visit, and we process nodes in the order: **current node → right child → left child** (since stacks are LIFO).

---

## 🔹 Why This Works

The iterative approach mimics the recursive call stack but uses an explicit stack to manage node traversal. By pushing the right child before the left child, we ensure that the left child is processed first (due to LIFO order), maintaining the **Root → Left → Right** sequence.

---

## 🔹 Algorithm

1. **Initialize**: An empty stack and an empty result list.
2. **Push Root**: If the root is not `null`, push it onto the stack.
3. **Process Stack**:
   - Pop the top node from the stack.
   - Add its value to the result list.
   - Push the right child onto the stack (if it exists).
   - Push the left child onto the stack (if it exists).
4. **Terminate**: When the stack is empty, return the result list.

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
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            result.add(node.val);  // Process the current node

            // Push right child first (so left is processed first)
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }

        return result;
    }
}
```

---

## 🔹 Detailed Dry Run

**Tree:**
```
      1
       \
        2
       /
      3
```

**Stack and Result State:**

| Step | Stack (Top → Bottom) | Action                     | Result List       |
|------|----------------------|----------------------------|-------------------|
| 1    | [1]                  | Pop 1, add to result       | [1]               |
| 2    | [2] (push right, then left) | Push 2 (right), null (left) | [1]               |
| 3    | [2]                  | Pop 2, add to result       | [1, 2]            |
| 4    | [3] (push right, then left) | Push null (right), 3 (left) | [1, 2]            |
| 5    | [3]                  | Pop 3, add to result       | [1, 2, 3]         |
| 6    | [] (push right, then left) | Push null (right), null (left) | [1, 2, 3]         |

**Final Result:** `[1, 2, 3]`

---

## 🔹 Complexity Analysis

| Complexity       | Value       |
|------------------|-------------|
| Time Complexity  | O(n)        |
| Space Complexity | O(n)        |

- **Time Complexity**: O(n), as each node is visited exactly once.
- **Space Complexity**: O(n), in the worst case (skewed tree), the stack may hold all nodes.

---

# 🔍 Edge Cases

| Edge Case                     | Expected Output          |
|-------------------------------|--------------------------|
| Empty tree (`root = null`)    | `[]`                     |
| Single node tree              | `[root.val]`             |
| Left-skewed tree              | `[root, left, left.left, ...]` |
| Right-skewed tree             | `[root, right, right.right, ...]` |
| Full binary tree              | `[root, left, right, ...]` (preorder) |
| Tree with negative values     | `[root.val, ...]` (correct order) |

---

# 📚 Key Takeaways

1. **Preorder Traversal**: Always process the root before its children (`Root → Left → Right`).
2. **Recursion vs Iteration**:
   - Recursion is intuitive but may cause stack overflow for very deep trees.
   - Iteration is more efficient and avoids recursion limits.
3. **Stack Usage**: In iterative preorder, push the right child before the left to ensure left is processed first.
4. **Time Complexity**: Both approaches are O(n), but iterative may have better constant factors.

---

# 🚀 Interview Tips

1. **Follow-Up Questions**:
   - How would you implement **inorder** or **postorder** traversal iteratively?
   - Can you modify the solution to handle **n-ary trees**?
   - How would you traverse the tree **level by level (BFS)**?

2. **Common Pitfalls**:
   - Forgetting to handle the `null` root case.
   - Pushing left child before right in the iterative approach (breaks preorder).
   - Incorrectly assuming recursion is always worse than iteration.

3. **Alternative Approaches**:
   - **Morris Traversal**: A space-optimized O(1) approach (advanced, modifies tree temporarily).
   - **Threaded Trees**: Another space-efficient method (less common).

4. **Optimization Discussions**:
   - For very deep trees, iterative is preferred to avoid stack overflow.
   - Recursion is often cleaner and easier to debug for small trees.

---

# ✅ Conclusion

The **iterative approach** is the optimal solution for preorder traversal, especially in interviews, as it demonstrates a deeper understanding of stack usage and avoids recursion limits. However, the **recursive approach** remains a valuable tool for its simplicity and readability.

**Key Insight**: Preorder traversal is inherently a depth-first search (DFS) pattern, and the stack (whether implicit or explicit) is the natural data structure to manage node processing order.

**Final Takeaway**: Mastering tree traversals is foundational for solving more complex tree problems (e.g., serialization, path sums, or binary search trees). Always consider both recursive and iterative approaches to demonstrate versatility.