package com.api.entity.sys_permission;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * <p>
 * 权限表
 * </p>
 *
 * @author hjs
 * @since 2020-10-16
 */
@TableName("sys_permission")
@ApiModel(value = "权限表")
public class SysPermission implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 权限描述
     */
    @ApiModelProperty(value = "权限描述", position = 1)
    private String description;

    /**
     * 接口url
     */
    @ApiModelProperty(value = "接口url", position = 1)
    private String url;

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
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "SysPermission{" +
        "id=" + id +
        ", description=" + description +
        ", url=" + url +
        "}";
    }
}
