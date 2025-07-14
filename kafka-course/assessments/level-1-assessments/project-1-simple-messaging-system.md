# Level 1 Project: Simple Messaging System

## Project Overview

Build a complete messaging system using Apache Kafka that demonstrates your understanding of fundamental Kafka concepts. This project will test your ability to set up Kafka, create topics, implement producers and consumers, and handle real-world scenarios.

## 🎯 Learning Objectives

By completing this project, you will demonstrate:
- Ability to set up and configure a Kafka cluster
- Understanding of topic management and partitioning
- Proficiency in implementing Java producers and consumers
- Knowledge of error handling and resource management
- Skills in testing and validating Kafka applications
- Ability to document and present your solution

## 📋 Project Requirements

### Core Requirements

#### 1. Kafka Cluster Setup
- Set up a single-node Kafka cluster
- Configure appropriate settings for development
- Verify cluster health and functionality
- Document your setup process

#### 2. Topic Management
- Create at least 3 different topics with different configurations:
  - `user-events` (6 partitions, 1 replication factor)
  - `system-logs` (3 partitions, 1 replication factor, 1-hour retention)
  - `notifications` (2 partitions, 1 replication factor, compacted)
- Implement topic monitoring and health checks
- Document topic configurations and rationale

#### 3. Message Producer
Create a Java producer application that:
- Sends different types of messages to appropriate topics
- Implements proper error handling and retry logic
- Uses appropriate serialization (String, JSON, or custom)
- Includes configuration for performance optimization
- Handles different message delivery semantics (sync/async)

#### 4. Message Consumer
Create a Java consumer application that:
- Consumes messages from all topics
- Implements consumer groups for scalability
- Handles different offset management strategies
- Includes proper error handling and recovery
- Processes messages based on their type and content

#### 5. Message Types and Processing
Implement support for at least 3 different message types:
- **User Events**: User registration, login, logout events
- **System Logs**: Application logs, error logs, performance metrics
- **Notifications**: Email notifications, SMS alerts, push notifications

### Advanced Requirements

#### 6. Error Handling and Resilience
- Implement comprehensive error handling for network issues
- Add retry mechanisms with exponential backoff
- Handle message processing failures gracefully
- Implement dead letter queue for failed messages
- Add circuit breaker patterns for external dependencies

#### 7. Monitoring and Observability
- Add logging with appropriate log levels
- Implement metrics collection (message count, latency, errors)
- Create health check endpoints
- Add monitoring dashboards (optional)
- Implement alerting for critical failures

#### 8. Testing and Validation
- Unit tests for producer and consumer logic
- Integration tests for end-to-end message flow
- Performance tests to measure throughput
- Load tests to verify system behavior under stress
- Documentation of test results and performance metrics

## 🛠️ Technical Specifications

### Technology Stack
- **Apache Kafka**: 3.x version
- **Java**: 8 or higher
- **Maven**: For build management
- **Logging**: SLF4J + Logback
- **Testing**: JUnit 5
- **JSON Processing**: Jackson (optional)

### Project Structure
```
simple-messaging-system/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/kafka/project/
│   │   │       ├── producer/
│   │   │       ├── consumer/
│   │   │       ├── model/
│   │   │       ├── config/
│   │   │       └── util/
│   │   └── resources/
│   └── test/
│       └── java/
├── config/
├── scripts/
├── docs/
├── pom.xml
└── README.md
```

### Message Schema Examples

#### User Event
```json
{
  "eventId": "uuid-123",
  "eventType": "USER_LOGIN",
  "userId": "user-456",
  "timestamp": "2024-01-01T12:00:00Z",
  "metadata": {
    "ipAddress": "192.168.1.1",
    "userAgent": "Mozilla/5.0..."
  }
}
```

#### System Log
```json
{
  "logId": "log-789",
  "level": "ERROR",
  "message": "Database connection failed",
  "timestamp": "2024-01-01T12:00:00Z",
  "service": "user-service",
  "stackTrace": "..."
}
```

#### Notification
```json
{
  "notificationId": "notif-101",
  "type": "EMAIL",
  "recipient": "user@example.com",
  "subject": "Welcome to our platform",
  "content": "Thank you for registering!",
  "timestamp": "2024-01-01T12:00:00Z"
}
```

## 📊 Evaluation Criteria

### Functionality (40%)
- **Cluster Setup**: Proper Kafka installation and configuration
- **Topic Management**: Correct topic creation and configuration
- **Producer Implementation**: Working producer with error handling
- **Consumer Implementation**: Working consumer with group management
- **Message Processing**: Correct handling of different message types

### Code Quality (30%)
- **Architecture**: Clean, modular design
- **Error Handling**: Comprehensive error handling and recovery
- **Resource Management**: Proper resource cleanup and management
- **Documentation**: Clear code comments and documentation
- **Testing**: Adequate test coverage

### Performance (20%)
- **Throughput**: Ability to handle reasonable message volumes
- **Latency**: Acceptable message processing times
- **Resource Usage**: Efficient memory and CPU usage
- **Scalability**: Design that supports horizontal scaling

### Documentation (10%)
- **Setup Instructions**: Clear installation and setup guide
- **API Documentation**: Well-documented interfaces
- **User Guide**: Instructions for running and using the system
- **Architecture Documentation**: System design and rationale

## 🚀 Implementation Guide

### Phase 1: Setup and Configuration (Week 1)
1. **Environment Setup**
   - Install and configure Kafka
   - Set up development environment
   - Create project structure

