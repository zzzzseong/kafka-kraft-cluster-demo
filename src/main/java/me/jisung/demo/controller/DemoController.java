package me.jisung.demo.controller;

import lombok.RequiredArgsConstructor;
import me.jisung.demo.dto.MessageRequestDto;
import me.jisung.demo.kafka.KafkaConst;
import me.jisung.demo.kafka.KafkaProducer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/kafka")
public class DemoController {

    private final KafkaProducer kafkaProducer;

    @PostMapping("/produce/demo")
    public ResponseEntity<Void> produce(@RequestBody MessageRequestDto request) throws Exception {
        kafkaProducer.produce(KafkaConst.KAFKA_TOPIC_DEMO, request.getMessage());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/produce/demo2")
    public ResponseEntity<Void> produce(
            @RequestBody MessageRequestDto request,
            @RequestParam(name = "key") String key
    ) throws Exception {
        kafkaProducer.produce(KafkaConst.KAFKA_TOPIC_DEMO, key, request.getMessage());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/produce/demo3")
    public ResponseEntity<Void> produce(
            @RequestBody MessageRequestDto request,
            @RequestParam(name = "partitionNo") Integer partitionNo,
            @RequestParam(name = "key") String key
    ) throws Exception {
        kafkaProducer.produce(KafkaConst.KAFKA_TOPIC_DEMO, partitionNo, key, request.getMessage());
        return ResponseEntity.ok().build();
    }
}
