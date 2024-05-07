package me.jisung.demo.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j(topic = "KafkaProducer")
public class KafkaProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    /**
     * basic record produce
     * @param topic topic name
     * @param message produce message
     * */
    public void produce(String topic, String message) {
        log.info("Produced message: {}", message);

        ProducerRecord<String, Object> record = new ProducerRecord<>(topic, message);

        kafkaTemplate.send(record);
    }

    /**
     * record hash key 지정하여 produce
     * @param topic topic name
     * @param key record hash key
     * @param message produce message
     * */
    public void produceWithKey(String topic, String key, String message) {
        log.info("Produced message with hash key: {}-{}", key, message);

        ProducerRecord<String, Object> record = new ProducerRecord<>(topic, key, message);

        kafkaTemplate.send(record);
    }

    /**
     * partition number 지정하여 produce
     * @param topic topic name
     * @param partitionNo partition number
     * @param key record hash key
     * @param message produce message
     * */
    public void produceWithPartitionNoAndKey(String topic, Integer partitionNo, String key, String message) {
        log.info("Produced message with partition and hash key: {}-{}-{}", partitionNo, key, message);

        ProducerRecord<String, Object> record = new ProducerRecord<>(topic, partitionNo, key, message);

        kafkaTemplate.send(record);
    }
}