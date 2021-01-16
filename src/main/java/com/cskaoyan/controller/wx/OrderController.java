package com.cskaoyan.controller.wx;

import com.cskaoyan.bean.both.BaseRespVo;
import com.cskaoyan.bean.wx.order.*;
import com.cskaoyan.service.UserService;
import com.cskaoyan.service.WxOrderService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zyhstart
 * @description
 * @create 2021-01-12 12:42
 */
@RestController
@RequestMapping("wx/order")
public class OrderController{

    @Autowired
    WxOrderService wxOrderService;
    @Autowired
    UserService userService;



    @RequestMapping("list")
    public BaseRespVo list(Integer showType,Integer page,Integer size){
        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getPrincipal();
        Integer userId = userService.selectUserByUsername(username);
        OrderListVo orderListVo = wxOrderService.queryList(showType,page,size,userId);
        return BaseRespVo.ok(orderListVo);
    }
    @RequestMapping("detail")
    public BaseRespVo detail(Integer orderId){
        OrderDetail orderDetail = wxOrderService.queryOrderDetail(orderId);
        return BaseRespVo.ok(orderDetail);
    }
    @RequestMapping("cancel")
    public BaseRespVo cancel(@RequestBody WxOrderBo cancelBo){
        wxOrderService.cancelOrder(cancelBo.getOrderId());
        return BaseRespVo.ok();
    }
    @RequestMapping("confirm")
    public BaseRespVo confirm(@RequestBody WxOrderBo confirmBo){
        wxOrderService.confirmOrder(confirmBo.getOrderId());
        return BaseRespVo.ok();
    }
    @RequestMapping("goods")
    public BaseRespVo goods(Integer goodsId, Integer orderId){
      WxOrderGoods wxOrderGoods = wxOrderService.commentGood(goodsId,orderId);
        return BaseRespVo.ok(wxOrderGoods);
    }
    @RequestMapping("comment")
    public BaseRespVo comment(@RequestBody OrderCommentBo orderComment){
        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getPrincipal();
        Integer userId = userService.selectUserByUsername(username);
        wxOrderService.comment(orderComment,userId);
        return BaseRespVo.ok();
    }
    @RequestMapping("delete")
    public BaseRespVo delete(@RequestBody WxOrderBo deleteBo){
        wxOrderService.deleteOrder(deleteBo.getOrderId());
        return BaseRespVo.ok();
    }
    @RequestMapping("refund")
    public BaseRespVo refund(@RequestBody RefundBo refundBo){
        wxOrderService.refund(refundBo.getOrderId());
        return BaseRespVo.ok();
    }
    @RequestMapping("submit")
    public BaseRespVo submit(@RequestBody SubmitOrderBo submitOrderBo){
       SubmitOrderVo submitOrderVo = wxOrderService.submit(submitOrderBo);
       return BaseRespVo.ok(submitOrderVo);
    }
    @RequestMapping("prepay")
    public BaseRespVo prepay(@RequestBody PrepayOrderBo prepayOrderBo){
        wxOrderService.prepay(prepayOrderBo.getOrderId());
        return BaseRespVo.fail(724,"订单不能支付");
    }
}
