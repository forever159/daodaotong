package com.hibi.www.domain;

import java.io.Serializable;

public class Permission implements Serializable{

    private Integer id;
    //权限名称
    private String name;

    //权限描述
    private String descritpion;

    //授权链接
    private String url;

    //父节点id
    private Integer pid;

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescritpion() {
        return descritpion;
    }

    public void setDescritpion(String descritpion) {
        this.descritpion = descritpion;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }


    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", descritpion='" + descritpion + '\'' +
                ", url='" + url + '\'' +
                ", pid=" + pid +
                '}';
    }
}
