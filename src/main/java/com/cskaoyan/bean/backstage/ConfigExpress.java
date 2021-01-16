package com.cskaoyan.bean.backstage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ConfigExpress {

    /**
     * cskaoyanmall_express_freight_value : 9
     * cskaoyanmall_express_freight_min : 88
     */

    private String cskaoyanmall_express_freight_value;
    private String cskaoyanmall_express_freight_min;

    public String getCskaoyanmall_express_freight_value() {
        return cskaoyanmall_express_freight_value;
    }

    public void setCskaoyanmall_express_freight_value(String cskaoyanmall_express_freight_value) {
        this.cskaoyanmall_express_freight_value = cskaoyanmall_express_freight_value;
    }

    public String getCskaoyanmall_express_freight_min() {
        return cskaoyanmall_express_freight_min;
    }

    public void setCskaoyanmall_express_freight_min(String cskaoyanmall_express_freight_min) {
        this.cskaoyanmall_express_freight_min = cskaoyanmall_express_freight_min;
    }
}
