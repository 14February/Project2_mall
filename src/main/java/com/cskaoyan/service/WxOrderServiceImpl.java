package com.cskaoyan.service;

import com.cskaoyan.bean.backstage.Address;
import com.cskaoyan.bean.backstage.user.User;
import com.cskaoyan.bean.both.coupon.Coupon;
import com.cskaoyan.bean.wx.cart.Cart;
import com.cskaoyan.bean.wx.order.*;
import com.cskaoyan.mapper.UserMapper;
import com.cskaoyan.mapper.WxCommentMapper;
import com.cskaoyan.mapper.WxOrderGoodsMapper;
import com.cskaoyan.mapper.WxOrderMapper;
import com.cskaoyan.mapper.backstage.AddressMapper;
import com.cskaoyan.mapper.backstage.CouponMapper;
import com.cskaoyan.mapper.backstage.CouponUserMapper;
import com.cskaoyan.mapper.wx.CartMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author zyhstart
 * @description
 * @create 2021-01-12 12:43
 */
@Service
public class WxOrderServiceImpl implements WxOrderService {
    @Autowired
    WxOrderMapper wxOrderMapper;
    @Autowired
    WxOrderGoodsMapper wxOrderGoodsMapper;
    @Autowired
    WxCommentMapper wxCommentMapper;
    @Autowired
    AddressMapper addressMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    CartMapper cartMapper;
    @Autowired
    CouponMapper couponMapper;
//    @Autowired
//    CouponUserMapper couponUserMapper;



