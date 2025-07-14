# Project 6: Microservices E-commerce Platform

## 🎯 Project Overview
Build a complete microservices-based e-commerce platform that demonstrates enterprise-level Java development skills, including Spring Boot, Spring Cloud, Docker, Kubernetes, and modern architectural patterns.

## 🏗️ Architecture Overview

### Microservices Architecture
```
┌─────────────────┐    ┌─────────────────┐    ┌─────────────────┐
│   API Gateway   │    │   Load Balancer │    │   Service Mesh  │
│   (Spring Cloud │    │   (Nginx/HAProxy│    │   (Istio/Linkerd│
│    Gateway)     │    │   /Envoy)       │    │   )             │
└─────────────────┘    └─────────────────┘    └─────────────────┘
         │                       │                       │
         └───────────────────────┼───────────────────────┘
                                 │
    ┌─────────────────────────────────────────────────────────────┐
    │                     Microservices Layer                     │
    ├─────────────┬─────────────┬─────────────┬───────────────────┤
    │   User      │   Product   │   Order     │   Payment        │
    │  Service    │   Service   │   Service   │   Service        │
    ├─────────────┼─────────────┼─────────────┼───────────────────┤
    │   Cart      │   Inventory │   Shipping  │   Notification   │
    │  Service    │   Service   │   Service   │   Service        │
    └─────────────┴─────────────┴─────────────┴───────────────────┘
                                 │
    ┌─────────────────────────────────────────────────────────────┐
    │                    Infrastructure Layer                     │
    ├─────────────┬─────────────┬─────────────┬───────────────────┤
    │   Service   │   Config    │   Circuit   │   Distributed    │
    │ Discovery   │   Server    │   Breaker   │   Tracing        │
    │ (Eureka)    │ (Config)    │ (Hystrix)   │ (Zipkin)         │
    ├─────────────┼─────────────┼─────────────┼───────────────────┤
    │   Message   │   Cache     │   Database  │   Monitoring     │
    │   Queue     │  (Redis)    │ (PostgreSQL)│ (Prometheus)     │
    │ (Kafka)     │             │             │                  │
    └─────────────┴─────────────┴─────────────┴───────────────────┘
```

## 🚀 Microservices Breakdown

### 1. API Gateway Service
**Technology Stack**: Spring Cloud Gateway, Spring Security, JWT

**Responsibilities**:
- Route requests to appropriate microservices
- Authentication and authorization
- Rate limiting and throttling
- Request/response transformation
- API documentation (Swagger/OpenAPI)

**Key Features**:
```java
@Configuration
public class GatewayConfig {
    
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
            .route("user-service", r -> r
                .path("/api/users/**")
                .filters(f -> f
                    .rewritePath("/api/users/(?<segment>.*)", "/${segment}")
                    .addRequestHeader("X-Response-Time", System.currentTimeMillis() + "")
                )
                .uri("lb://user-service"))
            .route("product-service", r -> r
                .path("/api/products/**")
                .filters(f -> f
                    .rewritePath("/api/products/(?<segment>.*)", "/${segment}")
                    .circuitBreaker(config -> config
                        .setName("productCircuitBreaker")
                        .setFallbackUri("forward:/fallback"))
                )
                .uri("lb://product-service"))
            .build();
    }
    
    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        return http
            .csrf().disable()
            .authorizeExchange()
                .pathMatchers("/api/auth/**").permitAll()
                .pathMatchers("/api/products/**").permitAll()
                .anyExchange().authenticated()
            .and()
            .oauth2ResourceServer()
                .jwt()
            .and().and().build();
    }
}
```

### 2. User Service
**Technology Stack**: Spring Boot, Spring Data JPA, PostgreSQL, Spring Security

**Responsibilities**:
- User registration and authentication
- Profile management
- Role-based access control
- User preferences

