package com.cskaoyan.bean.wx.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zyhstart
 * @description
 * @create 2021-01-12 17:14
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderInfo {
    private String consignee;
    private String address;
    private String addTime;
    private String orderSn;
    private double actualPrice;
    private String mobile;
    private String orderStatusText;
    private double goodsPrice;
    private double couponPrice;
    private int id;
    private double freightPrice;
    private HandleOptionVo handleOption;

}
