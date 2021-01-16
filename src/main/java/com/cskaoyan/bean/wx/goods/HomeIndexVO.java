package com.cskaoyan.bean.wx.goods;

import com.cskaoyan.bean.backstage.Ad;
import com.cskaoyan.bean.backstage.Topic;
import com.cskaoyan.bean.backstage.goodsbean.Goods;
import com.cskaoyan.bean.backstage.groupon.Groupon;
import com.cskaoyan.bean.backstage.marketManagement.adminBrand.Brand;
import com.cskaoyan.bean.backstage.marketManagement.adminCategory.Category;
import com.cskaoyan.bean.both.coupon.Coupon;
import lombok.Data;

import java.util.List;

@Data
public class HomeIndexVO {
    private List<Ad> banner;
    private List<Brand> brandList;
    private List<Category> channel;
    private List<Coupon> couponList;
    private List<FloorGoodsVO> floorGoodsList;
    private List<GrouponListVO> grouponList;
    private List<Goods> hotGoodsList;
    private List<Goods> newGoodsList;
    private List<Topic> topicList;
}
