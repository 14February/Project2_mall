package com.cskaoyan.bean.wx.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author zyhstart
 * @description
 * @create 2021-01-12 17:13
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetail {
     OrderInfo orderInfo;
     List<WxOrderGoods> orderGoods;

}
