# 📌 Trapping Rain Water

---

# 📝 Problem Statement

Given `n` non-negative integers representing an elevation map where the width of each bar is `1`, compute how much water it can trap after raining.

### **Objective**
Calculate the total amount of rainwater trapped between the bars.

### **Input**
- An array `height` of `n` non-negative integers where `0 <= n <= 2 * 10^4` and `0 <= height[i] <= 10^5`.

### **Output**
- An integer representing the total trapped water.

### **Constraints**
- The solution must efficiently handle the upper constraint limits.
- Water is trapped only between bars, not at the edges.

### **Example**
**Input:** `height = [0,1,0,2,1,0,1,3,2,1,2,1]`
**Output:** `6`
**Explanation:** The elevation map traps `6` units of water.

---

# 💡 Intuition

The key insight is that the amount of water trapped at any bar depends on the **maximum heights to its left and right**. Specifically, the water trapped at index `i` is determined by the minimum of the maximum heights on both sides minus the height of the current bar.

This problem can be approached in multiple ways:
1. **Brute Force:** For each bar, compute the maximum heights to its left and right, then calculate the trapped water.
2. **Optimal (Two Pointers):** Use two pointers to traverse the array from both ends, maintaining the maximum heights encountered so far, and compute the trapped water in a single pass.

The optimal approach reduces the time complexity from **O(n²)** to **O(n)** while using **O(1)** space.

---

# 🐌 Brute Force Approach

## 🔹 Approach
For each bar, compute the maximum height to its left and the maximum height to its right. The trapped water at that bar is the minimum of these two maxima minus the height of the bar. Sum this value for all bars to get the total trapped water.

## 🔹 Algorithm
1. Initialize `totalWater = 0`.
2. For each bar at index `i`:
   - Find the maximum height to the left of `i` (`leftMax`).
   - Find the maximum height to the right of `i` (`rightMax`).
   - Calculate the trapped water at `i` as `min(leftMax, rightMax) - height[i]`.
   - If the result is positive, add it to `totalWater`.
3. Return `totalWater`.

## 🔹 Code
```java
class Solution {
    public int trap(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }

        int totalWater = 0;
        int n = height.length;

        for (int i = 0; i < n; i++) {
            int leftMax = 0;
            for (int j = 0; j < i; j++) {
                leftMax = Math.max(leftMax, height[j]);
            }

            int rightMax = 0;
            for (int j = i + 1; j < n; j++) {
                rightMax = Math.max(rightMax, height[j]);
            }

            int water = Math.min(leftMax, rightMax) - height[i];
            if (water > 0) {
                totalWater += water;
            }
        }

        return totalWater;
    }
}
```

## 🔹 Dry Run
**Input:** `height = [0,1,0,2,1,0,1,3,2,1,2,1]`

| Index | Left Max | Right Max | Water Trapped (`min(leftMax, rightMax) - height[i]`) | Total Water |
|-------|----------|-----------|------------------------------------------------------|-------------|
| 0     | 0        | 3         | `min(0, 3) - 0 = 0`                                  | 0           |
| 1     | 0        | 3         | `min(0, 3) - 1 = -1` (ignored)                       | 0           |
| 2     | 1        | 3         | `min(1, 3) - 0 = 1`                                  | 1           |
| 3     | 1        | 3         | `min(1, 3) - 2 = -1` (ignored)                       | 1           |
| 4     | 2        | 3         | `min(2, 3) - 1 = 1`                                  | 2           |
| 5     | 2        | 3         | `min(2, 3) - 0 = 2`                                  | 4           |
| 6     | 2        | 3         | `min(2, 3) - 1 = 1`                                  | 5           |
| 7     | 2        | 2         | `min(2, 2) - 3 = -1` (ignored)                       | 5           |
| 8     | 3        | 2         | `min(3, 2) - 2 = 0`                                  | 5           |
| 9     | 3        | 2         | `min(3, 2) - 1 = 1`                                  | 6           |
| 10    | 3        | 1         | `min(3, 1) - 2 = -1` (ignored)                       | 6           |
| 11    | 3        | 0         | `min(3, 0) - 1 = -1` (ignored)                       | 6           |

**Final Output:** `6`

## 🔹 Complexity Analysis
| Complexity       | Value     |
|------------------|-----------|
| Time Complexity  | O(n²)     |
| Space Complexity | O(1)      |

---

# ⚡ Optimal Approach (Two Pointers)

## 🔹 Approach
Instead of computing `leftMax` and `rightMax` for every bar, use two pointers (`left` and `right`) to traverse the array from both ends. Maintain `leftMax` and `rightMax` as the highest bars encountered so far. The trapped water at any point depends on the smaller of the two maxima, as water is trapped only up to the smaller boundary.

## 🔹 Why This Works
- The two-pointer approach ensures that we only traverse the array once.
- By always moving the pointer pointing to the smaller bar, we ensure that we correctly compute the trapped water based on the current boundary.
- This approach eliminates the need for nested loops, reducing the time complexity to **O(n)**.

