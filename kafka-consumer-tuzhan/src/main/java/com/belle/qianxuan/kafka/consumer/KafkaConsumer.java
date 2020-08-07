package com.belle.qianxuan.kafka.consumer;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

@EnableBinding(Sink.class)
public class KafkaConsumer {

    private static Logger log = LogManager.getLogger("AsyncLogger");

    @StreamListener(Sink.INPUT)
    public void receiverMsg(Object msg){
        log.info(log);
       log.info("spring cloud stream 接收到消息：" +msg);
    }
}
