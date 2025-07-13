# Lesson 12: Optional Class and Null Safety

## Learning Objectives
- Master the Optional class for null safety in Java
- Understand functional programming patterns with Optional
- Learn to avoid null pointer exceptions effectively
- Practice Optional chaining and composition
- Apply Optional in real-world scenarios
- Understand when and how to use Optional appropriately

## Introduction to Optional

The `Optional` class was introduced in Java 8 to provide a container object that may or may not contain a non-null value. It helps eliminate null pointer exceptions and promotes functional programming patterns.

### Why Use Optional?
- **Null Safety**: Eliminates null pointer exceptions
- **Explicit Intent**: Makes it clear when a value might be absent
- **Functional Programming**: Enables method chaining and composition
- **API Design**: Better API design with clear return types
- **Code Readability**: More expressive and readable code

## Creating Optional Objects

### Basic Optional Creation
```java
public class OptionalCreationDemo {
    
    public static void main(String[] args) {
        // Creating Optional with a value
        Optional<String> presentOptional = Optional.of("Hello World");
        System.out.println("Present optional: " + presentOptional);
        
        // Creating empty Optional
        Optional<String> emptyOptional = Optional.empty();
        System.out.println("Empty optional: " + emptyOptional);
        
        // Creating Optional that might be null
        String nullableString = null;
        Optional<String> nullableOptional = Optional.ofNullable(nullableString);
        System.out.println("Nullable optional: " + nullableOptional);
        
        // Creating Optional with non-null value
        String nonNullString = "Hello";
        Optional<String> nonNullOptional = Optional.ofNullable(nonNullString);
        System.out.println("Non-null optional: " + nonNullOptional);
        
        // Demonstrating isPresent and isEmpty
        System.out.println("\nChecking optional states:");
        System.out.println("Present optional is present: " + presentOptional.isPresent());
        System.out.println("Present optional is empty: " + presentOptional.isEmpty());
        System.out.println("Empty optional is present: " + emptyOptional.isPresent());
        System.out.println("Empty optional is empty: " + emptyOptional.isEmpty());
        
        // Demonstrating get() method
        System.out.println("\nGetting values:");
        if (presentOptional.isPresent()) {
            System.out.println("Present value: " + presentOptional.get());
        }
        
        // This would throw NoSuchElementException:
        // System.out.println("Empty value: " + emptyOptional.get());
    }
}
```

### Optional with Different Types
```java
public class OptionalTypesDemo {
    
    public static void main(String[] args) {
        // Optional with primitive wrappers
        Optional<Integer> intOptional = Optional.of(42);
        Optional<Double> doubleOptional = Optional.of(3.14159);
        Optional<Boolean> booleanOptional = Optional.of(true);
        
        // Optional with custom objects
        Optional<Person> personOptional = Optional.of(new Person("Alice", 25));
        
        // Optional with collections
        Optional<List<String>> listOptional = Optional.of(Arrays.asList("a", "b", "c"));
        Optional<Map<String, Integer>> mapOptional = Optional.of(Map.of("key", 100));
        
        // Optional with arrays
        Optional<String[]> arrayOptional = Optional.of(new String[]{"hello", "world"});
        
        // Demonstrating usage
        System.out.println("Integer optional: " + intOptional.get());
        System.out.println("Double optional: " + doubleOptional.get());
        System.out.println("Boolean optional: " + booleanOptional.get());
        System.out.println("Person optional: " + personOptional.get());
        System.out.println("List optional: " + listOptional.get());
        System.out.println("Map optional: " + mapOptional.get());
        System.out.println("Array optional: " + Arrays.toString(arrayOptional.get()));
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
        
        @Override
        public String toString() {
            return name + " (" + age + ")";
        }
    }
}
```

## Accessing Optional Values

