package com.richmo.edu.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class KafkaController {

    @Autowired
    KafkaProducer kafkaProducer;

    @GetMapping("/kafka")
    public void testKafka(){
        kafkaProducer.sendMessage();

    }


}
