# Lesson 4: Arrays

## Learning Objectives
- Understand what arrays are and how to use them
- Learn how to create and initialize arrays
- Master array operations (accessing, modifying, iterating)
- Understand multi-dimensional arrays
- Practice common array algorithms and operations

## What are Arrays?
An array is a data structure that stores a fixed-size sequential collection of elements of the same type. Arrays are used to store multiple values in a single variable, instead of declaring separate variables for each value.

## Array Declaration and Initialization

### Declaring Arrays
```java
// Declare an array of integers
int[] numbers;

// Declare an array of strings
String[] names;

// Declare an array of doubles
double[] prices;
```

### Creating Arrays
```java
// Create an array with specified size
int[] numbers = new int[5];  // Creates array of 5 integers (all initialized to 0)
String[] names = new String[3];  // Creates array of 3 strings (all initialized to null)
```

### Initializing Arrays
```java
// Initialize with values
int[] numbers = {1, 2, 3, 4, 5};
String[] fruits = {"Apple", "Banana", "Orange"};
double[] prices = {10.99, 5.50, 3.25};

// Initialize after declaration
int[] scores;
scores = new int[]{85, 92, 78, 96, 88};
```

## Accessing Array Elements

### Array Indexing
```java
int[] numbers = {10, 20, 30, 40, 50};

// Access first element (index 0)
int first = numbers[0];  // 10

// Access third element (index 2)
int third = numbers[2];  // 30

// Access last element
int last = numbers[numbers.length - 1];  // 50

// Modify an element
numbers[1] = 25;  // Changes second element to 25
```

### Array Length
```java
int[] numbers = {1, 2, 3, 4, 5};
int length = numbers.length;  // 5

// Common pattern: iterate through array
for (int i = 0; i < numbers.length; i++) {
    System.out.println(numbers[i]);
}
```

## Array Operations

### Basic Array Operations
```java
public class ArrayOperations {
    // Find maximum value in array
    public static int findMax(int[] array) {
        if (array.length == 0) {
            throw new IllegalArgumentException("Array cannot be empty");
        }
        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }
        return max;
    }
    
    // Find minimum value in array
    public static int findMin(int[] array) {
        if (array.length == 0) {
            throw new IllegalArgumentException("Array cannot be empty");
        }
        int min = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] < min) {
                min = array[i];
            }
        }
        return min;
    }
    
    // Calculate sum of array elements
    public static int sum(int[] array) {
        int total = 0;
        for (int num : array) {
            total += num;
        }
        return total;
    }
    
    // Calculate average of array elements
    public static double average(int[] array) {
        if (array.length == 0) {
            return 0.0;
        }
        return (double) sum(array) / array.length;
    }
    
    // Search for an element in array
    public static int linearSearch(int[] array, int target) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == target) {
                return i;  // Return index if found
            }
        }
        return -1;  // Return -1 if not found
    }
}
```

## Array Iteration

### Different Ways to Iterate
```java
int[] numbers = {1, 2, 3, 4, 5};

// 1. Traditional for loop
for (int i = 0; i < numbers.length; i++) {
    System.out.println("Element " + i + ": " + numbers[i]);
}

// 2. Enhanced for loop (for-each)
for (int number : numbers) {
    System.out.println("Number: " + number);
}

// 3. While loop
int i = 0;
while (i < numbers.length) {
    System.out.println(numbers[i]);
    i++;
}

// 4. Do-while loop
int j = 0;
do {
    System.out.println(numbers[j]);
    j++;
} while (j < numbers.length);
```

### Iterating with Index
```java
String[] fruits = {"Apple", "Banana", "Orange", "Grape"};

// Print index and value
for (int i = 0; i < fruits.length; i++) {
    System.out.println("Index " + i + ": " + fruits[i]);
}

// Print only even indices
for (int i = 0; i < fruits.length; i += 2) {
    System.out.println("Even index " + i + ": " + fruits[i]);
}

// Print in reverse order
for (int i = fruits.length - 1; i >= 0; i--) {
    System.out.println("Reverse " + i + ": " + fruits[i]);
}
```

## Array Manipulation

### Common Array Operations
```java
public class ArrayManipulation {
    // Copy array
    public static int[] copyArray(int[] original) {
        int[] copy = new int[original.length];
        for (int i = 0; i < original.length; i++) {
            copy[i] = original[i];
        }
        return copy;
    }
    
    // Reverse array
    public static void reverseArray(int[] array) {
        int left = 0;
        int right = array.length - 1;
        while (left < right) {
            // Swap elements
            int temp = array[left];
            array[left] = array[right];
            array[right] = temp;
            left++;
            right--;
        }
    }
    
    // Rotate array by k positions
    public static void rotateArray(int[] array, int k) {
        k = k % array.length;  // Handle cases where k > array length
        reverseArray(array, 0, array.length - 1);
        reverseArray(array, 0, k - 1);
        reverseArray(array, k, array.length - 1);
    }
    
    private static void reverseArray(int[] array, int start, int end) {
        while (start < end) {
            int temp = array[start];
            array[start] = array[end];
            array[end] = temp;
            start++;
            end--;
        }
    }
    
    // Remove duplicates from sorted array
    public static int removeDuplicates(int[] array) {
        if (array.length <= 1) {
            return array.length;
        }
        
        int uniqueIndex = 0;
        for (int i = 1; i < array.length; i++) {
            if (array[i] != array[uniqueIndex]) {
                uniqueIndex++;
                array[uniqueIndex] = array[i];
            }
        }
        return uniqueIndex + 1;
    }
}
```

