package me.jisung.demo.controller;

import lombok.RequiredArgsConstructor;
import me.jisung.demo.dto.MessageRequestDto;
import me.jisung.demo.kafka.KafkaProducer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/kafka/demo")
public class DemoController {

    private final KafkaProducer kafkaProducer;

    @PostMapping("/produce/topicA")
    public ResponseEntity<Void> produce(
            @RequestBody MessageRequestDto request
    ) {
        kafkaProducer.produce("topicA", request.getMessage());
        return ResponseEntity.ok().build();
    }
}
