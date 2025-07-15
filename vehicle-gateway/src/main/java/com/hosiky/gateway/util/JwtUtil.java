package com.hosiky.gateway.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;

public class JwtUtil {

    public static boolean validateJwt(String token, String secretKey) {
        try {
            // 从密钥字符串生成 HMAC-SHA 签名密钥
            SecretKey key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));

            // 构建 JWT 解析器并设置签名密钥
            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token); // 解析 JWT

            // 如果解析成功，返回 true
            return true;
        } catch (Exception e) {
            // 如果解析失败（例如签名无效、过期等），返回 false
            return false;
        }
    }
}