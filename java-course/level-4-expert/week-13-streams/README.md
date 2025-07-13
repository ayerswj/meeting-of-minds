# Lesson 11: Streams API and Functional Programming

## Learning Objectives
- Master the Streams API for functional programming in Java
- Understand lambda expressions and functional interfaces
- Learn stream operations: intermediate and terminal
- Practice functional programming patterns and techniques
- Apply streams for data processing and transformation
- Understand parallel streams and performance considerations

## Introduction to Functional Programming

Functional programming is a programming paradigm that treats computation as the evaluation of mathematical functions and avoids changing state and mutable data. Java 8 introduced functional programming features through the Streams API and lambda expressions.

### Benefits of Functional Programming
- **Immutability**: Data doesn't change, reducing bugs
- **Declarative**: Focus on what to do, not how to do it
- **Composability**: Functions can be combined and chained
- **Parallelization**: Easy to parallelize operations
- **Readability**: More concise and expressive code

## Lambda Expressions

### Basic Lambda Syntax
```java
public class LambdaBasics {
    
    public static void main(String[] args) {
        // Traditional anonymous class
        Runnable traditionalRunnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello from traditional anonymous class");
            }
        };
        
        // Lambda expression equivalent
        Runnable lambdaRunnable = () -> System.out.println("Hello from lambda");
        
        // Execute both
        traditionalRunnable.run();
        lambdaRunnable.run();
        
        // Lambda with parameters
        BinaryOperator<Integer> add = (a, b) -> a + b;
        System.out.println("Sum: " + add.apply(5, 3));
        
        // Lambda with multiple statements
        BinaryOperator<Integer> multiply = (a, b) -> {
            int result = a * b;
            System.out.println("Multiplying " + a + " and " + b);
            return result;
        };
        System.out.println("Product: " + multiply.apply(4, 6));
        
        // Lambda with type inference
        Comparator<String> lengthComparator = (s1, s2) -> s1.length() - s2.length();
        List<String> words = Arrays.asList("cat", "elephant", "dog", "ant");
        words.sort(lengthComparator);
        System.out.println("Sorted by length: " + words);
    }
}
```

### Functional Interfaces
```java
public class FunctionalInterfacesDemo {
    
    // Custom functional interface
    @FunctionalInterface
    interface StringProcessor {
        String process(String input);
    }
    
    @FunctionalInterface
    interface NumberValidator {
        boolean isValid(int number);
    }
    
    @FunctionalInterface
    interface Calculator<T> {
        T calculate(T a, T b);
    }
    
    public static void main(String[] args) {
        // String processing
        StringProcessor upperCase = str -> str.toUpperCase();
        StringProcessor reverse = str -> new StringBuilder(str).reverse().toString();
        StringProcessor addPrefix = str -> "Processed: " + str;
        
        String text = "hello world";
        System.out.println("Original: " + text);
        System.out.println("Uppercase: " + upperCase.process(text));
        System.out.println("Reversed: " + reverse.process(text));
        System.out.println("With prefix: " + addPrefix.process(text));
        
        // Number validation
        NumberValidator isEven = n -> n % 2 == 0;
        NumberValidator isPositive = n -> n > 0;
        NumberValidator isPrime = n -> {
            if (n < 2) return false;
            for (int i = 2; i <= Math.sqrt(n); i++) {
                if (n % i == 0) return false;
            }
            return true;
        };
        
        System.out.println("Is 10 even? " + isEven.isValid(10));
        System.out.println("Is -5 positive? " + isPositive.isValid(-5));
        System.out.println("Is 17 prime? " + isPrime.isValid(17));
        
        // Generic calculator
        Calculator<Integer> intAdder = (a, b) -> a + b;
        Calculator<String> stringConcatenator = (a, b) -> a + " " + b;
        Calculator<Double> doubleMultiplier = (a, b) -> a * b;
        
        System.out.println("Integer sum: " + intAdder.calculate(5, 3));
        System.out.println("String concatenation: " + stringConcatenator.calculate("Hello", "World"));
        System.out.println("Double product: " + doubleMultiplier.calculate(2.5, 3.0));
    }
}
```