### Safe Value Access
```java
public class SafeValueAccessDemo {
    
    public static void main(String[] args) {
        Optional<String> presentOptional = Optional.of("Hello");
        Optional<String> emptyOptional = Optional.empty();
        
        // orElse - provides default value if empty
        String presentValue = presentOptional.orElse("Default");
        String emptyValue = emptyOptional.orElse("Default");
        System.out.println("Present orElse: " + presentValue);
        System.out.println("Empty orElse: " + emptyValue);
        
        // orElseGet - provides default value using supplier
        String presentValueGet = presentOptional.orElseGet(() -> "Default from supplier");
        String emptyValueGet = emptyOptional.orElseGet(() -> "Default from supplier");
        System.out.println("Present orElseGet: " + presentValueGet);
        System.out.println("Empty orElseGet: " + emptyValueGet);
        
        // orElseThrow - throws exception if empty
        try {
            String presentValueThrow = presentOptional.orElseThrow(() -> 
                new RuntimeException("Value is empty"));
            System.out.println("Present orElseThrow: " + presentValueThrow);
            
            String emptyValueThrow = emptyOptional.orElseThrow(() -> 
                new RuntimeException("Value is empty"));
            System.out.println("Empty orElseThrow: " + emptyValueThrow);
        } catch (RuntimeException e) {
            System.out.println("Exception caught: " + e.getMessage());
        }
        
        // ifPresent - executes action if value is present
        System.out.println("\nifPresent examples:");
        presentOptional.ifPresent(value -> System.out.println("Present value: " + value));
        emptyOptional.ifPresent(value -> System.out.println("This won't print"));
        
        // ifPresentOrElse - executes different actions for present/empty
        System.out.println("\nifPresentOrElse examples:");
        presentOptional.ifPresentOrElse(
            value -> System.out.println("Present: " + value),
            () -> System.out.println("Value is empty")
        );
        emptyOptional.ifPresentOrElse(
            value -> System.out.println("Present: " + value),
            () -> System.out.println("Value is empty")
        );
    }
}
```

### Conditional Access
```java
public class ConditionalAccessDemo {
    
    public static void main(String[] args) {
        Optional<String> optional = Optional.of("Hello World");
        
        // filter - only proceed if value matches condition
        Optional<String> filtered = optional.filter(str -> str.length() > 5);
        System.out.println("Filtered (length > 5): " + filtered);
        
        Optional<String> filteredEmpty = optional.filter(str -> str.length() > 20);
        System.out.println("Filtered (length > 20): " + filteredEmpty);
        
        // map - transform value if present
        Optional<Integer> mapped = optional.map(String::length);
        System.out.println("Mapped to length: " + mapped);
        
        Optional<String> upperCase = optional.map(String::toUpperCase);
        System.out.println("Mapped to uppercase: " + upperCase);
        
        // flatMap - transform to another Optional
        Optional<String> flatMapped = optional.flatMap(str -> 
            str.length() > 5 ? Optional.of("Long string") : Optional.empty());
        System.out.println("Flat mapped: " + flatMapped);
        
        // Complex example with chaining
        Optional<String> result = optional
            .filter(str -> str.contains("Hello"))
            .map(String::toUpperCase)
            .flatMap(str -> str.length() > 10 ? Optional.of(str) : Optional.empty());
        System.out.println("Complex chain result: " + result);
    }
}
```

## Optional Chaining and Composition

### Method Chaining
```java
public class OptionalChainingDemo {
    
    public static void main(String[] args) {
        // Simple chaining
        Optional<String> result1 = Optional.of("hello")
            .map(String::toUpperCase)
            .filter(str -> str.length() > 3)
            .map(str -> str + "!");
        System.out.println("Chained result 1: " + result1);
        
        // Chaining with flatMap
        Optional<String> result2 = Optional.of("world")
            .flatMap(str -> Optional.of(str.toUpperCase()))
            .flatMap(str -> str.length() > 3 ? Optional.of(str) : Optional.empty())
            .map(str -> "Hello " + str);
        System.out.println("Chained result 2: " + result2);
        
        // Chaining with orElse
        String result3 = Optional.of("test")
            .map(String::toUpperCase)
            .filter(str -> str.length() > 10)
            .orElse("DEFAULT");
        System.out.println("Chained with orElse: " + result3);
        
        // Complex chaining example
        Optional<String> complexResult = Optional.of("  hello world  ")
            .map(String::trim)
            .filter(str -> !str.isEmpty())
            .map(String::toUpperCase)
            .flatMap(str -> str.contains("WORLD") ? Optional.of(str) : Optional.empty())
            .map(str -> "Processed: " + str);
        System.out.println("Complex chained result: " + complexResult);
    }
}
```

