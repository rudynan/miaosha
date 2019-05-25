package com.rudy.miaosha.service;

import com.rudy.miaosha.dao.UserDao;
import com.rudy.miaosha.domain.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserDao userDao;
    @Override
    public UserInfo getUserinfoById(int id) {
        return userDao.getUserById(id) ;
    }
}
