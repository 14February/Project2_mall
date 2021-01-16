package com.cskaoyan.bean.wx.cart;

import lombok.Data;

import java.util.List;

@Data
public class CheckoutVo {

    /**
     * grouponPrice : 0
     * grouponRulesId : 0
     * checkedAddress : {"id":5,"name":"松哥哥","userId":1,"provinceId":17,"cityId":201,"areaId":1876,"address":"花山街道软件新城3期22","mobile":"15976753826","isDefault":true,"addTime":"2020-04-27 20:58:25","updateTime":"2021-01-13 22:17:00","deleted":false}
     * actualPrice : 457
     * orderTotalPrice : 457
     * couponPrice : 2
     * availableCouponLength : 3
     * couponId : 43
     * freightPrice : 0
     * checkedGoodsList : [{"id":289,"userId":1,"goodsId":1166008,"goodsSn":"1166008","goodsName":"Carat钻石 不粘厨具组合","productId":244,"price":459,"number":1,"specifications":["标准"],"checked":true,"picUrl":"http://yanxuan.nosdn.127.net/615a16e899e01efb780c488df4233f48.png","addTime":"2021-01-13 22:39:32","updateTime":"2021-01-13 22:39:32","deleted":false}]
     * goodsTotalPrice : 459
     * addressId : 5
     */

    private int grouponPrice;
    private int grouponRulesId;
    private CheckedAddressVo checkedAddress;
    private int actualPrice;
    private int orderTotalPrice;
    private int couponPrice;
    private int availableCouponLength;
    private int couponId;
    private int freightPrice;
    private int goodsTotalPrice;
    private int addressId;
    private List<Cart> checkedGoodsList;

    public int getGrouponPrice() {
        return grouponPrice;
    }

    public void setGrouponPrice(int grouponPrice) {
        this.grouponPrice = grouponPrice;
    }

    public int getGrouponRulesId() {
        return grouponRulesId;
    }

    public void setGrouponRulesId(int grouponRulesId) {
        this.grouponRulesId = grouponRulesId;
    }

    public int getActualPrice() {
        return actualPrice;
    }

    public void setActualPrice(int actualPrice) {
        this.actualPrice = actualPrice;
    }

    public int getOrderTotalPrice() {
        return orderTotalPrice;
    }

    public void setOrderTotalPrice(int orderTotalPrice) {
        this.orderTotalPrice = orderTotalPrice;
    }

    public int getCouponPrice() {
        return couponPrice;
    }

    public void setCouponPrice(int couponPrice) {
        this.couponPrice = couponPrice;
    }

    public int getAvailableCouponLength() {
        return availableCouponLength;
    }

    public void setAvailableCouponLength(int availableCouponLength) {
        this.availableCouponLength = availableCouponLength;
    }

    public int getCouponId() {
        return couponId;
    }

    public void setCouponId(int couponId) {
        this.couponId = couponId;
    }

    public int getFreightPrice() {
        return freightPrice;
    }

    public void setFreightPrice(int freightPrice) {
        this.freightPrice = freightPrice;
    }

    public int getGoodsTotalPrice() {
        return goodsTotalPrice;
    }

    public void setGoodsTotalPrice(int goodsTotalPrice) {
        this.goodsTotalPrice = goodsTotalPrice;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

}
