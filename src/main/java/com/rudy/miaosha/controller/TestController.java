package com.rudy.miaosha.controller;

import com.rudy.miaosha.domain.UserInfo;
import com.rudy.miaosha.result.CodeMsg;
import com.rudy.miaosha.result.Result;
import com.rudy.miaosha.service.UserService;
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

    @RequestMapping(value = "/test")
    @ResponseBody
    public Result<UserInfo> testMethod(int id) {
        UserInfo userinfo = userService.getUserinfoById(id);
        return Result.success(userinfo);
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
}
