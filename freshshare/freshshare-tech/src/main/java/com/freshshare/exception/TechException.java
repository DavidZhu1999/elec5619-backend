package com.freshshare.exception;

public class TechException extends BaseException{

    private String msg;

    private int code;


    public TechException(TechExceptionEnum techExceptionEnum) {
        super(techExceptionEnum.getCode(),techExceptionEnum.getMsg());
    }

    public TechException(int code, String msg) {
        super(code, msg);
    }
}
