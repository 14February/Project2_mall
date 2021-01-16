package com.cskaoyan.service;

import com.cskaoyan.bean.backstage.goodsbean.Goods;
import com.cskaoyan.bean.backstage.goodsbean.GoodsProduct;
import com.cskaoyan.bean.backstage.user.User;
import com.cskaoyan.bean.both.coupon.Coupon;
import com.cskaoyan.bean.both.coupon.CouponUser;
import com.cskaoyan.bean.both.coupon.CouponUserExample;
import com.cskaoyan.bean.wx.cart.*;
import com.cskaoyan.mapper.CartsMapper;
import com.cskaoyan.mapper.UserMapper;
import com.cskaoyan.mapper.WxOrderMapper;
import com.cskaoyan.mapper.backstage.*;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    CartsMapper cartsMapper;
    @Autowired
    GoodsMapper goodsMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    GoodsproductMapper goodsproductMapper;
    @Autowired
    WxOrderMapper wxOrderMapper;
    @Autowired
    AddressMapper addressMapper;
    @Autowired
    CouponMapper couponMapper;
    @Autowired
    CouponUserMapper couponUserMapper;

    /**
     * checked=0 -->未选中
     * checked=1 -->选中
     * deleted=0 -->false,表示未删除
     * deleted=1 -->true,表示已删除
     *
     * @param addCart
     * @return
     */
    @Override
    //    @Transactional(rollbackFor = {RuntimeException.class,Error.class})
    public int add( AddCart addCart) {
        String username= (String) SecurityUtils.getSubject().getPrincipal();
        Short number =addCart.getNumber();
        //查询userId
        User user = userMapper.selectUserByUsername(username);
        Integer userId = user.getId();
        //查询goods
        Goods goods = goodsMapper.selectByPrimaryKey(addCart.getGoodsId());
        //查询商品规格
        GoodsProduct goodsProduct = goodsproductMapper.selectByPrimaryKey(addCart.getProductId());
        BigDecimal price = goodsProduct.getPrice();
        String[] specifications = goodsProduct.getSpecifications();
        int productId=addCart.getProductId();
        CartVo cart=cartsMapper.selectByProductIdAndUserId(productId,userId);
        if(cart==null){
            CartVo newCart = new CartVo(null,userId,addCart.getGoodsId(),goods.getGoodsSn(),goods.getName(),
                    productId,price,number,specifications,1,goods.getPicUrl(),
                    new Date(),null,0);
            cartsMapper.insertCartVoSelective(newCart);
        }else {                                         //不为null,修改购物车存在的商品的数量即可
            Short cartNumber = cart.getNumber();
            Short addNumber=addCart.getNumber();
            int totalNumber=cartNumber+addNumber;
            cartsMapper.updateByProductIdAndUserId(productId,userId,totalNumber);
        }
        return getUserCartCount(userId);
    }

    //设置唯一订单编号order_sn
    private String getGoodsSn() {
        LocalDate now = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String date = now.format(formatter);
        Random random = new Random();
        int randomNumber = random.nextInt(999999);
        String value = String.valueOf(randomNumber);
        return date+value;
    }

    /**
     * 获取购物车中的商品数量
     * @param userId
     * @return
     */
    private int getUserCartCount(Integer userId) {
        Integer integer = cartsMapper.countGoodsByUserId(userId);
        if(integer==null){
            return 0;
        }
        return integer;
    }

    @Override
    public int getGoodsCount(String username) {
        User user = userMapper.selectUserByUsername(username);
        Integer userId = user.getId();
        return getUserCartCount(userId);
    }

    @Override
    public CartIndexVo queryCart(boolean isChecked) {
        String username= (String) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
        User user = userMapper.selectUserByUsername(username);
        Integer userId = user.getId();
        int userCartCount = getUserCartCount(userId);
        CartIndexVo.CartTotalBean cartTotalBean = new CartIndexVo.CartTotalBean();
        cartTotalBean.setGoodsCount(userCartCount);
        List<CartVo> cartVos=null;
        if(!isChecked){
            cartVos= cartsMapper.selectCartByUserId(userId);
        }else {
            cartVos= cartsMapper.selectCheckedCartByUserId(userId);
        }

        int goodsAmount=0;
        int checkedGoodsCount=0;
        int checkedGoodsAmount=0;
        ArrayList<Cart> carts = new ArrayList<>();
        for (CartVo cartVo : cartVos) {
            Cart cart = new Cart();
            cart.setId(cartVo.getId());
            cart.setUserId(cartVo.getUserId());
            cart.setGoodsId(cartVo.getGoodsId());
            cart.setGoodsSn(cartVo.getGoodsSn());
            cart.setGoodsName(cartVo.getGoodsName());
            cart.setProductId(cartVo.getProductId());
            cart.setPrice(cartVo.getPrice());
            cart.setNumber(cartVo.getNumber());
            String[] specifications = cartVo.getSpecifications();
            cart.setSpecifications(specifications[0]);
            if(cartVo.getChecked()==1){
                cart.setChecked(true);
                checkedGoodsCount++;
                checkedGoodsAmount+=cartVo.getPrice().intValue()*cartVo.getNumber().intValue();
            }else {
                cart.setChecked(false);
            }
            cart.setPicUrl(cartVo.getPicUrl());
            cart.setAddTime(cartVo.getAddTime());
            cart.setUpdateTime(cartVo.getUpdateTime());
            cart.setDeleted(false);
            carts.add(cart);
            goodsAmount+=cartVo.getPrice().intValue()*cartVo.getNumber().intValue();
        }
        cartTotalBean.setGoodsAmount(goodsAmount);
        cartTotalBean.setCheckedGoodsCount(checkedGoodsCount);
        cartTotalBean.setCheckedGoodsAmount(checkedGoodsAmount);
        CartIndexVo cartIndexVo = new CartIndexVo();
        cartIndexVo.setCartTotal(cartTotalBean);
        cartIndexVo.setCartList(carts);
        return cartIndexVo;
    }

    @Override
    public void delete(Map map) {
        String username= (String) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
        User user = userMapper.selectUserByUsername(username);
        Integer userId = user.getId();
        List<Integer> productIds = (List<Integer>) map.get("productIds");
        for (Integer productId : productIds) {
            cartsMapper.deleteByProductId(productId,userId);
        }
    }

    @Override
    public void update(Map map) {
        Cart cart = new Cart();
        Object number = map.get("number");
        String num = number.toString();
        int id = (int) map.get("id");
        cart.setNumber(Short.valueOf(num));
        cart.setId(id);
        cart.setUpdateTime(new Date());
        cartsMapper.updateByPrimaryKeySelective(cart);
    }

    @Override
    public int checked(CheckedBo checkedBo) {
        String username= (String) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
        User user = userMapper.selectUserByUsername(username);
        Integer userId = user.getId();
        List<Integer> productIds = checkedBo.getProductIds();
        Integer isChecked = checkedBo.getIsChecked();
        int code=0;
        for (Integer productId : productIds) {
            code = cartsMapper.updateCheckedByProductIdAndUserId(userId, productId, isChecked);
        }
        return code;
    }

    @Override
    //    @Transactional(rollbackFor = {RuntimeException.class,Error.class})
    public int fastAdd(AddCart addCart) {
        add(addCart);
        return cartsMapper.selectCartId();
    }

    @Override
    public CheckoutVo checkout(Integer cartId, Integer addressId, Integer couponId, Integer grouponRulesId) {
        String username= (String) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
        User user = userMapper.selectUserByUsername(username);
        Integer userId = user.getId();
        CheckoutVo checkoutVo = new CheckoutVo();
        CartIndexVo cartIndexVo = queryCart(true);

        List<Cart> cartList = cartIndexVo.getCartList();
        checkoutVo.setCheckedGoodsList(cartList);
        int goodsTotalPrice=0;
        for (Cart cart : cartList) {
            int number = cart.getNumber();
            BigDecimal price = cart.getPrice();
            goodsTotalPrice += cart.getPrice().intValue() * cart.getNumber().intValue();
        }

        checkoutVo.setGrouponRulesId(grouponRulesId);//团购功能不需要实现，返回0
        checkoutVo.setGoodsTotalPrice(0);//团购功能不需要实现，返回0
        CheckedAddressVo checkedAddressVo = addressMapper.selectCheckedAddressByPrimaryKey(addressId);
        checkoutVo.setCheckedAddress(checkedAddressVo);

        int freightPrice=0;
        if(goodsTotalPrice>=88){
            checkoutVo.setFreightPrice(freightPrice);
            checkoutVo.setOrderTotalPrice(goodsTotalPrice);
        }else {
            checkoutVo.setFreightPrice(freightPrice+10);  //不足88元加10元运费
            checkoutVo.setOrderTotalPrice(goodsTotalPrice+10);
        }
        CouponUserExample example = new CouponUserExample();
        CouponUserExample.Criteria criteria1 = example.createCriteria();
        criteria1.andUserIdEqualTo(userId).andStatusEqualTo(Integer.valueOf("0")).
                andEndTimeGreaterThanOrEqualTo(new Date()).andDeletedEqualTo(false);
        int availableCouponLength = (int) couponUserMapper.countByExample(example);

        if(couponId==0){
            List<CouponUser> couponUsers = couponUserMapper.selectByExample(example);
            for (CouponUser couponUser : couponUsers) {
                couponId=couponUser.getCouponId();
                setCouponPriceAndActualPrice(couponId, checkoutVo);
            }
        }else if(couponId==-1){
            int couponPrice=0;
            checkoutVo.setCouponPrice(couponPrice);
            checkoutVo.setActualPrice(checkoutVo.getOrderTotalPrice()-couponPrice); //实付价格=订单总价-优惠金额
        } else {
            setCouponPriceAndActualPrice(couponId, checkoutVo);
        }
        checkoutVo.setAvailableCouponLength(availableCouponLength);
        checkoutVo.setCouponId(couponId);
        checkoutVo.setGoodsTotalPrice(goodsTotalPrice);
        checkoutVo.setAddressId(addressId);
        return checkoutVo;
    }

    private void setCouponPriceAndActualPrice(Integer couponId, CheckoutVo checkoutVo) {
        Coupon coupon = couponMapper.selectByPrimaryKey(couponId);
        int couponPrice = coupon.getDiscount().intValue();
        checkoutVo.setCouponPrice(couponPrice);
        checkoutVo.setActualPrice(checkoutVo.getOrderTotalPrice() - couponPrice); //实付价格=订单总价-优惠金额
    }
}
