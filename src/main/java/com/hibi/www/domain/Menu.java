package com.hibi.www.domain;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 菜单表
 * @Author  penglei
 * @datetime 2018年8月4日
 * @description 用于保存系统菜单
 */
@Table(name = "ddt_menu")
public class Menu  implements Serializable{


    @Id
    private String id;
    private String menuName;
    private String menuUrl;
    private String menuPid;
    private String menuType;
    private String menuRemark;
    private Integer menuStatu;


    public Integer getMenuStatu() {
        return menuStatu;
    }

    public void setMenuStatu(Integer menuStatu) {
        this.menuStatu = menuStatu;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuUrl() {
        return menuUrl;
    }

    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl;
    }

    public String getMenuPid() {
        return menuPid;
    }

    public void setMenuPid(String menuPid) {
        this.menuPid = menuPid;
    }

    public String getMenuType() {
        return menuType;
    }

    public void setMenuType(String menuType) {
        this.menuType = menuType;
    }

    public String getMenuRemark() {
        return menuRemark;
    }

    public void setMenuRemark(String menuRemark) {
        this.menuRemark = menuRemark;
    }

    @Override
    public String toString() {
        return "{" +
                "id='" + id + '\'' +
                ", menuName='" + menuName + '\'' +
                ", menuUrl='" + menuUrl + '\'' +
                ", menuPid='" + menuPid + '\'' +
                ", menuType='" + menuType + '\'' +
                ", menuRemark='" + menuRemark + '\'' +
                ", menuStatu='" + menuStatu + '\'' +
                '}';
    }
}