    @Override
    public OrderListVo queryList(Integer showType, Integer page, Integer size,Integer userId) {
        OrderListVo orderListVo = new OrderListVo();
        List<OrderListData> dataList = new ArrayList<>();
        List<OrderGoodsVo> goodList = new ArrayList<>();
        HandleOptionVo handleOption = new HandleOptionVo();

        if (showType == null || showType == 0) {             //获取全部订单
            return getOrderListVo(page, orderListVo, dataList, handleOption,userId);
        }else if (showType == 1){       //待付款
            OrderListVo orderListVo1 = getOrderListVo(page, orderListVo, dataList, handleOption,userId);
            List<OrderListData> data = orderListVo1.getData();
            List<OrderListData> notPaidData = new ArrayList<>();
            OrderListVo notPaidorderListVo = new OrderListVo();
            for (int i = 0; i < data.size(); i++) {
                if ("未付款".equals(data.get(i).getOrderStatusText())){
                    notPaidData.add(data.get(i));
                }
            }
            PageInfo<OrderListData> orderListDataPageInfo = new PageInfo<>(notPaidData);
            notPaidorderListVo.setTotalPages((int) orderListDataPageInfo.getTotal());
            notPaidorderListVo.setCount(notPaidData.size());
            notPaidorderListVo.setData(notPaidData);
            return notPaidorderListVo;

        }else if (showType == 2){       //待发货
            OrderListVo orderListVo1 = getOrderListVo(page, orderListVo, dataList, handleOption,userId);
            List<OrderListData> data = orderListVo1.getData();
            List<OrderListData> noShipData = new ArrayList<>();
            OrderListVo noShiporderListVo = new OrderListVo();
            for (int i = 0; i < data.size(); i++) {
                if ("已付款".equals(data.get(i).getOrderStatusText())){
                    noShipData.add(data.get(i));
                }
            }
            PageInfo<OrderListData> orderListDataPageInfo = new PageInfo<>(noShipData);
            noShiporderListVo.setTotalPages((int) orderListDataPageInfo.getTotal());
            noShiporderListVo.setCount(noShipData.size());
            noShiporderListVo.setData(noShipData);
            return noShiporderListVo;
        }else if (showType == 3){       //待收货
            OrderListVo orderListVo1 = getOrderListVo(page, orderListVo, dataList, handleOption,userId);
            List<OrderListData> data = orderListVo1.getData();
            List<OrderListData> shipData = new ArrayList<>();
            OrderListVo ShiporderListVo = new OrderListVo();
            for (int i = 0; i < data.size(); i++) {
                if ("已发货".equals(data.get(i).getOrderStatusText())){
                    shipData.add(data.get(i));
                }
            }
            PageInfo<OrderListData> orderListDataPageInfo = new PageInfo<>(shipData);
            ShiporderListVo.setTotalPages((int) orderListDataPageInfo.getTotal());
            ShiporderListVo.setCount(shipData.size());
            ShiporderListVo.setData(shipData);
            return ShiporderListVo;
        }else if (showType == 4){       //待评价
            OrderListVo orderListVo1 = getOrderListVo(page, orderListVo, dataList, handleOption,userId);
            List<OrderListData> data = orderListVo1.getData();
            List<OrderListData> notCommentData = new ArrayList<>();
            OrderListVo notCommentorderListVo = new OrderListVo();
            for (int i = 0; i < data.size(); i++) {
                if ("已收货".equals(data.get(i).getOrderStatusText())){
                    notCommentData.add(data.get(i));
                }
            }
            PageInfo<OrderListData> orderListDataPageInfo = new PageInfo<>(notCommentData);
            notCommentorderListVo.setTotalPages((int) orderListDataPageInfo.getTotal());
            notCommentorderListVo.setCount(notCommentData.size());
            notCommentorderListVo.setData(notCommentData);
            return notCommentorderListVo;
        }
        return null;
    }
    @Override
    public OrderDetail queryOrderDetail(Integer orderId) {
        OrderDetail orderDetail = new OrderDetail();
        List<WxOrderGoods> orderGoods = new ArrayList<>();
        HandleOptionVo handleOption = new HandleOptionVo();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        WxOrder wxOrder = wxOrderMapper.selectByPrimaryKey(orderId);
        OrderInfo orderInfo = new OrderInfo(wxOrder.getConsignee(),wxOrder.getAddress(),dateFormat.format(wxOrder.getAddTime()),wxOrder.getOrderSn(),wxOrder.getActualPrice(),wxOrder.getMobile(),null,wxOrder.getGoodsPrice(),wxOrder.getCouponPrice(),wxOrder.getId(),wxOrder.getFreightPrice(),null);
        Short status = wxOrder.getOrderStatus();
        if (status == 101){
            orderInfo.setOrderStatusText("未付款");
            handleOption.setPay(true);
            handleOption.setCancel(true);
        }
        if (status == 102){
            orderInfo.setOrderStatusText("已取消");
            handleOption.setDelete(true);
        }
        if (status == 103){
            orderInfo.setOrderStatusText("已取消(系统)");
            handleOption.setDelete(true);
        }
        if (status == 201){
            orderInfo.setOrderStatusText("已付款");
            handleOption.setRefund(true);
        }
        if (status == 202){
            orderInfo.setOrderStatusText("订单取消，退款中");
        }
        if (status == 203){
            orderInfo.setOrderStatusText("已退款");
            handleOption.setDelete(true);
        }
        if (status == 301){
            orderInfo.setOrderStatusText("已发货");
            handleOption.setConfirm(true);
        }
        if (status == 401){
            orderInfo.setOrderStatusText("已收货");
            handleOption.setDelete(true);
            handleOption.setComment(true);
            handleOption.setRebuy(true);
        }
        if (status == 402){
            orderInfo.setOrderStatusText("系统收货");
        }
        orderInfo.setHandleOption(handleOption);
        orderGoods = wxOrderGoodsMapper.selectdetailByOrderId(orderId);
        orderDetail.setOrderInfo(orderInfo);
        orderDetail.setOrderGoods(orderGoods);
        return orderDetail;
    }

    @Override
    public void cancelOrder(Integer orderId) {
        wxOrderMapper.cancelOrderById(orderId);
    }

    @Override
    public void confirmOrder(Integer orderId) {
        List<Integer> numbers = wxOrderGoodsMapper.selectNumberByOrderId(orderId);
        Integer orderNum = 0;
        for (Integer number : numbers) {
            orderNum = orderNum + number;
        }
        wxOrderMapper.confirmOrder(orderId,orderNum);
    }

