# Week 7: Advanced Generics and Collections

## Learning Objectives
- Master generic types and type parameters
- Understand bounded generics and wildcards
- Learn advanced collection implementations
- Work with custom data structures
- Apply generics in real-world scenarios
- Understand type erasure and its implications

## Theory

### 1. Generics Fundamentals

#### Generic Classes
```java
public class Box<T> {
    private T content;
    
    public void set(T content) {
        this.content = content;
    }
    
    public T get() {
        return content;
    }
}
```

#### Generic Methods
```java
public static <T> void printArray(T[] array) {
    for (T element : array) {
        System.out.println(element);
    }
}
```

#### Type Bounds
- **Upper Bounds**: `T extends Class`
- **Lower Bounds**: `T super Class`
- **Multiple Bounds**: `T extends Class1 & Interface1`

### 2. Wildcards
- **Unbounded**: `?`
- **Upper Bounded**: `? extends Type`
- **Lower Bounded**: `? super Type`

### 3. Advanced Collections
- **TreeMap/TreeSet**: Sorted collections
- **LinkedHashMap/LinkedHashSet**: Ordered collections
- **PriorityQueue**: Priority-based queue
- **ConcurrentHashMap**: Thread-safe map

## Code Examples

### Example 1: Generic Classes and Methods
```java
// Generic class with multiple type parameters
public class Pair<T, U> {
    private T first;
    private U second;
    
    public Pair(T first, U second) {
        this.first = first;
        this.second = second;
    }
    
    public T getFirst() { return first; }
    public U getSecond() { return second; }
    
    public void setFirst(T first) { this.first = first; }
    public void setSecond(U second) { this.second = second; }
    
    @Override
    public String toString() {
        return "(" + first + ", " + second + ")";
    }
}

// Generic method with type bounds
public class GenericUtils {
    
    // Generic method to find maximum
    public static <T extends Comparable<T>> T findMax(T[] array) {
        if (array == null || array.length == 0) {
            return null;
        }
        
        T max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i].compareTo(max) > 0) {
                max = array[i];
            }
        }
        return max;
    }
    
    // Generic method to swap elements
    public static <T> void swap(T[] array, int i, int j) {
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
    
    // Generic method to reverse array
    public static <T> void reverse(T[] array) {
        int left = 0;
        int right = array.length - 1;
        
        while (left < right) {
            swap(array, left, right);
            left++;
            right--;
        }
    }
    
    // Generic method with multiple bounds
    public static <T extends Comparable<T> & Cloneable> T[] sortAndClone(T[] array) {
        T[] copy = array.clone();
        Arrays.sort(copy);
        return copy;
    }
}

// Main class to demonstrate generics
public class GenericsDemo {
    public static void main(String[] args) {
        // Using generic class
        Pair<String, Integer> pair1 = new Pair<>("Hello", 42);
        Pair<Double, Boolean> pair2 = new Pair<>(3.14, true);
        
        System.out.println("Pair 1: " + pair1);
        System.out.println("Pair 2: " + pair2);
        
        // Using generic methods
        Integer[] numbers = {5, 2, 8, 1, 9, 3};
        String[] words = {"apple", "banana", "cherry", "date"};
        
        System.out.println("Max number: " + GenericUtils.findMax(numbers));
        System.out.println("Max word: " + GenericUtils.findMax(words));
        
        System.out.println("Original array: " + Arrays.toString(numbers));
        GenericUtils.reverse(numbers);
        System.out.println("Reversed array: " + Arrays.toString(numbers));
    }
}
```

