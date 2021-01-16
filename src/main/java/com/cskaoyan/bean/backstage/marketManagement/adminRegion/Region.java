package com.cskaoyan.bean.backstage.marketManagement.adminRegion;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Region {
    private Integer id;

    private Integer pid;

    private String name;

    private Byte type;

    private Integer code;

}