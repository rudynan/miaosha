package com.rudy.miaosha.util;

import org.apache.commons.codec.digest.DigestUtils;

public class MD5Util {

    private static final String salt = "loveisforever";
    public static String MD5Encode(String str){
       return DigestUtils.md5Hex(str);
    }

    public static String inputPassFormPass(String pass){
        return MD5Encode(pass+salt);
    }
    public static String formPassToDBPass(String formPass,String salt){
        return MD5Encode(formPass+salt);
    }

    public static String inputPassToDbPass(String formPass,String salt) {
        String inputPassFormPass = inputPassFormPass(formPass);
        return formPassToDBPass(inputPassFormPass, salt);
    }


}
