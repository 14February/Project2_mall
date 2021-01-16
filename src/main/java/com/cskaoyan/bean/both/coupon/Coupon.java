package com.cskaoyan.bean.both.coupon;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class Coupon {
    private Integer id;

    private String name;

    private String desc;

    private String tag;

    private Integer total;

    private Double discount;

    private Double min;

    private Integer limit;

    private Integer type;

    private Integer status;

    private Integer goodsType;

    private Integer[] goodsValue;

    private String code;

    private Integer timeType;

    private Integer days;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date startTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date endTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date addTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

    private Boolean deleted;

}