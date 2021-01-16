package com.cskaoyan.bean.backstage.marketManagement.adminBrand;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BrandCreat {
        /**
         * id : 1046003
         * name : iPhone
         * desc : Apple iPhone 12 (A2404) 128GB 红色 支持移动联通电信5G 双卡双待手机
         * floorPrice : 6799
         * addTime : 2021-01-10 11:31:37
         * updateTime : 2021-01-10 11:31:37
         */
        @JsonProperty("id")
        private Integer id;
        @JsonProperty("name")
        private String name;
        @JsonProperty("desc")
        private String desc;
        @JsonProperty("floorPrice")
        private BigDecimal floorPrice;

        private String picUrl;

        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
        @JsonProperty("addTime")
        private Date addTime;

        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
        @JsonProperty("updateTime")
        private Date updateTime;
}
