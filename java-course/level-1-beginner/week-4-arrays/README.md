# Week 4: Arrays and Collections

## 🎯 Learning Objectives
- Understand single and multi-dimensional arrays
- Learn array creation, initialization, and manipulation
- Master common array algorithms (sorting, searching)
- Practice working with arrays of different data types
- Build programs that efficiently handle multiple values

## 📚 Theory

### What are Arrays?
Arrays are data structures that store multiple values of the same type in contiguous memory locations. Each element is accessed by its index.

### Array Characteristics
- **Fixed size**: Once created, size cannot be changed
- **Zero-indexed**: First element is at index 0
- **Contiguous memory**: Elements are stored in adjacent memory locations
- **Same data type**: All elements must be of the same type

### Single-Dimensional Arrays

#### Declaration and Initialization
```java
// Declaration
int[] numbers;
String[] names;

// Initialization with size
numbers = new int[5];
names = new String[3];

// Declaration and initialization in one line
int[] scores = new int[10];
String[] colors = new String[4];

// Array literal initialization
int[] primes = {2, 3, 5, 7, 11};
String[] fruits = {"apple", "banana", "orange"};
```

#### Accessing Array Elements
```java
int[] numbers = {10, 20, 30, 40, 50};

// Accessing elements
int first = numbers[0];  // 10
int third = numbers[2];  // 30
int last = numbers[numbers.length - 1];  // 50

// Modifying elements
numbers[1] = 25;  // Changes second element to 25
```

#### Array Length
```java
int[] array = {1, 2, 3, 4, 5};
int length = array.length;  // 5

// Common pattern for iterating
for (int i = 0; i < array.length; i++) {
    System.out.println(array[i]);
}
```

### Multi-Dimensional Arrays

#### 2D Arrays (Matrices)
```java
// Declaration
int[][] matrix;

// Initialization
matrix = new int[3][4];  // 3 rows, 4 columns

// Declaration and initialization
int[][] grid = new int[2][3];

// Array literal initialization
int[][] table = {
    {1, 2, 3},
    {4, 5, 6},
    {7, 8, 9}
};
```

#### Accessing 2D Array Elements
```java
int[][] matrix = {
    {1, 2, 3},
    {4, 5, 6},
    {7, 8, 9}
};

// Accessing elements
int element = matrix[1][2];  // 6 (row 1, column 2)

// Getting dimensions
int rows = matrix.length;     // 3
int cols = matrix[0].length;  // 3
```

#### 3D Arrays
```java
// 3D array (depth x rows x columns)
int[][][] cube = new int[3][4][5];

// Accessing elements
int value = cube[1][2][3];  // depth 1, row 2, column 3
```

### Array Operations

#### Iterating Through Arrays
```java
int[] numbers = {1, 2, 3, 4, 5};

// Traditional for loop
for (int i = 0; i < numbers.length; i++) {
    System.out.println(numbers[i]);
}

// Enhanced for loop (for-each)
for (int num : numbers) {
    System.out.println(num);
}

// Iterating through 2D array
int[][] matrix = {{1, 2}, {3, 4}, {5, 6}};
for (int[] row : matrix) {
    for (int element : row) {
        System.out.print(element + " ");
    }
    System.out.println();
}
```

#### Common Array Operations
```java
// Finding maximum value
public static int findMax(int[] arr) {
    if (arr.length == 0) return Integer.MIN_VALUE;
    int max = arr[0];
    for (int i = 1; i < arr.length; i++) {
        if (arr[i] > max) max = arr[i];
    }
    return max;
}

// Finding minimum value
public static int findMin(int[] arr) {
    if (arr.length == 0) return Integer.MAX_VALUE;
    int min = arr[0];
    for (int i = 1; i < arr.length; i++) {
        if (arr[i] < min) min = arr[i];
    }
    return min;
}

// Calculating sum
public static int sum(int[] arr) {
    int total = 0;
    for (int num : arr) {
        total += num;
    }
    return total;
}

// Calculating average
public static double average(int[] arr) {
    if (arr.length == 0) return 0.0;
    return (double) sum(arr) / arr.length;
}
```

## 💻 Practice Examples

