package com.hibi.www.service.impl;

import com.hibi.www.service.ProducerService;
import org.springframework.stereotype.Service;


@Service
public class IProducerService implements ProducerService {
//    @Autowired // 也可以注入JmsTemplate，JmsMessagingTemplate对JmsTemplate进行了封装
//    private JmsMessagingTemplate jmsTemplate;
//    // 发送消息，destination是发送到的队列，message是待发送的消息
//    @Override
//    public void sendMessage(Destination destination, final String message){
//        jmsTemplate.convertAndSend(destination, message);
//    }
//
//    @Override
//    @JmsListener(destination = "out.queue")
//    public void consumerMessage(String text) {
//        System.out.print("从out.queue队列收到的回复报文为："+text);
//    }
}
