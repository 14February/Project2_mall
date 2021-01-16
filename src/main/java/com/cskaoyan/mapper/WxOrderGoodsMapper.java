package com.cskaoyan.mapper;


import com.cskaoyan.bean.wx.order.OrderGoodsVo;
import com.cskaoyan.bean.wx.order.WxOrderGoods;
import com.cskaoyan.bean.wx.order.WxOrderGoodsExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WxOrderGoodsMapper {
    long countByExample(WxOrderGoodsExample example);

    int deleteByExample(WxOrderGoodsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(WxOrderGoods record);

    int insertSelective(WxOrderGoods record);

    List<WxOrderGoods> selectByExample(WxOrderGoodsExample example);

    WxOrderGoods selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") WxOrderGoods record, @Param("example") WxOrderGoodsExample example);

    int updateByExample(@Param("record") WxOrderGoods record, @Param("example") WxOrderGoodsExample example);

    int updateByPrimaryKeySelective(WxOrderGoods record);

    int updateByPrimaryKey(WxOrderGoods record);

    List<OrderGoodsVo> selectByOrderId(@Param("id") Integer id);

    List<WxOrderGoods> selectdetailByOrderId(@Param("orderId") Integer orderId);

    WxOrderGoods selectByorderIdAndGoodsId(@Param("goodsId") Integer goodsId, @Param("orderId") Integer orderId);

    Integer selectGoodsIdById(@Param("orderGoodsId") Integer orderGoodsId);

    Integer selectUserIdById(@Param("orderGoodsId") Integer orderGoodsId);

    void updateCommentStatusById(@Param("orderGoodsId") Integer orderGoodsId);

    List<Integer> selectNumberByOrderId(@Param("orderId") Integer orderId);

    Integer selectOrderIdByOrderGoodsId(@Param("orderGoodsId") Integer orderGoodsId);
}