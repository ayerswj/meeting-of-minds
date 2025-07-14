package main

import (
	"context"
	"fmt"
	"math/rand"
	"sync"
	"time"
)

func main() {
	fmt.Println("=== Go Channels Project ===")

	// Exercise 1: Basic Channel Operations
	fmt.Println("\n1. Basic Channel Operations:")
	demonstrateBasicChannels()

	// Exercise 2: Buffered Channels
	fmt.Println("\n2. Buffered Channels:")
	demonstrateBufferedChannels()

	// Exercise 3: Channel Direction
	fmt.Println("\n3. Channel Direction:")
	demonstrateChannelDirection()

	// Exercise 4: Select Statement
	fmt.Println("\n4. Select Statement:")
	demonstrateSelectStatement()

	// Exercise 5: Channel Synchronization
	fmt.Println("\n5. Channel Synchronization:")
	demonstrateChannelSynchronization()

	// Exercise 6: Fan-out Fan-in Pattern
	fmt.Println("\n6. Fan-out Fan-in Pattern:")
	demonstrateFanOutFanIn()

	// Exercise 7: Worker Pool Pattern
	fmt.Println("\n7. Worker Pool Pattern:")
	demonstrateWorkerPool()

	// Exercise 8: Context and Cancellation
	fmt.Println("\n8. Context and Cancellation:")
	demonstrateContextCancellation()

	fmt.Println("\n=== Project completed successfully! ===")
}

// Basic channel operations
func demonstrateBasicChannels() {
	// Unbuffered channel
	ch := make(chan string)

	// Send in goroutine
	go func() {
		fmt.Println("Sending: Hello from goroutine")
		ch <- "Hello from goroutine"
		close(ch)
	}()

	// Receive in main
	msg := <-ch
	fmt.Println("Received:", msg)

	// Check if channel is closed
	if _, ok := <-ch; !ok {
		fmt.Println("Channel closed")
	}
}

// Buffered channels
func demonstrateBufferedChannels() {
	// Buffered channel with capacity 3
	ch := make(chan string, 3)

	// Send multiple values without blocking
	fmt.Println("Sending: Message 1")
	ch <- "Message 1"
	fmt.Println("Sending: Message 2")
	ch <- "Message 2"
	fmt.Println("Sending: Message 3")
	ch <- "Message 3"

	// Close the channel
	close(ch)

	// Receive all values
	for i := 0; i < 3; i++ {
		msg := <-ch
		fmt.Println("Received:", msg)
	}
}

// Channel direction
func demonstrateChannelDirection() {
	// Create a channel
	ch := make(chan string, 3)

	// Start producer (send-only channel)
	go producer(ch)

	// Start consumer (receive-only channel)
	go consumer(ch)

	// Wait for completion
	time.Sleep(time.Second)
}

// Send-only channel
func producer(ch chan<- string) {
	items := []string{"Item 1", "Item 2", "Item 3"}
	for _, item := range items {
		fmt.Println("Producer sending:", item)
		ch <- item
		time.Sleep(time.Millisecond * 100)
	}
	close(ch)
}

// Receive-only channel
func consumer(ch <-chan string) {
	for item := range ch {
		fmt.Println("Consumer received:", item)
		time.Sleep(time.Millisecond * 100)
	}
}

// Select statement
func demonstrateSelectStatement() {
	ch1 := make(chan string, 1)
	ch2 := make(chan string, 1)

	// Send data to channels
	go func() {
		time.Sleep(time.Millisecond * 100)
		ch1 <- "data1"
	}()

	go func() {
		time.Sleep(time.Millisecond * 200)
		ch2 <- "data2"
	}()

	// Use select to handle multiple channels
	for i := 0; i < 4; i++ {
		select {
		case msg1 := <-ch1:
			fmt.Println("Received from ch1:", msg1)
		case msg2 := <-ch2:
			fmt.Println("Received from ch2:", msg2)
		case <-time.After(time.Millisecond * 300):
			fmt.Println("Timeout occurred")
		default:
			fmt.Println("No communication ready")
		}
	}
}

// Channel synchronization
func demonstrateChannelSynchronization() {
	var wg sync.WaitGroup
	done := make(chan bool)

	// Start workers
	for i := 1; i <= 3; i++ {
		wg.Add(1)
		go worker(i, &wg, done)
	}

	// Wait for all workers to complete
	go func() {
		wg.Wait()
		close(done)
	}()

	// Wait for completion signal
	<-done
	fmt.Println("All workers completed")
}

