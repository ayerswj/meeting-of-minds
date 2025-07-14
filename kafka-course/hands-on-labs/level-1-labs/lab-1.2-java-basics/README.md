# Lab 1.2: Java Producer and Consumer

## 🎯 Learning Objectives

By the end of this lab, you will be able to:
- Set up a Java project with Kafka client dependencies
- Implement a basic Kafka producer with proper configuration
- Implement a basic Kafka consumer with proper configuration
- Handle different message delivery semantics
- Manage consumer offsets and groups
- Implement proper error handling and resource management
- Test producer and consumer applications

## ⏱️ Duration
4-5 hours

## 📋 Prerequisites
- Completed Lab 1.1: First Steps with Kafka
- Java 8 or higher installed
- Maven installed
- Basic Java programming knowledge
- Kafka cluster running (from previous lab)

## 🛠️ Lab Setup

### Step 1: Verify Kafka is Running
```bash
# Check if Kafka is running
bin/kafka-broker-api-versions.sh --bootstrap-server localhost:9092

# Create the topic for this lab
bin/kafka-topics.sh --create \
    --topic java-basics-topic \
    --bootstrap-server localhost:9092 \
    --partitions 3 \
    --replication-factor 1
```

### Step 2: Set Up Java Project
```bash
# Navigate to the lab directory
cd hands-on-labs/level-1-labs/lab-1.2-java-basics

# Verify Maven is installed
mvn --version

# Build the project
mvn clean compile

# Run tests (if any)
mvn test
```

### Step 3: Verify Dependencies
The project includes the following dependencies:
- **kafka-clients**: Apache Kafka Java client library
- **slf4j-api**: Logging facade
- **logback-classic**: Logging implementation
- **jackson-databind**: JSON processing
- **junit**: Testing framework
- **commons-lang3**: Utility classes

## 🚀 Lab Exercises

### Exercise 1: Understanding the Producer

#### Task 1.1: Review Producer Configuration
Open `BasicProducer.java` and examine the configuration in the `createProducer()` method:

```java
// Required configurations
props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

// Performance configurations
props.put(ProducerConfig.BATCH_SIZE_CONFIG, 16384); // 16KB batch size
props.put(ProducerConfig.LINGER_MS_CONFIG, 1); // Wait up to 1ms for more records
props.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 33554432); // 32MB buffer
props.put(ProducerConfig.COMPRESSION_TYPE_CONFIG, "snappy"); // Enable compression

// Reliability configurations
props.put(ProducerConfig.ACKS_CONFIG, "all"); // Wait for all replicas
props.put(ProducerConfig.RETRIES_CONFIG, 3); // Retry failed sends
props.put(ProducerConfig.RETRY_BACKOFF_MS_CONFIG, 100); // Backoff between retries

// Idempotence (exactly-once semantics)
props.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, true);
```

**Questions to Answer:**
1. What is the purpose of `BATCH_SIZE_CONFIG`?
2. How does `LINGER_MS_CONFIG` affect performance?
3. What does `ACKS_CONFIG` control?
4. Why is idempotence important?

#### Task 1.2: Run the Basic Producer
```bash
# Compile the project
mvn clean compile

# Run the producer
mvn exec:java -Dexec.mainClass="com.kafka.course.lab1.BasicProducer"
```

**Expected Output:**
```
INFO  - === Starting Basic Producer Demo ===
INFO  - Sending message synchronously: key=sync-key, value=Hello Kafka from Java!
INFO  - Message sent successfully to topic=java-basics-topic, partition=0, offset=0
INFO  - Sending message asynchronously: key=async-key, value=Hello Kafka asynchronously!
INFO  - Message sent successfully to topic=java-basics-topic, partition=1, offset=0
INFO  - Sending batch of 5 messages
INFO  - Batch sending completed
INFO  - Sending messages with different keys to demonstrate partitioning
INFO  - Message sent: key=user1, partition=2, offset=0
INFO  - Message sent: key=user2, partition=0, offset=1
...
INFO  - === Basic Producer Demo Completed ===
```

