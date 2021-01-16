package com.cskaoyan.bean.wx;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * @ProjectName: project2
 * @ClassName: CommentVo
 * @TODO: TODO
 * @Author caifanglin
 * @Create 2021-01-12 21:11
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentVo {

    /**
     * data : [{"userInfo":{"nickName":"测试用户","avatarUrl":"https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif?imageView2/1/w/80/h/80"},"addTime":"2018-02-01 00:00:00","picList":[],"content":"是记忆棉 很满意"},{"userInfo":{"nickName":"测试用户","avatarUrl":"https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif?imageView2/1/w/80/h/80"},"addTime":"2018-02-01 00:00:00","picList":[],"content":"很好的东西"},{"userInfo":{"nickName":"测试用户","avatarUrl":"https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif?imageView2/1/w/80/h/80"},"addTime":"2018-02-01 00:00:00","picList":[],"content":"很舒服，有没有那么的软，不错！"},{"userInfo":{"nickName":"测试用户","avatarUrl":"https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif?imageView2/1/w/80/h/80"},"addTime":"2018-02-01 00:00:00","picList":[],"content":"确实舒服，不过夏天会不会热啊？"},{"userInfo":{"nickName":"测试用户","avatarUrl":"https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif?imageView2/1/w/80/h/80"},"addTime":"2018-02-01 00:00:00","picList":[],"content":"有点过软。等到夏季，上面直接铺凉席的话，不知道透气性会怎样？"},{"userInfo":{"nickName":"测试用户","avatarUrl":"https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif?imageView2/1/w/80/h/80"},"addTime":"2018-02-01 00:00:00","picList":[],"content":"包装完好\n无异味\n厚度适中\n慢回弹\n\n我直接放置在地板上，当做孩子的爬爬垫使用，上面铺床单，便于洗涤。作为直接铺地板的，有一个问题就是是否透气，否则用了1个月，反面肯定是发霉的状态，等实际效果"},{"userInfo":{"nickName":"测试用户","avatarUrl":"https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif?imageView2/1/w/80/h/80"},"addTime":"2018-02-01 00:00:00","picList":[],"content":"东西挺好，为了脊柱而买"}]
     * count : 7
     * currentPage : 1
     */

    private int count;
    private int currentPage;
    private List<DataBean> data;


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DataBean {
        /**
         * userInfo : {"nickName":"测试用户","avatarUrl":"https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif?imageView2/1/w/80/h/80"}
         * addTime : 2018-02-01 00:00:00
         * picList : []
         * content : 是记忆棉 很满意
         */

        private UserInfoBean userInfo;
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GTM+8")
        private Date addTime;
        private String content;
        private String[] picList;


        @Data
        @AllArgsConstructor
        @NoArgsConstructor
        public static class UserInfoBean {
            /**
             * nickName : 测试用户
             * avatarUrl : https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif?imageView2/1/w/80/h/80
             */

            private String nickName;
            private String avatarUrl;

        }
    }
}
