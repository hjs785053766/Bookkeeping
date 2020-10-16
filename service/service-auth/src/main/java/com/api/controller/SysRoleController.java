package com.api.controller;

import com.api.entity.sys_role.SysRole;
import com.api.service.impl.sys_role.SysRoleServiceImpl;
import com.api.util.Notice;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Api(tags = "角色接口")
@RestController
@RequestMapping("/SysRole")
public class SysRoleController {
    @Resource
    SysRoleServiceImpl sysRoleServiceImpl;

    /**
     * @return
     */
    @GetMapping("/insRole")
    @ApiOperation(value = "添加角色", notes = "添加角色", response = SysRole.class)
    public Notice insRole() {
        return null;
    }
}
