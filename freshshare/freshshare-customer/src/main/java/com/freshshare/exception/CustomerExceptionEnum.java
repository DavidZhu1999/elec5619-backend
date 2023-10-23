package com.freshshare.exception;

public enum CustomerExceptionEnum {
    UPDATE_COMMODITY_ERROR(999999, "update order error"), VIEW_SHOP_ERROR(999999,"view shop error" );

    private String msg;

    private int code;


    CustomerExceptionEnum(int code, String msg){
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
