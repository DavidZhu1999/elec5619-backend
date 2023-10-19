package com.freshshare.exception;

public class ReviewException extends BaseException{
    private String msg;

    private int code;


    public ReviewException(ReviewExceptionEnum reviewExceptionEnum) {
        super(reviewExceptionEnum.getCode(),reviewExceptionEnum.getMsg());
    }

    public ReviewException(int code, String msg) {
        super(code, msg);
    }
}
