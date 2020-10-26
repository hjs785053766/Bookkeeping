package com.api.controller;

import cn.hutool.core.util.IdUtil;
import com.api.aop.ExtPageHelper;
import com.api.entity.flowing_water.FlowingWater;
import com.api.service.impl.account.AccountServiceImpl;
import com.api.service.impl.flowing_water.FlowingWaterServiceImpl;
import com.api.util.BaseApiService;
import com.api.util.Notice;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;

@Api(tags = "流水接口")
@RestController
@RequestMapping("/flowingWaterController")
public class FlowingWaterController extends BaseApiService {
    @Resource
    FlowingWaterServiceImpl flowingWaterServiceImpl;
    @Resource
    AccountServiceImpl accountServiceImpl;

    @GetMapping("/selFlowingWaterList")
    @ApiOperation(value = "查询流水列表", notes = "查询流水列表", response = FlowingWater.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageSize", value = "分页大小", required = true, dataType = "int"),
            @ApiImplicitParam(name = "pageNum", value = "分页开始索引", required = true, dataType = "int"),
            @ApiImplicitParam(name = "name", value = "流水名称", dataType = "String")
    })
    @ExtPageHelper
    public Notice selFlowingWaterList(@RequestParam("name") String name) {
        QueryWrapper<FlowingWater> queryWrapper = new QueryWrapper<FlowingWater>();
        queryWrapper.eq("user_id", getUserId());
        if (name != null && !name.equals("null")) {
            queryWrapper.like("name", name);
        }
        return new Notice(HttpStatus.OK, flowingWaterServiceImpl.list(queryWrapper), "成功");
    }

    @PostMapping("/insFlowingWater")
    @ApiOperation(value = "创建流水", notes = "创建流水", response = FlowingWater.class)
    public Notice insAccount(@RequestBody FlowingWater flowingWater) {
        flowingWater.setId(IdUtil.simpleUUID());
        flowingWater.setUserId(getUserId());
        if (flowingWaterServiceImpl.save(flowingWater)) {
            if (flowingWater.getOperationType() != 3) {
                accountServiceImpl.IncomeAndExpenditure(flowingWater.getDeductionAccountId(), flowingWater.getOperationType(), 1, flowingWater.getAmount());
            } else {
                accountServiceImpl.transferAccounts(flowingWater.getDeductionAccountId(), flowingWater.getCollectionAccountId(), 1, flowingWater.getAmount());
            }
            return new Notice(HttpStatus.OK, "成功");
        }
        return new Notice(HttpStatus.INTERNAL_SERVER_ERROR, "失败");
    }


    @PutMapping("/updFlowingWater")
    @ApiOperation(value = "修改流水", notes = "修改流水", response = FlowingWater.class)
    public Notice updAccount(@RequestBody FlowingWater flowingWater) {
        FlowingWater flowingWaterSon = flowingWaterServiceImpl.getById(flowingWater.getId());
        if (flowingWaterSon.getOperationType() != 3) {
            accountServiceImpl.IncomeAndExpenditure(flowingWaterSon.getDeductionAccountId(), flowingWaterSon.getOperationType(), 2, flowingWaterSon.getAmount());
            accountServiceImpl.IncomeAndExpenditure(flowingWater.getDeductionAccountId(), flowingWater.getOperationType(), 1, flowingWater.getAmount());
        } else {
            accountServiceImpl.transferAccounts(flowingWater.getDeductionAccountId(), flowingWater.getCollectionAccountId(), 2, flowingWater.getAmount());
            accountServiceImpl.transferAccounts(flowingWater.getDeductionAccountId(), flowingWater.getCollectionAccountId(), 1, flowingWater.getAmount());
        }
        if (flowingWaterServiceImpl.updateById(flowingWater)) {
            return new Notice(HttpStatus.OK, "成功");
        }
        return new Notice(HttpStatus.INTERNAL_SERVER_ERROR, "失败");
    }

    @DeleteMapping("/delFlowingWater")
    @ApiOperation(value = "删除流水", notes = "删除流水")
    public Notice delFlowingWater(@RequestParam("id") String id) {
        FlowingWater flowingWaterSon = flowingWaterServiceImpl.getById(id);
        if (flowingWaterServiceImpl.removeById(id)) {
            if (flowingWaterSon.getOperationType() != 3) {
                accountServiceImpl.IncomeAndExpenditure(flowingWaterSon.getDeductionAccountId(), flowingWaterSon.getOperationType(), 2, flowingWaterSon.getAmount());
            }
            return new Notice(HttpStatus.OK, "成功");
        }
        return new Notice(HttpStatus.INTERNAL_SERVER_ERROR, "失败");
    }
}