## Streams API Fundamentals

### Creating Streams
```java
public class StreamCreationDemo {
    
    public static void main(String[] args) {
        // From collections
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "Diana");
        Stream<String> nameStream = names.stream();
        
        // From arrays
        String[] words = {"hello", "world", "java", "streams"};
        Stream<String> wordStream = Arrays.stream(words);
        
        // From individual elements
        Stream<String> singleStream = Stream.of("single", "element");
        
        // From builder
        Stream<String> builderStream = Stream.<String>builder()
            .add("first")
            .add("second")
            .add("third")
            .build();
        
        // Infinite streams
        Stream<Integer> infiniteNumbers = Stream.iterate(0, n -> n + 1);
        Stream<Double> infiniteRandom = Stream.generate(Math::random);
        
        // Range streams
        IntStream rangeStream = IntStream.range(1, 10); // 1 to 9
        IntStream rangeClosedStream = IntStream.rangeClosed(1, 10); // 1 to 10
        
        // Empty stream
        Stream<String> emptyStream = Stream.empty();
        
        // Demonstrate usage
        System.out.println("Names stream:");
        names.stream().forEach(System.out::println);
        
        System.out.println("\nFirst 5 numbers from infinite stream:");
        infiniteNumbers.limit(5).forEach(System.out::println);
        
        System.out.println("\nRange stream:");
        rangeStream.forEach(System.out::println);
    }
}
```

### Basic Stream Operations
```java
public class BasicStreamOperations {
    
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        
        // forEach - terminal operation
        System.out.println("All numbers:");
        numbers.stream().forEach(n -> System.out.print(n + " "));
        System.out.println();
        
        // filter - intermediate operation
        System.out.println("Even numbers:");
        numbers.stream()
            .filter(n -> n % 2 == 0)
            .forEach(n -> System.out.print(n + " "));
        System.out.println();
        
        // map - intermediate operation
        System.out.println("Squared numbers:");
        numbers.stream()
            .map(n -> n * n)
            .forEach(n -> System.out.print(n + " "));
        System.out.println();
        
        // collect - terminal operation
        List<Integer> evenSquares = numbers.stream()
            .filter(n -> n % 2 == 0)
            .map(n -> n * n)
            .collect(Collectors.toList());
        System.out.println("Even squares collected: " + evenSquares);
        
        // reduce - terminal operation
        int sum = numbers.stream()
            .reduce(0, (a, b) -> a + b);
        System.out.println("Sum of all numbers: " + sum);
        
        // findFirst - terminal operation
        Optional<Integer> firstEven = numbers.stream()
            .filter(n -> n % 2 == 0)
            .findFirst();
        System.out.println("First even number: " + firstEven.orElse(-1));
        
        // anyMatch - terminal operation
        boolean hasEven = numbers.stream()
            .anyMatch(n -> n % 2 == 0);
        System.out.println("Has even numbers: " + hasEven);
        
        // allMatch - terminal operation
        boolean allPositive = numbers.stream()
            .allMatch(n -> n > 0);
        System.out.println("All numbers positive: " + allPositive);
    }
}
```

## Intermediate Operations

### Filtering Operations
```java
public class FilteringOperations {
    
    public static void main(String[] args) {
        List<String> words = Arrays.asList("apple", "banana", "cherry", "date", 
                                         "elderberry", "fig", "grape", "honeydew");
        
        // filter - keep elements that match predicate
        System.out.println("Words starting with 'a':");
        words.stream()
            .filter(word -> word.startsWith("a"))
            .forEach(System.out::println);
        
        // distinct - remove duplicates
        List<String> duplicates = Arrays.asList("apple", "banana", "apple", "cherry", "banana");
        System.out.println("\nUnique words:");
        duplicates.stream()
            .distinct()
            .forEach(System.out::println);
        
        // limit - take first n elements
        System.out.println("\nFirst 3 words:");
        words.stream()
            .limit(3)
            .forEach(System.out::println);
        
        // skip - skip first n elements
        System.out.println("\nWords after skipping first 2:");
        words.stream()
            .skip(2)
            .forEach(System.out::println);
        
        // takeWhile - take elements while predicate is true (Java 9+)
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        System.out.println("\nNumbers while less than 5:");
        numbers.stream()
            .takeWhile(n -> n < 5)
            .forEach(System.out::println);
        
        // dropWhile - drop elements while predicate is true (Java 9+)
        System.out.println("\nNumbers after dropping while less than 5:");
        numbers.stream()
            .dropWhile(n -> n < 5)
            .forEach(System.out::println);
    }
}
```

