package com.cskaoyan.realm;

import com.cskaoyan.bean.backstage.user.User;
import com.cskaoyan.controller.wx.AuthController;
import com.cskaoyan.mapper.UserMapper;
import com.cskaoyan.token.MallToken;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 */
@Component
public class WxRealm extends AuthorizingRealm{

    @Autowired
    UserMapper userMapper;

    @Autowired
    AdminRealm adminRealm;

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException
    {
        MallToken token = (MallToken)authenticationToken;
        String username = token.getUsername();
        User user = userMapper.selectUserByUsername(username);
        AuthController.avatarUrl = user.getAvatar();
        return new SimpleAuthenticationInfo(username, user.getPassword(), this.getName());
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals)
    {
        return adminRealm.doGetAuthorizationInfo(principals);
    }
}
