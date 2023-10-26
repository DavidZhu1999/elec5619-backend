package com.freshshare.customer.exception;

import com.freshshare.exception.BaseException;

/**
 * This is the exception for business auth
 */
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
