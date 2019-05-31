package com.rudy.miaosha.exception;

import com.rudy.miaosha.result.CodeMsg;

public class GlobleException extends RuntimeException {
    private CodeMsg cm;

    public GlobleException(CodeMsg cm){
        super(cm.toString());
        this.cm = cm;
    }

    public CodeMsg getCm() {
        return cm;
    }
}
