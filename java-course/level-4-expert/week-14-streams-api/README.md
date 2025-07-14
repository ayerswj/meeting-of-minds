# Week 14: Streams API and Collectors

## 🎯 Learning Objectives
- Master the Java Streams API for data processing
- Understand stream operations and their characteristics
- Learn to use collectors effectively
- Implement custom collectors
- Optimize stream performance
- Apply streams in real-world scenarios

## 📚 Theory

### What are Streams?
Streams are a sequence of elements that support various operations to perform computations. They provide a declarative way to process collections of data.

### Key Characteristics
- **Lazy Evaluation**: Operations are only executed when needed
- **Functional**: Operations don't modify the source data
- **Pipelined**: Operations can be chained together
- **Internal Iteration**: The stream handles the iteration internally

### Stream Operations

#### Intermediate Operations (Lazy)
- `filter()` - Filter elements based on a predicate
- `map()` - Transform elements
- `flatMap()` - Transform and flatten
- `distinct()` - Remove duplicates
- `sorted()` - Sort elements
- `limit()` - Limit the number of elements
- `skip()` - Skip elements
- `peek()` - Perform side effects

#### Terminal Operations (Eager)
- `forEach()` - Perform action on each element
- `collect()` - Collect elements into a collection
- `reduce()` - Reduce elements to a single value
- `count()` - Count elements
- `min()`, `max()` - Find minimum/maximum
- `findFirst()`, `findAny()` - Find elements
- `anyMatch()`, `allMatch()`, `noneMatch()` - Check conditions
- `toArray()` - Convert to array

### Basic Stream Operations

#### Creating Streams
```java
// From collections
List<String> list = Arrays.asList("a", "b", "c");
Stream<String> stream1 = list.stream();

// From arrays
String[] array = {"a", "b", "c"};
Stream<String> stream2 = Arrays.stream(array);

// From individual elements
Stream<String> stream3 = Stream.of("a", "b", "c");

// Empty stream
Stream<String> emptyStream = Stream.empty();

// Infinite streams
Stream<Integer> infinite1 = Stream.iterate(0, n -> n + 1);
Stream<Double> infinite2 = Stream.generate(Math::random);

// From ranges
IntStream range1 = IntStream.range(0, 10);        // 0 to 9
IntStream range2 = IntStream.rangeClosed(0, 10);  // 0 to 10
```

#### Filtering and Mapping
```java
List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David", "Eve");

// Filter names longer than 3 characters
List<String> longNames = names.stream()
    .filter(name -> name.length() > 3)
    .collect(Collectors.toList());

// Map to uppercase
List<String> upperNames = names.stream()
    .map(String::toUpperCase)
    .collect(Collectors.toList());

// Map to lengths
List<Integer> nameLengths = names.stream()
    .map(String::length)
    .collect(Collectors.toList());

// Filter and map in one pipeline
List<String> result = names.stream()
    .filter(name -> name.length() > 3)
    .map(String::toUpperCase)
    .collect(Collectors.toList());
```

#### FlatMap
```java
List<List<String>> nestedList = Arrays.asList(
    Arrays.asList("a", "b"),
    Arrays.asList("c", "d"),
    Arrays.asList("e", "f")
);

// Flatten the nested list
List<String> flattened = nestedList.stream()
    .flatMap(List::stream)
    .collect(Collectors.toList());

// Example with words and characters
List<String> words = Arrays.asList("hello", "world");
List<Character> characters = words.stream()
    .flatMap(word -> word.chars().mapToObj(c -> (char) c))
    .collect(Collectors.toList());
```

### Collectors Framework

#### Basic Collectors
```java
List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David");

// To List
List<String> nameList = names.stream()
    .collect(Collectors.toList());

// To Set
Set<String> nameSet = names.stream()
    .collect(Collectors.toSet());

// To specific collection types
ArrayList<String> nameArrayList = names.stream()
    .collect(Collectors.toCollection(ArrayList::new));

TreeSet<String> nameTreeSet = names.stream()
    .collect(Collectors.toCollection(TreeSet::new));
```

