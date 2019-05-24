package com.rudy.miaosha.result;

public class CodeMsg {
    private int code;
    private String msg;

    /*********通用异常*********/
    public static CodeMsg SYSTEM_ERROR = new CodeMsg(99999,"系统异常");
    public static CodeMsg SUCCESS = new CodeMsg(0,"成功");
    /*********通用异常end*********/


    /***********登录模块异常 5002xx****************/

    /***********登录模块异常end****************/

    public CodeMsg() {
    }

    public CodeMsg(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

}