    @Override
    public WxOrderGoods commentGood(Integer goodsId, Integer orderId) {
        WxOrderGoods wxOrderGoods = wxOrderGoodsMapper.selectByorderIdAndGoodsId(goodsId,orderId);
        return wxOrderGoods;
    }

    @Override
    public void comment(OrderCommentBo orderComment,Integer userId) {
        WxComment wxComment = new WxComment();
        wxComment.setContent(orderComment.getContent());
        wxComment.setPicUrls(orderComment.getPicUrls());
        wxComment.setHasPicture(orderComment.getHasPicture());
        wxComment.setStar(orderComment.getStar());
        wxComment.setAddTime( new Date());
        wxComment.setUpdateTime(new Date());
        wxComment.setDeleted(false);
        wxComment.setValueId(wxOrderGoodsMapper.selectGoodsIdById(orderComment.getOrderGoodsId()));
        wxComment.setUserId(userId);
        wxCommentMapper.insertSelective(wxComment);
        Integer orderId = wxOrderGoodsMapper.selectOrderIdByOrderGoodsId(orderComment.getOrderGoodsId());
        wxOrderMapper.updateCommentStatusById(orderId);
        System.out.println();
    }

    @Override
    public void deleteOrder(Integer orderId) {
        //wxOrderMapper.deleteByPrimaryKey(orderId);
        wxOrderMapper.updateDeletedByOrderId(orderId);
    }

    @Override
    public void refund(Integer orderId) {
        wxOrderMapper.refund(orderId);
    }

