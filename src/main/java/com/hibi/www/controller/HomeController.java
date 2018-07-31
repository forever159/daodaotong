package com.hibi.www.controller;


import com.google.code.kaptcha.Constants;
import com.hibi.www.domain.Msg;
import com.hibi.www.tools.LogTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import com.google.code.kaptcha.Producer;

@Controller
public class HomeController {

    @Autowired
    private Producer captchaProducer;



//    @RequestMapping("/")
//    public String index(Model model){
//        Msg msg =  new Msg("登录成功!","测试内容","欢迎来到HOME页面,您拥有 ROLE_HOME 权限");
//        model.addAttribute("msg", msg);
//        return "admin/index";
//    }



    @GetMapping("/login")
    public String login(){
        return "login";
    }

    /**
     * @param req
     * @param resp
     * @return code
     * @throws IOException
     * description 生成验证码
     */
    @GetMapping("/getCode")
    public void code(HttpServletRequest req, HttpServletResponse response) throws IOException {
//        // 调用工具类生成的验证码和验证码图片
//        Map<String, Object> codeMap = CodeUtil.generateCodeAndPic();
//        // 将四位数字的验证码保存到Session中。
//        HttpSession session = req.getSession();
//        session.setAttribute("session_imageCode", codeMap.get("session_imageCode").toString());
//
//        // 禁止图像缓存。
//        resp.setHeader("Pragma", "no-cache");
//        resp.setHeader("Cache-Control", "no-cache");
//        resp.setDateHeader("Expires", -1);
//
//        resp.setContentType("image/jpeg");
//
//        // 将图像输出到Servlet输出流中。
//        ServletOutputStream sos;
//        try {
//            resp.reset();
//            sos = resp.getOutputStream();
//            ImageIO.write((RenderedImage) codeMap.get("codePic"), "jpeg", sos);
//            sos.flush();
//            sos.close();
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        return  codeMap.get("session_imageCode").toString();
        HttpSession session = req.getSession();
        response.setDateHeader("Expires", 0);
        // Set standard HTTP/1.1 no-cache headers.
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        // Set IE extended HTTP/1.1 no-cache headers (use addHeader).
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        // Set standard HTTP/1.0 no-cache header.
        response.setHeader("Pragma", "no-cache");
        // return a jpeg
        response.setContentType("image/jpeg");
        // create the text for the image

        String capText = captchaProducer.createText();
        // store the text in the session
        //request.getSession().setAttribute(Constants.KAPTCHA_SESSION_KEY, capText);
        //将验证码存到session
        session.setAttribute(Constants.KAPTCHA_SESSION_KEY, capText);
        LogTool.printLog(HomeController.class, capText, 1);
        // create the image with the text
        BufferedImage bi = captchaProducer.createImage(capText);
        ServletOutputStream out = response.getOutputStream();
        // write the data out
        ImageIO.write(bi, "jpg", out);
        try {
            out.flush();
        } finally {
            out.close();
        }
    }


}
