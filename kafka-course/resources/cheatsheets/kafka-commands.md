# Kafka Command Line Cheatsheet

## Quick Reference for Kafka CLI Tools

### Basic Commands

#### Check Kafka Version
```bash
kafka-topics.sh --version
```

#### Check Broker API Versions
```bash
kafka-broker-api-versions.sh --bootstrap-server localhost:9092
```

---

## Topic Management

### List Topics
```bash
# List all topics
kafka-topics.sh --list --bootstrap-server localhost:9092

# List topics with details
kafka-topics.sh --list --bootstrap-server localhost:9092 --exclude-internal
```

### Create Topics
```bash
# Basic topic creation
kafka-topics.sh --create \
    --topic my-topic \
    --bootstrap-server localhost:9092 \
    --partitions 3 \
    --replication-factor 1

# Topic with custom configuration
kafka-topics.sh --create \
    --topic config-topic \
    --bootstrap-server localhost:9092 \
    --partitions 2 \
    --replication-factor 1 \
    --config retention.ms=3600000 \
    --config cleanup.policy=delete
```

### Describe Topics
```bash
# Describe specific topic
kafka-topics.sh --describe \
    --topic my-topic \
    --bootstrap-server localhost:9092

# Describe all topics
kafka-topics.sh --describe \
    --bootstrap-server localhost:9092

# Describe topics with overrides
kafka-topics.sh --describe \
    --topic my-topic \
    --bootstrap-server localhost:9092 \
    --topics-with-overrides
```

### Modify Topics
```bash
# Add partitions
kafka-topics.sh --alter \
    --topic my-topic \
    --bootstrap-server localhost:9092 \
    --partitions 6

# Modify configuration
kafka-configs.sh --bootstrap-server localhost:9092 \
    --entity-type topics \
    --entity-name my-topic \
    --alter \
    --add-config retention.ms=7200000

# Delete topic
kafka-topics.sh --delete \
    --topic my-topic \
    --bootstrap-server localhost:9092
```

### Topic Configuration
```bash
# Describe topic configuration
kafka-configs.sh --bootstrap-server localhost:9092 \
    --entity-type topics \
    --entity-name my-topic \
    --describe

# Describe broker configuration
kafka-configs.sh --bootstrap-server localhost:9092 \
    --entity-type brokers \
    --entity-name 1 \
    --describe
```

---

## Producer and Consumer

### Console Producer
```bash
# Basic producer
kafka-console-producer.sh \
    --topic my-topic \
    --bootstrap-server localhost:9092

# Producer with key separator
kafka-console-producer.sh \
    --topic my-topic \
    --bootstrap-server localhost:9092 \
    --property "parse.key=true" \
    --property "key.separator=:"

# Producer with custom properties
kafka-console-producer.sh \
    --topic my-topic \
    --bootstrap-server localhost:9092 \
    --property "acks=all" \
    --property "retries=3"
```

### Console Consumer
```bash
# Basic consumer
kafka-console-consumer.sh \
    --topic my-topic \
    --bootstrap-server localhost:9092 \
    --from-beginning

# Consumer with group
kafka-console-consumer.sh \
    --topic my-topic \
    --bootstrap-server localhost:9092 \
    --group my-group \
    --from-beginning

# Consumer with key display
kafka-console-consumer.sh \
    --topic my-topic \
    --bootstrap-server localhost:9092 \
    --property "print.key=true" \
    --property "key.separator=:" \
    --from-beginning

# Consumer with max messages
kafka-console-consumer.sh \
    --topic my-topic \
    --bootstrap-server localhost:9092 \
    --max-messages 10 \
    --from-beginning
```

---

## Consumer Groups

### List Consumer Groups
```bash
# List all consumer groups
kafka-consumer-groups.sh \
    --list \
    --bootstrap-server localhost:9092

# List groups with state
kafka-consumer-groups.sh \
    --list \
    --bootstrap-server localhost:9092 \
    --state Stable
```

### Describe Consumer Groups
```bash
# Describe specific group
kafka-consumer-groups.sh \
    --describe \
    --group my-group \
    --bootstrap-server localhost:9092

# Describe all groups
kafka-consumer-groups.sh \
    --describe \
    --all-groups \
    --bootstrap-server localhost:9092

# Describe with members
kafka-consumer-groups.sh \
    --describe \
    --group my-group \
    --members \
    --bootstrap-server localhost:9092
```

