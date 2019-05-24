package com.rudy.miaosha.result;

import com.rudy.miaosha.enumfile.Enums;

import java.io.Serializable;

/**
 * 前端交互的实体类
 *
 * @param <T>
 */
public class Result<T> implements Serializable, Cloneable {
    private int code;
    private String msg;
    private T data;

    public Result(T data) {
        this.code = Enums.SUCCESS.getCode();
        this.msg = Enums.SUCCESS.getMessage();
        this.data = data;
    }

    public Result(CodeMsg codeMsg) {
        this.code = codeMsg.getCode();
        this.msg = codeMsg.getMsg();
        this.data = null;
    }

    public static <T> Result<T> success(T data) {
        return new Result<>(data);
    }

    public static <T> Result<T> error(CodeMsg codeMsg) {
        if (codeMsg == null) {
            return null;
        }
        return new Result<T>(codeMsg);
    }

    public int getCode() {
        return code;
    }


    public String getMsg() {
        return msg;
    }


    public T getData() {
        return data;
    }


    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
