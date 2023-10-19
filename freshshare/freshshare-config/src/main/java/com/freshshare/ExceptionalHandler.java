package com.freshshare;

import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.util.SaResult;
import com.freshshare.exception.BaseException;
import com.freshshare.response.ResponseObj;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionalHandler {

    @ExceptionHandler(BaseException.class)
    public ResponseObj catchBaseException(BaseException exception){
        return new ResponseObj(exception.getCode(),exception.getMessage());
    }

    // 全局异常拦截（拦截项目中的NotLoginException异常）
    @ExceptionHandler(NotLoginException.class)
    public SaResult handlerNotLoginException(NotLoginException nle)
            throws Exception {

        // 打印堆栈，以供调试
        nle.printStackTrace();

        // 判断场景值，定制化异常信息
        String message = "";
        if(nle.getType().equals(NotLoginException.NOT_TOKEN)) {
            message = "Unable to read a valid token";
        } else if(nle.getType().equals(NotLoginException.INVALID_TOKEN)) {
            message = "Token is invalid";
        } else if(nle.getType().equals(NotLoginException.TOKEN_TIMEOUT)) {
            message = "Token has expired";
        } else if(nle.getType().equals(NotLoginException.BE_REPLACED)) {
            message = "Token has been logged out (or replaced)";
        } else if(nle.getType().equals(NotLoginException.KICK_OUT)) {
            message = "Token has been kicked offline";
        } else if(nle.getType().equals(NotLoginException.TOKEN_FREEZE)) {
            message = "Token has been frozen";
        } else if(nle.getType().equals(NotLoginException.NO_PREFIX)) {
            message = "The token was not submitted with the specified prefix";
        } else {
            message = "The current session is not logged in";
        }


        // 返回给前端
        return SaResult.error(message);
    }


}
