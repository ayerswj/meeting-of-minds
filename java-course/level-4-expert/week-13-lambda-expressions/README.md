# Week 13: Lambda Expressions and Functional Programming

## 🎯 Learning Objectives
- Understand functional programming concepts in Java
- Master lambda expression syntax and usage
- Learn about functional interfaces
- Implement method references effectively
- Apply functional programming patterns
- Understand the benefits and trade-offs of functional programming

## 📚 Theory

### What is Functional Programming?
Functional programming is a programming paradigm that treats computation as the evaluation of mathematical functions and avoids changing state and mutable data. In Java, functional programming features were introduced in Java 8.

### Key Concepts
- **Immutability**: Data cannot be changed after creation
- **Pure Functions**: Functions that always return the same output for the same input
- **Higher-Order Functions**: Functions that take other functions as parameters or return functions
- **Function Composition**: Combining multiple functions to create new functions

### Lambda Expressions

#### Basic Syntax
```java
// Old way (anonymous class)
Runnable runnable = new Runnable() {
    @Override
    public void run() {
        System.out.println("Hello World");
    }
};

// Lambda expression
Runnable runnable = () -> System.out.println("Hello World");
```

#### Lambda Expression Structure
```java
(parameters) -> { body }
```

#### Parameter Types
```java
// No parameters
() -> System.out.println("Hello");

// Single parameter (type can be inferred)
name -> System.out.println("Hello " + name);

// Multiple parameters
(a, b) -> a + b;

// Explicit types
(int a, int b) -> a + b;

// Multiple statements
(a, b) -> {
    int result = a + b;
    System.out.println("Result: " + result);
    return result;
};
```

### Functional Interfaces

#### What are Functional Interfaces?
A functional interface is an interface that contains exactly one abstract method. They can have multiple default or static methods.

#### Built-in Functional Interfaces

##### Predicate<T>
```java
@FunctionalInterface
public interface Predicate<T> {
    boolean test(T t);
}

// Examples
Predicate<String> isEmpty = String::isEmpty;
Predicate<String> isLongerThan5 = s -> s.length() > 5;
Predicate<String> startsWithA = s -> s.startsWith("A");

// Combining predicates
Predicate<String> complexPredicate = isEmpty.negate()
    .and(isLongerThan5)
    .or(startsWithA);
```

##### Function<T, R>
```java
@FunctionalInterface
public interface Function<T, R> {
    R apply(T t);
}

// Examples
Function<String, Integer> lengthFunction = String::length;
Function<String, String> upperCaseFunction = String::toUpperCase;
Function<Integer, String> toStringFunction = Object::toString;

// Composing functions
Function<String, String> pipeline = upperCaseFunction
    .andThen(s -> s + "!")
    .compose(s -> "Hello " + s);
```

##### Consumer<T>
```java
@FunctionalInterface
public interface Consumer<T> {
    void accept(T t);
}

// Examples
Consumer<String> printer = System.out::println;
Consumer<String> logger = s -> System.out.println("Log: " + s);

// Combining consumers
Consumer<String> combined = printer.andThen(logger);
```

##### Supplier<T>
```java
@FunctionalInterface
public interface Supplier<T> {
    T get();
}

// Examples
Supplier<String> stringSupplier = () -> "Hello";
Supplier<LocalDateTime> dateSupplier = LocalDateTime::now;
Supplier<Random> randomSupplier = Random::new;
```

##### BiFunction<T, U, R>
```java
@FunctionalInterface
public interface BiFunction<T, U, R> {
    R apply(T t, U u);
}

// Examples
BiFunction<String, String, String> concat = String::concat;
BiFunction<Integer, Integer, Integer> sum = Integer::sum;
BiFunction<String, Integer, String> repeat = String::repeat;
```

#### Creating Custom Functional Interfaces
```java
@FunctionalInterface
public interface TriFunction<T, U, V, R> {
    R apply(T t, U u, V v);
}

@FunctionalInterface
public interface Validator<T> {
    boolean isValid(T t);
    
    // Default method
    default Validator<T> and(Validator<T> other) {
        return t -> this.isValid(t) && other.isValid(t);
    }
}
```

