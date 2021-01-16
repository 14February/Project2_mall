package com.cskaoyan.service;

import com.cskaoyan.bean.both.coupon.Coupon;
import com.cskaoyan.bean.both.coupon.CouponList;
import com.cskaoyan.bean.both.coupon.CouponUser;
import com.cskaoyan.bean.backstage.ListData;

import java.util.List;


public interface CouponService {
    ListData<Coupon> queryCouponList(Integer page, Integer limit, String name, Integer type, Integer status, String sort, String order);

    Coupon addCoupon(Coupon coupon);

    Coupon queryCouponById(Integer id);

    ListData<CouponUser> queryCouponUserList(Integer page, Integer limit, Integer userId, Integer status, Integer couponId, String sort, String order);

    Coupon update(Coupon coupon);

    void delete(Coupon coupon);

    CouponList queryCoupons(Integer page,Integer size);

    int receive(Integer couponId,String username);

    CouponList queryCouponsByUsernameAndStatus(String username,Integer status,Integer page,Integer size);

    List<Coupon> selectList(Integer cardId,String username);

    int exchange(String code,String username);
}
