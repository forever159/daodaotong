package com.hibi.www.controller;


import com.hibi.www.controller.paramValue.User;
import com.hibi.www.domain.Msg;
import com.hibi.www.interfaces.Auth;
import com.hibi.www.tools.CodeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.RenderedImage;
import java.io.IOException;
import java.util.Map;


@Controller
@EnableAutoConfiguration
@RequestMapping("init")
public class InternalController {

    private final Logger log = LoggerFactory.getLogger(InternalController.class);


    @Autowired
    CodeUtil codeUtil;

    @RequestMapping("/index")
//    @Auth
    public String index(Model model){
        return "/home";
    }

}
