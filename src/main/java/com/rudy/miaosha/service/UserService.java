package com.rudy.miaosha.service;

import com.rudy.miaosha.dao.UserDao;
import com.rudy.miaosha.domain.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


public interface UserService {

    UserInfo getUserinfoById(int id);
}
