package com.ljtao.springbootrocketmq.message;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.springframework.stereotype.Component;

/**
 * @author ljtao3 on 2020/4/3
 */
@Slf4j
@Component
public class Producer {
    private String producerGroup="test_producer";
    private DefaultMQProducer producer;
    public Producer(){
        //实例生产者
        producer=new DefaultMQProducer(producerGroup);
        //不开启vip通道， 开通端口会减2
        producer.setVipChannelEnabled(false);
        //绑定name server
        producer.setNamesrvAddr(MQConfig.NAME_SERVER);
        start();
    }

    private void start() {
        try {
            this.producer.start();
        } catch (MQClientException e) {
            e.printStackTrace();
        }
    }
    public DefaultMQProducer getProducer(){
        return this.producer;
    }
    //一般在应用上下文，使用上下文监听器，进行关闭
    public void shutdown(){
        this.producer.shutdown();
    }
}
