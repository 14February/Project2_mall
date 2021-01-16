package com.cskaoyan.bean.wx;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AddressDetail {

        /**
         * isDefault : false
         * areaId : 1881
         * address : 珞喻路129号
         *
         * cityName : 武汉市
         * provinceName : 湖北省
         * areaName : 洪山区
         *
         * name : 刘师傅
         * mobile : 18375730021
         * id : 3
         * cityId : 201
         *
         * provinceId : 17
         */

        @JsonProperty("isDefault")
        private Boolean isDefault;
        @JsonProperty("areaId")
        private Integer areaId;
        @JsonProperty("address")
        private String address;
        @JsonProperty("cityName")
        private String cityName;
        @JsonProperty("areaName")
        private String areaName;
        @JsonProperty("name")
        private String name;
        @JsonProperty("mobile")
        private String mobile;
        @JsonProperty("id")
        private Integer id;
        @JsonProperty("cityId")
        private Integer cityId;
        @JsonProperty("provinceName")
        private String provinceName;
        @JsonProperty("provinceId")
        private Integer provinceId;
}
