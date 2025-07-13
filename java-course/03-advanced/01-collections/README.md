# Lesson 6: Collections Framework

## Learning Objectives
- Understand the Java Collections Framework
- Learn about different collection types (List, Set, Map)
- Master ArrayList, LinkedList, HashSet, TreeSet, HashMap, TreeMap
- Understand when to use each collection type
- Practice common collection operations and algorithms

## What is the Collections Framework?
The Java Collections Framework is a unified architecture for representing and manipulating collections. It provides a set of interfaces and classes that implement commonly reusable collection data structures.

## Collection Hierarchy

### Core Interfaces
```
Collection
├── List (ordered, allows duplicates)
│   ├── ArrayList
│   ├── LinkedList
│   └── Vector
├── Set (no duplicates)
│   ├── HashSet
│   ├── TreeSet
│   └── LinkedHashSet
└── Queue (FIFO/LIFO)
    ├── PriorityQueue
    └── LinkedList

Map (key-value pairs)
├── HashMap
├── TreeMap
└── LinkedHashMap
```

## List Interface

### ArrayList
```java
import java.util.ArrayList;
import java.util.List;

public class ArrayListDemo {
    public static void main(String[] args) {
        // Create ArrayList
        List<String> fruits = new ArrayList<>();
        
        // Add elements
        fruits.add("Apple");
        fruits.add("Banana");
        fruits.add("Orange");
        fruits.add("Apple"); // Duplicates allowed
        
        // Access elements
        String firstFruit = fruits.get(0);
        System.out.println("First fruit: " + firstFruit);
        
        // Check if element exists
        boolean hasApple = fruits.contains("Apple");
        System.out.println("Contains Apple: " + hasApple);
        
        // Get size
        int size = fruits.size();
        System.out.println("Size: " + size);
        
        // Remove element
        fruits.remove("Banana");
        
        // Iterate through list
        for (String fruit : fruits) {
            System.out.println(fruit);
        }
        
        // Clear list
        fruits.clear();
        System.out.println("Is empty: " + fruits.isEmpty());
    }
}
```

### LinkedList
```java
import java.util.LinkedList;

public class LinkedListDemo {
    public static void main(String[] args) {
        LinkedList<String> names = new LinkedList<>();
        
        // Add elements
        names.add("Alice");
        names.add("Bob");
        names.add("Charlie");
        
        // Add at beginning and end
        names.addFirst("Zoe");
        names.addLast("David");
        
        // Remove from beginning and end
        String first = names.removeFirst();
        String last = names.removeLast();
        
        // Get first and last elements
        String firstElement = names.getFirst();
        String lastElement = names.getLast();
        
        // LinkedList as Queue
        names.offer("Eve");  // Add to end
        String head = names.poll();  // Remove from beginning
        
        // LinkedList as Stack
        names.push("Frank");  // Push to top
        String top = names.pop();  // Pop from top
    }
}
```

### ArrayList vs LinkedList
```java
public class ListComparison {
    public static void main(String[] args) {
        // ArrayList: Fast random access, slow insertions/deletions
        ArrayList<Integer> arrayList = new ArrayList<>();
        
        // LinkedList: Fast insertions/deletions, slow random access
        LinkedList<Integer> linkedList = new LinkedList<>();
        
        // Performance comparison
        long startTime = System.currentTimeMillis();
        
        // Adding elements to ArrayList
        for (int i = 0; i < 100000; i++) {
            arrayList.add(0, i); // Slow for ArrayList
        }
        
        long arrayListTime = System.currentTimeMillis() - startTime;
        
        startTime = System.currentTimeMillis();
        
        // Adding elements to LinkedList
        for (int i = 0; i < 100000; i++) {
            linkedList.add(0, i); // Fast for LinkedList
        }
        
        long linkedListTime = System.currentTimeMillis() - startTime;
        
        System.out.println("ArrayList time: " + arrayListTime + "ms");
        System.out.println("LinkedList time: " + linkedListTime + "ms");
    }
}
```

## Set Interface

