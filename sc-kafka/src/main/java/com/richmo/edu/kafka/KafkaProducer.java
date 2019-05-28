package com.richmo.edu.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer {

    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;

    public void sendMessage(){

        for(int i = 0 ;i<100;i++){
            kafkaTemplate.send("testgroup1","welcome："+i);

        }

    }

}
