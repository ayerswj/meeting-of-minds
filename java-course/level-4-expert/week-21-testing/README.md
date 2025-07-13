# Week 21: Testing and Quality Assurance

## 🎯 Learning Objectives
- Master unit testing with JUnit 5 and Mockito
- Implement integration testing strategies
- Practice Test-Driven Development (TDD)
- Understand code coverage and quality metrics
- Learn performance testing techniques
- Implement automated testing pipelines

## 📚 Theory

### Testing Pyramid
```
        /\
       /  \     E2E Tests (Few)
      /____\    
     /      \   Integration Tests (Some)
    /________\  
   /          \ Unit Tests (Many)
  /____________\
```

### Testing Types
1. **Unit Tests**: Test individual components in isolation
2. **Integration Tests**: Test interactions between components
3. **End-to-End Tests**: Test complete user workflows
4. **Performance Tests**: Test system performance under load
5. **Security Tests**: Test security vulnerabilities

## 🧪 Unit Testing with JUnit 5

### Basic JUnit 5 Structure
```java
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Calculator Tests")
class CalculatorTest {
    
    private Calculator calculator;
    
    @BeforeEach
    void setUp() {
        calculator = new Calculator();
    }
    
    @Test
    @DisplayName("Should add two positive numbers")
    void shouldAddTwoPositiveNumbers() {
        // Given
        int a = 5;
        int b = 3;
        
        // When
        int result = calculator.add(a, b);
        
        // Then
        assertEquals(8, result);
    }
    
    @Test
    @DisplayName("Should throw exception for division by zero")
    void shouldThrowExceptionForDivisionByZero() {
        // Given
        int a = 10;
        int b = 0;
        
        // When & Then
        assertThrows(ArithmeticException.class, () -> {
            calculator.divide(a, b);
        });
    }
    
    @Test
    @DisplayName("Should handle multiple test cases")
    @ParameterizedTest
    @CsvSource({
        "2, 3, 5",
        "0, 0, 0",
        "-1, 1, 0",
        "100, 200, 300"
    })
    void shouldAddNumbers(int a, int b, int expected) {
        assertEquals(expected, calculator.add(a, b));
    }
    
    @Test
    @DisplayName("Should handle null input")
    void shouldHandleNullInput() {
        assertThrows(IllegalArgumentException.class, () -> {
            calculator.add(null, 5);
        });
    }
    
    @Nested
    @DisplayName("Multiplication Tests")
    class MultiplicationTests {
        
        @Test
        @DisplayName("Should multiply positive numbers")
        void shouldMultiplyPositiveNumbers() {
            assertEquals(15, calculator.multiply(3, 5));
        }
        
        @Test
        @DisplayName("Should handle zero multiplication")
        void shouldHandleZeroMultiplication() {
            assertEquals(0, calculator.multiply(5, 0));
        }
    }
}
```

### Advanced JUnit 5 Features

#### Test Lifecycle
```java
@DisplayName("Test Lifecycle Example")
class TestLifecycleTest {
    
    @BeforeAll
    static void beforeAll() {
        System.out.println("Before all tests - runs once");
    }
    
    @BeforeEach
    void beforeEach() {
        System.out.println("Before each test");
    }
    
    @AfterEach
    void afterEach() {
        System.out.println("After each test");
    }
    
    @AfterAll
    static void afterAll() {
        System.out.println("After all tests - runs once");
    }
    
    @Test
    void test1() {
        System.out.println("Test 1");
    }
    
    @Test
    void test2() {
        System.out.println("Test 2");
    }
}
```

#### Conditional Test Execution
```java
class ConditionalTestExecutionTest {
    
    @Test
    @EnabledOnOs(OS.WINDOWS)
    void onlyOnWindows() {
        // This test will only run on Windows
    }
    
    @Test
    @DisabledOnOs(OS.MAC)
    void notOnMac() {
        // This test will not run on Mac
    }
    
    @Test
    @EnabledIfSystemProperty(named = "os.arch", matches = ".*64.*")
    void onlyOn64BitArchitecture() {
        // This test will only run on 64-bit architecture
    }
    
    @Test
    @EnabledIfEnvironmentVariable(named = "CI", matches = "true")
    void onlyInCI() {
        // This test will only run in CI environment
    }
    
    @Test
    @Disabled("This test is broken and needs fixing")
    void brokenTest() {
        // This test is disabled
    }
}
```