### Mapping Operations
```java
public class MappingOperations {
    
    public static void main(String[] args) {
        List<String> names = Arrays.asList("alice", "bob", "charlie", "diana");
        
        // map - transform each element
        System.out.println("Capitalized names:");
        names.stream()
            .map(String::toUpperCase)
            .forEach(System.out::println);
        
        // mapToInt - transform to primitive int stream
        System.out.println("\nName lengths:");
        names.stream()
            .mapToInt(String::length)
            .forEach(System.out::println);
        
        // mapToDouble - transform to primitive double stream
        System.out.println("\nName lengths as doubles:");
        names.stream()
            .mapToDouble(String::length)
            .forEach(System.out::println);
        
        // flatMap - flatten nested structures
        List<List<String>> nestedLists = Arrays.asList(
            Arrays.asList("a", "b", "c"),
            Arrays.asList("d", "e"),
            Arrays.asList("f", "g", "h", "i")
        );
        
        System.out.println("\nFlattened list:");
        nestedLists.stream()
            .flatMap(List::stream)
            .forEach(System.out::println);
        
        // Complex mapping example
        List<Person> people = Arrays.asList(
            new Person("Alice", 25),
            new Person("Bob", 30),
            new Person("Charlie", 35),
            new Person("Diana", 28)
        );
        
        System.out.println("\nPerson names:");
        people.stream()
            .map(Person::getName)
            .forEach(System.out::println);
        
        System.out.println("\nPerson ages:");
        people.stream()
            .mapToInt(Person::getAge)
            .forEach(System.out::println);
        
        // Mapping with custom transformations
        System.out.println("\nPerson descriptions:");
        people.stream()
            .map(person -> person.getName() + " is " + person.getAge() + " years old")
            .forEach(System.out::println);
    }
    
    static class Person {
        private String name;
        private int age;
        
        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }
        
        public String getName() { return name; }
        public int getAge() { return age; }
    }
}
```

### Sorting Operations
```java
public class SortingOperations {
    
    public static void main(String[] args) {
        List<String> words = Arrays.asList("banana", "apple", "cherry", "date");
        
        // Natural sorting
        System.out.println("Natural sort:");
        words.stream()
            .sorted()
            .forEach(System.out::println);
        
        // Custom comparator
        System.out.println("\nSort by length:");
        words.stream()
            .sorted((s1, s2) -> s1.length() - s2.length())
            .forEach(System.out::println);
        
        // Using Comparator methods
        System.out.println("\nSort by length (using Comparator):");
        words.stream()
            .sorted(Comparator.comparing(String::length))
            .forEach(System.out::println);
        
        // Reverse sorting
        System.out.println("\nReverse natural sort:");
        words.stream()
            .sorted(Comparator.reverseOrder())
            .forEach(System.out::println);
        
        // Multiple criteria sorting
        List<Student> students = Arrays.asList(
            new Student("Alice", 85, "Math"),
            new Student("Bob", 92, "Science"),
            new Student("Charlie", 85, "English"),
            new Student("Diana", 95, "Math")
        );
        
        System.out.println("\nStudents sorted by grade (descending), then by name:");
        students.stream()
            .sorted(Comparator.comparing(Student::getGrade).reversed()
                    .thenComparing(Student::getName))
            .forEach(System.out::println);
        
        // Sorting with null handling
        List<String> wordsWithNull = Arrays.asList("banana", null, "apple", "cherry");
        System.out.println("\nSort with nulls last:");
        wordsWithNull.stream()
            .sorted(Comparator.nullsLast(Comparator.naturalOrder()))
            .forEach(System.out::println);
    }
    
    static class Student {
        private String name;
        private int grade;
        private String subject;
        
        public Student(String name, int grade, String subject) {
            this.name = name;
            this.grade = grade;
            this.subject = subject;
        }
        
        public String getName() { return name; }
        public int getGrade() { return grade; }
        public String getSubject() { return subject; }
        
        @Override
        public String toString() {
            return name + " (" + grade + ") - " + subject;
        }
    }
}
```

