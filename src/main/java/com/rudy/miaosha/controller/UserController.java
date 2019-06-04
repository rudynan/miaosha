package com.rudy.miaosha.controller;

import com.rudy.miaosha.domain.MXUser;
import com.rudy.miaosha.redis.UserKey;
import com.rudy.miaosha.result.CodeMsg;
import com.rudy.miaosha.result.Result;
import com.rudy.miaosha.service.RedisService;
import com.rudy.miaosha.service.UserService;
import com.rudy.miaosha.util.UUIDUtil;
import com.rudy.miaosha.util.ValidatorUtil;
import com.rudy.miaosha.vo.LoginVo;
import com.sun.corba.se.spi.ior.IdentifiableFactory;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
public class UserController {

    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;


    @GetMapping(value = "/")
    public String  tologin() {
        return "login";
    }

    @PostMapping(value = "/login/do_login")
    @ResponseBody
    public Result<String> login(HttpServletResponse response, @Valid LoginVo loginVo) throws Exception {
        logger.info("login接口入参为:{}",loginVo);
        /****参数验证****/
//        if (StringUtils.isBlank(loginVo.getPassword())){
//            return Result.error(CodeMsg.PASSWORD_EMPTY);
//        }
//        if (StringUtils.isBlank(loginVo.getMobile())){
//            return Result.error(CodeMsg.MOBILE_EMPTY);
//        }
//        if (!ValidatorUtil.isMobile(loginVo.getMobile())){
//            return Result.error(CodeMsg.MOBILE_ERROR);
//        }
        /****参数验证end****/

        boolean login = userService.login(response,loginVo);
        logger.info("登录结果为:{}", login);
        return Result.success("登录成功");


    }
    @RequestMapping("/getuser")
    @ResponseBody
    public Result<MXUser> getUser(MXUser user, HttpServletRequest request){
        logger.info("获取用户信息为:{}", user);
        return Result.success(user);
    }
}
