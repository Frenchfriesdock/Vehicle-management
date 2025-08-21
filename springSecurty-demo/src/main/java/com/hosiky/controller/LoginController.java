package com.hosiky.controller;

import com.hosiky.common.Result;
import com.hosiky.domain.po.User;
import com.hosiky.security.entity.GiteeModel;
import com.hosiky.security.entity.OAuthRequest;
import com.hosiky.service.IUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@Tag(name = "登录")
@RestController
@RequestMapping("/user/login/")
@RequiredArgsConstructor
@Slf4j
public class LoginController {

    private final IUserService userService;
    private final GiteeModel giteeModel;

    @Operation(summary = "用户名登录")
    @PostMapping("username")
    public Result loginByUsername(@RequestBody User user) {
        return userService.loginByUsername(user);
    }

    @Operation(summary = "传给前端gitee的信息")
    @GetMapping("getGitee")
    public Result getGitee() {
        HashMap<String, Object> config = new HashMap<>();
        config.put("clientId", giteeModel.getClientId());
        config.put("redirectUri", giteeModel.getRedirectUri());
        return Result.ok(config);
    }

    @Operation(summary = "gitee登录")
    @PostMapping("gitee")
    public Result loginByGitee(@RequestBody OAuthRequest oauth) {
        return userService.loginByGitee(oauth);
    }


}
