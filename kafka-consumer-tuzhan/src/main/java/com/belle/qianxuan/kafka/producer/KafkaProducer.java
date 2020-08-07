package com.belle.qianxuan.kafka.producer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@EnableBinding(Source.class)
public class KafkaProducer {

    private static Logger log = LogManager.getLogger("AsyncLogger");
    @Autowired
    Source procedureSource;

    public void createMessage(String msg){
        log.info("生产者推送消息："+msg);
       procedureSource.output().send(MessageBuilder.withPayload(msg).build());
    }
}
