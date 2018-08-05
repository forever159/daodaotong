package com.hibi.www.controller.user;


import com.hibi.www.domain.Msg;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@EnableAutoConfiguration
@RequestMapping("user")
public class UserController {



    @PostMapping("/bar")
    public String bar(){
        return "";
    }








}
