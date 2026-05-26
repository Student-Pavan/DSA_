# 🌲 Binary Tree Level Order Traversal

---

# 📝 Problem Statement

Given the `root` of a binary tree, return the **level order traversal** of its nodes' values. The traversal should return nodes level by level from left to right.

### **Objective**
Return a list of lists where each inner list contains the values of nodes at the same level in the tree.

### **Input**
- `root`: The root node of a binary tree (`TreeNode`)

### **Output**
- `List<List<Integer>>`: A list of lists where each sublist represents a level in the tree

### **Constraints**
- The number of nodes in the tree is in the range `[0, 2000]`
- `-1000 <= Node.val <= 1000`

---

# 💡 Intuition

Level order traversal requires visiting nodes level by level, starting from the root. The key insight is that nodes at the same level must be processed together before moving to the next level.

This naturally suggests using a **Breadth-First Search (BFS)** approach, where we use a queue to keep track of nodes at the current level. As we dequeue a node, we enqueue its children, ensuring that nodes are processed in the correct order.

While a brute-force approach might involve multiple traversals or recursion, BFS provides an efficient and intuitive way to traverse the tree level by level in a single pass.

---

# 🐌 Brute Force Approach

## 🔹 Approach

A naive approach could involve:
1. Calculating the height of the tree.
2. For each level from `0` to `height-1`, perform a traversal to collect nodes at that level.

However, this requires multiple passes over the tree, making it inefficient. Instead, we can simulate level order traversal using **recursion with depth tracking**, where we recursively visit nodes and place their values in the appropriate level list.

## 🔹 Algorithm

1. Initialize a result list to store level-wise node values.
2. Define a helper function that takes a node and its current depth.
3. If the node is `null`, return.
4. If the result list doesn't have a sublist for the current depth, create one.
5. Add the node's value to the sublist corresponding to its depth.
6. Recursively call the helper function for the left and right children, incrementing the depth by 1.
7. Return the result list after traversal.

## 🔹 Code

```java
import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        traverse(root, 0, result);
        return result;
    }

    private void traverse(TreeNode node, int depth, List<List<Integer>> result) {
        if (node == null) {
            return;
        }

        if (result.size() == depth) {
            result.add(new ArrayList<>());
        }

        result.get(depth).add(node.val);
        traverse(node.left, depth + 1, result);
        traverse(node.right, depth + 1, result);
    }
}
```

## 🔹 Dry Run

**Tree Structure:**
```
      3
     / \
    9  20
      /  \
     15   7
```

**Dry Run Steps:**

| Step | Node | Depth | Action | Result State |
|------|------|-------|--------|--------------|
| 1    | 3    | 0     | Add level 0 list, add 3 | `[[3]]` |
| 2    | 9    | 1     | Add level 1 list, add 9 | `[[3], [9]]` |
| 3    | null | 2     | Return | `[[3], [9]]` |
| 4    | 20   | 1     | Add 20 to level 1 | `[[3], [9, 20]]` |
| 5    | 15   | 2     | Add level 2 list, add 15 | `[[3], [9, 20], [15]]` |
| 6    | null | 3     | Return | `[[3], [9, 20], [15]]` |
| 7    | 7    | 2     | Add 7 to level 2 | `[[3], [9, 20], [15, 7]]` |

**Final Result:** `[[3], [9, 20], [15, 7]]`

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(N) |
| Space Complexity | O(N) |

**Explanation:**
- **Time:** Each node is visited exactly once.
- **Space:** The recursion stack can go up to `O(N)` in the worst case (skewed tree), and the result list stores all nodes.

---

# ⚡ Optimal Approach

## 🔹 Approach

The optimal approach uses **Breadth-First Search (BFS)** with a queue to traverse the tree level by level. This ensures that nodes are processed in the correct order without recursion, making it more efficient and intuitive.

### **Key Insight**
- Use a queue to keep track of nodes at the current level.
- For each level, process all nodes in the queue, enqueue their children, and move to the next level.

## 🔹 Why This Works

BFS naturally processes nodes level by level. By using a queue, we ensure that:
1. Nodes are dequeued in the order they were enqueued (FIFO).
2. Children of the current level are enqueued for the next level.
3. The number of nodes in the queue at any point represents the nodes at the current level.

This approach is efficient and avoids the overhead of recursion.

## 🔹 Algorithm

