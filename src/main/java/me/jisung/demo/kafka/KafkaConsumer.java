package me.jisung.demo.kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

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
        handleRecord(records);
    }

    /**
     * demo_manual topic consume (manual commit)
     * @param records consumed message(List for fetch.min.bytes option)
     * @param ack acknowledgment for manual commit
     * */
    @KafkaListener(topics = KafkaConst.KAFKA_TOPIC_DEMO_MANUAL)
    public void consumeDemoManual(ConsumerRecords<String, String> records, Acknowledgment ack) {
        handleRecord(records);
        ack.acknowledge();
    }

    @KafkaListener(topics = KafkaConst.KAFKA_TOPIC_STREAMS_FILTER)
    public void streams(ConsumerRecords<String, String> records) {
        handleRecord(records);
    }


    private void handleRecord(ConsumerRecords<String, String> records) {
        // Schedulers.boundsElastic()
        // Reactor에서 제공하는 Scheduler로 비동기 작업을 처리하고, boundedElastic으로 동적으로 크기를 조절하는 스레드 풀 사용

        Flux.fromIterable(records)
                .subscribeOn(Schedulers.boundedElastic())
                .doOnNext(record -> log.info("{}", record))
                .doOnError((e) -> log.error(e.getMessage(), e))
                .subscribe();
    }
}