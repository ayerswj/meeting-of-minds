# Lab 1.1: First Steps with Kafka

## 🎯 Learning Objectives

By the end of this lab, you will be able to:
- Install and configure Apache Kafka
- Start Zookeeper and Kafka broker
- Create your first topic
- Send messages using console producer
- Consume messages using console consumer
- Explore topic configuration

## ⏱️ Duration
2-3 hours

## 📋 Prerequisites
- Linux/macOS system (Windows users can use WSL or Docker)
- Java 8 or higher installed
- Basic command-line knowledge
- 4GB+ RAM available

## 🛠️ Lab Setup

### Option 1: Local Installation (Recommended for Learning)

#### Step 1: Download Kafka
```bash
# Create a directory for Kafka
mkdir ~/kafka-lab
cd ~/kafka-lab

# Download Apache Kafka 3.5.1
wget https://downloads.apache.org/kafka/3.5.1/kafka_2.13-3.5.1.tgz

# Extract the archive
tar -xzf kafka_2.13-3.5.1.tgz

# Navigate to Kafka directory
cd kafka_2.13-3.5.1
```

#### Step 2: Verify Installation
```bash
# Check Kafka version
bin/kafka-topics.sh --version

# Check if Java is available
java -version
```

### Option 2: Docker Setup (Alternative)
```bash
# Create docker-compose.yml file
cat > docker-compose.yml << 'EOF'
version: '3'
services:
  zookeeper:
    image: confluentinc/cp-zookeeper:latest
    environment:
      ZOOKEEPER_CLIENT_PORT: 2181
      ZOOKEEPER_TICK_TIME: 2000
    ports:
      - "2181:2181"

  kafka:
    image: confluentinc/cp-kafka:latest
    depends_on:
      - zookeeper
    ports:
      - "9092:9092"
    environment:
      KAFKA_BROKER_ID: 1
      KAFKA_ZOOKEEPER_CONNECT: zookeeper:2181
      KAFKA_ADVERTISED_LISTENERS: PLAINTEXT://localhost:9092
      KAFKA_OFFSETS_TOPIC_REPLICATION_FACTOR: 1
      KAFKA_AUTO_CREATE_TOPICS_ENABLE: 'true'

  kafka-ui:
    image: provectuslabs/kafka-ui:latest
    depends_on:
      - kafka
    ports:
      - "8080:8080"
    environment:
      KAFKA_CLUSTERS_0_NAME: local
      KAFKA_CLUSTERS_0_BOOTSTRAPSERVERS: kafka:9092
      KAFKA_CLUSTERS_0_ZOOKEEPER: zookeeper:2181
EOF

# Start the services
docker-compose up -d
```

## 🚀 Lab Exercises

### Exercise 1: Start Kafka Services

#### Task 1.1: Start Zookeeper
```bash
# Start Zookeeper in the background
bin/zookeeper-server-start.sh -daemon config/zookeeper.properties

# Verify Zookeeper is running
echo stat | nc localhost 2181
```

#### Task 1.2: Start Kafka Broker
```bash
# Start Kafka broker in the background
bin/kafka-server-start.sh -daemon config/server.properties

# Wait a few seconds for Kafka to start
sleep 10

# Verify Kafka is running
bin/kafka-broker-api-versions.sh --bootstrap-server localhost:9092
```

### Exercise 2: Topic Management

#### Task 2.1: List Existing Topics
```bash
# List all topics (should be empty initially)
bin/kafka-topics.sh --list --bootstrap-server localhost:9092
```

#### Task 2.2: Create Your First Topic
```bash
# Create a topic named 'my-first-topic' with 3 partitions and replication factor 1
bin/kafka-topics.sh --create \
    --topic my-first-topic \
    --bootstrap-server localhost:9092 \
    --partitions 3 \
    --replication-factor 1
```

#### Task 2.3: Describe the Topic
```bash
# Get detailed information about the topic
bin/kafka-topics.sh --describe \
    --topic my-first-topic \
    --bootstrap-server localhost:9092
```

**Expected Output:**
```
Topic: my-first-topic	TopicId: xyz123	PartitionCount: 3	ReplicationFactor: 1	Configs: segment.bytes=1073741824
	Topic: my-first-topic	Partition: 0	Leader: 1	Replicas: 1	Isr: 1
	Topic: my-first-topic	Partition: 1	Leader: 1	Replicas: 1	Replicas: 1	Isr: 1
	Topic: my-first-topic	Partition: 2	Leader: 1	Replicas: 1	Replicas: 1	Isr: 1
```

