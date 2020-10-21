package com.api.entity.fixed_expenditure;

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
 * 固定支出
 * </p>
 *
 * @author hjs
 * @since 2020-10-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("fixed_expenditure")
@ApiModel(value="FixedExpenditure对象", description="固定支出")
public class FixedExpenditure implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "费用（元）")
    private BigDecimal cost;

    @ApiModelProperty(value = "计费时间")
    private Integer billingTime;

    @ApiModelProperty(value = "单位")
    private String company;

    @ApiModelProperty(value = "共计（元）")
    private BigDecimal total;

    @ApiModelProperty(value = "实用（元）")
    private BigDecimal practical;

    @ApiModelProperty(value = "用户id")
    private String userId;

    @ApiModelProperty(value = "创建时间")
    private Date creationTime;


}
