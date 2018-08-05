package com.hibi.www.controller;

import com.hibi.www.domain.Message;
import com.hibi.www.service.impl.IMessageService;
import org.aspectj.bridge.IMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@EnableAutoConfiguration
@RequestMapping("socket")
public class SocketController {


    @Autowired
    IMessageService messageService;


    /**
     * 聊天跳转控制器
     * @param model
     * @return
     */
    @RequestMapping("/index")
    public String  index(Model model){
        return "socket/index";
    }



    @PostMapping("/saveMessage")
    public String saveMessage(HttpServletRequest request){
        String user = request.getParameter("user");
        String message = request.getParameter("message");
        System.out.println(user);
        Message msg  = new Message();
        msg.setUser(user);
        msg.setMessage(message);
        messageService.saveMessage(msg);
        return "success";
    }





}
