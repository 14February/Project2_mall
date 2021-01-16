package com.cskaoyan.bean.both;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

/**
 * Vo viewT
 * @param <T>
 * 这是前后端分离，json数据一个常用的格式
 */
@Data
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class BaseRespVo<T> {
    T data;
    String errmsg;//告诉前端请求的消息
    long errno;//自己的前后端应用 ： 自定义的状态码 → 通常前端根据该状态码做不同的处理


    public static <T> BaseRespVo<T> ok(){
        BaseRespVo<T> baseRespVo = new BaseRespVo<>();
        baseRespVo.setErrno(0);
        baseRespVo.setErrmsg("成功");
        return baseRespVo;
    }
    public static <T> BaseRespVo<T> ok(T data){
        BaseRespVo<T> baseRespVo = new BaseRespVo<>();
        baseRespVo.setData(data);
        baseRespVo.setErrno(0);
        baseRespVo.setErrmsg("成功");
        return baseRespVo;
    }
    public static <T> BaseRespVo<T> ok(T data, String msg){
        BaseRespVo<T> baseRespVo = new BaseRespVo<>();
        baseRespVo.setData(data);
        baseRespVo.setErrno(0);
        baseRespVo.setErrmsg(msg);
        return baseRespVo;
    }
    public static <T> BaseRespVo<T> fail(){
        BaseRespVo<T> baseRespVo = new BaseRespVo<>();
        baseRespVo.setErrno(500);
        baseRespVo.setErrmsg("失败");
        return baseRespVo;
    }
    public static <T> BaseRespVo<T> fail(String msg){
        BaseRespVo<T> baseRespVo = new BaseRespVo<>();
        baseRespVo.setErrno(500);
        baseRespVo.setErrmsg(msg);

        return baseRespVo;
    }

    public static <T> BaseRespVo<T> fail(int errno,String msg){
        BaseRespVo<T> baseRespVo = new BaseRespVo<>();
        baseRespVo.setErrno(errno);
        baseRespVo.setErrmsg(msg);
        return baseRespVo;
    }

}
