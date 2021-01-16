package com.cskaoyan.service;

import com.cskaoyan.bean.backstage.ListData;
import com.cskaoyan.bean.backstage.role.Role;
import com.cskaoyan.bean.backstage.role.RoleExample;
import com.cskaoyan.bean.backstage.role.RolePermissionRelationExample;
import com.cskaoyan.bean.backstage.role.RoleVo;
import com.cskaoyan.mapper.backstage.PermissionMapper;
import com.cskaoyan.mapper.backstage.RoleMapper;
import com.cskaoyan.mapper.backstage.RolePermissionRelationMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 */
@Service
public class RoleServiceImpl implements RoleService{

    @Autowired
    RoleMapper roleMapper;

    @Autowired
    RolePermissionRelationMapper rolePermissionRelationMapper;

    @Autowired
    PermissionMapper permissionMapper;

    @Override
    public void updatePermissionsByRoleId(int roleId,List<String> permissionList)
    {
        List<Integer> permissionIds = permissionMapper.selectPermissionIds(permissionList);
        System.out.println(permissionIds);
        RolePermissionRelationExample rolePermissionRelationExample = new RolePermissionRelationExample();
        rolePermissionRelationExample.createCriteria().andRoleIdEqualTo(roleId);
        rolePermissionRelationMapper.deleteByExample(rolePermissionRelationExample);
        roleMapper.insertPermissionsAndRoleId(roleId,permissionIds);
    }

    @Override
    public List<String> queryAssignedPermissions(Integer roleId)
    {
        return roleMapper.selectAssignedPermissionsByRoleId(roleId);
    }

    @Override
    public void deleteRole(Role role)
    {
        roleMapper.deleteByPrimaryKey(role.getId());
    }

    @Override
    public void updateRole(Role role)
    {
        roleMapper.updateByPrimaryKeySelective(role);
    }

    @Override
    public void createRole(Role role)
    {
        roleMapper.insertSelective(role);
    }

    @Override
    public ListData<Role> queryRoleList(Integer page,Integer limit,String sort,String order,String name)
    {
        PageHelper.startPage(page, limit);
        RoleExample example = new RoleExample();
        example.setOrderByClause(sort + " " + order);

        RoleExample.Criteria criteria = example.createCriteria();
        if(name != null) criteria.andNameLike("%" + name + "%");

        List<Role> roles = roleMapper.selectByExample(example);
        PageInfo<Role> userPageInfo = new PageInfo<>(roles);
        int total = (int)userPageInfo.getTotal();   //符合条件的总记录数
        return ListData.data(total, roles);
    }

    @Override
    public List<RoleVo> selectRoleList()
    {
        return roleMapper.selectRoles();
    }
}
