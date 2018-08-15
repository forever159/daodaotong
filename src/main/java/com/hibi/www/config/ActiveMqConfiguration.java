package com.hibi.www.config;


import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.core.JmsMessagingTemplate;

import javax.jms.Queue;

/**
 * ActiveMQ队列配置类
 * 作者：penglei
 * 日期：2018年8月14日15:13:55
 * */

@ComponentScan("com.hibi.www")
@Configuration
public class ActiveMqConfiguration {
    private static final String BROKER_URL="failover:(tcp://127.0.0.1:61616," +
            "tcp://127.0.0.1:61617," +
            "tcp://127.0.0.1:61618," +
            "tcp://127.0.0.1:61619)";


    @Bean
    public Queue productActiveMQQueue(){
        return  new ActiveMQQueue("jim.queue.product");
    }

    @Bean
    public JmsListenerContainerFactory<?> jmsListenerContainerFactory(){
        DefaultJmsListenerContainerFactory defaultBean= new DefaultJmsListenerContainerFactory();
        defaultBean.setConnectionFactory(new ActiveMQConnectionFactory(BROKER_URL));
        return defaultBean;
    }

    @Bean
    public JmsMessagingTemplate jmsMessagingTemplate(){
        return new JmsMessagingTemplate(new ActiveMQConnectionFactory(BROKER_URL));
    }


}
