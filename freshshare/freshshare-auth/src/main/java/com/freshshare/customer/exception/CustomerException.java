package com.freshshare.customer.exception;

import com.freshshare.exception.BaseException;

public class CustomerException extends BaseException {
    private String msg;
    private int code;

    public CustomerException(CustomerExceptionEnum signUpExceptionEnum) {
        super(signUpExceptionEnum.getCode(),signUpExceptionEnum.getMsg());
    }

    public CustomerException(int code, String msg){
        super(code, msg);
    }


}
