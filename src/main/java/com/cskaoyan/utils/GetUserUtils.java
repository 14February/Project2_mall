package com.cskaoyan.utils;

import com.cskaoyan.bean.backstage.user.User;
import com.cskaoyan.mapper.UserMapper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;



public class GetUserUtils {

    @Autowired
    UserMapper userMapper;

    public User getUser(){
        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getPrincipals().getPrimaryPrincipal();
        User user = userMapper.selectUserByUsername(username);
        return user;
    }
}
