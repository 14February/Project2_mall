package com.cskaoyan.bean.backstage.role;

import com.cskaoyan.bean.backstage.permission.PermVo;
import lombok.Data;

import java.util.List;

/**
 *
 */
@Data
public class RolePermVo{
    List<PermVo> systemPermissions;
    List<String> assignedPermissions;
}
