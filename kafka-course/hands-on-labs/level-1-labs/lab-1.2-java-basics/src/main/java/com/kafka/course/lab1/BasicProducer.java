package com.kafka.course.lab1;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Basic Kafka Producer Example
 * 
 * This class demonstrates the fundamental concepts of a Kafka producer:
 * - Configuration setup
 * - Message sending with different delivery semantics
 * - Error handling
 * - Callback usage
 * - Resource management
 */
public class BasicProducer {
    
    private static final Logger logger = LoggerFactory.getLogger(BasicProducer.class);
    private static final String TOPIC_NAME = "java-basics-topic";
    private static final String BOOTSTRAP_SERVERS = "localhost:9092";
    
    private final Producer<String, String> producer;
    
    public BasicProducer() {
        this.producer = createProducer();
    }
    
    /**
     * Create and configure a Kafka producer
     */
    private Producer<String, String> createProducer() {
        Properties props = new Properties();
        
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
        
        return new KafkaProducer<>(props);
    }
    
    /**
     * Send a message synchronously (blocking)
     */
    public void sendMessageSync(String key, String value) {
        try {
            ProducerRecord<String, String> record = new ProducerRecord<>(TOPIC_NAME, key, value);
            
            logger.info("Sending message synchronously: key={}, value={}", key, value);
            
            Future<RecordMetadata> future = producer.send(record);
            RecordMetadata metadata = future.get(); // This blocks until the record is sent
            
            logger.info("Message sent successfully to topic={}, partition={}, offset={}", 
                       metadata.topic(), metadata.partition(), metadata.offset());
            
        } catch (InterruptedException | ExecutionException e) {
            logger.error("Error sending message synchronously", e);
            Thread.currentThread().interrupt();
        }
    }
    
    /**
     * Send a message asynchronously (non-blocking)
     */
    public void sendMessageAsync(String key, String value) {
        ProducerRecord<String, String> record = new ProducerRecord<>(TOPIC_NAME, key, value);
        
        logger.info("Sending message asynchronously: key={}, value={}", key, value);
        
        producer.send(record, new Callback() {
            @Override
            public void onCompletion(RecordMetadata metadata, Exception exception) {
                if (exception == null) {
                    logger.info("Message sent successfully to topic={}, partition={}, offset={}", 
                               metadata.topic(), metadata.partition(), metadata.offset());
                } else {
                    logger.error("Error sending message asynchronously", exception);
                }
            }
        });
    }
    
    /**
     * Send multiple messages in a batch
     */
    public void sendBatchMessages() {
        String[] messages = {
            "First message in batch",
            "Second message in batch", 
            "Third message in batch",
            "Fourth message in batch",
            "Fifth message in batch"
        };
        
        logger.info("Sending batch of {} messages", messages.length);
        
        for (int i = 0; i < messages.length; i++) {
            String key = "batch-key-" + i;
            String value = messages[i];
            
            ProducerRecord<String, String> record = new ProducerRecord<>(TOPIC_NAME, key, value);
            
            producer.send(record, (metadata, exception) -> {
                if (exception == null) {
                    logger.debug("Batch message sent: key={}, partition={}, offset={}", 
                                key, metadata.partition(), metadata.offset());
                } else {
                    logger.error("Error sending batch message: key={}", key, exception);
                }
            });
        }
        
        // Flush to ensure all messages are sent
        producer.flush();
        logger.info("Batch sending completed");
    }
    
    /**
     * Send messages with different keys to demonstrate partitioning
     */
    public void sendMessagesWithKeys() {
        String[] keys = {"user1", "user2", "user1", "user3", "user2", "user1"};
        String[] values = {
            "Hello from user1",
            "Hello from user2", 
            "Another message from user1",
            "Hello from user3",
            "Another message from user2",
            "Final message from user1"
        };
        
        logger.info("Sending messages with different keys to demonstrate partitioning");
        
        for (int i = 0; i < keys.length; i++) {
            ProducerRecord<String, String> record = new ProducerRecord<>(TOPIC_NAME, keys[i], values[i]);
            
            producer.send(record, (metadata, exception) -> {
                if (exception == null) {
                    logger.info("Message sent: key={}, partition={}, offset={}", 
                               keys[i], metadata.partition(), metadata.offset());
                } else {
                    logger.error("Error sending message: key={}", keys[i], exception);
                }
            });
        }
        
        producer.flush();
    }
    
    /**
     * Demonstrate error handling and retries
     */
    public void sendMessageWithErrorHandling(String key, String value) {
        ProducerRecord<String, String> record = new ProducerRecord<>(TOPIC_NAME, key, value);
        
        logger.info("Sending message with error handling: key={}, value={}", key, value);
        
        producer.send(record, new Callback() {
            @Override
            public void onCompletion(RecordMetadata metadata, Exception exception) {
                if (exception == null) {
                    logger.info("Message sent successfully: topic={}, partition={}, offset={}", 
                               metadata.topic(), metadata.partition(), metadata.offset());
                } else {
                    if (exception instanceof org.apache.kafka.common.errors.RecordTooLargeException) {
                        logger.error("Message too large: {}", exception.getMessage());
                    } else if (exception instanceof org.apache.kafka.common.errors.TimeoutException) {
                        logger.error("Timeout sending message: {}", exception.getMessage());
                    } else {
                        logger.error("Unexpected error sending message", exception);
                    }
                }
            }
        });
    }
    
    /**
     * Close the producer and release resources
     */
    public void close() {
        logger.info("Closing producer...");
        producer.close();
        logger.info("Producer closed successfully");
    }
    
    /**
     * Main method to demonstrate the producer
     */
    public static void main(String[] args) {
        BasicProducer producer = new BasicProducer();
        
        try {
            // Wait a moment for the producer to initialize
            Thread.sleep(1000);
            
            logger.info("=== Starting Basic Producer Demo ===");
            
            // Send a simple message synchronously
            producer.sendMessageSync("sync-key", "Hello Kafka from Java!");
            
            // Send a message asynchronously
            producer.sendMessageAsync("async-key", "Hello Kafka asynchronously!");
            
            // Send batch messages
            producer.sendBatchMessages();
            
            // Send messages with different keys
            producer.sendMessagesWithKeys();
            
            // Send a message with error handling
            producer.sendMessageWithErrorHandling("error-test-key", "Testing error handling");
            
            // Wait for all async operations to complete
            Thread.sleep(2000);
            
            logger.info("=== Basic Producer Demo Completed ===");
            
        } catch (InterruptedException e) {
            logger.error("Demo interrupted", e);
            Thread.currentThread().interrupt();
        } finally {
            producer.close();
        }
    }
}