### Example 2: Wildcards and Bounded Types
```java
import java.util.*;

// Base class and hierarchy
class Animal {
    protected String name;
    
    public Animal(String name) {
        this.name = name;
    }
    
    public String getName() { return name; }
    
    @Override
    public String toString() {
        return "Animal: " + name;
    }
}

class Dog extends Animal {
    public Dog(String name) {
        super(name);
    }
    
    @Override
    public String toString() {
        return "Dog: " + name;
    }
}

class Cat extends Animal {
    public Cat(String name) {
        super(name);
    }
    
    @Override
    public String toString() {
        return "Cat: " + name;
    }
}

// Generic container class
class Container<T> {
    private List<T> items = new ArrayList<>();
    
    public void add(T item) {
        items.add(item);
    }
    
    public T get(int index) {
        return items.get(index);
    }
    
    public List<T> getAll() {
        return new ArrayList<>(items);
    }
    
    public int size() {
        return items.size();
    }
}

// Utility class demonstrating wildcards
public class WildcardDemo {
    
    // Upper bounded wildcard - can read from container
    public static void printAnimals(Container<? extends Animal> container) {
        System.out.println("Animals in container:");
        for (int i = 0; i < container.size(); i++) {
            Animal animal = container.get(i);
            System.out.println(animal);
        }
    }
    
    // Lower bounded wildcard - can add to container
    public static void addDogs(Container<? super Dog> container) {
        container.add(new Dog("Buddy"));
        container.add(new Dog("Max"));
        System.out.println("Added dogs to container");
    }
    
    // Unbounded wildcard - can only use Object methods
    public static void printContainerSize(Container<?> container) {
        System.out.println("Container size: " + container.size());
    }
    
    // Generic method with wildcards
    public static <T> void copyContainer(Container<? extends T> source, 
                                       Container<? super T> destination) {
        for (int i = 0; i < source.size(); i++) {
            destination.add(source.get(i));
        }
        System.out.println("Copied " + source.size() + " items");
    }
    
    public static void main(String[] args) {
        // Create containers
        Container<Animal> animalContainer = new Container<>();
        Container<Dog> dogContainer = new Container<>();
        Container<Cat> catContainer = new Container<>();
        
        // Add animals
        animalContainer.add(new Animal("Generic Animal"));
        animalContainer.add(new Dog("Rex"));
        animalContainer.add(new Cat("Whiskers"));
        
        dogContainer.add(new Dog("Buddy"));
        dogContainer.add(new Dog("Max"));
        
        catContainer.add(new Cat("Fluffy"));
        catContainer.add(new Cat("Shadow"));
        
        // Demonstrate wildcards
        printAnimals(animalContainer);
        printAnimals(dogContainer);
        printAnimals(catContainer);
        
        addDogs(animalContainer); // OK - Animal is super of Dog
        // addDogs(dogContainer); // OK - Dog is super of Dog
        // addDogs(catContainer); // Compile error - Cat is not super of Dog
        
        printContainerSize(animalContainer);
        printContainerSize(dogContainer);
        
        // Copy containers
        Container<Animal> newAnimalContainer = new Container<>();
        copyContainer(dogContainer, newAnimalContainer);
        printAnimals(newAnimalContainer);
    }
}
```

