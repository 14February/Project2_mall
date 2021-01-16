package com.cskaoyan.bean.wx.goods;

import com.cskaoyan.bean.backstage.groupon.GrouponGoods;
import lombok.Data;

@Data
public class GrouponListVO {
    private GoodsVO goods;

    private Integer grouponMember;
    private Integer grouponPrice;
}
