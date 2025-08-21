package com.hosiky.gateway.config;

import com.hosiky.gateway.security.SecurityWhiteList;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor

public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    private final JwtProperties jwtProperties;
    private final SecurityWhiteList securityWhiteList;
    private final AuthenticationManager authenticationManager;

    @Override
    protected void doFilterInternal(@NotNull HttpServletRequest request,
                                    @NotNull HttpServletResponse response,
                                    @NotNull FilterChain filterChain) throws ServletException, IOException {


//        判断是否在白名单里面
        String requestUrl = request.getRequestURI();
        if(isWhiteListed(requestUrl)) {
            filterChain.doFilter(request, response);
            return;
        }


        try {
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean isWhiteListed(String requestUrl) {
        return securityWhiteList.isWhiteList(requestUrl);
    }
}
