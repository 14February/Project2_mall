package com.cskaoyan.bean.wx.goods;


import lombok.Data;

import java.util.List;

@Data
public class FloorGoodsVO {
    private String name;
    private List<GoodsVO> goodsList;
    private Integer id;
}