**Key Features**:
```java
@Service
@Transactional
public class UserService {
    
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final KafkaTemplate<String, UserEvent> kafkaTemplate;
    
    public UserResponse createUser(CreateUserRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new UserAlreadyExistsException("Email already registered");
        }
        
        User user = User.builder()
            .email(request.getEmail())
            .password(passwordEncoder.encode(request.getPassword()))
            .firstName(request.getFirstName())
            .lastName(request.getLastName())
            .role(Role.CUSTOMER)
            .status(UserStatus.ACTIVE)
            .build();
        
        User savedUser = userRepository.save(user);
        
        // Publish user created event
        UserEvent event = UserEvent.builder()
            .eventType(UserEventType.USER_CREATED)
            .userId(savedUser.getId())
            .email(savedUser.getEmail())
            .timestamp(Instant.now())
            .build();
        
        kafkaTemplate.send("user-events", event);
        
        return UserResponse.from(savedUser);
    }
    
    public AuthenticationResponse authenticate(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
            .orElseThrow(() -> new AuthenticationException("Invalid credentials"));
        
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new AuthenticationException("Invalid credentials");
        }
        
        String token = jwtTokenProvider.generateToken(user);
        
        return AuthenticationResponse.builder()
            .token(token)
            .user(UserResponse.from(user))
            .build();
    }
}
```

### 3. Product Service
**Technology Stack**: Spring Boot, Spring Data JPA, PostgreSQL, Redis, Elasticsearch

**Responsibilities**:
- Product catalog management
- Product search and filtering
- Product reviews and ratings
- Inventory tracking

**Key Features**:
```java
@Service
@Transactional
public class ProductService {
    
    private final ProductRepository productRepository;
    private final ProductSearchRepository searchRepository;
    private final RedisTemplate<String, Product> redisTemplate;
    private final KafkaTemplate<String, ProductEvent> kafkaTemplate;
    
    @Cacheable(value = "products", key = "#id")
    public ProductResponse getProduct(Long id) {
        Product product = productRepository.findById(id)
            .orElseThrow(() -> new ProductNotFoundException("Product not found"));
        
        return ProductResponse.from(product);
    }
    
    public Page<ProductResponse> searchProducts(ProductSearchRequest request) {
        // Try cache first
        String cacheKey = "search:" + request.hashCode();
        Page<ProductResponse> cached = (Page<ProductResponse>) redisTemplate.opsForValue().get(cacheKey);
        if (cached != null) {
            return cached;
        }
        
        // Search in Elasticsearch
        Page<Product> products = searchRepository.search(
            request.getQuery(),
            request.getCategory(),
            request.getMinPrice(),
            request.getMaxPrice(),
            PageRequest.of(request.getPage(), request.getSize())
        );
        
        Page<ProductResponse> result = products.map(ProductResponse::from);
        
        // Cache result
        redisTemplate.opsForValue().set(cacheKey, result, Duration.ofMinutes(5));
        
        return result;
    }
    
    @CacheEvict(value = "products", key = "#request.id")
    public ProductResponse updateProduct(UpdateProductRequest request) {
        Product product = productRepository.findById(request.getId())
            .orElseThrow(() -> new ProductNotFoundException("Product not found"));
        
        product.update(request);
        Product savedProduct = productRepository.save(product);
        
        // Update search index
        searchRepository.save(savedProduct);
        
        // Publish product updated event
        ProductEvent event = ProductEvent.builder()
            .eventType(ProductEventType.PRODUCT_UPDATED)
            .productId(savedProduct.getId())
            .timestamp(Instant.now())
            .build();
        
        kafkaTemplate.send("product-events", event);
        
        return ProductResponse.from(savedProduct);
    }
}
```

### 4. Order Service
**Technology Stack**: Spring Boot, Spring Data JPA, PostgreSQL, Saga Pattern

**Responsibilities**:
- Order creation and management
- Order status tracking
- Order history
- Saga orchestration for distributed transactions

