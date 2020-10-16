package com.api.service.impl.user_info;

import com.api.entity.user_info.UserInfo;
import com.api.entity.user_info.UserInfoTwo;
import com.api.mapper.UserInfoDao;
import com.api.service.able.UserInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author hjs
 * @since 2020-10-16
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoDao, UserInfo> implements UserInfoService {
    @Override
    public UserInfoTwo SignIn(Map map) {
        return baseMapper.SignIn(map);
    }
}
