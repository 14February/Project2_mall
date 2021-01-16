package com.cskaoyan.mapper.backstage;

import com.cskaoyan.bean.backstage.role.Role;
import com.cskaoyan.bean.backstage.role.RoleExample;
import com.cskaoyan.bean.backstage.role.RoleVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMapper{
    long countByExample(RoleExample example);

    int deleteByExample(RoleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Role record);

    int insertSelective(Role record);

    List<Role> selectByExample(RoleExample example);

    Role selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Role record, @Param("example") RoleExample example);

    int updateByExample(@Param("record") Role record, @Param("example") RoleExample example);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    List<RoleVo> selectRoles();

    List<String> selectRoleNameById(Integer[] roleIds);

    List<String> selectAssignedPermissionsByRoleId(@Param("roleId") Integer roleId);


    List<Integer> selectExistPermissionIdsByRoleId(@Param("roleId") int roleId);

    void insertPermissionsAndRoleId(@Param("roleId") int roleId,@Param("permissionIds") List<Integer> permissionIds);
}