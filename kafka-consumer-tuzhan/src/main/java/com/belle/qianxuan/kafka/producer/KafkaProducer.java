package com.belle.qianxuan.kafka.producer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@EnableBinding(Source.class)
public class KafkaProducer {

    private static Logger log = LogManager.getLogger(KafkaProducer.class);
    @Autowired
    Source procedureSource;

    public void createMessage(String msg){
        log.info("生产者推送消息："+msg);
        Map<String,Object> map = new HashMap<>();
        map.put("a","i am a");
        map.put("b","i am b");
        map.put("c","i am c");
        map.put("d","i am d");
       procedureSource.output().send(MessageBuilder.withPayload(map).build());
    }
}
