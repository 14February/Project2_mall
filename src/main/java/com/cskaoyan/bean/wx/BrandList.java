package com.cskaoyan.bean.wx;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor
@Data
public class BrandList {

        @JsonProperty("totalPages")
        private Integer totalPages;
        @JsonProperty("brandList")
        private List<BrandListDTO> brandList;

        @AllArgsConstructor
        @NoArgsConstructor
        @Data
        public static class BrandListDTO {
            /**
             * id : 1046019
             * name : aa
             * desc : aa
             * picUrl :
             * floorPrice : 11.0
             */

            @JsonProperty("id")
            private Integer id;
            @JsonProperty("name")
            private String name;
            @JsonProperty("desc")
            private String desc;
            @JsonProperty("picUrl")
            private String picUrl;
            @JsonProperty("floorPrice")
            private BigDecimal floorPrice;
        }

}
