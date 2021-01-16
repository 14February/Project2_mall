package com.cskaoyan.service;

import com.cskaoyan.bean.backstage.stat.*;
import com.cskaoyan.mapper.backstage.StatMapper;
import com.cskaoyan.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StatServiceImpl implements StatService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    StatMapper statMapper;
    @Override
    public UserGrowth queryUser() {
        int count = (int) userMapper.countByExample(null);
        UserGrowth userGrowth = new UserGrowth();
        UserGrowth.RowsBean rowsBean = new UserGrowth.RowsBean();
        //赋值
        rowsBean.setUsers(count);
        ArrayList<UserGrowth.RowsBean> rowsBeans = new ArrayList<>();
        rowsBeans.add(rowsBean);
        userGrowth.setRows(rowsBeans);
        return userGrowth;
    }

    @Override
    public OrderStat queryOrder() {
        List<OrderBean> orderStats = statMapper.selectOrderByDay();
        for (OrderBean orderStat : orderStats) {
            orderStat.setPcr(orderStat.getAmount()/orderStat.getCustomers());
        }
        OrderStat orderStat = new OrderStat();
        orderStat.setRows(orderStats);
        return orderStat;
    }

    @Override
    public GoodsStat queryGoods() {
        List<GoodsBean> goodsBeans = statMapper.selectByDay();
        GoodsStat goodsStat = new GoodsStat();
        goodsStat.setRows(goodsBeans);
        return goodsStat;
    }
}
