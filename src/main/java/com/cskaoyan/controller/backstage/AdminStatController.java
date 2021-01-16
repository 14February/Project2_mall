package com.cskaoyan.controller.backstage;

import com.cskaoyan.bean.both.BaseRespVo;
import com.cskaoyan.bean.backstage.stat.GoodsStat;
import com.cskaoyan.bean.backstage.stat.OrderStat;
import com.cskaoyan.bean.backstage.stat.UserGrowth;
import com.cskaoyan.service.StatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("admin/stat")
public class AdminStatController{
    @Autowired
    StatService statService;
    @RequestMapping("user")
    public BaseRespVo getUserStat(){
        UserGrowth userGrowth = statService.queryUser();
        return BaseRespVo.ok(userGrowth);
    }
    @RequestMapping("order")
    public BaseRespVo getOrderStat(){
        OrderStat orderStat=statService.queryOrder();
        return BaseRespVo.ok(orderStat);
    }
    @RequestMapping("goods")
    public BaseRespVo getGoodsStat(){
        GoodsStat goodsStat=statService.queryGoods();
        return BaseRespVo.ok(goodsStat);
    }
}
