package com.hibi.www.tools;

import com.hibi.www.domain.Message;
import com.hibi.www.service.impl.IMessageService;
import org.aspectj.bridge.IMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * 保存消息工具类
 * 作者：penglei
 * 日期：2018年8月1日
 */
@Component
public class SaveMessage {

    @Autowired
    IMessageService messageService;
    /**
     *
     * @param message
     */
    public void  insertMessage(Message message){
        messageService.saveMessage(message);
    }
}
