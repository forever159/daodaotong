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
