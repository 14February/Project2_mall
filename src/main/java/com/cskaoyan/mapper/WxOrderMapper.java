package com.cskaoyan.mapper;


import com.cskaoyan.bean.wx.order.WxOrder;
import com.cskaoyan.bean.wx.order.WxOrderExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WxOrderMapper {
    long countByExample(WxOrderExample example);

    int deleteByExample(WxOrderExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(WxOrder record);

    int insertSelective(WxOrder record);

    List<WxOrder> selectByExample(WxOrderExample example);

    WxOrder selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") WxOrder record, @Param("example") WxOrderExample example);

    int updateByExample(@Param("record") WxOrder record, @Param("example") WxOrderExample example);

    int updateByPrimaryKeySelective(WxOrder record);

    int updateByPrimaryKey(WxOrder record);


    void cancelOrderById(@Param("orderId") Integer orderId);

    void confirmOrder(@Param("orderId") Integer orderId, @Param("number") Integer number);

    Integer selectUserIdById(@Param("orderGoodsId") Integer orderGoodsId);

    void updateCommentStatusById(@Param("orderGoodsId") Integer orderGoodsId);

    void updateDeletedByOrderId(@Param("orderId") Integer orderId);

    void refund(@Param("orderId") Integer orderId);

    Integer selectOrderIdByOrderSn(@Param("orderSn") String orderSn);

    void updateStatusById(@Param("orderId") Integer orderId);

    List<WxOrder> selectByUserId(@Param("userId") Integer userId);
}