### Reset Consumer Group Offsets
```bash
# Reset to earliest
kafka-consumer-groups.sh \
    --bootstrap-server localhost:9092 \
    --group my-group \
    --reset-offsets \
    --to-earliest \
    --topic my-topic \
    --execute

# Reset to latest
kafka-consumer-groups.sh \
    --bootstrap-server localhost:9092 \
    --group my-group \
    --reset-offsets \
    --to-latest \
    --topic my-topic \
    --execute

# Reset to specific offset
kafka-consumer-groups.sh \
    --bootstrap-server localhost:9092 \
    --group my-group \
    --reset-offsets \
    --to-offset 100 \
    --topic my-topic \
    --execute

# Reset to datetime
kafka-consumer-groups.sh \
    --bootstrap-server localhost:9092 \
    --group my-group \
    --reset-offsets \
    --to-datetime 2024-01-01T00:00:00.000Z \
    --topic my-topic \
    --execute
```

### Delete Consumer Group
```bash
kafka-consumer-groups.sh \
    --bootstrap-server localhost:9092 \
    --group my-group \
    --delete
```

---

## ACLs (Access Control Lists)

### List ACLs
```bash
# List all ACLs
kafka-acls.sh \
    --list \
    --bootstrap-server localhost:9092

# List ACLs for topic
kafka-acls.sh \
    --list \
    --topic my-topic \
    --bootstrap-server localhost:9092

# List ACLs for user
kafka-acls.sh \
    --list \
    --principal User:alice \
    --bootstrap-server localhost:9092
```

### Add ACLs
```bash
# Allow user to read topic
kafka-acls.sh \
    --add \
    --allow-principal User:alice \
    --operation Read \
    --topic my-topic \
    --bootstrap-server localhost:9092

# Allow user to write to topic
kafka-acls.sh \
    --add \
    --allow-principal User:alice \
    --operation Write \
    --topic my-topic \
    --bootstrap-server localhost:9092

# Allow user to create topics
kafka-acls.sh \
    --add \
    --allow-principal User:alice \
    --operation Create \
    --resource-pattern-type Literal \
    --topic '*' \
    --bootstrap-server localhost:9092
```

### Remove ACLs
```bash
kafka-acls.sh \
    --remove \
    --allow-principal User:alice \
    --operation Read \
    --topic my-topic \
    --bootstrap-server localhost:9092
```

---

## Performance Testing

### Producer Performance Test
```bash
# Basic performance test
kafka-producer-perf-test.sh \
    --topic test-topic \
    --num-records 1000000 \
    --record-size 1000 \
    --throughput 10000 \
    --producer-props bootstrap.servers=localhost:9092

# Performance test with custom properties
kafka-producer-perf-test.sh \
    --topic test-topic \
    --num-records 1000000 \
    --record-size 1000 \
    --throughput 10000 \
    --producer-props bootstrap.servers=localhost:9092,acks=all,retries=3,compression.type=snappy
```

### Consumer Performance Test
```bash
# Basic consumer performance test
kafka-consumer-perf-test.sh \
    --topic test-topic \
    --bootstrap-server localhost:9092 \
    --messages 1000000

# Consumer test with group
kafka-consumer-perf-test.sh \
    --topic test-topic \
    --bootstrap-server localhost:9092 \
    --messages 1000000 \
    --group perf-test-group
```

---

## Logs and Monitoring

### View Logs
```bash
# View server log
tail -f logs/server.log

# View controller log
tail -f logs/controller.log

# View state change log
tail -f logs/state-change.log
```

### JMX Metrics
```bash
# Enable JMX
export JMX_PORT=9999

# Start Kafka with JMX
bin/kafka-server-start.sh config/server.properties &

# Connect with jconsole
jconsole localhost:9999
```

---

## Cluster Management

### Describe Cluster
```bash
# Describe cluster
kafka-cluster.sh \
    --bootstrap-server localhost:9092 \
    --describe

# Describe cluster with controller
kafka-cluster.sh \
    --bootstrap-server localhost:9092 \
    --describe \
    --controller
```

