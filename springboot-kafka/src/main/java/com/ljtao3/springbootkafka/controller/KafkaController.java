package com.ljtao3.springbootkafka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ljtao
 * @date 2020/4/25
 */
@RestController
@RequestMapping("kafka")
public class KafkaController {
    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;
    @RequestMapping("send")
    public String send(String name){
        kafkaTemplate.send("sendTopic",name);
        for (int i = 0; i < 5; i++) {
            kafkaTemplate.send("sendTopic",name+i);
        }
        return "success";
    }
}
