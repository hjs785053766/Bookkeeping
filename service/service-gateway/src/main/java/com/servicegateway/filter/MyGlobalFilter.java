package com.servicegateway.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.servicegateway.utils.EncryptUtil;
import com.servicegateway.utils.Notice;
import com.servicegateway.utils.RedisUtil;
import io.jsonwebtoken.*;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.net.URI;
import java.util.*;


/**
 * 全局过滤器
 * 校验token
 */
public class MyGlobalFilter implements GlobalFilter, Ordered {

    private static final Log log = LogFactory.getLog(MyGlobalFilter.class);

    @Resource
    private RedisUtil redisUtil;

    /**
     * 秘钥
     */
    @Value("${jwt.secret}")
    String secret;
    /**
     * 有效期，单位秒
     */
    @Value("${jwt.expirationTimeInSecond}")
    Long expirationTimeInSecond;

    private static final String TOKEN = "token";

    /**
     * 请求白名单
     */
    private String[] skipAuthUrls = {"auth"};

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String url = exchange.getRequest().getURI().getPath();
        String urlSon = exchange.getRequest().getURI().getPath();
        ServerHttpRequest oldRequest = exchange.getRequest();
        URI uri = oldRequest.getURI();
        ServerHttpRequest.Builder builder = oldRequest.mutate().uri(uri);
        //判断是否是Knife4j请求
        boolean urlJudge = url.contains("/v2/api-docs") || url.contains("/auth/UserInfo/getToken") || url.contains("/auth/UserInfo/verifyToken");
        if (urlJudge) {
            return chain.filter(exchange);
        }
        //获取token
        List<String> tokenList = exchange.getRequest().getHeaders().get(TOKEN);
        String token = null;
        String userJson = null;
        if (tokenList != null && tokenList.size() > 0) {
            token = tokenList.get(0);
        }
        if (StringUtils.isBlank(token)) {
            //没有token
            return returnAuthFail(exchange, 4);
        } else {
            try {
                Notice notice = verification(token);
                if (notice.getState() == HttpStatus.INTERNAL_SERVER_ERROR) {
                    if (notice.getNotice().equals("5")) {
                        return returnAuthFail(exchange, 5);
                    } else if (notice.getNotice().equals("6")) {
                        return returnAuthFail(exchange, 6);
                    }
                    return returnAuthFail(exchange, 0);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            Claims claims = getClaimsFromToken(token);
            String redisKey = "";
            if (claims == null) {
                return returnAuthFail(exchange, 3);
            }
            redisKey = claims.get("key").toString();
            redisUtil.setDataBase(0);
            Object user = redisUtil.get(redisKey);
            if (user == null) {
                return returnAuthFail(exchange, 2);
            }
            userJson = user.toString();
        }
        //查询白名单
        url = url.substring(url.indexOf("/") + 1, url.lastIndexOf("/"));
        url = url.substring(0, url.lastIndexOf("/"));
        if (null != skipAuthUrls && Arrays.asList(skipAuthUrls).contains(url)) {
            if (urlSon.toLowerCase().contains("list") || urlSon.toLowerCase().contains("List")) {
                String query = exchange.getRequest().getURI().getQuery();
                List<String> result = Arrays.asList(query.split("&"));
                for (String a : result) {
                    String[] split = a.split("=");//以逗号分割
                    builder.header(split[0], split[1]);
                }
                ServerHttpRequest newRequest = builder.build();
                return chain.filter(exchange.mutate().request(newRequest).build());
            }
            return chain.filter(exchange);
        }
        try {
            Map mapTypes = JSON.parseObject(userJson);
            Map urlMap = JSON.parseObject(mapTypes.get("url").toString());
            if (urlMap.get(urlSon) != null) {
                Map userMap = JSON.parseObject(mapTypes.get("user").toString());
                //重写请求头部
                EncryptUtil des = EncryptUtil.getEncryptUtil("b068931cc450442b63f5b3d276ea4297", "utf-8");
                builder.header("name", des.encode(userMap.get("name").toString()));
                builder.header("url", urlSon);
                builder.header("id", userMap.get("id").toString());
                builder.header("time", new Date().getTime() / 1000 + "");
                if (urlSon.toLowerCase().contains("list") || urlSon.toLowerCase().contains("List")) {
                    String query = exchange.getRequest().getURI().getQuery();
                    List<String> result = Arrays.asList(query.split("&"));
                    for (String a : result) {
                        String[] split = a.split("=");//以逗号分割
                        builder.header(split[0], split[1]);
                    }
                }
                //重写请求头部
                ServerHttpRequest newRequest = builder.build();

                return chain.filter(exchange.mutate().request(newRequest).build());
            } else {
                return returnAuthFail(exchange, 1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return returnAuthFail(exchange, 0);
        }
    }

    /**
     * 返回校验失败
     *
     * @param exchange
     * @return
     */
    private Mono<Void> returnAuthFail(ServerWebExchange exchange, int state) {
        String url = url = "http://127.0.0.1:8762/error?state=" + state;
        ServerHttpResponse response = exchange.getResponse();
        //303状态码表示由于请求对应的资源存在着另一个URI，应使用GET方法定向获取请求的资源
        response.setStatusCode(HttpStatus.SEE_OTHER);
        response.getHeaders().set(HttpHeaders.LOCATION, url);
        return response.setComplete();
    }

    /**
     * 从token中获取claim
     *
     * @param token token
     * @return claim
     */
    public Claims getClaimsFromToken(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(this.secret.getBytes())
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException | IllegalArgumentException e) {
            return null;
        }
    }

    @Override
    public int getOrder() {
        return -100;
    }

    /**
     * 判断token是否非法
     *
     * @param token token
     * @return 未过期返回true，否则返回false
     */
    public Boolean validateToken(String token) {
        return !isTokenExpired(token);
    }

    /**
     * 判断token是否过期
     *
     * @param token token
     * @return 已过期返回true，未过期返回false
     */
    private Boolean isTokenExpired(String token) {
        Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    /**
     * 获取token的过期时间
     *
     * @param token token
     * @return 过期时间
     */
    public Date getExpirationDateFromToken(String token) {
        return getClaimsFromToken(token)
                .getExpiration();
    }

    /**
     * 验证token是否合法以及过期，未过期返回用户json
     *
     * @param token
     * @return
     * @throws Exception
     */
    public Notice verification(String token) throws Exception {
        // 验证token是否被修改
        try {
            validateToken(token);
        } catch (Exception e) {
            // TODO: handle exception
            return new Notice(HttpStatus.INTERNAL_SERVER_ERROR, "5");
        }

        String someToken = token;
        // 测试2: 如果能token合法且未过期，返回true
        Boolean validateToken = validateToken(someToken);
        if (!validateToken) {
            return new Notice(HttpStatus.INTERNAL_SERVER_ERROR, "6");
        }
        return new Notice(HttpStatus.OK, "成功");
    }
}