### HashSet
```java
import java.util.HashSet;
import java.util.Set;

public class HashSetDemo {
    public static void main(String[] args) {
        Set<String> uniqueNames = new HashSet<>();
        
        // Add elements
        uniqueNames.add("Alice");
        uniqueNames.add("Bob");
        uniqueNames.add("Alice"); // Duplicate ignored
        uniqueNames.add("Charlie");
        
        System.out.println("Size: " + uniqueNames.size()); // 3
        
        // Check if element exists
        boolean hasAlice = uniqueNames.contains("Alice");
        
        // Remove element
        uniqueNames.remove("Bob");
        
        // Iterate (order not guaranteed)
        for (String name : uniqueNames) {
            System.out.println(name);
        }
        
        // Create HashSet from existing collection
        List<String> namesList = Arrays.asList("Alice", "Bob", "Charlie", "Alice");
        Set<String> uniqueSet = new HashSet<>(namesList);
        System.out.println("Unique names: " + uniqueSet);
    }
}
```

### TreeSet
```java
import java.util.TreeSet;

public class TreeSetDemo {
    public static void main(String[] args) {
        // TreeSet maintains natural ordering
        TreeSet<Integer> numbers = new TreeSet<>();
        
        numbers.add(5);
        numbers.add(2);
        numbers.add(8);
        numbers.add(1);
        numbers.add(9);
        
        // Elements are automatically sorted
        System.out.println("Sorted numbers: " + numbers);
        
        // Get first and last elements
        Integer first = numbers.first();
        Integer last = numbers.last();
        
        // Get elements less than or greater than
        Set<Integer> lessThan5 = numbers.headSet(5);
        Set<Integer> greaterThan5 = numbers.tailSet(5);
        
        // Custom ordering with Comparator
        TreeSet<String> customOrder = new TreeSet<>((s1, s2) -> s2.compareTo(s1));
        customOrder.add("Alice");
        customOrder.add("Bob");
        customOrder.add("Charlie");
        System.out.println("Reverse order: " + customOrder);
    }
}
```

### LinkedHashSet
```java
import java.util.LinkedHashSet;

public class LinkedHashSetDemo {
    public static void main(String[] args) {
        // LinkedHashSet maintains insertion order
        LinkedHashSet<String> orderedNames = new LinkedHashSet<>();
        
        orderedNames.add("Alice");
        orderedNames.add("Bob");
        orderedNames.add("Charlie");
        orderedNames.add("Alice"); // Duplicate ignored
        
        // Order is preserved
        for (String name : orderedNames) {
            System.out.println(name); // Alice, Bob, Charlie
        }
    }
}
```

## Map Interface

### HashMap
```java
import java.util.HashMap;
import java.util.Map;

public class HashMapDemo {
    public static void main(String[] args) {
        Map<String, Integer> scores = new HashMap<>();
        
        // Add key-value pairs
        scores.put("Alice", 95);
        scores.put("Bob", 87);
        scores.put("Charlie", 92);
        scores.put("Alice", 98); // Overwrites previous value
        
        // Get value
        Integer aliceScore = scores.get("Alice");
        
        // Check if key exists
        boolean hasAlice = scores.containsKey("Alice");
        boolean hasScore95 = scores.containsValue(95);
        
        // Remove entry
        scores.remove("Bob");
        
        // Get all keys and values
        Set<String> keys = scores.keySet();
        Collection<Integer> values = scores.values();
        
        // Iterate through map
        for (Map.Entry<String, Integer> entry : scores.entrySet()) {
            String name = entry.getKey();
            Integer score = entry.getValue();
            System.out.println(name + ": " + score);
        }
        
        // Using forEach (Java 8+)
        scores.forEach((name, score) -> 
            System.out.println(name + ": " + score));
    }
}
```

