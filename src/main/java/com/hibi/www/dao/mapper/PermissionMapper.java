package com.hibi.www.dao.mapper;

import com.hibi.www.dao.MyMapper;
import com.hibi.www.domain.Permission;
import org.apache.ibatis.annotations.Select;

import java.util.List;


public interface PermissionMapper extends MyMapper<Permission>{

    @Select("SELECT * FROM sys_permission")
    public List<Permission> selectAll();



    @Select("SELECT p.* FROM sys_user u " +
            "LEFT JOIN sys_role_user sru on u.id=sru.sys_user_id " +
            "LEFT JOIN Sys_Role r on sru.Sys_Role_id=r.id " +
            "LEFT JOIN Sys_permission_role spr on spr.role_id=r.id " +
            "LEFT JOIN Sys_permission p on p.id =spr.permission_id " +
            "WHERE u.id = #{userId}")
    public List<Permission> findByAdminUserId(int userId);



    @Select("SELECT SUBSTRING_INDEX(name,'_',-1) " +
            "FROM sys_permission WHERE name like '%ADMIN%'")
    public List<String> findPermissionByLike(String name);






}
