package com.hosiky.controller;


import com.hosiky.common.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public/")
public class PublicController {

    /**
     * 测试方法1 -- 游客访问 -- 不登录也可以访问
     */
    @GetMapping(value = "test1")
    public Result test1(){
        System.out.println("test1...");
        return Result.ok("test1...");
    }

}
