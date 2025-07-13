# Week 4: Arrays and Collections Framework

## Learning Objectives
- Master array declaration, initialization, and manipulation
- Understand the Collections Framework hierarchy
- Learn to use ArrayList, LinkedList, HashSet, and HashMap
- Implement basic algorithms with collections
- Apply collections in real-world scenarios

## Theory

### 1. Arrays

#### Array Declaration and Initialization
```java
// Declaration
int[] numbers;
String[] names;

// Initialization
int[] numbers = {1, 2, 3, 4, 5};
String[] names = new String[5];
int[] scores = new int[]{85, 92, 78, 96, 88};
```

#### Array Operations
```java
// Accessing elements
int firstNumber = numbers[0];

// Modifying elements
numbers[2] = 10;

// Length property
int length = numbers.length;

// Iterating through arrays
for (int i = 0; i < numbers.length; i++) {
    System.out.println(numbers[i]);
}

// Enhanced for loop
for (int number : numbers) {
    System.out.println(number);
}
```

### 2. Collections Framework

#### List Interface
- **ArrayList**: Dynamic array implementation
- **LinkedList**: Doubly-linked list implementation

#### Set Interface
- **HashSet**: Unordered collection with no duplicates
- **TreeSet**: Sorted collection with no duplicates

#### Map Interface
- **HashMap**: Key-value pairs with no order
- **TreeMap**: Sorted key-value pairs

## Code Examples

### Example 1: Array Operations
```java
public class ArrayOperations {
    public static void main(String[] args) {
        // Initialize array
        int[] numbers = {23, 45, 12, 67, 34, 89, 56};
        
        // Find maximum value
        int max = numbers[0];
        for (int i = 1; i < numbers.length; i++) {
            if (numbers[i] > max) {
                max = numbers[i];
            }
        }
        System.out.println("Maximum value: " + max);
        
        // Calculate average
        int sum = 0;
        for (int number : numbers) {
            sum += number;
        }
        double average = (double) sum / numbers.length;
        System.out.println("Average: " + average);
        
        // Reverse array
        System.out.println("Original array:");
        printArray(numbers);
        
        System.out.println("Reversed array:");
        reverseArray(numbers);
        printArray(numbers);
    }
    
    public static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
    
    public static void reverseArray(int[] arr) {
        int left = 0;
        int right = arr.length - 1;
        
        while (left < right) {
            int temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
            left++;
            right--;
        }
    }
}
```

### Example 2: ArrayList Operations
```java
import java.util.ArrayList;
import java.util.Collections;

public class ArrayListDemo {
    public static void main(String[] args) {
        ArrayList<String> fruits = new ArrayList<>();
        
        // Adding elements
        fruits.add("Apple");
        fruits.add("Banana");
        fruits.add("Orange");
        fruits.add("Mango");
        
        System.out.println("Original list: " + fruits);
        
        // Accessing elements
        System.out.println("First fruit: " + fruits.get(0));
        
        // Modifying elements
        fruits.set(1, "Pineapple");
        System.out.println("After modification: " + fruits);
        
        // Removing elements
        fruits.remove("Orange");
        System.out.println("After removal: " + fruits);
        
        // Sorting
        Collections.sort(fruits);
        System.out.println("Sorted list: " + fruits);
        
        // Size and contains
        System.out.println("Size: " + fruits.size());
        System.out.println("Contains Apple: " + fruits.contains("Apple"));
        
        // Iterating
        System.out.println("Fruits:");
        for (String fruit : fruits) {
            System.out.println("- " + fruit);
        }
    }
}
```

### Example 3: HashSet and HashMap
```java
import java.util.HashSet;
import java.util.HashMap;
import java.util.Map;

public class SetMapDemo {
    public static void main(String[] args) {
        // HashSet example
        HashSet<String> uniqueWords = new HashSet<>();
        String[] words = {"hello", "world", "hello", "java", "world", "programming"};
        
        for (String word : words) {
            uniqueWords.add(word);
        }
        
        System.out.println("Unique words: " + uniqueWords);
        System.out.println("Total unique words: " + uniqueWords.size());
        
        // HashMap example
        HashMap<String, Integer> wordCount = new HashMap<>();
        
        for (String word : words) {
            wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
        }
        
        System.out.println("\nWord count:");
        for (Map.Entry<String, Integer> entry : wordCount.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        
        // Student grades example
        HashMap<String, Double> studentGrades = new HashMap<>();
        studentGrades.put("Alice", 85.5);
        studentGrades.put("Bob", 92.0);
        studentGrades.put("Charlie", 78.5);
        studentGrades.put("Diana", 96.0);
        
        System.out.println("\nStudent grades:");
        for (String student : studentGrades.keySet()) {
            System.out.println(student + ": " + studentGrades.get(student));
        }
    }
}
```

