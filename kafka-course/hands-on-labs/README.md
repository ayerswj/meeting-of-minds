# Hands-on Labs: Kafka Practical Exercises

This directory contains hands-on labs and practical exercises for all levels of the Kafka course. Each lab is designed to reinforce theoretical concepts with real-world practice.

## Lab Structure

```
hands-on-labs/
├── level-1-labs/
├── level-2-labs/
├── level-3-labs/
├── level-4-labs/
├── common/
└── solutions/
```

## Prerequisites

Before starting any lab, ensure you have:

- **Hardware Requirements**:
  - Minimum 8GB RAM
  - 20GB free disk space
  - Multi-core processor

- **Software Requirements**:
  - Java 8 or higher
  - Docker and Docker Compose
  - Git
  - Maven or Gradle
  - IDE (IntelliJ IDEA, Eclipse, or VS Code)

- **Network Requirements**:
  - Internet connection for downloading dependencies
  - Ability to run containers

## Lab Environment Setup

### Option 1: Local Development Environment
```bash
# Install Java
sudo apt-get install openjdk-11-jdk

# Install Docker
curl -fsSL https://get.docker.com -o get-docker.sh
sudo sh get-docker.sh

# Install Docker Compose
sudo curl -L "https://github.com/docker/compose/releases/download/1.29.2/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose
sudo chmod +x /usr/local/bin/docker-compose

# Install Maven
sudo apt-get install maven
```

### Option 2: Docker Environment
```bash
# Clone the course repository
git clone <repository-url>
cd kafka-course

# Start the lab environment
docker-compose up -d
```

## Level 1 Labs: Beginner

### Lab 1.1: First Steps with Kafka
**Duration**: 2-3 hours
**Objective**: Set up a basic Kafka cluster and send/receive messages

**Tasks**:
1. Install and configure Kafka
2. Start Zookeeper and Kafka broker
3. Create your first topic
4. Send messages using console producer
5. Consume messages using console consumer
6. Explore topic configuration

**Files**:
- `level-1-labs/lab-1.1-first-steps/`
- `level-1-labs/lab-1.1-first-steps/README.md`
- `level-1-labs/lab-1.1-first-steps/docker-compose.yml`
- `level-1-labs/lab-1.1-first-steps/scripts/`

### Lab 1.2: Java Producer and Consumer
**Duration**: 4-5 hours
**Objective**: Write Java applications to produce and consume messages

**Tasks**:
1. Set up Java project with Maven
2. Implement basic producer
3. Implement basic consumer
4. Add error handling
5. Test with different message types
6. Implement consumer group

**Files**:
- `level-1-labs/lab-1.2-java-basics/`
- `level-1-labs/lab-1.2-java-basics/pom.xml`
- `level-1-labs/lab-1.2-java-basics/src/`

### Lab 1.3: Topic Management
**Duration**: 2-3 hours
**Objective**: Learn topic management and partitioning

**Tasks**:
1. Create topics with different configurations
2. Explore partition distribution
3. Modify topic configurations
4. Understand replication
5. Monitor topic metrics

**Files**:
- `level-1-labs/lab-1.3-topic-management/`
- `level-1-labs/lab-1.3-topic-management/scripts/`

## Level 2 Labs: Intermediate

### Lab 2.1: Advanced Producer/Consumer
**Duration**: 6-8 hours
**Objective**: Implement advanced producer and consumer patterns

**Tasks**:
1. Implement idempotent producer
2. Create custom partitioner
3. Build consumer with manual offset management
4. Implement custom serializers
5. Add interceptors
6. Handle rebalancing

**Files**:
- `level-2-labs/lab-2.1-advanced-pc/`
- `level-2-labs/lab-2.1-advanced-pc/pom.xml`
- `level-2-labs/lab-2.1-advanced-pc/src/`

### Lab 2.2: Kafka Streams Application
**Duration**: 8-10 hours
**Objective**: Build real-time analytics with Kafka Streams

**Tasks**:
1. Set up Kafka Streams project
2. Implement word count application
3. Add windowing operations
4. Create interactive queries
5. Implement state stores
6. Add testing

**Files**:
- `level-2-labs/lab-2.2-kafka-streams/`
- `level-2-labs/lab-2.2-kafka-streams/pom.xml`
- `level-2-labs/lab-2.2-kafka-streams/src/`

### Lab 2.3: Schema Registry
**Duration**: 4-5 hours
**Objective**: Implement schema management with Schema Registry

**Tasks**:
1. Set up Schema Registry
2. Define Avro schemas
3. Implement schema-aware producers/consumers
4. Test schema evolution
5. Handle schema compatibility

**Files**:
- `level-2-labs/lab-2.3-schema-registry/`
- `level-2-labs/lab-2.3-schema-registry/docker-compose.yml`
- `level-2-labs/lab-2.3-schema-registry/schemas/`

### Lab 2.4: Monitoring and Alerting
**Duration**: 6-8 hours
**Objective**: Set up comprehensive monitoring

**Tasks**:
1. Configure JMX metrics
2. Set up Prometheus
3. Create Grafana dashboards
4. Implement alerting rules
5. Monitor consumer lag
6. Set up log aggregation

