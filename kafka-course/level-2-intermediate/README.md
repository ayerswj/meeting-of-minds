# Level 2: Kafka Intermediate

## Learning Objectives

By the end of this level, you will be able to:
- Implement advanced producer and consumer patterns
- Use Kafka Streams for real-time data processing
- Manage schemas with Schema Registry
- Set up monitoring and alerting systems
- Handle common production scenarios
- Implement basic security measures

## Duration: 3-4 weeks

## Prerequisites
- Complete Level 1: Beginner
- Solid understanding of Java programming
- Basic knowledge of distributed systems

## Module 1: Advanced Producer Patterns

### 1.1 Producer Configuration Deep Dive
- **Learning Time**: 4-5 hours
- **Topics**:
  - Acks configuration and durability
  - Retry policies and backoff strategies
  - Batch size and linger.ms optimization
  - Compression types and trade-offs
  - Idempotent producers
- **Hands-on**: Configure producers for different scenarios
- **Assessment**: Producer configuration quiz

### 1.2 Advanced Producer Features
- **Learning Time**: 6-8 hours
- **Topics**:
  - Custom partitioners
  - Interceptors
  - Serializers and deserializers
  - Transactional producers
  - Exactly-once semantics
- **Hands-on**: Implement custom partitioner and interceptor
- **Assessment**: Advanced producer coding project

## Module 2: Advanced Consumer Patterns

### 2.1 Consumer Configuration and Tuning
- **Learning Time**: 5-6 hours
- **Topics**:
  - Consumer group management
  - Rebalancing strategies
  - Offset commit strategies
  - Fetch size and poll interval tuning
  - Consumer lag monitoring
- **Hands-on**: Optimize consumer performance
- **Assessment**: Consumer tuning exercises

### 2.2 Advanced Consumer Features
- **Learning Time**: 6-8 hours
- **Topics**:
  - Custom deserializers
  - Consumer interceptors
  - Manual partition assignment
  - Seek operations
  - Consumer rebalancing listeners
- **Hands-on**: Implement custom consumer features
- **Assessment**: Advanced consumer project

## Module 3: Kafka Streams

### 3.1 Introduction to Stream Processing
- **Learning Time**: 4-5 hours
- **Topics**:
  - Stream processing concepts
  - Kafka Streams vs other stream processing frameworks
  - Topology and processing nodes
  - Stateful vs stateless operations
- **Hands-on**: Create simple stream processing application
- **Assessment**: Stream processing concepts quiz

### 3.2 Kafka Streams API
- **Learning Time**: 8-10 hours
- **Topics**:
  - KStream and KTable concepts
  - Transformations (map, filter, join, aggregate)
  - Windowing operations
  - State stores
  - Interactive queries
- **Hands-on**: Build real-time analytics application
- **Assessment**: Stream processing application

### 3.3 Advanced Stream Processing
- **Learning Time**: 6-8 hours
- **Topics**:
  - Stream partitioning
  - Repartitioning strategies
  - Fault tolerance and recovery
  - Testing stream applications
  - Performance optimization
- **Hands-on**: Optimize and test stream application
- **Assessment**: Advanced stream processing project

## Module 4: Schema Management

### 4.1 Schema Registry Introduction
- **Learning Time**: 3-4 hours
- **Topics**:
  - Why schema management is important
  - Schema Registry architecture
  - Schema evolution strategies
  - Compatibility modes
- **Hands-on**: Set up Schema Registry
- **Assessment**: Schema management quiz

### 4.2 Working with Schemas
- **Learning Time**: 6-8 hours
- **Topics**:
  - Avro schema definition
  - Schema serialization/deserialization
  - Schema versioning
  - Schema compatibility rules
  - Schema migration strategies
- **Hands-on**: Implement schema-aware producers/consumers
- **Assessment**: Schema implementation project

## Module 5: Monitoring and Observability

### 5.1 Kafka Metrics and Monitoring
- **Learning Time**: 5-6 hours
- **Topics**:
  - JMX metrics categories
  - Key performance indicators
  - Consumer lag monitoring
  - Broker health metrics
  - Topic-level metrics
