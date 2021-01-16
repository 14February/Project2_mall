package com.cskaoyan.config;

import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
//import org.springframework.web.server.session.DefaultWebSessionManager;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

/**
 *
 */
public class CustomSessionManager extends DefaultWebSessionManager {
    @Override
    protected Serializable getSessionId(ServletRequest req,ServletResponse resp)
    {
        HttpServletRequest request = (HttpServletRequest)req;
        String sessionId = request.getHeader("X-cskaoyan-mall-Admin-Token");
        if(sessionId != null && !"".equals(sessionId)) return sessionId;
        return super.getSessionId(req,resp);
    }
}