**Files**:
- `level-2-labs/lab-2.4-monitoring/`
- `level-2-labs/lab-2.4-monitoring/docker-compose.yml`
- `level-2-labs/lab-2.4-monitoring/grafana/`

## Level 3 Labs: Advanced

### Lab 3.1: Performance Optimization
**Duration**: 8-10 hours
**Objective**: Optimize Kafka for high performance

**Tasks**:
1. Tune broker performance
2. Optimize producer configuration
3. Tune consumer performance
4. Benchmark different configurations
5. Analyze performance metrics
6. Document optimization strategies

**Files**:
- `level-3-labs/lab-3.1-performance/`
- `level-3-labs/lab-3.1-performance/scripts/`
- `level-3-labs/lab-3.1-performance/configs/`

### Lab 3.2: Security Implementation
**Duration**: 8-10 hours
**Objective**: Implement comprehensive security

**Tasks**:
1. Set up SSL/TLS encryption
2. Configure SASL authentication
3. Implement ACLs
4. Set up audit logging
5. Test security measures
6. Document security procedures

**Files**:
- `level-3-labs/lab-3.2-security/`
- `level-3-labs/lab-3.2-security/docker-compose.yml`
- `level-3-labs/lab-3.2-security/certs/`

### Lab 3.3: Multi-Datacenter Setup
**Duration**: 10-12 hours
**Objective**: Deploy MirrorMaker 2.0

**Tasks**:
1. Set up multi-DC environment
2. Configure MirrorMaker 2.0
3. Test replication
4. Implement failover
5. Monitor replication lag
6. Test disaster recovery

**Files**:
- `level-3-labs/lab-3.3-multi-dc/`
- `level-3-labs/lab-3.3-multi-dc/docker-compose.yml`
- `level-3-labs/lab-3.3-multi-dc/configs/`

## Level 4 Labs: Expert

### Lab 4.1: Custom Connector Development
**Duration**: 12-15 hours
**Objective**: Develop custom Kafka Connect connector

**Tasks**:
1. Understand Connect framework
2. Design connector architecture
3. Implement source connector
4. Implement sink connector
5. Add error handling
6. Test and package connector

**Files**:
- `level-4-labs/lab-4.1-custom-connector/`
- `level-4-labs/lab-4.1-custom-connector/pom.xml`
- `level-4-labs/lab-4.1-custom-connector/src/`

### Lab 4.2: Large-Scale Architecture
**Duration**: 15-20 hours
**Objective**: Design and implement large-scale system

**Tasks**:
1. Design event-driven architecture
2. Implement microservices
3. Set up CQRS pattern
4. Implement event sourcing
5. Add monitoring and alerting
6. Performance test the system

**Files**:
- `level-4-labs/lab-4.2-large-scale/`
- `level-4-labs/lab-4.2-large-scale/docker-compose.yml`
- `level-4-labs/lab-4.2-large-scale/services/`

### Lab 4.3: Advanced Troubleshooting
**Duration**: 10-12 hours
**Objective**: Debug complex production issues

**Tasks**:
1. Simulate production issues
2. Use debugging tools
3. Analyze logs and metrics
4. Conduct root cause analysis
5. Implement fixes
6. Document procedures

**Files**:
- `level-4-labs/lab-4.3-troubleshooting/`
- `level-4-labs/lab-4.3-troubleshooting/scripts/`
- `level-4-labs/lab-4.3-troubleshooting/scenarios/`

## Common Resources

### Docker Compose Templates
- `common/docker-compose-templates/`
- Base Kafka cluster setup
- Monitoring stack setup
- Security setup

### Scripts and Utilities
- `common/scripts/`
- Setup scripts
- Utility functions
- Testing helpers

### Configuration Templates
- `common/configs/`
- Broker configurations
- Client configurations
- Monitoring configurations

## Lab Completion Guidelines

### For Each Lab:
1. **Read the README** thoroughly before starting
2. **Follow the setup instructions** exactly
3. **Complete all tasks** in the specified order
4. **Document your progress** and any issues encountered
5. **Test your solutions** thoroughly
6. **Submit your work** for review

### Assessment Criteria:
- **Functionality**: Does the solution work as expected?
- **Code Quality**: Is the code well-structured and documented?
- **Understanding**: Can you explain your solution?
- **Problem Solving**: How did you handle challenges?

## Getting Help

### Documentation:
- Each lab includes detailed README files
- Code comments explain complex sections
- Configuration files are documented

### Troubleshooting:
- Common issues and solutions are documented
- Debug scripts are provided
- Log analysis guides are included

### Support:
- Check the troubleshooting section first
- Review the solutions directory (after attempting)
- Ask questions in the course forum

## Lab Environment Cleanup

After completing labs, clean up your environment:

```bash
# Stop all containers
docker-compose down

# Remove volumes (if needed)
docker-compose down -v

# Clean up Docker images
docker system prune -a

# Remove downloaded files
rm -rf kafka-course/downloads/
```

## Next Steps

After completing the labs:
1. Review your solutions against the provided solutions
2. Reflect on what you learned
3. Apply concepts to your own projects
4. Continue with the next level
5. Share your experiences with the community

Happy learning! 🚀