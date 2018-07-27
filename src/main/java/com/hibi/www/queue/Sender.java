package com.hibi.www.queue;

import org.springframework.stereotype.Service;

@Service
public class Sender {

    public void sendMessageQueue(String text){
//        // ConnectionFactory ：连接工厂，JMS 用它创建连接
//        //61616是ActiveMQ默认端口
//        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
//                ActiveMQConnection.DEFAULT_USER,
//                ActiveMQConnection.DEFAULT_PASSWORD,
//                "tcp://localhost:61616");
//        // Connection ：JMS 客户端到JMS Provider 的连接
//        Connection connection =  connectionFactory.createConnection();
//        connection.start();
//        // Session： 一个发送或接收消息的线程
//        Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
//        // Destination ：消息的目的地;消息发送给谁.
//        Destination destination =  session.createTopic("STOCKS.myTopic");
////        Destination destination =  session.createQueue("STOCKS.myTopic");
//        // MessageProducer：消息发送者
//        MessageProducer producer =  session.createProducer(destination);
//        // 设置不持久化，可以更改
//        producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
//        //创建文本消息
//        TextMessage message = session.createTextMessage("hello.I am producer, this is a test message"+text);
////            Thread.sleep(1000);
//        //发送消息
//        producer.send(message);
//        session.commit();
//        session.close();
//        connection.close();
    }





    public static void main(String[] args){

    }
}
