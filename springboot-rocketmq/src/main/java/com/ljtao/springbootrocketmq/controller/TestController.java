package com.ljtao.springbootrocketmq.controller;

import com.alibaba.fastjson.JSONObject;
import com.ljtao.springbootrocketmq.message.MQConfig;
import com.ljtao.springbootrocketmq.message.Producer;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * @author ljtao3 on 2020/4/3
 */
@Slf4j
@RestController
@RequestMapping("test")
public class TestController {
    @Autowired
    private Producer producer;
    /*
        测试将消息放到mq中，然后消费
     */
    @GetMapping("/fun1")
    public String fun1(String id) throws Exception {
        String str="消息1:"+id;
        Message message=new Message(MQConfig.TOPIC,str.getBytes());
        return id;
    }

    /*

     */
    @GetMapping("/message")
    public  void  message() throws Exception {
        //同步
        sync();
        //异步
        async();
        //单项发送
        oneWay();
    }
    /**
     * 1、同步发送消息
     */
    private  void sync() throws Exception {
        //创建消息
        Message message = new Message("topic_family", ("  同步发送  ").getBytes());
        //同步发送消息
        SendResult sendResult = producer.getProducer().send(message);
        log.info("Product-同步发送-Product信息={}", sendResult);
    }
    /**
     * 2、异步发送消息
     */
    private  void async() throws Exception {
        //创建消息
        Message message = new Message("topic_family", ("  异步发送  ").getBytes());
        //异步发送消息
        producer.getProducer().send(message, new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                log.info("Product-异步发送-输出信息={}", sendResult);
            }
            @Override
            public void onException(Throwable e) {
                e.printStackTrace();
                //补偿机制，根据业务情况进行使用，看是否进行重试
            }
        });
    }
    /**
     * 3、单项发送消息
     */
    private  void oneWay() throws Exception {
        //创建消息
        Message message = new Message("topic_family", (" 单项发送 ").getBytes());
        //同步发送消息
        producer.getProducer().sendOneway(message);
    }

    /*
    将数据实体对象，转成String类型再发送，
     */
    @GetMapping("/fun2")
    public String fun2(String id) throws Exception {
        HashMap<String,String> hm=new HashMap();
        hm.put("001","张三");
        hm.put("002","李四");
        hm.put("003","王五");
        String s = JSONObject.toJSONString(hm);
        Message message=new Message(MQConfig.TOPIC,s.getBytes());
        SendResult send = producer.getProducer().send(message);
        log.info("消息已发送：{}",send);
        return id;
    }
}