1. Initialize a result list and a queue.
2. If the root is `null`, return the empty result.
3. Enqueue the root node.
4. While the queue is not empty:
   - Determine the number of nodes at the current level (`levelSize`).
   - Initialize a list to store values of the current level.
   - For each node in the current level:
     - Dequeue the node and add its value to the current level list.
     - Enqueue the left and right children if they exist.
   - Add the current level list to the result.
5. Return the result.

## 🔹 Code

```java
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            List<Integer> currentLevel = new ArrayList<>();

            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                currentLevel.add(node.val);

                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }

            result.add(currentLevel);
        }

        return result;
    }
}
```

## 🔹 Detailed Dry Run

**Tree Structure:**
```
      3
     / \
    9  20
      /  \
     15   7
```

**Dry Run Steps:**

| Step | Queue State | Current Level | Action | Result State |
|------|-------------|---------------|--------|--------------|
| 1    | [3]         | []            | Start processing level 0 | `[]` |
| 2    | []          | [3]           | Dequeue 3, enqueue 9, 20 | `[[3]]` |
| 3    | [9, 20]     | []            | Start processing level 1 | `[[3]]` |
| 4    | [20]        | [9]           | Dequeue 9, no children | `[[3], [9]]` |
| 5    | [20, 15]    | [9]           | Dequeue 20, enqueue 15, 7 | `[[3], [9]]` |
| 6    | [15, 7]     | [9, 20]       | Add level 1 to result | `[[3], [9, 20]]` |
| 7    | [7]         | [15]          | Dequeue 15, no children | `[[3], [9, 20]]` |
| 8    | []          | [15, 7]       | Dequeue 7, no children | `[[3], [9, 20], [15, 7]]` |

**Final Result:** `[[3], [9, 20], [15, 7]]`

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(N) |
| Space Complexity | O(N) |

**Explanation:**
- **Time:** Each node is enqueued and dequeued exactly once.
- **Space:** The queue can hold up to `O(N)` nodes in the worst case (last level of a complete tree).

---

# 🔍 Edge Cases

| Edge Case | Input | Expected Output |
|-----------|-------|-----------------|
| Empty Tree | `null` | `[]` |
| Single Node | `[1]` | `[[1]]` |
| Left-Skewed Tree | `[1, 2, null, 3]` | `[[1], [2], [3]]` |
| Right-Skewed Tree | `[1, null, 2, null, 3]` | `[[1], [2], [3]]` |
| Full Binary Tree | `[1, 2, 3, 4, 5, 6, 7]` | `[[1], [2, 3], [4, 5, 6, 7]]` |
| Large Tree | 2000 nodes | Correct level order traversal |

---

# 📚 Key Takeaways

1. **BFS vs DFS**: BFS is ideal for level-order traversal, while DFS (recursion) can also achieve it with depth tracking.
2. **Queue Usage**: A queue is essential for BFS to process nodes level by level.
3. **Time-Space Tradeoff**: Both approaches are `O(N)` in time and space, but BFS is more intuitive for level-order traversal.
4. **Recursion Depth**: The recursive approach may hit stack limits for very deep trees (though constraints prevent this here).

---

# 🚀 Interview Tips

1. **Follow-Up Questions**:
   - How would you modify the solution to return nodes in **reverse level order**?
   - Can you implement level order traversal using **only one queue** without tracking level size?
   - How would you handle a tree with **millions of nodes**? (Hint: Iterative BFS is better for large trees.)

2. **Common Pitfalls**:
   - Forgetting to handle the `null` root case.
   - Incorrectly tracking the current level in the recursive approach.
   - Not using `levelSize` in BFS, leading to incorrect level separation.

3. **Alternative Approaches**:
   - **DFS with Depth Tracking**: As shown in the brute-force approach.
   - **Two Queues**: Use one queue for the current level and another for the next level.

4. **Optimization Discussion**:
   - BFS is generally preferred for level-order traversal due to its clarity and efficiency.
   - The recursive approach is simpler but may not be as intuitive for beginners.

---

# ✅ Conclusion

The **optimal solution** for level order traversal uses **BFS with a queue**, which is both efficient and easy to understand. It processes nodes level by level, ensuring correctness and clarity. While the recursive approach works, BFS is the go-to method for level-order traversal in interviews due to its straightforward implementation and optimal performance.

**Key Insight**: Use a queue to naturally separate nodes by level, and process each level in batches to achieve the desired traversal order.