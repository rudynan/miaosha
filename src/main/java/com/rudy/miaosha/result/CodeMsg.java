package com.rudy.miaosha.result;

public class CodeMsg {
    private int code;
    private String msg;

    /*********通用异常*********/
    public static CodeMsg SYSTEM_ERROR = new CodeMsg(99999,"系统异常");
    public static CodeMsg SUCCESS = new CodeMsg(0,"成功");
    public static CodeMsg BIND_ERROR = new CodeMsg(77777,"参数校验异常:%s");

    /*********通用异常end*********/


    /***********登录模块异常 5001xx****************/
    public static CodeMsg PASSWORD_EMPTY = new CodeMsg(500100,"密码不能为空");
    public static CodeMsg MOBILE_EMPTY = new CodeMsg(500101,"手机号不能为空");
    public static CodeMsg MOBILE_ERROR = new CodeMsg(500102,"手机号格式错误");
    public static CodeMsg MOBILE_NOT_EXIST = new CodeMsg(500103,"手机号不存在");
    public static CodeMsg PASSWORD_ERROR = new CodeMsg(500104,"密码错误");


    /***********登录模块异常end****************/

    /*************秒杀模块 500500***************/
    public static CodeMsg MIAO_SHA_OVER = new CodeMsg(500500,"秒杀已经完毕");
    public static CodeMsg MIAO_SHA_REPEAT = new CodeMsg(500501,"不能重复秒杀");
    /*************秒杀模块end***************/
    public CodeMsg() {
    }

    public CodeMsg(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public CodeMsg fillArgs(Object...args){
        int code = this.code;
        String message = String.format(this.msg, args);
        return new CodeMsg(code,message);
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

}
