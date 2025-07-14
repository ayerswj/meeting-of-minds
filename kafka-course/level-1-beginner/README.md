# Level 1: Kafka Beginner

## Learning Objectives

By the end of this level, you will be able to:
- Understand Kafka's core concepts and architecture
- Set up a basic Kafka cluster
- Create topics and manage partitions
- Write basic producers and consumers
- Understand the role of brokers, topics, and partitions
- Use Kafka command-line tools effectively

## Duration: 2-3 weeks

## Module 1: Introduction to Apache Kafka

### 1.1 What is Apache Kafka?
- **Learning Time**: 2-3 hours
- **Topics**:
  - History and background of Kafka
  - Why Kafka was created
  - Use cases and applications
  - Comparison with other messaging systems
- **Hands-on**: None
- **Assessment**: Quiz on Kafka fundamentals

### 1.2 Kafka Architecture Overview
- **Learning Time**: 3-4 hours
- **Topics**:
  - Distributed system concepts
  - Kafka cluster architecture
  - Brokers, topics, partitions, and replicas
  - Zookeeper's role (and its removal in newer versions)
  - Producer and consumer concepts
- **Hands-on**: Architecture diagram creation
- **Assessment**: Architecture quiz

## Module 2: Core Concepts

### 2.1 Topics and Partitions
- **Learning Time**: 4-5 hours
- **Topics**:
  - What are topics?
  - Partition concept and importance
  - Replication factor
  - Partition distribution across brokers
  - Topic configuration parameters
- **Hands-on**: Create topics with different configurations
- **Assessment**: Topic management exercises

### 2.2 Producers and Consumers
- **Learning Time**: 6-8 hours
- **Topics**:
  - Producer basics and configuration
  - Consumer groups and rebalancing
  - Offset management
  - Message delivery semantics
  - Basic producer/consumer patterns
- **Hands-on**: Write simple Java producer and consumer
- **Assessment**: Producer/consumer coding assignment

## Module 3: Getting Started

### 3.1 Kafka Installation and Setup
- **Learning Time**: 3-4 hours
- **Topics**:
  - System requirements
  - Installation methods (binary, Docker, package managers)
  - Basic configuration
  - Starting and stopping Kafka
  - Verifying installation
- **Hands-on**: Install Kafka on local machine
- **Assessment**: Setup verification

### 3.2 Command Line Tools
- **Learning Time**: 4-5 hours
- **Topics**:
  - kafka-topics.sh
  - kafka-console-producer.sh
  - kafka-console-consumer.sh
  - kafka-configs.sh
  - kafka-acls.sh (basic usage)
- **Hands-on**: Complete command-line tutorial
- **Assessment**: CLI proficiency test

## Module 4: Basic Operations

### 4.1 Topic Management
- **Learning Time**: 3-4 hours
- **Topics**:
  - Creating topics
  - Listing and describing topics
  - Modifying topic configurations
  - Deleting topics
  - Topic retention policies
- **Hands-on**: Topic management exercises
- **Assessment**: Topic management project

### 4.2 Basic Producer/Consumer Programming
- **Learning Time**: 8-10 hours
- **Topics**:
  - Java client library basics
  - Producer API usage
  - Consumer API usage
  - Error handling
  - Configuration options
- **Hands-on**: Build a simple messaging application
- **Assessment**: Coding project submission

## Module 5: Understanding Data Flow

### 5.1 Message Lifecycle
- **Learning Time**: 2-3 hours
- **Topics**:
  - How messages flow through Kafka
  - Storage and retrieval process
  - Log compaction concepts
  - Message ordering guarantees
- **Hands-on**: Trace message flow through system
- **Assessment**: Message flow quiz

### 5.2 Basic Monitoring
- **Learning Time**: 3-4 hours
- **Topics**:
  - JMX metrics overview
  - Basic monitoring tools
  - Key metrics to watch
  - Log analysis
- **Hands-on**: Set up basic monitoring
- **Assessment**: Monitoring setup verification

## Hands-on Labs

### Lab 1: First Steps with Kafka
- Set up a single-node Kafka cluster
- Create your first topic
- Send and receive messages using console tools

### Lab 2: Java Producer/Consumer
- Write a Java producer that sends messages
- Write a Java consumer that reads messages
- Implement basic error handling

### Lab 3: Topic Management
- Create topics with different configurations
- Modify topic settings
- Understand partition distribution

## Assessment

### Quiz 1: Fundamentals (20 questions)
- Kafka architecture
- Core concepts
- Basic terminology

### Quiz 2: Operations (15 questions)
- Command-line tools
- Topic management
- Basic configuration

### Project: Simple Messaging System
- Build a complete producer/consumer application
- Implement proper error handling
- Document your solution

## Resources

### Required Reading
- [Apache Kafka Documentation](https://kafka.apache.org/documentation/)
- [Kafka: The Definitive Guide](https://www.confluent.io/resources/kafka-the-definitive-guide/) (Chapters 1-3)

### Additional Resources
- [Confluent Platform Documentation](https://docs.confluent.io/)
- [Kafka Tutorials](https://kafka.apache.org/quickstart)

### Tools
- Apache Kafka 3.x
- Java 8 or higher
- Maven or Gradle
- Docker (optional)

## Next Steps

After completing Level 1, you should:
- Have a solid understanding of Kafka fundamentals
- Be able to set up and operate a basic Kafka cluster
- Write simple producer and consumer applications
- Use Kafka command-line tools effectively

Ready to move to **Level 2: Intermediate** where you'll learn advanced concepts and practical applications!