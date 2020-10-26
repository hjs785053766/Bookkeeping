package com.api.entity.flowing_water;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * <p>
 * 流水表
 * </p>
 *
 * @author hjs
 * @since 2020-10-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("flowing_water")
@ApiModel(value="FlowingWater对象", description="流水表")
public class FlowingWater implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "操作类型（1、支出，2、收入，3、转账）")
    private Integer operationType;

    @ApiModelProperty(value = "扣款账户id")
    private String deductionAccountId;

    @ApiModelProperty(value = "扣款账户名")
    private String deductionAccountName;

    @ApiModelProperty(value = "收款账户id（转账类型不为空）")
    private String collectionAccountId;

    @ApiModelProperty(value = "收款账户id（转账类型不为空）")
    private String collectionAccountName;

    @ApiModelProperty(value = "金额")
    private BigDecimal amount;

    @ApiModelProperty(value = "记录时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss", timezone = "GMT+8")
    private Date creationTime;

    @ApiModelProperty(value = "操作人id")
    private String userId;


}
