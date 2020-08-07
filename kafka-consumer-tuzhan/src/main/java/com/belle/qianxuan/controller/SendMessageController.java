package com.belle.qianxuan.controller;

import com.belle.qianxuan.kafka.producer.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SendMessageController {
    @Autowired
    KafkaProducer kafkaProducer;
    @GetMapping("/test/{msg}")
    public String sendMsg(@PathVariable("msg") String msg){
        kafkaProducer.createMessage(msg);
        return "已推送到kafka: "+ msg;
    }
    @GetMapping("/test")
    public String test(){
        return "helloword";
    }
}
