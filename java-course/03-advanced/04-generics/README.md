# Lesson 10: Generics

## Learning Objectives
- Understand the concept of generics and type safety
- Master generic classes, methods, and interfaces
- Learn about bounded types and wildcards
- Practice advanced generic patterns and constraints
- Understand type erasure and its implications
- Apply generics in real-world scenarios

## What are Generics?

Generics provide type safety at compile time by allowing you to write code that works with different types while maintaining type checking. They were introduced in Java 5 to eliminate the need for casting and provide compile-time type checking.

## Basic Generic Classes

### Simple Generic Class
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
public class GenericsDemo {
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
        Double number = doubleBox.getContent();
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

// Usage
public class PairDemo {
    public static void main(String[] args) {
        // String-Integer pair
        Pair<String, Integer> nameAge = new Pair<>("John", 30);
        System.out.println(nameAge);
        
        // Integer-Double pair
        Pair<Integer, Double> idScore = new Pair<>(101, 95.5);
        System.out.println(idScore);
        
        // Nested generics
        Pair<String, Pair<Integer, Double>> student = 
            new Pair<>("Alice", new Pair<>(102, 88.5));
        System.out.println(student);
    }
}
```

## Generic Methods

### Simple Generic Method
```java
public class GenericMethods {
    
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
    
    // Generic method that compares elements
    public static <T extends Comparable<T>> T findMax(T[] array) {
        if (array.length == 0) {
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
    
    public static void main(String[] args) {
        // Test with different types
        Integer[] intArray = {1, 2, 3, 4, 5};
        String[] stringArray = {"apple", "banana", "cherry"};
        Double[] doubleArray = {1.1, 2.2, 3.3, 4.4};
        
        System.out.println("Integer array:");
        printArray(intArray);
        System.out.println("First element: " + getFirstElement(intArray));
        System.out.println("Max element: " + findMax(intArray));
        
        System.out.println("\nString array:");
        printArray(stringArray);
        System.out.println("First element: " + getFirstElement(stringArray));
        System.out.println("Max element: " + findMax(stringArray));
        
        System.out.println("\nDouble array:");
        printArray(doubleArray);
        System.out.println("First element: " + getFirstElement(doubleArray));
        System.out.println("Max element: " + findMax(doubleArray));
        
        // Test multiple type parameters
        printPair("Hello", 42);
        printPair(3.14, true);
    }
}
```

## Bounded Type Parameters

### Upper Bounded Types
```java
public class BoundedTypes {
    
    // Method that works with any Number type
    public static <T extends Number> double sum(T[] numbers) {
        double sum = 0.0;
        for (T number : numbers) {
            sum += number.doubleValue();
        }
        return sum;
    }
    
    // Method that works with Comparable types
    public static <T extends Comparable<T>> T findMin(T[] array) {
        if (array.length == 0) {
            return null;
        }
        
        T min = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i].compareTo(min) < 0) {
                min = array[i];
            }
        }
        return min;
    }
    
