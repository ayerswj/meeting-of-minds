# Level 1: Week 1 - Python Basics & Environment Setup

## 🎯 Learning Objectives
By the end of this week, you will be able to:
- Set up a Python development environment
- Write and run your first Python program
- Understand basic syntax and structure
- Use print statements and comments
- Work with the Python interactive shell

## 📚 Theory

### What is Python?
Python is a high-level, interpreted programming language known for its simplicity and readability. It's used in web development, data science, artificial intelligence, automation, and much more.

### Why Python?
- **Beginner-friendly**: Clear, readable syntax
- **Versatile**: Used in many fields
- **Large community**: Extensive libraries and resources
- **High demand**: Excellent job opportunities

## 🛠️ Environment Setup

### Step 1: Install Python
1. **Windows**: Download from [python.org](https://www.python.org/downloads/)
2. **Mac**: Use Homebrew: `brew install python`
3. **Linux**: Usually pre-installed, or use package manager

### Step 2: Verify Installation
```bash
python --version
# or
python3 --version
```

### Step 3: Install VS Code
1. Download from [code.visualstudio.com](https://code.visualstudio.com/)
2. Install Python extension
3. Set up integrated terminal

## 💻 Your First Python Program

### Exercise 1: Hello World
Create a file called `hello_world.py`:

```python
# This is a comment - it doesn't run as code
print("Hello, World!")
print("Welcome to Python programming!")
```

### Exercise 2: Interactive Shell
Open terminal/command prompt and type:
```bash
python
# or
python3
```

Try these commands:
```python
>>> print("Hello from interactive mode!")
>>> 2 + 2
>>> "Python" + " is awesome"
>>> exit()
```

## 📝 Basic Syntax Rules

### 1. Comments
```python
# Single line comment
"""
Multi-line comment
or docstring
"""
```

### 2. Print Statements
```python
print("Hello")                    # Basic print
print("Hello", "World")           # Multiple items
print("Hello", end=" ")           # Custom ending
print("World")
```

### 3. Indentation
Python uses indentation to define code blocks:
```python
if True:
    print("This is indented")
    print("This too")
print("This is not indented")
```

## 🎯 Practice Exercises

### Exercise 1: Personal Introduction
Create a program that prints your name, age, and favorite programming language.

### Exercise 2: Simple Calculator
Create a program that prints the result of basic math operations.

### Exercise 3: ASCII Art
Create a program that prints a simple ASCII art pattern.

## 🚀 Mini-Project: Personal Profile Generator

Create a program that generates a personal profile card:

```python
# personal_profile.py
print("=" * 40)
print("        PERSONAL PROFILE")
print("=" * 40)
print("Name: [Your Name]")
print("Age: [Your Age]")
print("Occupation: [Your Job/Student]")
print("Favorite Language: Python")
print("=" * 40)
```

## 📖 Additional Resources

- [Python Official Tutorial](https://docs.python.org/3/tutorial/)
- [Python Style Guide (PEP 8)](https://www.python.org/dev/peps/pep-0008/)
- [VS Code Python Extension](https://marketplace.visualstudio.com/items?itemName=ms-python.python)

## 🎯 Weekly Challenge

Create a program that prints a calendar-style display for the current month using only print statements and basic formatting.

## ✅ Checklist

- [ ] Python installed and working
- [ ] VS Code set up with Python extension
- [ ] First "Hello World" program written
- [ ] Interactive shell explored
- [ ] All practice exercises completed
- [ ] Mini-project completed
- [ ] Weekly challenge attempted

---

**Next Week**: [Week 2 - Data Types & Variables](./../week2/README.md)