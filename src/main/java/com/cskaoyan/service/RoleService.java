package com.cskaoyan.service;

import com.cskaoyan.bean.backstage.ListData;
import com.cskaoyan.bean.backstage.role.Role;
import com.cskaoyan.bean.backstage.role.RoleVo;

import java.util.List;

/**
 *
 */
public interface RoleService{
    List<RoleVo> selectRoleList();

    ListData<Role> queryRoleList(Integer page,Integer limit,String sort,String order,String name);

    void createRole(Role role);

    void updateRole(Role role);

    void deleteRole(Role role);

    List<String> queryAssignedPermissions(Integer roleId);

    void updatePermissionsByRoleId(int roleId,List<String> permissionList);
}
