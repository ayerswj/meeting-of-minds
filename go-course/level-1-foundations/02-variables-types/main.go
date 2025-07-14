package main

import (
	"fmt"
	"strconv"
)

func main() {
	fmt.Println("=== Go Variables and Types Project ===")

	// Exercise 1: Basic Variable Declaration
	fmt.Println("\n1. Basic Variable Declaration:")
	
	// Explicit declaration
	var name string = "John"
	var age int = 25
	var height float64 = 1.75
	var isStudent bool = true
	
	// Type inference with short declaration
	country := "USA"
	salary := 50000.0
	
	fmt.Printf("Name: %s (%T)\n", name, name)
	fmt.Printf("Age: %d (%T)\n", age, age)
	fmt.Printf("Height: %.2f (%T)\n", height, height)
	fmt.Printf("IsStudent: %t (%T)\n", isStudent, isStudent)
	fmt.Printf("Country: %s (%T)\n", country, country)
	fmt.Printf("Salary: %.2f (%T)\n", salary, salary)

	// Exercise 2: Type Conversion
	fmt.Println("\n2. Type Conversion Examples:")
	
	intValue := 42
	floatValue := float64(intValue)
	stringValue := strconv.Itoa(intValue)
	
	fmt.Printf("Integer: %d\n", intValue)
	fmt.Printf("Float: %.1f\n", floatValue)
	fmt.Printf("String: %q\n", stringValue)
	
	// String to int conversion
	strNum := "123"
	convertedNum, err := strconv.Atoi(strNum)
	if err == nil {
		fmt.Printf("String to Int: %d\n", convertedNum)
	}
	
	// Float to int conversion
	floatNum := 3.14
	intFromFloat := int(floatNum)
	fmt.Printf("Float to Int: %d\n", intFromFloat)

	// Exercise 3: Arrays and Slices
	fmt.Println("\n3. Arrays and Slices:")
	
	// Array (fixed size)
	numbers := [5]int{1, 2, 3, 4, 5}
	fmt.Printf("Array: %v\n", numbers)
	
	// Slice (dynamic size)
	scores := []int{10, 20, 30, 40, 50}
	fmt.Printf("Slice: %v\n", scores)
	fmt.Printf("Slice length: %d, capacity: %d\n", len(scores), cap(scores))
	
	// Append to slice
	scores = append(scores, 60)
	fmt.Printf("Appended slice: %v\n", scores)
	
	// Slice operations
	firstThree := scores[:3]
	lastThree := scores[len(scores)-3:]
	fmt.Printf("First three: %v\n", firstThree)
	fmt.Printf("Last three: %v\n", lastThree)

	// Exercise 4: Maps
	fmt.Println("\n4. Maps Example:")
	
	// Create a map
	person := map[string]interface{}{
		"name": "Alice",
		"age":  30,
		"city": "New York",
	}
	
	fmt.Printf("Person: %v\n", person)
	fmt.Printf("Name: %s\n", person["name"])
	fmt.Printf("Age: %d\n", person["age"])
	fmt.Printf("City: %s\n", person["city"])
	
	// Check if key exists
	if occupation, exists := person["occupation"]; exists {
		fmt.Printf("Occupation: %s\n", occupation)
	} else {
		fmt.Println("Occupation: Not specified")
	}
	
	// Add new key-value pair
	person["occupation"] = "Engineer"
	fmt.Printf("Updated person: %v\n", person)

	// Exercise 5: Constants and Zero Values
	fmt.Println("\n5. Constants and Zero Values:")
	
	const (
		PI       = 3.14159
		Language = "Go"
		Version  = "1.21"
	)
	
	fmt.Printf("PI: %f\n", PI)
	fmt.Printf("Language: %s\n", Language)
	fmt.Printf("Version: %s\n", Version)
	
	// Zero values demonstration
	var zeroInt int
	var zeroFloat float64
	var zeroString string
	var zeroBool bool
	var zeroSlice []int
	var zeroMap map[string]int
	
	fmt.Println("\nZero Values:")
	fmt.Printf("int: %d\n", zeroInt)
	fmt.Printf("float64: %f\n", zeroFloat)
	fmt.Printf("string: %q\n", zeroString)
	fmt.Printf("bool: %t\n", zeroBool)
	fmt.Printf("slice: %v (nil: %t)\n", zeroSlice, zeroSlice == nil)
	fmt.Printf("map: %v (nil: %t)\n", zeroMap, zeroMap == nil)

	fmt.Println("\n=== Project completed successfully! ===")
}