## Terminal Operations

### Collection Operations
```java
public class CollectionOperations {
    
    public static void main(String[] args) {
        List<String> words = Arrays.asList("apple", "banana", "cherry", "date", "elderberry");
        
        // collect to List
        List<String> upperCaseWords = words.stream()
            .map(String::toUpperCase)
            .collect(Collectors.toList());
        System.out.println("Uppercase words: " + upperCaseWords);
        
        // collect to Set
        Set<String> uniqueWords = words.stream()
            .collect(Collectors.toSet());
        System.out.println("Unique words: " + uniqueWords);
        
        // collect to Map
        Map<String, Integer> wordLengthMap = words.stream()
            .collect(Collectors.toMap(
                word -> word,
                String::length
            ));
        System.out.println("Word length map: " + wordLengthMap);
        
        // collect to specific collection types
        LinkedList<String> linkedList = words.stream()
            .collect(Collectors.toCollection(LinkedList::new));
        System.out.println("LinkedList: " + linkedList);
        
        // collect with joining
        String joinedWords = words.stream()
            .collect(Collectors.joining(", "));
        System.out.println("Joined words: " + joinedWords);
        
        // collect with prefix and suffix
        String formattedWords = words.stream()
            .collect(Collectors.joining(", ", "[", "]"));
        System.out.println("Formatted words: " + formattedWords);
        
        // collect with grouping
        Map<Integer, List<String>> wordsByLength = words.stream()
            .collect(Collectors.groupingBy(String::length));
        System.out.println("Words grouped by length: " + wordsByLength);
        
        // collect with partitioning
        Map<Boolean, List<String>> partitionedWords = words.stream()
            .collect(Collectors.partitioningBy(word -> word.length() > 5));
        System.out.println("Words partitioned by length > 5: " + partitionedWords);
    }
}
```

### Reduction Operations
```java
public class ReductionOperations {
    
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        
        // reduce with identity and accumulator
        int sum = numbers.stream()
            .reduce(0, (a, b) -> a + b);
        System.out.println("Sum: " + sum);
        
        // reduce with accumulator only (returns Optional)
        Optional<Integer> product = numbers.stream()
            .reduce((a, b) -> a * b);
        System.out.println("Product: " + product.orElse(0));
        
        // reduce with identity, accumulator, and combiner
        int sumWithCombiner = numbers.stream()
            .reduce(0, (a, b) -> a + b, (a, b) -> a + b);
        System.out.println("Sum with combiner: " + sumWithCombiner);
        
        // max and min
        Optional<Integer> max = numbers.stream()
            .max(Integer::compareTo);
        Optional<Integer> min = numbers.stream()
            .min(Integer::compareTo);
        System.out.println("Max: " + max.orElse(0));
        System.out.println("Min: " + min.orElse(0));
        
        // count
        long count = numbers.stream()
            .filter(n -> n % 2 == 0)
            .count();
        System.out.println("Count of even numbers: " + count);
        
        // findFirst and findAny
        Optional<Integer> firstEven = numbers.stream()
            .filter(n -> n % 2 == 0)
            .findFirst();
        Optional<Integer> anyEven = numbers.stream()
            .filter(n -> n % 2 == 0)
            .findAny();
        System.out.println("First even: " + firstEven.orElse(0));
        System.out.println("Any even: " + anyEven.orElse(0));
        
        // allMatch, anyMatch, noneMatch
        boolean allPositive = numbers.stream()
            .allMatch(n -> n > 0);
        boolean anyNegative = numbers.stream()
            .anyMatch(n -> n < 0);
        boolean noneZero = numbers.stream()
            .noneMatch(n -> n == 0);
        System.out.println("All positive: " + allPositive);
        System.out.println("Any negative: " + anyNegative);
        System.out.println("None zero: " + noneZero);
    }
}
```

