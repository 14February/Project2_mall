package com.cskaoyan.config;

import com.cskaoyan.realm.AdminRealm;
import com.cskaoyan.realm.WxRealm;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
@Configuration
public class ShiroConfig{

    //注册filter组件
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager securityManager)
    {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

        shiroFilterFactoryBean.setLoginUrl("/unAuth");    //设置认证失败后重定向的url

        //设置SecurityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        //设置FilterChainDefinitionMap
        Map<String,String> filterMap = new LinkedHashMap<>();

        //设置允许匿名访问的请求
        filterMap.put("/*/auth/login","anon");
        filterMap.put("/unAuth","anon");
        filterMap.put("/file/**","anon");
        filterMap.put("/pic/**", "anon");
        filterMap.put("/wx/storage/upload", "anon");
        filterMap.put("/wx/auth/regCaptcha", "anon");
        filterMap.put("/wx/auth/register", "anon");
        filterMap.put("/wx/auth/reset", "anon");

        filterMap.put("/wx/home/index", "anon");

        //设置需要认证的请求
        filterMap.put("/**","authc");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
        return shiroFilterFactoryBean;
    }

    //注册SecurityManager
    @Bean
    public DefaultWebSecurityManager securityManager(AdminRealm adminRealm,WxRealm wxRealm,CustomAuthenticator customAuthenticator)
    {
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(adminRealm);
        defaultWebSecurityManager.setRealm(wxRealm);
        defaultWebSecurityManager.setSessionManager(webSessionManager());
        defaultWebSecurityManager.setAuthenticator(customAuthenticator);
        return defaultWebSecurityManager;
    }

    //注册SessionManager
    @Bean
    public DefaultWebSessionManager webSessionManager()
    {
        CustomSessionManager customSessionManager = new CustomSessionManager();
        customSessionManager.setGlobalSessionTimeout(1000 * 60 * 60 * 24);
        return customSessionManager;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultWebSecurityManager securityManager)
    {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    @Bean
    public CustomAuthenticator authenticator(AdminRealm adminRealm,WxRealm wxRealm)
    {
        CustomAuthenticator customAuthenticator = new CustomAuthenticator();
        List<Realm> realms = new ArrayList<>();
        realms.add(adminRealm);
        realms.add(wxRealm);
        customAuthenticator.setRealms(realms);
        return customAuthenticator;
    }
}
