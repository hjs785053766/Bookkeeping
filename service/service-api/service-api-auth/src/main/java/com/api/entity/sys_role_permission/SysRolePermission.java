package com.api.entity.sys_role_permission;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * <p>
 * 角色权限表
 * </p>
 *
 * @author hjs
 * @since 2020-10-16
 */
@TableName("sys_role_permission")
@ApiModel(value = "角色权限表")
public class SysRolePermission implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 角色id
     */
    @ApiModelProperty(value = "角色id", position = 1)
    private Long roleId;

    /**
     * 权限id
     */
    @ApiModelProperty(value = "权限id", position = 1)
    private Long permissionId;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
    public Long getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Long permissionId) {
        this.permissionId = permissionId;
    }

    @Override
    public String toString() {
        return "SysRolePermission{" +
        "roleId=" + roleId +
        ", permissionId=" + permissionId +
        "}";
    }
}
