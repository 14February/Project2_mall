package com.cskaoyan.bean.wx.goods;

import com.cskaoyan.bean.backstage.Issue;
import com.cskaoyan.bean.backstage.goodsbean.Goods;
import com.cskaoyan.bean.backstage.goodsbean.GoodsAttribute;
import com.cskaoyan.bean.backstage.goodsbean.GoodsProduct;
import com.cskaoyan.bean.backstage.groupon.Groupon;
import com.cskaoyan.bean.backstage.marketManagement.adminBrand.Brand;
import lombok.Data;

import java.util.List;

@Data
public class GoodsDetailVO {
    List<GoodsSpecificationVO> specificationList;
    List<Groupon> groupon;
    List<Issue> issue;
    Integer userHasCollect;
    List<GoodsAttribute> attribute;
    GoodsDetailCommentVO comment;
    Brand brand;
    List<GoodsProduct> productList;
    Goods info;
    String shareImage;
}
