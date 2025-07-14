# Project 3: Control Structures

## 🎯 Learning Objectives

- Master conditional statements (`if`, `else`, `switch`)
- Understand loop constructs (`for`)
- Learn about control flow and branching
- Practice logical operators and comparisons
- Understand Go's unique control structure features

## 📚 Concepts Covered

- **If Statements**: Basic conditionals with `if`, `else if`, `else`
- **Switch Statements**: Multi-way branching with `switch`
- **For Loops**: The only loop construct in Go
- **Range Loops**: Iterating over collections
- **Logical Operators**: `&&`, `||`, `!`
- **Comparison Operators**: `==`, `!=`, `<`, `>`, `<=`, `>=`

## 🚀 Getting Started

### Setup
1. Navigate to this directory: `cd 03-control-structures`
2. Initialize Go module: `go mod init control-structures`
3. Create your Go file: `main.go`

## 📝 Exercises

### Exercise 1: Basic If Statements
Create a program that demonstrates various if-else conditions.

**Expected Output:**
```
=== If Statement Examples ===
Number: 15
15 is positive and less than 20
15 is not divisible by 3
```

### Exercise 2: Switch Statements
Demonstrate different types of switch statements in Go.

**Expected Output:**
```
=== Switch Statement Examples ===
Day: Monday
Monday is a weekday
Grade: B
B is a good grade
```

### Exercise 3: For Loops
Show different ways to use for loops in Go.

**Expected Output:**
```
=== For Loop Examples ===
Traditional for loop: 0 1 2 3 4
While-style loop: 5 4 3 2 1
Range loop over slice: apple banana cherry
Range loop with index: 0:apple 1:banana 2:cherry
```

### Exercise 4: Nested Control Structures
Create complex nested conditions and loops.

**Expected Output:**
```
=== Nested Control Structures ===
Processing numbers: 1-10
1 is odd and less than 5
2 is even and less than 5
3 is odd and less than 5
4 is even and less than 5
5 is odd and greater than or equal to 5
```

## 🔧 Code Examples

### If Statements
```go
// Basic if
if x > 0 {
    fmt.Println("x is positive")
}

// If-else
if x > 0 {
    fmt.Println("positive")
} else if x < 0 {
    fmt.Println("negative")
} else {
    fmt.Println("zero")
}

// If with initialization
if value := getValue(); value > 10 {
    fmt.Println("value is greater than 10")
}
```

### Switch Statements
```go
// Basic switch
switch day {
case "Monday", "Tuesday", "Wednesday", "Thursday", "Friday":
    fmt.Println("Weekday")
case "Saturday", "Sunday":
    fmt.Println("Weekend")
default:
    fmt.Println("Invalid day")
}

// Switch without expression
switch {
case age < 18:
    fmt.Println("Minor")
case age < 65:
    fmt.Println("Adult")
default:
    fmt.Println("Senior")
}
```

### For Loops
```go
// Traditional for loop
for i := 0; i < 5; i++ {
    fmt.Println(i)
}

// While-style loop
i := 0
for i < 5 {
    fmt.Println(i)
    i++
}

// Range loop
fruits := []string{"apple", "banana", "cherry"}
for _, fruit := range fruits {
    fmt.Println(fruit)
}
```

## 🧪 Testing Your Code

Run your program:
```bash
go run main.go
```

## 📋 Checklist

- [ ] Used if statements with various conditions
- [ ] Implemented if-else chains
- [ ] Used switch statements
- [ ] Demonstrated switch without expression
- [ ] Used traditional for loops
- [ ] Implemented while-style loops
- [ ] Used range loops
- [ ] Created nested control structures
- [ ] Used logical operators
- [ ] Completed all exercises

## 🎓 Key Takeaways

After completing this project, you should understand:
- How to control program flow with conditionals
- When to use if vs switch statements
- Different ways to iterate with for loops
- How to combine control structures
- Go's unique control structure features

## 🔗 Related Resources

- [Go Tour: If](https://tour.golang.org/flowcontrol/5)
- [Go Tour: For](https://tour.golang.org/flowcontrol/1)
- [Go Tour: Switch](https://tour.golang.org/flowcontrol/9)