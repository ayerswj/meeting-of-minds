package main

import (
	"errors"
	"fmt"
	"math"
	"strings"
	"time"
)

func main() {
	fmt.Println("=== Go Functions Project ===")

	// Exercise 1: Basic Functions
	fmt.Println("\n1. Basic Functions:")
	demonstrateBasicFunctions()

	// Exercise 2: Multiple Return Values
	fmt.Println("\n2. Multiple Return Values:")
	demonstrateMultipleReturns()

	// Exercise 3: Variadic Functions
	fmt.Println("\n3. Variadic Functions:")
	demonstrateVariadicFunctions()

	// Exercise 4: Anonymous Functions and Closures
	fmt.Println("\n4. Anonymous Functions and Closures:")
	demonstrateAnonymousFunctions()

	// Exercise 5: Function Types and Higher-Order Functions
	fmt.Println("\n5. Function Types and Higher-Order Functions:")
	demonstrateFunctionTypes()

	// Exercise 6: Defer and Panic/Recover
	fmt.Println("\n6. Defer and Panic/Recover:")
	demonstrateDeferAndPanic()

	fmt.Println("\n=== Project completed successfully! ===")
}

// Basic function examples
func demonstrateBasicFunctions() {
	// Simple function call
	result := add(5, 3)
	fmt.Printf("Add(5, 3) = %d\n", result)

	// Function with string parameters
	greeting := greet("Alice", 25)
	fmt.Printf("Greet(\"Alice\", 25) = %s\n", greeting)

	// Function with multiple parameters of same type
	product := multiply(2, 3, 4)
	fmt.Printf("Multiply(2, 3, 4) = %d\n", product)

	// Function returning a struct
	person := getPerson()
	fmt.Printf("GetPerson() = %+v\n", person)

	// Function with no parameters
	currentTime := getCurrentTime()
	fmt.Printf("GetCurrentTime() = %s\n", currentTime)
}

// Multiple return values
func demonstrateMultipleReturns() {
	// Division with error handling
	result, err := divide(10, 2)
	if err != nil {
		fmt.Printf("Error: %v\n", err)
	} else {
		fmt.Printf("Divide(10, 2) = %d, error = %v\n", result, err)
	}

	// Division by zero
	result, err = divide(10, 0)
	if err != nil {
		fmt.Printf("Divide(10, 0) = %d, error = %v\n", result, err)
	}

	// Function with named return values
	name, age := getPersonInfo()
	fmt.Printf("GetPersonInfo() = name: %s, age: %d\n", name, age)

	// Function returning multiple values of different types
	number, text, flag := getMixedValues()
	fmt.Printf("GetMixedValues() = %d, %s, %t\n", number, text, flag)

	// Function returning coordinates
	x, y := getCoordinates()
	fmt.Printf("GetCoordinates() = (%d, %d)\n", x, y)
}

// Variadic functions
func demonstrateVariadicFunctions() {
	// Sum function with variable arguments
	total := sum(1, 2, 3, 4, 5)
	fmt.Printf("Sum(1, 2, 3, 4, 5) = %d\n", total)

	// Sum with no arguments
	total = sum()
	fmt.Printf("Sum() = %d\n", total)

	// Join strings
	result := join("Hello", "World", "Go", "Programming")
	fmt.Printf("Join(\"Hello\", \"World\", \"Go\", \"Programming\") = %s\n", result)

	// Find maximum value
	max := findMax(3, 7, 2, 9, 1, 5)
	fmt.Printf("FindMax(3, 7, 2, 9, 1, 5) = %d\n", max)

	// Calculate average
	avg := calculateAverage(85, 92, 78, 96, 88)
	fmt.Printf("CalculateAverage(85, 92, 78, 96, 88) = %.2f\n", avg)
}

// Anonymous functions and closures
func demonstrateAnonymousFunctions() {
	// Anonymous function assigned to variable
	square := func(x int) int {
		return x * x
	}
	fmt.Printf("Square(5) = %d\n", square(5))

	// Anonymous function called immediately
	result := func(x int) int {
		return x * x * x
	}(3)
	fmt.Printf("Anonymous cube function(3) = %d\n", result)

	// Closure example - counter
	counter := createCounter()
	fmt.Printf("Counter: %d\n", counter())
	fmt.Printf("Counter: %d\n", counter())
	fmt.Printf("Counter: %d\n", counter())

	// Another counter instance
	counter2 := createCounter()
	fmt.Printf("Counter2: %d\n", counter2())
	fmt.Printf("Counter2: %d\n", counter2())

	// Closure with parameters
	adder := createAdder(10)
	fmt.Printf("Adder(5) = %d\n", adder(5))
	fmt.Printf("Adder(3) = %d\n", adder(3))

	// Closure that captures multiple variables
	multiplier := createMultiplier(2)
	fmt.Printf("Multiplier(4) = %d\n", multiplier(4))
	fmt.Printf("Multiplier(7) = %d\n", multiplier(7))
}