#### Joining Strings
```java
List<String> names = Arrays.asList("Alice", "Bob", "Charlie");

// Simple join
String result1 = names.stream()
    .collect(Collectors.joining());

// Join with delimiter
String result2 = names.stream()
    .collect(Collectors.joining(", "));

// Join with prefix and suffix
String result3 = names.stream()
    .collect(Collectors.joining(", ", "[", "]"));
```

#### Grouping and Partitioning
```java
List<Person> people = Arrays.asList(
    new Person("Alice", 25, "Engineering"),
    new Person("Bob", 30, "Sales"),
    new Person("Charlie", 25, "Engineering"),
    new Person("David", 35, "Marketing")
);

// Group by department
Map<String, List<Person>> byDepartment = people.stream()
    .collect(Collectors.groupingBy(Person::getDepartment));

// Group by age range
Map<String, List<Person>> byAgeRange = people.stream()
    .collect(Collectors.groupingBy(person -> {
        if (person.getAge() < 30) return "Young";
        else if (person.getAge() < 40) return "Middle";
        else return "Senior";
    }));

// Partition by age (true/false)
Map<Boolean, List<Person>> byAge30 = people.stream()
    .collect(Collectors.partitioningBy(person -> person.getAge() >= 30));
```

#### Advanced Collectors
```java
List<Person> people = Arrays.asList(
    new Person("Alice", 25, 50000),
    new Person("Bob", 30, 60000),
    new Person("Charlie", 25, 55000),
    new Person("David", 35, 70000)
);

// Count by department
Map<String, Long> countByDept = people.stream()
    .collect(Collectors.groupingBy(
        Person::getDepartment,
        Collectors.counting()
    ));

// Average salary by department
Map<String, Double> avgSalaryByDept = people.stream()
    .collect(Collectors.groupingBy(
        Person::getDepartment,
        Collectors.averagingDouble(Person::getSalary)
    ));

// Max salary by department
Map<String, Optional<Person>> maxSalaryByDept = people.stream()
    .collect(Collectors.groupingBy(
        Person::getDepartment,
        Collectors.maxBy(Comparator.comparing(Person::getSalary))
    ));

// Summary statistics
DoubleSummaryStatistics stats = people.stream()
    .collect(Collectors.summarizingDouble(Person::getSalary));
```

### Custom Collectors

#### Building Custom Collectors
```java
public class CustomCollectors {
    
    // Collector that finds the second largest element
    public static <T extends Comparable<T>> Collector<T, ?, Optional<T>> secondLargest() {
        return Collector.of(
            () -> new ArrayList<T>(),
            (list, element) -> {
                list.add(element);
                list.sort(Collections.reverseOrder());
                if (list.size() > 2) {
                    list.remove(list.size() - 1);
                }
            },
            (list1, list2) -> {
                list1.addAll(list2);
                list1.sort(Collections.reverseOrder());
                while (list1.size() > 2) {
                    list1.remove(list1.size() - 1);
                }
                return list1;
            },
            list -> list.size() >= 2 ? Optional.of(list.get(1)) : Optional.empty()
        );
    }
    
    // Collector that groups elements into batches
    public static <T> Collector<T, ?, List<List<T>>> batching(int batchSize) {
        return Collector.of(
            () -> new ArrayList<List<T>>(),
            (batches, element) -> {
                if (batches.isEmpty() || batches.get(batches.size() - 1).size() >= batchSize) {
                    batches.add(new ArrayList<>());
                }
                batches.get(batches.size() - 1).add(element);
            },
            (batches1, batches2) -> {
                batches1.addAll(batches2);
                return batches1;
            }
        );
    }
    
    // Collector that finds the most frequent element
    public static <T> Collector<T, ?, Optional<T>> mostFrequent() {
        return Collector.of(
            HashMap<T, Long>::new,
            (map, element) -> map.merge(element, 1L, Long::sum),
            (map1, map2) -> {
                map2.forEach((k, v) -> map1.merge(k, v, Long::sum));
                return map1;
            },
            map -> map.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
        );
    }
}
```

### Stream Performance and Optimization

