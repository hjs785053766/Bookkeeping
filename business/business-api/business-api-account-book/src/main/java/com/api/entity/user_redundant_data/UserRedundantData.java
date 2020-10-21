package com.api.entity.user_redundant_data;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户冗余数据
 * </p>
 *
 * @author hjs
 * @since 2020-10-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("user_redundant_data")
@ApiModel(value="UserRedundantData对象", description="用户冗余数据")
public class UserRedundantData implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    @ApiModelProperty(value = "用户id")
    private String userId;

    @ApiModelProperty(value = "固定预计金额")
    private BigDecimal fixedEstimatedAmount;

    @ApiModelProperty(value = "固定预计实用")
    private BigDecimal fixedForecastPractical;

    @ApiModelProperty(value = "固定实际实用")
    private BigDecimal fixedPractical;

    @ApiModelProperty(value = "固定剩余")
    private BigDecimal fixedSurplus;

    @ApiModelProperty(value = "流水支出")
    private BigDecimal flowingExpenditure;

    @ApiModelProperty(value = "流水收入")
    private BigDecimal flowingIncome;

    @ApiModelProperty(value = "流水转账")
    private BigDecimal flowingTransferAccounts;

    @ApiModelProperty(value = "流水共计")
    private BigDecimal flowingTotal;


}
