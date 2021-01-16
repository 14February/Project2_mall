package com.cskaoyan.bean.wx.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author zyhstart
 * @description
 * @create 2021-01-12 13:06
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderListData {

    /**
     * orderStatusText : 已取消(系统)
     * isGroupin : false
     * orderSn : 20210112306871
     * actualPrice : 1500.0
     * goodsList : [{"number":1,"picUrl":"http://yanxuan.nosdn.127.net/1f67b1970ee20fd572b7202da0ff705d.png","id":24,"goodsName":"母亲节礼物-舒适安睡组合"}]
     * id : 24
     * handleOption : {"cancel":false,"delete":true,"pay":false,"comment":false,"confirm":false,"refund":false,"rebuy":false}
     */
    private String orderStatusText;
    private boolean isGroupin;
    private String orderSn;
    private double actualPrice;
    private int id;
    List<OrderGoodsVo> goodsList;
    HandleOptionVo handleOption;
}