**Key Features**:
```java
@Service
@Transactional
public class OrderService {
    
    private final OrderRepository orderRepository;
    private final OrderSagaManager sagaManager;
    private final KafkaTemplate<String, OrderEvent> kafkaTemplate;
    
    public OrderResponse createOrder(CreateOrderRequest request) {
        // Start saga for distributed transaction
        OrderSaga saga = OrderSaga.builder()
            .orderId(UUID.randomUUID().toString())
            .userId(request.getUserId())
            .items(request.getItems())
            .shippingAddress(request.getShippingAddress())
            .paymentMethod(request.getPaymentMethod())
            .build();
        
        sagaManager.startSaga(saga);
        
        return OrderResponse.from(saga.getOrder());
    }
    
    @EventListener
    public void handleInventoryReserved(InventoryReservedEvent event) {
        Order order = orderRepository.findBySagaId(event.getSagaId())
            .orElseThrow(() -> new OrderNotFoundException("Order not found"));
        
        order.setStatus(OrderStatus.INVENTORY_RESERVED);
        orderRepository.save(order);
        
        // Trigger payment processing
        PaymentRequest paymentRequest = PaymentRequest.builder()
            .orderId(order.getId())
            .amount(order.getTotalAmount())
            .paymentMethod(order.getPaymentMethod())
            .build();
        
        kafkaTemplate.send("payment-requests", paymentRequest);
    }
    
    @EventListener
    public void handlePaymentProcessed(PaymentProcessedEvent event) {
        Order order = orderRepository.findBySagaId(event.getSagaId())
            .orElseThrow(() -> new OrderNotFoundException("Order not found"));
        
        order.setStatus(OrderStatus.PAYMENT_PROCESSED);
        orderRepository.save(order);
        
        // Trigger shipping
        ShippingRequest shippingRequest = ShippingRequest.builder()
            .orderId(order.getId())
            .shippingAddress(order.getShippingAddress())
            .items(order.getItems())
            .build();
        
        kafkaTemplate.send("shipping-requests", shippingRequest);
    }
}
```

### 5. Payment Service
**Technology Stack**: Spring Boot, Spring Data JPA, PostgreSQL, Stripe API

**Responsibilities**:
- Payment processing
- Payment method management
- Refund processing
- Payment security

**Key Features**:
```java
@Service
@Transactional
public class PaymentService {
    
    private final PaymentRepository paymentRepository;
    private final StripeService stripeService;
    private final KafkaTemplate<String, PaymentEvent> kafkaTemplate;
    
    public PaymentResponse processPayment(PaymentRequest request) {
        try {
            // Process payment with Stripe
            PaymentIntent paymentIntent = stripeService.createPaymentIntent(
                request.getAmount(),
                request.getCurrency(),
                request.getPaymentMethodId()
            );
            
            Payment payment = Payment.builder()
                .orderId(request.getOrderId())
                .amount(request.getAmount())
                .currency(request.getCurrency())
                .status(PaymentStatus.PROCESSING)
                .paymentIntentId(paymentIntent.getId())
                .build();
            
            Payment savedPayment = paymentRepository.save(payment);
            
            // Confirm payment
            PaymentIntent confirmedIntent = stripeService.confirmPayment(paymentIntent.getId());
            
            if ("succeeded".equals(confirmedIntent.getStatus())) {
                savedPayment.setStatus(PaymentStatus.COMPLETED);
                paymentRepository.save(savedPayment);
                
                // Publish payment success event
                PaymentEvent event = PaymentEvent.builder()
                    .eventType(PaymentEventType.PAYMENT_COMPLETED)
                    .orderId(request.getOrderId())
                    .paymentId(savedPayment.getId())
                    .amount(request.getAmount())
                    .timestamp(Instant.now())
                    .build();
                
                kafkaTemplate.send("payment-events", event);
                
                return PaymentResponse.from(savedPayment);
            } else {
                savedPayment.setStatus(PaymentStatus.FAILED);
                paymentRepository.save(savedPayment);
                
                throw new PaymentProcessingException("Payment failed");
            }
            
        } catch (Exception e) {
            // Publish payment failure event
            PaymentEvent event = PaymentEvent.builder()
                .eventType(PaymentEventType.PAYMENT_FAILED)
                .orderId(request.getOrderId())
                .errorMessage(e.getMessage())
                .timestamp(Instant.now())
                .build();
            
            kafkaTemplate.send("payment-events", event);
            
            throw new PaymentProcessingException("Payment processing failed", e);
        }
    }
}
```

## 🏗️ Infrastructure Components

