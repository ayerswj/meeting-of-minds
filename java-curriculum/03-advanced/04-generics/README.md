# Lesson 10: Generics

## Learning Objectives
- Understand the concept of generics and type safety
- Master generic classes, methods, and interfaces
- Learn about bounded types and wildcards
- Practice advanced generic patterns and constraints
- Understand type erasure and its implications
- Apply generics in real-world scenarios

## What are Generics?

Generics provide type safety at compile time by allowing you to write code that works with different types while maintaining type checking. They were introduced in Java 5 to eliminate the need for casting and provide compile-time type safety.

### Benefits of Generics
- **Type Safety**: Catch type errors at compile time
- **Eliminate Casting**: No need for explicit type casting
- **Code Reusability**: Write once, use with multiple types
- **Better Performance**: Avoid boxing/unboxing overhead

## Generic Classes

### Basic Generic Class
```java
public class Box<T> {
    private T content;
    
    public Box(T content) {
        this.content = content;
    }
    
    public T getContent() {
        return content;
    }
    
    public void setContent(T content) {
        this.content = content;
    }
    
    @Override
    public String toString() {
        return "Box containing: " + content;
    }
}

// Usage examples
public class GenericClassDemo {
    public static void main(String[] args) {
        // Integer box
        Box<Integer> integerBox = new Box<>(42);
        Integer value = integerBox.getContent(); // No casting needed
        System.out.println(integerBox);
        
        // String box
        Box<String> stringBox = new Box<>("Hello Generics");
        String text = stringBox.getContent(); // No casting needed
        System.out.println(stringBox);
        
        // Double box
        Box<Double> doubleBox = new Box<>(3.14159);
        Double pi = doubleBox.getContent();
        System.out.println(doubleBox);
        
        // This would cause a compile-time error:
        // integerBox.setContent("String"); // Type mismatch
    }
}
```

### Multiple Type Parameters
```java
public class Pair<K, V> {
    private K key;
    private V value;
    
    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }
    
    public K getKey() {
        return key;
    }
    
    public V getValue() {
        return value;
    }
    
    public void setKey(K key) {
        this.key = key;
    }
    
    public void setValue(V value) {
        this.value = value;
    }
    
    @Override
    public String toString() {
        return "(" + key + ", " + value + ")";
    }
}

public class MultipleTypeParametersDemo {
    public static void main(String[] args) {
        // String-Integer pair
        Pair<String, Integer> nameAge = new Pair<>("John", 30);
        System.out.println(nameAge);
        
        // Integer-Double pair
        Pair<Integer, Double> idScore = new Pair<>(101, 95.5);
        System.out.println(idScore);
        
        // String-String pair
        Pair<String, String> firstNameLastName = new Pair<>("John", "Doe");
        System.out.println(firstNameLastName);
        
        // Nested generics
        Pair<String, Pair<Integer, Double>> complexPair = 
            new Pair<>("Student", new Pair<>(101, 95.5));
        System.out.println(complexPair);
    }
}
```

## Generic Methods

### Basic Generic Method
```java
public class GenericMethodDemo {
    
    // Generic method with type parameter T
    public static <T> void printArray(T[] array) {
        for (T element : array) {
            System.out.print(element + " ");
        }
        System.out.println();
    }
    
    // Generic method that returns a value
    public static <T> T getFirstElement(T[] array) {
        if (array.length > 0) {
            return array[0];
        }
        return null;
    }
    
    // Generic method with multiple type parameters
    public static <T, U> void printPair(T first, U second) {
        System.out.println("First: " + first + ", Second: " + second);
    }
    
    // Generic method that compares two objects
    public static <T extends Comparable<T>> T findMax(T a, T b) {
        return a.compareTo(b) > 0 ? a : b;
    }
    
    public static void main(String[] args) {
        // Integer array
        Integer[] intArray = {1, 2, 3, 4, 5};
        printArray(intArray);
        System.out.println("First element: " + getFirstElement(intArray));
        
        // String array
        String[] stringArray = {"Hello", "World", "Generics"};
        printArray(stringArray);
        System.out.println("First element: " + getFirstElement(stringArray));
        
        // Double array
        Double[] doubleArray = {1.1, 2.2, 3.3};
        printArray(doubleArray);
        
        // Multiple type parameters
        printPair("Hello", 42);
        printPair(3.14, true);
        
        // Using comparable constraint
        System.out.println("Max of 10 and 20: " + findMax(10, 20));
        System.out.println("Max of 'apple' and 'banana': " + findMax("apple", "banana"));
    }
}
```

