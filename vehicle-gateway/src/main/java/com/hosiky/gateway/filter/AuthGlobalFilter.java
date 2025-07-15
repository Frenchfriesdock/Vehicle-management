package com.hosiky.gateway.filter;

import cn.hutool.core.text.AntPathMatcher;
import com.hosiky.gateway.config.AuthProperties;
import com.hosiky.gateway.config.JwtProperties;
import com.hosiky.gateway.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
@Order(-1)
public class AuthGlobalFilter implements GlobalFilter {

    @Autowired
    private AuthProperties authProperties;

    @Autowired
    private JwtProperties jwtProperties;

    private final AntPathMatcher pathMatcher = new AntPathMatcher();

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 获取当前请求
        ServerHttpRequest request = exchange.getRequest();
        String path = request.getURI().getPath();

        // 检查请求路径是否在排除列表中（无需验证的路径）
        for(String pattern : authProperties.getExcludePaths()) {
            if(pathMatcher.match(pattern, path)) {
                return chain.filter(exchange); // 直接放行
            }
        }

        // 从请求头中获取 JWT 令牌
        String token = request.getHeaders().getFirst(jwtProperties.getTokenName());

        // 验证令牌是否存在且有效
        if(token != null || !JwtUtil.validateJwt(token, jwtProperties.getSecretKey())) {
            ServerHttpResponse response = exchange.getResponse();
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return response.setComplete(); // 返回 401 未授权
        }

        // 验证通过，继续处理请求
        return chain.filter(exchange);
    }
}