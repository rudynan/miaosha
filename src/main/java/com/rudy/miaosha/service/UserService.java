package com.rudy.miaosha.service;

import com.rudy.miaosha.domain.MXUser;
import com.rudy.miaosha.vo.LoginVo;

import javax.servlet.http.HttpServletResponse;


public interface UserService {

    public String login(HttpServletResponse response, LoginVo loginVo)throws Exception;

    public MXUser getUserByToken(HttpServletResponse response,String token) throws Exception;

}
