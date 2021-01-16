package com.cskaoyan.bean.wx.cart;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartVo {
    private Integer id;

    private Integer userId;

    private Integer goodsId;

    private String goodsSn;

    private String goodsName;

    private Integer productId;

    private BigDecimal price;

    private Short number;

    private String[] specifications;

    private Integer checked;

    private String picUrl;

    private Date addTime;

    private Date updateTime;

    private Integer deleted;
}
