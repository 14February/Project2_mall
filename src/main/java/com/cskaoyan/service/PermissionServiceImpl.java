package com.cskaoyan.service;

import com.cskaoyan.bean.backstage.permission.PermVo;
import com.cskaoyan.mapper.backstage.PermissionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 */
@Service
public class PermissionServiceImpl implements PermissionService{

    @Autowired
    PermissionMapper permissionMapper;

    @Override
    public List<PermVo> queryAllPermissions()
    {
        return permissionMapper.queryAllPermissions();
    }
}
