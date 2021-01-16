package com.cskaoyan.bean.wx.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zyhstart
 * @description
 * @create 2021-01-12 13:00
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HandleOptionVo {

    private boolean cancel;
    private boolean delete;
    private boolean pay;
    private boolean comment;
    private boolean confirm;
    private boolean refund;
    private boolean rebuy;

}