### Example 1: Array Basics and Operations
```java
public class ArrayBasics {
    public static void main(String[] args) {
        // Creating and initializing arrays
        int[] numbers = {23, 45, 12, 67, 89, 34, 56, 78};
        String[] names = {"Alice", "Bob", "Charlie", "Diana"};
        
        // Displaying arrays
        System.out.println("Numbers array:");
        printArray(numbers);
        
        System.out.println("\nNames array:");
        printArray(names);
        
        // Array operations
        System.out.println("\nArray Analysis:");
        System.out.println("Length: " + numbers.length);
        System.out.println("Maximum: " + findMax(numbers));
        System.out.println("Minimum: " + findMin(numbers));
        System.out.println("Sum: " + sum(numbers));
        System.out.println("Average: " + average(numbers));
        
        // Searching
        int searchValue = 67;
        int index = linearSearch(numbers, searchValue);
        if (index != -1) {
            System.out.println(searchValue + " found at index " + index);
        } else {
            System.out.println(searchValue + " not found");
        }
        
        // Sorting
        System.out.println("\nBefore sorting:");
        printArray(numbers);
        
        bubbleSort(numbers);
        System.out.println("After sorting:");
        printArray(numbers);
    }
    
    // Utility methods
    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i < arr.length - 1) System.out.print(", ");
        }
        System.out.println();
    }
    
    public static void printArray(String[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i < arr.length - 1) System.out.print(", ");
        }
        System.out.println();
    }
    
    public static int findMax(int[] arr) {
        if (arr.length == 0) return Integer.MIN_VALUE;
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) max = arr[i];
        }
        return max;
    }
    
    public static int findMin(int[] arr) {
        if (arr.length == 0) return Integer.MAX_VALUE;
        int min = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < min) min = arr[i];
        }
        return min;
    }
    
    public static int sum(int[] arr) {
        int total = 0;
        for (int num : arr) {
            total += num;
        }
        return total;
    }
    
    public static double average(int[] arr) {
        if (arr.length == 0) return 0.0;
        return (double) sum(arr) / arr.length;
    }
    
    public static int linearSearch(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                return i;
            }
        }
        return -1;
    }
    
    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    // Swap elements
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }
}
```

### Example 2: 2D Array Operations
```java
public class MatrixOperations {
    public static void main(String[] args) {
        // Creating matrices
        int[][] matrix1 = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
        
        int[][] matrix2 = {
            {9, 8, 7},
            {6, 5, 4},
            {3, 2, 1}
        };
        
        System.out.println("Matrix 1:");
        printMatrix(matrix1);
        
        System.out.println("\nMatrix 2:");
        printMatrix(matrix2);
        
        // Matrix operations
        System.out.println("\nMatrix Addition:");
        int[][] sum = addMatrices(matrix1, matrix2);
        printMatrix(sum);
        
        System.out.println("\nMatrix Transpose:");
        int[][] transpose = transposeMatrix(matrix1);
        printMatrix(transpose);
        
        System.out.println("\nRow sums:");
        int[] rowSums = getRowSums(matrix1);
        for (int i = 0; i < rowSums.length; i++) {
            System.out.println("Row " + i + " sum: " + rowSums[i]);
        }
        
        System.out.println("\nColumn sums:");
        int[] colSums = getColumnSums(matrix1);
        for (int i = 0; i < colSums.length; i++) {
            System.out.println("Column " + i + " sum: " + colSums[i]);
        }
    }
    
    public static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int element : row) {
                System.out.print(element + "\t");
            }
            System.out.println();
        }
    }
    
    public static int[][] addMatrices(int[][] a, int[][] b) {
        int rows = a.length;
        int cols = a[0].length;
        int[][] result = new int[rows][cols];
        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[i][j] = a[i][j] + b[i][j];
            }
        }
        return result;
    }
    
    public static int[][] transposeMatrix(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[][] transpose = new int[cols][rows];
        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                transpose[j][i] = matrix[i][j];
            }
        }
        return transpose;
    }
    
    public static int[] getRowSums(int[][] matrix) {
        int rows = matrix.length;
        int[] rowSums = new int[rows];
        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                rowSums[i] += matrix[i][j];
            }
        }
        return rowSums;
    }
    
    public static int[] getColumnSums(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[] colSums = new int[cols];
        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                colSums[j] += matrix[i][j];
            }
        }
        return colSums;
    }
}
```

