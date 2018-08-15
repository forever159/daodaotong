package com.hibi.www.controller.admin;


import com.hibi.www.domain.Permission;
import com.hibi.www.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 权限视图控制类
 * 作者：penglei
 * 日期：2018年8月14日09:23:18
 */
@Controller
@RequestMapping("permission")
public class PermissionController {


    /**
     * 自动注入业务层
     * */
    @Autowired
    PermissionService permissionService;


    @RequestMapping(value = "/getPermissionList",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String  getPermissionList(){
        long bengTime = System.currentTimeMillis();
        List<String> permissions = permissionService.findPermissionByLike("");
        long time=System.currentTimeMillis()-bengTime;
        System.out.println(time);
        return permissions.toString();
    }




    @RequestMapping(value = "/getPermission",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String  getPermission(){
        long bengTime = System.currentTimeMillis();
        List<Permission> permissions = permissionService.getPermissions();
        long time=System.currentTimeMillis()-bengTime;
        System.out.println(time);
        return permissions.toString();
    }









}