#### Test Tags and Filtering
```java
@Tag("fast")
class FastTests {
    
    @Test
    @Tag("unit")
    void fastUnitTest() {
        // Fast unit test
    }
}

@Tag("slow")
class SlowTests {
    
    @Test
    @Tag("integration")
    void slowIntegrationTest() {
        // Slow integration test
    }
}

// Run with: mvn test -Dgroups="fast,unit"
```

## 🎭 Mocking with Mockito

### Basic Mocking
```java
@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    
    @Mock
    private UserRepository userRepository;
    
    @Mock
    private EmailService emailService;
    
    @Mock
    private PasswordEncoder passwordEncoder;
    
    @InjectMocks
    private UserService userService;
    
    @Test
    @DisplayName("Should create user successfully")
    void shouldCreateUserSuccessfully() {
        // Given
        CreateUserRequest request = CreateUserRequest.builder()
            .email("test@example.com")
            .password("password")
            .firstName("John")
            .lastName("Doe")
            .build();
        
        User savedUser = User.builder()
            .id(1L)
            .email(request.getEmail())
            .firstName(request.getFirstName())
            .lastName(request.getLastName())
            .build();
        
        when(userRepository.existsByEmail(request.getEmail())).thenReturn(false);
        when(passwordEncoder.encode(request.getPassword())).thenReturn("encodedPassword");
        when(userRepository.save(any(User.class))).thenReturn(savedUser);
        doNothing().when(emailService).sendWelcomeEmail(anyString());
        
        // When
        UserResponse response = userService.createUser(request);
        
        // Then
        assertNotNull(response);
        assertEquals(request.getEmail(), response.getEmail());
        assertEquals(request.getFirstName(), response.getFirstName());
        
        verify(userRepository).existsByEmail(request.getEmail());
        verify(passwordEncoder).encode(request.getPassword());
        verify(userRepository).save(any(User.class));
        verify(emailService).sendWelcomeEmail(request.getEmail());
        verifyNoMoreInteractions(userRepository, emailService, passwordEncoder);
    }
    
    @Test
    @DisplayName("Should throw exception when email already exists")
    void shouldThrowExceptionWhenEmailExists() {
        // Given
        CreateUserRequest request = CreateUserRequest.builder()
            .email("existing@example.com")
            .password("password")
            .firstName("John")
            .lastName("Doe")
            .build();
        
        when(userRepository.existsByEmail(request.getEmail())).thenReturn(true);
        
        // When & Then
        UserAlreadyExistsException exception = assertThrows(
            UserAlreadyExistsException.class,
            () -> userService.createUser(request)
        );
        
        assertEquals("Email already exists: " + request.getEmail(), exception.getMessage());
        
        verify(userRepository).existsByEmail(request.getEmail());
        verifyNoMoreInteractions(userRepository);
        verifyNoInteractions(passwordEncoder, emailService);
    }
    
    @Test
    @DisplayName("Should handle repository exception")
    void shouldHandleRepositoryException() {
        // Given
        CreateUserRequest request = CreateUserRequest.builder()
            .email("test@example.com")
            .password("password")
            .firstName("John")
            .lastName("Doe")
            .build();
        
        when(userRepository.existsByEmail(request.getEmail())).thenReturn(false);
        when(passwordEncoder.encode(request.getPassword())).thenReturn("encodedPassword");
        when(userRepository.save(any(User.class))).thenThrow(new DataAccessException("Database error"));
        
        // When & Then
        assertThrows(DataAccessException.class, () -> userService.createUser(request));
        
        verify(userRepository).existsByEmail(request.getEmail());
        verify(passwordEncoder).encode(request.getPassword());
        verify(userRepository).save(any(User.class));
        verifyNoInteractions(emailService);
    }
}
```

