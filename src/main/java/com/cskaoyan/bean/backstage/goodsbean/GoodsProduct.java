package com.cskaoyan.bean.backstage.goodsbean;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
@Data
public class GoodsProduct{
    private Integer id;

    private Integer goodsId;

    private String[] specifications;

    private BigDecimal price;

    private Integer number;

    private String url;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GTM+8")
    private Date addTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GTM+8")
    private Date updateTime;

    private Boolean deleted;

}