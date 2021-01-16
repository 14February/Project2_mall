package com.cskaoyan.service;

import com.cskaoyan.bean.wx.cart.AddCart;
import com.cskaoyan.bean.wx.cart.CartIndexVo;
import com.cskaoyan.bean.wx.cart.CheckedBo;
import com.cskaoyan.bean.wx.cart.CheckoutVo;

import java.util.Map;

/**
 * 小程序购物车接口
 */
public interface CartService {

    int add( AddCart addCart);

    int getGoodsCount(String username);

    CartIndexVo queryCart(boolean isChecked);

    void delete(Map productIds);

    void update(Map map);

    int checked(CheckedBo checkedBo);

    int fastAdd(AddCart addCart);

    CheckoutVo checkout(Integer cartId, Integer addressId, Integer couponId, Integer grouponRulesId);
}
