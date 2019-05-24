package com.rudy.miaosha.enumfile;

public enum Enums {
    SUCCESS(0,"成功"),
    ERROR(99999,"失败")
    ;
    private int code;
    private String message;

    Enums(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
