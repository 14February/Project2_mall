package com.cskaoyan.bean.backstage.permission;

import lombok.Data;

@Data
public class Permission {
    private Integer id;

    private String api;

    private Integer permId;

    private String label;

    private String alias;
}