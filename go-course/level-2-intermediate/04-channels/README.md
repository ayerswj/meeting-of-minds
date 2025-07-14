# Project 4: Channels

## 🎯 Learning Objectives

- Understand channel communication in Go
- Master buffered and unbuffered channels
- Learn channel direction and select statements
- Implement channel synchronization patterns
- Understand channel patterns like fan-out and fan-in
- Practice channel-based concurrency patterns
- Learn about channel timeouts and cancellation

## 📚 Concepts Covered

- **Channel Creation**: Buffered and unbuffered channels
- **Channel Operations**: Send, receive, and close
- **Channel Direction**: Send-only, receive-only, and bidirectional
- **Select Statement**: Non-blocking channel operations
- **Channel Synchronization**: Coordinating goroutines
- **Channel Patterns**: Fan-out, fan-in, worker pools
- **Context and Cancellation**: Graceful shutdown
- **Channel Best Practices**: Avoiding deadlocks and leaks

## 🚀 Getting Started

### Setup
1. Navigate to this directory: `cd 04-channels`
2. Initialize Go module: `go mod init channels`
3. Create your Go file: `main.go`

## 📝 Exercises

### Exercise 1: Basic Channel Operations
Create and use basic channels for communication.

**Expected Output:**
```
=== Basic Channel Operations ===
Sending: Hello from goroutine
Received: Hello from goroutine
Channel closed
```

### Exercise 2: Buffered Channels
Demonstrate buffered channel behavior.

**Expected Output:**
```
=== Buffered Channels ===
Sending: Message 1
Sending: Message 2
Sending: Message 3
Received: Message 1
Received: Message 2
Received: Message 3
```

### Exercise 3: Channel Direction
Use directional channels for specific purposes.

**Expected Output:**
```
=== Channel Direction ===
Producer sending: Item 1
Producer sending: Item 2
Producer sending: Item 3
Consumer received: Item 1
Consumer received: Item 2
Consumer received: Item 3
```

### Exercise 4: Select Statement
Use select for non-blocking channel operations.

**Expected Output:**
```
=== Select Statement ===
Received from ch1: data1
Received from ch2: data2
Timeout occurred
No communication ready
```

### Exercise 5: Channel Synchronization
Coordinate multiple goroutines using channels.

**Expected Output:**
```
=== Channel Synchronization ===
Worker 1 starting
Worker 2 starting
Worker 3 starting
Worker 1 completed
Worker 2 completed
Worker 3 completed
All workers completed
```

### Exercise 6: Fan-out Fan-in Pattern
Implement the fan-out, fan-in concurrency pattern.

**Expected Output:**
```
=== Fan-out Fan-in Pattern ===
Producer: 1
Producer: 2
Producer: 3
Worker 1 processing: 1
Worker 2 processing: 2
Worker 1 processing: 3
Consumer received: 2
Consumer received: 1
Consumer received: 3
```

## 🔧 Code Examples

### Basic Channel Operations
```go
// Unbuffered channel
ch := make(chan string)

// Send in goroutine
go func() {
    ch <- "Hello from goroutine"
    close(ch)
}()

// Receive in main
msg := <-ch
fmt.Println("Received:", msg)
```

### Buffered Channel
```go
// Buffered channel with capacity 3
ch := make(chan string, 3)

// Send multiple values
ch <- "Message 1"
ch <- "Message 2"
ch <- "Message 3"

// Receive all values
for i := 0; i < 3; i++ {
    msg := <-ch
    fmt.Println("Received:", msg)
}
```

### Channel Direction
```go
// Send-only channel
func producer(ch chan<- string) {
    ch <- "Item 1"
    ch <- "Item 2"
    close(ch)
}

// Receive-only channel
func consumer(ch <-chan string) {
    for item := range ch {
        fmt.Println("Received:", item)
    }
}
```

### Select Statement
```go
select {
case msg1 := <-ch1:
    fmt.Println("Received from ch1:", msg1)
case ch2 <- value:
    fmt.Println("Sent to ch2")
case <-time.After(time.Second):
    fmt.Println("Timeout")
default:
    fmt.Println("No communication ready")
}
```

### Worker Pool Pattern
```go
func worker(id int, jobs <-chan int, results chan<- int) {
    for job := range jobs {
        fmt.Printf("Worker %d processing job %d\n", id, job)
        time.Sleep(time.Millisecond * 500)
        results <- job * 2
    }
}

func main() {
    jobs := make(chan int, 100)
    results := make(chan int, 100)

    // Start workers
    for i := 1; i <= 3; i++ {
        go worker(i, jobs, results)
    }

    // Send jobs
    for i := 1; i <= 9; i++ {
        jobs <- i
    }
    close(jobs)

    // Collect results
    for i := 1; i <= 9; i++ {
        result := <-results
        fmt.Printf("Result: %d\n", result)
    }
}
```

## 🧪 Testing Your Code

Run your program:
```bash
go run main.go
```

## ⚠️ Important Notes

- **Unbuffered Channels**: Block until sender and receiver are ready
- **Buffered Channels**: Block only when buffer is full or empty
- **Channel Closing**: Only the sender should close a channel
- **Range over Channel**: Automatically stops when channel is closed
- **Select**: Provides non-blocking channel operations
- **Context**: Use for cancellation and timeouts

## 📋 Checklist

- [ ] Created unbuffered channels
- [ ] Used buffered channels
- [ ] Implemented directional channels
- [ ] Used select statements
- [ ] Created channel synchronization
- [ ] Implemented fan-out fan-in pattern
- [ ] Used context for cancellation
- [ ] Avoided deadlocks and leaks
- [ ] Completed all exercises

## 🎓 Key Takeaways

After completing this project, you should understand:
- How channels enable communication between goroutines
- When to use buffered vs unbuffered channels
- How to coordinate multiple goroutines
- Common channel patterns and best practices
- How to avoid common channel pitfalls

## 🔗 Related Resources

- [Go Tour: Channels](https://tour.golang.org/concurrency/2)
- [Go Tour: Select](https://tour.golang.org/concurrency/5)
- [Effective Go: Channels](https://golang.org/doc/effective_go.html#channels)