## Advanced Stream Operations

### Parallel Streams
```java
public class ParallelStreamsDemo {
    
    public static void main(String[] args) {
        List<Integer> numbers = IntStream.range(1, 1000000)
            .boxed()
            .collect(Collectors.toList());
        
        // Sequential processing
        long startTime = System.currentTimeMillis();
        long sequentialSum = numbers.stream()
            .filter(n -> n % 2 == 0)
            .mapToLong(Long::valueOf)
            .sum();
        long sequentialTime = System.currentTimeMillis() - startTime;
        
        // Parallel processing
        startTime = System.currentTimeMillis();
        long parallelSum = numbers.parallelStream()
            .filter(n -> n % 2 == 0)
            .mapToLong(Long::valueOf)
            .sum();
        long parallelTime = System.currentTimeMillis() - startTime;
        
        System.out.println("Sequential sum: " + sequentialSum + " (time: " + sequentialTime + "ms)");
        System.out.println("Parallel sum: " + parallelSum + " (time: " + parallelTime + "ms)");
        
        // Parallel stream considerations
        List<String> words = Arrays.asList("apple", "banana", "cherry", "date", "elderberry");
        
        // Order may not be preserved in parallel streams
        System.out.println("\nSequential order:");
        words.stream().forEach(System.out::println);
        
        System.out.println("\nParallel order (may vary):");
        words.parallelStream().forEach(System.out::println);
        
        // Using forEachOrdered to maintain order
        System.out.println("\nParallel with ordered forEach:");
        words.parallelStream().forEachOrdered(System.out::println);
        
        // When to use parallel streams
        System.out.println("\nParallel stream best practices:");
        System.out.println("- Use for large datasets");
        System.out.println("- Avoid for small datasets (overhead)");
        System.out.println("- Be careful with stateful operations");
        System.out.println("- Consider thread safety");
    }
}
```

### Custom Collectors
```java
public class CustomCollectorsDemo {
    
    public static void main(String[] args) {
        List<String> words = Arrays.asList("apple", "banana", "cherry", "date", "elderberry");
        
        // Custom collector to find longest word
        Collector<String, ?, Optional<String>> longestWordCollector = 
            Collector.of(
                () -> new String[1], // supplier
                (acc, word) -> { // accumulator
                    if (acc[0] == null || word.length() > acc[0].length()) {
                        acc[0] = word;
                    }
                },
                (acc1, acc2) -> { // combiner
                    if (acc1[0] == null) return acc2;
                    if (acc2[0] == null) return acc1;
                    return acc1[0].length() > acc2[0].length() ? acc1 : acc2;
                },
                acc -> Optional.ofNullable(acc[0]) // finisher
            );
        
        Optional<String> longestWord = words.stream()
            .collect(longestWordCollector);
        System.out.println("Longest word: " + longestWord.orElse("none"));
        
        // Custom collector to collect statistics
        Collector<String, StringStats, StringStats> statsCollector = 
            Collector.of(
                StringStats::new, // supplier
                StringStats::accept, // accumulator
                StringStats::combine, // combiner
                Function.identity() // finisher
            );
        
        StringStats stats = words.stream()
            .collect(statsCollector);
        System.out.println("String statistics: " + stats);
    }
    
    static class StringStats {
        private int count;
        private int totalLength;
        private String shortest;
        private String longest;
        
        public void accept(String word) {
            count++;
            totalLength += word.length();
            if (shortest == null || word.length() < shortest.length()) {
                shortest = word;
            }
            if (longest == null || word.length() > longest.length()) {
                longest = word;
            }
        }
        
        public StringStats combine(StringStats other) {
            StringStats combined = new StringStats();
            combined.count = this.count + other.count;
            combined.totalLength = this.totalLength + other.totalLength;
            combined.shortest = this.shortest == null ? other.shortest :
                (other.shortest == null ? this.shortest :
                 this.shortest.length() <= other.shortest.length() ? this.shortest : other.shortest);
            combined.longest = this.longest == null ? other.longest :
                (other.longest == null ? this.longest :
                 this.longest.length() >= other.longest.length() ? this.longest : other.longest);
            return combined;
        }
        
        @Override
        public String toString() {
            return String.format("Count: %d, Avg Length: %.2f, Shortest: %s, Longest: %s",
                count, count > 0 ? (double) totalLength / count : 0, shortest, longest);
        }
    }
}
```

