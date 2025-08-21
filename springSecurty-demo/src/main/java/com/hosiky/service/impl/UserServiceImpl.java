package com.hosiky.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.toolkit.Db;
import com.hosiky.common.Result;
import com.hosiky.domain.po.User;
import com.hosiky.domain.po.UserRole;
import com.hosiky.mapper.UserMapper;
import com.hosiky.security.entity.GiteeModel;
import com.hosiky.security.entity.MyUserDetail;
import com.hosiky.security.entity.OAuthRequest;
import com.hosiky.service.IUserRoleService;
import com.hosiky.service.IUserService;
import com.hosiky.utils.JwtUtils;
import com.hosiky.utils.RedisUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author author
 * @since 2025-04-01
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    private final GiteeModel giteeModel;
    private final RedisUtil redisUtil;
    private final WebClient loginClient;
    private final AuthenticationManager authenticationManager;
    private final IUserRoleService userRoleService;
    private final JwtUtils jwtUtils;

    @Override
    public Result loginByUsername(User user) {
        // 封装用户名和密码
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                user.getUsername(), user.getPassword());
        // 调用认证管理中的认证方法，调用后可抛出异常。所以需要try...catch
            // 封装用户信息至SpringSecurity上下文中

            try {
                Authentication authenticate = authenticationManager.authenticate(authenticationToken);
                SecurityContextHolder.getContext().setAuthentication(authenticate);
                if (Objects.isNull(authenticate)) {
                    throw new AuthenticationServiceException("认证失败,用户名或密码错误");
                }

                // 获取用户身份信息
                MyUserDetail myUserDetail = (MyUserDetail) authenticate.getPrincipal();
                if (Objects.isNull(myUserDetail)) {
                    throw new AuthenticationServiceException("认证失败,用户名或密码错误");
                }
                // 清理redis
                String id = myUserDetail.getUser().getUserId().toString();
                Set<String> keys = redisUtil.scan("User:" + id + ":*");
                if (!keys.isEmpty()) {
                    redisUtil.delete(keys);
                }
                // 生成TOKEN
                Map<String, Object> claims = Map.of(
                        "id", id
                );

                String token = jwtUtils.generateJwt(claims);

                // 写入redis
                String key = "User:" + id + ":" + token;
                redisUtil.set(key, JSON.toJSONString(myUserDetail));

                return Result.ok(token);
            } catch (AuthenticationException e) {
                throw new AuthenticationServiceException("认证失败,用户名或密码错误");
            }
    }

    @Override
    public Result loginByGitee(OAuthRequest oauth) {
        String code = oauth.getCode();
        // post 获取Token
        String url = "https://gitee.com/oauth/token?grant_type=authorization_code"
                + "&code=" + code
                + "&client_id=" + giteeModel.getClientId()
                + "&redirect_uri=" + giteeModel.getRedirectUri()
                + "&client_secret=" + giteeModel.getClientSecret();
        Map block = loginClient.post().uri(url).retrieve().bodyToMono(Map.class).block();
        if (block == null) {
            // 抛出异常
            throw new RuntimeException("获取Gitee的Token失败");
        }
        // get 获取用户信息
        Map thirdUser = loginClient.get()
                .uri("https://gitee.com/api/v5/user?access_token=" + block.get("access_token"))
                .retrieve()
                .bodyToMono(Map.class)
                .block();
        log.info("Gitee返回信息：{}",thirdUser);
        String openId = thirdUser.get("id").toString();
        LambdaQueryChainWrapper<User> qw = Db.lambdaQuery(User.class).eq(User::getOpenId, openId);
        User user;
        if (!qw.exists()) {
            // 没找到账号信息，那就是第一次使用gitee登录，需要创建一个新用户
            user = new User();
            user.setOpenId(openId);
            user.setUsername(thirdUser.get("login").toString());
            user.setPassword("123456"); // 设置默认密码
            user.setPhone(thirdUser.get("phone") != null ? thirdUser.get("phone").toString() : "");
            user.setEmail(thirdUser.get("email") != null ? thirdUser.get("email").toString() : "");
            save(user);
            UserRole userRole = new UserRole();
            userRole.setUserId(user.getUserId());
            userRole.setRoleId(1000L);
            userRoleService.save(userRole);
        } else {
            user = qw.one();
        }

        // 创建认证token
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                user.getUsername(), user.getPassword());
        
        try {
            // 进行认证
            Authentication authenticate = authenticationManager.authenticate(authenticationToken);
            SecurityContextHolder.getContext().setAuthentication(authenticate);
            
            if (Objects.isNull(authenticate)) {
                throw new AuthenticationServiceException("Gitee认证失败");
            }
            
            // 获取用户身份信息
            MyUserDetail myUserDetail = (MyUserDetail) authenticate.getPrincipal();
            if (Objects.isNull(myUserDetail)) {
                throw new AuthenticationServiceException("Gitee认证失败");
            }
            
            // 清理redis
            String id = myUserDetail.getUser().getUserId().toString();
            Set<String> keys = redisUtil.scan("User:" + id + ":*");
            if (!keys.isEmpty()) {
                redisUtil.delete(keys);
            }

            Map<String, Object> claims = Map.of(
                    "id", id
            );
            // 生成JWT token
            String jwtToken = jwtUtils.generateJwt(claims);
//            String jwtToken = JwtTokenUtil.createToken(id);
            
            // 写入redis
            String key = "User:" + id + ":" + jwtToken;
            redisUtil.set(key, JSON.toJSONString(myUserDetail));
            
            return Result.ok(jwtToken);
        } catch (AuthenticationException e) {
            throw new AuthenticationServiceException("Gitee认证失败");
        }
    }
}