## Bounded Types

### Upper Bounded Wildcards
```java
public class BoundedTypesDemo {
    
    // Upper bounded wildcard - accepts Number or its subtypes
    public static double sumOfNumbers(List<? extends Number> numbers) {
        double sum = 0.0;
        for (Number number : numbers) {
            sum += number.doubleValue();
        }
        return sum;
    }
    
    // Upper bounded type parameter
    public static <T extends Number> T findMax(T a, T b) {
        return a.doubleValue() > b.doubleValue() ? a : b;
    }
    
    // Multiple bounds
    public static <T extends Number & Comparable<T>> T findMaxComparable(T a, T b) {
        return a.compareTo(b) > 0 ? a : b;
    }
    
    public static void main(String[] args) {
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5);
        List<Double> doubles = Arrays.asList(1.1, 2.2, 3.3, 4.4, 5.5);
        List<Long> longs = Arrays.asList(1L, 2L, 3L, 4L, 5L);
        
        System.out.println("Sum of integers: " + sumOfNumbers(integers));
        System.out.println("Sum of doubles: " + sumOfNumbers(doubles));
        System.out.println("Sum of longs: " + sumOfNumbers(longs));
        
        // Using bounded type parameters
        System.out.println("Max of 10 and 20: " + findMax(10, 20));
        System.out.println("Max of 3.14 and 2.71: " + findMax(3.14, 2.71));
        
        // Multiple bounds
        System.out.println("Max comparable: " + findMaxComparable(100, 200));
    }
}
```

### Lower Bounded Wildcards
```java
public class LowerBoundedWildcardsDemo {
    
    // Lower bounded wildcard - accepts Integer or its supertypes
    public static void addNumbers(List<? super Integer> list) {
        list.add(1);
        list.add(2);
        list.add(3);
    }
    
    // Lower bounded wildcard with multiple elements
    public static void addAllNumbers(List<? super Integer> target, List<Integer> source) {
        target.addAll(source);
    }
    
    public static void main(String[] args) {
        List<Number> numberList = new ArrayList<>();
        List<Object> objectList = new ArrayList<>();
        List<Integer> integerList = new ArrayList<>();
        
        // Adding numbers to different list types
        addNumbers(numberList);
        addNumbers(objectList);
        addNumbers(integerList);
        
        System.out.println("Number list: " + numberList);
        System.out.println("Object list: " + objectList);
        System.out.println("Integer list: " + integerList);
        
        // Adding all numbers from source to target
        List<Integer> source = Arrays.asList(4, 5, 6);
        addAllNumbers(numberList, source);
        System.out.println("After adding source: " + numberList);
    }
}
```

## Wildcards

### Unbounded Wildcards
```java
public class WildcardsDemo {
    
    // Unbounded wildcard - accepts any type
    public static void printList(List<?> list) {
        for (Object item : list) {
            System.out.print(item + " ");
        }
        System.out.println();
    }
    
    // Unbounded wildcard with size check
    public static boolean isEmpty(List<?> list) {
        return list == null || list.isEmpty();
    }
    
    // Unbounded wildcard with size
    public static int size(List<?> list) {
        return list == null ? 0 : list.size();
    }
    
    public static void main(String[] args) {
        List<String> stringList = Arrays.asList("Hello", "World");
        List<Integer> intList = Arrays.asList(1, 2, 3, 4, 5);
        List<Double> doubleList = Arrays.asList(1.1, 2.2, 3.3);
        
        printList(stringList);
        printList(intList);
        printList(doubleList);
        
        System.out.println("String list empty: " + isEmpty(stringList));
        System.out.println("Integer list size: " + size(intList));
    }
}
```

### Wildcard Capture
```java
public class WildcardCaptureDemo {
    
    // Wildcard capture helper method
    public static void reverse(List<?> list) {
        reverseHelper(list);
    }
    
    // Helper method with type parameter
    private static <T> void reverseHelper(List<T> list) {
        for (int i = 0, j = list.size() - 1; i < j; i++, j--) {
            T temp = list.get(i);
            list.set(i, list.get(j));
            list.set(j, temp);
        }
    }
    
    // Another example of wildcard capture
    public static void swap(List<?> list, int i, int j) {
        swapHelper(list, i, j);
    }
    
    private static <T> void swapHelper(List<T> list, int i, int j) {
        T temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }
    
    public static void main(String[] args) {
        List<String> stringList = new ArrayList<>(Arrays.asList("A", "B", "C", "D"));
        List<Integer> intList = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        
        System.out.println("Original string list: " + stringList);
        reverse(stringList);
        System.out.println("Reversed string list: " + stringList);
        
        System.out.println("Original int list: " + intList);
        swap(intList, 0, 4);
        System.out.println("After swap: " + intList);
    }
}
```

