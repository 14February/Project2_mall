package com.cskaoyan.bean.backstage.marketManagement.adminRegion;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class AdminRegion {


        /**
         * id : 1
         * name : 北京市
         * type : 1
         * code : 11
         * children : [{"id":32,"name":"市辖区","type":2,"code":1101,"children":[{"id":376,"name":"东城区","type":3,"code":110101},{"id":377,"name":"西城区","type":3,"code":110102},{"id":378,"name":"朝阳区","type":3,"code":110105},{"id":379,"name":"丰台区","type":3,"code":110106},{"id":380,"name":"石景山区","type":3,"code":110107},{"id":381,"name":"海淀区","type":3,"code":110108},{"id":382,"name":"门头沟区","type":3,"code":110109},{"id":383,"name":"房山区","type":3,"code":110111},{"id":384,"name":"通州区","type":3,"code":110112},{"id":385,"name":"顺义区","type":3,"code":110113},{"id":386,"name":"昌平区","type":3,"code":110114},{"id":387,"name":"大兴区","type":3,"code":110115},{"id":388,"name":"怀柔区","type":3,"code":110116},{"id":389,"name":"平谷区","type":3,"code":110117},{"id":390,"name":"密云区","type":3,"code":110118},{"id":391,"name":"延庆区","type":3,"code":110119}]}]
         */

        @JsonProperty("id")
        private Integer id;
        @JsonProperty("name")
        private String name;
        @JsonProperty("type")
        private Byte type;
        @JsonProperty("code")
        private Integer code;
        @JsonProperty("children")
        private List<Children1> children;

        @NoArgsConstructor
        @AllArgsConstructor
        @Data
        public static class Children1 {
            /**
             * id : 32
             * name : 市辖区
             * type : 2
             * code : 1101
             * children : [{"id":376,"name":"东城区","type":3,"code":110101},{"id":377,"name":"西城区","type":3,"code":110102},{"id":378,"name":"朝阳区","type":3,"code":110105},{"id":379,"name":"丰台区","type":3,"code":110106},{"id":380,"name":"石景山区","type":3,"code":110107},{"id":381,"name":"海淀区","type":3,"code":110108},{"id":382,"name":"门头沟区","type":3,"code":110109},{"id":383,"name":"房山区","type":3,"code":110111},{"id":384,"name":"通州区","type":3,"code":110112},{"id":385,"name":"顺义区","type":3,"code":110113},{"id":386,"name":"昌平区","type":3,"code":110114},{"id":387,"name":"大兴区","type":3,"code":110115},{"id":388,"name":"怀柔区","type":3,"code":110116},{"id":389,"name":"平谷区","type":3,"code":110117},{"id":390,"name":"密云区","type":3,"code":110118},{"id":391,"name":"延庆区","type":3,"code":110119}]
             */

            @JsonProperty("id")
            private Integer id;
            @JsonProperty("name")
            private String name;
            @JsonProperty("type")
            private Byte type;
            @JsonProperty("code")
            private Integer code;
            @JsonProperty("children")
            private List<AdminRegion.Children1.Children2> children;

            @NoArgsConstructor
            @AllArgsConstructor
            @Data
            public static class Children2 {
                /**
                 * id : 376
                 * name : 东城区
                 * type : 3
                 * code : 110101
                 */

                @JsonProperty("id")
                private Integer id;
                @JsonProperty("name")
                private String name;
                @JsonProperty("type")
                private Byte type;
                @JsonProperty("code")
                private Integer code;
            }
        }
}
