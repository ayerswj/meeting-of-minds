# Lesson 1: Variables and Data Types

## Learning Objectives
- Understand what variables are and why we use them
- Learn about Java's primitive data types
- Practice declaring and initializing variables
- Understand type conversion and casting

## What are Variables?
Variables are containers that store data values. Think of them as labeled boxes where you can put different types of information.

## Java Primitive Data Types

### 1. Integer Types
- `byte`: 8-bit signed integer (-128 to 127)
- `short`: 16-bit signed integer (-32,768 to 32,767)
- `int`: 32-bit signed integer (-2^31 to 2^31-1) - **Most commonly used**
- `long`: 64-bit signed integer (-2^63 to 2^63-1)

### 2. Floating-Point Types
- `float`: 32-bit floating-point number
- `double`: 64-bit floating-point number - **Most commonly used**

### 3. Character Type
- `char`: 16-bit Unicode character

### 4. Boolean Type
- `boolean`: true or false

## Variable Declaration Syntax
```java
dataType variableName = value;
```

## Examples

### Basic Variable Declaration
```java
// Integer variables
int age = 25;
int temperature = -5;
long population = 7800000000L; // Note the 'L' suffix for long

// Floating-point variables
double price = 19.99;
float pi = 3.14f; // Note the 'f' suffix for float

// Character variable
char grade = 'A';

// Boolean variable
boolean isStudent = true;
```

### Multiple Variable Declaration
```java
// Declaring multiple variables of the same type
int x = 10, y = 20, z = 30;

// Declaring without initialization
int count;
count = 5; // Initialization later
```

## Type Conversion (Casting)

### Implicit Casting (Widening)
Java automatically converts smaller data types to larger ones:
```java
int myInt = 9;
double myDouble = myInt; // Automatic conversion: int to double
System.out.println(myInt);      // Outputs: 9
System.out.println(myDouble);   // Outputs: 9.0
```

### Explicit Casting (Narrowing)
You must manually convert larger data types to smaller ones:
```java
double myDouble = 9.78;
int myInt = (int) myDouble; // Manual casting: double to int
System.out.println(myDouble);   // Outputs: 9.78
System.out.println(myInt);      // Outputs: 9
```

## Exercises

### Exercise 1: Variable Declaration
Create variables for the following scenarios:
- Your age
- Your height in meters
- Your favorite letter
- Whether you like programming
- The current year

### Exercise 2: Type Conversion
Practice implicit and explicit casting:
- Convert an int to double
- Convert a double to int (observe data loss)
- Convert a char to int (ASCII value)

### Exercise 3: Calculations
Create variables and perform calculations:
- Calculate the area of a circle (π * radius²)
- Convert temperature from Celsius to Fahrenheit
- Calculate your age in days

## Practice Files
- `VariablesDemo.java` - Basic variable examples
- `TypeConversion.java` - Casting examples
- `Exercises.java` - Practice exercises

## Key Takeaways
1. Variables must be declared with a specific data type
2. Choose the appropriate data type for your data
3. Be careful with type conversion to avoid data loss
4. Use meaningful variable names
5. Initialize variables when possible

## Next Steps
After completing this lesson, move on to:
- Control Flow (if statements, loops)
- Operators and expressions
- String manipulation

## Additional Resources
- [Java Variables Tutorial](https://docs.oracle.com/javase/tutorial/java/nutsandbolts/variables.html)
- [Java Data Types](https://docs.oracle.com/javase/tutorial/java/nutsandbolts/datatypes.html)