# Kafka Course Resources

This directory contains additional resources, tools, and reference materials to support your Kafka learning journey.

## Directory Structure

```
resources/
├── books/
├── videos/
├── articles/
├── tools/
├── cheatsheets/
├── templates/
└── community/
```

## Books and Documentation

### Essential Reading
- **Kafka: The Definitive Guide** by Neha Narkhede, Gwen Shapira, and Todd Palino
  - [Free PDF from Confluent](https://www.confluent.io/resources/kafka-the-definitive-guide/)
  - Covers fundamentals to advanced concepts

- **Designing Data-Intensive Applications** by Martin Kleppmann
  - Essential for understanding distributed systems
  - Chapter on stream processing is particularly relevant

- **Event Streaming with Kafka** by Alexander Dean
  - Practical guide to event-driven architectures
  - Real-world examples and patterns

### Official Documentation
- [Apache Kafka Documentation](https://kafka.apache.org/documentation/)
- [Confluent Platform Documentation](https://docs.confluent.io/platform/current/)
- [Kafka Streams Documentation](https://kafka.apache.org/documentation/streams/)
- [Schema Registry Documentation](https://docs.confluent.io/platform/current/schema-registry/index.html)

## Video Resources

### Free Courses
- **Confluent Kafka Tutorials**
  - [YouTube Channel](https://www.youtube.com/c/ConfluentInc)
  - Comprehensive video tutorials
  - Real-world use cases

- **Apache Kafka Fundamentals**
  - [Pluralsight Course](https://www.pluralsight.com/courses/apache-kafka-fundamentals)
  - Structured learning path
  - Hands-on exercises

### Conference Talks
- **Kafka Summit**
  - [Official Website](https://kafka-summit.org/)
  - Annual conference with expert talks
  - Real-world case studies

- **Strange Loop**
  - [Conference Videos](https://www.thestrangeloop.com/)
  - Advanced distributed systems talks
  - Performance and architecture insights

## Articles and Blogs

### Technical Blogs
- **Confluent Blog**
  - [https://www.confluent.io/blog/](https://www.confluent.io/blog/)
  - Regular updates on Kafka features
  - Best practices and tutorials

- **Apache Kafka Blog**
  - [https://blogs.apache.org/kafka/](https://blogs.apache.org/kafka/)
  - Official project updates
  - Release notes and announcements

### Community Blogs
- **Medium Kafka Tag**
  - [https://medium.com/tag/apache-kafka](https://medium.com/tag/apache-kafka)
  - Community-written articles
  - Diverse perspectives and use cases

## Tools and Utilities

### Development Tools
- **Kafka Tool**
  - GUI client for Kafka
  - Topic management and message browsing
  - [Download](https://www.kafkatool.com/)

- **Kafka Manager (CMAK)**
  - Web-based cluster management
  - Topic and consumer group management
  - [GitHub](https://github.com/yahoo/CMAK)

- **Kafka UI**
  - Modern web interface
  - Real-time monitoring
  - [GitHub](https://github.com/provectus/kafka-ui)

### Monitoring Tools
- **Prometheus**
  - Metrics collection and storage
  - [Official Site](https://prometheus.io/)

- **Grafana**
  - Visualization and dashboards
  - [Official Site](https://grafana.com/)

- **Kafka Exporter**
  - Prometheus metrics exporter
  - [GitHub](https://github.com/danielqsj/kafka_exporter)

### Testing Tools
- **Kafka Test Containers**
  - Integration testing
  - [GitHub](https://github.com/testcontainers/testcontainers-java)

- **Kafka Performance Testing**
  - Built-in performance tools
  - [Documentation](https://kafka.apache.org/documentation/#performance)

## Cheat Sheets

### Command Line Reference
```bash
# Topic Management
kafka-topics.sh --create --topic my-topic --bootstrap-server localhost:9092 --partitions 3 --replication-factor 1
kafka-topics.sh --list --bootstrap-server localhost:9092
kafka-topics.sh --describe --topic my-topic --bootstrap-server localhost:9092

# Producer/Consumer
kafka-console-producer.sh --topic my-topic --bootstrap-server localhost:9092
kafka-console-consumer.sh --topic my-topic --bootstrap-server localhost:9092 --from-beginning

# Consumer Groups
kafka-consumer-groups.sh --list --bootstrap-server localhost:9092
kafka-consumer-groups.sh --describe --group my-group --bootstrap-server localhost:9092
```

### Configuration Reference
```properties
# Producer Configuration
bootstrap.servers=localhost:9092
acks=all
retries=3
batch.size=16384
linger.ms=1
buffer.memory=33554432
compression.type=snappy

# Consumer Configuration
bootstrap.servers=localhost:9092
group.id=my-group
auto.offset.reset=earliest
enable.auto.commit=true
auto.commit.interval.ms=1000
session.timeout.ms=30000
```

### JMX Metrics Reference
```bash
# Key Metrics to Monitor
kafka.server:type=BrokerTopicMetrics,name=MessagesInPerSec
kafka.server:type=BrokerTopicMetrics,name=BytesInPerSec
kafka.server:type=BrokerTopicMetrics,name=BytesOutPerSec
kafka.server:type=ReplicaManager,name=UnderReplicatedPartitions
kafka.controller:type=KafkaController,name=OfflinePartitionsCount
```

## Templates

### Docker Compose Templates
```yaml
# Basic Kafka Cluster
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
```

### Java Producer Template
```java
import org.apache.kafka.clients.producer.*;
import java.util.Properties;

public class KafkaProducerExample {
    public static void main(String[] args) {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, 
                  "org.apache.kafka.common.serialization.StringSerializer");
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, 
                  "org.apache.kafka.common.serialization.StringSerializer");

        Producer<String, String> producer = new KafkaProducer<>(props);
        
        ProducerRecord<String, String> record = 
            new ProducerRecord<>("my-topic", "key", "value");
        
        producer.send(record, (metadata, exception) -> {
            if (exception == null) {
                System.out.println("Record sent successfully");
            } else {
                exception.printStackTrace();
            }
        });
        
        producer.close();
    }
}
```

### Java Consumer Template
```java
import org.apache.kafka.clients.consumer.*;
import java.util.Collections;
import java.util.Properties;

public class KafkaConsumerExample {
    public static void main(String[] args) {
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "my-group");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, 
                  "org.apache.kafka.common.serialization.StringDeserializer");
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, 
                  "org.apache.kafka.common.serialization.StringDeserializer");
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        Consumer<String, String> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Collections.singletonList("my-topic"));

        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(100);
            for (ConsumerRecord<String, String> record : records) {
                System.out.printf("offset = %d, key = %s, value = %s%n", 
                                 record.offset(), record.key(), record.value());
            }
        }
    }
}
```

## Community Resources

### Forums and Q&A
- **Stack Overflow**
  - [Apache Kafka Tag](https://stackoverflow.com/questions/tagged/apache-kafka)
  - Active community support
  - Quality answers from experts

- **Confluent Community**
  - [Community Forum](https://community.confluent.io/)
  - Official support channel
  - Expert advice and best practices

### Mailing Lists
- **Apache Kafka Users**
  - [users@kafka.apache.org](mailto:users@kafka.apache.org)
  - General questions and discussions
  - [Archive](https://lists.apache.org/list.html?users@kafka.apache.org)

- **Apache Kafka Developers**
  - [dev@kafka.apache.org](mailto:dev@kafka.apache.org)
  - Development discussions
  - [Archive](https://lists.apache.org/list.html?dev@kafka.apache.org)

### Social Media
- **Twitter**
  - [@apachekafka](https://twitter.com/apachekafka)
  - [@confluentinc](https://twitter.com/confluentinc)
  - Latest updates and announcements

- **LinkedIn**
  - [Apache Kafka Group](https://www.linkedin.com/groups/3864732/)
  - Professional networking
  - Job opportunities

## Certification Resources

### Confluent Certifications
- **Kafka Developer Certification**
  - [Exam Guide](https://www.confluent.io/certification/)
  - Practice tests and study materials
  - Official certification path

- **Kafka Administrator Certification**
  - Operations-focused certification
  - Cluster management and troubleshooting
  - Production deployment expertise

### Study Materials
- **Practice Tests**
  - Sample questions and scenarios
  - Mock exams
  - Performance-based assessments

- **Study Guides**
  - Topic-specific guides
  - Best practices summaries
  - Common pitfalls and solutions

## Additional Learning Paths

### Related Technologies
- **Apache Flink**
  - Stream processing framework
  - [Official Site](https://flink.apache.org/)

- **Apache Spark**
  - Big data processing
  - [Official Site](https://spark.apache.org/)

- **Apache Pulsar**
  - Alternative messaging system
  - [Official Site](https://pulsar.apache.org/)

### Cloud Platforms
- **AWS MSK**
  - Managed Kafka service
  - [Documentation](https://aws.amazon.com/msk/)

- **Azure Event Hubs**
  - Event streaming platform
  - [Documentation](https://azure.microsoft.com/en-us/services/event-hubs/)

- **Google Cloud Pub/Sub**
  - Messaging service
  - [Documentation](https://cloud.google.com/pubsub)

## Contributing

### How to Contribute
1. **Report Issues**: Use GitHub issues for bugs and feature requests
2. **Submit Pull Requests**: Contribute code improvements
3. **Write Documentation**: Help improve guides and tutorials
4. **Share Knowledge**: Write blog posts and create tutorials

### Contribution Guidelines
- Follow coding standards
- Include tests for new features
- Update documentation
- Review existing contributions

## Support and Feedback

### Getting Help
- Check the troubleshooting guides first
- Search existing issues and discussions
- Ask questions in community forums
- Contact course instructors

### Providing Feedback
- Course evaluation forms
- GitHub issues for technical problems
- Community forums for discussions
- Direct feedback to instructors

## License and Attribution

### Open Source Licenses
- Apache Kafka: Apache License 2.0
- Confluent Platform: Various licenses
- Tools and utilities: Respective licenses

### Attribution
- Credit original authors and contributors
- Follow license requirements
- Respect intellectual property rights

---

**Note**: This resources directory is continuously updated. Check back regularly for new materials, tools, and community resources.

Happy learning! 🚀