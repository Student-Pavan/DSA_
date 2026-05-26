# 📌 Baseball Game

---

# 📝 Problem Statement

You are keeping score for a baseball game with strange rules. The game consists of several rounds, where the scores of past rounds may affect future rounds' scores.

At the beginning of the game, you start with an empty record. You are given a list of strings `ops`, where `ops[i]` is the `i-th` operation you must apply to the record and is one of the following:

1. **Integer (positive or negative)**: Record a new score of the given integer.
2. `"+"`: Record a new score that is the sum of the previous two scores. It is guaranteed there will always be two previous scores.
3. `"D"`: Record a new score that is double the previous score. It is guaranteed there will always be a previous score.
4. `"C"`: Invalidate the previous score, removing it from the record. It is guaranteed there will always be a previous score.

Return the sum of all the scores on the record.

### Example 1:
```
Input: ops = ["5","2","C","D","+"]
Output: 30
Explanation:
"5" - Add 5 to the record, record is now [5].
"2" - Add 2 to the record, record is now [5, 2].
"C" - Invalidate and remove the previous score, record is now [5].
"D" - Add 2 * 5 = 10 to the record, record is now [5, 10].
"+" - Add 5 + 10 = 15 to the record, record is now [5, 10, 15].
The total sum is 5 + 10 + 15 = 30.
```

### Constraints:
- `1 <= ops.length <= 1000`
- `ops[i]` is `"C"`, `"D"`, `"+"`, or a string representing an integer in the range `[-3 * 10^4, 3 * 10^4]`.
- For operation `"+"`, there will always be at least two previous scores on the record.
- For operations `"C"` and `"D"`, there will always be at least one previous score on the record.

---

# 💡 Intuition

The problem requires maintaining a dynamic record of scores where operations can modify the record in real-time. The key insight is recognizing that we need a data structure that allows efficient insertion, deletion, and access to the most recent elements.

A **stack** is the ideal choice because:
- It supports **O(1)** insertion and deletion at the top.
- It naturally maintains the order of operations (LIFO).
- It allows easy access to the most recent elements (top of the stack).

The operations `"C"`, `"D"`, and `"+"` all depend on the most recent scores, making the stack a perfect fit for this problem.

---

# 🐌 Brute Force Approach

## 🔹 Approach

The brute force approach involves simulating each operation step-by-step using a list to maintain the record. For each operation:
- If it's an integer, add it to the list.
- If it's `"C"`, remove the last element.
- If it's `"D"`, double the last element and add it.
- If it's `"+"`, sum the last two elements and add it.

While this approach works, it is inefficient for large inputs because list operations like `remove()` and accessing elements by index can take **O(n)** time in the worst case.

---

## 🔹 Algorithm

1. Initialize an empty list to store scores.
2. Iterate through each operation in `ops`:
   - If the operation is `"C"`, remove the last score from the list.
   - If the operation is `"D"`, double the last score and append it to the list.
   - If the operation is `"+"`, sum the last two scores and append the result to the list.
   - Otherwise, parse the operation as an integer and append it to the list.
3. Sum all elements in the list and return the result.

---

## 🔹 Code

```java
import java.util.ArrayList;
import java.util.List;

class Solution {
    public int calPoints(String[] ops) {
        List<Integer> record = new ArrayList<>();

        for (String op : ops) {
            if (op.equals("C")) {
                record.remove(record.size() - 1);
            } else if (op.equals("D")) {
                int last = record.get(record.size() - 1);
                record.add(last * 2);
            } else if (op.equals("+")) {
                int last = record.get(record.size() - 1);
                int secondLast = record.get(record.size() - 2);
                record.add(last + secondLast);
            } else {
                record.add(Integer.parseInt(op));
            }
        }

        int sum = 0;
        for (int score : record) {
            sum += score;
        }
        return sum;
    }
}
```

---

## 🔹 Dry Run

**Input:** `ops = ["5","2","C","D","+"]`

| Step | Operation | Action                          | Record State       | Sum  |
|------|-----------|---------------------------------|--------------------|------|
| 1    | "5"       | Add 5                           | [5]                | 5    |
| 2    | "2"       | Add 2                           | [5, 2]             | 7    |
| 3    | "C"       | Remove last (2)                 | [5]                | 5    |
| 4    | "D"       | Double last (5 * 2 = 10)        | [5, 10]            | 15   |
| 5    | "+"       | Sum last two (5 + 10 = 15)      | [5, 10, 15]        | 30   |

**Final Sum:** `30`

---

## 🔹 Complexity Analysis

| Complexity       | Value       |
|------------------|-------------|
| Time Complexity  | O(n²)       |
| Space Complexity | O(n)        |

**Explanation:**
- **Time:** `O(n²)` because `remove()` and `get()` operations on a list can take `O(n)` time in the worst case.
- **Space:** `O(n)` to store the record of scores.

---

# ⚡ Optimal Approach

## 🔹 Approach

