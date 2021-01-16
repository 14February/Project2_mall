package com.cskaoyan.bean.wx;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AddressList {

        @JsonProperty("isDefault")
        private Boolean isDefault;
        @JsonProperty("detailedAddress")
        private String detailedAddress;
        @JsonProperty("name")
        private String name;
        @JsonProperty("mobile")
        private String mobile;
        @JsonProperty("id")
        private Integer id;

}
