package com.cskaoyan.bean.wx.goods;

import com.cskaoyan.bean.backstage.goodsbean.GoodsComment;
import lombok.Data;

import java.util.List;

@Data
public class GoodsDetailCommentVO {

    private List<GoodsComment> data;
    private Integer count;
}