The optimal approach leverages a **stack** to efficiently handle the operations. A stack provides **O(1)** time complexity for insertion, deletion, and access to the top element, making it ideal for this problem.

### Key Optimizations:
- Use a stack to maintain the record of scores.
- For `"C"`, simply pop the top element.
- For `"D"`, push `2 * top` onto the stack.
- For `"+"`, pop the top, sum it with the new top, and push both back.
- For integers, parse and push onto the stack.

---

## 🔹 Why This Works

The stack naturally aligns with the problem's requirements:
- **LIFO Order:** Ensures that the most recent scores are always at the top, making operations like `"C"`, `"D"`, and `"+"` efficient.
- **O(1) Operations:** All stack operations (push, pop, peek) are constant time, leading to an overall **O(n)** time complexity.

---

## 🔹 Algorithm

1. Initialize an empty stack to store scores.
2. Iterate through each operation in `ops`:
   - If the operation is `"C"`, pop the top element from the stack.
   - If the operation is `"D"`, push `2 * top` onto the stack.
   - If the operation is `"+"`, pop the top element, sum it with the new top, and push both back.
   - Otherwise, parse the operation as an integer and push it onto the stack.
3. Sum all elements in the stack and return the result.

---

## 🔹 Code

```java
import java.util.Stack;

class Solution {
    public int calPoints(String[] ops) {
        Stack<Integer> stack = new Stack<>();

        for (String op : ops) {
            if (op.equals("C")) {
                stack.pop();
            } else if (op.equals("D")) {
                stack.push(2 * stack.peek());
            } else if (op.equals("+")) {
                int top = stack.pop();
                int newTop = top + stack.peek();
                stack.push(top);
                stack.push(newTop);
            } else {
                stack.push(Integer.parseInt(op));
            }
        }

        int sum = 0;
        for (int score : stack) {
            sum += score;
        }
        return sum;
    }
}
```

---

## 🔹 Detailed Dry Run

**Input:** `ops = ["5","2","C","D","+"]`

| Step | Operation | Action                          | Stack State       | Sum  |
|------|-----------|---------------------------------|-------------------|------|
| 1    | "5"       | Push 5                          | [5]               | 5    |
| 2    | "2"       | Push 2                          | [5, 2]            | 7    |
| 3    | "C"       | Pop 2                           | [5]               | 5    |
| 4    | "D"       | Push 2 * 5 = 10                 | [5, 10]           | 15   |
| 5    | "+"       | Pop 10, sum 5 + 10 = 15, push 10, push 15 | [5, 10, 15] | 30   |

**Final Sum:** `30`

---

## 🔹 Complexity Analysis

| Complexity       | Value       |
|------------------|-------------|
| Time Complexity  | O(n)        |
| Space Complexity | O(n)        |

**Explanation:**
- **Time:** `O(n)` because each operation is processed in constant time.
- **Space:** `O(n)` to store the stack of scores.

---

# 🔍 Edge Cases

| Edge Case                     | Expected Output | Explanation                                                                 |
|-------------------------------|-----------------|-----------------------------------------------------------------------------|
| `ops = ["5"]`                 | 5               | Single integer operation.                                                   |
| `ops = ["5","-2","4","C","D","9","+","+"]` | 27       | Mix of all operations, including negative values.                           |
| `ops = ["1","C"]`             | 0               | Remove the only element, resulting in an empty record.                      |
| `ops = ["1","D","D"]`         | 7               | Double the last score twice.                                                |
| `ops = ["1","1","+"]`         | 3               | Sum the last two scores.                                                    |
| `ops = ["100000"]`            | 100000          | Large integer value within constraints.                                     |

---

# 📚 Key Takeaways

1. **Stacks for Dynamic Operations:** Use a stack when operations depend on the most recent elements (LIFO order).
2. **Efficiency Matters:** The stack provides **O(1)** operations, making it optimal for this problem.
3. **Edge Case Handling:** Always validate operations like `"C"`, `"D"`, and `"+"` for edge cases (e.g., empty stack).
4. **Parsing Input:** Carefully parse integers from strings, especially for negative values.

---

# 🚀 Interview Tips

1. **Clarify Constraints:** Confirm that `"C"`, `"D"`, and `"+"` will always have valid previous scores.
2. **Discuss Trade-offs:** Compare the brute force (list) and optimal (stack) approaches in terms of time and space complexity.
3. **Follow-up Questions:**
   - How would you handle invalid operations (e.g., `"C"` on an empty record)?
   - Can you optimize space further (e.g., using a single variable for the sum)?
4. **Alternative Approaches:** Could you use a deque or array instead of a stack? Discuss pros and cons.

---

# ✅ Conclusion

The **optimal solution** uses a stack to efficiently handle dynamic score operations, achieving **O(n)** time and space complexity. The stack's LIFO property perfectly matches the problem's requirements, making it the ideal choice for this scenario.

**Key Insight:** Recognize when a problem involves operations on the most recent elements—this is a strong indicator that a stack is the right data structure.