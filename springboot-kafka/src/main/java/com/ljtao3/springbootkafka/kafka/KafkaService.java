package com.ljtao3.springbootkafka.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @author ljtao
 * @date 2020/4/25
 */
@Component
public class KafkaService {
    @KafkaListener(topics = {"sendTopic"} )
    public void listen(ConsumerRecord<?,String> record){
        String value = record.value();
        System.out.println(value);
        System.out.println(record);
    }
}
