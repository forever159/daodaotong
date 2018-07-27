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
    @Auth
    public String index(Model model){
        return "login";
    }

    @RequestMapping("/login")
    public String login(Model model){
        return "login";
    }

    @PostMapping("/loginTo")
    @ResponseBody
    public String loginTo(HttpServletRequest request){
        String code = (String) request.getSession().getAttribute("code");
//        System.out.print(code);
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String code_ = request.getParameter("code");
        if (code.equals(code_)){
            return "success";
        }
        return "error";
    }


    /**
     * @param req
     * @param resp
     * @return code
     * @throws IOException
     * description 生成验证码
     */
    @RequestMapping("/code")
    public String code(HttpServletRequest req, HttpServletResponse resp){
        // 调用工具类生成的验证码和验证码图片
        Map<String, Object> codeMap = CodeUtil.generateCodeAndPic();

        // 将四位数字的验证码保存到Session中。
        HttpSession session = req.getSession();
        session.setAttribute("code", codeMap.get("code").toString());

        // 禁止图像缓存。
        resp.setHeader("Pragma", "no-cache");
        resp.setHeader("Cache-Control", "no-cache");
        resp.setDateHeader("Expires", -1);

        resp.setContentType("image/jpeg");

        // 将图像输出到Servlet输出流中。
        ServletOutputStream sos;
        try {
            resp.reset();
            sos = resp.getOutputStream();
            ImageIO.write((RenderedImage) codeMap.get("codePic"), "jpeg", sos);
            sos.flush();
            sos.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return  codeMap.get("code").toString();
    }




}
