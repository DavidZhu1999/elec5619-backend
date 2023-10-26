package com.freshshare.staff.response;

import com.freshshare.response.RespEnum;

/**
 * This is the response enum for staff
 */
public enum StaffResponseEnum implements RespEnum {
    SIGNUP_SUCCESS(100000, "Sign up successfully"),

    LOGIN_SUCCESS(100010, "Login successfully"),

    LOGOUT_SUCCESS(100020, "Logout successfully");


    private String msg;

    private int code;

    StaffResponseEnum(int code, String msg){
        this.code = code;
        this.msg = msg;
    }

    StaffResponseEnum(){

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