## Generic Interfaces

### Basic Generic Interface
```java
public interface Container<T> {
    void add(T item);
    T get(int index);
    int size();
    boolean isEmpty();
}

public class GenericContainer<T> implements Container<T> {
    private List<T> items;
    
    public GenericContainer() {
        this.items = new ArrayList<>();
    }
    
    @Override
    public void add(T item) {
        items.add(item);
    }
    
    @Override
    public T get(int index) {
        if (index >= 0 && index < items.size()) {
            return items.get(index);
        }
        throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + items.size());
    }
    
    @Override
    public int size() {
        return items.size();
    }
    
    @Override
    public boolean isEmpty() {
        return items.isEmpty();
    }
    
    @Override
    public String toString() {
        return items.toString();
    }
}

public class GenericInterfaceDemo {
    public static void main(String[] args) {
        Container<String> stringContainer = new GenericContainer<>();
        stringContainer.add("Hello");
        stringContainer.add("World");
        stringContainer.add("Generics");
        
        System.out.println("String container: " + stringContainer);
        System.out.println("Size: " + stringContainer.size());
        System.out.println("First element: " + stringContainer.get(0));
        
        Container<Integer> intContainer = new GenericContainer<>();
        intContainer.add(1);
        intContainer.add(2);
        intContainer.add(3);
        
        System.out.println("Integer container: " + intContainer);
        System.out.println("Size: " + intContainer.size());
    }
}
```

### Bounded Generic Interface
```java
public interface Comparable<T> {
    int compareTo(T other);
}

public interface Sorter<T extends Comparable<T>> {
    void sort(List<T> list);
}

public class BubbleSorter<T extends Comparable<T>> implements Sorter<T> {
    @Override
    public void sort(List<T> list) {
        int n = list.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (list.get(j).compareTo(list.get(j + 1)) > 0) {
                    // Swap elements
                    T temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);
                }
            }
        }
    }
}

public class Student implements Comparable<Student> {
    private String name;
    private int grade;
    
    public Student(String name, int grade) {
        this.name = name;
        this.grade = grade;
    }
    
    @Override
    public int compareTo(Student other) {
        return Integer.compare(this.grade, other.grade);
    }
    
    @Override
    public String toString() {
        return name + " (" + grade + ")";
    }
}

public class BoundedInterfaceDemo {
    public static void main(String[] args) {
        List<Student> students = Arrays.asList(
            new Student("Alice", 85),
            new Student("Bob", 92),
            new Student("Charlie", 78),
            new Student("Diana", 95)
        );
        
        System.out.println("Original list: " + students);
        
        Sorter<Student> sorter = new BubbleSorter<>();
        sorter.sort(students);
        
        System.out.println("Sorted list: " + students);
    }
}
```

## Advanced Generic Patterns

### Generic Stack Implementation
```java
public class GenericStack<T> {
    private List<T> elements;
    
    public GenericStack() {
        this.elements = new ArrayList<>();
    }
    
    public void push(T element) {
        elements.add(element);
    }
    
    public T pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        return elements.remove(elements.size() - 1);
    }
    
    public T peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        return elements.get(elements.size() - 1);
    }
    
    public boolean isEmpty() {
        return elements.isEmpty();
    }
    
    public int size() {
        return elements.size();
    }
    
    @Override
    public String toString() {
        return elements.toString();
    }
}

public class StackDemo {
    public static void main(String[] args) {
        GenericStack<String> stringStack = new GenericStack<>();
        stringStack.push("First");
        stringStack.push("Second");
        stringStack.push("Third");
        
        System.out.println("Stack: " + stringStack);
        System.out.println("Peek: " + stringStack.peek());
        System.out.println("Pop: " + stringStack.pop());
        System.out.println("Stack after pop: " + stringStack);
        
        GenericStack<Integer> intStack = new GenericStack<>();
        intStack.push(1);
        intStack.push(2);
        intStack.push(3);
        
        System.out.println("Integer stack: " + intStack);
        while (!intStack.isEmpty()) {
            System.out.println("Popped: " + intStack.pop());
        }
    }
}
```

