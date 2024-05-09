package me.jisung.demo.kafka;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.streams.KafkaStreams;
import org.springframework.stereotype.Component;

/**
 * KafkaStreamsConfig
 * */
@Component
@RequiredArgsConstructor
@Slf4j(topic = "KafkaStreamsConsumer")
public class KafkaStreamsStarter {

    private final KafkaStreams kafkaStreams;

    @PostConstruct
    public void start() {
        kafkaStreams.start();
    }
}