### Example 3: Advanced Collections
```java
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class AdvancedCollectionsDemo {
    
    // TreeMap - sorted map
    public static void demonstrateTreeMap() {
        System.out.println("=== TreeMap Demo ===");
        TreeMap<String, Integer> treeMap = new TreeMap<>();
        
        treeMap.put("Zebra", 100);
        treeMap.put("Apple", 50);
        treeMap.put("Banana", 75);
        treeMap.put("Cherry", 25);
        
        System.out.println("TreeMap (sorted by key): " + treeMap);
        System.out.println("First key: " + treeMap.firstKey());
        System.out.println("Last key: " + treeMap.lastKey());
        System.out.println("Keys less than 'C': " + treeMap.headMap("C"));
        System.out.println("Keys between 'A' and 'C': " + treeMap.subMap("A", "C"));
    }
    
    // LinkedHashMap - ordered map
    public static void demonstrateLinkedHashMap() {
        System.out.println("\n=== LinkedHashMap Demo ===");
        LinkedHashMap<String, Integer> linkedMap = new LinkedHashMap<>();
        
        linkedMap.put("First", 1);
        linkedMap.put("Second", 2);
        linkedMap.put("Third", 3);
        linkedMap.put("Fourth", 4);
        
        System.out.println("LinkedHashMap (insertion order): " + linkedMap);
        
        // Access order
        LinkedHashMap<String, Integer> accessOrderMap = new LinkedHashMap<>(16, 0.75f, true);
        accessOrderMap.put("A", 1);
        accessOrderMap.put("B", 2);
        accessOrderMap.put("C", 3);
        
        accessOrderMap.get("A"); // Move A to end
        System.out.println("Access order map: " + accessOrderMap);
    }
    
    // PriorityQueue - priority-based queue
    public static void demonstratePriorityQueue() {
        System.out.println("\n=== PriorityQueue Demo ===");
        
        // Natural ordering
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.offer(5);
        pq.offer(2);
        pq.offer(8);
        pq.offer(1);
        pq.offer(9);
        
        System.out.println("PriorityQueue (min-heap):");
        while (!pq.isEmpty()) {
            System.out.print(pq.poll() + " ");
        }
        System.out.println();
        
        // Custom comparator
        PriorityQueue<String> stringPq = new PriorityQueue<>((s1, s2) -> s2.compareTo(s1));
        stringPq.offer("apple");
        stringPq.offer("banana");
        stringPq.offer("cherry");
        stringPq.offer("date");
        
        System.out.println("PriorityQueue (reverse order):");
        while (!stringPq.isEmpty()) {
            System.out.print(stringPq.poll() + " ");
        }
        System.out.println();
    }
    
    // ConcurrentHashMap - thread-safe map
    public static void demonstrateConcurrentHashMap() {
        System.out.println("\n=== ConcurrentHashMap Demo ===");
        ConcurrentHashMap<String, Integer> concurrentMap = new ConcurrentHashMap<>();
        
        concurrentMap.put("A", 1);
        concurrentMap.put("B", 2);
        concurrentMap.put("C", 3);
        
        // Atomic operations
        concurrentMap.putIfAbsent("A", 10); // Won't replace existing
        concurrentMap.putIfAbsent("D", 4);  // Will add new
        
        System.out.println("ConcurrentHashMap: " + concurrentMap);
        
        // Atomic update
        concurrentMap.compute("A", (key, value) -> value != null ? value + 10 : 0);
        System.out.println("After compute: " + concurrentMap);
    }
    
    // Custom comparator examples
    public static void demonstrateCustomComparators() {
        System.out.println("\n=== Custom Comparator Demo ===");
        
        List<String> words = Arrays.asList("apple", "Banana", "cherry", "Date", "elderberry");
        
        // Case-insensitive sort
        words.sort(String.CASE_INSENSITIVE_ORDER);
        System.out.println("Case-insensitive sort: " + words);
        
        // Sort by length, then alphabetically
        words.sort((s1, s2) -> {
            int lengthCompare = Integer.compare(s1.length(), s2.length());
            return lengthCompare != 0 ? lengthCompare : s1.compareTo(s2);
        });
        System.out.println("Sort by length then alphabetically: " + words);
    }
    
    public static void main(String[] args) {
        demonstrateTreeMap();
        demonstrateLinkedHashMap();
        demonstratePriorityQueue();
        demonstrateConcurrentHashMap();
        demonstrateCustomComparators();
    }
}
```

### Example 4: Custom Data Structures
```java
import java.util.*;

// Generic Stack implementation
public class GenericStack<T> {
    private List<T> elements = new ArrayList<>();
    
    public void push(T element) {
        elements.add(element);
    }
    
    public T pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return elements.remove(elements.size() - 1);
    }
    
    public T peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
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

// Generic Queue implementation
public class GenericQueue<T> {
    private LinkedList<T> elements = new LinkedList<>();
    
    public void enqueue(T element) {
        elements.addLast(element);
    }
    
    public T dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        return elements.removeFirst();
    }
    
    public T peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        return elements.getFirst();
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

// Generic Binary Tree implementation
class TreeNode<T> {
    T data;
    TreeNode<T> left;
    TreeNode<T> right;
    
    public TreeNode(T data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }
}

public class GenericBinaryTree<T extends Comparable<T>> {
    private TreeNode<T> root;
    
    public void insert(T data) {
        root = insertRec(root, data);
    }
    
    private TreeNode<T> insertRec(TreeNode<T> node, T data) {
        if (node == null) {
            return new TreeNode<>(data);
        }
        
        if (data.compareTo(node.data) < 0) {
            node.left = insertRec(node.left, data);
        } else if (data.compareTo(node.data) > 0) {
            node.right = insertRec(node.right, data);
        }
        
        return node;
    }
    
    public void inOrderTraversal() {
        System.out.print("In-order traversal: ");
        inOrderRec(root);
        System.out.println();
    }
    
    private void inOrderRec(TreeNode<T> node) {
        if (node != null) {
            inOrderRec(node.left);
            System.out.print(node.data + " ");
            inOrderRec(node.right);
        }
    }
    
    public boolean search(T data) {
        return searchRec(root, data);
    }
    
    private boolean searchRec(TreeNode<T> node, T data) {
        if (node == null) {
            return false;
        }
        
        if (data.equals(node.data)) {
            return true;
        }
        
        if (data.compareTo(node.data) < 0) {
            return searchRec(node.left, data);
        } else {
            return searchRec(node.right, data);
        }
    }
}

// Main class to demonstrate custom data structures
public class CustomDataStructuresDemo {
    public static void main(String[] args) {
        // Stack demo
        System.out.println("=== Generic Stack Demo ===");
        GenericStack<String> stringStack = new GenericStack<>();
        stringStack.push("First");
        stringStack.push("Second");
        stringStack.push("Third");
        
        System.out.println("Stack: " + stringStack);
        System.out.println("Peek: " + stringStack.peek());
        System.out.println("Pop: " + stringStack.pop());
        System.out.println("Stack after pop: " + stringStack);
        
        // Queue demo
        System.out.println("\n=== Generic Queue Demo ===");
        GenericQueue<Integer> intQueue = new GenericQueue<>();
        intQueue.enqueue(1);
        intQueue.enqueue(2);
        intQueue.enqueue(3);
        
        System.out.println("Queue: " + intQueue);
        System.out.println("Peek: " + intQueue.peek());
        System.out.println("Dequeue: " + intQueue.dequeue());
        System.out.println("Queue after dequeue: " + intQueue);
        
        // Binary Tree demo
        System.out.println("\n=== Generic Binary Tree Demo ===");
        GenericBinaryTree<Integer> tree = new GenericBinaryTree<>();
        tree.insert(50);
        tree.insert(30);
        tree.insert(70);
        tree.insert(20);
        tree.insert(40);
        tree.insert(60);
        tree.insert(80);
        
        tree.inOrderTraversal();
        System.out.println("Search for 40: " + tree.search(40));
        System.out.println("Search for 90: " + tree.search(90));
    }
}
```

