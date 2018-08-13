package com.hibi.www.dao.mapper;

import com.hibi.www.dao.MyMapper;
import com.hibi.www.domain.Role;
import com.hibi.www.domain.RoleUser;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * 角色数据底层操作类
 * 作者：penglei
 * 日期：2018年8月8日13:31:44
 */


public interface RoleMapper extends MyMapper<Role>{


    /**
     * 连接查询，查询全部数据，包括子集
     * @return
     */
    @Select("SELECT sys_role_id,sys_user_id,role.`name`,role.pid,role.mark,IF(role.id=sru.sys_role_id,1,0) as live\n" +
            "FROM sys_role AS role\n" +
            "LEFT JOIN sys_role_user sru ON role.id = sru.sys_role_id")
    public List<RoleUser> getRoleUserList();


}