### Generic Queue Implementation
```java
public class GenericQueue<T> {
    private List<T> elements;
    
    public GenericQueue() {
        this.elements = new ArrayList<>();
    }
    
    public void enqueue(T element) {
        elements.add(element);
    }
    
    public T dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        return elements.remove(0);
    }
    
    public T peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        return elements.get(0);
    }
    
    public boolean isEmpty() {
        return elements.isEmpty();
    }
    
    public int size() {
        return elements.size();
    }
    
    @Override
    public String toString() {
        return elements.toString();
    }
}

public class QueueDemo {
    public static void main(String[] args) {
        GenericQueue<String> stringQueue = new GenericQueue<>();
        stringQueue.enqueue("First");
        stringQueue.enqueue("Second");
        stringQueue.enqueue("Third");
        
        System.out.println("Queue: " + stringQueue);
        System.out.println("Peek: " + stringQueue.peek());
        System.out.println("Dequeue: " + stringQueue.dequeue());
        System.out.println("Queue after dequeue: " + stringQueue);
        
        GenericQueue<Integer> intQueue = new GenericQueue<>();
        intQueue.enqueue(1);
        intQueue.enqueue(2);
        intQueue.enqueue(3);
        
        System.out.println("Integer queue: " + intQueue);
        while (!intQueue.isEmpty()) {
            System.out.println("Dequeued: " + intQueue.dequeue());
        }
    }
}
```

### Generic Cache Implementation
```java
public class GenericCache<K, V> {
    private Map<K, V> cache;
    private int maxSize;
    
    public GenericCache(int maxSize) {
        this.cache = new LinkedHashMap<>(maxSize, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
                return size() > maxSize;
            }
        };
        this.maxSize = maxSize;
    }
    
    public void put(K key, V value) {
        cache.put(key, value);
    }
    
    public V get(K key) {
        return cache.get(key);
    }
    
    public boolean containsKey(K key) {
        return cache.containsKey(key);
    }
    
    public void remove(K key) {
        cache.remove(key);
    }
    
    public void clear() {
        cache.clear();
    }
    
    public int size() {
        return cache.size();
    }
    
    public boolean isEmpty() {
        return cache.isEmpty();
    }
    
    @Override
    public String toString() {
        return cache.toString();
    }
}

public class CacheDemo {
    public static void main(String[] args) {
        GenericCache<String, Integer> cache = new GenericCache<>(3);
        
        cache.put("A", 1);
        cache.put("B", 2);
        cache.put("C", 3);
        
        System.out.println("Cache: " + cache);
        System.out.println("Get A: " + cache.get("A"));
        
        // This will evict the oldest entry (A)
        cache.put("D", 4);
        System.out.println("Cache after adding D: " + cache);
        
        // Access B to make it most recently used
        cache.get("B");
        cache.put("E", 5);
        System.out.println("Cache after adding E: " + cache);
    }
}
```

## Type Erasure and Runtime Behavior

### Understanding Type Erasure
```java
public class TypeErasureDemo {
    
    public static void demonstrateTypeErasure() {
        List<String> stringList = new ArrayList<>();
        List<Integer> intList = new ArrayList<>();
        
        // At runtime, both lists have the same type due to type erasure
        System.out.println("String list class: " + stringList.getClass());
        System.out.println("Integer list class: " + intList.getClass());
        System.out.println("Are they the same class? " + 
            (stringList.getClass() == intList.getClass()));
        
        // Type erasure in action
        List rawList = stringList; // Raw type assignment
        rawList.add(42); // This compiles but is unsafe
        
        // This will cause ClassCastException at runtime
        try {
            String str = stringList.get(1); // Exception here
        } catch (ClassCastException e) {
            System.out.println("ClassCastException caught: " + e.getMessage());
        }
    }
    
    // Generic method with type erasure
    public static <T> void addToList(List<T> list, T item) {
        list.add(item);
    }
    
    // This method demonstrates that type information is lost at runtime
    public static void demonstrateRuntimeLimitations() {
        List<String> stringList = new ArrayList<>();
        
        // This works fine
        addToList(stringList, "Hello");
        
        // At runtime, we can't check the generic type
        System.out.println("List type at runtime: " + stringList.getClass());
        
        // We can't do this at runtime:
        // if (stringList instanceof List<String>) // Compilation error
    }
    
    public static void main(String[] args) {
        demonstrateTypeErasure();
        System.out.println();
        demonstrateRuntimeLimitations();
    }
}
```