### Advanced Mocking Techniques
```java
@ExtendWith(MockitoExtension.class)
class AdvancedMockingTest {
    
    @Mock
    private UserRepository userRepository;
    
    @Mock
    private EmailService emailService;
    
    @InjectMocks
    private UserService userService;
    
    @Test
    @DisplayName("Should handle argument captor")
    void shouldHandleArgumentCaptor() {
        // Given
        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
        CreateUserRequest request = CreateUserRequest.builder()
            .email("test@example.com")
            .password("password")
            .firstName("John")
            .lastName("Doe")
            .build();
        
        when(userRepository.existsByEmail(request.getEmail())).thenReturn(false);
        when(userRepository.save(any(User.class))).thenAnswer(i -> i.getArgument(0));
        
        // When
        userService.createUser(request);
        
        // Then
        verify(userRepository).save(userCaptor.capture());
        User capturedUser = userCaptor.getValue();
        
        assertEquals(request.getEmail(), capturedUser.getEmail());
        assertEquals(request.getFirstName(), capturedUser.getFirstName());
        assertEquals(request.getLastName(), capturedUser.getLastName());
    }
    
    @Test
    @DisplayName("Should handle spy")
    void shouldHandleSpy() {
        // Given
        UserService spyService = spy(userService);
        CreateUserRequest request = CreateUserRequest.builder()
            .email("test@example.com")
            .password("password")
            .firstName("John")
            .lastName("Doe")
            .build();
        
        doReturn(false).when(userRepository).existsByEmail(request.getEmail());
        doReturn(new User()).when(userRepository).save(any(User.class));
        
        // When
        spyService.createUser(request);
        
        // Then
        verify(spyService).validateRequest(request);
        verify(spyService).createUser(request);
    }
    
    @Test
    @DisplayName("Should handle multiple calls")
    void shouldHandleMultipleCalls() {
        // Given
        String email = "test@example.com";
        
        when(userRepository.findByEmail(email))
            .thenReturn(Optional.empty())
            .thenReturn(Optional.of(new User()))
            .thenThrow(new RuntimeException("Database error"));
        
        // When & Then
        assertFalse(userRepository.findByEmail(email).isPresent());
        assertTrue(userRepository.findByEmail(email).isPresent());
        assertThrows(RuntimeException.class, () -> userRepository.findByEmail(email));
    }
}
```

## 🔗 Integration Testing

### Spring Boot Integration Tests
```java
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource(properties = {
    "spring.jpa.hibernate.ddl-auto=create-drop",
    "spring.datasource.url=jdbc:h2:mem:testdb"
})
@Transactional
class UserControllerIntegrationTest {
    
    @Autowired
    private TestRestTemplate restTemplate;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private ObjectMapper objectMapper;
    
    @Test
    @DisplayName("Should create user via REST API")
    void shouldCreateUserViaRestApi() throws Exception {
        // Given
        CreateUserRequest request = CreateUserRequest.builder()
            .email("integration@example.com")
            .password("password")
            .firstName("Integration")
            .lastName("Test")
            .build();
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        
        HttpEntity<CreateUserRequest> entity = new HttpEntity<>(request, headers);
        
        // When
        ResponseEntity<UserResponse> response = restTemplate.exchange(
            "/api/users",
            HttpMethod.POST,
            entity,
            UserResponse.class
        );
        
        // Then
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(request.getEmail(), response.getBody().getEmail());
        assertEquals(request.getFirstName(), response.getBody().getFirstName());
        
        // Verify database
        Optional<User> savedUser = userRepository.findByEmail(request.getEmail());
        assertTrue(savedUser.isPresent());
        assertEquals(request.getFirstName(), savedUser.get().getFirstName());
    }
    
    @Test
    @DisplayName("Should return 400 for invalid request")
    void shouldReturn400ForInvalidRequest() {
        // Given
        CreateUserRequest request = CreateUserRequest.builder()
            .email("invalid-email")
            .password("")
            .firstName("")
            .lastName("")
            .build();
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        
        HttpEntity<CreateUserRequest> entity = new HttpEntity<>(request, headers);
        
        // When
        ResponseEntity<ErrorResponse> response = restTemplate.exchange(
            "/api/users",
            HttpMethod.POST,
            entity,
            ErrorResponse.class
        );
        
        // Then
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().getErrors().size() > 0);
    }
    
    @Test
    @DisplayName("Should get user by ID")
    void shouldGetUserById() {
        // Given
        User user = User.builder()
            .email("test@example.com")
            .firstName("Test")
            .lastName("User")
            .build();
        
        User savedUser = userRepository.save(user);
        
        // When
        ResponseEntity<UserResponse> response = restTemplate.getForEntity(
            "/api/users/" + savedUser.getId(),
            UserResponse.class
        );
        
        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(savedUser.getId(), response.getBody().getId());
        assertEquals(savedUser.getEmail(), response.getBody().getEmail());
    }
}
```

