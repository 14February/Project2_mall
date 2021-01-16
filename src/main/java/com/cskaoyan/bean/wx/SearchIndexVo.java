package com.cskaoyan.bean.wx;

import com.cskaoyan.bean.backstage.keyword.Keyword;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * @ProjectName: project2
 * @ClassName: SearchIndexVo
 * @TODO: TODO
 * @Author caifanglin
 * @Create 2021-01-12 14:32
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchIndexVo {

    /**
     * defaultKeyword : {"id":1,"keyword":"母亲节1","url":"mother1","isHot":true,"isDefault":true,"sortOrder":100,"addTime":"2018-02-01 00:00:00","updateTime":"2021-01-09 19:31:48","deleted":false}
     * hotKeywordList : [{"id":1,"keyword":"母亲节1","url":"mother1","isHot":true,"isDefault":true,"sortOrder":100,"addTime":"2018-02-01 00:00:00","updateTime":"2021-01-09 19:31:48","deleted":false},{"id":6,"keyword":"520元礼包抢先领","url":"","isHot":true,"isDefault":true,"sortOrder":1,"addTime":"2018-02-01 00:00:00","updateTime":"2018-02-01 00:00:00","deleted":false},{"id":11,"keyword":"父亲节2","url":"4399.com","isHot":true,"isDefault":true,"sortOrder":100,"addTime":"2021-01-09 21:36:17","updateTime":"2021-01-09 21:36:17","deleted":false},{"id":12,"keyword":"父亲节","url":"4399.com","isHot":true,"isDefault":true,"sortOrder":100,"addTime":"2021-01-09 21:47:30","updateTime":"2021-01-12 09:26:49","deleted":false},{"id":18,"keyword":"gwaga ","url":"wghag","isHot":true,"isDefault":true,"sortOrder":100,"addTime":"2021-01-12 09:29:04","updateTime":"2021-01-12 09:29:26","deleted":false}]
     * historyKeywordList : [{"keyword":"正义"}]
     */

    private DefaultKeywordBean defaultKeyword;
    private List<HotKeywordListBean> hotKeywordList;
    private List<HistoryKeywordListBean> historyKeywordList;


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DefaultKeywordBean {
        /**
         * id : 1
         * keyword : 母亲节1
         * url : mother1
         * isHot : true
         * isDefault : true
         * sortOrder : 100
         * addTime : 2018-02-01 00:00:00
         * updateTime : 2021-01-09 19:31:48
         * deleted : false
         */

        private int id;
        private String keyword;
        private String url;
        private boolean isHot;
        private boolean isDefault;
        private int sortOrder;
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GTM+8")
        private Date addTime;
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GTM+8")
        private Date updateTime;
        private boolean deleted;

    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class HotKeywordListBean {
        /**
         * id : 1
         * keyword : 母亲节1
         * url : mother1
         * isHot : true
         * isDefault : true
         * sortOrder : 100
         * addTime : 2018-02-01 00:00:00
         * updateTime : 2021-01-09 19:31:48
         * deleted : false
         */

        private int id;
        private String keyword;
        private String url;
        private boolean isHot;
        private boolean isDefault;
        private int sortOrder;
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GTM+8")
        private Date addTime;
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GTM+8")
        private Date updateTime;
        private boolean deleted;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class HistoryKeywordListBean {
        /**
         * keyword : 正义
         */

        private String keyword;


    }
}