- **Hands-on**: Set up comprehensive monitoring
- **Assessment**: Monitoring setup verification

### 5.2 Alerting and Dashboards
- **Learning Time**: 4-5 hours
- **Topics**:
  - Alerting strategies
  - Prometheus and Grafana setup
  - Custom dashboards
  - SLO/SLA monitoring
  - Incident response procedures
- **Hands-on**: Create monitoring dashboards
- **Assessment**: Alerting system implementation

## Module 6: Basic Security

### 6.1 Authentication and Authorization
- **Learning Time**: 6-8 hours
- **Topics**:
  - SASL authentication mechanisms
  - SSL/TLS encryption
  - ACL (Access Control Lists)
  - User management
  - Security best practices
- **Hands-on**: Implement basic security
- **Assessment**: Security configuration project

### 6.2 Security in Practice
- **Learning Time**: 4-5 hours
- **Topics**:
  - Secure client configuration
  - Certificate management
  - Security monitoring
  - Common security pitfalls
  - Compliance considerations
- **Hands-on**: Secure a Kafka cluster
- **Assessment**: Security audit project

## Module 7: Production Readiness

### 7.1 Cluster Planning and Sizing
- **Learning Time**: 4-5 hours
- **Topics**:
  - Capacity planning
  - Hardware requirements
  - Network considerations
  - Storage planning
  - Scaling strategies
- **Hands-on**: Design cluster architecture
- **Assessment**: Cluster design project

### 7.2 Operational Best Practices
- **Learning Time**: 5-6 hours
- **Topics**:
  - Backup and recovery
  - Rolling upgrades
  - Configuration management
  - Log management
  - Operational procedures
- **Hands-on**: Implement operational procedures
- **Assessment**: Operations documentation

## Hands-on Labs

### Lab 1: Advanced Producer/Consumer
- Implement idempotent producer
- Create custom partitioner
- Build consumer with manual offset management

### Lab 2: Real-time Analytics with Kafka Streams
- Build a real-time analytics pipeline
- Implement windowing operations
- Create interactive queries

### Lab 3: Schema Management
- Set up Schema Registry
- Implement schema evolution
- Handle schema compatibility

### Lab 4: Monitoring and Alerting
- Set up Prometheus and Grafana
- Create custom dashboards
- Implement alerting rules

### Lab 5: Security Implementation
- Configure SASL authentication
- Set up SSL/TLS encryption
- Implement ACLs

## Assessment

### Quiz 1: Advanced Concepts (25 questions)
- Producer/consumer patterns
- Stream processing concepts
- Schema management

### Quiz 2: Operations and Security (20 questions)
- Monitoring and alerting
- Security concepts
- Production best practices

### Project 1: Real-time Data Pipeline
- Build a complete stream processing application
- Implement schema management
- Add monitoring and alerting

### Project 2: Production-Ready Cluster
- Design and implement a secure, monitored cluster
- Document operational procedures
- Create disaster recovery plan

## Resources

### Required Reading
- [Kafka Streams Documentation](https://kafka.apache.org/documentation/streams/)
- [Schema Registry Documentation](https://docs.confluent.io/platform/current/schema-registry/index.html)
- [Kafka: The Definitive Guide](https://www.confluent.io/resources/kafka-the-definitive-guide/) (Chapters 4-8)

### Additional Resources
- [Confluent Stream Processing](https://docs.confluent.io/platform/current/streams/index.html)
- [Kafka Streams Examples](https://github.com/confluentinc/kafka-streams-examples)
- [Monitoring Kafka](https://docs.confluent.io/platform/current/kafka/monitoring.html)

### Tools
- Apache Kafka 3.x
- Schema Registry
- Kafka Streams
- Prometheus
- Grafana
- Java 8 or higher
- Maven or Gradle

## Next Steps

After completing Level 2, you should:
- Be proficient with advanced producer/consumer patterns
- Understand stream processing with Kafka Streams
- Be able to manage schemas effectively
- Have experience with monitoring and basic security
- Be ready for production deployments

Ready to move to **Level 3: Advanced** where you'll learn performance tuning and enterprise features!