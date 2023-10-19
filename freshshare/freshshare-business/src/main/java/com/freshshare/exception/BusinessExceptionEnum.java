package com.freshshare.exception;

public enum BusinessExceptionEnum {
    ADD_COMMODITY_ERROR(100001,"Add commodity error"),
    GET_COMMODITY_LIST_ERROR(100011,"get commodity list error" ),
    UPDATE_COMMODITY_ERROR(100021, "update commodity error" ),
    UPDATE_ORDER_ERROR(100031, "update order error"),
    GET_ORDER_LIST_ERROR(100041, "get order list error"),

    OPEN_STORE_ERROR(110001, "open store error"),
    CLOSE_STORE_ERROR(120001, "close store error");
    private String msg;

    private int code;


    BusinessExceptionEnum(int code, String msg){
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
