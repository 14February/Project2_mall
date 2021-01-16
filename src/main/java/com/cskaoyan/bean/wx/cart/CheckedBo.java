package com.cskaoyan.bean.wx.cart;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CheckedBo {
    List<Integer> productIds;
    Integer isChecked;
}
