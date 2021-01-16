package com.cskaoyan.service;

import com.cskaoyan.bean.backstage.Ad;
import com.cskaoyan.bean.backstage.AdExample;
import com.cskaoyan.bean.backstage.Topic;
import com.cskaoyan.bean.backstage.TopicExample;
import com.cskaoyan.bean.backstage.goodsbean.Goods;
import com.cskaoyan.bean.backstage.goodsbean.GoodsExample;
import com.cskaoyan.bean.backstage.marketManagement.adminBrand.Brand;
import com.cskaoyan.bean.backstage.marketManagement.adminBrand.BrandExample;
import com.cskaoyan.bean.backstage.marketManagement.adminCategory.Category;
import com.cskaoyan.bean.backstage.marketManagement.adminCategory.CategoryExample;
import com.cskaoyan.bean.both.coupon.Coupon;
import com.cskaoyan.bean.both.coupon.CouponExample;
import com.cskaoyan.bean.wx.goods.FloorGoodsVO;
import com.cskaoyan.bean.wx.goods.GrouponListVO;
import com.cskaoyan.bean.wx.goods.HomeIndexVO;
import com.cskaoyan.mapper.backstage.*;
import com.cskaoyan.mapper.backstage.marketManagementMapper.BrandMapper;
import com.cskaoyan.mapper.backstage.marketManagementMapper.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HomeServiceImpl implements HomeService{
    @Autowired
    AdMapper adMapper;
    @Autowired
    BrandMapper brandMapper;
    @Autowired
    CategoryMapper categoryMapper;
    @Autowired
    CouponMapper couponMapper;
    @Autowired
    GoodsMapper goodsMapper;
    @Autowired
    GrouponMapper grouponMapper;
    @Autowired
    GrouponRulesMapper grouponRulesMapper;
    @Autowired
    TopicMapper topicMapper;
    @Override
    public HomeIndexVO index() {
        AdExample adExample = new AdExample();
        List<Ad> ads = adMapper.selectByExample(adExample);
        List<Brand> brands = brandMapper.selectBrand();

        List<Category> categories = categoryMapper.selectCategory();
        CouponExample couponExample = new CouponExample();
        CouponExample.Criteria criteria2 = couponExample.createCriteria();
        criteria2.andDeletedEqualTo(false);

        couponExample.createCriteria().andTypeEqualTo(0);

        List<Coupon> coupons = couponMapper.selectByExample(couponExample);

        List<Goods> hotGoodsList = goodsMapper.selectHotGoods();

        List<Goods> newGoodsList = goodsMapper.selectNewGoods();
//        GoodsExample floorGoodsExample = new GoodsExample();
//        GoodsExample.Criteria criteria3 = floorGoodsExample.createCriteria();
//        criteria3.andIsNewNotEqualTo(true);
//        criteria3.andIsHotNotEqualTo(true);
//        List<Goods> floorGoodsList = goodsMapper.selectByExample(floorGoodsExample);

        List<GrouponListVO> grouponListVOS = goodsMapper.selectGrouponList();
        TopicExample topicExample = new TopicExample();
        TopicExample.Criteria criteria3 = topicExample.createCriteria();
        criteria3.andDeletedEqualTo(false);
        List<Topic> topics = topicMapper.selectByExample(topicExample);

        List<FloorGoodsVO> floorGoodsVOS = categoryMapper.selectIdAndName();

        HomeIndexVO homeIndexVO = new HomeIndexVO();
        homeIndexVO.setBanner(ads);
        homeIndexVO.setBrandList(brands);
        homeIndexVO.setChannel(categories);
        homeIndexVO.setCouponList(coupons);
        homeIndexVO.setFloorGoodsList(floorGoodsVOS);
        homeIndexVO.setGrouponList(grouponListVOS);
        homeIndexVO.setHotGoodsList(hotGoodsList);
        homeIndexVO.setNewGoodsList(newGoodsList);
        homeIndexVO.setTopicList(topics);
        return homeIndexVO;
    }
}
