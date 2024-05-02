package me.jisung.demo.kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j(topic = "KafkaConsumer")
public class KafkaConsumer {

    /**
     * demo topic consume (auto commit)
     * @param message consumed message
     * */
    @KafkaListener(topics = KafkaConst.KAFKA_TOPIC_DEMO)
    public void consumeDemo(String message) {
        log.info("Consumed message using string: {}", message);
    }

    /**
     * demo topic consume (auto commit)
     * @param payloads consumed message(List for fetch.min.bytes option)
     * */
    @KafkaListener(topics = KafkaConst.KAFKA_TOPIC_DEMO)
    public void consumeDemo(List<ConsumerRecord<Integer, String>> payloads) {
        for (ConsumerRecord<Integer, String> payload : payloads) {
            String message = payload.value();
            log.info("Consumed message using consumer record: {}", message);
        }
    }

    /**
     * demo_manual topic consume (manual commit)
     * @param payloads consumed message(List for fetch.min.bytes option)
     * @param ack acknowledgment for manual commit
     * */
    @KafkaListener(topics = KafkaConst.KAFKA_TOPIC_DEMO_MANUAL)
    public void consumeDemoManual(List<ConsumerRecord<Integer, String>> payloads, Acknowledgment ack) {
        for (ConsumerRecord<Integer, String> payload : payloads) {
            String message = payload.value();
            log.info("Consumed message: {}", message);
        }
        ack.acknowledge();
    }
}