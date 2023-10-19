package com.freshshare.customer.response;

import com.freshshare.response.RespEnum;

public enum CustomerResponseEnum implements RespEnum {
    SIGNUP_SUCCESS(100000, "Sign up successfully"),

    LOGIN_SUCCESS(100010, "Login successfully"),

    LOGOUT_SUCCESS(100020, "Logout successfully");


    private String msg;

    private int code;

    CustomerResponseEnum(int code, String msg){
        this.code = code;
        this.msg = msg;
    }

    CustomerResponseEnum(){

    }

    @Override
    public String getMsg() {
        return this.msg;
    }

    @Override
    public int getCode() {
        return this.code;
    }
}
