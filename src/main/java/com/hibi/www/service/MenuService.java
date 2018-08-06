package com.hibi.www.service;

import com.hibi.www.domain.Menu;
import com.hibi.www.tools.Pages;

import java.util.List;

/**
 * 菜单接口
 */
public interface MenuService {

    /**
     * 分页获取菜单列表，接口
     * @param page
     * @param rows
     * @param order
     * @param key
     * @return
     */
    public Pages getMenuByPage(int page, int rows, String order, Menu key);

    /**
     * 获取菜单列表，接口
     * @return
     */
    public Pages getMenuList();

    /**
     * 插入菜单操作，接口
     * @param menu
     * @return
     */
    public  Pages insertMenu(Menu menu);

    /**
     * 删除菜单操作，接口
     * @param id
     * @return
     */
    public Pages delMenu(String id);

    /**
     * 按照Id获取菜单信息，接口
     * @param id
     * @return
     */
    public Menu selectByPrimaryKey(String id);
}