## Real-World Examples

### Data Processing Pipeline
```java
public class DataProcessingPipeline {
    
    public static void main(String[] args) {
        List<Transaction> transactions = Arrays.asList(
            new Transaction("T1", 100.0, "GROCERIES"),
            new Transaction("T2", 250.0, "ENTERTAINMENT"),
            new Transaction("T3", 75.0, "GROCERIES"),
            new Transaction("T4", 500.0, "TRAVEL"),
            new Transaction("T5", 150.0, "ENTERTAINMENT"),
            new Transaction("T6", 300.0, "TRAVEL"),
            new Transaction("T7", 50.0, "GROCERIES"),
            new Transaction("T8", 200.0, "ENTERTAINMENT")
        );
        
        // Complex data processing pipeline
        Map<String, DoubleSummaryStatistics> categoryStats = transactions.stream()
            .filter(t -> t.getAmount() > 50) // Filter small transactions
            .collect(Collectors.groupingBy(
                Transaction::getCategory,
                Collectors.summarizingDouble(Transaction::getAmount)
            ));
        
        System.out.println("Transaction statistics by category:");
        categoryStats.forEach((category, stats) -> {
            System.out.printf("%s: Count=%d, Total=%.2f, Avg=%.2f, Min=%.2f, Max=%.2f%n",
                category, stats.getCount(), stats.getSum(), stats.getAverage(),
                stats.getMin(), stats.getMax());
        });
        
        // Find top 3 most expensive transactions
        List<Transaction> topTransactions = transactions.stream()
            .sorted(Comparator.comparing(Transaction::getAmount).reversed())
            .limit(3)
            .collect(Collectors.toList());
        
        System.out.println("\nTop 3 most expensive transactions:");
        topTransactions.forEach(t -> 
            System.out.printf("%s: %.2f (%s)%n", t.getId(), t.getAmount(), t.getCategory()));
        
        // Calculate total spending by category
        Map<String, Double> totalByCategory = transactions.stream()
            .collect(Collectors.groupingBy(
                Transaction::getCategory,
                Collectors.summingDouble(Transaction::getAmount)
            ));
        
        System.out.println("\nTotal spending by category:");
        totalByCategory.entrySet().stream()
            .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
            .forEach(entry -> 
                System.out.printf("%s: %.2f%n", entry.getKey(), entry.getValue()));
    }
    
    static class Transaction {
        private String id;
        private double amount;
        private String category;
        
        public Transaction(String id, double amount, String category) {
            this.id = id;
            this.amount = amount;
            this.category = category;
        }
        
        public String getId() { return id; }
        public double getAmount() { return amount; }
        public String getCategory() { return category; }
    }
}
```

