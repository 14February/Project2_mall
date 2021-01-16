package com.cskaoyan.bean.wx.goods;

import com.cskaoyan.bean.backstage.goodsbean.Goods;
import com.cskaoyan.bean.backstage.marketManagement.adminCategory.Category;
import lombok.Data;

import java.util.List;

@Data
public class GoodsListVO {
    private List<Goods> goodsList;
    Integer count;
    private List<Category> filterCategoryList;
}
