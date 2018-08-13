package com.hibi.www.service.impl;

import com.hibi.www.dao.mapper.PermissionMapper;
import com.hibi.www.domain.Permission;
import com.hibi.www.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
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
    PermissionMapper permissionMapper;


    @Override
    public List<Permission> findPermissionByLike(String name) {
        return permissionMapper.findPermissionByLike(name);
    }
}
