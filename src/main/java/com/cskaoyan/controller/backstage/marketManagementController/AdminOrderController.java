package com.cskaoyan.controller.backstage.marketManagementController;

import com.cskaoyan.bean.both.BaseRespVo;
import com.cskaoyan.bean.backstage.ListData;
import com.cskaoyan.bean.backstage.marketManagement.adminOrder.Order;
import com.cskaoyan.bean.backstage.marketManagement.adminOrder.OrderDetail;
import com.cskaoyan.bean.backstage.marketManagement.adminOrder.OrderShip;

import com.cskaoyan.map.OperationMap;
import com.cskaoyan.service.MarketManageService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Map;

@RestController
@RequestMapping("/admin/order")
public class AdminOrderController{

    @Autowired
    MarketManageService marketManageService;
    @Autowired
    OperationMap operationMap;

    @RequestMapping("/list")
    public BaseRespVo orderList(Integer page,Integer limit,String sort,String order,
                                Short[] orderStatusArray,String orderSn,Integer userId){

        ListData<Order> orderList = marketManageService.orderList(page,limit,sort,order,orderStatusArray,orderSn,userId);
        return BaseRespVo.ok(orderList);
    }

    @RequestMapping("/detail")
    public BaseRespVo orderDetail(Integer id){
        OrderDetail orderDetail = marketManageService.orderDetail(id);
        return BaseRespVo.ok(orderDetail);
    }

    @RequestMapping("/refund")
    public BaseRespVo orderReturn(@RequestBody Map map, HttpServletRequest request){

        Integer orderId = (Integer) map.get("orderId");
        Integer refundMoney = (Integer) map.get("refundMoney");
        BigDecimal bigDecimal = marketManageService.orderReturn(orderId);
        operationMap.insertOperations(request, SecurityUtils.getSubject(), OperationMap.operations.get(31), true);
        return BaseRespVo.ok("已退" + bigDecimal );
    }


    @RequestMapping("/ship")
    public BaseRespVo orderShip(@RequestBody OrderShip orderShip,HttpServletRequest request){
        operationMap.insertOperations(request, SecurityUtils.getSubject(), OperationMap.operations.get(32), true);
        marketManageService.orderShip(orderShip);
        return BaseRespVo.ok();
    }
}
