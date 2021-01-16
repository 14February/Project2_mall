package com.cskaoyan.bean.backstage;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ConfigOrder {

    /**
     * cskaoyanmall_order_unconfirm : 7
     * cskaoyanmall_order_unpaid : 30
     * cskaoyanmall_order_comment : 7
     */

    private String cskaoyanmall_order_unconfirm;
    private String cskaoyanmall_order_unpaid;
    private String cskaoyanmall_order_comment;

    public String getCskaoyanmall_order_unconfirm() {
        return cskaoyanmall_order_unconfirm;
    }

    public void setCskaoyanmall_order_unconfirm(String cskaoyanmall_order_unconfirm) {
        this.cskaoyanmall_order_unconfirm = cskaoyanmall_order_unconfirm;
    }

    public String getCskaoyanmall_order_unpaid() {
        return cskaoyanmall_order_unpaid;
    }

    public void setCskaoyanmall_order_unpaid(String cskaoyanmall_order_unpaid) {
        this.cskaoyanmall_order_unpaid = cskaoyanmall_order_unpaid;
    }

    public String getCskaoyanmall_order_comment() {
        return cskaoyanmall_order_comment;
    }

    public void setCskaoyanmall_order_comment(String cskaoyanmall_order_comment) {
        this.cskaoyanmall_order_comment = cskaoyanmall_order_comment;
    }
}
