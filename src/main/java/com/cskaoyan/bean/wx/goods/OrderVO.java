package com.cskaoyan.bean.wx.goods;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderVO {

    private Integer unrecv; //未收货
    private Integer uncomment; //未评论
    private Integer unpaid; //未付款
    private Integer unship; //未发货
}