#### Task 1.3: Analyze Producer Behavior
1. **Synchronous vs Asynchronous**: Notice the difference in timing between sync and async sends
2. **Partitioning**: Observe how messages with the same key go to the same partition
3. **Batching**: See how multiple messages are batched together
4. **Error Handling**: Check the error handling for different scenarios

### Exercise 2: Understanding the Consumer

#### Task 2.1: Review Consumer Configuration
Open `BasicConsumer.java` and examine the configuration in the `createConsumer()` method:

```java
// Required configurations
props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
props.put(ConsumerConfig.GROUP_ID_CONFIG, GROUP_ID);
props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());

// Offset management
props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest"); // Start from beginning if no offset
props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, true); // Auto-commit offsets
props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, 1000); // Commit every 1 second

// Performance configurations
props.put(ConsumerConfig.FETCH_MIN_BYTES_CONFIG, 1); // Fetch even single records
props.put(ConsumerConfig.FETCH_MAX_WAIT_MS_CONFIG, 500); // Wait up to 500ms for data
props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, 500); // Max records per poll
props.put(ConsumerConfig.MAX_POLL_INTERVAL_MS_CONFIG, 300000); // 5 minutes max poll interval

// Session and heartbeat
props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, 30000); // 30 seconds session timeout
props.put(ConsumerConfig.HEARTBEAT_INTERVAL_MS_CONFIG, 3000); // 3 seconds heartbeat
```

**Questions to Answer:**
1. What is the purpose of `AUTO_OFFSET_RESET_CONFIG`?
2. How does `ENABLE_AUTO_COMMIT_CONFIG` work?
3. What is the relationship between `SESSION_TIMEOUT_MS_CONFIG` and `HEARTBEAT_INTERVAL_MS_CONFIG`?
4. Why is `MAX_POLL_INTERVAL_MS_CONFIG` important?

#### Task 2.2: Run the Basic Consumer
```bash
# In a new terminal, run the consumer
mvn exec:java -Dexec.mainClass="com.kafka.course.lab1.BasicConsumer"
```

**Expected Output:**
```
INFO  - === Starting Basic Consumer Demo ===
INFO  - Subscribed to topic: java-basics-topic
INFO  - Received 15 records
INFO  - Received message: topic=java-basics-topic, partition=0, offset=0, key=sync-key, value=Hello Kafka from Java!
INFO  - Received message: topic=java-basics-topic, partition=1, offset=0, key=async-key, value=Hello Kafka asynchronously!
...
```

#### Task 2.3: Test Different Consumer Modes
```bash
# Test manual commit consumer
mvn exec:java -Dexec.mainClass="com.kafka.course.lab1.BasicConsumer" -Dexec.args="manual-commit"

# Test consumer group behavior
mvn exec:java -Dexec.mainClass="com.kafka.course.lab1.BasicConsumer" -Dexec.args="consumer-group"

# Test rebalancing
mvn exec:java -Dexec.mainClass="com.kafka.course.lab1.BasicConsumer" -Dexec.args="rebalancing"
```

### Exercise 3: Producer-Consumer Integration

#### Task 3.1: Run Producer and Consumer Together
```bash
# Terminal 1: Start the consumer
mvn exec:java -Dexec.mainClass="com.kafka.course.lab1.BasicConsumer"

# Terminal 2: Start the producer
mvn exec:java -Dexec.mainClass="com.kafka.course.lab1.BasicProducer"
```

#### Task 3.2: Observe Real-time Message Flow
1. Watch how messages flow from producer to consumer
2. Notice the timing and ordering of messages
3. Observe partition distribution
4. Check consumer group behavior

#### Task 3.3: Test Consumer Group Scaling
```bash
# Terminal 1: Start first consumer
mvn exec:java -Dexec.mainClass="com.kafka.course.lab1.BasicConsumer" -Dexec.args="consumer-group"

# Terminal 2: Start second consumer (same group)
mvn exec:java -Dexec.mainClass="com.kafka.course.lab1.BasicConsumer" -Dexec.args="consumer-group"

# Terminal 3: Send messages
mvn exec:java -Dexec.mainClass="com.kafka.course.lab1.BasicProducer"
```