### File Processing with Streams
```java
public class FileProcessingWithStreams {
    
    public static void main(String[] args) {
        // Simulate file lines
        List<String> fileLines = Arrays.asList(
            "user1,25,engineer",
            "user2,30,manager",
            "user3,28,developer",
            "user4,35,architect",
            "user5,22,intern",
            "user6,40,manager",
            "user7,29,developer"
        );
        
        // Parse and process user data
        List<User> users = fileLines.stream()
            .map(line -> {
                String[] parts = line.split(",");
                return new User(parts[0], Integer.parseInt(parts[1]), parts[2]);
            })
            .collect(Collectors.toList());
        
        System.out.println("All users:");
        users.forEach(System.out::println);
        
        // Find average age by role
        Map<String, Double> avgAgeByRole = users.stream()
            .collect(Collectors.groupingBy(
                User::getRole,
                Collectors.averagingInt(User::getAge)
            ));
        
        System.out.println("\nAverage age by role:");
        avgAgeByRole.forEach((role, avgAge) -> 
            System.out.printf("%s: %.1f%n", role, avgAge));
        
        // Find users by age range
        Map<String, List<User>> usersByAgeRange = users.stream()
            .collect(Collectors.groupingBy(user -> {
                int age = user.getAge();
                if (age < 25) return "Young";
                else if (age < 35) return "Middle";
                else return "Senior";
            }));
        
        System.out.println("\nUsers by age range:");
        usersByAgeRange.forEach((range, userList) -> {
            System.out.println(range + ":");
            userList.forEach(user -> System.out.println("  " + user));
        });
        
        // Find most common role
        String mostCommonRole = users.stream()
            .collect(Collectors.groupingBy(
                User::getRole,
                Collectors.counting()
            ))
            .entrySet().stream()
            .max(Map.Entry.comparingByValue())
            .map(Map.Entry::getKey)
            .orElse("Unknown");
        
        System.out.println("\nMost common role: " + mostCommonRole);
    }
    
    static class User {
        private String name;
        private int age;
        private String role;
        
        public User(String name, int age, String role) {
            this.name = name;
            this.age = age;
            this.role = role;
        }
        
        public String getName() { return name; }
        public int getAge() { return age; }
        public String getRole() { return role; }
        
        @Override
        public String toString() {
            return name + " (" + age + ", " + role + ")";
        }
    }
}
```

## Exercises

### Exercise 1: Stream Processing Pipeline
Create a stream pipeline that processes a list of products, filters by category, calculates total value, and finds the most expensive item.

### Exercise 2: Custom Collector
Implement a custom collector that calculates the median of a stream of numbers.

### Exercise 3: Parallel Stream Processing
Create a parallel stream application that processes a large dataset and compares performance with sequential processing.

### Exercise 4: Functional Data Transformation
Build a stream-based system that transforms data between different formats (e.g., CSV to JSON).

## Practice Files
- `LambdaBasics.java` - Basic lambda expression examples
- `FunctionalInterfacesDemo.java` - Custom functional interfaces
- `StreamCreationDemo.java` - Different ways to create streams
- `BasicStreamOperations.java` - Fundamental stream operations
- `FilteringOperations.java` - Stream filtering techniques
- `MappingOperations.java` - Stream mapping operations
- `SortingOperations.java` - Stream sorting operations
- `CollectionOperations.java` - Terminal collection operations
- `ReductionOperations.java` - Stream reduction operations
- `ParallelStreamsDemo.java` - Parallel stream processing
- `CustomCollectorsDemo.java` - Custom collector implementations
- `DataProcessingPipeline.java` - Real-world data processing
- `FileProcessingWithStreams.java` - File processing examples

## Key Takeaways
1. Streams provide a functional approach to data processing
2. Lambda expressions enable concise functional programming
3. Stream operations are either intermediate or terminal
4. Parallel streams can improve performance for large datasets
5. Custom collectors enable complex data aggregation
6. Streams promote immutability and declarative programming
7. Functional interfaces are the foundation of lambda expressions
8. Streams can be chained to create complex processing pipelines

## Next Steps
After completing this lesson, move on to:
- Optional class and null safety
- Date and Time API
- CompletableFuture for async programming
- Reactive programming with Java

## Additional Resources
- [Java Streams Tutorial](https://docs.oracle.com/javase/tutorial/collections/streams/)
- [Lambda Expressions](https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html)
- [Functional Interfaces](https://docs.oracle.com/javase/8/docs/api/java/util/function/package-summary.html)
- [Collectors Class](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Collectors.html)