### TestContainers for Integration Testing
```java
@SpringBootTest
@Testcontainers
class UserServiceIntegrationTest {
    
    @Container
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:13")
        .withDatabaseName("testdb")
        .withUsername("test")
        .withPassword("test");
    
    @Container
    static RedisContainer redis = new RedisContainer("redis:6-alpine");
    
    @Container
    static KafkaContainer kafka = new KafkaContainer(
        DockerImageName.parse("confluentinc/cp-kafka:latest")
    );
    
    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
        registry.add("spring.redis.host", redis::getHost);
        registry.add("spring.redis.port", redis::getFirstMappedPort);
        registry.add("spring.kafka.bootstrap-servers", kafka::getBootstrapServers);
    }
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private UserRepository userRepository;
    
    @Test
    @DisplayName("Should create user with real database")
    void shouldCreateUserWithRealDatabase() {
        // Given
        CreateUserRequest request = CreateUserRequest.builder()
            .email("test@example.com")
            .password("password")
            .firstName("Test")
            .lastName("User")
            .build();
        
        // When
        UserResponse response = userService.createUser(request);
        
        // Then
        assertNotNull(response);
        assertEquals(request.getEmail(), response.getEmail());
        
        // Verify in database
        Optional<User> savedUser = userRepository.findByEmail(request.getEmail());
        assertTrue(savedUser.isPresent());
        assertEquals(request.getFirstName(), savedUser.get().getFirstName());
    }
}
```

## 🚀 Test-Driven Development (TDD)

### TDD Cycle: Red-Green-Refactor

#### Step 1: Red (Write failing test)
```java
@Test
@DisplayName("Should calculate total price with tax")
void shouldCalculateTotalPriceWithTax() {
    // Given
    ShoppingCart cart = new ShoppingCart();
    cart.addItem(new CartItem("Product 1", 100.0, 1));
    cart.addItem(new CartItem("Product 2", 50.0, 2));
    
    // When
    double totalWithTax = cart.calculateTotalWithTax(0.1); // 10% tax
    
    // Then
    assertEquals(220.0, totalWithTax); // (100 + 50*2) * 1.1
}
```

#### Step 2: Green (Write minimal code to pass)
```java
public class ShoppingCart {
    private List<CartItem> items = new ArrayList<>();
    
    public void addItem(CartItem item) {
        items.add(item);
    }
    
    public double calculateTotalWithTax(double taxRate) {
        double subtotal = items.stream()
            .mapToDouble(item -> item.getPrice() * item.getQuantity())
            .sum();
        return subtotal * (1 + taxRate);
    }
}
```

#### Step 3: Refactor (Improve code)
```java
public class ShoppingCart {
    private final List<CartItem> items = new ArrayList<>();
    
    public void addItem(CartItem item) {
        if (item == null) {
            throw new IllegalArgumentException("Item cannot be null");
        }
        items.add(item);
    }
    
    public double calculateTotalWithTax(double taxRate) {
        if (taxRate < 0) {
            throw new IllegalArgumentException("Tax rate cannot be negative");
        }
        
        double subtotal = calculateSubtotal();
        return subtotal * (1 + taxRate);
    }
    
    public double calculateSubtotal() {
        return items.stream()
            .mapToDouble(CartItem::getTotalPrice)
            .sum();
    }
    
    public int getItemCount() {
        return items.size();
    }
    
    public boolean isEmpty() {
        return items.isEmpty();
    }
}
```

### TDD Example: Bank Account
```java
@DisplayName("Bank Account TDD")
class BankAccountTDDTest {
    
    @Test
    @DisplayName("Should create account with initial balance")
    void shouldCreateAccountWithInitialBalance() {
        // Given & When
        BankAccount account = new BankAccount(100.0);
        
        // Then
        assertEquals(100.0, account.getBalance());
    }
    
    @Test
    @DisplayName("Should deposit money")
    void shouldDepositMoney() {
        // Given
        BankAccount account = new BankAccount(100.0);
        
        // When
        account.deposit(50.0);
        
        // Then
        assertEquals(150.0, account.getBalance());
    }
    
    @Test
    @DisplayName("Should withdraw money")
    void shouldWithdrawMoney() {
        // Given
        BankAccount account = new BankAccount(100.0);
        
        // When
        account.withdraw(30.0);
        
        // Then
        assertEquals(70.0, account.getBalance());
    }
    
    @Test
    @DisplayName("Should throw exception when insufficient funds")
    void shouldThrowExceptionWhenInsufficientFunds() {
        // Given
        BankAccount account = new BankAccount(100.0);
        
        // When & Then
        assertThrows(InsufficientFundsException.class, () -> {
            account.withdraw(150.0);
        });
    }
    
    @Test
    @DisplayName("Should calculate interest")
    void shouldCalculateInterest() {
        // Given
        BankAccount account = new BankAccount(1000.0);
        
        // When
        double interest = account.calculateInterest(0.05); // 5% interest
        
        // Then
        assertEquals(50.0, interest);
    }
}
```

