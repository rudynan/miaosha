package com.rudy.miaosha.controller;

import com.rudy.miaosha.domain.MXUser;
import com.rudy.miaosha.service.UserService;
import com.rudy.miaosha.service.impl.UserServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;


@Controller
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private UserService userService;

    @RequestMapping("/to_list")
    public String toList(MXUser mxUser,Model model) throws Exception {
        if (mxUser == null){
            return "login";
        }else {
            model.addAttribute("user", mxUser);
            return "goods_list";
        }
    }
}
