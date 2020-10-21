package com.api.entity.sys_user_role;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * <p>
 * 用户角色表
 * </p>
 *
 * @author hjs
 * @since 2020-10-16
 */
@TableName("sys_user_role")
@ApiModel(value = "用户角色表")
public class SysUserRole implements Serializable {

    private static final long serialVersionUID = 1L;

    public SysUserRole() {

    }

    public SysUserRole(Long roleId, Long uid) {
        this.roleId = roleId;
        this.uid = uid;
    }

    /**
     * 角色id
     */
    @ApiModelProperty(value = "角色id", position = 1)
    private Long roleId;

    /**
     * 用户id
     */
    @ApiModelProperty(value = "用户id", position = 1)
    private Long uid;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    @Override
    public String toString() {
        return "SysUserRole{" +
                "roleId=" + roleId +
                ", uid=" + uid +
                "}";
    }
}