**Expected Behavior:**
- Both consumers should receive different partitions
- Messages should be distributed between consumers
- Rebalancing should occur when consumers join/leave

### Exercise 4: Advanced Features

#### Task 4.1: Custom Message Processing
Modify the `processRecord()` method in `BasicConsumer.java` to add custom processing:

```java
private void processRecord(ConsumerRecord<String, String> record) {
    logger.info("Processing message: key={}, value={}", record.key(), record.value());
    
    // Add custom processing logic
    if (record.key() != null && record.key().startsWith("user")) {
        logger.info("Processing user message: {}", record.value());
        // Simulate user-specific processing
    } else if (record.key() != null && record.key().startsWith("batch")) {
        logger.info("Processing batch message: {}", record.value());
        // Simulate batch processing
    }
    
    // Simulate processing time
    try {
        Thread.sleep(200); // Increased processing time
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
        logger.warn("Processing interrupted");
    }
}
```

#### Task 4.2: Error Handling Enhancement
Add more sophisticated error handling to the producer:

```java
public void sendMessageWithRetry(String key, String value, int maxRetries) {
    int retryCount = 0;
    while (retryCount < maxRetries) {
        try {
            ProducerRecord<String, String> record = new ProducerRecord<>(TOPIC_NAME, key, value);
            Future<RecordMetadata> future = producer.send(record);
            RecordMetadata metadata = future.get();
            
            logger.info("Message sent successfully after {} retries: partition={}, offset={}", 
                       retryCount, metadata.partition(), metadata.offset());
            return;
            
        } catch (Exception e) {
            retryCount++;
            logger.warn("Send failed (attempt {}/{}): {}", retryCount, maxRetries, e.getMessage());
            
            if (retryCount >= maxRetries) {
                logger.error("Failed to send message after {} retries", maxRetries, e);
                throw new RuntimeException("Failed to send message", e);
            }
            
            // Wait before retry
            try {
                Thread.sleep(1000 * retryCount); // Exponential backoff
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
                throw new RuntimeException("Interrupted during retry", ie);
            }
        }
    }
}
```

#### Task 4.3: Performance Monitoring
Add performance monitoring to track throughput:

```java
public class PerformanceMonitor {
    private final AtomicLong messageCount = new AtomicLong(0);
    private final AtomicLong totalLatency = new AtomicLong(0);
    private final long startTime = System.currentTimeMillis();
    
    public void recordMessage(long latency) {
        messageCount.incrementAndGet();
        totalLatency.addAndGet(latency);
    }
    
    public void printStats() {
        long count = messageCount.get();
        long totalLat = totalLatency.get();
        long duration = System.currentTimeMillis() - startTime;
        
        logger.info("Performance Stats:");
        logger.info("  Messages sent: {}", count);
        logger.info("  Total duration: {} ms", duration);
        logger.info("  Throughput: {:.2f} msg/sec", (count * 1000.0) / duration);
        logger.info("  Average latency: {:.2f} ms", count > 0 ? (double) totalLat / count : 0);
    }
}
```

## 🧪 Testing and Validation

### Test 1: Message Ordering
```bash
# Send messages with specific keys to test ordering
mvn exec:java -Dexec.mainClass="com.kafka.course.lab1.BasicProducer"

# Verify messages are received in order within partitions
mvn exec:java -Dexec.mainClass="com.kafka.course.lab1.BasicConsumer"
```

### Test 2: Consumer Offset Management
```bash
# Start consumer and read some messages
mvn exec:java -Dexec.mainClass="com.kafka.course.lab1.BasicConsumer" -Dexec.args="manual-commit"

# Stop consumer and restart - should continue from last committed offset
mvn exec:java -Dexec.mainClass="com.kafka.course.lab1.BasicConsumer" -Dexec.args="manual-commit"
```

