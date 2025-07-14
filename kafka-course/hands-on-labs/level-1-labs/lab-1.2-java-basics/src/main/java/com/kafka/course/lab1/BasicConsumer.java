package com.kafka.course.lab1;

import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.Arrays;
import java.util.Collections;
import java.util.Properties;
import java.util.Set;

/**
 * Basic Kafka Consumer Example
 * 
 * This class demonstrates the fundamental concepts of a Kafka consumer:
 * - Configuration setup
 * - Message consumption with different strategies
 * - Offset management
 * - Consumer group behavior
 * - Error handling
 * - Resource management
 */
public class BasicConsumer {
    
    private static final Logger logger = LoggerFactory.getLogger(BasicConsumer.class);
    private static final String TOPIC_NAME = "java-basics-topic";
    private static final String BOOTSTRAP_SERVERS = "localhost:9092";
    private static final String GROUP_ID = "java-basics-group";
    
    private final Consumer<String, String> consumer;
    private volatile boolean running = true;
    
    public BasicConsumer() {
        this.consumer = createConsumer();
    }
    
    /**
     * Create and configure a Kafka consumer
     */
    private Consumer<String, String> createConsumer() {
        Properties props = new Properties();
        
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
        
        return new KafkaConsumer<>(props);
    }
    
    /**
     * Subscribe to a topic and start consuming messages
     */
    public void consumeMessages() {
        try {
            // Subscribe to the topic
            consumer.subscribe(Arrays.asList(TOPIC_NAME));
            logger.info("Subscribed to topic: {}", TOPIC_NAME);
            
            // Start consuming messages
            while (running) {
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
                
                if (!records.isEmpty()) {
                    logger.info("Received {} records", records.count());
                    
                    for (ConsumerRecord<String, String> record : records) {
                        processRecord(record);
                    }
                }
            }
            
        } catch (Exception e) {
            logger.error("Error consuming messages", e);
        } finally {
            close();
        }
    }
    