### Example 5: Type Erasure and Runtime Behavior
```java
import java.lang.reflect.*;
import java.util.*;

public class TypeErasureDemo {
    
    // Demonstrate type erasure
    public static void demonstrateTypeErasure() {
        System.out.println("=== Type Erasure Demo ===");
        
        List<String> stringList = new ArrayList<>();
        List<Integer> intList = new ArrayList<>();
        
        // At runtime, both lists have the same type
        System.out.println("String list class: " + stringList.getClass());
        System.out.println("Integer list class: " + intList.getClass());
        System.out.println("Are they the same class? " + 
                          (stringList.getClass() == intList.getClass()));
        
        // Reflection shows erased type
        try {
            Field elementData = ArrayList.class.getDeclaredField("elementData");
            elementData.setAccessible(true);
            System.out.println("Element data type: " + elementData.getType());
        } catch (NoSuchFieldException e) {
            System.err.println("Field not found: " + e.getMessage());
        }
    }
    
    // Demonstrate generic method with reflection
    public static <T> void analyzeGenericType(T obj) {
        System.out.println("Object type: " + obj.getClass());
        System.out.println("Object: " + obj);
        
        // Note: We can't get the generic type parameter T at runtime
        // due to type erasure
    }
    
    // Demonstrate type bounds checking
    public static <T extends Number> void processNumbers(List<T> numbers) {
        double sum = 0.0;
        for (T number : numbers) {
            sum += number.doubleValue(); // Can call Number methods
        }
        System.out.println("Sum: " + sum);
    }
    
    // Demonstrate wildcard usage
    public static void processAnyList(List<?> list) {
        System.out.println("List size: " + list.size());
        for (Object item : list) {
            System.out.println("Item: " + item);
        }
    }
    
    public static void main(String[] args) {
        demonstrateTypeErasure();
        
        System.out.println("\n=== Generic Method Demo ===");
        analyzeGenericType("Hello");
        analyzeGenericType(42);
        analyzeGenericType(3.14);
        
        System.out.println("\n=== Type Bounds Demo ===");
        List<Integer> intList = Arrays.asList(1, 2, 3, 4, 5);
        List<Double> doubleList = Arrays.asList(1.1, 2.2, 3.3, 4.4, 5.5);
        
        processNumbers(intList);
        processNumbers(doubleList);
        
        System.out.println("\n=== Wildcard Demo ===");
        List<String> stringList = Arrays.asList("a", "b", "c");
        processAnyList(stringList);
        processAnyList(intList);
    }
}
```

## Practice Exercises

### Exercise 1: Generic Calculator
Create a generic calculator that can work with different numeric types.

### Exercise 2: Generic Cache System
Build a generic cache system with different eviction strategies.

### Exercise 3: Generic Sorting Algorithms
Implement generic versions of sorting algorithms (bubble, merge, quick).

### Exercise 4: Generic Graph Implementation
Create a generic graph data structure with traversal methods.

