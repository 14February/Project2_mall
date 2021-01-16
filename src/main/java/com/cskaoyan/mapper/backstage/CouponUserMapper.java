package com.cskaoyan.mapper.backstage;

import com.cskaoyan.bean.both.coupon.CouponUser;
import com.cskaoyan.bean.both.coupon.CouponUserExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CouponUserMapper {
    long countByExample(CouponUserExample example);

    int deleteByExample(CouponUserExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CouponUser record);

    int insertSelective(CouponUser record);

    List<CouponUser> selectByExample(CouponUserExample example);

    CouponUser selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CouponUser record, @Param("example") CouponUserExample example);

    int updateByExample(@Param("record") CouponUser record, @Param("example") CouponUserExample example);

    int updateByPrimaryKeySelective(CouponUser record);

    int updateByPrimaryKey(CouponUser record);

    int selectTotalCountByCouponIdAndUserId(@Param("couponId") Integer couponId,@Param("userId") Integer userId);

    List<Integer> selectCouponIdsByExample(@Param("userId") Integer userId);
}