### Method References

#### Types of Method References

##### Static Method References
```java
// Class::staticMethod
Function<String, Integer> parseInt = Integer::parseInt;
Supplier<Double> random = Math::random;
```

##### Instance Method References on Specific Objects
```java
// object::instanceMethod
String prefix = "Hello ";
Function<String, String> addPrefix = prefix::concat;
```

##### Instance Method References on Arbitrary Objects
```java
// Class::instanceMethod
Function<String, Integer> length = String::length;
BiFunction<String, String, Boolean> contains = String::contains;
```

##### Constructor References
```java
// Class::new
Supplier<ArrayList<String>> listSupplier = ArrayList::new;
Function<Integer, ArrayList<String>> sizedListSupplier = ArrayList::new;
```

### Practical Examples

#### Example 1: Event Handling System
```java
public class EventHandler {
    private Map<String, List<Consumer<Event>>> handlers = new HashMap<>();
    
    public void registerHandler(String eventType, Consumer<Event> handler) {
        handlers.computeIfAbsent(eventType, k -> new ArrayList<>()).add(handler);
    }
    
    public void fireEvent(String eventType, Event event) {
        handlers.getOrDefault(eventType, Collections.emptyList())
                .forEach(handler -> handler.accept(event));
    }
}

// Usage
EventHandler handler = new EventHandler();
handler.registerHandler("USER_LOGIN", event -> 
    System.out.println("User logged in: " + event.getData()));
handler.registerHandler("USER_LOGOUT", event -> 
    System.out.println("User logged out: " + event.getData()));
```

#### Example 2: Data Processing Pipeline
```java
public class DataProcessor {
    public static <T, R> List<R> processData(
            List<T> data,
            Predicate<T> filter,
            Function<T, R> mapper,
            Consumer<R> consumer) {
        
        return data.stream()
                .filter(filter)
                .map(mapper)
                .peek(consumer)
                .collect(Collectors.toList());
    }
}

// Usage
List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David");
List<String> processed = DataProcessor.processData(
    names,
    name -> name.length() > 3,           // filter
    String::toUpperCase,                 // mapper
    System.out::println                  // consumer
);
```

#### Example 3: Configuration Builder
```java
public class ConfigurationBuilder {
    private Map<String, Object> config = new HashMap<>();
    
    public ConfigurationBuilder set(String key, Object value) {
        config.put(key, value);
        return this;
    }
    
    public ConfigurationBuilder setIf(boolean condition, String key, Object value) {
        if (condition) {
            config.put(key, value);
        }
        return this;
    }
    
    public ConfigurationBuilder setIfPresent(String key, Supplier<Object> valueSupplier) {
        try {
            Object value = valueSupplier.get();
            if (value != null) {
                config.put(key, value);
            }
        } catch (Exception e) {
            // Ignore exceptions from supplier
        }
        return this;
    }
    
    public Map<String, Object> build() {
        return new HashMap<>(config);
    }
}

// Usage
Map<String, Object> config = new ConfigurationBuilder()
    .set("host", "localhost")
    .set("port", 8080)
    .setIf(System.getProperty("debug") != null, "debug", true)
    .setIfPresent("apiKey", () -> System.getenv("API_KEY"))
    .build();
```

## 💻 Practice Examples

### Example 1: Functional Calculator
```java
public class FunctionalCalculator {
    private static final Map<String, BiFunction<Double, Double, Double>> operations = Map.of(
        "+", Double::sum,
        "-", (a, b) -> a - b,
        "*", (a, b) -> a * b,
        "/", (a, b) -> b != 0 ? a / b : Double.NaN,
        "^", Math::pow,
        "max", Math::max,
        "min", Math::min
    );
    
    public static double calculate(String operation, double a, double b) {
        BiFunction<Double, Double, Double> op = operations.get(operation);
        if (op == null) {
            throw new IllegalArgumentException("Unknown operation: " + operation);
        }
        return op.apply(a, b);
    }
    
    public static void main(String[] args) {
        System.out.println(calculate("+", 5, 3));  // 8.0
        System.out.println(calculate("*", 4, 7));  // 28.0
        System.out.println(calculate("^", 2, 8));  // 256.0
    }
}
```

