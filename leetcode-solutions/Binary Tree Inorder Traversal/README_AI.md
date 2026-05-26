# 📌 Binary Tree Inorder Traversal

---

# 📝 Problem Statement

Given the `root` of a binary tree, return the **inorder traversal** of its nodes' values.

**Inorder traversal** is a depth-first traversal method where nodes are visited in the order:
**Left subtree → Root → Right subtree**

### Input:
- `TreeNode root`: The root node of a binary tree. May be `null`.

### Output:
- `List<Integer>`: A list containing the values of the nodes in inorder traversal order.

### Constraints:
- The number of nodes in the tree is in the range `[0, 100]`.
- `-100 <= Node.val <= 100`

### Example:
```
Input: root = [1,null,2,3]
    1
     \
      2
     /
    3
Output: [1,3,2]
```

---

# 💡 Intuition

Inorder traversal is a fundamental tree traversal technique. The key insight is that we must explore the **left subtree completely** before processing the current node, then the **right subtree**.

This recursive structure naturally suggests a **depth-first search (DFS)** approach. However, recursion uses implicit stack space. To optimize space usage, we can simulate the recursion using an **explicit stack**, which leads to an iterative solution with the same time complexity but better control over space.

---

# 🐌 Brute Force Approach

## 🔹 Approach

The brute force approach uses **recursion** to perform inorder traversal. This is the most intuitive method:

1. Traverse the left subtree recursively.
2. Visit the current node (add to result).
3. Traverse the right subtree recursively.

This approach leverages the call stack to keep track of nodes, making it simple but potentially inefficient in terms of space for very deep trees.

---

## 🔹 Algorithm

1. Initialize an empty list to store the result.
2. Define a helper function that takes a node and the result list.
3. If the node is `null`, return.
4. Recursively call the helper on the left child.
5. Add the node's value to the result list.
6. Recursively call the helper on the right child.
7. Call the helper on the root node.
8. Return the result list.

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
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        helper(root, result);
        return result;
    }

    private void helper(TreeNode node, List<Integer> result) {
        if (node == null) {
            return;
        }
        helper(node.left, result);   // Traverse left
        result.add(node.val);        // Visit node
        helper(node.right, result);  // Traverse right
    }
}
```

---

## 🔹 Dry Run

Consider the tree:
```
    1
     \
      2
     /
    3
```

We'll trace the recursive calls:

| Step | Function Call       | Action                                      | Result List |
|------|---------------------|---------------------------------------------|-------------|
| 1    | `inorderTraversal(1)` | Call `helper(1, [])`                        | []          |
| 2    | `helper(1, [])`       | `1 != null`, call `helper(1.left, [])`      | []          |
| 3    | `helper(null, [])`    | `null`, return                              | []          |
| 4    | Back to `helper(1, [])` | Add `1.val` → `1`                          | [1]         |
| 5    | `helper(1.right, [1])` | Call `helper(2, [1])`                       | [1]         |
| 6    | `helper(2, [1])`       | `2 != null`, call `helper(2.left, [1])`     | [1]         |
| 7    | `helper(3, [1])`       | `3 != null`, call `helper(3.left, [1])`     | [1]         |
| 8    | `helper(null, [1])`    | `null`, return                              | [1]         |
| 9    | Back to `helper(3, [1])` | Add `3.val` → `3`                          | [1, 3]      |
| 10   | `helper(3.right, [1,3])` | Call `helper(null, [1,3])`                 | [1, 3]      |
| 11   | `helper(null, [1,3])`   | `null`, return                              | [1, 3]      |
| 12   | Back to `helper(2, [1,3])` | Add `2.val` → `2`                          | [1, 3, 2]   |
| 13   | `helper(2.right, [1,3,2])` | Call `helper(null, [1,3,2])`               | [1, 3, 2]   |
| 14   | `helper(null, [1,3,2])`  | `null`, return                              | [1, 3, 2]   |
| 15   | Return from all calls   | Final result                                | [1, 3, 2]   |

---

## 🔹 Complexity Analysis

| Complexity         | Value       |
|--------------------|-------------|
| Time Complexity    | O(n)        |
| Space Complexity   | O(h)        |

> **n**: Number of nodes in the tree
> **h**: Height of the tree (worst case: O(n) for skewed tree)

---

# ⚡ Optimal Approach

## 🔹 Approach

The **iterative approach** using an explicit stack eliminates recursion and reduces space overhead in practice (though worst-case remains O(h)). It simulates the call stack manually:

1. Use a stack to keep track of nodes.
2. Start from the root and push all left children onto the stack.
3. When no more left children exist, pop a node, add its value to the result.
4. Move to the right child and repeat.

This approach maintains the same time complexity but is often preferred in interviews due to better control over memory and avoidance of stack overflow in deep trees.

---

## 🔹 Why This Works

The iterative method mimics the recursive call stack. By pushing all left nodes first, we ensure the leftmost node is processed first (inorder property). After processing, we move to the right subtree, which again is processed in inorder fashion.

The stack ensures we return to parent nodes in the correct order, preserving the traversal sequence.

---

## 🔹 Algorithm

1. Initialize an empty list `result` and a stack.
2. Start with `current = root`.
3. While `current != null` or stack is not empty:
   - Push all left children of `current` onto the stack.
   - Pop the top node from the stack, add its value to `result`.
   - Set `current` to the right child of the popped node.
4. Return `result`.

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
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode current = root;

        while (current != null || !stack.isEmpty()) {
            // Push all left children
            while (current != null) {
                stack.push(current);
                current = current.left;
            }
            // Process node
            current = stack.pop();
            result.add(current.val);
            // Move to right subtree
            current = current.right;
        }

        return result;
    }
}
```

