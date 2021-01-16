package com.cskaoyan.bean.wx.cart;

import lombok.Data;

@Data
public class CheckedAddressVo {
    private int id;
    private String name;
    private int userId;
    private int provinceId;
    private int cityId;
    private int areaId;
    private String address;
    private String mobile;
    private boolean isDefault;
    private String addTime;
    private String updateTime;
    private boolean deleted;
}
