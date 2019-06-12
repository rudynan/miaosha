package com.rudy.miaosha.service.impl;

import com.rudy.miaosha.dao.MXUserDao;
import com.rudy.miaosha.domain.MXUser;
import com.rudy.miaosha.exception.GlobleException;
import com.rudy.miaosha.redis.UserKey;
import com.rudy.miaosha.result.CodeMsg;
import com.rudy.miaosha.service.RedisService;
import com.rudy.miaosha.service.UserService;
import com.rudy.miaosha.util.MD5Util;
import com.rudy.miaosha.util.UUIDUtil;
import com.rudy.miaosha.vo.LoginVo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Service
public class UserServiceImpl implements UserService {

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    public static final String COOKIE = "token";
    @Autowired
    private MXUserDao mxUserDao;
    @Autowired
    RedisService redisService;

    @Override
    public String  login(HttpServletResponse response, LoginVo loginVo) throws Exception {
        String mobile = loginVo.getMobile();
        Long mobileNum = Long.parseLong(mobile);
        MXUser userById = mxUserDao.getUserById(mobileNum);

        if (userById != null){
            String password = loginVo.getPassword();
            String fpass = MD5Util.formPassToDBPass(password, userById.getSalt());
            if (StringUtils.equals(fpass, userById.getPassword())){
                String token = UUIDUtil.uuid();
                this.addToken(response,token, userById);
                return token;
            }else {
                throw new GlobleException(CodeMsg.PASSWORD_ERROR);
            }
        }
       throw new GlobleException(CodeMsg.MOBILE_NOT_EXIST);
    }

    @Override
    public MXUser getUserByToken(HttpServletResponse response,String token) throws Exception {
        if (StringUtils.isBlank(token)){
            return null;
        }
        try {
            MXUser mxUser = redisService.get(UserKey.getByToken, token, MXUser.class);
            if (mxUser != null){
                // 延长有效期
                this.addToken(response,token, mxUser);
            }
            return mxUser;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public MXUser getUserById(long id) throws Exception {
        MXUser user = redisService.get(UserKey.getById, id + "", MXUser.class);
        if (user != null){
            return user;
        }
        MXUser userById = mxUserDao.getUserById(id);
        if (userById != null){
            boolean set = redisService.set(UserKey.getById, id + "", userById);
        }
        return userById;
    }


    private void addToken(HttpServletResponse response,String token,MXUser userById) throws Exception {

        redisService.set(UserKey.getByToken,token,userById);
        Cookie cookie = new Cookie(COOKIE,token);
        cookie.setMaxAge(UserKey.getByToken.expireSeconds());
        cookie.setPath("/");
        response.addCookie(cookie);
    }
}
