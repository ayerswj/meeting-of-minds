package main

import (
	"bufio"
	"fmt"
	"os"
	"strings"
)

func main() {
	fmt.Println("=== Go Hello World Project ===")
	
	// Exercise 1: Basic Hello World
	fmt.Println("\n1. Basic Hello World:")
	fmt.Println("Hello, World!")
	
	// Exercise 2: Personalized Greeting
	fmt.Println("\n2. Personalized Greeting:")
	fmt.Print("What's your name? ")
	reader := bufio.NewReader(os.Stdin)
	name, err := reader.ReadString('\n')
	if err != nil {
		fmt.Println("Error reading input:", err)
		return
	}
	name = strings.TrimSpace(name)
	fmt.Printf("Hello, %s! Welcome to Go programming!\n", name)
	
	// Exercise 3: Multiple Greetings
	fmt.Println("\n3. Multiple Greetings:")
	greetings := map[string]string{
		"English": "Hello, World!",
		"Spanish": "Hola, Mundo!",
		"French":  "Bonjour, le Monde!",
		"Italian": "Ciao, Mondo!",
		"German":  "Hallo, Welt!",
		"Japanese": "こんにちは、世界！",
	}
	
	for language, greeting := range greetings {
		fmt.Printf("%s (%s)\n", greeting, language)
	}
	
	fmt.Println("\n=== Project completed successfully! ===")
}