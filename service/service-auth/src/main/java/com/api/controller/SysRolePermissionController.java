package com.api.controller;

import com.api.aop.ExtPageHelper;
import com.api.entity.sys_role_permission.SysRolePermission;
import com.api.service.impl.sys_role_permission.SysRolePermissionServiceImpl;
import com.api.util.HttpStatus;
import com.api.util.Notice;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Api(tags = "角色权限接口")
@RestController
@RequestMapping("/sysRolePermission")
public class SysRolePermissionController {
    @Resource
    SysRolePermissionServiceImpl sysRolePermissionServiceImpl;

    @PostMapping("/insSysRolePermission")
    @ApiOperation(value = "创建角色权限", notes = "创建角色权限", response = SysRolePermission.class)
    public Notice insSysRolePermission(@RequestBody SysRolePermission sysRolePermission) {
        if (sysRolePermissionServiceImpl.save(sysRolePermission)) {
            return new Notice(HttpStatus.OK, "成功");
        }
        return new Notice(HttpStatus.INTERNAL_SERVER_ERROR, "失败");
    }

    @GetMapping("/selSysRolePermissionList")
    @ApiOperation(value = "查询角色权限列表", notes = "查询角色权限列表", response = SysRolePermission.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageSize", value = "分页大小", required = true, dataType = "int"),
            @ApiImplicitParam(name = "pageNum", value = "分页开始索引", required = true, dataType = "int"),
            @ApiImplicitParam(name = "roleId", value = "角色id", required = true, dataType = "int")
    })
    @ExtPageHelper
    public Notice selSysRolePermissionList(@RequestParam("roleId") String roleId) {
        QueryWrapper<SysRolePermission> queryWrapper = new QueryWrapper<SysRolePermission>();
        queryWrapper.eq("role_id", roleId);
        return new Notice(HttpStatus.OK, sysRolePermissionServiceImpl.list(queryWrapper), "成功");
    }

    @GetMapping("/delSysRolePermission")
    @ApiOperation(value = "删除角色权限", notes = "删除角色权限")
    public Notice delSysRolePermission(@RequestParam("id") String id) {
        if (sysRolePermissionServiceImpl.removeById(id)) {
            return new Notice(HttpStatus.OK, "成功");
        }
        return new Notice(HttpStatus.INTERNAL_SERVER_ERROR, "失败");
    }
}
