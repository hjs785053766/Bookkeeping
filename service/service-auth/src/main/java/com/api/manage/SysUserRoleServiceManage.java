package com.api.manage;

import com.api.entity.sys_role.SysRole;
import com.api.entity.sys_user_role.SysUserRole;
import com.api.service.impl.sys_role.SysRoleServiceImpl;
import com.api.service.impl.sys_user_role.SysUserRoleServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class SysUserRoleServiceManage {

    @Resource
    SysUserRoleServiceImpl sysUserRoleServiceImpl;
    @Resource
    SysRoleServiceImpl sysRoleServiceImpl;

    public void insSysUserRole(Long uid) {
        QueryWrapper<SysRole> queryWrapper = new QueryWrapper<SysRole>();
        queryWrapper.eq("name", "记账角色");
        List<SysRole> list = sysRoleServiceImpl.list(queryWrapper);
        Long id = null;
        if (list.size() <= 0) {
            SysRole sysRole = new SysRole();
            id = new Date().getTime() / 1000;
            sysRole.setId(id);
            sysRole.setName("记账角色");
            sysRole.setDescription("记账专用角色");
            sysRoleServiceImpl.save(sysRole);
        } else {
            id = list.get(0).getId();
        }
        sysUserRoleServiceImpl.save(new SysUserRole(id, uid));
    }
}
