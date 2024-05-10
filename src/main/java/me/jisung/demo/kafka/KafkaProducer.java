package me.jisung.demo.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
@Slf4j(topic = "KafkaProducer")
public class KafkaProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    /**
     * basic record produce
     * @param topic topic name
     * @param message produce message
     * */
    public void produce(String topic, String message) throws Exception {
        ProducerRecord<String, String> record = new ProducerRecord<>(topic, message);

        // synchronous send - timeout 10s
        SendResult<String, String> result = kafkaTemplate.send(record).get(10, TimeUnit.SECONDS);
        handleResult(result);

        // asynchronous send
        CompletableFuture<SendResult<String, String>> future = kafkaTemplate.send(record);
        future.whenComplete((r, e) -> handleResult(r));
    }

    /**
     * hash key 지정하여 produce
     * @param topic topic name
     * @param key record hash key
     * @param message produce message
     * */
    public void produce(String topic, String key, String message) throws Exception {
        ProducerRecord<String, String> record = new ProducerRecord<>(topic, key, message);

        SendResult<String, String> result = kafkaTemplate.send(record).get();
        handleResult(result);
    }

    /**
     * hash key 및 partition number 지정하여 produce
     * @param topic topic name
     * @param partitionNo partition number
     * @param key record hash key
     * @param message produce message
     * */
    public void produce(String topic, Integer partitionNo, String key, String message) throws Exception {
        ProducerRecord<String, String> record = new ProducerRecord<>(topic, partitionNo, key, message);

        SendResult<String, String> result = kafkaTemplate.send(record).get();
        handleResult(result);
    }

    private void handleResult(SendResult<String, String> result) {
        log.info("{}", result);
    }
}