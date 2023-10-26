package com.freshshare.staff.exception;

import com.freshshare.exception.BaseException;

/**
 * This is the exception for staff
 */
public class StaffException extends BaseException {
    private String msg;

    private int code;

    public StaffException(StaffExceptionEnum signUpExceptionEnum) {
        super(signUpExceptionEnum.getCode(),signUpExceptionEnum.getMsg());
    }

    public StaffException(int code, String msg){
        super(code, msg);
    }
}
