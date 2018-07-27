package com.hibi.www.controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@EnableAutoConfiguration
@RequestMapping("socket")
public class SocketController {


    @RequestMapping("/index")
    public String  index(Model model){
        return "/user/index";
    }

}
