package com.api.mapper;

import com.api.entity.user_info.UserInfo;
import com.api.entity.user_info.UserInfoTwo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.Map;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author hjs
 * @since 2020-10-16
 */
public interface UserInfoDao extends BaseMapper<UserInfo> {
    UserInfoTwo SignIn(Map map);
}
