# Project 5: Microservices Architecture System

## Project Overview

Build a comprehensive microservices architecture system that demonstrates modern distributed system patterns, service communication, and scalable application design. This project will showcase real-world microservices implementation with multiple services, API gateway, service discovery, and load balancing.

## Learning Objectives

- Design and implement microservices architecture
- Master service-to-service communication patterns
- Implement API gateway and service discovery
- Practice distributed system patterns and resilience
- Understand load balancing and fault tolerance
- Apply event-driven architecture principles
- Implement distributed tracing and monitoring
- Master containerization and deployment strategies

## Architecture Overview

```
┌─────────────────┐    ┌─────────────────┐    ┌─────────────────┐
│   API Gateway   │    │  Load Balancer  │    │  Service Mesh   │
└─────────────────┘    └─────────────────┘    └─────────────────┘
         │                       │                       │
         └───────────────────────┼───────────────────────┘
                                 │
    ┌─────────────┬─────────────┼─────────────┬─────────────┐
    │             │             │             │             │
┌─────────┐ ┌─────────┐ ┌─────────┐ ┌─────────┐ ┌─────────┐
│  User   │ │ Product │ │ Order   │ │ Payment │ │  Auth   │
│Service  │ │Service  │ │Service  │ │Service  │ │Service  │
└─────────┘ └─────────┘ └─────────┘ └─────────┘ └─────────┘
    │             │             │             │             │
    └─────────────┴─────────────┴─────────────┴─────────────┘
                                 │
                    ┌─────────────────────────┐
                    │    Message Broker       │
                    │   (Event Bus)           │
                    └─────────────────────────┘
```

## Core Services

### 1. API Gateway Service
**Purpose**: Single entry point for all client requests, routing, authentication, and rate limiting.

**Key Features**:
- Request routing and load balancing
- Authentication and authorization
- Rate limiting and throttling
- Request/response transformation
- Caching and compression
- Circuit breaker implementation

**Technologies**:
- Spring Cloud Gateway
- Netflix Zuul (alternative)
- Resilience4j for circuit breakers
- Redis for caching

### 2. User Service
**Purpose**: Manage user accounts, profiles, and authentication.

**Key Features**:
- User registration and authentication
- Profile management
- Role-based access control
- User preferences and settings
- Account verification

**Technologies**:
- Spring Boot
- Spring Security
- JWT for authentication
- PostgreSQL for data storage
- Redis for session management

### 3. Product Service
**Purpose**: Manage product catalog, inventory, and pricing.

**Key Features**:
- Product catalog management
- Inventory tracking
- Price management
- Product search and filtering
- Category management

**Technologies**:
- Spring Boot
- Spring Data JPA
- Elasticsearch for search
- MongoDB for product data
- Redis for caching

### 4. Order Service
**Purpose**: Handle order processing, status tracking, and order history.

**Key Features**:
- Order creation and management
- Order status tracking
- Order history and analytics
- Order validation and processing
- Integration with payment service

**Technologies**:
- Spring Boot
- Spring Data JPA
- PostgreSQL for order data
- Apache Kafka for events
- Redis for order caching

### 5. Payment Service
**Purpose**: Process payments, handle payment gateways, and manage transactions.

**Key Features**:
- Payment processing
- Multiple payment methods
- Transaction management
- Payment security
- Refund processing

**Technologies**:
- Spring Boot
- Stripe/PayPal integration
- PostgreSQL for transactions
- Apache Kafka for events
- Encryption for sensitive data

### 6. Auth Service
**Purpose**: Centralized authentication and authorization.

**Key Features**:
- JWT token management
- OAuth2 implementation
- Session management
- Permission management
- SSO integration

**Technologies**:
- Spring Boot
- Spring Security OAuth2
- JWT tokens
- Redis for token storage
- PostgreSQL for user data

## Supporting Infrastructure

### Service Discovery (Eureka)
```java
@SpringBootApplication
@EnableEurekaServer
public class ServiceDiscoveryApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceDiscoveryApplication.class, args);
    }
}
```