### Example 4: Two-Dimensional Arrays
```java
public class TwoDArrayDemo {
    public static void main(String[] args) {
        // Initialize 2D array
        int[][] matrix = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
        
        // Print matrix
        System.out.println("Matrix:");
        printMatrix(matrix);
        
        // Calculate sum of each row
        System.out.println("\nRow sums:");
        for (int i = 0; i < matrix.length; i++) {
            int rowSum = 0;
            for (int j = 0; j < matrix[i].length; j++) {
                rowSum += matrix[i][j];
            }
            System.out.println("Row " + (i + 1) + ": " + rowSum);
        }
        
        // Find maximum value
        int max = matrix[0][0];
        for (int[] row : matrix) {
            for (int value : row) {
                if (value > max) {
                    max = value;
                }
            }
        }
        System.out.println("Maximum value: " + max);
    }
    
    public static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int value : row) {
                System.out.print(value + " ");
            }
            System.out.println();
        }
    }
}
```

## Practice Exercises

### Exercise 1: Array Statistics
Create a program that calculates mean, median, and mode of an array of numbers.

### Exercise 2: Student Management System
Build a system using ArrayList to manage student records (name, ID, grades).

### Exercise 3: Word Frequency Counter
Use HashMap to count the frequency of words in a text.

### Exercise 4: Matrix Operations
Implement matrix addition, multiplication, and transpose operations.

### Exercise 5: Unique Elements Finder
Find all unique elements in an array using HashSet.

## Mini-Project: Library Management System

Create a simple library management system using collections:

```java
import java.util.*;

class Book {
    private String title;
    private String author;
    private String isbn;
    private boolean available;
    
    public Book(String title, String author, String isbn) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.available = true;
    }
    
    // Getters and setters
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getIsbn() { return isbn; }
    public boolean isAvailable() { return available; }
    public void setAvailable(boolean available) { this.available = available; }
    
    @Override
    public String toString() {
        return title + " by " + author + " (ISBN: " + isbn + ") - " + 
               (available ? "Available" : "Borrowed");
    }
}

public class LibraryManagementSystem {
    private ArrayList<Book> books;
    private HashMap<String, Book> booksByIsbn;
    
    public LibraryManagementSystem() {
        books = new ArrayList<>();
        booksByIsbn = new HashMap<>();
    }
    
    public void addBook(String title, String author, String isbn) {
        Book book = new Book(title, author, isbn);
        books.add(book);
        booksByIsbn.put(isbn, book);
        System.out.println("Book added successfully: " + title);
    }
    
    public void borrowBook(String isbn) {
        Book book = booksByIsbn.get(isbn);
        if (book != null && book.isAvailable()) {
            book.setAvailable(false);
            System.out.println("Book borrowed: " + book.getTitle());
        } else {
            System.out.println("Book not available or not found.");
        }
    }
    
    public void returnBook(String isbn) {
        Book book = booksByIsbn.get(isbn);
        if (book != null && !book.isAvailable()) {
            book.setAvailable(true);
            System.out.println("Book returned: " + book.getTitle());
        } else {
            System.out.println("Book not found or already available.");
        }
    }
    
    public void displayAllBooks() {
        System.out.println("\nAll Books:");
        for (Book book : books) {
            System.out.println(book);
        }
    }
    
    public void displayAvailableBooks() {
        System.out.println("\nAvailable Books:");
        for (Book book : books) {
            if (book.isAvailable()) {
                System.out.println(book);
            }
        }
    }
    
    public void searchByAuthor(String author) {
        System.out.println("\nBooks by " + author + ":");
        for (Book book : books) {
            if (book.getAuthor().equalsIgnoreCase(author)) {
                System.out.println(book);
            }
        }
    }
    
    public static void main(String[] args) {
        LibraryManagementSystem library = new LibraryManagementSystem();
        
        // Add books
        library.addBook("Java Programming", "John Doe", "1234567890");
        library.addBook("Data Structures", "Jane Smith", "0987654321");
        library.addBook("Algorithms", "Bob Johnson", "1122334455");
        library.addBook("Web Development", "Alice Brown", "5566778899");
        
        // Display all books
        library.displayAllBooks();
        
        // Borrow a book
        library.borrowBook("1234567890");
        
        // Display available books
        library.displayAvailableBooks();
        
        // Search by author
        library.searchByAuthor("John Doe");
        
        // Return a book
        library.returnBook("1234567890");
        
        // Display all books again
        library.displayAllBooks();
    }
}
```