## Multi-dimensional Arrays

### 2D Arrays
```java
// Declare 2D array
int[][] matrix;

// Create 2D array
int[][] matrix = new int[3][4];  // 3 rows, 4 columns

// Initialize 2D array
int[][] matrix = {
    {1, 2, 3, 4},
    {5, 6, 7, 8},
    {9, 10, 11, 12}
};

// Access elements
int element = matrix[1][2];  // 7 (row 1, column 2)

// Iterate through 2D array
for (int i = 0; i < matrix.length; i++) {
    for (int j = 0; j < matrix[i].length; j++) {
        System.out.print(matrix[i][j] + " ");
    }
    System.out.println();
}
```

### 3D Arrays
```java
// Create 3D array
int[][][] cube = new int[3][3][3];

// Initialize 3D array
int[][][] cube = {
    {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}},
    {{10, 11, 12}, {13, 14, 15}, {16, 17, 18}},
    {{19, 20, 21}, {22, 23, 24}, {25, 26, 27}}
};

// Access element
int element = cube[1][2][0];  // 16
```

## Array Algorithms

### Sorting Algorithms
```java
public class ArrayAlgorithms {
    // Bubble Sort
    public static void bubbleSort(int[] array) {
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    // Swap elements
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }
    
    // Selection Sort
    public static void selectionSort(int[] array) {
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }
            }
            // Swap elements
            int temp = array[minIndex];
            array[minIndex] = array[i];
            array[i] = temp;
        }
    }
    
    // Binary Search (requires sorted array)
    public static int binarySearch(int[] array, int target) {
        int left = 0;
        int right = array.length - 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (array[mid] == target) {
                return mid;
            }
            
            if (array[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;  // Not found
    }
}
```

## Array Utilities

### Common Array Utilities
```java
import java.util.Arrays;

public class ArrayUtilities {
    public static void main(String[] args) {
        int[] numbers = {3, 1, 4, 1, 5, 9, 2, 6};
        
        // Sort array
        Arrays.sort(numbers);
        System.out.println("Sorted: " + Arrays.toString(numbers));
        
        // Fill array with value
        int[] filled = new int[5];
        Arrays.fill(filled, 42);
        System.out.println("Filled: " + Arrays.toString(filled));
        
        // Copy array
        int[] copy = Arrays.copyOf(numbers, numbers.length);
        System.out.println("Copy: " + Arrays.toString(copy));
        
        // Check if arrays are equal
        boolean equal = Arrays.equals(numbers, copy);
        System.out.println("Arrays equal: " + equal);
        
        // Binary search (requires sorted array)
        int index = Arrays.binarySearch(numbers, 5);
        System.out.println("Index of 5: " + index);
    }
}
```

## Exercises

### Exercise 1: Basic Array Operations
Create methods to:
- Find the second largest number in an array
- Count occurrences of a specific element
- Check if an array is sorted
- Find the most frequent element

### Exercise 2: Array Manipulation
Create methods to:
- Move all zeros to the end of an array
- Find pairs that sum to a target value
- Merge two sorted arrays
- Find the missing number in a sequence

### Exercise 3: 2D Array Operations
Create methods to:
- Calculate the sum of each row in a 2D array
- Find the maximum element in a 2D array
- Transpose a 2D array
- Check if a 2D array is symmetric

### Exercise 4: Advanced Array Problems
Create methods to:
- Find the longest increasing subsequence
- Implement a sliding window algorithm
- Find the median of two sorted arrays
- Implement the Dutch National Flag problem

## Practice Files
- `ArraysDemo.java` - Basic array examples
- `ArrayOperations.java` - Common array operations
- `MultiDimensionalArrays.java` - 2D and 3D arrays
- `ArrayAlgorithms.java` - Sorting and searching algorithms
- `ArraysExercises.java` - Practice problems

## Key Takeaways
1. Arrays store multiple values of the same type
2. Array indices start at 0 and go up to length-1
3. Use enhanced for loops when you don't need the index
4. Arrays have a fixed size once created
5. Multi-dimensional arrays are arrays of arrays
6. Always check array bounds to avoid ArrayIndexOutOfBoundsException
7. Use Arrays utility class for common operations

## Next Steps
After completing this lesson, move on to:
- Collections Framework (ArrayList, LinkedList, etc.)
- Object-oriented programming
- Exception handling
- File I/O operations

## Additional Resources
- [Java Arrays Tutorial](https://docs.oracle.com/javase/tutorial/java/nutsandbolts/arrays.html)
- [Arrays Class Documentation](https://docs.oracle.com/javase/8/docs/api/java/util/Arrays.html)