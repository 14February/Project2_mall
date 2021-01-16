package com.cskaoyan.mapper.backstage;

import com.cskaoyan.bean.both.coupon.Coupon;
import com.cskaoyan.bean.both.coupon.CouponExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CouponMapper {
    long countByExample(CouponExample example);

    int deleteByExample(CouponExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Coupon record);

    int insertSelective(Coupon record);

    List<Coupon> selectByExample(CouponExample example);

    Coupon selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Coupon record, @Param("example") CouponExample example);

    int updateByExample(@Param("record") Coupon record, @Param("example") CouponExample example);

    int updateByPrimaryKeySelective(Coupon record);

    int updateByPrimaryKey(Coupon record);

    List<Coupon> queryCouponListWithPage(@Param("offset") Integer offset,@Param("size") Integer size);

    int queryCouponTotal();

    void updateToInvalidByCouponId(@Param("couponId") Integer couponId);

    void decreaseOneByCouponId(@Param("couponId") Integer couponId);

    List<Coupon> queryCouponListWithPageByUserIdAndStatus(@Param("userId") Integer userId,@Param("status") Integer status,@Param("offset") Integer offset,
                                                          @Param("size") Integer size);

    int queryCouponTotalByUserIdAndStatus(@Param("userId") Integer userId,@Param("status") Integer status);

    List<Coupon> selectValidCouponsByUserId(@Param("userId") Integer userId);
}