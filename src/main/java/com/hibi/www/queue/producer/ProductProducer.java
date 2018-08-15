package com.hibi.www.queue.producer;

import com.hibi.www.queue.interfa.ProductSendMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

import javax.jms.Queue;

/**
 * 消息生产者接口实现类
 * 作者：penglei
 * 日期：2018年8月14日15:21:28
 * */
@Service
public class ProductProducer implements ProductSendMessage{

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Autowired
    private Queue activeMQQueue;


    /**
     * 消息发送方法，接口实现方法
     * */
    @Override
    public void sendMessage(Object message) {
        this.jmsMessagingTemplate.convertAndSend(this.activeMQQueue,message);
    }
}
