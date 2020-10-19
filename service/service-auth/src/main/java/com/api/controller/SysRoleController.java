package com.api.controller;

import com.api.aop.ExtPageHelper;
import com.api.entity.sys_permission.SysPermission;
import com.api.entity.sys_role.SysRole;
import com.api.entity.sys_role_permission.SysRolePermission;
import com.api.service.impl.sys_role.SysRoleServiceImpl;
import com.api.service.impl.sys_role_permission.SysRolePermissionServiceImpl;
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

@Api(tags = "角色接口")
@RestController
@RequestMapping("/SysRole")
public class SysRoleController {
    @Resource
    SysRoleServiceImpl sysRoleServiceImpl;
    @Resource
    SysRolePermissionServiceImpl sysRolePermissionServiceImpl;

    /**
     * 创建角色
     *
     * @return
     */
    @PostMapping("/insRole")
    @ApiOperation(value = "创建角色", notes = "创建角色", response = SysRole.class)
    public Notice insRole(@RequestBody SysRole sysRole) {
        sysRole.setId(new Date().getTime() / 1000);
        if (sysRoleServiceImpl.save(sysRole)) {
            return new Notice(HttpStatus.OK, "成功");
        }
        return new Notice(HttpStatus.INTERNAL_SERVER_ERROR, "失败");
    }

    @GetMapping("/selRoleList")
    @ApiOperation(value = "查询角色列表", notes = "查询角色列表", response = SysRole.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageSize", value = "分页大小", required = true, dataType = "int"),
            @ApiImplicitParam(name = "pageNum", value = "分页开始索引", required = true, dataType = "int")
    })
    @ExtPageHelper
    public Notice selRoleList() {
        return new Notice(HttpStatus.OK, sysRoleServiceImpl.list(), "成功");
    }

    @PutMapping("/updRole")
    @ApiOperation(value = "修改角色", notes = "修改角色", response = SysRole.class)
    public Notice updRole(@RequestBody SysRole sysRole) {
        if (sysRoleServiceImpl.updateById(sysRole)) {
            return new Notice(HttpStatus.OK, "成功");
        }
        return new Notice(HttpStatus.INTERNAL_SERVER_ERROR, "失败");
    }

    @DeleteMapping("/delRole")
    @ApiOperation(value = "删除角色", notes = "删除角色")
    public Notice delRole(@RequestParam("id") String id) {
        QueryWrapper<SysRolePermission> queryWrapper = new QueryWrapper<SysRolePermission>();
        queryWrapper.eq("role_id", id);
        if (sysRolePermissionServiceImpl.list(queryWrapper).size() > 0) {
            Map<String, Object> map = new HashMap<>();
            map.put("role_id", id);
            sysRolePermissionServiceImpl.removeByMap(map);
        }
        if (sysRoleServiceImpl.removeById(id)) {
            return new Notice(HttpStatus.OK, "成功");
        }
        return new Notice(HttpStatus.INTERNAL_SERVER_ERROR, "失败");
    }
}
