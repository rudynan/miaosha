package com.rudy.miaosha.controller;

import com.rudy.miaosha.result.CodeMsg;
import com.rudy.miaosha.result.Result;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {


    @RequestMapping(value = "/test")
    public Result<String> testMethod() {
        return Result.success("this is ok");
    }

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