### Reassign Partitions
```bash
# Generate reassignment plan
kafka-reassign-partitions.sh \
    --bootstrap-server localhost:9092 \
    --topics-to-move-json-file topics-to-move.json \
    --broker-list "1,2,3" \
    --generate

# Execute reassignment
kafka-reassign-partitions.sh \
    --bootstrap-server localhost:9092 \
    --reassignment-json-file reassignment.json \
    --execute

# Verify reassignment
kafka-reassign-partitions.sh \
    --bootstrap-server localhost:9092 \
    --reassignment-json-file reassignment.json \
    --verify
```

---

## Common Configuration Parameters

### Producer Configuration
```properties
# Required
bootstrap.servers=localhost:9092
key.serializer=org.apache.kafka.common.serialization.StringSerializer
value.serializer=org.apache.kafka.common.serialization.StringSerializer

# Performance
batch.size=16384
linger.ms=1
buffer.memory=33554432
compression.type=snappy

# Reliability
acks=all
retries=3
retry.backoff.ms=100
enable.idempotence=true
```

### Consumer Configuration
```properties
# Required
bootstrap.servers=localhost:9092
group.id=my-group
key.deserializer=org.apache.kafka.common.serialization.StringDeserializer
value.deserializer=org.apache.kafka.common.serialization.StringDeserializer

# Offset management
auto.offset.reset=earliest
enable.auto.commit=true
auto.commit.interval.ms=1000

# Performance
fetch.min.bytes=1
fetch.max.wait.ms=500
max.poll.records=500
max.poll.interval.ms=300000

# Session management
session.timeout.ms=30000
heartbeat.interval.ms=3000
```

### Topic Configuration
```properties
# Retention
retention.ms=604800000
retention.bytes=-1

# Cleanup
cleanup.policy=delete
delete.retention.ms=86400000
min.compaction.lag.ms=0

# Performance
segment.bytes=1073741824
segment.ms=604800000
segment.index.bytes=10485760

# Replication
min.insync.replicas=1
```

---

## Troubleshooting Commands

### Check Cluster Health
```bash
# Check if brokers are reachable
kafka-broker-api-versions.sh --bootstrap-server localhost:9092

# Check topic health
kafka-topics.sh --describe --topic my-topic --bootstrap-server localhost:9092

# Check consumer lag
kafka-consumer-groups.sh --describe --group my-group --bootstrap-server localhost:9092
```

### Debug Issues
```bash
# Check logs for errors
grep -i error logs/server.log

# Check for warnings
grep -i warn logs/server.log

# Check partition assignment
kafka-consumer-groups.sh --describe --group my-group --bootstrap-server localhost:9092
```

---

## Environment Variables

### Common Environment Variables
```bash
# Kafka home directory
export KAFKA_HOME=/opt/kafka

# Java options
export KAFKA_HEAP_OPTS="-Xmx1G -Xms1G"

# JMX options
export JMX_PORT=9999
export JMX_HOSTNAME=localhost

# Logging
export KAFKA_LOG4J_OPTS="-Dlog4j.configuration=file:config/log4j.properties"
```

---

## Quick Reference

### Most Used Commands
```bash
# Create topic
kafka-topics.sh --create --topic my-topic --bootstrap-server localhost:9092 --partitions 3 --replication-factor 1

# List topics
kafka-topics.sh --list --bootstrap-server localhost:9092

# Describe topic
kafka-topics.sh --describe --topic my-topic --bootstrap-server localhost:9092

# Send message
echo "Hello Kafka" | kafka-console-producer.sh --topic my-topic --bootstrap-server localhost:9092

# Receive messages
kafka-console-consumer.sh --topic my-topic --bootstrap-server localhost:9092 --from-beginning

# List consumer groups
kafka-consumer-groups.sh --list --bootstrap-server localhost:9092

# Describe consumer group
kafka-consumer-groups.sh --describe --group my-group --bootstrap-server localhost:9092
```

---

**Note**: Replace `localhost:9092` with your actual Kafka broker address and port. For production environments, use appropriate security configurations and authentication.