# Level 1: Week 2 - Data Types & Variables

## 🎯 Learning Objectives
By the end of this week, you will be able to:
- Understand Python's basic data types
- Create and use variables effectively
- Perform operations on different data types
- Use type conversion functions
- Handle user input

## 📚 Theory

### What are Variables?
Variables are containers that store data values. Think of them as labeled boxes where you can put different types of information.

### Python's Basic Data Types

#### 1. Numbers
- **Integers (int)**: Whole numbers (1, 100, -5)
- **Floats (float)**: Decimal numbers (3.14, -0.001, 2.0)

#### 2. Strings (str)
- Text enclosed in quotes ("Hello", 'Python', """Multi-line""")

#### 3. Booleans (bool)
- True or False values

#### 4. None
- Represents absence of value

## 💻 Variables and Assignment

### Creating Variables
```python
# Variable assignment
name = "Alice"
age = 25
height = 5.6
is_student = True
```

### Variable Naming Rules
- Use letters, numbers, and underscores
- Start with letter or underscore
- Case sensitive
- Use descriptive names

```python
# Good names
user_name = "John"
age_2023 = 30
_private_var = "secret"

# Bad names
2name = "John"      # Can't start with number
user-name = "John"  # Can't use hyphens
```

## 🔢 Number Operations

### Basic Math
```python
a = 10
b = 3

print(a + b)    # Addition: 13
print(a - b)    # Subtraction: 7
print(a * b)    # Multiplication: 30
print(a / b)    # Division: 3.333...
print(a // b)   # Floor division: 3
print(a % b)    # Modulo: 1
print(a ** b)   # Exponentiation: 1000
```

### Math Functions
```python
import math

print(abs(-5))          # Absolute value: 5
print(round(3.7))       # Round: 4
print(math.sqrt(16))    # Square root: 4.0
print(math.pi)          # Pi constant
```

## 📝 String Operations

### String Creation
```python
single_quotes = 'Hello'
double_quotes = "World"
triple_quotes = """Multi-line
string"""
```

### String Methods
```python
text = "  Python Programming  "

print(text.strip())           # Remove whitespace
print(text.upper())           # Convert to uppercase
print(text.lower())           # Convert to lowercase
print(text.replace("Python", "Java"))
print(len(text))              # Get length
print(text.split())           # Split into list
```

### String Formatting
```python
name = "Alice"
age = 25

# f-strings (recommended)
print(f"My name is {name} and I'm {age} years old")

# .format() method
print("My name is {} and I'm {} years old".format(name, age))

# % operator (older style)
print("My name is %s and I'm %d years old" % (name, age))
```

## 🔄 Type Conversion

### Converting Between Types
```python
# String to number
age_str = "25"
age_int = int(age_str)
age_float = float(age_str)

# Number to string
number = 42
number_str = str(number)

# Boolean conversion
print(bool(1))      # True
print(bool(0))      # False
print(bool(""))     # False
print(bool("Hello")) # True
```

## 📥 User Input

### Getting Input from User
```python
# Basic input
name = input("What's your name? ")
print(f"Hello, {name}!")

# Input with type conversion
age = int(input("How old are you? "))
height = float(input("What's your height in meters? "))
```

## 🎯 Practice Exercises

### Exercise 1: Calculator with Variables
Create a calculator that uses variables for all operations.

### Exercise 2: String Manipulation
Create a program that takes a sentence and performs various string operations.

### Exercise 3: Type Conversion Practice
Write a program that demonstrates different type conversions.

## 🚀 Mini-Project: Personal Information Manager

Create a program that collects and displays personal information:

```python
# personal_info.py
print("PERSONAL INFORMATION MANAGER")
print("=" * 30)

# Collect information
name = input("Enter your name: ")
age = int(input("Enter your age: "))
height = float(input("Enter your height (meters): "))
favorite_color = input("Enter your favorite color: ")

# Display information
print("\nYOUR INFORMATION:")
print(f"Name: {name}")
print(f"Age: {age} years old")
print(f"Height: {height} meters")
print(f"Favorite Color: {favorite_color}")

# Calculate age in months
age_in_months = age * 12
print(f"Age in months: {age_in_months}")

# Create a formatted summary
summary = f"{name} is {age} years old and {height}m tall."
print(f"\nSummary: {summary}")
```

## 🎯 Advanced Concepts

### Multiple Assignment
```python
# Assign multiple variables at once
x, y, z = 1, 2, 3
a = b = c = 0
```

### Augmented Assignment
```python
count = 5
count += 1      # Same as count = count + 1
count -= 1      # Same as count = count - 1
count *= 2      # Same as count = count * 2
```

## 📖 Additional Resources

- [Python Data Types](https://docs.python.org/3/library/stdtypes.html)
- [String Methods](https://docs.python.org/3/library/stdtypes.html#string-methods)
- [Math Module](https://docs.python.org/3/library/math.html)

## 🎯 Weekly Challenge

Create a program that:
1. Asks for a person's name, age, and salary
2. Calculates their monthly salary
3. Determines if they can afford a $500,000 house (assuming 20% down payment)
4. Formats all output nicely

## ✅ Checklist

- [ ] Understand all basic data types
- [ ] Can create and use variables
- [ ] Know how to perform math operations
- [ ] Can manipulate strings
- [ ] Understand type conversion
- [ ] Can get user input
- [ ] Completed all practice exercises
- [ ] Finished mini-project
- [ ] Attempted weekly challenge

---

**Next Week**: [Week 3 - Control Flow & Functions](./../week3/README.md)