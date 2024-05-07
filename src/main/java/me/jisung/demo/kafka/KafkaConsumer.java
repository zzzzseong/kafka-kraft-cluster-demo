package me.jisung.demo.kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j(topic = "KafkaConsumer")
public class KafkaConsumer {

    /**
     * demo topic consume (auto commit)
     * @param records consumed message
     * */
    @KafkaListener(topics = KafkaConst.KAFKA_TOPIC_DEMO)
    public void consumeDemo(ConsumerRecords<String, String> records) {
        for (ConsumerRecord<String, String> record : records) log.info("{}", record);
    }

    /**
     * demo_manual topic consume (manual commit)
     * @param records consumed message(List for fetch.min.bytes option)
     * @param ack acknowledgment for manual commit
     * */
    @KafkaListener(topics = KafkaConst.KAFKA_TOPIC_DEMO_MANUAL)
    public void consumeDemoManual(ConsumerRecords<String, String> records, Acknowledgment ack) {

        for (ConsumerRecord<String, String> record : records) log.info("{}", record);

        // data를 정상적으로 consume하고 partition offset을 manual commit.
        ack.acknowledge();
    }
}