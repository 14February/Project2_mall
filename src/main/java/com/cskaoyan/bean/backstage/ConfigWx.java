package com.cskaoyan.bean.backstage;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;

@AllArgsConstructor
@NoArgsConstructor
public class ConfigWx {
        private String cskaoyanmall_wx_share;
        private String cskaoyanmall_wx_index_brand;
        private String cskaoyanmall_wx_index_topic;
        private String cskaoyanmall_wx_index_hot;
        private String cskaoyanmall_wx_catlog_goods;
        private String cskaoyanmall_wx_catlog_list;
        private String cskaoyanmall_wx_index_new;

        public String getCskaoyanmall_wx_share() {
            return cskaoyanmall_wx_share;
        }

        public void setCskaoyanmall_wx_share(String cskaoyanmall_wx_share) {
            this.cskaoyanmall_wx_share = cskaoyanmall_wx_share;
        }

        public String getCskaoyanmall_wx_index_brand() {
            return cskaoyanmall_wx_index_brand;
        }

        public void setCskaoyanmall_wx_index_brand(String cskaoyanmall_wx_index_brand) {
            this.cskaoyanmall_wx_index_brand = cskaoyanmall_wx_index_brand;
        }

        public String getCskaoyanmall_wx_index_topic() {
            return cskaoyanmall_wx_index_topic;
        }

        public void setCskaoyanmall_wx_index_topic(String cskaoyanmall_wx_index_topic) {
            this.cskaoyanmall_wx_index_topic = cskaoyanmall_wx_index_topic;
        }

        public String getCskaoyanmall_wx_index_hot() {
            return cskaoyanmall_wx_index_hot;
        }

        public void setCskaoyanmall_wx_index_hot(String cskaoyanmall_wx_index_hot) {
            this.cskaoyanmall_wx_index_hot = cskaoyanmall_wx_index_hot;
        }

        public String getCskaoyanmall_wx_catlog_goods() {
            return cskaoyanmall_wx_catlog_goods;
        }

        public void setCskaoyanmall_wx_catlog_goods(String cskaoyanmall_wx_catlog_goods) {
            this.cskaoyanmall_wx_catlog_goods = cskaoyanmall_wx_catlog_goods;
        }

        public String getCskaoyanmall_wx_catlog_list() {
            return cskaoyanmall_wx_catlog_list;
        }

        public void setCskaoyanmall_wx_catlog_list(String cskaoyanmall_wx_catlog_list) {
            this.cskaoyanmall_wx_catlog_list = cskaoyanmall_wx_catlog_list;
        }

        public String getCskaoyanmall_wx_index_new() {
            return cskaoyanmall_wx_index_new;
        }

        public void setCskaoyanmall_wx_index_new(String cskaoyanmall_wx_index_new) {
            this.cskaoyanmall_wx_index_new = cskaoyanmall_wx_index_new;
        }

}