### Exercise 5: Generic Event System
Build a generic event system with different event types and handlers.

## Mini-Project: Generic Database Simulator

Create a generic database simulator with collections:

```java
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

// Generic record class
class Record<T> {
    private String id;
    private T data;
    private long timestamp;
    
    public Record(String id, T data) {
        this.id = id;
        this.data = data;
        this.timestamp = System.currentTimeMillis();
    }
    
    // Getters
    public String getId() { return id; }
    public T getData() { return data; }
    public long getTimestamp() { return timestamp; }
    
    @Override
    public String toString() {
        return "Record{id='" + id + "', data=" + data + ", timestamp=" + timestamp + "}";
    }
}

// Generic database table
class Table<T> {
    private String name;
    private Map<String, Record<T>> records;
    private List<String> indexes;
    
    public Table(String name) {
        this.name = name;
        this.records = new ConcurrentHashMap<>();
        this.indexes = new ArrayList<>();
    }
    
    public void insert(String id, T data) {
        records.put(id, new Record<>(id, data));
        System.out.println("Inserted record with ID: " + id);
    }
    
    public Optional<Record<T>> find(String id) {
        return Optional.ofNullable(records.get(id));
    }
    
    public void update(String id, T newData) {
        if (records.containsKey(id)) {
            records.put(id, new Record<>(id, newData));
            System.out.println("Updated record with ID: " + id);
        } else {
            System.out.println("Record not found: " + id);
        }
    }
    
    public void delete(String id) {
        if (records.remove(id) != null) {
            System.out.println("Deleted record with ID: " + id);
        } else {
            System.out.println("Record not found: " + id);
        }
    }
    
    public List<Record<T>> getAllRecords() {
        return new ArrayList<>(records.values());
    }
    
    public int size() {
        return records.size();
    }
    
    public String getName() { return name; }
    
    // Generic query method
    public List<Record<T>> query(Predicate<T> predicate) {
        return records.values().stream()
                .filter(record -> predicate.test(record.getData()))
                .collect(Collectors.toList());
    }
}

// Generic database
public class GenericDatabase {
    private Map<String, Table<?>> tables;
    
    public GenericDatabase() {
        this.tables = new ConcurrentHashMap<>();
    }
    
    public <T> void createTable(String tableName) {
        tables.put(tableName, new Table<T>(tableName));
        System.out.println("Created table: " + tableName);
    }
    
    @SuppressWarnings("unchecked")
    public <T> Table<T> getTable(String tableName) {
        return (Table<T>) tables.get(tableName);
    }
    
    public void listTables() {
        System.out.println("Tables in database:");
        tables.keySet().forEach(System.out::println);
    }
    
    public void dropTable(String tableName) {
        if (tables.remove(tableName) != null) {
            System.out.println("Dropped table: " + tableName);
        } else {
            System.out.println("Table not found: " + tableName);
        }
    }
}

// User class for demonstration
class User {
    private String name;
    private int age;
    private String email;
    
    public User(String name, int age, String email) {
        this.name = name;
        this.age = age;
        this.email = email;
    }
    
    // Getters
    public String getName() { return name; }
    public int getAge() { return age; }
    public String getEmail() { return email; }
    
    @Override
    public String toString() {
        return "User{name='" + name + "', age=" + age + ", email='" + email + "'}";
    }
}

// Product class for demonstration
class Product {
    private String name;
    private double price;
    private String category;
    
    public Product(String name, double price, String category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }
    
    // Getters
    public String getName() { return name; }
    public double getPrice() { return price; }
    public String getCategory() { return category; }
    
    @Override
    public String toString() {
        return "Product{name='" + name + "', price=" + price + ", category='" + category + "'}";
    }
}

// Main class to demonstrate the database
public class DatabaseSimulator {
    public static void main(String[] args) {
        GenericDatabase db = new GenericDatabase();
        
        // Create tables
        db.createTable("users");
        db.createTable("products");
        
        // Get table references
        Table<User> userTable = db.getTable("users");
        Table<Product> productTable = db.getTable("products");
        
        // Insert users
        userTable.insert("u1", new User("Alice", 25, "alice@email.com"));
        userTable.insert("u2", new User("Bob", 30, "bob@email.com"));
        userTable.insert("u3", new User("Charlie", 35, "charlie@email.com"));
        
        // Insert products
        productTable.insert("p1", new Product("Laptop", 999.99, "Electronics"));
        productTable.insert("p2", new Product("Book", 29.99, "Books"));
        productTable.insert("p3", new Product("Phone", 699.99, "Electronics"));
        
        // Query examples
        System.out.println("\n=== Query Examples ===");
        
        // Find specific records
        userTable.find("u1").ifPresent(System.out::println);
        productTable.find("p2").ifPresent(System.out::println);
        
        // Query with predicates
        System.out.println("\nUsers older than 28:");
        userTable.query(user -> user.getAge() > 28)
                .forEach(System.out::println);
        
        System.out.println("\nElectronics products:");
        productTable.query(product -> "Electronics".equals(product.getCategory()))
                .forEach(System.out::println);
        
        System.out.println("\nExpensive products (>$500):");
        productTable.query(product -> product.getPrice() > 500)
                .forEach(System.out::println);
        
        // Update and delete operations
        System.out.println("\n=== Update and Delete ===");
        userTable.update("u1", new User("Alice Smith", 26, "alice.smith@email.com"));
        productTable.delete("p2");
        
        // Display all records
        System.out.println("\nAll users:");
        userTable.getAllRecords().forEach(System.out::println);
        
        System.out.println("\nAll products:");
        productTable.getAllRecords().forEach(System.out::println);
        
        // List all tables
        System.out.println("\n=== Database Summary ===");
        db.listTables();
        System.out.println("Users table size: " + userTable.size());
        System.out.println("Products table size: " + productTable.size());
    }
}
```

