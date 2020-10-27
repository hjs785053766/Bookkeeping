package com.api.entity.flowing_water;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class FlowingWaterSon extends FlowingWater {
    @ApiModelProperty(value = "操作类型转义")
    String operationTypeStr;

    public String getOperationTypeStr() {
        if (getOperationType() != null) {
            switch (getOperationType()) {
                case 1:
                    return "支出";
                case 2:
                    return "收入";
                case 3:
                    return "转账";
            }
        }
        return null;
    }
}
