package com.rudy.miaosha.exception;

import com.rudy.miaosha.result.CodeMsg;
import com.rudy.miaosha.result.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@ControllerAdvice
@ResponseBody
public class GlobleExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @ExceptionHandler(value = Exception.class)
    public Result<String> exceptionHandler(Exception e) {

        logger.info("全局异常处理:{}",e);
        if (e instanceof BindException){
            BindException ex = (BindException)e;
            List<ObjectError> allErrors = ex.getAllErrors();
            ObjectError objectError = allErrors.get(0);
            String msg = objectError.getDefaultMessage();
            return Result.error(CodeMsg.BIND_ERROR.fillArgs(msg));
        }else if (e instanceof GlobleException){
            GlobleException globleException = (GlobleException)e;
            CodeMsg cm = globleException.getCm();
            return Result.error(cm);
        }else {
            return Result.error(CodeMsg.SYSTEM_ERROR);
        }

    }
}
