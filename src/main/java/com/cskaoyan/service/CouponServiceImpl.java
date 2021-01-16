package com.cskaoyan.service;

import com.cskaoyan.bean.backstage.goodsbean.Goods;
import com.cskaoyan.bean.backstage.user.User;
import com.cskaoyan.bean.both.coupon.*;
import com.cskaoyan.bean.backstage.*;
import com.cskaoyan.bean.wx.cart.Cart;
import com.cskaoyan.mapper.UserMapper;
import com.cskaoyan.mapper.backstage.CouponMapper;
import com.cskaoyan.mapper.backstage.CouponUserMapper;
import com.cskaoyan.mapper.backstage.GoodsMapper;
import com.cskaoyan.mapper.wx.CartMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CouponServiceImpl implements CouponService{
    @Autowired
    CouponMapper couponMapper;

    @Autowired
    CouponUserMapper couponUserMapper;

    @Autowired
    UserMapper userMapper;

    @Autowired
    CartMapper cartMapper;

    @Autowired
    GoodsMapper goodsMapper;

    @Override
    public int exchange(String code,String username)
    {
        CouponExample example = new CouponExample();
        example.createCriteria().andCodeEqualTo(code);
        List<Coupon> coupons = couponMapper.selectByExample(example);
        if(coupons.size() == 0) return 3;
        Coupon coupon = coupons.get(0);
        return receive(coupon.getId(),username);
    }

    @Override
    public List<Coupon> selectList(Integer cardId,String username)
    {
        List<Coupon> coupons = new ArrayList<>();
        Cart cart = cartMapper.selectByPrimaryKey(cardId);
        Integer userId = cardId == 0 ? userMapper.selectUserByUsername(username).getId():cart.getUserId();
        if(cardId == 0){
            List<Integer> couponIds = couponUserMapper.selectCouponIdsByExample(userId);
            CouponExample example = new CouponExample();
            example.createCriteria().andIdIn(couponIds);
            coupons = couponMapper.selectByExample(example);
        }else{
            List<Coupon> originCoupons = couponMapper.selectValidCouponsByUserId(userId);
            Integer goodsId = cart.getGoodsId();
            Goods goods = goodsMapper.selectByPrimaryKey(goodsId);

            for(Coupon originCoupon : originCoupons)
                if(originCoupon.getStatus() == 0)
                {
                    Integer goodsType = originCoupon.getGoodsType();
                    if(goodsType == 0){
                        coupons.add(originCoupon);
                    }else if(goodsType == 1){
                        Integer[] goodsValue = originCoupon.getGoodsValue();
                        for(Integer value : goodsValue)
                            if(value.equals(goods.getCategoryId()))
                                coupons.add(originCoupon);
                    }else{
                        Integer[] goodsValue = originCoupon.getGoodsValue();
                        for(Integer value : goodsValue)
                            if(value.equals(goodsId))
                                coupons.add(originCoupon);
                    }
                }
        }
        return coupons;
    }

    @Override
    public CouponList queryCouponsByUsernameAndStatus(String username,Integer status,Integer page,Integer size)
    {
        User user = userMapper.selectUserByUsername(username);
        Integer userId = user.getId();
        List<Coupon> coupons = couponMapper.queryCouponListWithPageByUserIdAndStatus(userId,status,(page - 1) * size,size);
        int total = couponMapper.queryCouponTotalByUserIdAndStatus(userId,status);
        CouponList couponList = new CouponList();
        couponList.setData(coupons);
        couponList.setCount(total);
        return couponList;
    }

    @Override
    public int receive(Integer couponId,String username)
    {
        Coupon coupon = couponMapper.selectByPrimaryKey(couponId);
        User user = userMapper.selectUserByUsername(username);
        Integer userId = user.getId();
        int alreadyUse = couponUserMapper.selectTotalCountByCouponIdAndUserId(couponId,userId);
        if(coupon.getLimit() != 0 && alreadyUse >= coupon.getLimit()) return 0;
        Integer total = coupon.getTotal();
        if(total == -1) return 1;
        else if(total == 1) couponMapper.updateToInvalidByCouponId(couponId);
        else couponMapper.decreaseOneByCouponId(couponId);
        CouponUser couponUser = new CouponUser(null,userId,coupon.getId(),0,null,coupon.getStartTime(),coupon.getEndTime(),null,new Date(),null,false);
        couponUserMapper.insertSelective(couponUser);
        return 2;
    }

    @Override
    public CouponList queryCoupons(Integer page,Integer size)
    {
        List<Coupon> coupons = couponMapper.queryCouponListWithPage((page - 1) * size,size);
        int total = couponMapper.queryCouponTotal();
        CouponList couponList = new CouponList();
        couponList.setData(coupons);
        couponList.setCount(total);
        return couponList;
    }

    @Override
    public void delete(Coupon coupon) {
        coupon.setDeleted(true);
        int delete = couponMapper.updateByPrimaryKey(coupon);
    }

    @Override
    public Coupon update(Coupon coupon) {
        coupon.setUpdateTime(new Date());
        int update = couponMapper.updateByPrimaryKey(coupon);
        Coupon coupon1 = couponMapper.selectByPrimaryKey(coupon.getId());
        return coupon;
    }

    @Override
    public ListData<CouponUser> queryCouponUserList(Integer page,Integer limit,Integer userId,Integer status,Integer couponId,String sort,String order) {
        PageHelper.startPage(page, limit);
        CouponUserExample example = new CouponUserExample();
        example.setOrderByClause(sort + " " + order);
        CouponUserExample.Criteria criteria = example.createCriteria();
        if(couponId != null) criteria.andIdEqualTo(couponId);
        if(userId != null) criteria.andUserIdEqualTo(userId);
        if(status != null) criteria.andStatusEqualTo(status);
        List<CouponUser> couponUsers = couponUserMapper.selectByExample(example);
        PageInfo<CouponUser> pageInfo = new PageInfo<>(couponUsers);
        int total = (int)pageInfo.getTotal();
        return ListData.data(total, couponUsers);
    }

    @Override
    public Coupon queryCouponById(Integer id) {
        Coupon coupon = couponMapper.selectByPrimaryKey(id);
        return coupon;
    }

    @Override
    public Coupon addCoupon(Coupon coupon) {
        coupon.setAddTime(new Date());
        coupon.setUpdateTime(new Date());
        coupon.setDeleted(false);
        int insert = couponMapper.insert(coupon);
        Coupon insertCoupon = couponMapper.selectByPrimaryKey(coupon.getId());

        return insertCoupon;
    }

    @Override
    public ListData<Coupon> queryCouponList(Integer page, Integer limit, String name, Integer type, Integer status, String sort, String order) {
        PageHelper.startPage(page, limit);
        CouponExample example = new CouponExample();
        example.setOrderByClause(sort + " " + order);

        CouponExample.Criteria criteria = example.createCriteria();
        if(name != null && !name.equals("")) criteria.andNameLike("%" + name + "%");
        if(status != null) criteria.andStatusEqualTo(status);
        if(type != null) criteria.andTypeEqualTo(type);
        List<Coupon> coupons = couponMapper.selectByExample(example);
        PageInfo<Coupon> couponPageInfo = new PageInfo<>(coupons);
        int total = (int)couponPageInfo.getTotal();
        return ListData.data(total, coupons);
    }
}