---

## 🔹 Detailed Dry Run

Using the same tree:
```
    1
     \
      2
     /
    3
```

| Step | Current | Stack State       | Action                     | Result List |
|------|---------|-------------------|----------------------------|-------------|
| 1    | 1       | []                | Push 1 → stack = [1]       | []          |
| 2    | 1.left = null | [1]           | `current == null`, break   | []          |
| 3    | -       | [1]               | Pop 1, add 1 → result = [1]| [1]         |
| 4    | 1.right = 2 | []             | Push 2 → stack = [2]       | [1]         |
| 5    | 2.left = 3 | [2]            | Push 3 → stack = [2,3]     | [1]         |
| 6    | 3.left = null | [2,3]        | `current == null`, break   | [1]         |
| 7    | -       | [2,3]            | Pop 3, add 3 → result = [1,3] | [1,3]      |
| 8    | 3.right = null | [2]          | `current == null`, skip    | [1,3]       |
| 9    | -       | [2]               | Pop 2, add 2 → result = [1,3,2] | [1,3,2] |
| 10   | 2.right = null | []           | `current == null`, skip    | [1,3,2]     |
| 11   | null    | []                | Stack empty, loop ends     | [1,3,2]     |

---

## 🔹 Complexity Analysis

| Complexity         | Value       |
|--------------------|-------------|
| Time Complexity    | O(n)        |
| Space Complexity   | O(h)        |

> **n**: Number of nodes
> **h**: Height of the tree (worst case: O(n))

---

# 🔍 Edge Cases

| Edge Case                     | Expected Output          | Notes |
|-------------------------------|--------------------------|-------|
| Empty tree (`root = null`)    | `[]`                     | Handle base case |
| Single node (`[1]`)           | `[1]`                    | Only root |
| Left-skewed tree (`[1,2,null]`) | `[2,1]`                | All left children |
| Right-skewed tree (`[1,null,2]`) | `[1,2]`                | All right children |
| Full binary tree (`[1,2,3]`)  | `[2,1,3]`                | Both children |
| Tree with negative values     | `[...]`                  | Values don’t affect traversal |

---

# 📚 Key Takeaways

- **Inorder traversal** follows **Left → Root → Right** order.
- **Recursive solution** is intuitive but uses implicit stack space.
- **Iterative solution** uses an explicit stack and is preferred in interviews.
- Both approaches have **O(n) time** and **O(h) space** complexity.
- **Stack-based iteration** is a powerful technique for simulating recursion in trees.

---

# 🚀 Interview Tips

- **Follow-up**: Can you do it **without recursion or stack**? (Morris Traversal — O(1) space)
- **Common pitfall**: Forgetting to push all left children before popping.
- **Alternative**: Morris Traversal is advanced but optimal in space.
- **Optimization discussion**: Recursion vs iteration — trade-offs in readability vs control.
- **Test with**: Skewed trees, full trees, and edge cases.

---

# ✅ Conclusion

The **iterative stack-based approach** is the optimal solution for inorder traversal in most interview settings. It combines clarity, efficiency, and robustness, avoiding recursion limits while maintaining O(n) time and O(h) space complexity.

The key insight is that **a stack can perfectly simulate the call stack**, allowing us to traverse the tree in the correct order without recursion. This pattern is widely applicable in tree and graph algorithms.