2. **Topic Creation**
   - Create required topics with appropriate configurations
   - Test topic operations
   - Document topic settings

3. **Basic Producer/Consumer**
   - Implement basic producer and consumer
   - Test message sending and receiving
   - Verify basic functionality

### Phase 2: Core Implementation (Week 2)
1. **Message Models**
   - Define message schemas and models
   - Implement serialization/deserialization
   - Add validation logic

2. **Enhanced Producer**
   - Add different message types
   - Implement error handling
   - Add performance optimizations

3. **Enhanced Consumer**
   - Implement consumer groups
   - Add message processing logic
   - Handle different message types

### Phase 3: Advanced Features (Week 3)
1. **Error Handling**
   - Implement retry mechanisms
   - Add dead letter queue
   - Handle edge cases

2. **Monitoring**
   - Add logging and metrics
   - Implement health checks
   - Create monitoring dashboards

3. **Testing**
   - Write unit tests
   - Implement integration tests
   - Perform performance testing

### Phase 4: Documentation and Delivery (Week 4)
1. **Documentation**
   - Complete API documentation
   - Write user guides
   - Create architecture documentation

2. **Final Testing**
   - End-to-end testing
   - Performance validation
   - Bug fixes and improvements

3. **Project Submission**
   - Prepare presentation
   - Create demo
   - Submit final deliverables

## 📝 Deliverables

### Required Files
1. **Source Code**
   - Complete Java source code
   - Maven project with dependencies
   - Configuration files

2. **Documentation**
   - `README.md` with setup instructions
   - `ARCHITECTURE.md` with system design
   - `API.md` with interface documentation
   - `TESTING.md` with test results

3. **Scripts and Tools**
   - Setup scripts for Kafka
   - Build and run scripts
   - Testing scripts

4. **Configuration**
   - Kafka configuration files
   - Application configuration
   - Logging configuration

### Optional Enhancements
- Docker containerization
- CI/CD pipeline
- Monitoring dashboards
- Performance benchmarks
- Security implementation

## 🧪 Testing Requirements

### Unit Tests
- Producer functionality tests
- Consumer functionality tests
- Message processing tests
- Error handling tests
- Configuration tests

### Integration Tests
- End-to-end message flow
- Topic creation and management
- Consumer group behavior
- Error recovery scenarios

### Performance Tests
- Throughput measurement
- Latency measurement
- Resource usage monitoring
- Load testing

### Manual Testing
- Setup and installation verification
- Message sending and receiving
- Error scenario testing
- Configuration changes

## 📊 Success Metrics

### Functional Requirements
- [ ] All topics created successfully
- [ ] Producer sends messages to correct topics
- [ ] Consumer receives and processes messages
- [ ] Error handling works correctly
- [ ] Consumer groups function properly

### Performance Requirements
- [ ] Can handle 1000+ messages per second
- [ ] Message latency < 100ms
- [ ] Memory usage < 512MB
- [ ] CPU usage < 50% under normal load

### Quality Requirements
- [ ] Code coverage > 80%
- [ ] All tests pass
- [ ] No critical bugs
- [ ] Documentation complete

## 🔧 Troubleshooting Guide

### Common Issues
1. **Kafka Connection Issues**
   - Check if Kafka is running
   - Verify bootstrap servers configuration
   - Check network connectivity

2. **Topic Creation Failures**
   - Verify broker configuration
   - Check replication factor vs available brokers
   - Review topic naming conventions

3. **Consumer Group Issues**
   - Check group ID configuration
   - Verify partition assignment
   - Review offset management

4. **Performance Problems**
   - Monitor broker resources
   - Check producer/consumer configuration
   - Review message size and frequency

## 📚 Resources

### Documentation
- [Apache Kafka Documentation](https://kafka.apache.org/documentation/)
- [Kafka Producer API](https://kafka.apache.org/documentation/#producerapi)
- [Kafka Consumer API](https://kafka.apache.org/documentation/#consumerapi)

### Examples and Tutorials
- [Kafka Quick Start](https://kafka.apache.org/quickstart)
- [Kafka Java Examples](https://github.com/apache/kafka/tree/trunk/examples)
- [Confluent Platform Examples](https://github.com/confluentinc/examples)

### Tools and Utilities
- [Kafka Tool](https://www.kafkatool.com/)
- [Kafka UI](https://github.com/provectus/kafka-ui)
- [JMX Exporter](https://github.com/prometheus/jmx_exporter)

## 🎯 Submission Guidelines

### Submission Format
- GitHub repository with complete source code
- Comprehensive README with setup instructions
- Documentation in Markdown format
- Video demo (optional but recommended)

### Evaluation Process
1. **Code Review**: Technical implementation assessment
2. **Functionality Test**: End-to-end system testing
3. **Documentation Review**: Completeness and clarity
4. **Presentation**: Live demonstration and Q&A

### Grading Rubric
- **A (90-100%)**: Exceptional implementation with all requirements met
- **B (80-89%)**: Good implementation with minor issues
- **C (70-79%)**: Satisfactory implementation with some gaps
- **D (60-69%)**: Basic implementation with significant issues
- **F (<60%)**: Incomplete or non-functional implementation

## 🏆 Project Showcase

The best projects will be:
- Featured in the course showcase
- Used as examples for future students
- Recommended for portfolio inclusion
- Considered for open-source contribution

---

**Good luck with your project!** 🚀

Remember: This project is designed to be challenging but achievable. Take your time, plan carefully, and don't hesitate to ask for help when needed.