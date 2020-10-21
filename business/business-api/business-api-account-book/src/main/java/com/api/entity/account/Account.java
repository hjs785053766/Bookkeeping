package com.api.entity.account;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 账户表
 * </p>
 *
 * @author hjs
 * @since 2020-10-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("account")
@ApiModel(value="Account对象", description="账户表")
public class Account implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    @ApiModelProperty(value = "账户名")
    private String accountName;

    @ApiModelProperty(value = "账户收入")
    private BigDecimal accountIncome;

    @ApiModelProperty(value = "账户消费")
    private BigDecimal accountConsumption;

    @ApiModelProperty(value = "账户余额")
    private BigDecimal accountBalance;

    @ApiModelProperty(value = "用户id")
    private String userId;

    @ApiModelProperty(value = "创建时间")
    private Date creationTime;


}
