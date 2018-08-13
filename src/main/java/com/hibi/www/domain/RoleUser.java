package com.hibi.www.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RoleUser {

    private Integer sys_user_id;
    private Integer sys_role_id;
    private String username;
    private String mark;
    private int live;
    private int pid;
    private Map<String,Map<String,RoleUser>> children = null;


    public Map<String,Map<String,RoleUser>> getChildren(String type) {
        if (children!=null & !children.containsKey(type)){
            children = new HashMap<String,Map<String,RoleUser>>();
        }
        return children;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public int getLive() {
        return live;
    }

    public void setLive(int live) {
        this.live = live;
    }

    public Integer getSys_role_id() {
        return sys_role_id;
    }

    public void setSys_role_id(Integer sys_role_id) {
        this.sys_role_id = sys_role_id;
    }

    public Integer getSys_user_id() {
        return sys_user_id;
    }

    public void setSys_user_id(Integer sys_user_id) {
        this.sys_user_id = sys_user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    @Override
    public String toString() {
        return "{" +
                "sysUserId=" + sys_user_id +
                ", sysRoleId=" + sys_role_id +
                ", username='" + username + '\'' +
                ", mark='" + mark + '\'' +
                '}';
    }
}
