package com.cskaoyan.controller.wx;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.cskaoyan.bean.both.BaseRespVo;
import com.cskaoyan.bean.wx.LoginVo;
import com.cskaoyan.bean.wx.register.Register;
import com.cskaoyan.service.UserService;
import com.cskaoyan.token.MallToken;
import com.cskaoyan.utils.Md5Utils;
import lombok.val;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 *
 */
@RequestMapping("wx/auth")
@RestController
public class AuthController{

    public static String avatarUrl;

    private static String code;

    @Autowired
    UserService userService;

    @PostMapping("login")
    public BaseRespVo<Object> login(@RequestBody Map<String,String> map)
    {
        String username = map.get("username");
        String password = null;
        try
        {
            password = Md5Utils.getMd5WithSalt(map.get("password"));
        }catch(NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }

        Subject subject = SecurityUtils.getSubject();
        try
        {
            subject.login(new MallToken(username,password,"wx"));
        }catch(AuthenticationException e)
        {
            return BaseRespVo.fail(700,"账号密码不对");
        }
        HashMap<String,String> userInfo = new HashMap<>();
        userInfo.put("nickName",username);
        userInfo.put("avatarUrl",avatarUrl);
        LoginVo loginVo = new LoginVo();
        loginVo.setUserInfo(userInfo);
        loginVo.setTokenExpire(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24));
        String sessionId = subject.getSession().getId().toString();
        loginVo.setToken(sessionId);
        return BaseRespVo.ok(loginVo);
    }

    @PostMapping("logout")
    public BaseRespVo<Object> logout()
    {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return BaseRespVo.ok();
    }

    @PostMapping("register")
    public BaseRespVo<Object> register(@RequestBody Register register)
    {
        String registerCode = register.getCode();
        if(!registerCode.equals(code)) return BaseRespVo.fail(740,"验证码错误");
        int status = 0;
        try
        {
            status = userService.register(register);
        }catch(NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        if(status == 1) return BaseRespVo.fail(740,"用户名已存在");
        else if(status == 2) return BaseRespVo.fail(740,"您已注册过");
        else if(status == 3) return BaseRespVo.fail(740,"该手机号已被使用");
        HashMap<String,String> userInfo = new HashMap<>();
        userInfo.put("nickName",register.getUsername());
        LoginVo loginVo = new LoginVo();
        loginVo.setUserInfo(userInfo);
        loginVo.setTokenExpire(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24));
        loginVo.setToken(SecurityUtils.getSubject().getSession().getId().toString());
        return BaseRespVo.ok(loginVo);
    }

    @PostMapping("regCaptcha")
    public BaseRespVo<Object> regCaptcha(@RequestBody Map<String,String> map)
    {
        String accessKeyId = "LTAI4GCSmf7gqRuV44PsaBSy";
        String accessKeySecret = "8hdL5THwITiQdkHcKVPLYs3zXM2V3w";
        String signName = "stone4j";
        String templateCode = "SMS_173765187";
        String phoneNumber = map.get("mobile");
        code = genRandomCode(6);
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers", phoneNumber);
        request.putQueryParameter("SignName", signName);
        request.putQueryParameter("TemplateCode", templateCode);

        request.putQueryParameter("TemplateParam", "{\"code\":\"" + code + "\"}");
        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
        }catch (ClientException e) {
            e.printStackTrace();
        }
        /*code = genRandomCode(6);
        return BaseRespVo.ok(code);*/
        return BaseRespVo.ok();
    }

    @PostMapping("reset")
    public BaseRespVo<Object> reset(@RequestBody Map<String,String> map)
    {
        String mobile = map.get("mobile");
        String registerCode = map.get("code");
        if(!registerCode.equals(code)) return BaseRespVo.fail(740,"验证码错误");
        String password = null;
        try
        {
            password = Md5Utils.getMd5WithSalt(map.get("password"));
        }catch(NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        if(userService.resetPassword(mobile,password)) return BaseRespVo.ok("修改成功");
        return BaseRespVo.fail(740,"修改失败");
    }

    private static String genRandomCode(int length)
    {
        StringBuffer code = new StringBuffer();
        Random random = new Random();
        for(int i = 0;i < length;i++)  code.append(random.nextInt(10));
        return code.toString();
    }
}
