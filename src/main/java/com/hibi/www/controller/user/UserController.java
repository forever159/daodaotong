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




    @RequestMapping("/index")
    public String index(Model model){
        Msg msg =  new Msg("测试标题","测试内容","欢迎来到HOME页面,您拥有 ROLE_HOME 权限");
        model.addAttribute("msg", msg);
        return "home";
    }




}
