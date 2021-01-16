package com.cskaoyan.service;


import com.cskaoyan.bean.wx.order.*;

/**
 * @author zyhstart
 * @description
 * @create 2021-01-12 12:43
 */
public interface WxOrderService {
    OrderListVo queryList(Integer showType, Integer page, Integer size,Integer userId);

    OrderDetail queryOrderDetail(Integer orderId);

    void cancelOrder(Integer orderId);

    void confirmOrder(Integer orderId);


    WxOrderGoods commentGood(Integer goodsId, Integer orderId);

    void comment(OrderCommentBo orderComment,Integer userId);

    void deleteOrder(Integer orderId);

    void refund(Integer orderId);

    SubmitOrderVo submit(SubmitOrderBo submitOrderBo);

    void prepay(Integer orderId);
}
