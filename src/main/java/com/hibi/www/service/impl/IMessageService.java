package com.hibi.www.service.impl;

import com.hibi.www.dao.mapper.MessageMapper;
import com.hibi.www.domain.Message;
import com.hibi.www.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 保存消息业务实现类
 * 类型：类（class）
 * 作者：penglei
 * 日期：2018年8月1日
 */


@Service("messageService")
public class IMessageService implements MessageService{

    /**
     * 自动注入消息持久层
     */
    @Autowired
    MessageMapper messageMapper;



    /**
     * 功能：保存消息
     * @param message
     * @return int
     * 作者：penglei
     */
    @Override
    public int saveMessage(Message message) {
        //调用messageMapper保存消息
        int insert = messageMapper.insert(message);
        return insert;
    }

}
