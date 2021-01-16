package com.cskaoyan.bean.wx;


import com.cskaoyan.bean.backstage.ListData;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CollectionList<T> {

        @JsonProperty("totalPages")
        private Integer totalPages;
        @JsonProperty("collectList")
        private List<CollectListDTO> collectList;


        public static <T> CollectionList<T> data(Integer totalPages, List<CollectListDTO> collectList)
        {
            CollectionList<T> listData = new CollectionList<>();
            listData.setCollectList(collectList);
            listData.setTotalPages(totalPages);
            return listData;
        }



        @AllArgsConstructor
        @NoArgsConstructor
        @Data
        public static class CollectListDTO {
            /**
             * brief : 明星同款
             * picUrl : http://182.92.235.201:8083/wx/storage/fetch/yl929t0beuq4jgiq978e.jpg
             * valueId : 1181033
             * name : 古力娜扎同款
             * id : 28
             * type : 0
             * retailPrice : 888.0
             */

            @JsonProperty("brief")
            private String brief;
            @JsonProperty("picUrl")
            private String picUrl;
            @JsonProperty("valueId")
            private Integer valueId;
            @JsonProperty("name")
            private String name;
            @JsonProperty("id")
            private Integer id;
            @JsonProperty("type")
            private Integer type;
            @JsonProperty("retailPrice")
            private Integer retailPrice;
        }
}
