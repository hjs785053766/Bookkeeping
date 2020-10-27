package com.api.service.impl.flowing_water_name;

import cn.hutool.core.util.IdUtil;
import com.api.entity.flowing_water_name.FlowingWaterName;
import com.api.mapper.FlowingWaterNameMapper;
import com.api.service.able.FlowingWaterNameService;
import com.api.util.RedisUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 快捷流水名称 服务实现类
 * </p>
 *
 * @author hjs
 * @since 2020-10-27
 */
@Service
public class FlowingWaterNameServiceImpl extends ServiceImpl<FlowingWaterNameMapper, FlowingWaterName> implements FlowingWaterNameService {

    @Resource
    private RedisUtil redisUtil;

    public void selName(String name, String userId) {
        Object key = redisUtil.get(userId + "-" + name);
        if (key == null) {
            redisUtil.setDataBase(1);
            redisUtil.set(userId + "-" + name, name);
            FlowingWaterName flowingWaterName = new FlowingWaterName();
            flowingWaterName.setId(IdUtil.simpleUUID());
            flowingWaterName.setUserId(userId);
            flowingWaterName.setValue(name);
            save(flowingWaterName);
        }
    }
}
