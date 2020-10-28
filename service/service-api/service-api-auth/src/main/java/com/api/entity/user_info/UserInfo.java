package com.api.entity.user_info;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

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
@Data
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

    @ApiModelProperty(value = "创建时间")
    private Date creationTime;

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
