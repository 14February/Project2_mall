package com.cskaoyan.bean.wx.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author zyhstart
 * @description
 * @create 2021-01-12 12:56
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderListVo {
    private List<OrderListData> data;
    private int count;
    private int totalPages;
}
