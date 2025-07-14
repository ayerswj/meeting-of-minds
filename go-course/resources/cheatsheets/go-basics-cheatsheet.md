# Go Basics Cheatsheet

## 📝 Variable Declaration

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

// Constants
const PI = 3.14159
const (
    StatusOK = 200
    StatusNotFound = 404
)
```

## 🔢 Basic Types

```go
// Numeric types
var intNum int = 42
var floatNum float64 = 3.14
var complexNum complex128 = 1 + 2i

// String type
var str string = "Hello, Go!"

// Boolean type
var isTrue bool = true

// Zero values
var zeroInt int        // 0
var zeroFloat float64  // 0.0
var zeroString string  // ""
var zeroBool bool      // false
```

## 🏗️ Composite Types

```go
// Arrays (fixed size)
numbers := [5]int{1, 2, 3, 4, 5}
var matrix [3][3]int

// Slices (dynamic size)
scores := []int{10, 20, 30, 40, 50}
scores = append(scores, 60)
firstThree := scores[:3]
lastThree := scores[len(scores)-3:]

// Maps
person := map[string]interface{}{
    "name": "Alice",
    "age":  30,
    "city": "New York",
}
person["occupation"] = "Engineer"
delete(person, "age")

// Structs
type Person struct {
    Name  string
    Age   int
    Email string
}
john := Person{Name: "John", Age: 25, Email: "john@example.com"}
```

## 🔄 Control Structures

```go
// If statements
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

// Switch statements
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

// For loops
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
for index, fruit := range fruits {
    fmt.Printf("%d: %s\n", index, fruit)
}

// Range loop (index only)
for i := range fruits {
    fmt.Println(i)
}

// Range loop (value only)
for _, fruit := range fruits {
    fmt.Println(fruit)
}
```

## 🔧 Functions

```go
// Basic function
func add(a, b int) int {
    return a + b
}

// Multiple return values
func divide(a, b int) (int, error) {
    if b == 0 {
        return 0, errors.New("division by zero")
    }
    return a / b, nil
}

// Named return values
func getPerson() (name string, age int) {
    name = "John"
    age = 25
    return // naked return
}

// Variadic functions
func sum(numbers ...int) int {
    total := 0
    for _, num := range numbers {
        total += num
    }
    return total
}

// Anonymous functions
result := func(x int) int {
    return x * x
}(5)

// Function types
type Operation func(int, int) int

func applyOperation(a, b int, op Operation) int {
    return op(a, b)
}
```

## 🏛️ Structs and Methods

```go
// Struct declaration
type Rectangle struct {
    Width  float64
    Height float64
}

// Method with value receiver
func (r Rectangle) Area() float64 {
    return r.Width * r.Height
}

// Method with pointer receiver
func (r *Rectangle) Scale(factor float64) {
    r.Width *= factor
    r.Height *= factor
}

// Embedded structs (composition)
type Person struct {
    Name string
    Age  int
}

type Employee struct {
    Person
    Department string
    Salary     float64
}

// Employee can access Person's fields
employee := Employee{
    Person:     Person{Name: "John", Age: 30},
    Department: "Engineering",
    Salary:     75000,
}
```

## 🔌 Interfaces

```go
// Interface declaration
type Shape interface {
    Area() float64
    Perimeter() float64
}

// Interface implementation (implicit)
func (r Rectangle) Area() float64 {
    return r.Width * r.Height
}

func (r Rectangle) Perimeter() float64 {
    return 2 * (r.Width + r.Height)
}

// Interface composition
type Reader interface {
    Read(p []byte) (n int, err error)
}

type Writer interface {
    Write(p []byte) (n int, err error)
}

type ReadWriter interface {
    Reader
    Writer
}

// Type assertions
var i interface{} = "hello"
str, ok := i.(string)
if ok {
    fmt.Println(str)
}

