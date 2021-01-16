package com.cskaoyan.bean.wx.goods;

import com.cskaoyan.bean.backstage.goodsbean.GoodsSpecification;
import lombok.Data;

import java.util.List;

@Data
public class GoodsSpecificationVO {
    private String name;
    List<GoodsSpecification> valueList;
}
