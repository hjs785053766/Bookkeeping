package com.api.controller;

import com.api.entity.sys_role.SysRole;
import com.api.service.impl.sys_role.SysRoleServiceImpl;
import com.api.util.Notice;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;

@Api(tags = "角色接口")
@RestController
@RequestMapping("/SysRole")
public class SysRoleController {
    @Resource
    SysRoleServiceImpl sysRoleServiceImpl;

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
}