// Type switches
switch v := i.(type) {
case string:
    fmt.Printf("string: %s\n", v)
case int:
    fmt.Printf("int: %d\n", v)
default:
    fmt.Printf("unknown type: %T\n", v)
}
```

## 🔄 Goroutines and Channels

```go
// Goroutine creation
go func() {
    fmt.Println("Hello from goroutine!")
}()

// Channel creation
ch := make(chan int)
bufferedCh := make(chan int, 10)

// Channel operations
ch <- 42        // Send
value := <-ch   // Receive
close(ch)       // Close

// Select statement
select {
case msg1 := <-ch1:
    fmt.Println("Received from ch1:", msg1)
case ch2 <- value:
    fmt.Println("Sent to ch2")
case <-time.After(time.Second):
    fmt.Println("Timeout")
default:
    fmt.Println("No communication")
}

// Range over channel
for value := range ch {
    fmt.Println(value)
}
```

## 📦 Packages and Imports

```go
// Package declaration
package main

// Import statements
import (
    "fmt"
    "os"
    "strings"
)

import (
    "net/http"
    "encoding/json"
)

// Aliased imports
import (
    "fmt"
    "math/rand"
    r "math/rand"
)

// Dot imports (not recommended)
import . "fmt"

// Blank imports
import _ "image/png"
```

## 🛠️ Error Handling

```go
// Basic error handling
result, err := someFunction()
if err != nil {
    return err
}

// Custom errors
type ValidationError struct {
    Field string
    Message string
}

func (e ValidationError) Error() string {
    return fmt.Sprintf("validation error on %s: %s", e.Field, e.Message)
}

// Error wrapping (Go 1.13+)
if err != nil {
    return fmt.Errorf("failed to process: %w", err)
}

// Error unwrapping
var validationErr ValidationError
if errors.As(err, &validationErr) {
    fmt.Println("Validation error:", validationErr.Message)
}
```

## 🧪 Testing

```go
// Basic test
func TestAdd(t *testing.T) {
    result := add(2, 3)
    expected := 5
    if result != expected {
        t.Errorf("add(2, 3) = %d; want %d", result, expected)
    }
}

// Table-driven tests
func TestMultiply(t *testing.T) {
    tests := []struct {
        a, b, expected int
    }{
        {2, 3, 6},
        {0, 5, 0},
        {-2, 4, -8},
    }
    
    for _, test := range tests {
        result := multiply(test.a, test.b)
        if result != test.expected {
            t.Errorf("multiply(%d, %d) = %d; want %d", 
                test.a, test.b, result, test.expected)
        }
    }
}

// Benchmark
func BenchmarkAdd(b *testing.B) {
    for i := 0; i < b.N; i++ {
        add(1, 2)
    }
}
```

## 📋 Common Patterns

```go
// Builder pattern
type Config struct {
    Host string
    Port int
}

type ConfigBuilder struct {
    config Config
}

func (b *ConfigBuilder) Host(host string) *ConfigBuilder {
    b.config.Host = host
    return b
}

func (b *ConfigBuilder) Port(port int) *ConfigBuilder {
    b.config.Port = port
    return b
}

func (b *ConfigBuilder) Build() Config {
    return b.config
}

// Usage
config := NewConfigBuilder().
    Host("localhost").
    Port(8080).
    Build()

// Singleton pattern
type Singleton struct {
    data string
}

var instance *Singleton
var once sync.Once

func GetInstance() *Singleton {
    once.Do(func() {
        instance = &Singleton{data: "initialized"}
    })
    return instance
}
```

## 🚀 Best Practices

1. **Naming Conventions**
   - Use camelCase for variables and functions
   - Use PascalCase for exported names
   - Use UPPER_CASE for constants

2. **Error Handling**
   - Always check errors
   - Don't ignore errors
   - Use meaningful error messages

3. **Package Organization**
   - Keep packages focused and cohesive
   - Use meaningful package names
   - Avoid circular dependencies

4. **Concurrency**
   - Use goroutines for concurrent operations
   - Use channels for communication
   - Avoid shared state

5. **Testing**
   - Write tests for all public functions
   - Use table-driven tests
   - Aim for high test coverage