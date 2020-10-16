package com.api.entity.sys_role;


import com.api.entity.sys_permission.SysPermission;
import lombok.Data;

import java.util.List;

@Data
public class SysRoleTwo extends SysRole {
    List<SysPermission> sysPermissionList;
}