### API Gateway Configuration
```yaml
spring:
  cloud:
    gateway:
      routes:
        - id: user-service
          uri: lb://user-service
          predicates:
            - Path=/api/users/**
          filters:
            - StripPrefix=1
            - name: CircuitBreaker
              args:
                name: user-service-circuit-breaker
                fallbackUri: forward:/fallback/user-service
```

### Load Balancer (Ribbon)
```java
@Configuration
public class LoadBalancerConfig {
    
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
    
    @Bean
    public IRule ribbonRule() {
        return new RoundRobinRule();
    }
}
```

## Service Communication Patterns

### 1. Synchronous Communication (REST)
```java
@Service
public class OrderService {
    
    @Autowired
    private RestTemplate restTemplate;
    
    public Order createOrder(OrderRequest request) {
        // Validate user
        User user = restTemplate.getForObject(
            "http://user-service/api/users/{id}", 
            User.class, 
            request.getUserId()
        );
        
        // Validate products
        List<Product> products = restTemplate.postForObject(
            "http://product-service/api/products/validate",
            request.getProductIds(),
            List.class
        );
        
        // Create order
        Order order = new Order(user, products);
        return orderRepository.save(order);
    }
}
```

### 2. Asynchronous Communication (Events)
```java
@Component
public class OrderEventPublisher {
    
    @Autowired
    private KafkaTemplate<String, OrderEvent> kafkaTemplate;
    
    public void publishOrderCreated(Order order) {
        OrderEvent event = new OrderEvent(
            EventType.ORDER_CREATED,
            order.getId(),
            order
        );
        kafkaTemplate.send("order-events", event);
    }
}

@Component
public class PaymentEventListener {
    
    @KafkaListener(topics = "order-events")
    public void handleOrderEvent(OrderEvent event) {
        if (event.getType() == EventType.ORDER_CREATED) {
            processPayment(event.getOrder());
        }
    }
}
```

### 3. Circuit Breaker Pattern
```java
@Service
public class ProductServiceClient {
    
    @CircuitBreaker(name = "product-service", fallbackMethod = "getProductFallback")
    public Product getProduct(Long id) {
        return restTemplate.getForObject(
            "http://product-service/api/products/{id}",
            Product.class,
            id
        );
    }
    
    public Product getProductFallback(Long id, Exception e) {
        return Product.builder()
            .id(id)
            .name("Product Unavailable")
            .price(BigDecimal.ZERO)
            .available(false)
            .build();
    }
}
```

## Data Management

### Database Per Service Pattern
```java
// User Service - PostgreSQL
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String email;
    private String passwordHash;
    // ... other fields
}

// Product Service - MongoDB
@Document(collection = "products")
public class Product {
    @Id
    private String id;
    private String name;
    private String description;
    private BigDecimal price;
    private int stockQuantity;
    // ... other fields
}

// Order Service - PostgreSQL
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private String status;
    private BigDecimal totalAmount;
    @OneToMany(cascade = CascadeType.ALL)
    private List<OrderItem> items;
    // ... other fields
}
```

### Event Sourcing and CQRS
```java
@Entity
@Table(name = "events")
public class DomainEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String aggregateId;
    private String eventType;
    private String eventData;
    private LocalDateTime timestamp;
    private Long version;
}

@Service
public class EventStore {
    
    @Autowired
    private EventRepository eventRepository;
    
    public void saveEvent(String aggregateId, String eventType, Object eventData) {
        DomainEvent event = new DomainEvent();
        event.setAggregateId(aggregateId);
        event.setEventType(eventType);
        event.setEventData(JsonUtils.toJson(eventData));
        event.setTimestamp(LocalDateTime.now());
        event.setVersion(getNextVersion(aggregateId));
        eventRepository.save(event);
    }
    
    public List<DomainEvent> getEvents(String aggregateId) {
        return eventRepository.findByAggregateIdOrderByVersion(aggregateId);
    }
}
```

## Security and Authentication

