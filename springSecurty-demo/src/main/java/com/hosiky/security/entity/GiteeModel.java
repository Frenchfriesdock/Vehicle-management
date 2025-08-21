package com.hosiky.security.entity;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix =  "oauth2.gitee")
public class GiteeModel {
    private String clientId;
    private String clientSecret;
    private String redirectUri;
}
