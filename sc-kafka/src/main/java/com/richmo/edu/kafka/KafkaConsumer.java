package com.richmo.edu.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {

    @KafkaListener(topics = "testgroup1")
    public void processMessage(String content) {

        System.out.println("接收到kafka消息："+content);
    }
}