### JWT Token Management
```java
@Component
public class JwtTokenProvider {
    
    @Value("${jwt.secret}")
    private String jwtSecret;
    
    @Value("${jwt.expiration}")
    private long jwtExpiration;
    
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userDetails.getUsername());
    }
    
    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
            .setClaims(claims)
            .setSubject(subject)
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(new Date(System.currentTimeMillis() + jwtExpiration))
            .signWith(SignatureAlgorithm.HS512, jwtSecret)
            .compact();
    }
    
    public boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}
```

### API Gateway Security
```java
@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {
    
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    
    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            
            if (!request.getHeaders().containsKey("Authorization")) {
                return onError(exchange, "No Authorization header", HttpStatus.UNAUTHORIZED);
            }
            
            String authHeader = request.getHeaders().get("Authorization").get(0);
            if (!authHeader.startsWith("Bearer ")) {
                return onError(exchange, "Invalid Authorization header", HttpStatus.UNAUTHORIZED);
            }
            
            String token = authHeader.substring(7);
            
            try {
                if (!jwtTokenProvider.validateToken(token)) {
                    return onError(exchange, "Invalid token", HttpStatus.UNAUTHORIZED);
                }
            } catch (Exception e) {
                return onError(exchange, "Invalid token", HttpStatus.UNAUTHORIZED);
            }
            
            return chain.filter(exchange);
        };
    }
}
```

## Monitoring and Observability

### Distributed Tracing (Sleuth + Zipkin)
```java
@Configuration
public class TracingConfig {
    
    @Bean
    public Sampler defaultSampler() {
        return Sampler.ALWAYS_SAMPLE;
    }
}

@Component
public class TracingService {
    
    @Autowired
    private Tracer tracer;
    
    public void addCustomTag(String key, String value) {
        Span span = tracer.currentSpan();
        if (span != null) {
            span.tag(key, value);
        }
    }
    
    public void logEvent(String event) {
        Span span = tracer.currentSpan();
        if (span != null) {
            span.log(event);
        }
    }
}
```

### Health Checks and Metrics
```java
@Component
public class CustomHealthIndicator implements HealthIndicator {
    
    @Override
    public Health health() {
        try {
            // Check database connectivity
            // Check external service connectivity
            return Health.up()
                .withDetail("database", "UP")
                .withDetail("external-service", "UP")
                .build();
        } catch (Exception e) {
            return Health.down()
                .withDetail("error", e.getMessage())
                .build();
        }
    }
}

@RestController
@RequestMapping("/actuator")
public class MetricsController {
    
    @Autowired
    private MeterRegistry meterRegistry;
    
    @GetMapping("/metrics/custom")
    public Map<String, Object> getCustomMetrics() {
        Map<String, Object> metrics = new HashMap<>();
        metrics.put("active-users", meterRegistry.get("active.users").counter().count());
        metrics.put("orders-per-minute", meterRegistry.get("orders.per.minute").counter().count());
        return metrics;
    }
}
```

## Deployment and Containerization

### Docker Configuration
```dockerfile
# Dockerfile for User Service
FROM openjdk:11-jre-slim
WORKDIR /app
COPY target/user-service.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
```

### Docker Compose
```yaml
version: '3.8'
services:
  api-gateway:
    build: ./api-gateway
    ports:
      - "8080:8080"
    environment:
      - SPRING_PROFILES_ACTIVE=docker
    depends_on:
      - service-discovery
      
  user-service:
    build: ./user-service
    environment:
      - SPRING_PROFILES_ACTIVE=docker
    depends_on:
      - postgres
      - redis
      
  product-service:
    build: ./product-service
    environment:
      - SPRING_PROFILES_ACTIVE=docker
    depends_on:
      - mongodb
      - elasticsearch
      
  order-service:
    build: ./order-service
    environment:
      - SPRING_PROFILES_ACTIVE=docker
    depends_on:
      - postgres
      - kafka
      
  service-discovery:
    build: ./service-discovery
    ports:
      - "8761:8761"
      
  postgres:
    image: postgres:13
    environment:
      POSTGRES_DB: microservices
      POSTGRES_USER: postgres
      POSTGRES_PASSWORD: password
    volumes:
      - postgres-data:/var/lib/postgresql/data
      
  mongodb:
    image: mongo:4.4
    volumes:
      - mongo-data:/data/db
      
  redis:
    image: redis:6-alpine
    
  kafka:
    image: confluentinc/cp-kafka:latest
    environment:
      KAFKA_ZOOKEEPER_CONNECT: zookeeper:2181
      KAFKA_ADVERTISED_LISTENERS: PLAINTEXT://kafka:9092
    depends_on:
      - zookeeper
      
  zookeeper:
    image: confluentinc/cp-zookeeper:latest
    environment:
      ZOOKEEPER_CLIENT_PORT: 2181
      
  elasticsearch:
    image: elasticsearch:7.9.3
    environment:
      - discovery.type=single-node
    volumes:
      - elasticsearch-data:/usr/share/elasticsearch/data
      
volumes:
  postgres-data:
  mongo-data:
  elasticsearch-data:
```