### Example 3: Array Algorithms
```java
public class ArrayAlgorithms {
    public static void main(String[] args) {
        int[] numbers = {64, 34, 25, 12, 22, 11, 90, 88, 76, 54};
        
        System.out.println("Original array:");
        printArray(numbers);
        
        // Different sorting algorithms
        int[] bubbleSorted = numbers.clone();
        bubbleSort(bubbleSorted);
        System.out.println("\nBubble sort:");
        printArray(bubbleSorted);
        
        int[] selectionSorted = numbers.clone();
        selectionSort(selectionSorted);
        System.out.println("\nSelection sort:");
        printArray(selectionSorted);
        
        int[] insertionSorted = numbers.clone();
        insertionSort(insertionSorted);
        System.out.println("\nInsertion sort:");
        printArray(insertionSorted);
        
        // Binary search (requires sorted array)
        int searchValue = 25;
        int index = binarySearch(insertionSorted, searchValue);
        if (index != -1) {
            System.out.println("\n" + searchValue + " found at index " + index);
        } else {
            System.out.println("\n" + searchValue + " not found");
        }
        
        // Array manipulation
        System.out.println("\nReversed array:");
        reverseArray(numbers);
        printArray(numbers);
        
        System.out.println("\nRotated array (by 3 positions):");
        rotateArray(numbers, 3);
        printArray(numbers);
    }
    
    public static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
    
    // Bubble Sort
    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }
    
    // Selection Sort
    public static void selectionSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            int temp = arr[minIndex];
            arr[minIndex] = arr[i];
            arr[i] = temp;
        }
    }
    
    // Insertion Sort
    public static void insertionSort(int[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }
    
    // Binary Search
    public static int binarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }
    
    // Reverse Array
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
    
    // Rotate Array
    public static void rotateArray(int[] arr, int positions) {
        int n = arr.length;
        positions = positions % n; // Handle cases where positions > n
        
        // Reverse the entire array
        reverseArray(arr);
        
        // Reverse first 'positions' elements
        reversePartial(arr, 0, positions - 1);
        
        // Reverse remaining elements
        reversePartial(arr, positions, n - 1);
    }
    
    private static void reversePartial(int[] arr, int start, int end) {
        while (start < end) {
            int temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            start++;
            end--;
        }
    }
}
```

### Example 4: Practical Array Applications
```java
public class ArrayApplications {
    public static void main(String[] args) {
        // Grade management system
        double[] grades = {85.5, 92.0, 78.5, 96.0, 88.5, 91.0, 82.5, 89.0};
        String[] students = {"Alice", "Bob", "Charlie", "Diana", "Eve", "Frank", "Grace", "Henry"};
        
        System.out.println("Grade Management System:");
        displayGrades(students, grades);
        
        System.out.println("\nGrade Statistics:");
        System.out.println("Average: " + String.format("%.2f", calculateAverage(grades)));
        System.out.println("Highest: " + findMax(grades));
        System.out.println("Lowest: " + findMin(grades));
        
        // Find students with grades above average
        double average = calculateAverage(grades);
        System.out.println("\nStudents above average (" + String.format("%.2f", average) + "):");
        for (int i = 0; i < grades.length; i++) {
            if (grades[i] > average) {
                System.out.println(students[i] + ": " + grades[i]);
            }
        }
        
        // Inventory management
        String[] products = {"Laptop", "Mouse", "Keyboard", "Monitor", "Headphones"};
        int[] quantities = {10, 25, 15, 8, 20};
        double[] prices = {999.99, 29.99, 89.99, 299.99, 79.99};
        
        System.out.println("\nInventory Management:");
        displayInventory(products, quantities, prices);
        
        // Calculate total inventory value
        double totalValue = calculateTotalValue(quantities, prices);
        System.out.println("\nTotal inventory value: $" + String.format("%.2f", totalValue));
        
        // Find low stock items (quantity < 10)
        System.out.println("\nLow stock items (quantity < 10):");
        for (int i = 0; i < quantities.length; i++) {
            if (quantities[i] < 10) {
                System.out.println(products[i] + ": " + quantities[i] + " units");
            }
        }
    }
    
    public static void displayGrades(String[] students, double[] grades) {
        System.out.println("Student\t\tGrade");
        System.out.println("--------\t\t-----");
        for (int i = 0; i < students.length; i++) {
            System.out.printf("%-12s\t%.1f\n", students[i], grades[i]);
        }
    }
    
    public static double calculateAverage(double[] arr) {
        if (arr.length == 0) return 0.0;
        double sum = 0;
        for (double num : arr) {
            sum += num;
        }
        return sum / arr.length;
    }
    
    public static double findMax(double[] arr) {
        if (arr.length == 0) return Double.MIN_VALUE;
        double max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) max = arr[i];
        }
        return max;
    }
    
    public static double findMin(double[] arr) {
        if (arr.length == 0) return Double.MAX_VALUE;
        double min = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < min) min = arr[i];
        }
        return min;
    }
    
    public static void displayInventory(String[] products, int[] quantities, double[] prices) {
        System.out.println("Product\t\tQuantity\tPrice\t\tValue");
        System.out.println("-------\t\t--------\t-----\t\t-----");
        for (int i = 0; i < products.length; i++) {
            double value = quantities[i] * prices[i];
            System.out.printf("%-12s\t%d\t\t$%.2f\t\t$%.2f\n", 
                            products[i], quantities[i], prices[i], value);
        }
    }
    
    public static double calculateTotalValue(int[] quantities, double[] prices) {
        double total = 0;
        for (int i = 0; i < quantities.length; i++) {
            total += quantities[i] * prices[i];
        }
        return total;
    }
}
```

