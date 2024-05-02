package me.jisung.demo.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j(topic = "KafkaConsumer")
public class KafkaConsumer {

    /**
     * demo topic consume
     * @param message consumed message
     * */
    @KafkaListener(topics = KafkaConst.KAFKA_TOPIC_DEMO)
    public void consumeDemo(String message) {
        log.info("Consumed message: {}", message);
    }
}