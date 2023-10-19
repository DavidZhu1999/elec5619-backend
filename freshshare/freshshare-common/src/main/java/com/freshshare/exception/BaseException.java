package com.freshshare.exception;

public class BaseException extends RuntimeException{

    private int code;

    public BaseException(int code, String msg){
        super(msg);
        this.code = code;
    }

    public BaseException(String msg) {
        super(msg);
    }

    public int getCode() {
        return code;
    }

}
