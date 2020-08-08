package com.belle.qianxuan.kafka.consumer;


import com.alibaba.druid.support.json.JSONUtils;
import com.belle.qianxuan.util.JSONUtil;
import com.belle.qianxuan.util.ThreadExecutorsUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

@EnableBinding(Sink.class)
public class KafkaConsumer {

    /**
     * 分离记录接收到的所有消息
     * log4j2异步日志记录
     */
    private static Logger kafkaMsgLog = LogManager.getLogger("AsyncLogger");
    /**
     * 普通逻辑日志记录
     */
    private static Logger logger = LogManager.getLogger(KafkaConsumer.class);
    @Autowired
    ThreadExecutorsUtils executorsUtils;

    @StreamListener(Sink.INPUT)
    public void receiverMsg(Object msg){
        //1.先异步将日志记录到文本中
        kafkaMsgLog.info(msg);
        //开启线程池进行异步入库

//       log.info("spring cloud stream 接收到消息：" + JSONUtil.toJson(msg));
    }
}