### 1. Service Discovery (Eureka)
```yaml
# application.yml
spring:
  application:
    name: eureka-server
  cloud:
    inetutils:
      preferred-networks:
        - 10.0
        - 192.168
        - 172.16

server:
  port: 8761

eureka:
  instance:
    hostname: localhost
  client:
    registerWithEureka: false
    fetchRegistry: false
    serviceUrl:
      defaultZone: http://localhost:8761/eureka/
  server:
    wait-time-in-ms-when-sync-empty: 0
```

### 2. Configuration Server
```yaml
# application.yml
spring:
  application:
    name: config-server
  cloud:
    config:
      server:
        git:
          uri: https://github.com/your-org/ecommerce-config
          default-label: main
          search-paths: '{application}'

server:
  port: 8888
```

### 3. Circuit Breaker (Resilience4j)
```java
@Configuration
public class CircuitBreakerConfig {
    
    @Bean
    public CircuitBreakerRegistry circuitBreakerRegistry() {
        CircuitBreakerConfig config = CircuitBreakerConfig.custom()
            .failureRateThreshold(50)
            .waitDurationInOpenState(Duration.ofMillis(1000))
            .ringBufferSizeInHalfOpenState(2)
            .ringBufferSizeInClosedState(2)
            .build();
        
        return CircuitBreakerRegistry.of(config);
    }
    
    @Bean
    public CircuitBreaker productServiceCircuitBreaker(CircuitBreakerRegistry registry) {
        return registry.circuitBreaker("product-service");
    }
}
```

### 4. Distributed Tracing (Zipkin)
```yaml
# docker-compose.yml
version: '3.8'
services:
  zipkin:
    image: openzipkin/zipkin:latest
    ports:
      - "9411:9411"
    environment:
      - STORAGE_TYPE=elasticsearch
      - ES_HOSTS=elasticsearch:9200
    depends_on:
      - elasticsearch
```

## 🐳 Docker and Kubernetes

### Docker Configuration
```dockerfile
# Dockerfile for each service
FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
```

### Kubernetes Deployment
```yaml
# k8s/deployment.yaml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: user-service
spec:
  replicas: 3
  selector:
    matchLabels:
      app: user-service
  template:
    metadata:
      labels:
        app: user-service
    spec:
      containers:
      - name: user-service
        image: ecommerce/user-service:latest
        ports:
        - containerPort: 8080
        env:
        - name: SPRING_PROFILES_ACTIVE
          value: "kubernetes"
        - name: DB_HOST
          valueFrom:
            configMapKeyRef:
              name: app-config
              key: db.host
        resources:
          requests:
            memory: "512Mi"
            cpu: "250m"
          limits:
            memory: "1Gi"
            cpu: "500m"
        livenessProbe:
          httpGet:
            path: /actuator/health
            port: 8080
          initialDelaySeconds: 30
          periodSeconds: 10
        readinessProbe:
          httpGet:
            path: /actuator/health/readiness
            port: 8080
          initialDelaySeconds: 5
          periodSeconds: 5
```

## 📊 Monitoring and Observability

### 1. Prometheus Configuration
```yaml
# prometheus.yml
global:
  scrape_interval: 15s

scrape_configs:
  - job_name: 'spring-actuator'
    metrics_path: '/actuator/prometheus'
    static_configs:
      - targets: ['user-service:8080', 'product-service:8080', 'order-service:8080']
```

### 2. Grafana Dashboards
```json
{
  "dashboard": {
    "title": "E-commerce Microservices Dashboard",
    "panels": [
      {
        "title": "Request Rate",
        "type": "graph",
        "targets": [
          {
            "expr": "rate(http_requests_total[5m])",
            "legendFormat": "{{service}}"
          }
        ]
      },
      {
        "title": "Response Time",
        "type": "graph",
        "targets": [
          {
            "expr": "histogram_quantile(0.95, rate(http_request_duration_seconds_bucket[5m]))",
            "legendFormat": "{{service}}"
          }
        ]
      }
    ]
  }
}
```

## 🧪 Testing Strategy

