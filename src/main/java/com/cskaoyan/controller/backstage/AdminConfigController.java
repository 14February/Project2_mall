package com.cskaoyan.controller.backstage;


import com.cskaoyan.bean.both.BaseRespVo;
import com.cskaoyan.bean.backstage.*;
import com.cskaoyan.service.MallSystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.regex.Pattern;

@RestController
@RequestMapping("admin/config")
public class AdminConfigController{

    @Autowired
    MallSystemService mallSystemService;

    @GetMapping("mall")
    public BaseRespVo<MallSystemConfig> getMallConfig(){
        MallSystemConfig mallSystemConfig = mallSystemService.queryConfig();
        return BaseRespVo.ok(mallSystemConfig);
    }

    @PostMapping("mall")
    public BaseRespVo updateMallConfig(@RequestBody MallSystemConfig config){
        //判空及正则验证
        if (!isValid(config)){
            return BaseRespVo.fail("输入错误");
        }
        int code=mallSystemService.updateConfig(config);
        if(code==4){
            return BaseRespVo.ok();
        }
        return BaseRespVo.fail("服务器繁忙");
    }

    private boolean isValid(MallSystemConfig config) {
        String phoneRegex="\\d{3}-\\d{8}|\\d{4}-\\{7,8}";
        boolean isValidPhone = Pattern.matches(phoneRegex, config.getCskaoyanmall_mall_phone());
        String qqRegex="[1-9][0-9]{4,}";
        boolean isValidQq = Pattern.matches(qqRegex, config.getCskaoyanmall_mall_qq());
        if(StringUtils.hasText(config.getCskaoyanmall_mall_address()) &&
                StringUtils.hasText(config.getCskaoyanmall_mall_name()) &&
                StringUtils.hasText(config.getCskaoyanmall_mall_phone()) &&
                StringUtils.hasText(config.getCskaoyanmall_mall_qq()) && isValidPhone && isValidQq
                ){
            return true;
        }
        return false;
    }

    @GetMapping("express")
    public BaseRespVo getExpress(){
        ConfigExpress configExpress=mallSystemService.queryExpressConfig();
        return BaseRespVo.ok(configExpress);
    }

    @PostMapping("express")
    public BaseRespVo updateExpressConfig(@RequestBody ConfigExpress configExpress){
        int code=mallSystemService.updateExpress(configExpress.getCskaoyanmall_express_freight_value(),
                configExpress.getCskaoyanmall_express_freight_min());
        if(code==2){
            return BaseRespVo.ok();
        }
        return BaseRespVo.fail();
    }

    @GetMapping("order")
    public BaseRespVo getOrder(){
        ConfigOrder configOrder=mallSystemService.queryConfigOrder();
        return BaseRespVo.ok(configOrder);
    }

    @PostMapping("order")
    public BaseRespVo updateOrderConfig(@RequestBody ConfigOrder configOrder){
        int code=mallSystemService.updateOrderConfig(configOrder);
        if(code==3){
            return BaseRespVo.ok();
        }
        return BaseRespVo.fail();
    }
    @GetMapping("wx")
    public BaseRespVo getWx(){
        ConfigWx configWx=mallSystemService.queryWxConfig();
        return BaseRespVo.ok(configWx);
    }
    @PostMapping("wx")
    public BaseRespVo updateWxConfig(@RequestBody ConfigWx configWx){
        int code=mallSystemService.updateWxConfig(configWx);
        if(code==6){
            return BaseRespVo.ok();
        }
        return BaseRespVo.fail();
    }

}
