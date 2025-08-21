package com.hosiky.controller;

import com.hosiky.common.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/myApi")
public class TestController {

    /**
     * 测试方法2 -- 登录后才可以访问
     * @return
     */
    @PreAuthorize(value = "isAuthenticated()")
    @GetMapping(value = "/test2")
    public Result test2(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info("用户名：{}",authentication);
        return Result.ok("test2...");
    }

    /**
     * 测试方法3 -- 登录后，具有 student 角色才能访问
     */
    @PreAuthorize(value = "hasRole('student')")
    @GetMapping(value = "/test3")
    public Result test3(){
        System.out.println("test3...");
        return Result.ok("test3...");
    }

    /**
     * 测试方法4 -- 登录后，具有 teacher 或者 student 角色才能访问
     */
    @PreAuthorize(value = "hasAnyRole('teacher','student')")
    @GetMapping(value = "/test4")
    public Result test4(){
        System.out.println("test4...");
        return Result.ok("test4...");
    }

    /**
     * 测试方法5 -- 登录后，具有 add 权限才能访问
     */
    @PreAuthorize(value = "hasAuthority('add')")
    @GetMapping(value = "/test5")
    public Result test5(){
        System.out.println("test5...");
        return Result.ok("test5...");
    }

    /**
     * 测试方法6 -- 登录后，具有 student角色 和 add 权限才能访问
     */
    @PreAuthorize(value = "hasAuthority('add') and hasRole('student')")
    @GetMapping(value = "/test6")
    public Result test6(){
        System.out.println("test6...");
        return Result.ok("test6...");
    }

}
