package com.freshshare.business.exception;

public enum ManageBusinessExceptionEnum {
    GET_ONE_BUSINESS_ERROR(1000011, "get one business error"),
    UPDATE_BUSINESS_STATUS_ERROR_NOTEXIST(1000021, "update business status error, businessId not exist"),
    UPDATE_BUSINESS_STATUS_ERROR(1000022, "update business status error");

    private String msg;

    private int code;


    ManageBusinessExceptionEnum(int code, String msg){
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
