package com.cskaoyan.bean.wx.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zyhstart
 * @description
 * @create 2021-01-12 12:58
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderGoodsVo {
    private int number;
    private String picUrl;
    private int id;
    private String goodsName;

}
