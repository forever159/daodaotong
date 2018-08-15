package com.hibi.www.service.impl;

import com.hibi.www.dao.mapper.PermissionMapper;
import com.hibi.www.domain.Permission;
import com.hibi.www.service.PermissionService;
import com.hibi.www.tools.LogTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 权限业务实现类
 * 作者：penglei
 * 日期：2018年8月13日13:52:59
 */

@Service("permissionService")
public class IPermissionService implements PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    @Cacheable(value = "permis_admin")
    @Override
    public List<String> findPermissionByLike(String name) {

        LogTool.printLog(this.getClass(),"开始查询......",1);
        List<String> perList = null;
        try {
            LogTool.printLog(this.getClass(),"结束查询......",1);
            perList = permissionMapper.findPermissionByLike(name);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return perList;
    }


    @Cacheable(value = "permis")
    @Override
    public List<Permission> getPermissions(){
        System.out.println(">>>>>>执行查询......");
        List<Permission> permissions = null;
        try {
            permissions = permissionMapper.selectAll();
        } catch (Exception e) {
            e.printStackTrace();
            LogTool.printLog(this.getClass(),e.toString(),3);
        }
        return  permissions;
    }


}
