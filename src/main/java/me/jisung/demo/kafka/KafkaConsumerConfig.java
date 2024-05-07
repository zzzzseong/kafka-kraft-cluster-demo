package me.jisung.demo.kafka;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConsumerConfig {


    @Value("${kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Object> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, Object> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());

        return factory;
    }

    private ConsumerFactory<String, Object> consumerFactory() {
        Map<String, Object> props = new HashMap<>();

        // kafka cluster address setting
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);

        // string->object deserializer setting
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);

        // consumer group setting
        props.put(ConsumerConfig.GROUP_ID_CONFIG, KafkaConst.CONSUMER_GROUP);

        // earliest : 가장 처음부터 읽기, latest : 가장 마지막부터 읽기
        // consumer offset 을 사용할 수 없는 상태이거나 offset 정보를 찾을 수 없을떄의 option
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        // auto commit: true
        // manual commit: false
        /* props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false); */

        // record를 읽어들이는 최소 byte size setting (default: 1byte)
        // broker는 지정된 fetch.min.bytes 이상의 데이터를 받을때까지 대기한다.
         props.put(ConsumerConfig.FETCH_MIN_BYTES_CONFIG, 1000);
         props.put(ConsumerConfig.FETCH_MAX_WAIT_MS_CONFIG, 10000);

        return new DefaultKafkaConsumerFactory<>(props);
    }
}