#### Parallel Streams
```java
List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David", "Eve");

// Sequential processing
List<String> result1 = names.stream()
    .filter(name -> name.length() > 3)
    .map(String::toUpperCase)
    .collect(Collectors.toList());

// Parallel processing
List<String> result2 = names.parallelStream()
    .filter(name -> name.length() > 3)
    .map(String::toUpperCase)
    .collect(Collectors.toList());

// Convert to parallel stream
List<String> result3 = names.stream()
    .parallel()
    .filter(name -> name.length() > 3)
    .map(String::toUpperCase)
    .collect(Collectors.toList());
```

#### Performance Considerations
```java
// Good: Use specific collectors when possible
List<String> good = names.stream()
    .collect(Collectors.toList());

// Better: Use specific collection type if needed
ArrayList<String> better = names.stream()
    .collect(Collectors.toCollection(ArrayList::new));

// Avoid: Creating intermediate collections
List<String> avoid = names.stream()
    .filter(name -> name.length() > 3)
    .collect(Collectors.toList())  // Intermediate collection
    .stream()
    .map(String::toUpperCase)
    .collect(Collectors.toList());
```

## 💻 Practice Examples

### Example 1: Data Analysis Pipeline
```java
public class DataAnalysis {
    public static class Transaction {
        private String customerId;
        private double amount;
        private String category;
        private LocalDateTime timestamp;
        
        // Constructor, getters, setters...
    }
    
    public static void analyzeTransactions(List<Transaction> transactions) {
        // Total amount by category
        Map<String, Double> totalByCategory = transactions.stream()
            .collect(Collectors.groupingBy(
                Transaction::getCategory,
                Collectors.summingDouble(Transaction::getAmount)
            ));
        
        // Average transaction amount by customer
        Map<String, Double> avgByCustomer = transactions.stream()
            .collect(Collectors.groupingBy(
                Transaction::getCustomerId,
                Collectors.averagingDouble(Transaction::getAmount)
            ));
        
        // Top 5 customers by total spending
        List<String> topCustomers = transactions.stream()
            .collect(Collectors.groupingBy(
                Transaction::getCustomerId,
                Collectors.summingDouble(Transaction::getAmount)
            ))
            .entrySet().stream()
            .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
            .limit(5)
            .map(Map.Entry::getKey)
            .collect(Collectors.toList());
        
        // Monthly spending trends
        Map<YearMonth, Double> monthlySpending = transactions.stream()
            .collect(Collectors.groupingBy(
                t -> YearMonth.from(t.getTimestamp()),
                Collectors.summingDouble(Transaction::getAmount)
            ));
        
        // Category distribution
        Map<String, Long> categoryDistribution = transactions.stream()
            .collect(Collectors.groupingBy(
                Transaction::getCategory,
                Collectors.counting()
            ));
    }
}
```

### Example 2: Text Processing Pipeline
```java
public class TextProcessor {
    public static Map<String, Long> wordFrequency(String text) {
        return Arrays.stream(text.toLowerCase().split("\\s+"))
            .map(word -> word.replaceAll("[^a-zA-Z]", ""))
            .filter(word -> !word.isEmpty())
            .collect(Collectors.groupingBy(
                word -> word,
                Collectors.counting()
            ));
    }
    
    public static List<String> mostCommonWords(String text, int limit) {
        return wordFrequency(text).entrySet().stream()
            .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
            .limit(limit)
            .map(Map.Entry::getKey)
            .collect(Collectors.toList());
    }
    
    public static Map<Integer, List<String>> wordsByLength(String text) {
        return Arrays.stream(text.toLowerCase().split("\\s+"))
            .map(word -> word.replaceAll("[^a-zA-Z]", ""))
            .filter(word -> !word.isEmpty())
            .collect(Collectors.groupingBy(String::length));
    }
    
    public static String longestWord(String text) {
        return Arrays.stream(text.split("\\s+"))
            .map(word -> word.replaceAll("[^a-zA-Z]", ""))
            .filter(word -> !word.isEmpty())
            .max(Comparator.comparing(String::length))
            .orElse("");
    }
}
```

