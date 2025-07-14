# Project 2: Variables and Types

## 🎯 Learning Objectives

- Understand Go's type system
- Learn variable declaration and initialization
- Master different data types in Go
- Understand type conversion and casting
- Learn about constants and literals

## 📚 Concepts Covered

- **Variable Declaration**: `var`, `:=`, and `const`
- **Basic Types**: `int`, `float64`, `string`, `bool`
- **Composite Types**: `array`, `slice`, `map`
- **Type Conversion**: Explicit type conversion
- **Zero Values**: Default values for types
- **Type Inference**: Go's automatic type detection

## 🚀 Getting Started

### Setup
1. Navigate to this directory: `cd 02-variables-types`
2. Initialize Go module: `go mod init variables-types`
3. Create your Go file: `main.go`

## 📝 Exercises

### Exercise 1: Basic Variable Declaration
Create variables of different types and demonstrate various declaration methods.

**Expected Output:**
```
=== Variable Declaration Examples ===
Name: John (string)
Age: 25 (int)
Height: 1.75 (float64)
IsStudent: true (bool)
```

### Exercise 2: Type Conversion
Demonstrate type conversion between different numeric types.

**Expected Output:**
```
=== Type Conversion Examples ===
Integer: 42
Float: 42.0
String: "42"
Float to Int: 42
String to Int: 42
```

### Exercise 3: Arrays and Slices
Create and manipulate arrays and slices.

**Expected Output:**
```
=== Arrays and Slices ===
Array: [1 2 3 4 5]
Slice: [10 20 30 40 50]
Slice length: 5, capacity: 5
Appended slice: [10 20 30 40 50 60]
```

### Exercise 4: Maps
Create and use maps for key-value storage.

**Expected Output:**
```
=== Maps Example ===
Person: map[name:Alice age:30 city:New York]
Name: Alice
Age: 30
City: New York
```

## 🔧 Code Examples

### Variable Declaration
```go
// Explicit declaration
var name string = "John"
var age int = 25

// Type inference
height := 1.75
isStudent := true

// Multiple declaration
var (
    firstName = "John"
    lastName  = "Doe"
    age       = 25
)
```

### Type Conversion
```go
// Int to float
intValue := 42
floatValue := float64(intValue)

// Float to int
floatNum := 3.14
intNum := int(floatNum)

// String to int
strNum := "123"
num, err := strconv.Atoi(strNum)
```

### Arrays and Slices
```go
// Array (fixed size)
numbers := [5]int{1, 2, 3, 4, 5}

// Slice (dynamic size)
scores := []int{10, 20, 30, 40, 50}
scores = append(scores, 60)
```

## 🧪 Testing Your Code

Run your program:
```bash
go run main.go
```

Run tests:
```bash
go test
```

## 📋 Checklist

- [ ] Declared variables using `var` keyword
- [ ] Used short variable declaration (`:=`)
- [ ] Created constants using `const`
- [ ] Demonstrated all basic types
- [ ] Performed type conversions
- [ ] Created arrays and slices
- [ ] Used maps for key-value storage
- [ ] Understood zero values
- [ ] Completed all exercises

## 🎓 Key Takeaways

After completing this project, you should understand:
- How to declare and initialize variables
- Go's type system and type safety
- When to use arrays vs slices
- How to work with maps
- Type conversion and casting

## 🔗 Related Resources

- [Go Tour: Variables](https://tour.golang.org/basics/8)
- [Go Tour: Types](https://tour.golang.org/basics/11)
- [Effective Go: Variables](https://golang.org/doc/effective_go.html#variables)