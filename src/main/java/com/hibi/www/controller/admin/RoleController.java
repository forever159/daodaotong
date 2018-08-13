package com.hibi.www.controller.admin;

import com.alibaba.fastjson.JSONArray;
import com.hibi.www.domain.RoleUser;
import com.hibi.www.service.impl.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("role")
public class RoleController {

    @Autowired
    IRoleService roleService;

    @RequestMapping("/roleInit")
    public String roleInit(){
        return "admin/role/home";
    }


    @RequestMapping(value = "/roleTree",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String roleTree(RoleUser roleUser){
        JSONArray roleUserList = null;
        if (roleUser.getSys_role_id() == null){
            roleUserList = roleService.getRoleUserList();
        }
        return roleUserList.toString();
    }









}
