package com.hibi.www.queue;

import org.springframework.stereotype.Service;


@Service
public class Consumer3 {

//    // ConnectionFactory ：连接工厂，JMS 用它创建连接
//    private static ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER,
//            ActiveMQConnection.DEFAULT_PASSWORD, "tcp://localhost:61616");
//    private static String message="";
//    public String  getMessageQueue()  throws JMSException {
//
//        // Connection ：JMS 客户端到JMS Provider 的连接
//        final Connection connection =  connectionFactory.createConnection();
//        connection.start();
//        // Session： 一个发送或接收消息的线程
//        final Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
//        // Destination ：消息的目的地;消息送谁那获取.
//        Destination destination =  session.createTopic("STOCKS.myTopic");
////        Destination destination =  session.createQueue("STOCKS.myTopic");
//
//        // 消费者，消息接收者
//        MessageConsumer consumer1 =  session.createConsumer(destination);
//        consumer1.setMessageListener(new MessageListener() {
//            @Override
//            public void onMessage(Message msg) {
//                try {
//                    TextMessage message = (TextMessage)msg ;
//                    message = message;
//                    System.out.println("consumerOne收到消息： "+message.getText());
//                    session.commit();
//                } catch (JMSException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//        return message;
//    }
}
