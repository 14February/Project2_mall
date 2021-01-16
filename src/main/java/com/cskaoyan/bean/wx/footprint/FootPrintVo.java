package com.cskaoyan.bean.wx.footprint;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 *
 */
@Data
public class FootPrintVo{
    private Integer id;
    private String brief;
    private String picUrl;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date addTime;
    private String name;
    private Integer goodsId;
    private Double retailPrice;
}