### Example 3: Inventory Management System
```java
public class InventoryManager {
    public static class Product {
        private String id;
        private String name;
        private double price;
        private int quantity;
        private String category;
        
        // Constructor, getters, setters...
    }
    
    public static class InventoryAnalytics {
        public static Map<String, Double> totalValueByCategory(List<Product> products) {
            return products.stream()
                .collect(Collectors.groupingBy(
                    Product::getCategory,
                    Collectors.summingDouble(p -> p.getPrice() * p.getQuantity())
                ));
        }
        
        public static List<Product> lowStockProducts(List<Product> products, int threshold) {
            return products.stream()
                .filter(p -> p.getQuantity() <= threshold)
                .sorted(Comparator.comparing(Product::getQuantity))
                .collect(Collectors.toList());
        }
        
        public static Map<String, Double> averagePriceByCategory(List<Product> products) {
            return products.stream()
                .collect(Collectors.groupingBy(
                    Product::getCategory,
                    Collectors.averagingDouble(Product::getPrice)
                ));
        }
        
        public static List<String> categoriesByTotalValue(List<Product> products) {
            return totalValueByCategory(products).entrySet().stream()
                .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
        }
        
        public static double totalInventoryValue(List<Product> products) {
            return products.stream()
                .mapToDouble(p -> p.getPrice() * p.getQuantity())
                .sum();
        }
    }
}
```

## 🏋️ Exercises

### Exercise 1: Student Grade Analysis
Create a system to analyze student grades:
- Calculate average grade by subject
- Find top 10 students by GPA
- Group students by grade ranges (A, B, C, D, F)
- Calculate grade distribution
- Find students who improved most between semesters

### Exercise 2: E-commerce Analytics
Build analytics for an e-commerce system:
- Calculate total sales by product category
- Find best-selling products
- Analyze customer purchase patterns
- Calculate customer lifetime value
- Identify seasonal trends

### Exercise 3: Log Analysis System
Create a log analysis system:
- Parse log entries and extract information
- Calculate error rates by time period
- Find most common error types
- Analyze response times
- Generate performance reports

### Exercise 4: Custom Stream Operations
Implement custom stream operations:
- `slidingWindow` - Process elements in sliding windows
- `chunked` - Split stream into chunks
- `interleave` - Interleave elements from multiple streams
- `scan` - Apply function cumulatively to elements

## 🔍 Advanced Topics

### Stream Spliterators
```java
public class CustomSpliterator {
    public static <T> Spliterator<T> batchingSpliterator(
            Spliterator<T> source, int batchSize) {
        return new Spliterators.AbstractSpliterator<T>(
            source.estimateSize(), source.characteristics()) {
            
            @Override
            public boolean tryAdvance(Consumer<? super T> action) {
                return source.tryAdvance(action);
            }
            
            @Override
            public Spliterator<T> trySplit() {
                Spliterator<T> split = source.trySplit();
                if (split == null) return null;
                
                return new BatchingSpliterator<>(split, batchSize);
            }
        };
    }
}
```

### Stream Performance Monitoring
```java
public class StreamProfiler {
    public static <T> Stream<T> profile(Stream<T> stream, String name) {
        long startTime = System.nanoTime();
        long elementCount = 0;
        
        return stream.peek(element -> {
            elementCount++;
            if (elementCount % 1000 == 0) {
                long currentTime = System.nanoTime();
                System.out.printf("%s: Processed %d elements in %d ms%n", 
                    name, elementCount, (currentTime - startTime) / 1_000_000);
            }
        });
    }
}
```

## 📖 Best Practices

1. **Use appropriate collectors for your use case**
2. **Avoid side effects in stream operations**
3. **Consider parallel streams for large datasets**
4. **Use specific collection types when needed**
5. **Prefer method references over lambda expressions**
6. **Chain operations efficiently**
7. **Use `peek()` for debugging, not for side effects**
8. **Consider performance implications of terminal operations**

## 🎯 Next Steps
- Practice with real-world datasets
- Explore reactive streams (RxJava, Project Reactor)
- Learn about stream processing frameworks
- Study functional programming patterns with streams