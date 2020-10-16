package com.api.service.able;

import com.api.entity.user_info.UserInfo;
import com.api.entity.user_info.UserInfoTwo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author hjs
 * @since 2020-10-16
 */
public interface UserInfoService extends IService<UserInfo> {
    UserInfoTwo SignIn(Map map);
}
