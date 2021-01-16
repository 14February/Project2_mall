package com.cskaoyan.bean.wx.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zyhstart
 * @description
 * @create 2021-01-12 19:45
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderCommentBo {
    String content;
    Boolean hasPicture;
    Integer orderGoodsId;
    String[] picUrls;
    Short star;


}
