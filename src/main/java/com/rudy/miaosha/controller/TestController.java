package com.rudy.miaosha.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {


    @RequestMapping(value = "/test")
    public String testMethod() {
        return "hello";
    }
}
