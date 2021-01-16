package com.cskaoyan.controller.wx;

import com.cskaoyan.bean.both.BaseRespVo;
import com.cskaoyan.bean.backstage.user.User;
import com.cskaoyan.bean.backstage.user.UserInfo;
import com.cskaoyan.bean.wx.LoginVo;
import com.cskaoyan.bean.wx.cart.AddCart;
import com.cskaoyan.bean.wx.cart.CartIndexVo;
import com.cskaoyan.bean.wx.cart.CheckedBo;
import com.cskaoyan.bean.wx.cart.CheckoutVo;
import com.cskaoyan.service.CartService;
import com.cskaoyan.token.MallToken;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("wx/cart")
public class CartController {

    @Autowired
    CartService cartService;

    @RequestMapping("add")
    public BaseRespVo add(@RequestBody AddCart addCart){
        int cartCount=cartService.add(addCart);
        return BaseRespVo.ok(cartCount);
    }

    @RequestMapping("index")
    public BaseRespVo index(){
        CartIndexVo cartIndexVo=cartService.queryCart(false);
        return BaseRespVo.ok(cartIndexVo);
    }
    @RequestMapping("goodscount")
    public BaseRespVo getGoodsCount(){
        String username = (String) SecurityUtils.getSubject().getPrincipal();
        int count=cartService.getGoodsCount(username);
        return BaseRespVo.ok(count);
    }
    @RequestMapping("update")
    public BaseRespVo update(@RequestBody Map map){
        cartService.update(map);
        return BaseRespVo.ok();
    }
    @RequestMapping("checked")
    public BaseRespVo checked(@RequestBody CheckedBo checkedBo){
        cartService.checked(checkedBo);
        CartIndexVo cartIndexVo=cartService.queryCart(false);
        return BaseRespVo.ok(cartIndexVo);
    }
    @RequestMapping("fastadd")
    public BaseRespVo fastAdd(@RequestBody AddCart addCart){
        int cartId=cartService.fastAdd(addCart);
        return BaseRespVo.ok(cartId);
    }
    @RequestMapping("checkout")
    public BaseRespVo checkout(Integer cartId,Integer addressId,Integer couponId,Integer grouponRulesId){
        CheckoutVo checkoutVo=cartService.checkout(cartId,addressId,couponId,grouponRulesId);
        return BaseRespVo.ok(checkoutVo);
    }
    @RequestMapping("delete")
    public BaseRespVo delete(@RequestBody Map productIds){
        cartService.delete(productIds);
        CartIndexVo cartIndexVo=cartService.queryCart(false);
        return BaseRespVo.ok(cartIndexVo);
    }
}
