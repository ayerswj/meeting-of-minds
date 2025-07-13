# Level 1: Week 4 - Collections & Basic Algorithms

## 🎯 Learning Objectives
By the end of this week, you will be able to:
- Master Python's built-in collections (lists, tuples, dictionaries, sets)
- Implement basic algorithms (searching, sorting, filtering)
- Use list comprehensions and generator expressions
- Work with nested data structures
- Apply algorithmic thinking to solve problems

## 📚 Theory

### What are Collections?
Collections are data structures that can hold multiple items. Python provides several built-in collection types, each optimized for different use cases.

### Collection Types Overview
- **Lists**: Ordered, mutable sequences
- **Tuples**: Ordered, immutable sequences
- **Dictionaries**: Key-value mappings
- **Sets**: Unordered collections of unique elements

## 📋 Lists - The Workhorse of Python

### Creating and Manipulating Lists
```python
# Creating lists
numbers = [1, 2, 3, 4, 5]
fruits = ['apple', 'banana', 'orange']
mixed = [1, 'hello', 3.14, True]
empty = []

# List operations
print(len(numbers))           # Length: 5
print(numbers[0])             # First element: 1
print(numbers[-1])            # Last element: 5
print(numbers[1:3])           # Slicing: [2, 3]
print(numbers[::2])           # Step slicing: [1, 3, 5]

# Modifying lists
numbers.append(6)             # Add to end
numbers.insert(0, 0)          # Insert at position
numbers.extend([7, 8])        # Extend with another list
numbers.remove(3)             # Remove first occurrence
popped = numbers.pop()        # Remove and return last element
del numbers[0]                # Delete by index
```

### List Methods Deep Dive
```python
numbers = [3, 1, 4, 1, 5, 9, 2, 6]

# Sorting
numbers.sort()                # In-place sort
sorted_numbers = sorted(numbers)  # Returns new list
numbers.sort(reverse=True)    # Reverse sort

# Searching
index = numbers.index(5)      # Find index of value
count = numbers.count(1)      # Count occurrences
exists = 5 in numbers         # Check membership

# Reversing
numbers.reverse()             # In-place reverse
reversed_numbers = list(reversed(numbers))  # Returns new list

# Copying
shallow_copy = numbers.copy()  # Shallow copy
deep_copy = numbers[:]        # Another way to copy
```

### List Comprehensions
```python
# Basic list comprehension
squares = [x**2 for x in range(10)]
print(squares)  # [0, 1, 4, 9, 16, 25, 36, 49, 64, 81]

# With condition
even_squares = [x**2 for x in range(10) if x % 2 == 0]
print(even_squares)  # [0, 4, 16, 36, 64]

# Nested comprehension
matrix = [[i+j for j in range(3)] for i in range(3)]
print(matrix)  # [[0, 1, 2], [1, 2, 3], [2, 3, 4]]

# Dictionary comprehension
word_lengths = {word: len(word) for word in ['hello', 'world', 'python']}
print(word_lengths)  # {'hello': 5, 'world': 5, 'python': 6}
```

## 🔗 Tuples - Immutable Sequences

### Tuple Basics
```python
# Creating tuples
coordinates = (10, 20)
person = ('Alice', 25, 'Engineer')
single_item = (42,)  # Note the comma
empty_tuple = ()

# Tuple operations
print(len(coordinates))       # Length
print(coordinates[0])         # Indexing
print(coordinates[0:2])       # Slicing
x, y = coordinates            # Unpacking
name, age, job = person       # Multiple unpacking

# Tuple methods
numbers = (1, 2, 2, 3, 2, 4)
print(numbers.count(2))       # Count occurrences: 3
print(numbers.index(3))       # Find index: 3
```

### When to Use Tuples
```python
# Return multiple values from functions
def get_coordinates():
    return (10, 20)

x, y = get_coordinates()

# Dictionary keys (tuples are immutable)
point_dict = {(0, 0): 'origin', (1, 1): 'diagonal'}

# Named tuples for better readability
from collections import namedtuple
Point = namedtuple('Point', ['x', 'y'])
p = Point(10, 20)
print(p.x, p.y)  # 10 20
```

## 📖 Dictionaries - Key-Value Storage

### Dictionary Operations
```python
# Creating dictionaries
person = {'name': 'Alice', 'age': 25, 'city': 'New York'}
empty_dict = {}
dict_from_list = dict([('a', 1), ('b', 2)])

# Accessing values
print(person['name'])         # Direct access
print(person.get('age'))      # Safe access
print(person.get('phone', 'Not found'))  # Default value

# Modifying dictionaries
person['phone'] = '555-1234'  # Add new key-value
person['age'] = 26            # Update existing
del person['city']            # Remove key-value
phone = person.pop('phone')   # Remove and return value

# Dictionary methods
print(person.keys())          # All keys
print(person.values())        # All values
print(person.items())         # All key-value pairs
```

