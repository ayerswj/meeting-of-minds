# Project 1: Hello World

## 🎯 Learning Objectives

- Understand Go program structure
- Learn about the `main` package and `main` function
- Use `fmt` package for output
- Run your first Go program
- Understand Go workspace setup

## 📚 Concepts Covered

- **Package Declaration**: Every Go file starts with a package declaration
- **Main Function**: Entry point of a Go program
- **Import Statement**: How to use external packages
- **Print Functions**: Different ways to output text
- **Go Modules**: Modern dependency management

## 🚀 Getting Started

### Prerequisites
- Go 1.21+ installed
- Basic understanding of command line

### Setup
1. Navigate to this directory: `cd 01-hello-world`
2. Initialize Go module: `go mod init hello-world`
3. Create your first Go file: `main.go`

## 📝 Exercises

### Exercise 1: Basic Hello World
Create a simple program that prints "Hello, World!" to the console.

**Expected Output:**
```
Hello, World!
```

### Exercise 2: Personalized Greeting
Modify the program to accept a name as input and print a personalized greeting.

**Expected Output:**
```
What's your name? Alice
Hello, Alice! Welcome to Go programming!
```

### Exercise 3: Multiple Greetings
Create a program that prints greetings in multiple languages.

**Expected Output:**
```
Hello, World! (English)
Hola, Mundo! (Spanish)
Bonjour, le Monde! (French)
Ciao, Mondo! (Italian)
```

## 🔧 Code Examples

### Basic Hello World
```go
package main

import "fmt"

func main() {
    fmt.Println("Hello, World!")
}
```

### Using Printf
```go
package main

import "fmt"

func main() {
    name := "Go"
    fmt.Printf("Hello, %s!\n", name)
}
```

## 🧪 Testing Your Code

Run your program:
```bash
go run main.go
```

Build an executable:
```bash
go build -o hello main.go
./hello
```

## 📋 Checklist

- [ ] Created `main.go` file
- [ ] Added package declaration
- [ ] Imported `fmt` package
- [ ] Implemented `main` function
- [ ] Used `fmt.Println()` to output text
- [ ] Program runs without errors
- [ ] Completed all exercises

## 🎓 Next Steps

After completing this project, you should understand:
- How to structure a basic Go program
- The role of the `main` package and function
- How to use the `fmt` package for output
- How to run Go programs

## 🔗 Related Resources

- [Go Tour: Hello World](https://tour.golang.org/welcome/1)
- [fmt package documentation](https://pkg.go.dev/fmt)
- [Go by Example: Hello World](https://gobyexample.com/hello-world)