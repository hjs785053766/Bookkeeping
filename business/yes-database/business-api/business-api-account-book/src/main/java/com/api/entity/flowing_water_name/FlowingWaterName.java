package com.api.entity.flowing_water_name;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 快捷流水名称
 * </p>
 *
 * @author hjs
 * @since 2020-10-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("flowing_water_name")
@ApiModel(value="FlowingWaterName对象", description="快捷流水名称")
public class FlowingWaterName implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    private String id;

    @ApiModelProperty(value = "值")
    private String value;

    @ApiModelProperty(value = "用户id")
    private String userId;


}