### TreeMap
```java
import java.util.TreeMap;

public class TreeMapDemo {
    public static void main(String[] args) {
        // TreeMap maintains natural ordering of keys
        TreeMap<String, Integer> sortedScores = new TreeMap<>();
        
        sortedScores.put("Charlie", 92);
        sortedScores.put("Alice", 95);
        sortedScores.put("Bob", 87);
        
        // Keys are automatically sorted
        System.out.println("Sorted by name: " + sortedScores);
        
        // Get first and last entries
        Map.Entry<String, Integer> first = sortedScores.firstEntry();
        Map.Entry<String, Integer> last = sortedScores.lastEntry();
        
        // Get submap
        Map<String, Integer> subMap = sortedScores.subMap("Alice", "Charlie");
        
        // Custom ordering
        TreeMap<String, Integer> reverseOrder = new TreeMap<>((s1, s2) -> s2.compareTo(s1));
        reverseOrder.putAll(sortedScores);
        System.out.println("Reverse order: " + reverseOrder);
    }
}
```

## Queue Interface

### PriorityQueue
```java
import java.util.PriorityQueue;
import java.util.Queue;

public class PriorityQueueDemo {
    public static void main(String[] args) {
        // PriorityQueue orders elements by natural ordering
        Queue<Integer> numbers = new PriorityQueue<>();
        
        numbers.offer(5);
        numbers.offer(2);
        numbers.offer(8);
        numbers.offer(1);
        numbers.offer(9);
        
        // Elements are processed in order
        while (!numbers.isEmpty()) {
            System.out.println(numbers.poll()); // 1, 2, 5, 8, 9
        }
        
        // Custom priority with Comparator
        Queue<String> customPriority = new PriorityQueue<>((s1, s2) -> s2.compareTo(s1));
        customPriority.offer("Alice");
        customPriority.offer("Bob");
        customPriority.offer("Charlie");
        
        while (!customPriority.isEmpty()) {
            System.out.println(customPriority.poll()); // Charlie, Bob, Alice
        }
    }
}
```

## Collections Utility Class

### Common Operations
```java
import java.util.*;

public class CollectionsDemo {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David");
        
        // Sort list
        Collections.sort(names);
        System.out.println("Sorted: " + names);
        
        // Reverse list
        Collections.reverse(names);
        System.out.println("Reversed: " + names);
        
        // Shuffle list
        Collections.shuffle(names);
        System.out.println("Shuffled: " + names);
        
        // Find min and max
        String min = Collections.min(names);
        String max = Collections.max(names);
        
        // Binary search (requires sorted list)
        Collections.sort(names);
        int index = Collections.binarySearch(names, "Bob");
        
        // Fill list with value
        List<Integer> numbers = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        Collections.fill(numbers, 0);
        System.out.println("Filled: " + numbers);
        
        // Frequency of element
        List<String> fruits = Arrays.asList("Apple", "Banana", "Apple", "Orange");
        int appleCount = Collections.frequency(fruits, "Apple");
        System.out.println("Apple count: " + appleCount);
    }
}
```

## Generics with Collections

### Generic Collections
```java
public class GenericCollectionsDemo {
    public static void main(String[] args) {
        // Generic List
        List<Integer> numbers = new ArrayList<>();
        numbers.add(1);
        numbers.add(2);
        numbers.add(3);
        
        // Generic Set
        Set<String> names = new HashSet<>();
        names.add("Alice");
        names.add("Bob");
        
        // Generic Map
        Map<String, Integer> scores = new HashMap<>();
        scores.put("Alice", 95);
        scores.put("Bob", 87);
        
        // Generic method
        List<String> stringList = Arrays.asList("Alice", "Bob", "Charlie");
        List<Integer> intList = Arrays.asList(1, 2, 3, 4, 5);
        
        printList(stringList);
        printList(intList);
    }
    
    // Generic method
    public static <T> void printList(List<T> list) {
        for (T item : list) {
            System.out.println(item);
        }
    }
}
```

## Collection Algorithms

