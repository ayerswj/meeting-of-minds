package main

import (
	"fmt"
	"math/rand"
	"time"
)

func main() {
	fmt.Println("=== Go Control Structures Project ===")

	// Exercise 1: Basic If Statements
	fmt.Println("\n1. Basic If Statement Examples:")
	demonstrateIfStatements()

	// Exercise 2: Switch Statements
	fmt.Println("\n2. Switch Statement Examples:")
	demonstrateSwitchStatements()

	// Exercise 3: For Loops
	fmt.Println("\n3. For Loop Examples:")
	demonstrateForLoops()

	// Exercise 4: Nested Control Structures
	fmt.Println("\n4. Nested Control Structures:")
	demonstrateNestedStructures()

	// Exercise 5: Advanced Control Flow
	fmt.Println("\n5. Advanced Control Flow:")
	demonstrateAdvancedControlFlow()

	fmt.Println("\n=== Project completed successfully! ===")
}

func demonstrateIfStatements() {
	numbers := []int{15, -5, 0, 42, 7, 21}

	for _, num := range numbers {
		fmt.Printf("Number: %d - ", num)
		
		if num > 0 {
			if num < 20 {
				fmt.Printf("%d is positive and less than 20\n", num)
			} else {
				fmt.Printf("%d is positive and greater than or equal to 20\n", num)
			}
			
			if num%3 == 0 {
				fmt.Printf("  %d is divisible by 3\n", num)
			} else {
				fmt.Printf("  %d is not divisible by 3\n", num)
			}
		} else if num < 0 {
			fmt.Printf("%d is negative\n", num)
		} else {
			fmt.Printf("%d is zero\n", num)
		}
	}

	// If with initialization
	if value := getRandomValue(); value > 50 {
		fmt.Printf("Random value %d is greater than 50\n", value)
	} else {
		fmt.Printf("Random value %d is less than or equal to 50\n", value)
	}
}

func demonstrateSwitchStatements() {
	// Basic switch
	days := []string{"Monday", "Tuesday", "Saturday", "Sunday", "Invalid"}
	
	for _, day := range days {
		fmt.Printf("Day: %s - ", day)
		switch day {
		case "Monday", "Tuesday", "Wednesday", "Thursday", "Friday":
			fmt.Printf("%s is a weekday\n", day)
		case "Saturday", "Sunday":
			fmt.Printf("%s is a weekend\n", day)
		default:
			fmt.Printf("%s is not a valid day\n", day)
		}
	}

	// Switch without expression
	ages := []int{15, 25, 45, 70}
	for _, age := range ages {
		fmt.Printf("Age: %d - ", age)
		switch {
		case age < 18:
			fmt.Printf("%d is a minor\n", age)
		case age < 30:
			fmt.Printf("%d is a young adult\n", age)
		case age < 65:
			fmt.Printf("%d is an adult\n", age)
		default:
			fmt.Printf("%d is a senior\n", age)
		}
	}

	// Switch with type assertion
	values := []interface{}{"hello", 42, 3.14, true}
	for _, value := range values {
		fmt.Printf("Value: %v - ", value)
		switch v := value.(type) {
		case string:
			fmt.Printf("String with length %d\n", len(v))
		case int:
			fmt.Printf("Integer: %d\n", v)
		case float64:
			fmt.Printf("Float: %.2f\n", v)
		case bool:
			fmt.Printf("Boolean: %t\n", v)
		default:
			fmt.Printf("Unknown type: %T\n", v)
		}
	}
}

func demonstrateForLoops() {
	fmt.Println("Traditional for loop:")
	for i := 0; i < 5; i++ {
		fmt.Printf("%d ", i)
	}
	fmt.Println()

	fmt.Println("While-style loop:")
	i := 5
	for i > 0 {
		fmt.Printf("%d ", i)
		i--
	}
	fmt.Println()

	fmt.Println("Range loop over slice:")
	fruits := []string{"apple", "banana", "cherry", "date", "elderberry"}
	for _, fruit := range fruits {
		fmt.Printf("%s ", fruit)
	}
	fmt.Println()

	fmt.Println("Range loop with index:")
	for index, fruit := range fruits {
		fmt.Printf("%d:%s ", index, fruit)
	}
	fmt.Println()

	fmt.Println("Range loop over map:")
	person := map[string]string{
		"name": "Alice",
		"age":  "30",
		"city": "New York",
	}
	for key, value := range person {
		fmt.Printf("%s:%s ", key, value)
	}
	fmt.Println()

	fmt.Println("Range loop over string:")
	text := "Go"
	for index, char := range text {
		fmt.Printf("%d:%c ", index, char)
	}
	fmt.Println()

	// Infinite loop with break
	fmt.Println("Infinite loop with break:")
	counter := 0
	for {
		if counter >= 3 {
			break
		}
		fmt.Printf("Loop %d ", counter+1)
		counter++
	}
	fmt.Println()

	// Loop with continue
	fmt.Println("Loop with continue:")
	for i := 1; i <= 10; i++ {
		if i%2 == 0 {
			continue
		}
		fmt.Printf("%d ", i)
	}
	fmt.Println()
}

func demonstrateNestedStructures() {
	fmt.Println("Processing numbers 1-10:")
	for i := 1; i <= 10; i++ {
		fmt.Printf("%d is ", i)
		
		if i%2 == 0 {
			fmt.Printf("even")
		} else {
			fmt.Printf("odd")
		}
		
		if i < 5 {
			fmt.Printf(" and less than 5")
		} else if i == 5 {
			fmt.Printf(" and equal to 5")
		} else {
			fmt.Printf(" and greater than 5")
		}
		
		// Nested switch
		switch {
		case i < 3:
			fmt.Printf(" (small)")
		case i < 7:
			fmt.Printf(" (medium)")
		default:
			fmt.Printf(" (large)")
		}
		
		fmt.Println()
	}

	// Nested loops
	fmt.Println("\nNested loops (multiplication table):")
	for i := 1; i <= 3; i++ {
		for j := 1; j <= 3; j++ {
			fmt.Printf("%d×%d=%d ", i, j, i*j)
		}
		fmt.Println()
	}
}

func demonstrateAdvancedControlFlow() {
	// Defer example
	fmt.Println("Defer example:")
	defer fmt.Println("This will be printed last")
	defer fmt.Println("This will be printed second to last")
	fmt.Println("This will be printed first")

	// Labels and goto (rarely used, but good to know)
	fmt.Println("\nLabel and goto example:")
	i := 0
start:
	if i < 3 {
		fmt.Printf("Iteration %d\n", i+1)
		i++
		goto start
	}

	// Fallthrough in switch
	fmt.Println("\nFallthrough example:")
	grade := "B"
	switch grade {
	case "A":
		fmt.Println("Excellent!")
		fallthrough
	case "B":
		fmt.Println("Good!")
		fallthrough
	case "C":
		fmt.Println("Average")
	default:
		fmt.Println("Needs improvement")
	}
}

func getRandomValue() int {
	rand.Seed(time.Now().UnixNano())
	return rand.Intn(100)
}