package com.cskaoyan.service;

import com.cskaoyan.bean.backstage.stat.GoodsStat;
import com.cskaoyan.bean.backstage.stat.OrderStat;
import com.cskaoyan.bean.backstage.stat.UserGrowth;

public interface StatService {
    /**
     * 统计用户新增数据
     * @return
     */
    UserGrowth queryUser();

    /**
     * 统计订单数据,其中客单价为订单中除了未付款以外的所有订单总金额除以购买人数
     * @return
     */
    OrderStat queryOrder();

    /**
     * 商品统计
     * @return
     */
    GoodsStat queryGoods();
}