### Advanced Dictionary Techniques
```python
# Dictionary comprehension
squares = {x: x**2 for x in range(5)}
print(squares)  # {0: 0, 1: 1, 2: 4, 3: 9, 4: 16}

# Merging dictionaries
dict1 = {'a': 1, 'b': 2}
dict2 = {'c': 3, 'd': 4}
merged = {**dict1, **dict2}   # Python 3.5+
print(merged)  # {'a': 1, 'b': 2, 'c': 3, 'd': 4}

# Nested dictionaries
students = {
    'Alice': {'age': 20, 'grade': 'A'},
    'Bob': {'age': 22, 'grade': 'B'}
}
print(students['Alice']['grade'])  # 'A'

# Default dictionaries
from collections import defaultdict
word_count = defaultdict(int)
words = ['apple', 'banana', 'apple', 'cherry']
for word in words:
    word_count[word] += 1
print(dict(word_count))  # {'apple': 2, 'banana': 1, 'cherry': 1}
```

## 🔢 Sets - Unique Collections

### Set Operations
```python
# Creating sets
numbers = {1, 2, 3, 4, 5}
fruits = set(['apple', 'banana', 'apple'])  # Duplicates removed
empty_set = set()

# Set operations
numbers.add(6)                # Add element
numbers.remove(1)             # Remove element (raises error if not found)
numbers.discard(10)           # Remove element (no error if not found)
popped = numbers.pop()        # Remove and return arbitrary element

# Set methods
set1 = {1, 2, 3, 4}
set2 = {3, 4, 5, 6}

print(set1.union(set2))       # {1, 2, 3, 4, 5, 6}
print(set1.intersection(set2)) # {3, 4}
print(set1.difference(set2))   # {1, 2}
print(set1.symmetric_difference(set2))  # {1, 2, 5, 6}

# Set operators
print(set1 | set2)            # Union
print(set1 & set2)            # Intersection
print(set1 - set2)            # Difference
print(set1 ^ set2)            # Symmetric difference
```

## 🔍 Basic Algorithms

### Searching Algorithms
```python
def linear_search(arr, target):
    """Linear search - O(n)"""
    for i, item in enumerate(arr):
        if item == target:
            return i
    return -1

def binary_search(arr, target):
    """Binary search - O(log n) - requires sorted array"""
    left, right = 0, len(arr) - 1
    
    while left <= right:
        mid = (left + right) // 2
        if arr[mid] == target:
            return mid
        elif arr[mid] < target:
            left = mid + 1
        else:
            right = mid - 1
    return -1

# Usage
numbers = [1, 3, 5, 7, 9, 11, 13, 15]
print(linear_search(numbers, 7))   # 3
print(binary_search(numbers, 7))   # 3
print(binary_search(numbers, 10))  # -1
```

### Sorting Algorithms
```python
def bubble_sort(arr):
    """Bubble sort - O(n²)"""
    n = len(arr)
    for i in range(n):
        for j in range(0, n - i - 1):
            if arr[j] > arr[j + 1]:
                arr[j], arr[j + 1] = arr[j + 1], arr[j]
    return arr

def selection_sort(arr):
    """Selection sort - O(n²)"""
    n = len(arr)
    for i in range(n):
        min_idx = i
        for j in range(i + 1, n):
            if arr[j] < arr[min_idx]:
                min_idx = j
        arr[i], arr[min_idx] = arr[min_idx], arr[i]
    return arr

# Usage
numbers = [64, 34, 25, 12, 22, 11, 90]
print(bubble_sort(numbers.copy()))    # [11, 12, 22, 25, 34, 64, 90]
print(selection_sort(numbers.copy())) # [11, 12, 22, 25, 34, 64, 90]
```

### Filtering and Mapping
```python
# Using built-in functions
numbers = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]

# Filter even numbers
evens = list(filter(lambda x: x % 2 == 0, numbers))
print(evens)  # [2, 4, 6, 8, 10]

# Square all numbers
squares = list(map(lambda x: x**2, numbers))
print(squares)  # [1, 4, 9, 16, 25, 36, 49, 64, 81, 100]

# Combine filter and map
even_squares = list(map(lambda x: x**2, filter(lambda x: x % 2 == 0, numbers)))
print(even_squares)  # [4, 16, 36, 64, 100]

# Using list comprehensions (more Pythonic)
evens = [x for x in numbers if x % 2 == 0]
squares = [x**2 for x in numbers]
even_squares = [x**2 for x in numbers if x % 2 == 0]
```

## 🎯 Practice Exercises

### Exercise 1: List Manipulation
Create functions to:
- Find the second largest number in a list
- Remove duplicates while preserving order
- Rotate a list by n positions

### Exercise 2: Dictionary Operations
Build a word frequency counter and:
- Find the most common word
- Create a reverse lookup (frequency → words)
- Filter words by minimum frequency

### Exercise 3: Set Operations
Implement set-based solutions for:
- Finding common elements between multiple lists
- Removing duplicates from a list
- Checking if two lists have any common elements

## 🚀 Mini-Project: Student Grade Management System