### 1. Unit Tests
```java
@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    
    @Mock
    private UserRepository userRepository;
    
    @Mock
    private PasswordEncoder passwordEncoder;
    
    @Mock
    private KafkaTemplate<String, UserEvent> kafkaTemplate;
    
    @InjectMocks
    private UserService userService;
    
    @Test
    void createUser_WithValidRequest_ShouldCreateUser() {
        // Given
        CreateUserRequest request = CreateUserRequest.builder()
            .email("test@example.com")
            .password("password")
            .firstName("John")
            .lastName("Doe")
            .build();
        
        when(userRepository.existsByEmail(request.getEmail())).thenReturn(false);
        when(passwordEncoder.encode(request.getPassword())).thenReturn("encoded");
        when(userRepository.save(any(User.class))).thenAnswer(i -> i.getArgument(0));
        
        // When
        UserResponse response = userService.createUser(request);
        
        // Then
        assertThat(response.getEmail()).isEqualTo(request.getEmail());
        assertThat(response.getFirstName()).isEqualTo(request.getFirstName());
        verify(kafkaTemplate).send(eq("user-events"), any(UserEvent.class));
    }
}
```

### 2. Integration Tests
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
    static KafkaContainer kafka = new KafkaContainer(DockerImageName.parse("confluentinc/cp-kafka:latest"));
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private TestRestTemplate restTemplate;
    
    @Test
    void createUser_ShouldPersistToDatabase() {
        // Given
        CreateUserRequest request = CreateUserRequest.builder()
            .email("integration@example.com")
            .password("password")
            .firstName("Integration")
            .lastName("Test")
            .build();
        
        // When
        UserResponse response = userService.createUser(request);
        
        // Then
        assertThat(response.getId()).isNotNull();
        assertThat(response.getEmail()).isEqualTo(request.getEmail());
    }
}
```

### 3. Contract Tests (Pact)
```java
@ExtendWith(PactConsumerTestExt.class)
@PactTestFor(providerName = "product-service")
class ProductServiceContractTest {
    
    @Pact(consumer = "order-service")
    public RequestResponsePact createPact(PactDslWithProvider builder) {
        return builder
            .given("product exists")
            .uponReceiving("a request for product")
            .path("/api/products/1")
            .method("GET")
            .willRespondWith()
            .status(200)
            .body(new PactDslJsonBody()
                .numberType("id", 1)
                .stringType("name", "Test Product")
                .numberType("price", 99.99)
                .numberType("stock", 10))
            .toPact();
    }
    
    @Test
    @PactTestFor(pactMethod = "createPact")
    void testProductServiceContract(MockServer mockServer) {
        // Test the contract
        ProductResponse product = restTemplate.getForObject(
            mockServer.getUrl() + "/api/products/1", 
            ProductResponse.class
        );
        
        assertThat(product.getId()).isEqualTo(1L);
        assertThat(product.getName()).isEqualTo("Test Product");
    }
}
```

## 🚀 Deployment Pipeline

### 1. CI/CD Pipeline (GitHub Actions)
```yaml
# .github/workflows/deploy.yml
name: Deploy to Kubernetes

on:
  push:
    branches: [main]

jobs:
  build-and-deploy:
    runs-on: ubuntu-latest
    
    steps:
    - uses: actions/checkout@v3
    
    - name: Set up JDK 17
      uses: actions/setup-java@v3
      with:
        java-version: '17'
        distribution: 'temurin'
    
    - name: Build with Maven
      run: mvn clean package -DskipTests
    
    - name: Build Docker images
      run: |
        docker build -t ecommerce/user-service:${{ github.sha }} ./user-service
        docker build -t ecommerce/product-service:${{ github.sha }} ./product-service
        docker build -t ecommerce/order-service:${{ github.sha }} ./order-service
    
    - name: Push to registry
      run: |
        docker push ecommerce/user-service:${{ github.sha }}
        docker push ecommerce/product-service:${{ github.sha }}
        docker push ecommerce/order-service:${{ github.sha }}
    
    - name: Deploy to Kubernetes
      run: |
        kubectl set image deployment/user-service user-service=ecommerce/user-service:${{ github.sha }}
        kubectl set image deployment/product-service product-service=ecommerce/product-service:${{ github.sha }}
        kubectl set image deployment/order-service order-service=ecommerce/order-service:${{ github.sha }}
