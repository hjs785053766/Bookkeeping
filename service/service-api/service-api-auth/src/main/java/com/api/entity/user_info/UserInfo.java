package com.api.entity.user_info;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author hjs
 * @since 2020-10-16
 */
@TableName("user_info")
@ApiModel(value = "用户表")
public class UserInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名", position = 1)
    private String name;

    /**
     * 密码
     */
    @ApiModelProperty(value = "密码", position = 1)
    private String password;

    /**
     * 帐号
     */
    @ApiModelProperty(value = "帐号", position = 1)
    private String username;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
        "id=" + id +
        ", name=" + name +
        ", password=" + password +
        ", username=" + username +
        "}";
    }
}
