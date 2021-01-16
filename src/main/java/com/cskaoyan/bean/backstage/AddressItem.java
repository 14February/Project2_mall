package com.cskaoyan.bean.backstage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zyhstart
 * @description
 * 用户管理的收货地址
 * @create 2021-01-09 15:46
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressItem {
    private String area;
    private boolean isDefault;
    private int areaId;
    private String address;
    private String province;
    private String city;
    private String name;
    private String mobile;
    private int id;
    private int cityId;
    private int userId;
    private int provinceId;


}
