package com.rudy.miaosha.controller;

import com.rudy.miaosha.result.Result;
import com.rudy.miaosha.vo.LoginVo;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping(value = "/")
    public String  tologin() {
        return "login";
    }

    @PostMapping(value = "/login/do_login")
    @ResponseBody
    public Result<Boolean> login(LoginVo loginVo) {
        logger.info("login接口入参为:{}",loginVo);
        return null;
    }
}