### Optional Composition
```java
public class OptionalCompositionDemo {
    
    public static void main(String[] args) {
        Optional<String> opt1 = Optional.of("Hello");
        Optional<String> opt2 = Optional.of("World");
        Optional<String> opt3 = Optional.empty();
        
        // Combining two Optionals
        Optional<String> combined = opt1.flatMap(str1 -> 
            opt2.map(str2 -> str1 + " " + str2));
        System.out.println("Combined: " + combined);
        
        // Combining with empty Optional
        Optional<String> combinedWithEmpty = opt1.flatMap(str1 -> 
            opt3.map(str3 -> str1 + " " + str3));
        System.out.println("Combined with empty: " + combinedWithEmpty);
        
        // Combining multiple Optionals
        Optional<String> multiCombined = opt1.flatMap(str1 -> 
            opt2.flatMap(str2 -> 
                Optional.of("Goodbye").map(str3 -> str1 + " " + str2 + " " + str3)));
        System.out.println("Multi combined: " + multiCombined);
        
        // Using or to provide alternatives
        Optional<String> alternative = opt3.or(() -> opt1);
        System.out.println("Alternative: " + alternative);
        
        Optional<String> alternative2 = opt1.or(() -> opt2);
        System.out.println("Alternative 2: " + alternative2);
    }
}
```

## Real-World Examples

### User Service with Optional
```java
public class UserServiceDemo {
    
    public static void main(String[] args) {
        UserService userService = new UserService();
        
        // Find user by ID
        Optional<User> user1 = userService.findUserById(1L);
        Optional<User> user2 = userService.findUserById(999L);
        
        System.out.println("User 1: " + user1);
        System.out.println("User 2: " + user2);
        
        // Get user name safely
        String name1 = user1.map(User::getName).orElse("Unknown");
        String name2 = user2.map(User::getName).orElse("Unknown");
        System.out.println("Name 1: " + name1);
        System.out.println("Name 2: " + name2);
        
        // Get user email with validation
        Optional<String> email1 = user1.flatMap(User::getEmail);
        Optional<String> email2 = user2.flatMap(User::getEmail);
        System.out.println("Email 1: " + email1);
        System.out.println("Email 2: " + email2);
        
        // Process user if present
        user1.ifPresent(user -> {
            System.out.println("Processing user: " + user.getName());
            user.setLastLogin(LocalDateTime.now());
        });
        
        user2.ifPresent(user -> {
            System.out.println("This won't print");
        });
        
        // Find active users
        List<User> activeUsers = userService.getAllUsers().stream()
            .filter(user -> user.getStatus() == UserStatus.ACTIVE)
            .collect(Collectors.toList());
        System.out.println("Active users: " + activeUsers);
        
        // Get oldest user
        Optional<User> oldestUser = userService.getAllUsers().stream()
            .max(Comparator.comparing(User::getAge));
        System.out.println("Oldest user: " + oldestUser);
    }
    
    static class UserService {
        private Map<Long, User> users = new HashMap<>();
        
        public UserService() {
            users.put(1L, new User(1L, "Alice", 25, "alice@example.com", UserStatus.ACTIVE));
            users.put(2L, new User(2L, "Bob", 30, null, UserStatus.ACTIVE));
            users.put(3L, new User(3L, "Charlie", 35, "charlie@example.com", UserStatus.INACTIVE));
        }
        
        public Optional<User> findUserById(Long id) {
            return Optional.ofNullable(users.get(id));
        }
        
        public List<User> getAllUsers() {
            return new ArrayList<>(users.values());
        }
    }
    
    static class User {
        private Long id;
        private String name;
        private int age;
        private String email;
        private UserStatus status;
        
        public User(Long id, String name, int age, String email, UserStatus status) {
            this.id = id;
            this.name = name;
            this.age = age;
            this.email = email;
            this.status = status;
        }
        
        public Long getId() { return id; }
        public String getName() { return name; }
        public int getAge() { return age; }
        public Optional<String> getEmail() { return Optional.ofNullable(email); }
        public UserStatus getStatus() { return status; }
        public void setLastLogin(LocalDateTime lastLogin) {
            System.out.println("Last login set for " + name + ": " + lastLogin);
        }
        
        @Override
        public String toString() {
            return name + " (" + age + ", " + status + ")";
        }
    }
    
    enum UserStatus {
        ACTIVE, INACTIVE
    }
}
```

