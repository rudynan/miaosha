package com.rudy.miaosha.redis;

import org.apache.catalina.User;

public class UserKey extends BasePrefix {

    private static final int TOKEN_EXPIRE = 3600*24*2;

    public UserKey(String prefix) {
        super(prefix);
    }

    private UserKey(int expireSecond, String prefix) {
        super(expireSecond, prefix);
    }

    public static UserKey getById = new UserKey("id");
    public static UserKey getByName = new UserKey("name");
    public static UserKey getByToken = new UserKey(TOKEN_EXPIRE,"token");

}
