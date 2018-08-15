package com.hibi.www.queue.interfa;
/**
 * 接口类，自定义发送消息队列
 * 作者：penglei
 * 日期：2018年8月14日15:18:58
 * */
public interface ProductSendMessage {

    void sendMessage(Object message);
}
