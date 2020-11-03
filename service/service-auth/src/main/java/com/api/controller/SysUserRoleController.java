package com.api.controller;

import com.api.aop.ExtPageHelper;
import com.api.entity.sys_user_role.SysUserRole;
import com.api.service.impl.sys_user_role.SysUserRoleServiceImpl;
import com.api.util.Notice;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Api(tags = "用户角色接口")
@RestController
@RequestMapping("/sysUserRole")
public class SysUserRoleController {
    @Resource
    SysUserRoleServiceImpl sysUserRoleServiceImpl;

    @PostMapping("/insSysUserRole")
    @ApiOperation(value = "创建用户角色", notes = "创建用户角色", response = SysUserRole.class)
    public Notice insSysUserRole(@RequestBody SysUserRole sysUserRole) {
        if (sysUserRoleServiceImpl.save(sysUserRole)) {
            return new Notice(HttpStatus.OK, "成功");
        }
        return new Notice(HttpStatus.INTERNAL_SERVER_ERROR, "失败");
    }

    @GetMapping("/selSysUserRoleList")
    @ApiOperation(value = "查询用户角色列表", notes = "查询用户角色列表", response = SysUserRole.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageSize", value = "分页大小", required = true, dataType = "int"),
            @ApiImplicitParam(name = "pageNum", value = "分页开始索引", required = true, dataType = "int"),
            @ApiImplicitParam(name = "uid", value = "用户id", required = true, dataType = "int")
    })
    @ExtPageHelper
    public Notice selSysUserRoleList(@RequestParam("uid") String uid) {
        QueryWrapper<SysUserRole> queryWrapper = new QueryWrapper<SysUserRole>();
        queryWrapper.eq("uid", uid);
        return new Notice(HttpStatus.OK, sysUserRoleServiceImpl.list(queryWrapper), "成功");
    }

    @GetMapping("/delSysUserRole")
    @ApiOperation(value = "删除用户角色", notes = "删除用户角色")
    public Notice delSysUserRole(@RequestParam("id") String id) {
        if (sysUserRoleServiceImpl.removeById(id)) {
            return new Notice(HttpStatus.OK, "成功");
        }
        return new Notice(HttpStatus.INTERNAL_SERVER_ERROR, "失败");
    }
}
