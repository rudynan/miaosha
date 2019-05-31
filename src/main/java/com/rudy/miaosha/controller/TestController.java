package com.rudy.miaosha.controller;

import com.rudy.miaosha.domain.UserInfo;
import com.rudy.miaosha.redis.UserKey;
import com.rudy.miaosha.result.CodeMsg;
import com.rudy.miaosha.result.Result;
import com.rudy.miaosha.service.RedisService;
import com.rudy.miaosha.service.UserService;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class TestController {

    @Autowired
    private UserService userService;
    @Autowired
    private RedisService redisService;
    @RequestMapping(value = "/test")
    @ResponseBody
    public Result<UserInfo> testMethod(int id) {
        return null;
    }
    @ResponseBody
    @RequestMapping(value = "/testError")
    public Result<String> testError() {
        return Result.error(CodeMsg.SUCCESS);
    }

    @RequestMapping(value = "/thymeleaf")
    public String testThymeleaf(Model model) {

        model.addAttribute("name", "rudynan");
        return "hello";
    }

    @RequestMapping("/redis/get")
    @ResponseBody
    public Result<String> redisGet() throws Exception {
        String  key1 = redisService.get(UserKey.getById,"key1", String.class);
        return Result.success(key1);

    }

    @RequestMapping("/redis/set")
    @ResponseBody
    public Result<Boolean> redisSet() throws Exception {
         redisService.set(UserKey.getById,"key1", "valuessssss");
        return Result.success(true);

    }
}