### Exercise 3: Producer and Consumer

#### Task 3.1: Start Console Consumer
```bash
# Start a consumer in a new terminal window
# This will wait for messages
bin/kafka-console-consumer.sh \
    --topic my-first-topic \
    --bootstrap-server localhost:9092 \
    --from-beginning
```

#### Task 3.2: Send Messages with Console Producer
```bash
# In another terminal, start the producer
bin/kafka-console-producer.sh \
    --topic my-first-topic \
    --bootstrap-server localhost:9092
```

**Producer Commands:**
```
> Hello, Kafka!
> This is my first message
> Learning Kafka is fun!
> Goodbye for now
```

**Expected Consumer Output:**
```
Hello, Kafka!
This is my first message
Learning Kafka is fun!
Goodbye for now
```

#### Task 3.3: Send Messages with Keys
```bash
# Start producer with key separator
bin/kafka-console-producer.sh \
    --topic my-first-topic \
    --bootstrap-server localhost:9092 \
    --property "parse.key=true" \
    --property "key.separator=:"
```

**Producer Commands with Keys:**
```
user1:Hello from user1
user2:Hello from user2
user1:Another message from user1
user3:Hello from user3
```

### Exercise 4: Consumer Groups

#### Task 4.1: Create a Consumer Group
```bash
# Start consumer with a specific group
bin/kafka-console-consumer.sh \
    --topic my-first-topic \
    --bootstrap-server localhost:9092 \
    --group my-consumer-group \
    --from-beginning
```

#### Task 4.2: List Consumer Groups
```bash
# List all consumer groups
bin/kafka-consumer-groups.sh \
    --list \
    --bootstrap-server localhost:9092
```

#### Task 4.3: Describe Consumer Group
```bash
# Get detailed information about the consumer group
bin/kafka-consumer-groups.sh \
    --describe \
    --group my-consumer-group \
    --bootstrap-server localhost:9092
```

### Exercise 5: Topic Configuration

#### Task 5.1: Create Topic with Custom Configuration
```bash
# Create a topic with custom retention and cleanup policy
bin/kafka-topics.sh --create \
    --topic config-topic \
    --bootstrap-server localhost:9092 \
    --partitions 2 \
    --replication-factor 1 \
    --config retention.ms=3600000 \
    --config cleanup.policy=delete
```

#### Task 5.2: Modify Topic Configuration
```bash
# Change the retention time for the topic
bin/kafka-configs.sh --bootstrap-server localhost:9092 \
    --entity-type topics \
    --entity-name config-topic \
    --alter \
    --add-config retention.ms=7200000
```

#### Task 5.3: Describe Topic Configuration
```bash
# View the current configuration
bin/kafka-configs.sh --bootstrap-server localhost:9092 \
    --entity-type topics \
    --entity-name config-topic \
    --describe
```

## 🔍 Exploration Tasks

### Task 1: Explore Kafka Logs
```bash
# Navigate to Kafka logs directory
cd logs

# List log files
ls -la

# View server log
tail -f server.log

# View controller log
tail -f controller.log
```

### Task 2: Monitor Topic Metrics
```bash
# Get topic metrics using JMX
# First, enable JMX
export JMX_PORT=9999

# Start Kafka with JMX enabled
bin/kafka-server-start.sh config/server.properties &

# Use jconsole to connect to localhost:9999
# Or use a JMX client to view metrics
```

### Task 3: Test Different Message Formats
```bash
# Send JSON messages
echo '{"user": "alice", "message": "Hello Kafka!", "timestamp": "2024-01-01T12:00:00Z"}' | \
bin/kafka-console-producer.sh --topic my-first-topic --bootstrap-server localhost:9092

# Send CSV messages
echo 'user,action,timestamp' | \
bin/kafka-console-producer.sh --topic my-first-topic --bootstrap-server localhost:9092

echo 'alice,login,2024-01-01T12:00:00Z' | \
bin/kafka-console-producer.sh --topic my-first-topic --bootstrap-server localhost:9092
```

## 🧪 Testing and Validation

### Test 1: Message Ordering
```bash
# Send messages to specific partitions
bin/kafka-console-producer.sh \
    --topic my-first-topic \
    --bootstrap-server localhost:9092 \
    --property "parse.key=true" \
    --property "key.separator=:"

# Send messages with the same key (they'll go to the same partition)
user1:Message 1 from user1
user1:Message 2 from user1
user1:Message 3 from user1
user2:Message 1 from user2
user2:Message 2 from user2
```

