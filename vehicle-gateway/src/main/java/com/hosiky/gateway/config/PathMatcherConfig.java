package com.hosiky.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.util.AntPathMatcher;

public class PathMatcherConfig {

    @Bean
    public AntPathMatcher antPathMatcher() {
        return new AntPathMatcher();
    }
}
