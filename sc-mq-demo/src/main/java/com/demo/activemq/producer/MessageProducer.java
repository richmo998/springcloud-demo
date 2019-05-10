package com.demo.activemq.producer;

import com.demo.activemq.ActiveMqApplicationDemo;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.jms.Destination;

@Service("messageProducer")
public class MessageProducer {

    // 也可以注入JmsTemplate，JmsMessagingTemplate对JmsTemplate进行了封装
    @Autowired
    private JmsMessagingTemplate jmsTemplate;
    // 发送消息，destination是发送到的队列，message是待发送的消息

    private void sendMessage(Destination destination, final String message){
        jmsTemplate.convertAndSend(destination, message);
    }

    /**
     * 发送消息
     * @param queueName
     * @param message
     */
    public void sendMessage(String queueName,String message){
        if(StringUtils.isEmpty(queueName)){
            System.out.println("queueName不能为空");
            return ;
        }
        Destination destination = new ActiveMQQueue(queueName);
        sendMessage(destination,message);
    }

}
