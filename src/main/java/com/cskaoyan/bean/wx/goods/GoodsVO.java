package com.cskaoyan.bean.wx.goods;

import lombok.Data;

@Data
public class GoodsVO {
    private Integer id;
    private String brief;
    private Integer counterPrice;
    private Boolean isHot;
    private Boolean isNew;
    private String name;
    private String picUrl;
    private Integer retailPrice;
}
