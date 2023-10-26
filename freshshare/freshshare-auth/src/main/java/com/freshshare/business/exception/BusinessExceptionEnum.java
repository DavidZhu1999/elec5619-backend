package com.freshshare.business.exception;

/**
 * This is the exception enum for business auth
 */
public enum BusinessExceptionEnum {

    SIGN_UP_EXIST_USERNAME(100001, "Username already exists"),

    SIGN_UP_EXIST_EMAIL(100002, "Email already exists"),

    LOG_IN_ERROR_NOT_MATCH(100011, "Username or password is incorrect"),

    LOG_IN_ERROR(100012, "Login error"),

    LOG_IN_ERROR_NOT_ACCOUNT(100013, "Account does not exist"),

    LOG_IN_ERROR_ACCOUNT_LOCKED(100014, "Account is locked, please contact 0435111888"),

    LOG_OUT_ERROR(100021, "Log out error");

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