    /**
     * Process a single consumer record
     */
    private void processRecord(ConsumerRecord<String, String> record) {
        logger.info("Received message: topic={}, partition={}, offset={}, key={}, value={}", 
                   record.topic(), record.partition(), record.offset(), record.key(), record.value());
        
        // Simulate some processing time
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            logger.warn("Processing interrupted");
        }
    }
    
    /**
     * Consume messages with manual offset commit
     */
    public void consumeMessagesWithManualCommit() {
        try {
            // Disable auto-commit
            Properties manualProps = new Properties();
            manualProps.putAll(consumer.configs());
            manualProps.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
            
            Consumer<String, String> manualConsumer = new KafkaConsumer<>(manualProps);
            manualConsumer.subscribe(Arrays.asList(TOPIC_NAME));
            
            logger.info("Starting consumer with manual commit");
            
            while (running) {
                ConsumerRecords<String, String> records = manualConsumer.poll(Duration.ofMillis(100));
                
                if (!records.isEmpty()) {
                    logger.info("Processing {} records with manual commit", records.count());
                    
                    for (ConsumerRecord<String, String> record : records) {
                        processRecord(record);
                    }
                    
                    // Manually commit offsets
                    manualConsumer.commitSync();
                    logger.info("Committed offsets for {} records", records.count());
                }
            }
            
            manualConsumer.close();
            
        } catch (Exception e) {
            logger.error("Error in manual commit consumer", e);
        }
    }
    
    /**
     * Consume messages from specific partitions
     */
    public void consumeFromSpecificPartitions() {
        try {
            // Get topic partitions
            Set<TopicPartition> partitions = consumer.assignment();
            if (partitions.isEmpty()) {
                consumer.subscribe(Arrays.asList(TOPIC_NAME));
                consumer.poll(Duration.ofMillis(1000)); // Trigger assignment
                partitions = consumer.assignment();
            }
            
            logger.info("Consuming from partitions: {}", partitions);
            
            // Seek to beginning of each partition
            for (TopicPartition partition : partitions) {
                consumer.seekToBeginning(Collections.singleton(partition));
                logger.info("Seeked to beginning of partition: {}", partition);
            }
            
            // Consume messages
            while (running) {
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
                
                for (ConsumerRecord<String, String> record : records) {
                    logger.info("Partition-specific message: partition={}, offset={}, key={}, value={}", 
                               record.partition(), record.offset(), record.key(), record.value());
                }
            }
            
        } catch (Exception e) {
            logger.error("Error consuming from specific partitions", e);
        }
    }
    
    /**
     * Demonstrate consumer group behavior
     */
    public void demonstrateConsumerGroup() {
        try {
            consumer.subscribe(Arrays.asList(TOPIC_NAME));
            logger.info("Consumer group: {}", GROUP_ID);
            
            // Get current assignment
            Set<TopicPartition> assignment = consumer.assignment();
            logger.info("Current assignment: {}", assignment);
            
            // Poll to trigger assignment
            consumer.poll(Duration.ofMillis(1000));
            assignment = consumer.assignment();
            logger.info("Assignment after poll: {}", assignment);
            
            // Consume some messages
            int messageCount = 0;
            while (running && messageCount < 10) {
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
                
                for (ConsumerRecord<String, String> record : records) {
                    logger.info("Group consumer message: partition={}, offset={}, key={}, value={}", 
                               record.partition(), record.offset(), record.key(), record.value());
                    messageCount++;
                }
            }
            
        } catch (Exception e) {
            logger.error("Error in consumer group demo", e);
        }
    }
    
    /**
     * Handle consumer rebalancing
     */
    public void handleRebalancing() {
        try {
            consumer.subscribe(Arrays.asList(TOPIC_NAME), new ConsumerRebalanceListener() {
                @Override
                public void onPartitionsRevoked(Collection<TopicPartition> partitions) {
                    logger.info("Partitions revoked: {}", partitions);
                    // Commit offsets for revoked partitions
                    consumer.commitSync();
                }
                
                @Override
                public void onPartitionsAssigned(Collection<TopicPartition> partitions) {
                    logger.info("Partitions assigned: {}", partitions);
                }
            });
            
            logger.info("Consumer with rebalancing listener started");
            
            while (running) {
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
                
                for (ConsumerRecord<String, String> record : records) {
                    processRecord(record);
                }
            }
            
        } catch (Exception e) {
            logger.error("Error in rebalancing consumer", e);
        }
    }
    
    /**
     * Demonstrate error handling
     */
    public void consumeWithErrorHandling() {
        try {
            consumer.subscribe(Arrays.asList(TOPIC_NAME));
            
            while (running) {
                try {
                    ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
                    
                    for (ConsumerRecord<String, String> record : records) {
                        try {
                            processRecord(record);
                        } catch (Exception e) {
                            logger.error("Error processing record: partition={}, offset={}", 
                                       record.partition(), record.offset(), e);
                            // Continue processing other records
                        }
                    }
                    
                } catch (org.apache.kafka.common.errors.WakeupException e) {
                    logger.info("Consumer wakeup requested");
                    break;
                } catch (org.apache.kafka.common.errors.InterruptException e) {
                    logger.info("Consumer interrupted");
                    break;
                } catch (Exception e) {
                    logger.error("Unexpected error in consumer loop", e);
                    // Continue polling
                }
            }
            
        } catch (Exception e) {
            logger.error("Error in error handling consumer", e);
        }
    }
    
    /**
     * Stop the consumer
     */
    public void stop() {
        logger.info("Stopping consumer...");
        running = false;
        consumer.wakeup();
    }
    
    /**
     * Close the consumer and release resources
     */
    public void close() {
        logger.info("Closing consumer...");
        consumer.close();
        logger.info("Consumer closed successfully");
    }
    
    /**
     * Main method to demonstrate the consumer
     */
    public static void main(String[] args) {
        BasicConsumer consumer = new BasicConsumer();
        
        // Add shutdown hook
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            logger.info("Shutdown hook triggered");
            consumer.stop();
        }));
        
        try {
            logger.info("=== Starting Basic Consumer Demo ===");
            
            // Choose which demo to run based on command line argument
            String demo = args.length > 0 ? args[0] : "basic";
            
            switch (demo) {
                case "manual-commit":
                    consumer.consumeMessagesWithManualCommit();
                    break;
                case "specific-partitions":
                    consumer.consumeFromSpecificPartitions();
                    break;
                case "consumer-group":
                    consumer.demonstrateConsumerGroup();
                    break;
                case "rebalancing":
                    consumer.handleRebalancing();
                    break;
                case "error-handling":
                    consumer.consumeWithErrorHandling();
                    break;
                default:
                    consumer.consumeMessages();
                    break;
            }
            
        } catch (Exception e) {
            logger.error("Consumer demo error", e);
        } finally {
            consumer.close();
        }
    }
}