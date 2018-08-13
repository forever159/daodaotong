package com.hibi.www.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hibi.www.dao.mapper.RoleMapper;
import com.hibi.www.domain.RoleUser;
import com.hibi.www.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 角色接口实现类，业务逻辑层
 * 作者：penglei
 * 日期：2018年8月10日16:37:24
 */

@Service("roleService")
public class IRoleService implements RoleService {

    @Autowired
    private RoleMapper roleMapper;


    @Override
    public JSONArray getRoleUserList() {
        List<RoleUser> roleUserList = roleMapper.getRoleUserList();
        JSONArray jrr = new JSONArray();
        JSONObject job = null;
        Map<String,Object> isLive = new HashMap<>();
        for (RoleUser us:roleUserList) {
            if (isLive.containsKey(us.getMark())){
                continue;
            }
            job = new JSONObject();
            job.put("name",us.getMark());
            job.put("id",us.getSys_role_id());
            job.put("children","");
            isLive.put(us.getMark(),job);
            jrr.add(job);
        }
        return jrr;
    }
}
