package com.hibi.www.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hibi.www.dao.mapper.MenuMapper;
import com.hibi.www.domain.Menu;
import com.hibi.www.service.MenuService;
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

        Pages pgs = new Pages();
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
}
