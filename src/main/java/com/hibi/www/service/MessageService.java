package com.hibi.www.service;


import com.hibi.www.domain.Message;

/**
 * 功能：消息服务类（逻辑），保存消息
 * 类型：接口（interface）
 * 作者：penglei
 * 日期：2018年8月1日
 *
 */
public interface MessageService {


    /**
     * 保存消息方法
     * 返回值：i
     */
    public int saveMessage(Message message);

}
