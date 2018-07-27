package com.hibi.www;

import com.hibi.www.controller.paramValue.User;
import com.hibi.www.dao.mapper.PermissionMapper;
import com.hibi.www.dao.mapper.UserMapper;
import com.hibi.www.domain.Permission;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ZhuanxiuApplicationTests {

	@Autowired
	UserMapper userMapper;


	@Autowired
	PermissionMapper permissionMapper;


	@Test
	public void contextLoads() {
	}


	@Test
	public void testHH(){
//		User byUserName = userMapper.findByUserName("admin");
//		System.out.print(byUserName.getUsername());
	}


	@Test
	public void testHH1(){
		List<Permission> byAdminUserId = permissionMapper.findByAdminUserId(1);
		for (Permission per :byAdminUserId) {
			System.out.print(per.getName());
		}
	}

}
