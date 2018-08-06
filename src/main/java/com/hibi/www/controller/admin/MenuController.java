package com.hibi.www.controller.admin;

import com.hibi.www.controller.paramValue.MenuValue;
import com.hibi.www.domain.Menu;
import com.hibi.www.service.impl.IMenuService;
import com.hibi.www.tools.Pages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;


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
    private IMenuService menuService;


    private Menu menu = null;
    /**
     *功能：菜单初始化
     * @return /admin/home
     * @datetime 2018年8月4日
     */
    @RequestMapping("/menuInit")
    public String menuInit(){
        return "/admin/home";
    }


    /**
     * 跳转获取add页面
     * @return
     */
    @RequestMapping("/menuAdd")
    public String menuAddOrUpdate(MenuValue menu, HttpServletRequest request){
        if (menu.getKey().equals("add")){
            return "/admin/menu/add";
        }
        if (menu.getKey().equals("update")){
            System.out.println(menu.getId());
            //执行按照主键查询数据
            Menu meu = menuService.selectByPrimaryKey(menu.getId());
            if (meu!=null){
                request.getSession().setAttribute("menu",meu);
            }
            return "/admin/menu/update";
        }
        return "";
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

    /**
     * 获取菜单列表，返回JSON值
     * @return JSON
     * @datetime 2018年8月6日
     */
    @RequestMapping(value = "getMenuList",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getMenuList(){
        Pages menuList = menuService.getMenuList();
        return menuList.toString();
    }


    /**
     * 增加菜单操作，add
     * @param menu
     * @return
     * 日期：2018年8月6日
     */
    @RequestMapping(value = "/addMenuTree",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String addMenuTree(MenuValue menu){
        System.out.println(menu.getMenuName()+"-----");
        this.menu = new Menu();
        this.menu.setId(System.currentTimeMillis()+"");
        this.menu.setMenuName(menu.getMenuName());
        this.menu.setMenuUrl(menu.getMenuUrl());
        this.menu.setMenuPid(menu.getMenuPid());
        this.menu.setMenuType(menu.getMenuType());
        this.menu.setMenuRemark(menu.getMenuRemark());
        this.menu.setMenuStatu(1);
        Pages json = menuService.insertMenu(this.menu);
        return json.toString();
    }

    @RequestMapping(value = "/delMenu",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String delMenu(MenuValue menuValue){
        Pages pages =  menuService.delMenu(menuValue.getId());
        return pages.toString();
    }










}
