package com.hosiky.gateway.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hosiky.common.domain.Result;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;

/***
 * 当用户访问受保护资源但没有携带有效 Token 或 Token 无效时，Spring Security 会触发这个类，由它返回一个统一的 401 错误响应。
 */
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Resource
    private ObjectMapper objectMapper;
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType("application/json;charset=utf-8");

        response.getWriter().write(
                objectMapper.writeValueAsString(
                        Result.error(401, authException.getMessage())
                )
        );
    }
}
