package com.hibi.www.queue.consumer;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
/**
 * 消息接收服务类，组件
 * 用于接收相应队列中的消息
 * 作者：penglei
 * 日期：2018年8月14日15:25:50
 * */
@Component
public class ProductConsumer {

    /**
     * 队列消息接收方法
     * JmsListener监听指定消息队列
     * 作者：penglei
     * 日期：2018年8月14日15:28:14
     * */
    @JmsListener(destination = "jim.queue.product",containerFactory = "jmsListenerContainerFactory")
    public void receiveQueue(String text){
        System.out.println("Consumer,prodId:"+text);
    }
}
