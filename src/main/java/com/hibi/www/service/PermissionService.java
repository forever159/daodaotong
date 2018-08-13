package com.hibi.www.service;

import com.hibi.www.domain.Permission;

import java.util.List;

/**
 * 权限接口
 * 作者：penglei
 * 日期：2018年8月13日13:52:02
 */

public interface PermissionService {
    /**
     * 接口方法，查询权限（模糊）
     * @param name
     * @return List
     */
    public List<Permission> findPermissionByLike(String name);
}
