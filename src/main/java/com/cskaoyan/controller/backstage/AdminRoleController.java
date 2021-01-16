package com.cskaoyan.controller.backstage;

import com.cskaoyan.bean.both.BaseRespVo;
import com.cskaoyan.bean.backstage.ListData;
import com.cskaoyan.bean.backstage.permission.PermVo;
import com.cskaoyan.bean.backstage.role.Role;
import com.cskaoyan.bean.backstage.role.RolePermVo;
import com.cskaoyan.bean.backstage.role.RoleVo;
import com.cskaoyan.map.OperationMap;
import com.cskaoyan.service.PermissionService;
import com.cskaoyan.service.RoleService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 */
@RequestMapping("admin/role")
@RestController
public class AdminRoleController{

    @Autowired
    RoleService roleService;

    @Autowired
    PermissionService permissionService;

    @Autowired
    OperationMap operationMap;

    @RequestMapping("options")
    public BaseRespVo<List<RoleVo>> options()
    {
        return BaseRespVo.ok(roleService.selectRoleList());
    }

    @RequestMapping("list")
    public BaseRespVo<ListData<Role>> list(Integer page,Integer limit,String sort,String order,String name)
    {
        ListData<Role> listData = roleService.queryRoleList(page,limit,sort,order,name);

        return BaseRespVo.ok(listData);
    }

    @RequestMapping("create")
    public BaseRespVo<Role> create(HttpServletRequest request,@RequestBody Role role)
    {
        Date date = new Date();
        role.setAddTime(date);
        role.setUpdateTime(date);
        try
        {
            roleService.createRole(role);
        }catch(Exception e)
        {
            operationMap.insertOperations(request,SecurityUtils.getSubject(), OperationMap.operations.get(5), false);
            return BaseRespVo.fail(640,"角色已经存在");
        }
        operationMap.insertOperations(request,SecurityUtils.getSubject(), OperationMap.operations.get(5), true);
        return BaseRespVo.ok(role);
    }

    @RequestMapping("update")
    public BaseRespVo<Object> update(HttpServletRequest request,@RequestBody Role role)
    {
        try
        {
            roleService.updateRole(role);
        }catch(Exception e)
        {
            operationMap.insertOperations(request,SecurityUtils.getSubject(), OperationMap.operations.get(7), false);
            return BaseRespVo.fail(502,"系统内部错误");
        }
        operationMap.insertOperations(request,SecurityUtils.getSubject(), OperationMap.operations.get(7), true);
        return BaseRespVo.ok();
    }

    @RequestMapping("delete")
    public BaseRespVo<Object> delete(HttpServletRequest request,@RequestBody Role role)
    {
        roleService.deleteRole(role);
        operationMap.insertOperations(request,SecurityUtils.getSubject(), OperationMap.operations.get(8), true);
        return BaseRespVo.ok();
    }

    @GetMapping("permissions")
    public BaseRespVo<RolePermVo> permissions(Integer roleId)
    {
        List<PermVo> systemPermissions = permissionService.queryAllPermissions();
        List<String> assignedPermissions = roleService.queryAssignedPermissions(roleId);
        RolePermVo rolePermVo = new RolePermVo();
        rolePermVo.setSystemPermissions(systemPermissions);
        rolePermVo.setAssignedPermissions(assignedPermissions);
        return BaseRespVo.ok(rolePermVo);
    }

    @PostMapping("permissions")
    public BaseRespVo<Object> permissions(HttpServletRequest request,@RequestBody Map<String,Object> map)
    {
        List<String> permissionList = (List<String>)map.get("permissions");
        int roleId = (int)map.get("roleId");
        roleService.updatePermissionsByRoleId(roleId,permissionList);
        operationMap.insertOperations(request,SecurityUtils.getSubject(), OperationMap.operations.get(6), true);
        return BaseRespVo.ok();
    }
}
