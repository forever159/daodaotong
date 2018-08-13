package com.hibi.www.service.impl;
import com.hibi.www.dao.mapper.PermissionMapper;
import com.hibi.www.dao.mapper.UserMapper;
import com.hibi.www.domain.Permission;
import com.hibi.www.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service("userService")
public class IUserService implements UserDetailsService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    PermissionMapper permissionMapper;


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userMapper.findByUserName(s);
//        System.out.print(user.getUsername());
        if (user != null) {
            List<Permission> permissions = permissionMapper.findByAdminUserId(user.getId());
//            System.out.println("----------------------"+user.getUsername());
            List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
            for (Permission per:permissions) {
                if (per != null && per.getName() !=null) {
                    GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(per.getName());
//                    GrantedAuthority grantedAuthority1 = new SimpleGrantedAuthority(per.getUrl());
                    grantedAuthorities.add(grantedAuthority);
//                    grantedAuthorities.add(grantedAuthority1);
                }
            }
            return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword()
            ,grantedAuthorities);
        }else{
            throw new UsernameNotFoundException("admin: " + s + " do not exist!");
        }
    }

    public User findByUsername(String username) {
        return  userMapper.findByUserName(username);
    }
}
