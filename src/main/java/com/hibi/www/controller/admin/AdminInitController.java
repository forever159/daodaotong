package com.hibi.www.controller.admin;


import com.hibi.www.domain.Msg;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "admin")
public class AdminInitController {



    @RequestMapping("/init")
    public String init(Model model){
        Msg msg =  new Msg("登录成功!","测试内容","欢迎来到HOME页面,您拥有 ROLE_HOME 权限");
        model.addAttribute("msg", msg);
        return "admin/index";
    }

    @RequestMapping("/hello")
    @ResponseBody
    public String hello(){
        return "hello admin";
    }



    @RequestMapping("/add")
    public String add(Model model){

        return "admin/add";
    }








}