## 🔹 Algorithm
1. Initialize `left = 0`, `right = n - 1`, `leftMax = 0`, `rightMax = 0`, and `totalWater = 0`.
2. While `left < right`:
   - If `height[left] < height[right]`:
     - If `height[left] >= leftMax`, update `leftMax`.
     - Else, add `leftMax - height[left]` to `totalWater`.
     - Move `left` forward.
   - Else:
     - If `height[right] >= rightMax`, update `rightMax`.
     - Else, add `rightMax - height[right]` to `totalWater`.
     - Move `right` backward.
3. Return `totalWater`.

## 🔹 Code
```java
class Solution {
    public int trap(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }

        int left = 0;
        int right = height.length - 1;
        int leftMax = 0;
        int rightMax = 0;
        int totalWater = 0;

        while (left < right) {
            if (height[left] < height[right]) {
                if (height[left] >= leftMax) {
                    leftMax = height[left];
                } else {
                    totalWater += leftMax - height[left];
                }
                left++;
            } else {
                if (height[right] >= rightMax) {
                    rightMax = height[right];
                } else {
                    totalWater += rightMax - height[right];
                }
                right--;
            }
        }

        return totalWater;
    }
}
```

## 🔹 Detailed Dry Run
**Input:** `height = [0,1,0,2,1,0,1,3,2,1,2,1]`

| Left | Right | Left Max | Right Max | Action                          | Total Water |
|------|-------|----------|-----------|---------------------------------|-------------|
| 0    | 11    | 0        | 1         | `height[0] < height[11]` → `leftMax = 0` (no water) | 0           |
| 1    | 11    | 1        | 1         | `height[1] == height[11]` → `rightMax = 1` (no water) | 0           |
| 1    | 10    | 1        | 2         | `height[1] < height[10]` → `leftMax = 1` (no water) | 0           |
| 2    | 10    | 1        | 2         | `height[2] < height[10]` → `water = 1 - 0 = 1`      | 1           |
| 3    | 10    | 2        | 2         | `height[3] == height[10]` → `rightMax = 2` (no water) | 1           |
| 3    | 9     | 2        | 2         | `height[3] > height[9]` → `water = 2 - 1 = 1`       | 2           |
| 3    | 8     | 2        | 2         | `height[3] > height[8]` → `water = 2 - 2 = 0`       | 2           |
| 3    | 7     | 2        | 3         | `height[3] < height[7]` → `leftMax = 2` (no water) | 2           |
| 4    | 7     | 2        | 3         | `height[4] < height[7]` → `water = 2 - 1 = 1`       | 3           |
| 5    | 7     | 2        | 3         | `height[5] < height[7]` → `water = 2 - 0 = 2`       | 5           |
| 6    | 7     | 2        | 3         | `height[6] < height[7]` → `water = 2 - 1 = 1`       | 6           |

**Final Output:** `6`

## 🔹 Complexity Analysis
| Complexity       | Value     |
|------------------|-----------|
| Time Complexity  | O(n)      |
| Space Complexity | O(1)      |

---

# 🔍 Edge Cases
- **Empty array:** `height = []` → Output: `0`
- **Single bar:** `height = [5]` → Output: `0` (no boundaries to trap water)
- **Flat terrain:** `height = [2,2,2,2]` → Output: `0` (no gaps to trap water)
- **Descending order:** `height = [5,4,3,2,1]` → Output: `0` (no left boundary)
- **Ascending order:** `height = [1,2,3,4,5]` → Output: `0` (no right boundary)
- **Large input:** `height = [0,1,0,2,...,10^5]` → Must handle efficiently (optimal approach required).
- **All zeros:** `height = [0,0,0,0]` → Output: `0`

---

# 📚 Key Takeaways
1. **Two-Pointer Technique:** A powerful optimization for problems involving left and right boundaries.
2. **Boundary-Dependent Problems:** Trapped water depends on the minimum of the left and right maxima.
3. **Efficiency Matters:** The brute-force approach is intuitive but inefficient for large inputs.
4. **Space Optimization:** The optimal solution uses **O(1)** space, making it highly scalable.

---

# 🚀 Interview Tips
- **Follow-up:** Can you solve this using a stack? (Hint: Monotonic stack approach exists but is less intuitive.)
- **Common Pitfall:** Forgetting to handle negative water values (i.e., `min(leftMax, rightMax) - height[i]` must be positive).
- **Alternative Approach:** Dynamic Programming (precompute `leftMax` and `rightMax` arrays) achieves **O(n)** time but uses **O(n)** space.
- **Optimization Insight:** Always move the pointer pointing to the smaller bar to ensure correctness.

---

# ✅ Conclusion
The **two-pointer approach** is the most efficient solution for the **Trapping Rain Water** problem, achieving **O(n)** time and **O(1)** space. The key insight is that the trapped water at any bar is determined by the smaller of the left and right boundaries. This problem is a classic example of how **boundary-based problems** can be optimized using **two pointers**, making it a must-know for technical interviews.