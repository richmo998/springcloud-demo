package com.demo.activemq.consumer;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class MessageConsumer {


    @JmsListener(destination = "test.queue")
    public void receiveQueue(String text) {

        System.out.println("Consumer收到的报文为:"+text);
    }

}