    @Override
    public SubmitOrderVo submit(SubmitOrderBo submitOrderBo) {
        WxOrder wxorder = new WxOrder();
        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getPrincipal();
        User user = userMapper.selectUserByUsername(username);
        Integer userId = user.getId();
        Coupon coupon = couponMapper.selectByPrimaryKey(submitOrderBo.getCouponId());
        Double discount;
        Double min;
        if (coupon == null){
           discount = 0.0;
           min =0.0;
        }else {
           discount = coupon.getDiscount();
            min = coupon.getMin();
        }
        Double freightPrice = 10.0;
        Double integralPrice= 0.0;
        List<Cart> carts  = cartMapper.selectByUserId(userId);
        double orderPrice = 0;
        for (int i = 0; i < carts.size(); i++) {
            Cart cart = carts.get(i);
            orderPrice = orderPrice + (cart.getPrice().doubleValue()) * (cart.getNumber());
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        Address address = addressMapper.selectByPrimaryKey(submitOrderBo.getAddressId());
        String orderSn = simpleDateFormat.format(new Date());
        //添加到order表
        //设置优惠券价格
        if (orderPrice > min){
            wxorder.setCouponPrice(discount);
        }else {
            wxorder.setCouponPrice(0.0);
        }
        //满88包邮
        if (orderPrice > 88){
            wxorder.setFreightPrice(0.0);
        }else {
            wxorder.setFreightPrice(freightPrice);
        }
        wxorder.setUserId(userId);
        wxorder.setOrderSn(orderSn);
        wxorder.setConsignee(address.getName());
        wxorder.setMobile(address.getMobile());
        wxorder.setAddress(address.getAddress());
        wxorder.setMessage(submitOrderBo.getMessage());
        wxorder.setGoodsPrice(orderPrice + freightPrice);
        wxorder.setOrderPrice(orderPrice - discount + freightPrice);
        wxorder.setIntegralPrice(0.0);
        wxorder.setActualPrice(orderPrice - integralPrice);
        wxorder.setGrouponPrice(0.0);
        wxorder.setAddTime(new Date());
        wxorder.setUpdateTime(new Date());
        wxorder.setOrderStatus((short) 101);

        wxOrderMapper.insertSelective(wxorder);
        Integer orderId = wxOrderMapper.selectOrderIdByOrderSn(orderSn);
        for (int i = 0; i < carts.size(); i++) {
            Cart cart = carts.get(i);
            //添加到order_good表 和修改cart的delete状态
            WxOrderGoods wxOrderGoods = new WxOrderGoods();
            wxOrderGoods.setOrderId(orderId);
            wxOrderGoods.setGoodsId(cart.getGoodsId());
            wxOrderGoods.setGoodsName(cart.getGoodsName());
            wxOrderGoods.setGoodsSn(cart.getGoodsSn());
            wxOrderGoods.setGoodsName(cart.getGoodsName());
            wxOrderGoods.setProductId(cart.getProductId());
            wxOrderGoods.setPrice(cart.getPrice());
            wxOrderGoods.setNumber(cart.getNumber());
            wxOrderGoods.setSpecifications(cart.getSpecifications());
            wxOrderGoods.setPicUrl(cart.getPicUrl());
            wxOrderGoods.setAddTime(new Date());
            wxOrderGoods.setUpdateTime(new Date());
            wxOrderGoodsMapper.insertSelective(wxOrderGoods);
            cartMapper.updateDeletedById(cart.getId());
        }

        return new SubmitOrderVo(orderId);
    }

    @Override
    public void prepay(Integer orderId) {
        wxOrderMapper.updateStatusById(orderId);
    }


    private OrderListVo getOrderListVo(Integer page, OrderListVo orderListVo, List<OrderListData> dataList, HandleOptionVo handleOption,Integer userId) {
        PageHelper.startPage(page, 20);
        WxOrderExample example = new WxOrderExample();
        List<WxOrder> wxOrders = wxOrderMapper.selectByUserId(userId);
        for (int i = 0; i < wxOrders.size(); i++) {
            WxOrder wxOrder = wxOrders.get(i);
            if (!wxOrder.getDeleted()){
             OrderListData orderListData = queryOrderListData(wxOrder, handleOption);
             dataList.add(orderListData);
            }
        }
        PageInfo<WxOrder> wxOrderPageInfo = new PageInfo<>(wxOrders);
        orderListVo.setTotalPages((int) wxOrderPageInfo.getTotal());
        orderListVo.setCount(wxOrders.size());
        orderListVo.setData(dataList);
        return orderListVo;
    }

    private OrderListData queryOrderListData(WxOrder wxOrder,HandleOptionVo handleOption) {
        List<OrderGoodsVo> goodList;
        OrderListData orderListData = new OrderListData();
        orderListData.setId(wxOrder.getId());
        Short status = wxOrder.getOrderStatus();
        if (status == 101){
            orderListData.setOrderStatusText("未付款");
            handleOption.setCancel(true);
            handleOption.setPay(true);
        }
        if (status == 102){
            orderListData.setOrderStatusText("已取消");
            handleOption.setDelete(true);
        }
        if (status == 103){
            orderListData.setOrderStatusText("已取消(系统)");
            handleOption.setDelete(true);
        }
        if (status == 201){
            orderListData.setOrderStatusText("已付款");
            handleOption.setRefund(true);
        }
        if (status == 202){
            orderListData.setOrderStatusText("订单取消，退款中");
        }
        if (status == 203){
            orderListData.setOrderStatusText("已退款");
            handleOption.setDelete(true);
        }
        if (status == 301){
            orderListData.setOrderStatusText("已发货");
            handleOption.setRefund(true);
        }
        if (status == 401){
            orderListData.setOrderStatusText("已收货");
            handleOption.setDelete(true);
            handleOption.setComment(true);
            handleOption.setRebuy(true);
            handleOption.setRefund(true);
        }
        if (status == 402){
            orderListData.setOrderStatusText("系统收货");
        }

        orderListData.setOrderSn(wxOrder.getOrderSn());
        orderListData.setActualPrice(wxOrder.getActualPrice());
        goodList = wxOrderGoodsMapper.selectByOrderId(wxOrder.getId());
        orderListData.setGoodsList(goodList);
        orderListData.setHandleOption(handleOption);
        return orderListData;
    }

}
