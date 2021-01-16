package com.cskaoyan.bean.backstage.permission;

import lombok.Data;

import java.util.List;

/**
 *
 */
@Data
public class PermVo{
    private String id;
    private String label;
    private List<Object> children;
}
