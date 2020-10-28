package com.servicegateway.config;

import org.springframework.context.annotation.Configuration;

/**
 * 限流设置
 */
@Configuration
public class BeanConfig {

//    /**
//     * 这里设置的是ip的限流。
//     * @return
//     */
//    @Bean
//    public KeyResolver ipKeyResolver() {
//        return exchange -> Mono.just(exchange.getRequest().getRemoteAddress().getHostName());
//    }
}