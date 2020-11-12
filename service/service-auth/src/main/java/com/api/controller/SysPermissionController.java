package com.api.controller;

import com.api.aop.ExtPageHelper;
import com.api.entity.sys_permission.SysPermission;
import com.api.service.able.SysPermissionService;
import com.api.util.Notice;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;

@Api(tags = "权限接口")
@RestController
@RequestMapping("/sysPermission")
public class SysPermissionController {
    @Resource
    SysPermissionService sysPermissionService;

    @GetMapping("/selSysPermissionList")
    @ApiOperation(value = "查询接口权限列表", notes = "查询接口权限列表", response = SysPermission.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageSize", value = "分页大小", required = true, dataType = "int"),
            @ApiImplicitParam(name = "pageNum", value = "分页开始索引", required = true, dataType = "int")
    })
    @ExtPageHelper
    public Notice selSysPermissionList() {
        return new Notice(HttpStatus.OK, sysPermissionService.list(), "成功");
    }

    @PostMapping("/insSysPermission")
    @ApiOperation(value = "添加接口权限", notes = "添加接口权限", response = SysPermission.class)
    public Notice insSysPermission(@RequestBody SysPermission sysPermission) {
        sysPermission.setId(new Date().getTime() / 1000);
        if (sysPermissionService.save(sysPermission)) {
            return new Notice(HttpStatus.OK, "成功");
        }
        return new Notice(HttpStatus.INTERNAL_SERVER_ERROR, "失败");
    }

    @PostMapping("/updSysPermission")
    @ApiOperation(value = "修改接口权限", notes = "修改接口权限", response = SysPermission.class)
    public Notice updSysPermission(@RequestBody SysPermission sysPermission) {
        if (sysPermissionService.updateById(sysPermission)) {
            return new Notice(HttpStatus.OK, "成功");
        }
        return new Notice(HttpStatus.INTERNAL_SERVER_ERROR, "失败");
    }

    @GetMapping("/delSysPermission")
    @ApiOperation(value = "删除接口权限", notes = "删除接口权限")
    public Notice delSysPermission(@RequestParam("id") String id) {
        if (sysPermissionService.removeById(id)) {
            return new Notice(HttpStatus.OK, "成功");
        }
        return new Notice(HttpStatus.INTERNAL_SERVER_ERROR, "失败");
    }
}
