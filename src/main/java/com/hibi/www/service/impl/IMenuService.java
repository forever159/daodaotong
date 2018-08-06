package com.hibi.www.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hibi.www.dao.mapper.MenuMapper;
import com.hibi.www.domain.Menu;
import com.hibi.www.service.MenuService;
import com.hibi.www.tools.LogTool;
import com.hibi.www.tools.Pages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 功能：菜单业务实现类
 * @author penglei
 * description
 * 时间：2018年8月4日14:12:44
 */
@Service("menuService")
public class IMenuService  implements MenuService{

    /**
     * 菜单Mapper：menuMapper
     */
    @Autowired
    private MenuMapper menuMapper;
    //pageinfo容器工具类
    private PageInfo<Menu> pageInfo;
    //返回值类型容器
    private Map<String,Object> reMap = new HashMap<>();
    private Pages pgs = null;
    /**
     * 分页获取菜单列表,带模糊查询
     * @param page 从第几条获取
     * @param rows 获取第几条
     * @param order 排序
     * @return Map<String,Object>
     */
    @Override
    public Pages getMenuByPage(int page, int rows, String order,Menu key) {
        Example example = new Example(Menu.class);
        Example.Criteria criteria = example.createCriteria();
        if (!(key == null || key.getMenuName()==null || key.getMenuName() == "" && key.getMenuName().equals("")))
            criteria.andLike("menuName","%"+key.getMenuName()+"%");
//        int count = this.menuMapper.selectCount(key);
        //利用分页工具类进行分页获取列表
        pageInfo = PageHelper.startPage(page,rows)
                .setOrderBy(order==null?"id=ASC":order)
                .doSelectPageInfo(()->this.menuMapper.selectByExample(example));

        pgs = new Pages();
        pgs.setCode(0);
        JSONArray json = new JSONArray();
        for (Menu m:pageInfo.getList() ) {
            JSONObject object = new JSONObject();
            object.put("id",m.getId());
            object.put("menuName",m.getMenuName());
            object.put("menuUrl",m.getMenuUrl());
            object.put("menuPid",m.getMenuPid());
            object.put("menuType",m.getMenuType());
            object.put("menuRemark",m.getMenuRemark());
            object.put("menuStatu",m.getMenuStatu());
            json.add(object);
        }
        pgs.setData(json);
        pgs.setMsg("获取成功!");
        pgs.setPage(pageInfo.getPageNum());
        pgs.setTotal(pageInfo.getTotal());
        pgs.setRows(pageInfo.getPageSize());
        return pgs;
    }


    /**
     * 获取菜单列表，ALL
     * @return
     */
    @Override
    public Pages getMenuList() {
        pgs = new Pages();
        List<Menu> menus = menuMapper.selectAll();
        pgs.setMsg("获取成功");
        pgs.setCode(200);
        if (menus == null) {
            pgs.setData(new ArrayList());
            return pgs;
        }
        JSONArray json = new JSONArray();
        for (Menu m:menus) {
            JSONObject object = new JSONObject();
            object.put("id",m.getId());
            object.put("menuName",m.getMenuName());
            json.add(object);
        }
        pgs.setData(json);
        return pgs;
    }

    /**
     * 新增菜单业务类方法
     * @return Pages
     * 作者：penglei
     * 日期：2018年8月6日
     */
    @Override
    public Pages insertMenu(Menu menu) {
        int bool = menuMapper.insert(menu);
        pgs = new Pages();
        pgs.setCode(200);
        pgs.setMsg("插入成功!");
        pgs.setDts(bool+"");
        return pgs;
    }


    /**
     * 根据Id删除菜单操作
     * @param id
     * @return Pages
     * 作者：penglei
     * 日期：2018年8月6日
     */
    @Override
    public Pages delMenu(String id) {
        int rs;
        pgs =new Pages();
        try {
            rs = menuMapper.deleteByPrimaryKey(id);
            pgs.setCode(200);
            pgs.setMsg("删除成功!");
        } catch (Exception e) {
            pgs.setCode(300);
            pgs.setMsg("删除失败,原因："+e.getMessage());
            LogTool.printLog(this.getClass(),e.toString(),3);
        }
        return pgs;
    }


    /**
     * 按需加载，根据所提供主键ID查询相关数据
     * @param id
     * @return Menu
     * 作者：penglei
     * 日期：2018年8月6日
     */
    @Override
    public Menu selectByPrimaryKey(String id) {
        Menu menu = menuMapper.selectByPrimaryKey(id);
        return menu;
    }
}
