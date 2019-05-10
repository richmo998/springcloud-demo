package com.demo.activemq.controller;

import com.demo.activemq.consumer.MessageConsumer;
import com.demo.activemq.producer.MessageProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/activemq")
public class TestActiveMq {
    @Autowired
    MessageProducer messageProducer;

    @Autowired
    MessageConsumer messageConsumer;

    private String queueName="test.queue";

    @GetMapping(value = "/sendMessage")
    public String sendMessage(@RequestParam String msg){
        System.out.println("queueName="+queueName);
        System.out.println("msg="+msg);

        for(int i = 0;i<10;i++){
            messageProducer.sendMessage(queueName,msg+"_"+i);
        }

        return "send success";
    }


}