## Real-World Examples

### Generic Repository Pattern
```java
public interface Repository<T, ID> {
    T save(T entity);
    Optional<T> findById(ID id);
    List<T> findAll();
    void deleteById(ID id);
    boolean existsById(ID id);
    long count();
}

public class InMemoryRepository<T, ID> implements Repository<T, ID> {
    private Map<ID, T> storage;
    private Function<T, ID> idExtractor;
    
    public InMemoryRepository(Function<T, ID> idExtractor) {
        this.storage = new HashMap<>();
        this.idExtractor = idExtractor;
    }
    
    @Override
    public T save(T entity) {
        ID id = idExtractor.apply(entity);
        storage.put(id, entity);
        return entity;
    }
    
    @Override
    public Optional<T> findById(ID id) {
        return Optional.ofNullable(storage.get(id));
    }
    
    @Override
    public List<T> findAll() {
        return new ArrayList<>(storage.values());
    }
    
    @Override
    public void deleteById(ID id) {
        storage.remove(id);
    }
    
    @Override
    public boolean existsById(ID id) {
        return storage.containsKey(id);
    }
    
    @Override
    public long count() {
        return storage.size();
    }
}

public class User {
    private Long id;
    private String name;
    private String email;
    
    public User(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }
    
    public Long getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    
    @Override
    public String toString() {
        return "User{id=" + id + ", name='" + name + "', email='" + email + "'}";
    }
}

public class RepositoryDemo {
    public static void main(String[] args) {
        Repository<User, Long> userRepository = new InMemoryRepository<>(User::getId);
        
        // Save users
        User user1 = new User(1L, "John Doe", "john@example.com");
        User user2 = new User(2L, "Jane Smith", "jane@example.com");
        
        userRepository.save(user1);
        userRepository.save(user2);
        
        // Find users
        System.out.println("All users: " + userRepository.findAll());
        System.out.println("User with ID 1: " + userRepository.findById(1L));
        System.out.println("User count: " + userRepository.count());
        
        // Check existence
        System.out.println("User 1 exists: " + userRepository.existsById(1L));
        System.out.println("User 3 exists: " + userRepository.existsById(3L));
        
        // Delete user
        userRepository.deleteById(1L);
        System.out.println("After deletion: " + userRepository.findAll());
    }
}
```

## Exercises

### Exercise 1: Generic Calculator
Create a generic calculator that can perform operations on different numeric types (Integer, Double, etc.).

### Exercise 2: Generic Tree Implementation
Implement a generic binary tree with methods for insertion, traversal, and search.

### Exercise 3: Generic Sorting Utility
Create a utility class with generic sorting methods that work with any comparable type.

### Exercise 4: Generic Event System
Build a generic event system that can handle different types of events and listeners.

## Practice Files
- `GenericClassDemo.java` - Basic generic class examples
- `GenericMethodDemo.java` - Generic method examples
- `BoundedTypesDemo.java` - Bounded type examples
- `WildcardsDemo.java` - Wildcard examples
- `GenericInterfaceDemo.java` - Generic interface examples
- `AdvancedPatternsDemo.java` - Advanced generic patterns
- `TypeErasureDemo.java` - Type erasure examples
- `RepositoryDemo.java` - Real-world repository pattern

## Key Takeaways
1. Generics provide compile-time type safety
2. Use bounded types to restrict generic parameters
3. Wildcards provide flexibility when you don't need type information
4. Type erasure means generic information is lost at runtime
5. Generics eliminate the need for casting
6. Generic methods can be static or instance methods
7. Use generic interfaces for reusable abstractions
8. Advanced patterns like repositories benefit greatly from generics

## Next Steps
After completing this lesson, move on to:
- Streams API and functional programming
- Lambda expressions
- Optional class
- Date and Time API

## Additional Resources
- [Java Generics Tutorial](https://docs.oracle.com/javase/tutorial/java/generics/)
- [Generics in Java](https://docs.oracle.com/javase/tutorial/extra/generics/)
- [Type Erasure](https://docs.oracle.com/javase/tutorial/java/generics/erasure.html)