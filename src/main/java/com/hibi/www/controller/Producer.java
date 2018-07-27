package com.hibi.www.controller;


import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@EnableAutoConfiguration
@RequestMapping("producer")
public class Producer {
//    @Autowired
//    private JmsMessagingTemplate jmsMessagingTemplate;
//
//    @Autowired
//    private Queue queue;
//
//
//    @Autowired
//    private Sender sender;
//
//
//    @Autowired
//    private Consumer3 consumer3;
//
//
//    @RequestMapping("/sendMsg")
//    public void send(String msg) {
////        this.jmsMessagingTemplate.convertAndSend(this.queue, msg);
//        try {
//            System.out.print("-------------------"+msg);
//            sender.sendMessageQueue(msg);
//        } catch (JMSException e) {
//            e.printStackTrace();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }
//
//
//    @RequestMapping("/reciver")
//    @ResponseBody
//    public String reciver(){
//        try {
//            System.out.print("-------------------"+consumer3.getMessageQueue());
//            return consumer3.getMessageQueue();
//        } catch (JMSException e) {
//            e.printStackTrace();
//        }
//        return "";
//    }


}