### Test 2: Consumer Offset
```bash
# Start a consumer and read some messages
bin/kafka-console-consumer.sh \
    --topic my-first-topic \
    --bootstrap-server localhost:9092 \
    --group offset-test-group \
    --max-messages 3

# Start another consumer in the same group
# It should continue from where the first consumer left off
bin/kafka-console-consumer.sh \
    --topic my-first-topic \
    --bootstrap-server localhost:9092 \
    --group offset-test-group
```

### Test 3: Topic Deletion
```bash
# Create a test topic
bin/kafka-topics.sh --create \
    --topic test-delete-topic \
    --bootstrap-server localhost:9092 \
    --partitions 1 \
    --replication-factor 1

# Delete the topic
bin/kafka-topics.sh --delete \
    --topic test-delete-topic \
    --bootstrap-server localhost:9092

# Verify deletion
bin/kafka-topics.sh --list --bootstrap-server localhost:9092
```

## 📊 Lab Completion Checklist

- [ ] Successfully installed and started Kafka
- [ ] Created a topic with custom configuration
- [ ] Sent and received messages using console tools
- [ ] Explored consumer groups and offsets
- [ ] Modified topic configurations
- [ ] Tested message ordering and partitioning
- [ ] Monitored Kafka logs and metrics
- [ ] Successfully deleted a topic

## 🔧 Troubleshooting

### Common Issues and Solutions

#### Issue 1: Zookeeper Connection Refused
```bash
# Check if Zookeeper is running
netstat -an | grep 2181

# If not running, start it
bin/zookeeper-server-start.sh config/zookeeper.properties
```

#### Issue 2: Kafka Broker Won't Start
```bash
# Check if port 9092 is available
netstat -an | grep 9092

# Check Kafka logs
tail -f logs/server.log

# Kill any existing Kafka processes
pkill -f kafka
```

#### Issue 3: Topic Creation Fails
```bash
# Check if Kafka is running
bin/kafka-broker-api-versions.sh --bootstrap-server localhost:9092

# Check topic creation permissions
bin/kafka-topics.sh --list --bootstrap-server localhost:9092
```

#### Issue 4: Consumer Not Receiving Messages
```bash
# Check if messages were sent
bin/kafka-console-consumer.sh \
    --topic my-first-topic \
    --bootstrap-server localhost:9092 \
    --from-beginning \
    --max-messages 10

# Check consumer group status
bin/kafka-consumer-groups.sh \
    --describe \
    --group my-consumer-group \
    --bootstrap-server localhost:9092
```

## 📝 Lab Report

### Questions to Answer
1. What is the purpose of Zookeeper in a Kafka cluster?
2. How does partitioning help with scalability?
3. What is the difference between a topic and a partition?
4. How do consumer groups work?
5. What happens when you send messages with the same key?

### Observations
- Document any interesting behavior you observed
- Note any performance characteristics
- Record any issues you encountered and how you resolved them

### Next Steps
- What would you like to explore further?
- What questions do you have about Kafka fundamentals?
- How might you apply these concepts to a real-world scenario?

## 🧹 Cleanup

### Stop Kafka Services
```bash
# Stop Kafka broker
bin/kafka-server-stop.sh

# Stop Zookeeper
bin/zookeeper-server-stop.sh

# Or if using Docker
docker-compose down
```

### Remove Lab Files (Optional)
```bash
# Remove downloaded Kafka files
cd ~
rm -rf kafka-lab

# Remove Docker containers and volumes
docker-compose down -v
docker system prune -f
```

## 📚 Additional Resources

- [Apache Kafka Quick Start](https://kafka.apache.org/quickstart)
- [Kafka Command Line Tools](https://kafka.apache.org/documentation/#tools)
- [Kafka Configuration](https://kafka.apache.org/documentation/#configuration)
- [Kafka Architecture](https://kafka.apache.org/documentation/#introduction)

---

**Congratulations!** You've completed your first Kafka lab. You now have hands-on experience with:
- Installing and configuring Kafka
- Creating and managing topics
- Sending and receiving messages
- Working with consumer groups
- Basic monitoring and troubleshooting

**Next Lab**: [Lab 1.2: Java Producer and Consumer](../lab-1.2-java-basics/README.md)