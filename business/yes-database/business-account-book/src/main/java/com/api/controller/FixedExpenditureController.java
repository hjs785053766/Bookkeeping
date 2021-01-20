package com.api.controller;

import com.api.aop.ExtPageHelper;
import com.api.entity.fixed_expenditure.FixedExpenditure;
import com.api.entity.flowing_water.FlowingWater;
import com.api.service.impl.fixed_expenditure.FixedExpenditureServiceImpl;
import com.api.service.impl.flowing_water.FlowingWaterServiceImpl;
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
import java.util.Date;

@Api(tags = "固定支出接口")
@RestController
@RequestMapping("/fixedExpenditureController")
public class FixedExpenditureController extends BaseApiService {
    @Resource
    FixedExpenditureServiceImpl fixedExpenditureServiceImpl;
    @Resource
    FlowingWaterServiceImpl flowingWaterServiceImpl;

    @PostMapping("/insAndUpdFixedExpenditure")
    @ApiOperation(value = "创建/修改固定支出", notes = "创建/修改固定支出", response = FixedExpenditure.class)
    public Notice insAccount(@RequestBody FixedExpenditure fixedExpenditure) {
        boolean judge;
        if (fixedExpenditure.getId() == null) {
            fixedExpenditure.setUserId(getUserId());
            fixedExpenditure.setCreationTime(new Date());
            judge = true;
        } else {
            judge = false;
        }
        fixedExpenditure.setCost(fixedExpenditure.getCost().multiply(new BigDecimal(100)));
        if (judge) {
            if (fixedExpenditureServiceImpl.save(fixedExpenditure)) {
                return new Notice(HttpStatus.OK, "成功");
            }
        } else {
            if (fixedExpenditureServiceImpl.updateById(fixedExpenditure)) {
                return new Notice(HttpStatus.OK, "成功");
            }
        }
        return new Notice(HttpStatus.INTERNAL_SERVER_ERROR, "失败");
    }

    @GetMapping("/delFixedExpenditure")
    @ApiOperation(value = "删除固定支出", notes = "删除固定支出")
    public Notice delFixedExpenditure(@RequestParam("id") String id) {
        QueryWrapper<FlowingWater> queryWrapper = new QueryWrapper<FlowingWater>();
        queryWrapper.eq("fixed_expenditure_id", id);
        if (flowingWaterServiceImpl.list(queryWrapper).size() > 0) {
            queryWrapper = new QueryWrapper<FlowingWater>();
            queryWrapper.eq("fixed_expenditure_id", id);
            FlowingWater flowingWater = new FlowingWater();
            flowingWater.setFixedExpenditureId(null);
            flowingWaterServiceImpl.update(flowingWater, queryWrapper);
        }
        if (fixedExpenditureServiceImpl.removeById(id)) {
            return new Notice(HttpStatus.OK, "成功");
        }
        return new Notice(HttpStatus.INTERNAL_SERVER_ERROR, "失败");
    }

    @GetMapping("/selFixedExpenditureList")
    @ApiOperation(value = "查询固定支出列表", notes = "查询固定支出列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageSize", value = "分页大小", required = true, dataType = "int"),
            @ApiImplicitParam(name = "pageNum", value = "分页开始索引", required = true, dataType = "int"),
            @ApiImplicitParam(name = "name", value = "固定支出名称", dataType = "String")
    })
    @ExtPageHelper
    public Notice selFixedExpenditureList(@RequestParam("name") String name) {
        QueryWrapper<FixedExpenditure> queryWrapper = new QueryWrapper<FixedExpenditure>();
        queryWrapper.eq("user_id", getUserId());
        if (name != null && !name.equals("null")) {
            queryWrapper.like("name", name);
        }
        queryWrapper.orderByDesc("creation_time");
        return new Notice(HttpStatus.OK, fixedExpenditureServiceImpl.list(queryWrapper), "成功");
    }
}
