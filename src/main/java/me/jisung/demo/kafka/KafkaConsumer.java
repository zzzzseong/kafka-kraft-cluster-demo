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
     * @param record consumed message
     * */
    @KafkaListener(topics = KafkaConst.KAFKA_TOPIC_DEMO)
    public void consumeDemo(ConsumerRecord<String, Object> record) {
        // min.byte or max.wait.ms option을 이용한 batch consume시
        // list로 받을 필요 없이 record로 받아으면 메서드가 여러번 호출된다.
        log.info("Consumed message using consumer record: {}", record.value().toString());
    }

    /**
     * demo_manual topic consume (manual commit)
     * @param record consumed message(List for fetch.min.bytes option)
     * @param ack acknowledgment for manual commit
     * */
    @KafkaListener(topics = KafkaConst.KAFKA_TOPIC_DEMO_MANUAL)
    public void consumeDemoManual(ConsumerRecord<String, Object> record, Acknowledgment ack) {
        log.info("Consumed message: {}", record.value().toString());

        // data를 정상적으로 consume하고 partition offset을 manual commit.
        ack.acknowledge();
    }

    //consume하는 과정에서 ack.acknowledge()를 호출해야만 다음 offset을 읽어오나??
}