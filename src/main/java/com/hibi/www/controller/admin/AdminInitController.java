package com.hibi.www.controller.admin;


import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "adminInit")
    public class AdminInitController {



    @RequestMapping("/init")
    public String init(Model model){

        return "admin/index";
    }

    @RequestMapping("/add")
    public String add(Model model){

        return "admin/add";
    }
}