## Testing Strategies

### Unit Testing
```java
@ExtendWith(MockitoExtension.class)
class OrderServiceTest {
    
    @Mock
    private OrderRepository orderRepository;
    
    @Mock
    private RestTemplate restTemplate;
    
    @InjectMocks
    private OrderService orderService;
    
    @Test
    void createOrder_ValidRequest_ReturnsOrder() {
        // Given
        OrderRequest request = new OrderRequest(1L, Arrays.asList(1L, 2L));
        User user = new User(1L, "test@example.com");
        Product product = new Product(1L, "Test Product", BigDecimal.TEN);
        
        when(restTemplate.getForObject(anyString(), eq(User.class), anyLong()))
            .thenReturn(user);
        when(restTemplate.postForObject(anyString(), any(), eq(List.class)))
            .thenReturn(Arrays.asList(product));
        when(orderRepository.save(any(Order.class)))
            .thenAnswer(invocation -> invocation.getArgument(0));
        
        // When
        Order result = orderService.createOrder(request);
        
        // Then
        assertNotNull(result);
        assertEquals(user, result.getUser());
        assertEquals(1, result.getItems().size());
        verify(orderRepository).save(any(Order.class));
    }
}
```

### Integration Testing
```java
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:application-test.yml")
class OrderServiceIntegrationTest {
    
    @Autowired
    private TestRestTemplate restTemplate;
    
    @Test
    void createOrder_ValidRequest_ReturnsOrder() {
        // Given
        OrderRequest request = new OrderRequest(1L, Arrays.asList(1L, 2L));
        
        // When
        ResponseEntity<Order> response = restTemplate.postForEntity(
            "/api/orders",
            request,
            Order.class
        );
        
        // Then
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertNotNull(response.getBody().getId());
    }
}
```

### Contract Testing (Pact)
```java
@ExtendWith(PactConsumerTestExt.class)
class OrderServiceContractTest {
    
    @Pact(consumer = "order-service", provider = "user-service")
    public RequestResponsePact getUserPact(PactDslWithProvider builder) {
        return builder
            .given("user exists")
            .uponReceiving("get user by id")
            .path("/api/users/1")
            .method("GET")
            .willRespondWith()
            .status(200)
            .body(new PactDslJsonBody()
                .numberType("id", 1L)
                .stringType("email", "test@example.com")
                .stringType("username", "testuser"))
            .toPact();
    }
    
    @Test
    @PactTestFor(pactMethod = "getUserPact")
    void getUser_ValidId_ReturnsUser(MockServer mockServer) {
        // Given
        String url = mockServer.getUrl() + "/api/users/1";
        
        // When
        User user = restTemplate.getForObject(url, User.class);
        
        // Then
        assertNotNull(user);
        assertEquals(1L, user.getId());
        assertEquals("test@example.com", user.getEmail());
    }
}
```

## Performance and Scalability