### Example 2: Validation Framework
```java
public class ValidationFramework {
    @FunctionalInterface
    public interface Validator<T> {
        ValidationResult validate(T value);
        
        default Validator<T> and(Validator<T> other) {
            return value -> {
                ValidationResult result = this.validate(value);
                return result.isValid() ? other.validate(value) : result;
            };
        }
        
        default Validator<T> or(Validator<T> other) {
            return value -> {
                ValidationResult result = this.validate(value);
                return result.isValid() ? result : other.validate(value);
            };
        }
    }
    
    public static class ValidationResult {
        private final boolean valid;
        private final String message;
        
        public ValidationResult(boolean valid, String message) {
            this.valid = valid;
            this.message = message;
        }
        
        public boolean isValid() { return valid; }
        public String getMessage() { return message; }
        
        public static ValidationResult success() {
            return new ValidationResult(true, "Valid");
        }
        
        public static ValidationResult failure(String message) {
            return new ValidationResult(false, message);
        }
    }
    
    // Predefined validators
    public static <T> Validator<T> notNull() {
        return value -> value != null ? 
            ValidationResult.success() : 
            ValidationResult.failure("Value cannot be null");
    }
    
    public static Validator<String> notEmpty() {
        return value -> value != null && !value.trim().isEmpty() ? 
            ValidationResult.success() : 
            ValidationResult.failure("String cannot be empty");
    }
    
    public static Validator<String> minLength(int min) {
        return value -> value != null && value.length() >= min ? 
            ValidationResult.success() : 
            ValidationResult.failure("Length must be at least " + min);
    }
    
    public static Validator<String> matches(String regex) {
        return value -> value != null && value.matches(regex) ? 
            ValidationResult.success() : 
            ValidationResult.failure("Value does not match pattern: " + regex);
    }
    
    public static Validator<Integer> inRange(int min, int max) {
        return value -> value != null && value >= min && value <= max ? 
            ValidationResult.success() : 
            ValidationResult.failure("Value must be between " + min + " and " + max);
    }
}

// Usage
public class UserValidator {
    public static ValidationFramework.Validator<String> emailValidator() {
        return ValidationFramework.notNull()
            .and(ValidationFramework.notEmpty())
            .and(ValidationFramework.matches("^[A-Za-z0-9+_.-]+@(.+)$"));
    }
    
    public static ValidationFramework.Validator<String> passwordValidator() {
        return ValidationFramework.notNull()
            .and(ValidationFramework.minLength(8))
            .and(ValidationFramework.matches(".*[A-Z].*"))  // At least one uppercase
            .and(ValidationFramework.matches(".*[a-z].*"))  // At least one lowercase
            .and(ValidationFramework.matches(".*\\d.*"));   // At least one digit
    }
    
    public static ValidationFramework.Validator<Integer> ageValidator() {
        return ValidationFramework.inRange(0, 150);
    }
}
```

### Example 3: Retry Mechanism
```java
public class RetryMechanism {
    @FunctionalInterface
    public interface RetryableOperation<T> {
        T execute() throws Exception;
    }
    
    public static <T> T retry(RetryableOperation<T> operation, int maxAttempts, long delayMs) {
        Exception lastException = null;
        
        for (int attempt = 1; attempt <= maxAttempts; attempt++) {
            try {
                return operation.execute();
            } catch (Exception e) {
                lastException = e;
                if (attempt < maxAttempts) {
                    try {
                        Thread.sleep(delayMs);
                    } catch (InterruptedException ie) {
                        Thread.currentThread().interrupt();
                        throw new RuntimeException("Retry interrupted", ie);
                    }
                }
            }
        }
        
        throw new RuntimeException("Operation failed after " + maxAttempts + " attempts", lastException);
    }
    
    public static <T> T retryWithBackoff(RetryableOperation<T> operation, int maxAttempts, long initialDelayMs) {
        Exception lastException = null;
        long delay = initialDelayMs;
        
        for (int attempt = 1; attempt <= maxAttempts; attempt++) {
            try {
                return operation.execute();
            } catch (Exception e) {
                lastException = e;
                if (attempt < maxAttempts) {
                    try {
                        Thread.sleep(delay);
                        delay *= 2; // Exponential backoff
                    } catch (InterruptedException ie) {
                        Thread.currentThread().interrupt();
                        throw new RuntimeException("Retry interrupted", ie);
                    }
                }
            }
        }
        
        throw new RuntimeException("Operation failed after " + maxAttempts + " attempts", lastException);
    }
}

// Usage
public class ApiClient {
    public String fetchData(String url) {
        return RetryMechanism.retryWithBackoff(
            () -> {
                // Simulate API call that might fail
                if (Math.random() < 0.7) {
                    throw new RuntimeException("API call failed");
                }
                return "Data from " + url;
            },
            3,  // max attempts
            1000 // initial delay in ms
        );
    }
}
```

