package com.rudy.miaosha.dao;

import com.rudy.miaosha.domain.MXUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MXUserDao {

    MXUser getUserById(Long id) throws Exception;

    void addUser() throws Exception;

}
