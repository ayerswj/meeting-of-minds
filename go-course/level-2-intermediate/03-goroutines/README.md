# Project 3: Goroutines

## 🎯 Learning Objectives

- Understand what goroutines are and how they work
- Learn how to create and manage goroutines
- Understand the difference between goroutines and OS threads
- Master goroutine lifecycle and scheduling
- Learn about goroutine synchronization
- Practice concurrent programming patterns

## 📚 Concepts Covered

- **Goroutine Creation**: Using the `go` keyword
- **Goroutine Scheduling**: How Go runtime manages goroutines
- **Goroutine Lifecycle**: Creation, execution, and termination
- **Concurrency vs Parallelism**: Understanding the difference
- **Goroutine Communication**: Basic patterns
- **Goroutine Synchronization**: Coordinating goroutines

## 🚀 Getting Started

### Setup
1. Navigate to this directory: `cd 03-goroutines`
2. Initialize Go module: `go mod init goroutines`
3. Create your Go file: `main.go`

## 📝 Exercises

### Exercise 1: Basic Goroutines
Create simple goroutines and observe their behavior.

**Expected Output:**
```
=== Basic Goroutines ===
Main function started
Goroutine 1: Hello from goroutine!
Goroutine 2: Another goroutine message
Main function completed
```

### Exercise 2: Goroutine with Sleep
Demonstrate goroutine scheduling with time delays.

**Expected Output:**
```
=== Goroutine with Sleep ===
Starting goroutines...
Goroutine 1: Starting
Goroutine 2: Starting
Goroutine 1: After 1 second
Goroutine 2: After 2 seconds
All goroutines completed
```

### Exercise 3: Anonymous Goroutines
Use anonymous functions to create goroutines.

**Expected Output:**
```
=== Anonymous Goroutines ===
Starting anonymous goroutines...
Anonymous goroutine 1: Processing...
Anonymous goroutine 2: Processing...
Anonymous goroutine 1: Completed
Anonymous goroutine 2: Completed
```

### Exercise 4: Goroutine Counter
Create multiple goroutines that increment a shared counter.

**Expected Output:**
```
=== Goroutine Counter ===
Starting 5 goroutines...
Goroutine 1: Counter = 1
Goroutine 2: Counter = 2
Goroutine 3: Counter = 3
Goroutine 4: Counter = 4
Goroutine 5: Counter = 5
Final counter value: 5
```

## 🔧 Code Examples

### Basic Goroutine
```go
func sayHello() {
    fmt.Println("Hello from goroutine!")
}

func main() {
    go sayHello()
    fmt.Println("Hello from main")
    time.Sleep(time.Second)
}
```

### Anonymous Goroutine
```go
go func() {
    fmt.Println("Hello from anonymous goroutine!")
}()
```

### Multiple Goroutines
```go
func worker(id int) {
    fmt.Printf("Worker %d starting\n", id)
    time.Sleep(time.Second)
    fmt.Printf("Worker %d done\n", id)
}

func main() {
    for i := 1; i <= 3; i++ {
        go worker(i)
    }
    time.Sleep(2 * time.Second)
}
```

### Goroutine with Parameters
```go
func printMessage(msg string, delay time.Duration) {
    time.Sleep(delay)
    fmt.Println(msg)
}

func main() {
    go printMessage("First message", time.Second)
    go printMessage("Second message", 2*time.Second)
    time.Sleep(3 * time.Second)
}
```

## 🧪 Testing Your Code

Run your program:
```bash
go run main.go
```

## ⚠️ Important Notes

- **Goroutine Scheduling**: Goroutines are scheduled by the Go runtime, not the OS
- **Non-blocking**: The `go` keyword doesn't block the calling function
- **Main Function**: If the main function exits, all goroutines are terminated
- **Synchronization**: Use channels or sync primitives to coordinate goroutines

## 📋 Checklist

- [ ] Created basic goroutines
- [ ] Used anonymous goroutines
- [ ] Demonstrated goroutine scheduling
- [ ] Created multiple concurrent goroutines
- [ ] Understood goroutine lifecycle
- [ ] Used time.Sleep for demonstration
- [ ] Completed all exercises

## 🎓 Key Takeaways

After completing this project, you should understand:
- How to create and use goroutines
- The difference between concurrency and parallelism
- How goroutine scheduling works
- When to use goroutines
- Basic goroutine synchronization

## 🔗 Related Resources

- [Go Tour: Goroutines](https://tour.golang.org/concurrency/1)
- [Effective Go: Goroutines](https://golang.org/doc/effective_go.html#goroutines)
- [Go Concurrency Patterns](https://blog.golang.org/concurrency-patterns)