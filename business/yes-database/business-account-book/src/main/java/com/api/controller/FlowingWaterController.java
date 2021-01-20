package com.api.controller;

import cn.hutool.core.util.IdUtil;
import com.api.aop.ExtPageHelper;
import com.api.entity.flowing_water.FlowingWater;
import com.api.service.impl.account.AccountServiceImpl;
import com.api.service.impl.flowing_water.FlowingWaterServiceImpl;
import com.api.service.impl.flowing_water_name.FlowingWaterNameServiceImpl;
import com.api.util.BaseApiService;
import com.api.util.HttpStatus;
import com.api.util.Notice;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.math.BigDecimal;

@Api(tags = "流水接口")
@RestController
@RequestMapping("/flowingWaterController")
public class FlowingWaterController extends BaseApiService {
    @Resource
    FlowingWaterServiceImpl flowingWaterServiceImpl;
    @Resource
    FlowingWaterNameServiceImpl flowingWaterNameServiceImpl;
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
        queryWrapper.orderByDesc("creation_time");
        return new Notice(HttpStatus.OK, flowingWaterServiceImpl.list(queryWrapper), "成功");
    }

    @PostMapping("/insFlowingWater")
    @ApiOperation(value = "创建流水", notes = "创建流水", response = FlowingWater.class)
    public Notice insAccount(@RequestBody FlowingWater flowingWater) {
        flowingWater.setId(IdUtil.simpleUUID());
        flowingWater.setUserId(getUserId());
        flowingWater.setAmount(flowingWater.getAmount().multiply(new BigDecimal(100)));
        if (flowingWaterServiceImpl.save(flowingWater)) {
            if (flowingWater.getOperationType() == 1) {//钱包扣款
                accountServiceImpl.incomeAndExpenditure(flowingWater.getDeductionAccountId(), flowingWater.getOperationType(), 1, flowingWater.getAmount());
            } else if (flowingWater.getOperationType() == 2) {//钱包收款
                accountServiceImpl.incomeAndExpenditure(flowingWater.getCollectionAccountId(), flowingWater.getOperationType(), 1, flowingWater.getAmount());
            } else {//钱包转账
                accountServiceImpl.transferAccounts(flowingWater.getDeductionAccountId(), flowingWater.getCollectionAccountId(), 1, flowingWater.getAmount());
            }
            flowingWaterNameServiceImpl.selName(flowingWater.getName(), getUserId());
            return new Notice(HttpStatus.OK, "成功");
        }
        return new Notice(HttpStatus.INTERNAL_SERVER_ERROR, "失败");
    }


    @PostMapping("/updFlowingWater")
    @ApiOperation(value = "修改流水", notes = "修改流水", response = FlowingWater.class)
    public Notice updAccount(@RequestBody FlowingWater flowingWater) {
        flowingWater.setAmount(flowingWater.getAmount().multiply(new BigDecimal(100)));
        FlowingWater flowingWaterSon = flowingWaterServiceImpl.getById(flowingWater.getId());
        if (flowingWaterSon.getOperationType() == 1) {
            accountServiceImpl.incomeAndExpenditure(flowingWaterSon.getDeductionAccountId(), flowingWaterSon.getOperationType(), 2, flowingWaterSon.getAmount());
            accountServiceImpl.incomeAndExpenditure(flowingWater.getDeductionAccountId(), flowingWater.getOperationType(), 1, flowingWater.getAmount());
        } else if (flowingWaterSon.getOperationType() == 2) {
            accountServiceImpl.incomeAndExpenditure(flowingWaterSon.getCollectionAccountId(), flowingWaterSon.getOperationType(), 2, flowingWaterSon.getAmount());
            accountServiceImpl.incomeAndExpenditure(flowingWater.getCollectionAccountId(), flowingWater.getOperationType(), 1, flowingWater.getAmount());
        } else if (flowingWaterSon.getOperationType() == 3) {
            accountServiceImpl.transferAccounts(flowingWater.getDeductionAccountId(), flowingWater.getCollectionAccountId(), 2, flowingWater.getAmount());
            accountServiceImpl.transferAccounts(flowingWater.getDeductionAccountId(), flowingWater.getCollectionAccountId(), 1, flowingWater.getAmount());
        }
        if (flowingWaterServiceImpl.updateById(flowingWater)) {
            flowingWaterNameServiceImpl.selName(flowingWater.getName(), getUserId());
            return new Notice(HttpStatus.OK, "成功");
        }
        return new Notice(HttpStatus.INTERNAL_SERVER_ERROR, "失败");
    }

    @GetMapping("/delFlowingWater")
    @ApiOperation(value = "删除流水", notes = "删除流水")
    public Notice delFlowingWater(@RequestParam("id") String id) {
        FlowingWater flowingWaterSon = flowingWaterServiceImpl.getById(id);
        if (flowingWaterServiceImpl.removeById(id)) {
            if (flowingWaterSon.getOperationType() == 1) {
                accountServiceImpl.incomeAndExpenditure(flowingWaterSon.getDeductionAccountId(), flowingWaterSon.getOperationType(), 2, flowingWaterSon.getAmount());
            } else if (flowingWaterSon.getOperationType() == 2) {
                accountServiceImpl.incomeAndExpenditure(flowingWaterSon.getCollectionAccountId(), flowingWaterSon.getOperationType(), 2, flowingWaterSon.getAmount());
            } else if (flowingWaterSon.getOperationType() == 3) {
                accountServiceImpl.transferAccounts(flowingWaterSon.getCollectionAccountId(), flowingWaterSon.getDeductionAccountId(), 2, flowingWaterSon.getAmount());
                accountServiceImpl.transferAccounts(flowingWaterSon.getDeductionAccountId(), flowingWaterSon.getCollectionAccountId(), 1, flowingWaterSon.getAmount());
            }
            return new Notice(HttpStatus.OK, "成功");
        }
        return new Notice(HttpStatus.INTERNAL_SERVER_ERROR, "失败");
    }

    @GetMapping("/getFlowingWater")
    @ApiOperation(value = "获取流水", notes = "获取流水")
    public Notice getFlowingWater(@RequestParam("id") String id) {
        FlowingWater flowingWater = flowingWaterServiceImpl.getById(id);
        flowingWater.setAmount(flowingWater.getAmount().divide(new BigDecimal(100)));
        return new Notice(HttpStatus.OK, flowingWater, "成功");
    }
}
