# Level 1: Week 3 - Control Flow & Functions

## 🎯 Learning Objectives
By the end of this week, you will be able to:
- Use conditional statements (if/elif/else)
- Work with different types of loops
- Create and use functions
- Understand scope and parameters
- Handle program flow effectively

## 📚 Theory

### What is Control Flow?
Control flow determines the order in which program statements are executed. It allows programs to make decisions and repeat actions.

### What are Functions?
Functions are reusable blocks of code that perform specific tasks. They help organize code and avoid repetition.

## 🔀 Conditional Statements

### If Statements
```python
age = 18

if age >= 18:
    print("You are an adult")
```

### If-Else Statements
```python
age = 16

if age >= 18:
    print("You can vote")
else:
    print("You cannot vote yet")
```

### If-Elif-Else Statements
```python
score = 85

if score >= 90:
    grade = "A"
elif score >= 80:
    grade = "B"
elif score >= 70:
    grade = "C"
elif score >= 60:
    grade = "D"
else:
    grade = "F"

print(f"Your grade is: {grade}")
```

### Comparison Operators
```python
# Equal to
x == y

# Not equal to
x != y

# Greater than
x > y

# Less than
x < y

# Greater than or equal to
x >= y

# Less than or equal to
x <= y
```

### Logical Operators
```python
# AND - both conditions must be True
if age >= 18 and has_id:
    print("Can enter")

# OR - at least one condition must be True
if age >= 18 or has_parent:
    print("Can enter")

# NOT - reverses the condition
if not is_banned:
    print("Can enter")
```

## 🔄 Loops

### For Loops
```python
# Loop through a range
for i in range(5):
    print(i)  # Prints 0, 1, 2, 3, 4

# Loop through a list
fruits = ["apple", "banana", "orange"]
for fruit in fruits:
    print(fruit)

# Loop with enumerate
for index, fruit in enumerate(fruits):
    print(f"{index}: {fruit}")
```

### While Loops
```python
# Basic while loop
count = 0
while count < 5:
    print(count)
    count += 1

# While loop with break
while True:
    user_input = input("Enter 'quit' to exit: ")
    if user_input == 'quit':
        break
    print(f"You entered: {user_input}")
```

### Loop Control
```python
# Break - exit the loop
for i in range(10):
    if i == 5:
        break
    print(i)

# Continue - skip to next iteration
for i in range(10):
    if i % 2 == 0:
        continue
    print(i)  # Only prints odd numbers
```

## 🏗️ Functions

### Basic Function Definition
```python
def greet():
    print("Hello, World!")

# Call the function
greet()
```

### Functions with Parameters
```python
def greet(name):
    print(f"Hello, {name}!")

greet("Alice")
greet("Bob")
```

### Functions with Return Values
```python
def add(a, b):
    return a + b

result = add(5, 3)
print(result)  # 8
```

### Functions with Default Parameters
```python
def greet(name, greeting="Hello"):
    print(f"{greeting}, {name}!")

greet("Alice")           # Uses default greeting
greet("Bob", "Hi")       # Uses custom greeting
```

### Multiple Return Values
```python
def get_name_and_age():
    name = "Alice"
    age = 25
    return name, age

name, age = get_name_and_age()
print(f"{name} is {age} years old")
```

## 🎯 Practice Exercises

### Exercise 1: Grade Calculator
Create a function that takes a score and returns a letter grade.

### Exercise 2: Number Guessing Game
Create a simple number guessing game using loops and conditionals.

### Exercise 3: Temperature Converter
Create functions to convert between Celsius and Fahrenheit.

## 🚀 Mini-Project: Simple Calculator

Create a calculator with functions:

```python
def add(x, y):
    return x + y

def subtract(x, y):
    return x - y

def multiply(x, y):
    return x * y

def divide(x, y):
    if y == 0:
        return "Error: Cannot divide by zero"
    return x / y

def calculator():
    print("Simple Calculator")
    print("1. Add")
    print("2. Subtract")
    print("3. Multiply")
    print("4. Divide")
    
    choice = input("Enter choice (1-4): ")
    num1 = float(input("Enter first number: "))
    num2 = float(input("Enter second number: "))
    
    if choice == '1':
        print(f"Result: {add(num1, num2)}")
    elif choice == '2':
        print(f"Result: {subtract(num1, num2)}")
    elif choice == '3':
        print(f"Result: {multiply(num1, num2)}")
    elif choice == '4':
        print(f"Result: {divide(num1, num2)}")
    else:
        print("Invalid choice")

# Run the calculator
calculator()
```

## 🎯 Advanced Concepts

### Nested Functions
```python
def outer_function(x):
    def inner_function(y):
        return x + y
    return inner_function

add_five = outer_function(5)
result = add_five(3)  # 8
```

### Lambda Functions
```python
# Simple lambda function
square = lambda x: x ** 2
print(square(5))  # 25

# Lambda with multiple parameters
add = lambda x, y: x + y
print(add(3, 4))  # 7
```

### Function Documentation
```python
def calculate_area(length, width):
    """
    Calculate the area of a rectangle.
    
    Args:
        length (float): The length of the rectangle
        width (float): The width of the rectangle
    
    Returns:
        float: The area of the rectangle
    """
    return length * width
```

## 📖 Additional Resources

- [Python Control Flow](https://docs.python.org/3/tutorial/controlflow.html)
- [Python Functions](https://docs.python.org/3/tutorial/controlflow.html#defining-functions)
- [Python Loops](https://docs.python.org/3/tutorial/controlflow.html#for-statements)

## 🎯 Weekly Challenge

Create a program that:
1. Asks the user for their name and age
2. Uses functions to calculate different statistics (age in days, months, etc.)
3. Uses conditionals to give personalized messages based on age
4. Uses loops to display a countdown from their age to 0

## ✅ Checklist

- [ ] Can write if/elif/else statements
- [ ] Understand comparison and logical operators
- [ ] Can use for and while loops
- [ ] Know how to use break and continue
- [ ] Can create and call functions
- [ ] Understand function parameters and return values
- [ ] Can use default parameters
- [ ] Completed all practice exercises
- [ ] Finished mini-project
- [ ] Attempted weekly challenge

---

**Next Week**: [Week 4 - Collections & Basic Algorithms](./../week4/README.md)