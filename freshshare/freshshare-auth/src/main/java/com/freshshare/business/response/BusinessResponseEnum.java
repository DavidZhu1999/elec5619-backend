package com.freshshare.business.response;

import com.freshshare.response.RespEnum;

public enum BusinessResponseEnum implements RespEnum {
    SIGNUP_SUCCESS(100000, "Sign up successfully"),

    LOGIN_SUCCESS(100010, "Login successfully"),

    LOGOUT_SUCCESS(100020, "Logout successfully");


    private String msg;

    private int code;

    BusinessResponseEnum(int code, String msg){
        this.code = code;
        this.msg = msg;
    }

    BusinessResponseEnum(){

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
