package com.ljtao3.springbootkafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@SpringBootTest
class SpringbootKafkaApplicationTests {
	@Autowired
	private KafkaProducer kafkaProducer;
	@Test
	void contextLoads() {
		System.out.println("ddd");
	}
	@Test
	void testKafka() throws InterruptedException {
		kafkaProducer.send("testTopic2","aaa");
		kafkaProducer.send("testTopic2","bbb");
		Thread.sleep(1000*20);
	}

}
@Component
class KafkaProducer{
	@Autowired
	private KafkaTemplate<String,String> kafkaTemplate;
	public void send(String topic,String data){
		kafkaTemplate.send(topic,data);
	}
}
@Component
class KafkaConsumer{
	@KafkaListener(topics = {"testTopic2"})
	public void listen(ConsumerRecord<?,String> record){
		System.out.println(record.value());
	}
}