## Advanced Concepts

### 1. Generic Collections
```java
import java.util.*;

public class GenericCollections {
    public static void main(String[] args) {
        // Generic ArrayList
        ArrayList<Integer> numbers = new ArrayList<>();
        numbers.add(10);
        numbers.add(20);
        numbers.add(30);
        
        // Generic HashMap
        HashMap<String, List<String>> categories = new HashMap<>();
        categories.put("Fruits", Arrays.asList("Apple", "Banana", "Orange"));
        categories.put("Vegetables", Arrays.asList("Carrot", "Broccoli", "Spinach"));
        
        // Generic method
        List<String> fruits = categories.get("Fruits");
        printList(fruits);
    }
    
    public static <T> void printList(List<T> list) {
        for (T item : list) {
            System.out.println(item);
        }
    }
}
```

### 2. Collections Utility Methods
```java
import java.util.*;

public class CollectionsUtils {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(5, 2, 8, 1, 9, 3);
        
        // Sorting
        Collections.sort(numbers);
        System.out.println("Sorted: " + numbers);
        
        // Reversing
        Collections.reverse(numbers);
        System.out.println("Reversed: " + numbers);
        
        // Shuffling
        Collections.shuffle(numbers);
        System.out.println("Shuffled: " + numbers);
        
        // Finding min and max
        System.out.println("Min: " + Collections.min(numbers));
        System.out.println("Max: " + Collections.max(numbers));
        
        // Binary search (requires sorted list)
        Collections.sort(numbers);
        int index = Collections.binarySearch(numbers, 8);
        System.out.println("Index of 8: " + index);
    }
}
```

### 3. Custom Comparator
```java
import java.util.*;

class Student {
    private String name;
    private int age;
    private double gpa;
    
    public Student(String name, int age, double gpa) {
        this.name = name;
        this.age = age;
        this.gpa = gpa;
    }
    
    public String getName() { return name; }
    public int getAge() { return age; }
    public double getGpa() { return gpa; }
    
    @Override
    public String toString() {
        return name + " (Age: " + age + ", GPA: " + gpa + ")";
    }
}

public class CustomComparator {
    public static void main(String[] args) {
        List<Student> students = Arrays.asList(
            new Student("Alice", 20, 3.8),
            new Student("Bob", 19, 3.5),
            new Student("Charlie", 21, 3.9),
            new Student("Diana", 20, 3.7)
        );
        
        // Sort by name
        Collections.sort(students, (s1, s2) -> s1.getName().compareTo(s2.getName()));
        System.out.println("Sorted by name: " + students);
        
        // Sort by GPA (descending)
        Collections.sort(students, (s1, s2) -> Double.compare(s2.getGpa(), s1.getGpa()));
        System.out.println("Sorted by GPA (descending): " + students);
        
        // Sort by age, then by GPA
        Collections.sort(students, (s1, s2) -> {
            int ageCompare = Integer.compare(s1.getAge(), s2.getAge());
            if (ageCompare != 0) return ageCompare;
            return Double.compare(s1.getGpa(), s2.getGpa());
        });
        System.out.println("Sorted by age, then GPA: " + students);
    }
}
```

## Weekly Challenge: Inventory Management System

Create a comprehensive inventory management system that:
- Manages products with categories
- Tracks stock levels
- Handles sales and purchases
- Generates reports
- Uses multiple collection types effectively

## Assessment Criteria
- [ ] Proper array usage and manipulation
- [ ] Effective use of ArrayList and LinkedList
- [ ] Correct implementation of HashSet and HashMap
- [ ] Understanding of collection hierarchy
- [ ] Code organization and efficiency
- [ ] Problem-solving with collections

## Resources
- [Java Arrays Tutorial](https://docs.oracle.com/javase/tutorial/java/nutsandbolts/arrays.html)
- [Java Collections Framework](https://docs.oracle.com/javase/tutorial/collections/)
- [ArrayList Documentation](https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html)
- [HashMap Documentation](https://docs.oracle.com/javase/8/docs/api/java/util/HashMap.html)