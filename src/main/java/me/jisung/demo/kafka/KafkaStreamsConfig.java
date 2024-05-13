package me.jisung.demo.kafka;

import jakarta.annotation.PostConstruct;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaStreamsConfig {

    @Bean
    public KafkaStreams kafkaStreams() {
        return new KafkaStreams(topology(), streamsConfig());
    }

    private Topology topology() {
        StreamsBuilder builder = new StreamsBuilder();
        KStream<String, String> kStream = builder.stream(KafkaConst.KAFKA_TOPIC_STREAMS);
        kStream.filter((key, value) -> value.length() > 5).to(KafkaConst.KAFKA_TOPIC_STREAMS_FILTER);
        return builder.build();
    }

    private StreamsConfig streamsConfig() {
        Map<String, Object> props = new HashMap<>();

        props.put(StreamsConfig.APPLICATION_ID_CONFIG, KafkaConst.STREAMS_ID);
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, KafkaConst.BOOTSTRAP_SERVER);

        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());

        return new StreamsConfig(props);
    }
}