### Test 3: Error Scenarios
```bash
# Test with invalid topic
# Modify TOPIC_NAME in BasicProducer.java to "non-existent-topic"
mvn exec:java -Dexec.mainClass="com.kafka.course.lab1.BasicProducer"

# Test with network issues
# Stop Kafka and run producer
mvn exec:java -Dexec.mainClass="com.kafka.course.lab1.BasicProducer"
```

## 📊 Lab Completion Checklist

- [ ] Successfully compiled and ran the Java project
- [ ] Understood producer configuration options
- [ ] Understood consumer configuration options
- [ ] Tested synchronous and asynchronous message sending
- [ ] Observed message partitioning behavior
- [ ] Tested consumer group functionality
- [ ] Implemented manual offset commit
- [ ] Added custom error handling
- [ ] Monitored performance metrics
- [ ] Tested different consumer modes

## 🔧 Troubleshooting

### Common Issues and Solutions

#### Issue 1: Maven Build Fails
```bash
# Check Java version
java -version

# Clean and rebuild
mvn clean compile

# Check dependencies
mvn dependency:tree
```

#### Issue 2: Connection Refused
```bash
# Verify Kafka is running
bin/kafka-broker-api-versions.sh --bootstrap-server localhost:9092

# Check topic exists
bin/kafka-topics.sh --list --bootstrap-server localhost:9092
```

#### Issue 3: Consumer Not Receiving Messages
```bash
# Check consumer group status
bin/kafka-consumer-groups.sh --describe --group java-basics-group --bootstrap-server localhost:9092

# Reset consumer group offsets
bin/kafka-consumer-groups.sh --bootstrap-server localhost:9092 --group java-basics-group --reset-offsets --to-earliest --topic java-basics-topic --execute
```

#### Issue 4: Performance Issues
```bash
# Monitor Kafka metrics
bin/kafka-topics.sh --describe --topic java-basics-topic --bootstrap-server localhost:9092

# Check consumer lag
bin/kafka-consumer-groups.sh --describe --group java-basics-group --bootstrap-server localhost:9092
```

## 📝 Lab Report

### Questions to Answer
1. What is the difference between synchronous and asynchronous message sending?
2. How does message partitioning work in Kafka?
3. What is the purpose of consumer groups?
4. How does offset management work?
5. What are the trade-offs between auto-commit and manual commit?

### Code Analysis
- Review the producer configuration and explain each setting
- Review the consumer configuration and explain each setting
- Analyze the error handling patterns used
- Explain the resource management approach

### Performance Observations
- Document throughput and latency measurements
- Compare different configuration settings
- Analyze the impact of batching and compression
- Note any performance bottlenecks

### Next Steps
- What improvements would you make to the code?
- How would you handle different message formats (JSON, Avro, etc.)?
- What additional error scenarios should be handled?
- How would you scale this application?

## 🧹 Cleanup

### Stop Applications
```bash
# Stop any running Java applications
# Use Ctrl+C or kill the processes

# Stop Kafka (if needed)
bin/kafka-server-stop.sh
bin/zookeeper-server-stop.sh
```

### Clean Project
```bash
# Clean Maven build
mvn clean

# Remove generated files
rm -rf target/
```

## 📚 Additional Resources

- [Kafka Producer API](https://kafka.apache.org/documentation/#producerapi)
- [Kafka Consumer API](https://kafka.apache.org/documentation/#consumerapi)
- [Kafka Configuration](https://kafka.apache.org/documentation/#configuration)
- [Kafka Performance Tuning](https://kafka.apache.org/documentation/#performance)

---

**Congratulations!** You've completed the Java Producer and Consumer lab. You now have hands-on experience with:
- Setting up Kafka Java applications
- Configuring producers and consumers
- Handling different message delivery patterns
- Managing consumer groups and offsets
- Implementing error handling and monitoring
- Testing and validating Kafka applications

**Next Lab**: [Lab 1.3: Topic Management](../lab-1.3-topic-management/README.md)