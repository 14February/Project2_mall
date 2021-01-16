package com.cskaoyan.controller.wx;

import com.cskaoyan.bean.both.BaseRespVo;
import com.cskaoyan.bean.both.coupon.Coupon;
import com.cskaoyan.bean.both.coupon.CouponList;
import com.cskaoyan.service.CouponService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 */
@RestController
@RequestMapping("wx/coupon")
public class CouponController{

    @Autowired
    CouponService couponService;

    @RequestMapping("list")
    public BaseRespVo<CouponList> list(Integer page,Integer size)
    {
        CouponList couponList = couponService.queryCoupons(page,size);
        return BaseRespVo.ok(couponList);
    }

    @PostMapping("receive")
    public BaseRespVo<Object> receive(@RequestBody Map<String,Integer> map)
    {
        Integer couponId = map.get("couponId");
        Subject subject = SecurityUtils.getSubject();
        String username = subject.getPrincipals().toString();
        int status = couponService.receive(couponId,username);
        if(status == 0){
            return BaseRespVo.fail(740, "优惠券已经领取过");
        }else if(status == 1){
            return BaseRespVo.fail(740, "优惠券已领完");
        }
        return BaseRespVo.ok();
    }

    @RequestMapping("mylist")
    public BaseRespVo<CouponList> mylist(Integer status,Integer page,Integer size)
    {
        Subject subject = SecurityUtils.getSubject();
        String username = subject.getPrincipals().toString();
        CouponList couponList = couponService.queryCouponsByUsernameAndStatus(username,status,page,size);
        return BaseRespVo.ok(couponList);
    }

    @RequestMapping("selectlist")
    public BaseRespVo<List<Coupon>> selectList(Integer cartId,Integer grouponRulesId)
    {
        Subject subject = SecurityUtils.getSubject();
        String username = subject.getPrincipals().toString();
//        List<Coupon> coupons = new ArrayList<>();
//        if(cartId == 0) return BaseRespVo.ok(coupons);
//        coupons = couponService.selectList(cartId);
        List<Coupon> coupons = couponService.selectList(cartId,username);
        return BaseRespVo.ok(coupons);
    }

    @PostMapping("exchange")
    public BaseRespVo<Object> exchange(@RequestBody Map<String,String> map)
    {
        String code = map.get("code");
        Subject subject = SecurityUtils.getSubject();
        String username = subject.getPrincipals().toString();
        int status = couponService.exchange(code,username);
        if(status == 0) return BaseRespVo.fail(740, "优惠券已经兑换");
        else if(status == 1) return BaseRespVo.fail(740, "优惠券已兑换完");
        else if(status == 3) return BaseRespVo.fail(742, "优惠券不正确");
        return BaseRespVo.ok();
    }
}
