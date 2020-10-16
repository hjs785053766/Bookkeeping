package com.api.entity.sys_role;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * <p>
 * 角色表
 * </p>
 *
 * @author hjs
 * @since 2020-10-16
 */
@TableName("sys_role")
@ApiModel(value = "角色表")
public class SysRole implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * 角色描述
     */
    @ApiModelProperty(value = "角色描述", position = 1)
    private String description;

    /**
     * 角色名称
     */
    @ApiModelProperty(value = "角色名称", position = 2)
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "SysRole{" +
        "id=" + id +
        ", description=" + description +
        ", name=" + name +
        "}";
    }
}
