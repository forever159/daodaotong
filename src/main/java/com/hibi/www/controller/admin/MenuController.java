package com.hibi.www.controller.admin;

import com.hibi.www.controller.paramValue.MenuValue;
import com.hibi.www.domain.Menu;
import com.hibi.www.service.impl.IMenuService;
import com.hibi.www.tools.Pages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * 菜单控制器
 * @author penglei
 * @description 菜单相关操作控制
 * @datetime 2018年8月4日
 */

@Controller
@RequestMapping(value = "menu")
public class MenuController {

    @Autowired
    IMenuService menuService;

    /**
     *功能：菜单初始化
     * @return /admin/home
     * @datetime 2018年8月4日
     */
    @RequestMapping("/menuInit")
    public String menuInit(){
        return "/admin/home";
    }

    @RequestMapping("/menuAdd")
    public String menuAdd(){
        return "/admin/menu/add";
    }




    /**
     * 获取分页菜单列表
     * @return JSON
     * @datetime 2018年8月4日
     */
    @RequestMapping(value = "pageMenu",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String pageMenu(MenuValue menu){
        Pages menuByPage = menuService.getMenuByPage(menu.getPage(), menu.getLimit(), "", new Menu());
        return menuByPage.toString();
    }



}
