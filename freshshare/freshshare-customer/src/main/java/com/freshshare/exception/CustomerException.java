package com.freshshare.exception;

public class CustomerException extends BaseException{
    private String msg;
    private int code;

    public CustomerException(CustomerExceptionEnum customerExceptionEnum) {
        super(customerExceptionEnum.getCode(),customerExceptionEnum.getMsg());
    }

    public CustomerException(int code, String msg){
        super(code, msg);
    }
}