```

## 📈 Performance Optimization

### 1. Caching Strategy
```java
@Configuration
@EnableCaching
public class CacheConfig {
    
    @Bean
    public RedisCacheConfiguration cacheConfiguration() {
        return RedisCacheConfiguration.defaultCacheConfig()
            .entryTtl(Duration.ofMinutes(60))
            .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()))
            .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()));
    }
    
    @Bean
    public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
        return RedisCacheManager.builder(redisConnectionFactory)
            .cacheDefaults(cacheConfiguration())
            .withCacheConfiguration("products", 
                RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofMinutes(30)))
            .withCacheConfiguration("users", 
                RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofMinutes(15)))
            .build();
    }
}
```

### 2. Database Optimization
```java
@Entity
@Table(name = "products")
@Index(name = "idx_category_price", columns = {"category", "price"})
@Index(name = "idx_name_search", columns = {"name"})
public class Product {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String name;
    
    @Column(nullable = false)
    private String category;
    
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;
    
    @Column(columnDefinition = "TEXT")
    private String description;
    
    @Column(nullable = false)
    private Integer stock;
    
    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;
    
    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime updatedAt;
    
    // Getters, setters, and business methods
}
```

## 🔒 Security Implementation

### 1. JWT Authentication
```java
@Component
public class JwtTokenProvider {
    
    @Value("${jwt.secret}")
    private String jwtSecret;
    
    @Value("${jwt.expiration}")
    private long jwtExpiration;
    
    public String generateToken(User user) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpiration);
        
        return Jwts.builder()
            .setSubject(Long.toString(user.getId()))
            .claim("email", user.getEmail())
            .claim("role", user.getRole().name())
            .setIssuedAt(now)
            .setExpiration(expiryDate)
            .signWith(SignatureAlgorithm.HS512, jwtSecret)
            .compact();
    }
    
    public Long getUserIdFromToken(String token) {
        Claims claims = Jwts.parser()
            .setSigningKey(jwtSecret)
            .parseClaimsJws(token)
            .getBody();
        
        return Long.parseLong(claims.getSubject());
    }
    
    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }
}
```

### 2. Rate Limiting
```java
@Component
public class RateLimitingFilter implements WebFilter {
    
    private final RateLimiter rateLimiter;
    
    public RateLimitingFilter() {
        this.rateLimiter = RateLimiter.create(100.0); // 100 requests per second
    }
    
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        if (!rateLimiter.tryAcquire()) {
            exchange.getResponse().setStatusCode(HttpStatus.TOO_MANY_REQUESTS);
            return exchange.getResponse().setComplete();
        }
        
        return chain.filter(exchange);
    }
}
```

## 🎯 Learning Outcomes

By completing this project, you will have demonstrated:

1. **Microservices Architecture**: Understanding of distributed systems and service decomposition
2. **Spring Boot & Spring Cloud**: Mastery of modern Java frameworks
3. **Event-Driven Architecture**: Implementation of asynchronous communication patterns
4. **Containerization & Orchestration**: Docker and Kubernetes deployment
5. **Observability**: Monitoring, logging, and tracing in distributed systems
6. **Security**: JWT authentication, rate limiting, and secure communication
7. **Testing**: Unit, integration, and contract testing strategies
8. **CI/CD**: Automated deployment pipelines
9. **Performance**: Caching, database optimization, and scalability
10. **Resilience**: Circuit breakers, retry mechanisms, and fault tolerance

## 🚀 Getting Started

1. **Clone the repository structure**
2. **Set up development environment** (Docker, Kubernetes, IDE)
3. **Implement each microservice** following the provided patterns
4. **Set up infrastructure components** (Eureka, Config Server, etc.)
5. **Implement testing strategies** for each service
6. **Deploy to Kubernetes** using the provided manifests
7. **Monitor and optimize** the system performance

This project represents a production-ready microservices architecture that demonstrates expert-level Java development skills and modern software engineering practices.