## 🏋️ Exercises

### Exercise 1: Functional List Operations
Create a functional list utility class that provides:
- `map` function to transform elements
- `filter` function to filter elements
- `reduce` function to aggregate elements
- `forEach` function to perform actions on elements
- `flatMap` function to transform and flatten

### Exercise 2: Event-Driven Architecture
Build a simple event-driven system using functional interfaces:
- Create an `EventBus` that can register handlers for different event types
- Implement event publishing and handling
- Support event filtering and transformation
- Add support for async event processing

### Exercise 3: Configuration Management
Create a functional configuration management system:
- Support different configuration sources (file, environment, system properties)
- Implement configuration validation using functional validators
- Support configuration transformation and defaults
- Add support for configuration hot-reloading

### Exercise 4: Functional Caching
Implement a functional caching mechanism:
- Support different cache eviction strategies
- Implement cache statistics and monitoring
- Support cache warming and preloading
- Add support for distributed caching

## 🔍 Advanced Topics

### Currying and Partial Application
```java
public class FunctionalUtils {
    public static <T, U, R> Function<T, Function<U, R>> curry(BiFunction<T, U, R> function) {
        return t -> u -> function.apply(t, u);
    }
    
    public static <T, U, R> BiFunction<T, U, R> uncurry(Function<T, Function<U, R>> function) {
        return (t, u) -> function.apply(t).apply(u);
    }
    
    public static <T, U, R> Function<U, R> partial(BiFunction<T, U, R> function, T value) {
        return u -> function.apply(value, u);
    }
}
```

### Memoization
```java
public class Memoization {
    public static <T, R> Function<T, R> memoize(Function<T, R> function) {
        Map<T, R> cache = new ConcurrentHashMap<>();
        return t -> cache.computeIfAbsent(t, function);
    }
    
    public static <T, R> Function<T, R> memoizeWithExpiry(
            Function<T, R> function, 
            Duration expiry) {
        Map<T, TimedValue<R>> cache = new ConcurrentHashMap<>();
        return t -> {
            TimedValue<R> timedValue = cache.get(t);
            if (timedValue != null && !timedValue.isExpired()) {
                return timedValue.getValue();
            }
            R result = function.apply(t);
            cache.put(t, new TimedValue<>(result, Instant.now().plus(expiry)));
            return result;
        };
    }
    
    private static class TimedValue<T> {
        private final T value;
        private final Instant expiry;
        
        public TimedValue(T value, Instant expiry) {
            this.value = value;
            this.expiry = expiry;
        }
        
        public T getValue() { return value; }
        public boolean isExpired() { return Instant.now().isAfter(expiry); }
    }
}
```

## 📖 Best Practices

1. **Use meaningful names for lambda parameters**
2. **Keep lambda expressions short and focused**
3. **Prefer method references over lambda expressions when possible**
4. **Use functional interfaces for callback patterns**
5. **Avoid side effects in pure functions**
6. **Consider performance implications of functional programming**
7. **Use Optional for null safety**
8. **Combine functional and imperative programming appropriately**

## 🎯 Next Steps
- Practice implementing functional programming patterns
- Explore the Streams API in detail
- Learn about CompletableFuture for async programming
- Study design patterns that work well with functional programming