```python
class StudentGradeManager:
    def __init__(self):
        self.students = {}  # {student_id: {'name': str, 'grades': list}}
    
    def add_student(self, student_id, name):
        """Add a new student"""
        self.students[student_id] = {'name': name, 'grades': []}
    
    def add_grade(self, student_id, grade):
        """Add a grade for a student"""
        if student_id in self.students:
            self.students[student_id]['grades'].append(grade)
        else:
            print(f"Student {student_id} not found")
    
    def get_average(self, student_id):
        """Calculate average grade for a student"""
        if student_id in self.students:
            grades = self.students[student_id]['grades']
            return sum(grades) / len(grades) if grades else 0
        return None
    
    def get_class_average(self):
        """Calculate class average"""
        all_grades = []
        for student in self.students.values():
            all_grades.extend(student['grades'])
        return sum(all_grades) / len(all_grades) if all_grades else 0
    
    def get_top_students(self, n=3):
        """Get top n students by average grade"""
        student_averages = []
        for student_id, student in self.students.items():
            avg = self.get_average(student_id)
            student_averages.append((student_id, student['name'], avg))
        
        # Sort by average grade (descending)
        student_averages.sort(key=lambda x: x[2], reverse=True)
        return student_averages[:n]
    
    def get_grade_distribution(self):
        """Get distribution of grades"""
        all_grades = []
        for student in self.students.values():
            all_grades.extend(student['grades'])
        
        distribution = {}
        for grade in all_grades:
            if grade in distribution:
                distribution[grade] += 1
            else:
                distribution[grade] = 1
        return distribution
    
    def export_to_csv(self, filename):
        """Export student data to CSV"""
        import csv
        with open(filename, 'w', newline='') as file:
            writer = csv.writer(file)
            writer.writerow(['Student ID', 'Name', 'Grades', 'Average'])
            for student_id, student in self.students.items():
                grades_str = ','.join(map(str, student['grades']))
                avg = self.get_average(student_id)
                writer.writerow([student_id, student['name'], grades_str, avg])

# Usage
manager = StudentGradeManager()

# Add students
manager.add_student('S001', 'Alice')
manager.add_student('S002', 'Bob')
manager.add_student('S003', 'Charlie')

# Add grades
manager.add_grade('S001', 85)
manager.add_grade('S001', 90)
manager.add_grade('S002', 78)
manager.add_grade('S002', 82)
manager.add_grade('S003', 95)
manager.add_grade('S003', 88)

# Analysis
print(f"Class average: {manager.get_class_average():.2f}")
print(f"Top students: {manager.get_top_students()}")
print(f"Grade distribution: {manager.get_grade_distribution()}")

# Export data
manager.export_to_csv('student_grades.csv')
```

## 🎯 Advanced Concepts

### Generator Expressions
```python
# Memory efficient for large datasets
numbers = range(1000000)
squares_gen = (x**2 for x in numbers)  # Generator expression
squares_list = [x**2 for x in numbers]  # List comprehension

# Generator uses less memory
import sys
print(sys.getsizeof(squares_gen))      # Small size
print(sys.getsizeof(squares_list))     # Large size
```

### Nested Data Structures
```python
# Complex nested structure
company_data = {
    'departments': {
        'engineering': {
            'employees': [
                {'name': 'Alice', 'skills': ['Python', 'JavaScript'], 'projects': ['A', 'B']},
                {'name': 'Bob', 'skills': ['Java', 'Python'], 'projects': ['B', 'C']}
            ],
            'budget': 100000
        },
        'marketing': {
            'employees': [
                {'name': 'Charlie', 'skills': ['SEO', 'Content'], 'projects': ['D']}
            ],
            'budget': 50000
        }
    }
}

# Accessing nested data
python_developers = [
    emp['name'] for dept in company_data['departments'].values()
    for emp in dept['employees']
    if 'Python' in emp['skills']
]
print(python_developers)  # ['Alice', 'Bob']
```

## 📖 Additional Resources

- [Python Collections](https://docs.python.org/3/library/collections.html)
- [List Comprehensions](https://docs.python.org/3/tutorial/datastructures.html#list-comprehensions)
- [Dictionary Methods](https://docs.python.org/3/library/stdtypes.html#dict)
- [Set Operations](https://docs.python.org/3/library/stdtypes.html#set)

## 🎯 Weekly Challenge

Create a **Library Catalog System** that:
1. Stores books with title, author, ISBN, and availability
2. Allows searching by title, author, or ISBN
3. Tracks borrowed books and due dates
4. Generates reports (most popular books, overdue books)
5. Exports data to different formats (CSV, JSON)

## ✅ Checklist

- [ ] Can create and manipulate lists, tuples, dictionaries, and sets
- [ ] Understand when to use each collection type
- [ ] Can use list comprehensions and generator expressions
- [ ] Can implement basic searching and sorting algorithms
- [ ] Can work with nested data structures
- [ ] Understand algorithmic complexity basics
- [ ] Completed all practice exercises
- [ ] Finished mini-project
- [ ] Attempted weekly challenge

---

**Next Week**: [Week 5 - Object-Oriented Programming](./../level2/week5/README.md)