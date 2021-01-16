package com.cskaoyan.bean.marketManagement.adminCategory;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class CategoryL1 {
        @JsonProperty("value")
        private Integer value;
        @JsonProperty("label")
        private String label;
}
