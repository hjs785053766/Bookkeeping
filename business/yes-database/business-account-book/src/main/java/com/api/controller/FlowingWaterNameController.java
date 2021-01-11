package com.api.controller;

import com.api.util.BaseApiService;
import com.api.util.Notice;
import com.api.util.RedisUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Api(tags = "快捷流水名称接口")
@RestController
@RequestMapping("/flowingWaterNameController")
public class FlowingWaterNameController extends BaseApiService {

    @Resource
    private RedisUtil redisUtil;

    @GetMapping("/selFlowingWaterName")
    @ApiOperation(value = "查询快捷流水名称", notes = "查询快捷流水名称")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "流水名称", dataType = "String")
    })
    public Notice selFlowingWaterName(@RequestParam("name") String name) {
        redisUtil.setDataBase(1);
        Set<String> set = redisUtil.keys(getUserId() + "-*" + name + "*");
        List<String> date = new ArrayList();
        for (String a : set) {
            date.add(redisUtil.get(a).toString());
        }
        return new Notice(HttpStatus.OK, date, "成功");
    }
}
