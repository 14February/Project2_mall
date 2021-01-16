package com.cskaoyan.exception;

import com.cskaoyan.bean.both.BaseRespVo;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionControllerAdvice{

    @ExceptionHandler(AuthorizationException.class)
    @ResponseBody
    public BaseRespVo<Object> handlerAuthorException(Exception e)
    {
        return BaseRespVo.fail("未授权");
    }

}