## 🏋️ Exercises

### Exercise 1: Student Grade Analyzer
Create a program that:
- Stores student names and their grades in arrays
- Calculates class statistics (average, median, mode)
- Finds the highest and lowest performing students
- Generates a grade distribution report
- Identifies students who need improvement

### Exercise 2: Matrix Calculator
Create a program that:
- Performs matrix addition, subtraction, and multiplication
- Calculates matrix determinant and inverse
- Implements matrix rotation and scaling
- Handles different matrix sizes
- Provides matrix visualization

### Exercise 3: Array-Based Game
Create a simple game using arrays:
- Tic-tac-toe with 2D array
- Memory matching game
- Number guessing with history tracking
- Simple RPG with character stats

### Exercise 4: Data Analysis Tool
Create a program that:
- Analyzes sales data stored in arrays
- Calculates trends and patterns
- Generates reports and visualizations
- Handles missing data
- Exports results to formatted output

## 🔧 Practice Files

### PracticeFile1.java
```java
public class PracticeFile1 {
    public static void main(String[] args) {
        // TODO: Create a temperature tracking system
        // - Store daily temperatures for a week
        // - Calculate average, high, and low temperatures
        // - Find the day with the highest temperature
        // - Calculate temperature variance
        // - Display a temperature trend report
    }
}
```

### PracticeFile2.java
```java
public class PracticeFile2 {
    public static void main(String[] args) {
        // TODO: Create a simple image processing program
        // - Represent a grayscale image as a 2D array
        // - Implement brightness adjustment
        // - Add contrast enhancement
        // - Create edge detection
        // - Display the processed image
    }
}
```

## 📝 Quiz Questions

1. What is the index of the first element in a Java array?
2. What happens if you try to access an array index that doesn't exist?
3. How do you find the length of an array?
4. What is the difference between a 1D and 2D array?
5. How do you copy an array in Java?
6. What is the time complexity of linear search?
7. What is the time complexity of binary search?
8. How do you reverse an array in-place?
9. What is the difference between bubble sort and selection sort?
10. When would you use a 3D array?

## 🎯 Key Takeaways

- Arrays provide efficient storage for multiple values
- Use appropriate array dimensions for your data structure
- Choose the right sorting algorithm based on your needs
- Always validate array indices to avoid exceptions
- Consider time and space complexity for large arrays
- Use helper methods to organize array operations
- Practice with different array applications

## 📚 Additional Resources

- [Java Arrays Tutorial](https://docs.oracle.com/javase/tutorial/java/nutsandbolts/arrays.html)
- [Array Algorithms](https://www.geeksforgeeks.org/array-data-structure/)
- [Sorting Algorithms](https://www.geeksforgeeks.org/sorting-algorithms/)

## 🚀 Next Steps

In the next lesson, we'll explore object-oriented programming concepts including classes, objects, and inheritance.