## 📊 Code Coverage and Quality Metrics

### JaCoCo Configuration
```xml
<!-- pom.xml -->
<plugin>
    <groupId>org.jacoco</groupId>
    <artifactId>jacoco-maven-plugin</artifactId>
    <version>0.8.10</version>
    <executions>
        <execution>
            <goals>
                <goal>prepare-agent</goal>
            </goals>
        </execution>
        <execution>
            <id>report</id>
            <phase>test</phase>
            <goals>
                <goal>report</goal>
            </goals>
        </execution>
        <execution>
            <id>check</id>
            <goals>
                <goal>check</goal>
            </goals>
            <configuration>
                <rules>
                    <rule>
                        <element>BUNDLE</element>
                        <limits>
                            <limit>
                                <counter>LINE</counter>
                                <value>COVEREDRATIO</value>
                                <minimum>0.80</minimum>
                            </limit>
                        </limits>
                    </rule>
                </rules>
            </configuration>
        </execution>
    </executions>
</plugin>
```

### SonarQube Quality Gates
```yaml
# sonar-project.properties
sonar.projectKey=my-java-project
sonar.projectName=My Java Project
sonar.projectVersion=1.0

sonar.sources=src/main/java
sonar.tests=src/test/java
sonar.java.binaries=target/classes
sonar.java.test.binaries=target/test-classes

sonar.coverage.jacoco.xmlReportPaths=target/site/jacoco/jacoco.xml
sonar.junit.reportPaths=target/surefire-reports
```

## ⚡ Performance Testing

### JMH (Java Microbenchmark Harness)
```java
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Benchmark)
@Fork(value = 2, jvmArgs = {"-Xms2G", "-Xmx2G"})
@Warmup(iterations = 3)
@Measurement(iterations = 8)
public class PerformanceBenchmark {
    
    @Benchmark
    public void testStreamPerformance(Blackhole bh) {
        List<Integer> numbers = IntStream.range(0, 10000)
            .boxed()
            .collect(Collectors.toList());
        
        List<Integer> result = numbers.stream()
            .filter(n -> n % 2 == 0)
            .map(n -> n * 2)
            .collect(Collectors.toList());
        
        bh.consume(result);
    }
    
    @Benchmark
    public void testForLoopPerformance(Blackhole bh) {
        List<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            numbers.add(i);
        }
        
        List<Integer> result = new ArrayList<>();
        for (Integer number : numbers) {
            if (number % 2 == 0) {
                result.add(number * 2);
            }
        }
        
        bh.consume(result);
    }
    
    @Benchmark
    public void testParallelStreamPerformance(Blackhole bh) {
        List<Integer> numbers = IntStream.range(0, 10000)
            .boxed()
            .collect(Collectors.toList());
        
        List<Integer> result = numbers.parallelStream()
            .filter(n -> n % 2 == 0)
            .map(n -> n * 2)
            .collect(Collectors.toList());
        
        bh.consume(result);
    }
}
```

### Load Testing with Gatling
```scala
// UserSimulation.scala
import io.gatling.core.Predef._
import io.gatling.http.Predef._

class UserSimulation extends Simulation {
  
  val httpProtocol = http
    .baseUrl("http://localhost:8080")
    .acceptHeader("application/json")
    .userAgentHeader("Gatling/Performance Test")
  
  val scn = scenario("User API Load Test")
    .exec(http("Create User")
      .post("/api/users")
      .header("Content-Type", "application/json")
      .body(StringBody("""{
        "email": "test@example.com",
        "password": "password",
        "firstName": "Test",
        "lastName": "User"
      }"""))
      .check(status.is(201)))
    .pause(1)
    .exec(http("Get User")
      .get("/api/users/1")
      .check(status.is(200)))
    .pause(1)
    .exec(http("Update User")
      .put("/api/users/1")
      .header("Content-Type", "application/json")
      .body(StringBody("""{
        "firstName": "Updated",
        "lastName": "User"
      }"""))
      .check(status.is(200)))
  
  setUp(
    scn.inject(
      rampUsers(10).during(10.seconds),
      constantUsersPerSec(5).during(30.seconds)
    ).protocols(httpProtocol)
  )
}
```

