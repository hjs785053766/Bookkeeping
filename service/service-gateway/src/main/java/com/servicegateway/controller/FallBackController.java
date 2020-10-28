package com.servicegateway.controller;

import com.servicegateway.utils.Notice;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 熔断成功跳转接口
 */
@RestController
public class FallBackController {

    @GetMapping("/fallback")
    public Notice fallback() {
        return new Notice(HttpStatus.INTERNAL_SERVER_ERROR, "失败，服务被关闭");
    }

    @GetMapping("/error")
    public Notice error(@RequestParam("state") int state) {
        String notice = null;
        HttpStatus http = HttpStatus.UNAUTHORIZED;
        switch (state) {
            case 0:
                notice = "服务器异常";
                http = HttpStatus.INTERNAL_SERVER_ERROR;
                break;
            case 1:
                notice = "无权限，请更换帐号";
                break;
            case 2:
                notice = "帐号错误，请重新登录";
                break;
            case 3:
                notice = "帐号异常，请联系管理员";
                break;
            case 4:
                notice = "没有token,请重新登录";
                break;
            case 5:
                notice = "token过期或被串改，请重新登录";
                break;
        }
        return new Notice(http, notice);
    }
}
