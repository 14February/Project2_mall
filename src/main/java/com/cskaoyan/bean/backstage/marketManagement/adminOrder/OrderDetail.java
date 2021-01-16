package com.cskaoyan.bean.backstage.marketManagement.adminOrder;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@Data
public class OrderDetail {

        @JsonProperty("user")
        private UserDTO user;
        @JsonProperty("order")
        private OrderDTO order;
        @JsonProperty("orderGoods")
        private List<OrderGoodsDTO> orderGoods;


        @AllArgsConstructor
        @NoArgsConstructor
        @Data
        public static class UserDTO {

            @JsonProperty("nickname")
            private String nickname;
            @JsonProperty("avatar")
            private String avatar;
        }

        @AllArgsConstructor
        @NoArgsConstructor
        @Data
        public static class OrderDTO {

            @JsonProperty("id")
            private Integer id;

            @JsonProperty("userId")
            private Integer userId;

            @JsonProperty("orderSn")
            private String orderSn;

            @JsonProperty("orderStatus")
            private Short orderStatus;

            @JsonProperty("consignee")
            private String consignee;

            @JsonProperty("mobile")
            private String mobile;

            @JsonProperty("address")
            private String address;

            @JsonProperty("message")
            private String message;

            @JsonProperty("goodsPrice")
            private BigDecimal goodsPrice;

            @JsonProperty("freightPrice")
            private BigDecimal freightPrice;

            @JsonProperty("couponPrice")
            private BigDecimal couponPrice;

            @JsonProperty("integralPrice")
            private BigDecimal integralPrice;

            @JsonProperty("grouponPrice")
            private BigDecimal grouponPrice;

            @JsonProperty("orderPrice")
            private BigDecimal orderPrice;

            @JsonProperty("actualPrice")
            private BigDecimal actualPrice;

            @JsonProperty("comments")
            private Short comments;

            @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
            @JsonProperty("addTime")
            private Date addTime;

            @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
            @JsonProperty("updateTime")
            private Date updateTime;

            @JsonProperty("deleted")
            private Boolean deleted;


            private String shipChannel;
            private String shipSn;

            @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
            private Date payTime;
            @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
            private Date confirmTime;
            @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
            private Date shipTime;
        }

        @AllArgsConstructor
        @NoArgsConstructor
        @Data
        public static class OrderGoodsDTO {

            private Integer id;

            private Integer orderId;

            private Integer goodsId;

            private String goodsName;

            private String goodsSn;

            private Integer productId;

            private Short number;

            private BigDecimal price;

            private String picUrl;

            private Integer comment;

            @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
            private Date addTime;

            @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
            private Date updateTime;

            private Boolean deleted;

            private List<String> specifications;
        }
    }

