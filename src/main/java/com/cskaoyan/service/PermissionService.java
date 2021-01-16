package com.cskaoyan.service;

import com.cskaoyan.bean.backstage.permission.PermVo;

import java.util.List;

/**
 *
 */
public interface PermissionService{
    List<PermVo> queryAllPermissions();
}
