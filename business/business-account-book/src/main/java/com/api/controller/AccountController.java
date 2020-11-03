package com.api.controller;

import cn.hutool.core.util.IdUtil;
import com.api.aop.ExtPageHelper;
import com.api.entity.account.Account;
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
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Api(tags = "账户接口")
@RestController
@RequestMapping("/accountController")
public class AccountController extends BaseApiService {
    @Resource
    AccountServiceImpl accountServiceImpl;
    @Resource
    FlowingWaterServiceImpl flowingWaterServiceImpl;

    @PostMapping("/insAccount")
    @ApiOperation(value = "创建账户", notes = "创建账户", response = Account.class)
    public Notice insAccount(@RequestBody Account account) {
        account.setId(IdUtil.simpleUUID());
        account.setUserId(getUserId());
        account.setCreationTime(new Date());
        if (accountServiceImpl.save(account)) {
            return new Notice(HttpStatus.OK, "成功");
        }
        return new Notice(HttpStatus.INTERNAL_SERVER_ERROR, "失败");
    }

    @GetMapping("/selAccountList")
    @ApiOperation(value = "查询账户列表", notes = "查询账户列表", response = Account.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageSize", value = "分页大小", required = true, dataType = "int"),
            @ApiImplicitParam(name = "pageNum", value = "分页开始索引", required = true, dataType = "int"),
            @ApiImplicitParam(name = "accountName", value = "账户名", dataType = "String")
    })
    @ExtPageHelper
    public Notice selAccountList(@RequestParam("accountName") String accountName) {
        QueryWrapper<Account> queryWrapper = new QueryWrapper<Account>();
        queryWrapper.eq("user_id", getUserId());
        if (accountName != null && !accountName.equals("null")) {
            queryWrapper.like("account_name", accountName);
        }
        return new Notice(HttpStatus.OK, accountServiceImpl.list(queryWrapper), "成功");
    }

    @PostMapping("/updAccount")
    @ApiOperation(value = "修改账户", notes = "修改账户", response = Account.class)
    public Notice updAccount(@RequestBody Account account) {
        if (accountServiceImpl.updateById(account)) {
            return new Notice(HttpStatus.OK, "成功");
        }
        return new Notice(HttpStatus.INTERNAL_SERVER_ERROR, "失败");
    }

    @DeleteMapping("/delAccount")
    @ApiOperation(value = "删除账户", notes = "删除账户")
    public Notice delAccount(@RequestParam("id") String id) {
        QueryWrapper<FlowingWater> queryWrapper = new QueryWrapper<FlowingWater>();
        queryWrapper.eq("deduction_account_id", id);
        if (flowingWaterServiceImpl.list(queryWrapper).size() > 0) {
            Map<String, Object> map = new HashMap<>();
            map.put("deduction_account_id", id);
            flowingWaterServiceImpl.removeByMap(map);
        }
        if (accountServiceImpl.removeById(id)) {
            return new Notice(HttpStatus.OK, "成功");
        }
        return new Notice(HttpStatus.INTERNAL_SERVER_ERROR, "失败");
    }
}
