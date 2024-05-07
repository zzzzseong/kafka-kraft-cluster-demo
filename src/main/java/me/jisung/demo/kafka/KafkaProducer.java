package me.jisung.demo.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
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
    public void produce(String topic, String message) throws Exception {
        ProducerRecord<String, Object> record = new ProducerRecord<>(topic, message);

        SendResult<String, Object> result = kafkaTemplate.send(record).get();

        log.info("{}", result.toString());
    }

    /**
     * hash key 지정하여 produce
     * @param topic topic name
     * @param key record hash key
     * @param message produce message
     * */
    public void produce(String topic, String key, String message) throws Exception {
        ProducerRecord<String, Object> record = new ProducerRecord<>(topic, key, message);

        SendResult<String, Object> result = kafkaTemplate.send(record).get();

        log.info("{}", result.toString());
    }

    /**
     * hash key 및 partition number 지정하여 produce
     * @param topic topic name
     * @param partitionNo partition number
     * @param key record hash key
     * @param message produce message
     * */
    public void produce(String topic, Integer partitionNo, String key, String message) throws Exception {
        ProducerRecord<String, Object> record = new ProducerRecord<>(topic, partitionNo, key, message);

        SendResult<String, Object> result = kafkaTemplate.send(record).get();

        log.info("{}", result.toString());
    }
}