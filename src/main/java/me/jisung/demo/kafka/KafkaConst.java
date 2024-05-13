package me.jisung.demo.kafka;

/**
 * interface for store kafka topic literal
 * */
public interface KafkaConst {

    String BOOTSTRAP_SERVER = "localhost:9092,localhost:9094,localhost:9096";
    String CONSUMER_GROUP = "demo-group";
    String STREAMS_ID = "streams-demo";

    /* topics */
    String KAFKA_TOPIC_DEMO = "demo";
    String KAFKA_TOPIC_DEMO_MANUAL = "demo_manual";
    String KAFKA_TOPIC_STREAMS = "streams";
    String KAFKA_TOPIC_STREAMS_FILTER = "streams_filter";
}