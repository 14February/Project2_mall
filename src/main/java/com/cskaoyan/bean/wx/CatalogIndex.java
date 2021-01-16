package com.cskaoyan.bean.wx;

import com.cskaoyan.bean.backstage.marketManagement.adminCategory.Category;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CatalogIndex {


        //当前父目录
        @JsonProperty("currentCategory")
        private Category currentCategory;

        //当前父目录List
        @JsonProperty("categoryList")
        private List<Category> categoryList;

        //子类目
        @JsonProperty("currentSubCategory")
        private List<Category> currentSubCategory;

//        @NoArgsConstructor
//        @AllArgsConstructor
//        @Data
//        public static class CategoryListDTO {
//            /**
//             * id : 1010000
//             * name : 服装
//             * keywords : 444
//             * desc : 贴身的，要亲肤
//             * pid : 0
//             * iconUrl : http://yanxuan.nosdn.127.net/28a685c96f91584e7e4876f1397767db.png
//             * picUrl : http://yanxuan.nosdn.127.net/622c8d79292154017b0cbda97588a0d7.png
//             * level : L1
//             * sortOrder : 5
//             * addTime : 2018-02-01 00:00:00
//             * updateTime : 2021-01-11 18:07:27
//             * deleted : false
//             */
//
//            @JsonProperty("id")
//            private Integer id;
//            @JsonProperty("name")
//            private String name;
//            @JsonProperty("keywords")
//            private String keywords;
//            @JsonProperty("desc")
//            private String desc;
//            @JsonProperty("pid")
//            private Integer pid;
//            @JsonProperty("iconUrl")
//            private String iconUrl;
//            @JsonProperty("picUrl")
//            private String picUrl;
//            @JsonProperty("level")
//            private String level;
//            @JsonProperty("sortOrder")
//            private Byte sortOrder;
//
//            @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//            @JsonProperty("addTime")
//            private Date addTime;
//
//            @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//            @JsonProperty("updateTime")
//            private Date updateTime;
//            @JsonProperty("deleted")
//            private Boolean deleted;
//        }
//
//        @NoArgsConstructor
//        @AllArgsConstructor
//        @Data
//        public static class CurrentSubCategoryDTO {
//            /**
//             * id : 1010001
//             * name : 内衣
//             * keywords :
//             * desc : 给你贴身的关怀
//             * pid : 1010000
//             * iconUrl : http://yanxuan.nosdn.127.net/20279e1753e4eedc6e347857acda9681.png
//             * picUrl : http://yanxuan.nosdn.127.net/02fede55aba1bc6c9d7f7c01682f9e2d.png
//             * level : L2
//             * sortOrder : 2
//             * addTime : 2018-02-01 00:00:00
//             * updateTime : 2018-02-01 00:00:00
//             * deleted : false
//             */
//
//            @JsonProperty("id")
//            private Integer id;
//            @JsonProperty("name")
//            private String name;
//            @JsonProperty("keywords")
//            private String keywords;
//            @JsonProperty("desc")
//            private String desc;
//            @JsonProperty("pid")
//            private Integer pid;
//            @JsonProperty("iconUrl")
//            private String iconUrl;
//            @JsonProperty("picUrl")
//            private String picUrl;
//            @JsonProperty("level")
//            private String level;
//            @JsonProperty("sortOrder")
//            private Byte sortOrder;
//            @JsonProperty("addTime")
//            private Date addTime;
//            @JsonProperty("updateTime")
//            private Date updateTime;
//            @JsonProperty("deleted")
//            private Boolean deleted;
//        }
    }

