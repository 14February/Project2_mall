package com.cskaoyan.bean.backstage.marketManagement.adminOrder;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class Order {
    private Integer id;

    private Integer userId;

    private String orderSn;

    private Short orderStatus;

    private String consignee;

    private String mobile;

    private String address;

    private String message;

    private BigDecimal goodsPrice;

    private BigDecimal freightPrice;

    private BigDecimal couponPrice;

    private BigDecimal integralPrice;

    private BigDecimal grouponPrice;

    private BigDecimal orderPrice;

    private BigDecimal actualPrice;


    private String payId;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date payTime;

    private String shipSn;

    private String shipChannel;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date shipTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date confirmTime;

    private Short comments;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date addTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    private Boolean deleted;


}