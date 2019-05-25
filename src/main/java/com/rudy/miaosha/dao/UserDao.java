package com.rudy.miaosha.dao;

import com.rudy.miaosha.domain.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import javax.websocket.server.PathParam;

@Mapper
public interface UserDao {

    @Select("select * from user_info where id = #{id}")
    public UserInfo getUserById(int id);
}