### Configuration Service with Optional
```java
public class ConfigurationServiceDemo {
    
    public static void main(String[] args) {
        ConfigurationService configService = new ConfigurationService();
        
        // Get configuration values
        Optional<String> dbUrl = configService.getConfig("database.url");
        Optional<Integer> port = configService.getConfig("server.port")
            .flatMap(this::parseInteger);
        Optional<Boolean> debug = configService.getConfig("app.debug")
            .flatMap(this::parseBoolean);
        
        System.out.println("Database URL: " + dbUrl.orElse("Not configured"));
        System.out.println("Server port: " + port.orElse(8080));
        System.out.println("Debug mode: " + debug.orElse(false));
        
        // Process configuration with defaults
        String finalDbUrl = dbUrl.orElseGet(() -> "jdbc:default:localhost:3306/mydb");
        int finalPort = port.orElseGet(() -> 8080);
        boolean finalDebug = debug.orElseGet(() -> false);
        
        System.out.println("\nFinal configuration:");
        System.out.println("Database URL: " + finalDbUrl);
        System.out.println("Server port: " + finalPort);
        System.out.println("Debug mode: " + finalDebug);
        
        // Validate required configuration
        Optional<String> requiredConfig = configService.getConfig("required.setting");
        requiredConfig.orElseThrow(() -> new IllegalStateException("Required configuration missing"));
    }
    
    private Optional<Integer> parseInteger(String value) {
        try {
            return Optional.of(Integer.parseInt(value));
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }
    
    private Optional<Boolean> parseBoolean(String value) {
        if ("true".equalsIgnoreCase(value)) {
            return Optional.of(true);
        } else if ("false".equalsIgnoreCase(value)) {
            return Optional.of(false);
        }
        return Optional.empty();
    }
    
    static class ConfigurationService {
        private Map<String, String> config = new HashMap<>();
        
        public ConfigurationService() {
            config.put("database.url", "jdbc:mysql:localhost:3306/mydb");
            config.put("server.port", "9090");
            config.put("app.debug", "true");
        }
        
        public Optional<String> getConfig(String key) {
            return Optional.ofNullable(config.get(key));
        }
    }
}
```

## Advanced Optional Patterns

### Optional with Streams
```java
public class OptionalWithStreamsDemo {
    
    public static void main(String[] args) {
        List<Optional<String>> optionals = Arrays.asList(
            Optional.of("Hello"),
            Optional.empty(),
            Optional.of("World"),
            Optional.of("Java"),
            Optional.empty()
        );
        
        // Filter out empty Optionals and get values
        List<String> presentValues = optionals.stream()
            .filter(Optional::isPresent)
            .map(Optional::get)
            .collect(Collectors.toList());
        System.out.println("Present values: " + presentValues);
        
        // Using flatMap to handle Optionals in streams
        List<String> flatMappedValues = optionals.stream()
            .flatMap(opt -> opt.stream())
            .collect(Collectors.toList());
        System.out.println("Flat mapped values: " + flatMappedValues);
        
        // Find first present value
        Optional<String> firstPresent = optionals.stream()
            .filter(Optional::isPresent)
            .map(Optional::get)
            .findFirst();
        System.out.println("First present: " + firstPresent);
        
        // Using Optional with stream operations
        List<String> words = Arrays.asList("hello", "world", "java", "optional");
        
        Optional<String> longestWord = words.stream()
            .max(Comparator.comparing(String::length));
        System.out.println("Longest word: " + longestWord);
        
        Optional<String> wordStartingWithJ = words.stream()
            .filter(word -> word.startsWith("j"))
            .findFirst();
        System.out.println("Word starting with 'j': " + wordStartingWithJ);
    }
}
```

