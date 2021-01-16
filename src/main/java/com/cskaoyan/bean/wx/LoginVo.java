package com.cskaoyan.bean.wx;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.Map;

/**
 *
 */
@Data
public class LoginVo{
    Map<String,String> userInfo;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    Date tokenExpire;
    String token;
}
