package com.cskaoyan.controller.backstage;

import com.cskaoyan.bean.both.BaseRespVo;
import com.cskaoyan.bean.both.coupon.Coupon;
import com.cskaoyan.bean.both.coupon.CouponUser;
import com.cskaoyan.bean.backstage.ListData;
import com.cskaoyan.map.OperationMap;
import com.cskaoyan.service.CouponService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("admin/coupon")
public class AdminCouponController{
    @Autowired
    CouponService couponService;
    @Autowired
    OperationMap operationMap;
    @RequestMapping("list")
    public BaseRespVo list(Integer page, Integer limit, String name, Integer type, Integer status, String sort, String order) {
        ListData<Coupon> coupons = couponService.queryCouponList(page, limit, name, type, status, sort, order);
        return BaseRespVo.ok(coupons);
    }
    @PostMapping("create")
    public BaseRespVo create(HttpServletRequest request, @RequestBody Coupon coupon) {
        Coupon insertCoupon = couponService.addCoupon(coupon);
        operationMap.insertOperations(request, SecurityUtils.getSubject(), OperationMap.operations.get(12), true);
        return BaseRespVo.ok(insertCoupon);
    }
    @GetMapping("listuser")
    public BaseRespVo listUser(Integer page, Integer limit, Integer userId, Integer status, Integer couponId, String sort, String order) {
        ListData<CouponUser> couponUsers = couponService.queryCouponUserList(page, limit, userId, status, couponId, sort, order);
        return BaseRespVo.ok(couponUsers);
    }
    @GetMapping("read")
    public BaseRespVo read(Integer id) {
        Coupon coupon = couponService.queryCouponById(id);
        return BaseRespVo.ok(coupon);
    }
    @PostMapping("update")
    public BaseRespVo update(HttpServletRequest request, @RequestBody Coupon coupon) {
        Coupon updateCoupon = couponService.update(coupon);
        operationMap.insertOperations(request,SecurityUtils.getSubject(),OperationMap.operations.get(13), true);
        return BaseRespVo.ok(updateCoupon);
    }
    @PostMapping("delete")
    public BaseRespVo delete(HttpServletRequest request, @RequestBody Coupon coupon) {
        couponService.delete(coupon);
        operationMap.insertOperations(request,SecurityUtils.getSubject(),OperationMap.operations.get(14), true);
        return BaseRespVo.ok();
    }
}

