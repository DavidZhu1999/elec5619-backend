package com.freshshare.exception;


public enum ReviewExceptionEnum {
    GET_ONE_REVIEW_ERROR(1000011, "get one business error"), ADD_REVIEW_ERROR(1000012,"add error" );

    private String msg;

    private int code;


    ReviewExceptionEnum(int code, String msg){
        this.code = code;
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public int getCode() {
        return code;
    }
}
