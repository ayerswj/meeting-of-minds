# Project 4: Functions

## 🎯 Learning Objectives

- Understand function declaration and syntax
- Learn about function parameters and return values
- Master multiple return values (Go's unique feature)
- Understand function types and first-class functions
- Learn about anonymous functions and closures
- Practice variadic functions

## 📚 Concepts Covered

- **Function Declaration**: Basic function syntax
- **Parameters**: Single and multiple parameters
- **Return Values**: Single, multiple, and named returns
- **Function Types**: Functions as values
- **Anonymous Functions**: Lambda-like functions
- **Closures**: Functions that capture variables
- **Variadic Functions**: Functions with variable arguments
- **Defer**: Postponed function execution

## 🚀 Getting Started

### Setup
1. Navigate to this directory: `cd 04-functions`
2. Initialize Go module: `go mod init functions`
3. Create your Go file: `main.go`

## 📝 Exercises

### Exercise 1: Basic Functions
Create functions with different parameter and return types.

**Expected Output:**
```
=== Basic Functions ===
Add(5, 3) = 8
Greet("Alice") = Hello, Alice!
GetPerson() = {John 25}
```

### Exercise 2: Multiple Return Values
Demonstrate Go's multiple return value feature.

**Expected Output:**
```
=== Multiple Return Values ===
Divide(10, 2) = 5, nil
Divide(10, 0) = 0, division by zero
GetCoordinates() = (10, 20)
```

### Exercise 3: Variadic Functions
Create functions that accept variable numbers of arguments.

**Expected Output:**
```
=== Variadic Functions ===
Sum(1, 2, 3, 4, 5) = 15
Sum() = 0
Join("Hello", "World", "Go") = Hello World Go
```

### Exercise 4: Anonymous Functions and Closures
Demonstrate anonymous functions and closures.

**Expected Output:**
```
=== Anonymous Functions and Closures ===
Anonymous function result: 25
Counter: 1
Counter: 2
Counter: 3
```

## 🔧 Code Examples

### Basic Function Declaration
```go
// Simple function
func add(a, b int) int {
    return a + b
}

// Function with multiple parameters
func greet(name string, age int) string {
    return fmt.Sprintf("Hello %s, you are %d years old", name, age)
}

// Function with multiple return values
func divide(a, b int) (int, error) {
    if b == 0 {
        return 0, errors.New("division by zero")
    }
    return a / b, nil
}
```

### Named Return Values
```go
func getPerson() (name string, age int) {
    name = "John"
    age = 25
    return // naked return
}
```

### Variadic Functions
```go
func sum(numbers ...int) int {
    total := 0
    for _, num := range numbers {
        total += num
    }
    return total
}
```

### Anonymous Functions and Closures
```go
// Anonymous function
result := func(x int) int {
    return x * x
}(5)

// Closure
func counter() func() int {
    count := 0
    return func() int {
        count++
        return count
    }
}
```

## 🧪 Testing Your Code

Run your program:
```bash
go run main.go
```

## 📋 Checklist

- [ ] Created functions with different parameter types
- [ ] Used multiple return values
- [ ] Implemented named return values
- [ ] Created variadic functions
- [ ] Used anonymous functions
- [ ] Implemented closures
- [ ] Used defer statements
- [ ] Understood function types
- [ ] Completed all exercises

## 🎓 Key Takeaways

After completing this project, you should understand:
- How to declare and use functions
- Go's unique multiple return value feature
- When and how to use variadic functions
- How closures work in Go
- Function types and first-class functions

## 🔗 Related Resources

- [Go Tour: Functions](https://tour.golang.org/basics/4)
- [Go Tour: Multiple Results](https://tour.golang.org/basics/6)
- [Effective Go: Functions](https://golang.org/doc/effective_go.html#functions)