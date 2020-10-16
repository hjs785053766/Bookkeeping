package com.api.controller;

import com.api.entity.user_info.UserInfoTwo;
import com.api.service.able.UserInfoService;
import com.api.utils.JWTUtils;
import com.api.util.Notice;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Api(tags = "登录接口")
@RestController
@RequestMapping("/UserInfo")
public class UserInfoController {

    @Resource
    UserInfoService userInfoService;

    @Resource
    private JWTUtils jwtUtils;

    /**
     * 获取token
     *
     * @param username 账户
     * @return
     */
    @GetMapping("/getToken")
    @ApiOperation(value = "获取token", notes = "获取token")
    public Notice getToken(@RequestParam String username, @RequestParam String password) throws Exception {
        Map map = new HashMap();
        map.put("username", username);
        map.put("password", password);
        UserInfoTwo userInfoTwo = userInfoService.SignIn(map);
        return new Notice(HttpStatus.OK, jwtUtils.generateToken(userInfoTwo), "成功");
    }

    /**
     * 验证token
     *
     * @return
     */
    @GetMapping("/verifyToken")
    @ApiOperation(value = "验证token", notes = "验证token")
    public Notice verifyToken(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 获取指定的请求数据
        response.setContentType("text/html;charset=utf-8");
        System.out.println(request.getHeader("token"));
        return jwtUtils.verification(request.getHeader("token"));
    }
}