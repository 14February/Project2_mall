package com.cskaoyan.realm;

import com.cskaoyan.bean.backstage.admin.Admin;
import com.cskaoyan.bean.backstage.admin.AdminExample;
import com.cskaoyan.mapper.backstage.AdminMapper;
import com.cskaoyan.mapper.backstage.PermissionMapper;
import com.cskaoyan.mapper.backstage.RoleMapper;
import com.cskaoyan.token.MallToken;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 *
 */
@Component
public class AdminRealm extends AuthorizingRealm{

    @Autowired
    AdminMapper adminMapper;

    @Autowired
    RoleMapper roleMapper;

    @Autowired
    PermissionMapper permissionMapper;

    //获取认证信息
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException
    {
        MallToken token = (MallToken)authenticationToken;
        String username = token.getUsername();
        String password = adminMapper.selectPasswordByUsername(username);
        return new SimpleAuthenticationInfo(username, password, this.getName());
    }

    //获取授权信息
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals)
    {
        String username = (String)principals.getPrimaryPrincipal();
        AdminExample example = new AdminExample();
        example.createCriteria().andUsernameEqualTo(username);
        List<Admin> admins = adminMapper.selectListByExample(example);
        Admin admin = admins.get(0);
        Integer[] roleIds = admin.getRoleIds();
        List<String> roles = roleMapper.selectRoleNameById(roleIds);
        List<String> permissions = permissionMapper.selectPermissionByRoleId(roleIds);
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.addStringPermissions(permissions);
        return simpleAuthorizationInfo;
    }
}
