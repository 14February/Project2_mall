package com.cskaoyan.mapper.backstage;

import com.cskaoyan.bean.backstage.permission.*;
import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface PermissionMapper{
    long countByExample(PermissionExample example);

    int deleteByExample(PermissionExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Permission record);

    int insertSelective(Permission record);

    List<Permission> selectByExample(PermissionExample example);

    Permission selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Permission record, @Param("example") PermissionExample example);

    int updateByExample(@Param("record") Permission record, @Param("example") PermissionExample example);

    int updateByPrimaryKeySelective(Permission record);

    int updateByPrimaryKey(Permission record);

    List<String> selectPermissionByRoleId(Integer[] roleIds);

    List<PermVo> queryAllPermissions();

    List<Perm> selectFirstLevelPerms();

    List<Perm> selectSecondLevelPerms(int parentId);

    List<PermissionVo> selectApiList(int permId);

    List<Integer> selectPermissionIds(List<String> permissionList);
}