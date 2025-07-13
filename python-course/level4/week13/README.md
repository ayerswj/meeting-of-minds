# Level 4: Week 13 - Advanced Algorithms & Data Structures

## 🎯 Learning Objectives
By the end of this week, you will be able to:
- Implement advanced data structures (trees, graphs, heaps)
- Understand and implement complex algorithms
- Analyze algorithm complexity (Big O notation)
- Solve algorithmic problems efficiently
- Use advanced Python features for optimization

## 📚 Theory

### What are Advanced Algorithms?
Advanced algorithms are sophisticated problem-solving techniques that efficiently handle complex computational tasks. They often involve trade-offs between time complexity, space complexity, and implementation complexity.

### Algorithm Complexity Analysis
- **Time Complexity**: How runtime grows with input size
- **Space Complexity**: How memory usage grows with input size
- **Big O Notation**: Mathematical notation describing algorithm performance

## 🌳 Advanced Data Structures

### Binary Search Trees (BST)
```python
class Node:
    def __init__(self, key):
        self.key = key
        self.left = None
        self.right = None

class BinarySearchTree:
    def __init__(self):
        self.root = None
    
    def insert(self, key):
        self.root = self._insert_recursive(self.root, key)
    
    def _insert_recursive(self, root, key):
        if root is None:
            return Node(key)
        
        if key < root.key:
            root.left = self._insert_recursive(root.left, key)
        elif key > root.key:
            root.right = self._insert_recursive(root.right, key)
        
        return root
    
    def search(self, key):
        return self._search_recursive(self.root, key)
    
    def _search_recursive(self, root, key):
        if root is None or root.key == key:
            return root
        
        if key < root.key:
            return self._search_recursive(root.left, key)
        return self._search_recursive(root.right, key)
    
    def inorder_traversal(self):
        result = []
        self._inorder_recursive(self.root, result)
        return result
    
    def _inorder_recursive(self, root, result):
        if root:
            self._inorder_recursive(root.left, result)
            result.append(root.key)
            self._inorder_recursive(root.right, result)

# Usage
bst = BinarySearchTree()
bst.insert(50)
bst.insert(30)
bst.insert(70)
bst.insert(20)
bst.insert(40)
print("Inorder traversal:", bst.inorder_traversal())
```

### Heaps (Priority Queues)
```python
import heapq

class MinHeap:
    def __init__(self):
        self.heap = []
    
    def push(self, value):
        heapq.heappush(self.heap, value)
    
    def pop(self):
        return heapq.heappop(self.heap)
    
    def peek(self):
        return self.heap[0] if self.heap else None
    
    def size(self):
        return len(self.heap)

# Usage
min_heap = MinHeap()
min_heap.push(5)
min_heap.push(3)
min_heap.push(7)
min_heap.push(1)

print("Min element:", min_heap.pop())  # 1
print("Next min:", min_heap.peek())    # 3
```

### Graphs
```python
from collections import defaultdict

class Graph:
    def __init__(self):
        self.graph = defaultdict(list)
    
    def add_edge(self, u, v):
        self.graph[u].append(v)
    
    def bfs(self, start):
        visited = set()
        queue = [start]
        visited.add(start)
        
        while queue:
            vertex = queue.pop(0)
            print(vertex, end=" ")
            
            for neighbor in self.graph[vertex]:
                if neighbor not in visited:
                    visited.add(neighbor)
                    queue.append(neighbor)
    
    def dfs(self, start, visited=None):
        if visited is None:
            visited = set()
        
        visited.add(start)
        print(start, end=" ")
        
        for neighbor in self.graph[start]:
            if neighbor not in visited:
                self.dfs(neighbor, visited)

# Usage
g = Graph()
g.add_edge(0, 1)
g.add_edge(0, 2)
g.add_edge(1, 2)
g.add_edge(2, 0)
g.add_edge(2, 3)
g.add_edge(3, 3)

print("BFS starting from vertex 2:")
g.bfs(2)
print("\nDFS starting from vertex 2:")
g.dfs(2)
```

## 🔍 Advanced Algorithms