### Optional with Exception Handling
```java
public class OptionalExceptionHandlingDemo {
    
    public static void main(String[] args) {
        // Safe division with Optional
        Optional<Double> result1 = safeDivide(10.0, 2.0);
        Optional<Double> result2 = safeDivide(10.0, 0.0);
        
        System.out.println("10 / 2 = " + result1.orElse(Double.NaN));
        System.out.println("10 / 0 = " + result2.orElse(Double.NaN));
        
        // Safe file reading with Optional
        Optional<String> fileContent1 = safeReadFile("existing_file.txt");
        Optional<String> fileContent2 = safeReadFile("non_existing_file.txt");
        
        System.out.println("Existing file content: " + fileContent1.orElse("File not found"));
        System.out.println("Non-existing file content: " + fileContent2.orElse("File not found"));
        
        // Safe network call with Optional
        Optional<String> response1 = safeNetworkCall("https://api.example.com/data");
        Optional<String> response2 = safeNetworkCall("https://invalid-url.com");
        
        System.out.println("Valid API response: " + response1.orElse("No response"));
        System.out.println("Invalid API response: " + response2.orElse("No response"));
    }
    
    private static Optional<Double> safeDivide(double numerator, double denominator) {
        try {
            return Optional.of(numerator / denominator);
        } catch (ArithmeticException e) {
            return Optional.empty();
        }
    }
    
    private static Optional<String> safeReadFile(String filename) {
        try {
            // Simulate file reading
            if ("existing_file.txt".equals(filename)) {
                return Optional.of("File content here");
            } else {
                throw new FileNotFoundException("File not found: " + filename);
            }
        } catch (IOException e) {
            return Optional.empty();
        }
    }
    
    private static Optional<String> safeNetworkCall(String url) {
        try {
            // Simulate network call
            if (url.contains("example.com")) {
                return Optional.of("{\"status\": \"success\"}");
            } else {
                throw new RuntimeException("Network error");
            }
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
```

## Best Practices and Anti-Patterns

### Good Practices
```java
public class OptionalBestPractices {
    
    public static void main(String[] args) {
        // Good: Use Optional for return types that might be null
        Optional<String> goodMethod = findUserEmail(1L);
        
        // Good: Use orElse for simple defaults
        String email = goodMethod.orElse("no-email@example.com");
        
        // Good: Use orElseGet for expensive defaults
        String expensiveDefault = goodMethod.orElseGet(() -> generateDefaultEmail());
        
        // Good: Use orElseThrow for required values
        try {
            String requiredEmail = goodMethod.orElseThrow(() -> 
                new IllegalStateException("Email is required"));
        } catch (IllegalStateException e) {
            System.out.println("Exception: " + e.getMessage());
        }
        
        // Good: Use ifPresent for side effects
        goodMethod.ifPresent(email -> sendEmail(email));
        
        // Good: Use map for transformations
        Optional<String> upperEmail = goodMethod.map(String::toUpperCase);
        
        // Good: Use flatMap for Optional transformations
        Optional<User> user = findUser(1L);
        Optional<String> userEmail = user.flatMap(User::getEmail);
    }
    
    // Good: Return Optional for methods that might return null
    public static Optional<String> findUserEmail(Long userId) {
        // Simulate database lookup
        if (userId == 1L) {
            return Optional.of("user@example.com");
        }
        return Optional.empty();
    }
    
    // Good: Use Optional parameters sparingly
    public static String processUser(String name, Optional<String> email) {
        return name + " - " + email.orElse("no email");
    }
    
    private static String generateDefaultEmail() {
        return "default@example.com";
    }
    
    private static void sendEmail(String email) {
        System.out.println("Sending email to: " + email);
    }
    
    private static Optional<User> findUser(Long id) {
        if (id == 1L) {
            return Optional.of(new User("John", Optional.of("john@example.com")));
        }
        return Optional.empty();
    }
    
    static class User {
        private String name;
        private Optional<String> email;
        
        public User(String name, Optional<String> email) {
            this.name = name;
            this.email = email;
        }
        
        public Optional<String> getEmail() { return email; }
    }
}
```

