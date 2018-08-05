package com.hibi.www.socket;

import com.hibi.www.dao.mapper.MessageMapper;
import com.hibi.www.domain.Message;
import com.hibi.www.service.MessageService;
import com.hibi.www.service.impl.IMessageService;
import com.hibi.www.service.impl.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * 功能：页面聊天（简版）
 * 作者：penglei
 * 日期：2018年8月1日
 */
@ServerEndpoint(value = "/eoch")
@Component
@ComponentScan
public class MyWebSocket {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());


    /**
     * 自动注入消息服务类
     */
    @Autowired
    protected  IMessageService messageService;

    private static MyWebSocket myWebSocket;

    @PostConstruct
    public void init(){
        myWebSocket = this;
        myWebSocket.messageService = this.messageService;
    }

    /**
     * 描述：在线计数器
     */
    private static int onlineCount = 0;
    private static CopyOnWriteArraySet<MyWebSocket> webSocketSet = new CopyOnWriteArraySet<>();
    /**
     * 描述：session
     */
    private Session session;


    /**
     * 功能：开启聊天方法
     * @param session
     * @throws IOException
     */
    @OnOpen
    public void onOpen(Session session) throws IOException{
        this.session = session;
        webSocketSet.add(this);
        incrOnlineCount();
        for(MyWebSocket item : webSocketSet){
            if(!item.equals(this)) { //send to others only.
                item.sendMessage("欢迎加入聊天室!");
            }
        }
        logger.info("当前连接人数: {}", getOnlineCount());
    }

    /**
     * 功能：关闭聊天方法
     * @throws IOException
     */
    @OnClose
    public void onClose() throws IOException{
        webSocketSet.remove(this);
        decOnlineCount();
        for(MyWebSocket item : webSocketSet){
            item.sendMessage("有人退出聊天室.");
        }
        logger.info("有一个连接关闭，当前人数: {}", getOnlineCount());
    }


    /**
     * 遍历消息方法
     * @param message
     * @param session
     * @throws IOException
     */
    @OnMessage
    public void onMessage(String message, Session session) throws IOException {
        Message message1 = new Message();
        message1.setMessage(message);
        message1.setUser("-");
        myWebSocket.messageService.saveMessage(message1);
        logger.info("message received: {}", message);
        // broadcast received message
        for(MyWebSocket item : webSocketSet){
            item.sendMessage(message);
        }
    }

    /**
     * 功能：发送消息，进行交互
     * @param message
     * @throws IOException
     */
    public void sendMessage(String message) throws IOException {

        this.session.getBasicRemote().sendText(message);
    }

    public static synchronized int getOnlineCount(){
        return MyWebSocket.onlineCount;
    }

    public static synchronized void incrOnlineCount(){
        MyWebSocket.onlineCount++;
    }

    public static synchronized void decOnlineCount(){
        MyWebSocket.onlineCount--;
    }

}
