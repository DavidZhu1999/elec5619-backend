package com.freshshare.business.exception;

import com.freshshare.exception.BaseException;

public class BusinessException extends BaseException {
    private String msg;
    private int code;

    public BusinessException(BusinessExceptionEnum signUpExceptionEnum) {
        super(signUpExceptionEnum.getCode(),signUpExceptionEnum.getMsg());
    }

    public BusinessException(int code, String msg){
        super(code, msg);
    }


}