## 🔄 Automated Testing Pipeline

### GitHub Actions CI/CD
```yaml
# .github/workflows/test.yml
name: Test Pipeline

on:
  push:
    branches: [main, develop]
  pull_request:
    branches: [main]

jobs:
  test:
    runs-on: ubuntu-latest
    
    services:
      postgres:
        image: postgres:13
        env:
          POSTGRES_PASSWORD: postgres
          POSTGRES_DB: testdb
        options: >-
          --health-cmd pg_isready
          --health-interval 10s
          --health-timeout 5s
          --health-retries 5
        ports:
          - 5432:5432
    
    steps:
    - uses: actions/checkout@v3
    
    - name: Set up JDK 17
      uses: actions/setup-java@v3
      with:
        java-version: '17'
        distribution: 'temurin'
    
    - name: Cache Maven packages
      uses: actions/cache@v3
      with:
        path: ~/.m2
        key: ${{ runner.os }}-m2-${{ hashFiles('**/pom.xml') }}
        restore-keys: ${{ runner.os }}-m2
    
    - name: Run unit tests
      run: mvn test -Dgroups="unit"
    
    - name: Run integration tests
      run: mvn test -Dgroups="integration"
      env:
        SPRING_DATASOURCE_URL: jdbc:postgresql://localhost:5432/testdb
        SPRING_DATASOURCE_USERNAME: postgres
        SPRING_DATASOURCE_PASSWORD: postgres
    
    - name: Generate coverage report
      run: mvn jacoco:report
    
    - name: Upload coverage to Codecov
      uses: codecov/codecov-action@v3
      with:
        file: ./target/site/jacoco/jacoco.xml
    
    - name: Run SonarQube analysis
      run: mvn sonar:sonar
      env:
        SONAR_TOKEN: ${{ secrets.SONAR_TOKEN }}
    
    - name: Run performance tests
      run: mvn jmh:benchmark
    
    - name: Upload test results
      uses: actions/upload-artifact@v3
      if: always()
      with:
        name: test-results
        path: |
          target/surefire-reports/
          target/site/jacoco/
          target/benchmark-results/
```

## 🏋️ Exercises

### Exercise 1: Calculator TDD
Implement a calculator using TDD:
1. Start with basic operations (add, subtract, multiply, divide)
2. Add error handling for division by zero
3. Add support for decimal numbers
4. Add memory functions (store, recall, clear)
5. Add scientific functions (square, square root, power)

### Exercise 2: Shopping Cart Testing
Create comprehensive tests for a shopping cart:
1. Unit tests for cart operations
2. Integration tests with product service
3. Performance tests for large carts
4. Contract tests for cart API
5. End-to-end tests for checkout process

### Exercise 3: User Management System
Build a user management system with full test coverage:
1. Unit tests for user service
2. Integration tests with database
3. API tests for REST endpoints
4. Security tests for authentication
5. Performance tests for user operations

### Exercise 4: E-commerce API Testing
Create a complete testing suite for an e-commerce API:
1. Unit tests for all services
2. Integration tests for service interactions
3. API contract tests
4. Load tests for high traffic
5. Security tests for payment processing

## 📖 Best Practices

1. **Write tests first** - Follow TDD principles
2. **Keep tests simple** - One assertion per test
3. **Use descriptive names** - Test names should explain the scenario
4. **Arrange-Act-Assert** - Structure tests clearly
5. **Test edge cases** - Don't just test happy paths
6. **Mock external dependencies** - Keep unit tests isolated
7. **Use test data builders** - Create readable test data
8. **Maintain test data** - Keep tests up to date
9. **Measure coverage** - Aim for high coverage but don't obsess
10. **Automate everything** - CI/CD should run all tests

## 🎯 Next Steps
- Practice TDD on real projects
- Learn about BDD (Behavior-Driven Development)
- Explore mutation testing
- Study performance testing tools
- Implement continuous testing in CI/CD pipelines