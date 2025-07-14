# Level 1 Assessment: Kafka Fundamentals Quiz

## Quiz Information
- **Duration**: 30 minutes
- **Total Questions**: 20
- **Passing Score**: 70% (14 correct answers)
- **Question Types**: Multiple Choice, True/False, Short Answer

## Instructions
1. Read each question carefully
2. Choose the best answer for multiple choice questions
3. Answer True/False questions with T or F
4. Provide brief explanations for short answer questions
5. Show your work for any calculations

---

## Multiple Choice Questions (10 questions)

### Question 1
**What is the primary role of a Kafka broker?**
- A) To store and serve web pages
- B) To receive messages from producers and serve them to consumers
- C) To manage user authentication
- D) To provide database functionality

**Answer**: B

### Question 2
**How does partitioning help with scalability in Kafka?**
- A) It reduces the number of topics
- B) It allows parallel processing of messages across multiple brokers
- C) It decreases message size
- D) It eliminates the need for replication

**Answer**: B

### Question 3
**What is the difference between a topic and a partition?**
- A) A topic is a logical category, while a partition is a physical subdivision of a topic
- B) A topic is always smaller than a partition
- C) A partition can exist without a topic
- D) There is no difference; they are the same thing

**Answer**: A

### Question 4
**Which command is used to create a new topic in Kafka?**
- A) `kafka-create-topic.sh`
- B) `kafka-topics.sh --create`
- C) `kafka-new-topic.sh`
- D) `kafka-topic-create.sh`

**Answer**: B

### Question 5
**What is the purpose of replication in Kafka?**
- A) To reduce storage costs
- B) To provide fault tolerance and high availability
- C) To speed up message processing
- D) To compress messages

**Answer**: B

### Question 6
**Which of the following is NOT a core component of Kafka architecture?**
- A) Producer
- B) Consumer
- C) Broker
- D) Database

**Answer**: D

### Question 7
**What happens when you send messages with the same key to a Kafka topic?**
- A) They are always sent to the same partition
- B) They are distributed randomly across partitions
- C) They are rejected by the broker
- D) They are stored in a separate topic

**Answer**: A

### Question 8
**What is the role of Zookeeper in a Kafka cluster?**
- A) To store all the messages
- B) To manage cluster metadata and coordinate brokers
- C) To handle user authentication
- D) To compress messages

**Answer**: B

### Question 9
**Which configuration parameter controls how long messages are retained in a topic?**
- A) `retention.ms`
- B) `message.ttl`
- C) `expiry.time`
- D) `cleanup.interval`

**Answer**: A

### Question 10
**What is the default number of partitions for a Kafka topic?**
- A) 1
- B) 3
- C) 5
- D) 10

**Answer**: A

---

## True/False Questions (5 questions)

### Question 11
**Kafka guarantees exactly-once message delivery by default.**

**Answer**: F (False) - Kafka provides at-least-once delivery by default, not exactly-once.

### Question 12
**Messages within a partition are always ordered by their offset.**

**Answer**: T (True) - Messages within a partition are ordered by their sequential offset.

### Question 13
**A consumer group can have more consumers than partitions in a topic.**

**Answer**: T (True) - Extra consumers in a group will be idle but can take over if other consumers fail.

### Question 14
**Kafka topics are immutable once created.**

**Answer**: F (False) - Topic configurations can be modified, and partitions can be added.

### Question 15
**The console producer and consumer tools are only for testing purposes.**

**Answer**: T (True) - They are primarily used for testing and debugging, not production.

---

## Short Answer Questions (5 questions)

### Question 16
**Explain the concept of consumer groups in Kafka. How do they help with scalability?**

**Sample Answer**: Consumer groups allow multiple consumers to work together to process messages from a topic. Each partition is assigned to only one consumer within a group, enabling parallel processing. This allows the group to scale horizontally by adding more consumers, and provides fault tolerance if a consumer fails.

### Question 17
**What is the difference between 'earliest' and 'latest' in the auto.offset.reset configuration?**

**Sample Answer**: 
- 'earliest': Start consuming from the beginning of the topic (offset 0)
- 'latest': Start consuming only new messages that arrive after the consumer starts

### Question 18
**Describe the relationship between producers, topics, and consumers in Kafka.**

**Sample Answer**: Producers send messages to topics, which act as logical categories or feeds. Topics are divided into partitions for scalability. Consumers subscribe to topics and read messages from partitions. Multiple consumers can form a group to share the workload of processing messages from a topic.

### Question 19
**What is the purpose of the bootstrap.servers configuration in Kafka clients?**

**Sample Answer**: bootstrap.servers specifies the list of Kafka brokers that the client should connect to initially. The client uses these servers to discover the full cluster topology and then connects to the appropriate brokers for producing or consuming messages.

### Question 20
**Explain what happens during a consumer rebalancing event.**

**Sample Answer**: Consumer rebalancing occurs when consumers join or leave a consumer group. During rebalancing, partitions are redistributed among the remaining consumers to ensure fair workload distribution. The process is coordinated by the group coordinator and temporarily pauses message consumption.

---

## Scoring Guide

### Multiple Choice (10 points)
- Each correct answer: 1 point
- Total possible: 10 points

### True/False (5 points)
- Each correct answer: 1 point
- Total possible: 5 points

### Short Answer (5 points)
- Each question: 1 point
- Points awarded for:
  - Correct understanding of concepts (0.5 points)
  - Clear explanation (0.5 points)

### Total Score Calculation
- **18-20 points**: Excellent (90-100%)
- **16-17 points**: Good (80-89%)
- **14-15 points**: Satisfactory (70-79%)
- **12-13 points**: Needs Improvement (60-69%)
- **Below 12 points**: Unsatisfactory (<60%)

---

## Answer Key

### Multiple Choice
1. B
2. B
3. A
4. B
5. B
6. D
7. A
8. B
9. A
10. A

### True/False
11. F
12. T
13. T
14. F
15. T

### Short Answer
16. Consumer groups enable parallel processing and fault tolerance
17. earliest = from beginning, latest = only new messages
18. Producers → Topics → Consumers relationship
19. Initial broker discovery and cluster topology
20. Partition redistribution when group membership changes

---

## Study Tips for Retake

If you need to retake this quiz, focus on:
1. Understanding the core Kafka architecture components
2. Grasping the relationship between topics, partitions, and replication
3. Learning the purpose and behavior of consumer groups
4. Understanding basic configuration parameters
5. Practicing with command-line tools

## Next Steps

After completing this quiz:
- Review any incorrect answers
- Complete the hands-on labs if you haven't already
- Move on to the operations quiz
- Begin working on the project assignment

---

**Good luck with your assessment!** 🚀