// Function types and higher-order functions
func demonstrateFunctionTypes() {
	// Function type definition
	type Operation func(int, int) int

	// Functions that match the Operation type
	add := func(a, b int) int { return a + b }
	subtract := func(a, b int) int { return a - b }
	multiply := func(a, b int) int { return a * b }

	// Higher-order function that takes a function as parameter
	result := applyOperation(10, 5, add)
	fmt.Printf("ApplyOperation(10, 5, add) = %d\n", result)

	result = applyOperation(10, 5, subtract)
	fmt.Printf("ApplyOperation(10, 5, subtract) = %d\n", result)

	result = applyOperation(10, 5, multiply)
	fmt.Printf("ApplyOperation(10, 5, multiply) = %d\n", result)

	// Function that returns a function
	powerOf := createPowerFunction(2)
	fmt.Printf("PowerOf(3) = %d\n", powerOf(3))
	fmt.Printf("PowerOf(4) = %d\n", powerOf(4))

	// Function composition
	composed := compose(square, double)
	result = composed(3)
	fmt.Printf("Compose(square, double)(3) = %d\n", result)
}

// Defer and panic/recover
func demonstrateDeferAndPanic() {
	// Defer example
	fmt.Println("Demonstrating defer:")
	deferExample()

	// Panic and recover example
	fmt.Println("\nDemonstrating panic and recover:")
	safeFunction()

	// Defer with function calls
	fmt.Println("\nDefer with function calls:")
	deferWithFunctionCalls()
}

// Basic function implementations
func add(a, b int) int {
	return a + b
}

func greet(name string, age int) string {
	return fmt.Sprintf("Hello %s, you are %d years old!", name, age)
}

func multiply(a, b, c int) int {
	return a * b * c
}

func getPerson() Person {
	return Person{Name: "John", Age: 25}
}

func getCurrentTime() string {
	return time.Now().Format("2006-01-02 15:04:05")
}

// Multiple return value functions
func divide(a, b int) (int, error) {
	if b == 0 {
		return 0, errors.New("division by zero")
	}
	return a / b, nil
}

func getPersonInfo() (name string, age int) {
	name = "Alice"
	age = 30
	return // naked return
}

func getMixedValues() (int, string, bool) {
	return 42, "hello", true
}

func getCoordinates() (x, y int) {
	x = 10
	y = 20
	return
}

// Variadic functions
func sum(numbers ...int) int {
	total := 0
	for _, num := range numbers {
		total += num
	}
	return total
}

func join(parts ...string) string {
	return strings.Join(parts, " ")
}

func findMax(numbers ...int) int {
	if len(numbers) == 0 {
		return 0
	}
	max := numbers[0]
	for _, num := range numbers {
		if num > max {
			max = num
		}
	}
	return max
}

func calculateAverage(numbers ...float64) float64 {
	if len(numbers) == 0 {
		return 0
	}
	sum := 0.0
	for _, num := range numbers {
		sum += num
	}
	return sum / float64(len(numbers))
}

// Closure functions
func createCounter() func() int {
	count := 0
	return func() int {
		count++
		return count
	}
}

func createAdder(initial int) func(int) int {
	sum := initial
	return func(value int) int {
		sum += value
		return sum
	}
}

func createMultiplier(factor int) func(int) int {
	return func(value int) int {
		return value * factor
	}
}

// Higher-order functions
func applyOperation(a, b int, op func(int, int) int) int {
	return op(a, b)
}

func createPowerFunction(exponent int) func(int) int {
	return func(base int) int {
		return int(math.Pow(float64(base), float64(exponent)))
	}
}

func compose(f, g func(int) int) func(int) int {
	return func(x int) int {
		return f(g(x))
	}
}

func square(x int) int {
	return x * x
}

func double(x int) int {
	return x * 2
}

// Defer and panic functions
func deferExample() {
	fmt.Println("Starting defer example")
	defer fmt.Println("This will be printed last")
	defer fmt.Println("This will be printed second to last")
	fmt.Println("This will be printed first")
}

func safeFunction() {
	defer func() {
		if r := recover(); r != nil {
			fmt.Printf("Recovered from panic: %v\n", r)
		}
	}()
	
	fmt.Println("About to panic...")
	panic("This is a panic!")
}

func deferWithFunctionCalls() {
	defer func() {
		fmt.Println("Deferred function call")
	}()
	
	fmt.Println("Normal execution")
}

// Person struct for examples
type Person struct {
	Name string
	Age  int
}