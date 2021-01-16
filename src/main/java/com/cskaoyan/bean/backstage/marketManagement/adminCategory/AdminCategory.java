package com.cskaoyan.bean.backstage.marketManagement.adminCategory;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class AdminCategory {

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static class DataDTO {
        @JsonProperty("id")
        private Integer id;
        @JsonProperty("name")
        private String name;
        @JsonProperty("keywords")
        private String keywords;
        @JsonProperty("desc")
        private String desc;
        @JsonProperty("iconUrl")
        private String iconUrl;
        @JsonProperty("picUrl")
        private String picUrl;
        @JsonProperty("level")
        private String level;
        @JsonProperty("children")
        private List<ChildrenDTO> children;

        @NoArgsConstructor
        @AllArgsConstructor
        @Data
        public static class ChildrenDTO {

            @JsonProperty("id")
            private Integer id;
            @JsonProperty("name")
            private String name;
            @JsonProperty("keywords")
            private String keywords;
            @JsonProperty("desc")
            private String desc;
            @JsonProperty("iconUrl")
            private String iconUrl;
            @JsonProperty("picUrl")
            private String picUrl;
            @JsonProperty("level")
            private String level;
        }
    }
}
