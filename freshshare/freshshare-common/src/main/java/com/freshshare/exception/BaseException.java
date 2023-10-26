package com.freshshare.exception;

/**
 * This is the base exception extend runtime exception, if the exception is type of base exception, it will be caught by global exception handler
 */
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