    // Multiple bounds
    public static <T extends Number & Comparable<T>> T findMaxNumber(T[] array) {
        if (array.length == 0) {
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
    
    public static void main(String[] args) {
        Integer[] ints = {1, 5, 3, 9, 2};
        Double[] doubles = {1.1, 5.5, 3.3, 9.9, 2.2};
        
        System.out.println("Sum of integers: " + sum(ints));
        System.out.println("Sum of doubles: " + sum(doubles));
        
        System.out.println("Min integer: " + findMin(ints));
        System.out.println("Min double: " + findMin(doubles));
        
        System.out.println("Max number (integer): " + findMaxNumber(ints));
        System.out.println("Max number (double): " + findMaxNumber(doubles));
    }
}
```

### Lower Bounded Types
```java
public class LowerBoundedTypes {
    
    // Method that accepts Integer or any supertype of Integer
    public static void addNumbers(List<? super Integer> list) {
        list.add(1);
        list.add(2);
        list.add(3);
    }
    
    // Method that works with Number or any supertype
    public static void addNumbers(List<? super Number> list) {
        list.add(1);
        list.add(2.5);
        list.add(3L);
    }
    
    public static void main(String[] args) {
        List<Object> objectList = new ArrayList<>();
        List<Number> numberList = new ArrayList<>();
        List<Integer> integerList = new ArrayList<>();
        
        // These work because Object and Number are supertypes of Integer
        addNumbers(objectList);
        addNumbers(numberList);
        addNumbers(integerList);
        
        System.out.println("Object list: " + objectList);
        System.out.println("Number list: " + numberList);
        System.out.println("Integer list: " + integerList);
    }
}
```

## Wildcards

### Unbounded Wildcards
```java
public class Wildcards {
    
    // Method that works with any type
    public static void printList(List<?> list) {
        for (Object item : list) {
            System.out.print(item + " ");
        }
        System.out.println();
    }
    
    // Method that works with any type but can't modify the list
    public static void processList(List<?> list) {
        // Can read from the list
        for (Object item : list) {
            System.out.println("Processing: " + item);
        }
        
        // Cannot add to the list (compile-time error):
        // list.add("new item"); // This would cause an error
    }
    
    public static void main(String[] args) {
        List<Integer> intList = Arrays.asList(1, 2, 3, 4, 5);
        List<String> stringList = Arrays.asList("apple", "banana", "cherry");
        List<Double> doubleList = Arrays.asList(1.1, 2.2, 3.3);
        
        System.out.println("Integer list:");
        printList(intList);
        
        System.out.println("String list:");
        printList(stringList);
        
        System.out.println("Double list:");
        printList(doubleList);
        
        // Process lists
        processList(intList);
        processList(stringList);
    }
}
```

### Upper Bounded Wildcards
```java
public class UpperBoundedWildcards {
    
    // Method that works with any Number type
    public static double sumOfList(List<? extends Number> list) {
        double sum = 0.0;
        for (Number number : list) {
            sum += number.doubleValue();
        }
        return sum;
    }
    
    // Method that works with any Comparable type
    public static <T extends Comparable<T>> T findMax(List<? extends T> list) {
        if (list.isEmpty()) {
            return null;
        }
        
        T max = list.get(0);
        for (T item : list) {
            if (item.compareTo(max) > 0) {
                max = item;
            }
        }
        return max;
    }
    
    public static void main(String[] args) {
        List<Integer> intList = Arrays.asList(1, 5, 3, 9, 2);
        List<Double> doubleList = Arrays.asList(1.1, 5.5, 3.3, 9.9, 2.2);
        List<String> stringList = Arrays.asList("apple", "banana", "cherry");
        
        System.out.println("Sum of integers: " + sumOfList(intList));
        System.out.println("Sum of doubles: " + sumOfList(doubleList));
        
        System.out.println("Max integer: " + findMax(intList));
        System.out.println("Max double: " + findMax(doubleList));
        System.out.println("Max string: " + findMax(stringList));
    }
}
```

### Lower Bounded Wildcards
```java
public class LowerBoundedWildcards {
    
    // Method that can add Integer or any subtype to the list
    public static void addIntegers(List<? super Integer> list) {
        list.add(1);
        list.add(2);
        list.add(3);
        
        // Can also add subtypes of Integer
        list.add(4); // Integer
        // list.add(5L); // Long - compile error
    }
    
    // Method that can add Number or any subtype to the list
    public static void addNumbers(List<? super Number> list) {
        list.add(1);      // Integer
        list.add(2.5);    // Double
        list.add(3L);     // Long
        list.add(4.0f);   // Float
    }
    
    public static void main(String[] args) {
        List<Object> objectList = new ArrayList<>();
        List<Number> numberList = new ArrayList<>();
        List<Integer> integerList = new ArrayList<>();
        
        // Add integers to different list types
        addIntegers(objectList);
        addIntegers(numberList);
        addIntegers(integerList);
        
        System.out.println("Object list: " + objectList);
        System.out.println("Number list: " + numberList);
        System.out.println("Integer list: " + integerList);
        
        // Add numbers to different list types
        List<Object> objList = new ArrayList<>();
        List<Number> numList = new ArrayList<>();
        
        addNumbers(objList);
        addNumbers(numList);
        
        System.out.println("Object list with numbers: " + objList);
        System.out.println("Number list: " + numList);
    }
}
```

## Generic Interfaces

### Simple Generic Interface
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

// Usage
public class ContainerDemo {
    public static void main(String[] args) {
        Container<String> stringContainer = new GenericContainer<>();
        stringContainer.add("Hello");
        stringContainer.add("World");
        stringContainer.add("Generics");
        
        System.out.println("String container: " + stringContainer);
        System.out.println("Size: " + stringContainer.size());
        System.out.println("First item: " + stringContainer.get(0));
        
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
public interface ComparableContainer<T extends Comparable<T>> {
    void add(T item);
    T get(int index);
    T getMax();
    T getMin();
    void sort();
    int size();
}

public class ComparableGenericContainer<T extends Comparable<T>> implements ComparableContainer<T> {
    private List<T> items;
    
    public ComparableGenericContainer() {
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
    public T getMax() {
        if (items.isEmpty()) {
            return null;
        }
        
        T max = items.get(0);
        for (T item : items) {
            if (item.compareTo(max) > 0) {
                max = item;
            }
        }
        return max;
    }
    
    @Override
    public T getMin() {
        if (items.isEmpty()) {
            return null;
        }
        
        T min = items.get(0);
        for (T item : items) {
            if (item.compareTo(min) < 0) {
                min = item;
            }
        }
        return min;
    }
    
    @Override
    public void sort() {
        Collections.sort(items);
    }
    
    @Override
    public int size() {
        return items.size();
    }
    
    @Override
    public String toString() {
        return items.toString();
    }
}

// Usage
public class ComparableContainerDemo {
    public static void main(String[] args) {
        ComparableContainer<Integer> intContainer = new ComparableGenericContainer<>();
        intContainer.add(5);
        intContainer.add(2);
        intContainer.add(8);
        intContainer.add(1);
        intContainer.add(9);
        
        System.out.println("Original: " + intContainer);
        System.out.println("Max: " + intContainer.getMax());
        System.out.println("Min: " + intContainer.getMin());
        
        intContainer.sort();
        System.out.println("Sorted: " + intContainer);
        
        ComparableContainer<String> stringContainer = new ComparableGenericContainer<>();
        stringContainer.add("banana");
        stringContainer.add("apple");
        stringContainer.add("cherry");
        stringContainer.add("date");
        
        System.out.println("Original: " + stringContainer);
        System.out.println("Max: " + stringContainer.getMax());
        System.out.println("Min: " + stringContainer.getMin());
        
        stringContainer.sort();
        System.out.println("Sorted: " + stringContainer);
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

// Usage
public class StackDemo {
    public static void main(String[] args) {
        GenericStack<Integer> intStack = new GenericStack<>();
        intStack.push(1);
        intStack.push(2);
        intStack.push(3);
        
        System.out.println("Integer stack: " + intStack);
        System.out.println("Peek: " + intStack.peek());
        System.out.println("Pop: " + intStack.pop());
        System.out.println("After pop: " + intStack);
        
        GenericStack<String> stringStack = new GenericStack<>();
        stringStack.push("first");
        stringStack.push("second");
        stringStack.push("third");
        
        System.out.println("String stack: " + stringStack);
        System.out.println("Peek: " + stringStack.peek());
        System.out.println("Pop: " + stringStack.pop());
        System.out.println("After pop: " + stringStack);
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

// Usage
public class QueueDemo {
    public static void main(String[] args) {
        GenericQueue<String> stringQueue = new GenericQueue<>();
        stringQueue.enqueue("first");
        stringQueue.enqueue("second");
        stringQueue.enqueue("third");
        
        System.out.println("String queue: " + stringQueue);
        System.out.println("Peek: " + stringQueue.peek());
        System.out.println("Dequeue: " + stringQueue.dequeue());
        System.out.println("After dequeue: " + stringQueue);
        
        GenericQueue<Integer> intQueue = new GenericQueue<>();
        intQueue.enqueue(10);
        intQueue.enqueue(20);
        intQueue.enqueue(30);
        
        System.out.println("Integer queue: " + intQueue);
        System.out.println("Peek: " + intQueue.peek());
        System.out.println("Dequeue: " + intQueue.dequeue());
        System.out.println("After dequeue: " + intQueue);
    }
}
```

## Type Erasure and Runtime Behavior

### Understanding Type Erasure
```java
public class TypeErasureDemo {
    
    public static void main(String[] args) {
        // At compile time, these are different types
        List<String> stringList = new ArrayList<>();
        List<Integer> intList = new ArrayList<>();
        
        // At runtime, both are just List (type erasure)
        System.out.println("String list class: " + stringList.getClass());
        System.out.println("Integer list class: " + intList.getClass());
        System.out.println("Are they the same class? " + 
            (stringList.getClass() == intList.getClass()));
        
        // This demonstrates type erasure
        List rawList = new ArrayList();
        rawList.add("String");
        rawList.add(42);
        rawList.add(3.14);
        
        System.out.println("Raw list: " + rawList);
        
        // Generic method with type erasure
        printTypeInfo(stringList);
        printTypeInfo(intList);
    }
    
    // This method shows that type information is lost at runtime
    public static void printTypeInfo(List<?> list) {
        System.out.println("List type: " + list.getClass().getSimpleName());
        System.out.println("Generic type: " + 
            list.getClass().getGenericSuperclass());
    }
}
```

### Working with Type Erasure
```java
public class TypeErasureWorkarounds {
    
    // Using Class<T> to preserve type information
    public static <T> T createInstance(Class<T> clazz) throws Exception {
        return clazz.getDeclaredConstructor().newInstance();
    }
    
    // Using reflection to get generic type information
    public static void printGenericType(Class<?> clazz) {
        Type genericSuperclass = clazz.getGenericSuperclass();
        if (genericSuperclass instanceof ParameterizedType) {
            ParameterizedType paramType = (ParameterizedType) genericSuperclass;
            Type[] typeArguments = paramType.getActualTypeArguments();
            System.out.println("Generic type arguments:");
            for (Type typeArg : typeArguments) {
                System.out.println("  " + typeArg.getTypeName());
            }
        }
    }
    
    public static void main(String[] args) {
        try {
            // Create instances using Class<T>
            String str = createInstance(String.class);
            System.out.println("Created string: " + str);
            
            Integer num = createInstance(Integer.class);
            System.out.println("Created integer: " + num);
            
        } catch (Exception e) {
            System.err.println("Error creating instance: " + e.getMessage());
        }
        
        // Print generic type information
        printGenericType(ArrayList.class);
    }
}
```

## Real-World Generic Examples

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

// Usage
public class CacheDemo {
    public static void main(String[] args) {
        // String-Integer cache
        GenericCache<String, Integer> nameAgeCache = new GenericCache<>(3);
        nameAgeCache.put("John", 30);
        nameAgeCache.put("Jane", 25);
        nameAgeCache.put("Bob", 35);
        
        System.out.println("Cache: " + nameAgeCache);
        System.out.println("John's age: " + nameAgeCache.get("John"));
        
        // Adding more items will evict the oldest
        nameAgeCache.put("Alice", 28);
        System.out.println("Cache after adding Alice: " + nameAgeCache);
        
        // Integer-String cache
        GenericCache<Integer, String> idNameCache = new GenericCache<>(2);
        idNameCache.put(1, "Product A");
        idNameCache.put(2, "Product B");
        
        System.out.println("ID cache: " + idNameCache);
        System.out.println("Product 1: " + idNameCache.get(1));
    }
}
```

### Generic Builder Pattern
```java
public class GenericBuilder<T> {
    private T instance;
    private Class<T> clazz;
    
    public GenericBuilder(Class<T> clazz) {
        this.clazz = clazz;
        try {
            this.instance = clazz.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException("Failed to create instance", e);
        }
    }
    
    public GenericBuilder<T> with(String fieldName, Object value) {
        try {
            Field field = clazz.getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(instance, value);
        } catch (Exception e) {
            throw new RuntimeException("Failed to set field: " + fieldName, e);
        }
        return this;
    }
    
    public T build() {
        return instance;
    }
}

// Example class to use with builder
public class Person {
    private String name;
    private int age;
    private String email;
    
    public Person() {}
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    @Override
    public String toString() {
        return String.format("Person{name='%s', age=%d, email='%s'}", name, age, email);
    }
}

// Usage
public class BuilderDemo {
    public static void main(String[] args) {
        Person person = new GenericBuilder<>(Person.class)
            .with("name", "John Doe")
            .with("age", 30)
            .with("email", "john@example.com")
            .build();
        
        System.out.println("Built person: " + person);
    }
}
```

## Exercises

### Exercise 1: Generic Data Structures
Create generic implementations of:
- Binary Tree
- Linked List
- Priority Queue
- Hash Table

### Exercise 2: Generic Algorithms
Implement generic versions of:
- Sorting algorithms (bubble, merge, quick)
- Search algorithms (linear, binary)
- Filter and map operations
- Reduce/fold operations

### Exercise 3: Generic Collections
Build a generic collection framework with:
- Custom List implementation
- Custom Set implementation
- Custom Map implementation
- Iterator and Iterable support

### Exercise 4: Advanced Generic Patterns
Implement:
- Generic factory pattern
- Generic singleton pattern
- Generic observer pattern
- Generic command pattern

## Practice Files
- `GenericsDemo.java` - Basic generics examples
- `BoundedTypesDemo.java` - Bounded type parameters
- `WildcardsDemo.java` - Wildcard examples
- `GenericInterfacesDemo.java` - Generic interfaces
- `AdvancedPatternsDemo.java` - Advanced generic patterns
- `TypeErasureDemo.java` - Type erasure examples
- `RealWorldExamples.java` - Real-world generic applications

## Key Takeaways
1. Generics provide compile-time type safety
2. Use bounded types to restrict type parameters
3. Wildcards provide flexibility when working with collections
4. Type erasure means generic information is lost at runtime
5. Generics eliminate the need for casting in most cases
6. Generic methods can be more flexible than generic classes
7. Use appropriate wildcards for read-only or write-only operations
8. Consider type erasure when designing generic APIs

## Next Steps
After completing this lesson, move on to:
- Streams API and functional programming
- Lambda expressions and method references
- Optional and other functional interfaces
- Advanced Java features

## Additional Resources
- [Java Generics Tutorial](https://docs.oracle.com/javase/tutorial/java/generics/)
- [Generics in Java](https://docs.oracle.com/javase/tutorial/extra/generics/)
- [Type Erasure](https://docs.oracle.com/javase/tutorial/java/generics/erasure.html)