func worker(id int, wg *sync.WaitGroup, done chan bool) {
	defer wg.Done()
	fmt.Printf("Worker %d starting\n", id)
	time.Sleep(time.Millisecond * 500)
	fmt.Printf("Worker %d completed\n", id)
}

// Fan-out Fan-in pattern
func demonstrateFanOutFanIn() {
	// Input channel
	input := make(chan int, 10)

	// Start producer
	go func() {
		defer close(input)
		for i := 1; i <= 3; i++ {
			fmt.Printf("Producer: %d\n", i)
			input <- i
			time.Sleep(time.Millisecond * 100)
		}
	}()

	// Fan-out: Multiple workers
	worker1 := worker(input)
	worker2 := worker(input)

	// Fan-in: Combine results
	output := fanIn(worker1, worker2)

	// Consumer
	for i := 0; i < 3; i++ {
		result := <-output
		fmt.Printf("Consumer received: %d\n", result)
	}
}

func worker(input <-chan int) <-chan int {
	output := make(chan int)
	go func() {
		defer close(output)
		for item := range input {
			fmt.Printf("Worker processing: %d\n", item)
			time.Sleep(time.Millisecond * 200)
			output <- item
		}
	}()
	return output
}

func fanIn(input1, input2 <-chan int) <-chan int {
	output := make(chan int)
	go func() {
		defer close(output)
		for {
			select {
			case item := <-input1:
				output <- item
			case item := <-input2:
				output <- item
			}
		}
	}()
	return output
}

// Worker pool pattern
func demonstrateWorkerPool() {
	const numJobs = 5
	const numWorkers = 3

	jobs := make(chan int, numJobs)
	results := make(chan int, numJobs)

	// Start workers
	for i := 1; i <= numWorkers; i++ {
		go workerPool(i, jobs, results)
	}

	// Send jobs
	for i := 1; i <= numJobs; i++ {
		jobs <- i
	}
	close(jobs)

	// Collect results
	for i := 1; i <= numJobs; i++ {
		result := <-results
		fmt.Printf("Job result: %d\n", result)
	}
}

func workerPool(id int, jobs <-chan int, results chan<- int) {
	for job := range jobs {
		fmt.Printf("Worker %d processing job %d\n", id, job)
		time.Sleep(time.Millisecond * 500)
		results <- job * 2
	}
}

// Context and cancellation
func demonstrateContextCancellation() {
	// Create context with timeout
	ctx, cancel := context.WithTimeout(context.Background(), 2*time.Second)
	defer cancel()

	// Start goroutine that respects context
	go func() {
		for {
			select {
			case <-ctx.Done():
				fmt.Println("Context cancelled:", ctx.Err())
				return
			default:
				fmt.Println("Working...")
				time.Sleep(time.Millisecond * 500)
			}
		}
	}()

	// Wait for context to be cancelled
	<-ctx.Done()
	fmt.Println("Main: Context cancelled")
}

// Additional utility functions
func demonstrateChannelRange() {
	ch := make(chan int, 5)

	// Send values
	go func() {
		defer close(ch)
		for i := 1; i <= 5; i++ {
			ch <- i
		}
	}()

	// Range over channel
	fmt.Println("Range over channel:")
	for value := range ch {
		fmt.Printf("Received: %d\n", value)
	}
}

func demonstrateChannelCapacity() {
	// Check channel capacity
	ch := make(chan int, 10)
	fmt.Printf("Channel capacity: %d\n", cap(ch))
	fmt.Printf("Channel length: %d\n", len(ch))

	// Send some values
	ch <- 1
	ch <- 2
	ch <- 3

	fmt.Printf("After sending, channel length: %d\n", len(ch))

	// Receive values
	<-ch
	<-ch

	fmt.Printf("After receiving, channel length: %d\n", len(ch))
	close(ch)
}

func demonstrateChannelNil() {
	var ch chan int // nil channel

	// Sending to nil channel blocks forever
	go func() {
		fmt.Println("Attempting to send to nil channel...")
		ch <- 1 // This will block forever
		fmt.Println("This will never be printed")
	}()

	// Receiving from nil channel blocks forever
	go func() {
		fmt.Println("Attempting to receive from nil channel...")
		<-ch // This will block forever
		fmt.Println("This will never be printed")
	}()

	// Wait a bit to see the blocking behavior
	time.Sleep(time.Second)
	fmt.Println("Main function continues...")
}