### Dynamic Programming
```python
def fibonacci_dp(n):
    """Calculate Fibonacci using dynamic programming"""
    if n <= 1:
        return n
    
    dp = [0] * (n + 1)
    dp[1] = 1
    
    for i in range(2, n + 1):
        dp[i] = dp[i-1] + dp[i-2]
    
    return dp[n]

def longest_common_subsequence(text1, text2):
    """Find the longest common subsequence"""
    m, n = len(text1), len(text2)
    dp = [[0] * (n + 1) for _ in range(m + 1)]
    
    for i in range(1, m + 1):
        for j in range(1, n + 1):
            if text1[i-1] == text2[j-1]:
                dp[i][j] = dp[i-1][j-1] + 1
            else:
                dp[i][j] = max(dp[i-1][j], dp[i][j-1])
    
    return dp[m][n]

# Usage
print("Fibonacci(10):", fibonacci_dp(10))
print("LCS of 'ABCDGH' and 'AEDFHR':", longest_common_subsequence("ABCDGH", "AEDFHR"))
```

### Sorting Algorithms
```python
def merge_sort(arr):
    """Merge sort implementation"""
    if len(arr) <= 1:
        return arr
    
    mid = len(arr) // 2
    left = merge_sort(arr[:mid])
    right = merge_sort(arr[mid:])
    
    return merge(left, right)

def merge(left, right):
    """Merge two sorted arrays"""
    result = []
    i = j = 0
    
    while i < len(left) and j < len(right):
        if left[i] <= right[j]:
            result.append(left[i])
            i += 1
        else:
            result.append(right[j])
            j += 1
    
    result.extend(left[i:])
    result.extend(right[j:])
    return result

def quick_sort(arr):
    """Quick sort implementation"""
    if len(arr) <= 1:
        return arr
    
    pivot = arr[len(arr) // 2]
    left = [x for x in arr if x < pivot]
    middle = [x for x in arr if x == pivot]
    right = [x for x in arr if x > pivot]
    
    return quick_sort(left) + middle + quick_sort(right)

# Usage
arr = [64, 34, 25, 12, 22, 11, 90]
print("Original array:", arr)
print("Merge sorted:", merge_sort(arr.copy()))
print("Quick sorted:", quick_sort(arr.copy()))
```

### Graph Algorithms
```python
def dijkstra(graph, start):
    """Dijkstra's shortest path algorithm"""
    distances = {node: float('infinity') for node in graph}
    distances[start] = 0
    unvisited = set(graph.keys())
    
    while unvisited:
        current = min(unvisited, key=lambda node: distances[node])
        unvisited.remove(current)
        
        for neighbor, weight in graph[current].items():
            if neighbor in unvisited:
                new_distance = distances[current] + weight
                if new_distance < distances[neighbor]:
                    distances[neighbor] = new_distance
    
    return distances

# Usage
graph = {
    'A': {'B': 1, 'C': 4},
    'B': {'A': 1, 'C': 2, 'D': 5},
    'C': {'A': 4, 'B': 2, 'D': 1},
    'D': {'B': 5, 'C': 1}
}

shortest_paths = dijkstra(graph, 'A')
print("Shortest paths from A:", shortest_paths)
```

## ⚡ Optimization Techniques

### Memoization
```python
from functools import lru_cache

@lru_cache(maxsize=None)
def fibonacci_memo(n):
    """Fibonacci with memoization"""
    if n <= 1:
        return n
    return fibonacci_memo(n-1) + fibonacci_memo(n-2)

# Custom memoization
def memoize(func):
    cache = {}
    
    def wrapper(*args):
        if args not in cache:
            cache[args] = func(*args)
        return cache[args]
    
    return wrapper

@memoize
def expensive_function(n):
    """Simulate expensive computation"""
    import time
    time.sleep(0.1)  # Simulate work
    return n * n
```

### Generators for Memory Efficiency
```python
def fibonacci_generator():
    """Generate Fibonacci numbers using generator"""
    a, b = 0, 1
    while True:
        yield a
        a, b = b, a + b

# Usage
fib_gen = fibonacci_generator()
for i, num in enumerate(fib_gen):
    if i >= 10:
        break
    print(num, end=" ")
```

## 🎯 Practice Exercises

### Exercise 1: Implement AVL Tree
Create a self-balancing binary search tree.

### Exercise 2: Graph Coloring Algorithm
Implement a graph coloring algorithm using backtracking.

### Exercise 3: Knapsack Problem
Solve the 0/1 knapsack problem using dynamic programming.

## 🚀 Mini-Project: Algorithm Visualizer