### Common Algorithms
```java
public class CollectionAlgorithms {
    
    // Find duplicates in a list
    public static <T> Set<T> findDuplicates(List<T> list) {
        Set<T> duplicates = new HashSet<>();
        Set<T> seen = new HashSet<>();
        
        for (T item : list) {
            if (!seen.add(item)) {
                duplicates.add(item);
            }
        }
        return duplicates;
    }
    
    // Remove duplicates from a list
    public static <T> List<T> removeDuplicates(List<T> list) {
        return new ArrayList<>(new LinkedHashSet<>(list));
    }
    
    // Find intersection of two lists
    public static <T> List<T> intersection(List<T> list1, List<T> list2) {
        Set<T> set1 = new HashSet<>(list1);
        Set<T> set2 = new HashSet<>(list2);
        set1.retainAll(set2);
        return new ArrayList<>(set1);
    }
    
    // Find union of two lists
    public static <T> List<T> union(List<T> list1, List<T> list2) {
        Set<T> union = new HashSet<>(list1);
        union.addAll(list2);
        return new ArrayList<>(union);
    }
    
    // Group elements by a property
    public static <T> Map<String, List<T>> groupBy(List<T> list, Function<T, String> classifier) {
        return list.stream().collect(Collectors.groupingBy(classifier));
    }
}
```

## Performance Considerations

### Collection Performance
```java
public class PerformanceComparison {
    public static void main(String[] args) {
        // ArrayList vs LinkedList performance
        List<Integer> arrayList = new ArrayList<>();
        List<Integer> linkedList = new LinkedList<>();
        
        // Adding elements
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            arrayList.add(i);
        }
        long arrayListTime = System.currentTimeMillis() - start;
        
        start = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            linkedList.add(i);
        }
        long linkedListTime = System.currentTimeMillis() - start;
        
        System.out.println("ArrayList add time: " + arrayListTime + "ms");
        System.out.println("LinkedList add time: " + linkedListTime + "ms");
        
        // HashMap vs TreeMap performance
        Map<String, Integer> hashMap = new HashMap<>();
        Map<String, Integer> treeMap = new TreeMap<>();
        
        start = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            hashMap.put("key" + i, i);
        }
        long hashMapTime = System.currentTimeMillis() - start;
        
        start = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            treeMap.put("key" + i, i);
        }
        long treeMapTime = System.currentTimeMillis() - start;
        
        System.out.println("HashMap put time: " + hashMapTime + "ms");
        System.out.println("TreeMap put time: " + treeMapTime + "ms");
    }
}
```

## Exercises

### Exercise 1: List Operations
Create methods to:
- Find the most frequent element in a list
- Reverse a list without using Collections.reverse()
- Find pairs that sum to a target value
- Merge two sorted lists

### Exercise 2: Set Operations
Create methods to:
- Find the symmetric difference of two sets
- Check if a set is a subset of another
- Find all unique combinations of elements
- Implement a custom Set with specific ordering

### Exercise 3: Map Operations
Create methods to:
- Find the key with the highest value
- Group elements by frequency
- Create a bidirectional map
- Implement a cache with size limit

### Exercise 4: Advanced Collections
Create methods to:
- Implement a custom iterator
- Create a thread-safe collection wrapper
- Build a custom collection with specific behavior
- Implement a priority queue with custom comparator

## Practice Files
- `CollectionsDemo.java` - Basic collections examples
- `ListOperations.java` - List-specific operations
- `SetOperations.java` - Set-specific operations
- `MapOperations.java` - Map-specific operations
- `CollectionsExercises.java` - Practice problems

## Key Takeaways
1. Choose the right collection based on your needs
2. ArrayList for random access, LinkedList for frequent insertions/deletions
3. HashSet for unique elements, TreeSet for sorted elements
4. HashMap for fast lookups, TreeMap for sorted keys
5. Use generics to ensure type safety
6. Consider performance implications when choosing collections
7. Use Collections utility class for common operations
8. Understand the difference between ordered and sorted collections

## Next Steps
After completing this lesson, move on to:
- Exception handling
- File I/O operations
- Streams API
- Concurrency and threading

## Additional Resources
- [Java Collections Tutorial](https://docs.oracle.com/javase/tutorial/collections/)
- [Collections Framework Overview](https://docs.oracle.com/javase/8/docs/api/java/util/package-summary.html)