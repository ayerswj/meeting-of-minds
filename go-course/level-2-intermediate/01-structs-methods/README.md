# Project 1: Structs and Methods

## 🎯 Learning Objectives

- Understand struct declaration and initialization
- Learn about struct fields and tags
- Master method declaration and receivers
- Understand value vs pointer receivers
- Learn about embedded structs and composition
- Practice struct methods and interfaces

## 📚 Concepts Covered

- **Struct Declaration**: Defining custom types
- **Struct Fields**: Data members and their types
- **Struct Tags**: Metadata for struct fields
- **Method Receivers**: Value and pointer receivers
- **Embedded Structs**: Composition over inheritance
- **Method Sets**: Interface implementation
- **Struct Initialization**: Various ways to create structs

## 🚀 Getting Started

### Setup
1. Navigate to this directory: `cd 01-structs-methods`
2. Initialize Go module: `go mod init structs-methods`
3. Create your Go file: `main.go`

## 📝 Exercises

### Exercise 1: Basic Structs
Create and use basic structs with different field types.

**Expected Output:**
```
=== Basic Structs ===
Person: {John Doe 30 john@example.com}
Student: {Alice Smith 20 alice@university.edu Computer Science 3.8}
```

### Exercise 2: Methods with Value Receivers
Implement methods that work with copies of structs.

**Expected Output:**
```
=== Value Receiver Methods ===
Rectangle: {10 5}
Area: 50
Perimeter: 30
String representation: Rectangle{width: 10, height: 5}
```

### Exercise 3: Methods with Pointer Receivers
Implement methods that can modify the original struct.

**Expected Output:**
```
=== Pointer Receiver Methods ===
Bank Account: {12345 1000.00}
After deposit: {12345 1500.00}
After withdrawal: {12345 1200.00}
```

### Exercise 4: Embedded Structs
Demonstrate composition using embedded structs.

**Expected Output:**
```
=== Embedded Structs ===
Employee: {John Doe 30 john@company.com Engineering Senior 75000}
Full name: John Doe
Department: Engineering
Salary: $75,000.00
```

## 🔧 Code Examples

### Struct Declaration
```go
type Person struct {
    Name  string
    Age   int
    Email string
}

type Rectangle struct {
    Width  float64
    Height float64
}
```

### Method with Value Receiver
```go
func (r Rectangle) Area() float64 {
    return r.Width * r.Height
}

func (r Rectangle) Perimeter() float64 {
    return 2 * (r.Width + r.Height)
}
```

### Method with Pointer Receiver
```go
type BankAccount struct {
    AccountNumber string
    Balance       float64
}

func (b *BankAccount) Deposit(amount float64) {
    b.Balance += amount
}

func (b *BankAccount) Withdraw(amount float64) error {
    if amount > b.Balance {
        return errors.New("insufficient funds")
    }
    b.Balance -= amount
    return nil
}
```

### Embedded Structs
```go
type Employee struct {
    Person
    Department string
    Position   string
    Salary     float64
}

// Employee can use Person's methods
func (e Employee) FullName() string {
    return e.Name
}
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

- [ ] Created structs with different field types
- [ ] Implemented methods with value receivers
- [ ] Implemented methods with pointer receivers
- [ ] Used embedded structs for composition
- [ ] Added struct tags for metadata
- [ ] Created custom string representations
- [ ] Understood method sets
- [ ] Completed all exercises

## 🎓 Key Takeaways

After completing this project, you should understand:
- How to define and use structs
- When to use value vs pointer receivers
- How composition works in Go
- How methods relate to interfaces
- Struct initialization patterns

## 🔗 Related Resources

- [Go Tour: Structs](https://tour.golang.org/moretypes/2)
- [Go Tour: Methods](https://tour.golang.org/methods/1)
- [Effective Go: Structs](https://golang.org/doc/effective_go.html#structs)