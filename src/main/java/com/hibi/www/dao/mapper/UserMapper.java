package com.hibi.www.dao.mapper;

import com.hibi.www.dao.MyMapper;
import com.hibi.www.domain.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;


public interface UserMapper extends MyMapper<User>{
    @Select("SELECT * FROM sys_user WHERE username = #{username}")
    public User findByUserName(String username);
}