### Anti-Patterns to Avoid
```java
public class OptionalAntiPatterns {
    
    public static void main(String[] args) {
        // Anti-pattern: Using Optional as a field
        BadUser badUser = new BadUser("John", Optional.of("john@example.com"));
        
        // Anti-pattern: Checking isPresent() and then calling get()
        Optional<String> email = Optional.of("test@example.com");
        if (email.isPresent()) {
            String emailValue = email.get(); // Don't do this
            System.out.println("Email: " + emailValue);
        }
        
        // Better approach: Use ifPresent or orElse
        email.ifPresent(emailValue -> System.out.println("Email: " + emailValue));
        
        // Anti-pattern: Using Optional for primitive types unnecessarily
        Optional<Integer> age = Optional.of(25); // Consider using int with -1 or 0 as sentinel
        
        // Anti-pattern: Returning Optional from methods that always return a value
        Optional<String> alwaysPresent = alwaysReturnsValue(); // Don't do this
        
        // Anti-pattern: Using Optional for collections
        Optional<List<String>> list = Optional.of(Arrays.asList("a", "b")); // Use empty list instead
    }
    
    // Anti-pattern: Method that always returns a value
    public static Optional<String> alwaysReturnsValue() {
        return Optional.of("always present"); // Don't use Optional here
    }
    
    // Anti-pattern: Using Optional as a field
    static class BadUser {
        private String name;
        private Optional<String> email; // Don't do this
        
        public BadUser(String name, Optional<String> email) {
            this.name = name;
            this.email = email;
        }
    }
}
```

## Exercises

### Exercise 1: Optional Calculator
Create a calculator that uses Optional to handle division by zero and other mathematical errors safely.

### Exercise 2: Optional Chain Processing
Build a chain of Optional operations that processes user data through multiple validation and transformation steps.

### Exercise 3: Optional with Collections
Implement methods that use Optional with streams to find, filter, and transform collection elements safely.

### Exercise 4: Optional Configuration System
Create a configuration system that uses Optional to handle missing configuration values with sensible defaults.

## Practice Files
- `OptionalCreationDemo.java` - Creating Optional objects
- `OptionalTypesDemo.java` - Optional with different types
- `SafeValueAccessDemo.java` - Safe value access patterns
- `ConditionalAccessDemo.java` - Conditional access methods
- `OptionalChainingDemo.java` - Method chaining examples
- `OptionalCompositionDemo.java` - Optional composition patterns
- `UserServiceDemo.java` - Real-world user service example
- `ConfigurationServiceDemo.java` - Configuration service example
- `OptionalWithStreamsDemo.java` - Optional with streams
- `OptionalExceptionHandlingDemo.java` - Exception handling with Optional
- `OptionalBestPractices.java` - Best practices examples
- `OptionalAntiPatterns.java` - Anti-patterns to avoid

## Key Takeaways
1. Optional eliminates null pointer exceptions
2. Use Optional for return types that might be null
3. Prefer functional methods over imperative checks
4. Use orElse for simple defaults, orElseGet for expensive defaults
5. Use ifPresent for side effects, map for transformations
6. Avoid using Optional as fields or parameters
7. Don't use Optional for primitive types unnecessarily
8. Optional works well with streams and functional programming

## Next Steps
After completing this lesson, move on to:
- Date and Time API
- CompletableFuture for async programming
- Reactive programming with Java
- Advanced concurrency patterns

## Additional Resources
- [Optional Class Documentation](https://docs.oracle.com/javase/8/docs/api/java/util/Optional.html)
- [Optional Best Practices](https://www.baeldung.com/java-optional)
- [Optional Anti-Patterns](https://dzone.com/articles/using-optional-correctly-is-not-optional)
- [Optional with Streams](https://www.baeldung.com/java-optional-stream)