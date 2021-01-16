package com.cskaoyan.bean.wx.cart;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class CartIndexVo {

    /**
     * cartTotal : {"goodsCount":5,"checkedGoodsCount":0,"goodsAmount":5306,"checkedGoodsAmount":0}
     * cartList : [{"id":59,"userId":3,"goodsId":1116011,"goodsSn":"1116011","goodsName":"蔓越莓曲奇 200克","productId":167,"price":36,"number":3,"specifications":["标准"],"checked":false,"picUrl":"http://yanxuan.nosdn.127.net/767b370d07f3973500db54900bcbd2a7.png","addTime":"2021-01-12 16:06:29","updateTime":"2021-01-12 18:00:46","deleted":false},{"id":83,"userId":3,"goodsId":1135002,"goodsSn":"1135002","goodsName":"宫廷奢华真丝四件套","productId":204,"price":2599,"number":2,"specifications":["标准"],"checked":false,"picUrl":"http://yanxuan.nosdn.127.net/45548f26cfd0c7c41e0afc3709d48286.png","addTime":"2021-01-12 17:21:58","updateTime":"2021-01-12 18:00:46","deleted":false}]
     */

    private CartTotalBean cartTotal;
    private List<Cart> cartList;

    public CartTotalBean getCartTotal() {
        return cartTotal;
    }

    public void setCartTotal(CartTotalBean cartTotal) {
        this.cartTotal = cartTotal;
    }

    public List<Cart> getCartList() {
        return cartList;
    }

    public void setCartList(List<Cart> cartList) {
        this.cartList = cartList;
    }

    public static class CartTotalBean {
        /**
         * goodsCount : 5
         * checkedGoodsCount : 0
         * goodsAmount : 5306
         * checkedGoodsAmount : 0
         */

        private int goodsCount;
        private int checkedGoodsCount;
        private int goodsAmount;
        private int checkedGoodsAmount;

        public int getGoodsCount() {
            return goodsCount;
        }

        public void setGoodsCount(int goodsCount) {
            this.goodsCount = goodsCount;
        }

        public int getCheckedGoodsCount() {
            return checkedGoodsCount;
        }

        public void setCheckedGoodsCount(int checkedGoodsCount) {
            this.checkedGoodsCount = checkedGoodsCount;
        }

        public int getGoodsAmount() {
            return goodsAmount;
        }

        public void setGoodsAmount(int goodsAmount) {
            this.goodsAmount = goodsAmount;
        }

        public int getCheckedGoodsAmount() {
            return checkedGoodsAmount;
        }

        public void setCheckedGoodsAmount(int checkedGoodsAmount) {
            this.checkedGoodsAmount = checkedGoodsAmount;
        }
    }
}