## Advanced Concepts

### 1. Type Erasure and Runtime Type Checking
```java
public class TypeErasureAdvanced {
    
    // Demonstrate type erasure limitations
    public static <T> void checkType(T obj) {
        // This won't work due to type erasure
        // if (obj instanceof T) { ... }
        
        // Instead, check the actual runtime type
        if (obj instanceof String) {
            System.out.println("It's a String: " + obj);
        } else if (obj instanceof Integer) {
            System.out.println("It's an Integer: " + obj);
        } else {
            System.out.println("It's something else: " + obj.getClass());
        }
    }
    
    // Generic method with class parameter for type checking
    public static <T> void checkTypeWithClass(T obj, Class<T> clazz) {
        if (clazz.isInstance(obj)) {
            System.out.println("Object is instance of " + clazz.getSimpleName());
        } else {
            System.out.println("Object is not instance of " + clazz.getSimpleName());
        }
    }
}
```

### 2. Generic Factory Pattern
```java
public class GenericFactory<T> {
    private Class<T> type;
    private Supplier<T> supplier;
    
    public GenericFactory(Class<T> type) {
        this.type = type;
    }
    
    public GenericFactory(Supplier<T> supplier) {
        this.supplier = supplier;
    }
    
    public T createInstance() throws Exception {
        if (supplier != null) {
            return supplier.get();
        } else {
            return type.getDeclaredConstructor().newInstance();
        }
    }
    
    public List<T> createMultiple(int count) throws Exception {
        List<T> instances = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            instances.add(createInstance());
        }
        return instances;
    }
}
```

### 3. Generic Builder Pattern
```java
public class GenericBuilder<T> {
    private T instance;
    private List<Consumer<T>> operations = new ArrayList<>();
    
    public GenericBuilder(Supplier<T> supplier) {
        this.instance = supplier.get();
    }
    
    public GenericBuilder<T> with(Consumer<T> operation) {
        operations.add(operation);
        return this;
    }
    
    public T build() {
        operations.forEach(operation -> operation.accept(instance));
        return instance;
    }
}
```

## Weekly Challenge: Generic Library Management System

Create a comprehensive library management system that:
- Uses generics for different types of items (books, DVDs, magazines)
- Implements generic search and filtering
- Supports generic borrowing and returning
- Uses advanced collections for efficient operations
- Implements generic reporting and statistics

## Assessment Criteria
- [ ] Proper use of generic types and type parameters
- [ ] Effective implementation of bounded generics
- [ ] Correct usage of wildcards
- [ ] Advanced collection implementations
- [ ] Custom generic data structures
- [ ] Understanding of type erasure

## Resources
- [Java Generics Tutorial](https://docs.oracle.com/javase/tutorial/java/generics/)
- [Java Collections Framework](https://docs.oracle.com/javase/tutorial/collections/)
- [Java Type Erasure](https://docs.oracle.com/javase/tutorial/java/generics/erasure.html)
- [Java Wildcards](https://docs.oracle.com/javase/tutorial/java/generics/wildcards.html)