### Caching Strategy
```java
@Configuration
@EnableCaching
public class CacheConfig {
    
    @Bean
    public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig()
            .entryTtl(Duration.ofMinutes(30))
            .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()))
            .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()));
        
        return RedisCacheManager.builder(redisConnectionFactory)
            .cacheDefaults(config)
            .build();
    }
}

@Service
public class ProductService {
    
    @Cacheable(value = "products", key = "#id")
    public Product getProduct(Long id) {
        return productRepository.findById(id)
            .orElseThrow(() -> new ProductNotFoundException(id));
    }
    
    @CacheEvict(value = "products", key = "#product.id")
    public Product updateProduct(Product product) {
        return productRepository.save(product);
    }
}
```

### Rate Limiting
```java
@Component
public class RateLimitingFilter extends AbstractGatewayFilterFactory<RateLimitingFilter.Config> {
    
    private final RateLimiter rateLimiter = RateLimiter.create(100.0); // 100 requests per second
    
    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            if (!rateLimiter.tryAcquire()) {
                return onError(exchange, "Rate limit exceeded", HttpStatus.TOO_MANY_REQUESTS);
            }
            return chain.filter(exchange);
        };
    }
}
```

## Project Structure

```
microservices-architecture/
├── api-gateway/
│   ├── src/main/java/
│   ├── src/main/resources/
│   └── Dockerfile
├── user-service/
│   ├── src/main/java/
│   ├── src/main/resources/
│   └── Dockerfile
├── product-service/
│   ├── src/main/java/
│   ├── src/main/resources/
│   └── Dockerfile
├── order-service/
│   ├── src/main/java/
│   ├── src/main/resources/
│   └── Dockerfile
├── payment-service/
│   ├── src/main/java/
│   ├── src/main/resources/
│   └── Dockerfile
├── auth-service/
│   ├── src/main/java/
│   ├── src/main/resources/
│   └── Dockerfile
├── service-discovery/
│   ├── src/main/java/
│   ├── src/main/resources/
│   └── Dockerfile
├── shared/
│   ├── common/
│   ├── dto/
│   └── utils/
├── docker-compose.yml
├── docker-compose.override.yml
├── README.md
└── pom.xml
```

## Implementation Steps

### Phase 1: Foundation (Week 1-2)
1. Set up service discovery with Eureka
2. Create API gateway with Spring Cloud Gateway
3. Implement basic user service with authentication
4. Set up basic database connections

### Phase 2: Core Services (Week 3-4)
1. Implement product service with catalog management
2. Create order service with order processing
3. Build payment service with payment processing
4. Implement auth service with JWT tokens

### Phase 3: Communication (Week 5-6)
1. Implement synchronous communication between services
2. Set up asynchronous communication with Kafka
3. Implement circuit breaker patterns
4. Add load balancing with Ribbon

### Phase 4: Advanced Features (Week 7-8)
1. Implement distributed tracing with Sleuth
2. Add monitoring and health checks
3. Implement caching strategies
4. Add security and rate limiting

### Phase 5: Deployment (Week 9-10)
1. Containerize all services with Docker
2. Set up Docker Compose for local development
3. Implement CI/CD pipeline
4. Add comprehensive testing

## Key Features to Implement

1. **Service Discovery**: Automatic service registration and discovery
2. **Load Balancing**: Distribute traffic across service instances
3. **Circuit Breaker**: Handle service failures gracefully
4. **Distributed Tracing**: Track requests across services
5. **Event-Driven Architecture**: Loose coupling with events
6. **Security**: JWT authentication and authorization
7. **Monitoring**: Health checks and metrics
8. **Caching**: Redis-based caching for performance
9. **Rate Limiting**: Protect services from overload
10. **Containerization**: Docker-based deployment

## Success Criteria

- All services communicate successfully
- API gateway routes requests correctly
- Service discovery works automatically
- Circuit breakers handle failures gracefully
- Distributed tracing provides visibility
- Security is implemented properly
- Performance meets requirements
- System is containerized and deployable
- Comprehensive test coverage
- Documentation is complete

## Next Steps

After completing this project, explore:
- Kubernetes deployment
- Service mesh (Istio)
- Advanced monitoring (Prometheus, Grafana)
- Chaos engineering
- Blue-green deployments
- Canary releases
- Multi-region deployment

This microservices architecture project provides a solid foundation for understanding distributed systems and modern application architecture patterns.