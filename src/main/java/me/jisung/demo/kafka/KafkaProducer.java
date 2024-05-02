package me.jisung.demo.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j(topic = "KafkaProducer")
public class KafkaProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void produce(String topic, String message) {
        log.info("Produced message: {}", message);
        kafkaTemplate.send(topic, message);
    }
}