```python
import matplotlib.pyplot as plt
import numpy as np
import time

class AlgorithmVisualizer:
    def __init__(self):
        self.fig, self.ax = plt.subplots(figsize=(10, 6))
    
    def visualize_sorting(self, algorithm, arr):
        """Visualize sorting algorithm"""
        self.ax.clear()
        bars = self.ax.bar(range(len(arr)), arr)
        self.ax.set_title(f'{algorithm.__name__} Visualization')
        
        for i, bar in enumerate(bars):
            bar.set_color('lightblue')
        
        plt.pause(0.1)
        
        # Perform sorting with visualization
        algorithm(arr, self.update_visualization)
        
        # Final state
        self.ax.clear()
        bars = self.ax.bar(range(len(arr)), arr, color='green')
        self.ax.set_title(f'{algorithm.__name__} - Complete')
        plt.show()
    
    def update_visualization(self, arr, i, j):
        """Update visualization during sorting"""
        self.ax.clear()
        bars = self.ax.bar(range(len(arr)), arr)
        
        # Highlight compared elements
        if i < len(bars):
            bars[i].set_color('red')
        if j < len(bars):
            bars[j].set_color('red')
        
        plt.pause(0.01)

def bubble_sort_visual(arr, update_func):
    """Bubble sort with visualization callback"""
    n = len(arr)
    for i in range(n):
        for j in range(0, n - i - 1):
            if arr[j] > arr[j + 1]:
                arr[j], arr[j + 1] = arr[j + 1], arr[j]
                update_func(arr, j, j + 1)

# Usage
visualizer = AlgorithmVisualizer()
arr = np.random.randint(1, 100, 20)
visualizer.visualize_sorting(bubble_sort_visual, arr)
```

## 🎯 Advanced Concepts

### Custom Data Structures
```python
class LRUCache:
    """Least Recently Used Cache implementation"""
    def __init__(self, capacity):
        self.capacity = capacity
        self.cache = {}
        self.used_order = []
    
    def get(self, key):
        if key in self.cache:
            self.used_order.remove(key)
            self.used_order.append(key)
            return self.cache[key]
        return -1
    
    def put(self, key, value):
        if key in self.cache:
            self.used_order.remove(key)
        elif len(self.cache) >= self.capacity:
            oldest = self.used_order.pop(0)
            del self.cache[oldest]
        
        self.cache[key] = value
        self.used_order.append(key)

# Usage
lru = LRUCache(3)
lru.put(1, "one")
lru.put(2, "two")
lru.put(3, "three")
print(lru.get(1))  # "one"
lru.put(4, "four")  # Evicts key 2
print(lru.get(2))  # -1 (not found)
```

### Algorithm Complexity Analysis
```python
import time
import matplotlib.pyplot as plt

def analyze_complexity():
    """Analyze time complexity of different algorithms"""
    sizes = [100, 500, 1000, 2000, 5000]
    bubble_times = []
    merge_times = []
    
    for size in sizes:
        arr = list(range(size, 0, -1))  # Worst case for bubble sort
        
        # Time bubble sort
        start = time.time()
        bubble_sort(arr.copy())
        bubble_times.append(time.time() - start)
        
        # Time merge sort
        start = time.time()
        merge_sort(arr.copy())
        merge_times.append(time.time() - start)
    
    # Plot results
    plt.figure(figsize=(10, 6))
    plt.plot(sizes, bubble_times, 'o-', label='Bubble Sort (O(n²))')
    plt.plot(sizes, merge_times, 's-', label='Merge Sort (O(n log n))')
    plt.xlabel('Array Size')
    plt.ylabel('Time (seconds)')
    plt.title('Algorithm Complexity Comparison')
    plt.legend()
    plt.grid(True)
    plt.show()

# analyze_complexity()
```

## 📖 Additional Resources

- [Python Algorithms](https://runestone.academy/runestone/books/published/pythonds/index.html)
- [Big O Notation](https://www.bigocheatsheet.com/)
- [LeetCode](https://leetcode.com/) - Practice algorithmic problems
- [HackerRank](https://www.hackerrank.com/) - Algorithm challenges

## 🎯 Weekly Challenge

Implement a complete algorithmic problem solver:
1. Choose a complex problem (e.g., traveling salesman, graph algorithms)
2. Implement multiple solution approaches
3. Compare performance and complexity
4. Create visualizations of the algorithms
5. Write comprehensive documentation

## ✅ Checklist

- [ ] Can implement advanced data structures
- [ ] Understand algorithm complexity analysis
- [ ] Can implement dynamic programming solutions
- [ ] Know various sorting algorithms
- [ ] Can work with graph algorithms
- [ ] Understand optimization techniques
- [ ] Can use memoization and generators
- [ ] Completed all practice exercises
- [ ] Finished mini-project
- [ ] Attempted weekly challenge

---

**Next Week**: [Week 14 - System Programming & Automation](./../week14/README.md)