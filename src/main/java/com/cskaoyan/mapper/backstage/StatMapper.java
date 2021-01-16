package com.cskaoyan.mapper.backstage;

import com.cskaoyan.bean.backstage.stat.GoodsBean;
import com.cskaoyan.bean.backstage.stat.GoodsStat;
import com.cskaoyan.bean.backstage.stat.OrderBean;
import com.cskaoyan.bean.backstage.stat.OrderStat;

import java.util.List;

public interface StatMapper {
    //统计某一天order_status不为101,102,103的订单的信息，包括订单量，下单货品数量，总金额
    List<GoodsBean> selectByDay();

    //统计order_status为201的订单的信息，即当天付款的订单，包括订单量，下单用户，订单总额，客单价
    List<OrderBean> selectOrderByDay();
}
