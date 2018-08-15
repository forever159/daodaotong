package com.hibi.www;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hibi.www.dao.mapper.PermissionMapper;
import com.hibi.www.dao.mapper.RoleMapper;
import com.hibi.www.dao.mapper.UserMapper;
import com.hibi.www.domain.Menu;
import com.hibi.www.domain.Permission;
import com.hibi.www.domain.RoleUser;
import com.hibi.www.domain.User;
import com.hibi.www.service.RoleService;
import com.hibi.www.service.impl.IMenuService;
import com.hibi.www.tools.LogTool;
import com.hibi.www.tools.Pages;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.JedisPool;
import tk.mybatis.mapper.entity.Example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DaodaotongApplicationTests {

    @Autowired
    UserMapper userMapper;

    @Autowired
    IMenuService menuService;

    @Autowired
    RoleMapper roleMapper;


    @Autowired
    JedisPool jedisPool;

    @Autowired
    PermissionMapper permissionMapper;

    @Test
    public void test1() {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode("abel");
        System.out.println(hashedPassword);
    }


    @Test
    public void insertUser(){
        User user = null;
//        for (int i=0;i<=20;i++) {
//            user = new User("user" + i, new BCryptPasswordEncoder().encode("123456"));
//            userMapper.insert(user);
//        }
    }

    /**
     * 测试分页查询用户
     */
    @Test
    public void selectUserPage(){
        final PageInfo<User> pageInfo = PageHelper.startPage(2,5)
                .setOrderBy("id desc")
                .doSelectPageInfo(()->this.userMapper.selectAll());
        LogTool.printLog(this.getClass(),"--"+pageInfo,1);
    }

    /**
     * 根据条件查询菜单
     */
    @Test
    public void selectMenuCount(){
        Pages  list = menuService.getMenuByPage(1, 20, "", new Menu.MenuBuilder().build());
        LogTool.printLog(this.getClass(),"--"+list.toString(),1);
    }



    @Test
    public void testGetRoleUserList(){
        List<String> permissionByLike = permissionMapper.findPermissionByLike("");
        LogTool.printLog(this.getClass(),permissionByLike.toString(),1);

    }


    @Test
    public void get(){
        String key = "name";
        System.out.println(jedisPool.getResource().get(key));//aaa
    }
    @Test
    public void set(){
        String key = "name",value = "aaa";
        jedisPool.getResource().set(key, value);
    }//仅做测试，未关闭jedis客户端等资源
}
