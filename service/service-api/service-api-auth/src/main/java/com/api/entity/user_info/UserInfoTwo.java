package com.api.entity.user_info;


import com.api.entity.sys_role.SysRoleTwo;
import lombok.Data;

import java.util.List;

@Data
public class UserInfoTwo extends UserInfo{

    List<SysRoleTwo> sysRoleTwoList;
}