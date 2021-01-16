package com.cskaoyan.controller.backstage;

import com.cskaoyan.bean.both.BaseRespVo;
import com.cskaoyan.bean.backstage.user.UserInfo;
import com.cskaoyan.map.OperationMap;
import com.cskaoyan.service.AdminService;
import com.cskaoyan.token.MallToken;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.Map;

/**
 *
 */
@RestController
@RequestMapping("admin/auth")
public class AdminAuthController{

    @Autowired
    AdminService adminService;

    @Autowired
    OperationMap operationMap;

    @PostMapping("login")
    public BaseRespVo<Object> login(HttpServletRequest request, @RequestBody Map<String,String> map)
    {

        String username = map.get("username");
        String password = map.get("password");
        Subject subject = SecurityUtils.getSubject();
        try
        {
            subject.login(new MallToken(username, password,"admin"));
        }catch(AuthenticationException e)
        {
            operationMap.insertOperations(request,subject,OperationMap.operations.get(0), false);
            return BaseRespVo.fail("请重新登录");
        }
        Serializable sessionId = subject.getSession().getId();
        operationMap.insertOperations(request,subject,OperationMap.operations.get(0), true);
        return BaseRespVo.ok(sessionId);
    }

    @RequestMapping("info")
    public BaseRespVo<UserInfo> info(String token)
    {
        Subject subject = SecurityUtils.getSubject();
        PrincipalCollection principals = subject.getPrincipals();
        UserInfo userInfo = adminService.queryAdminByUsername(principals);
        return BaseRespVo.ok(userInfo);
    }

    @PostMapping("logout")
    public BaseRespVo<Object> logout(HttpServletRequest request)
    {
        String ip = request.getRemoteAddr();
        Subject subject = SecurityUtils.getSubject();
        String username = subject.getPrincipals().toString();
        operationMap.insertOperations(request,subject, OperationMap.operations.get(1), true);
        subject.logout();
        return BaseRespVo.ok();
    }

}
