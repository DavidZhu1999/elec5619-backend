package com.freshshare.staff.exception;

import com.freshshare.exception.BaseException;

public class ManageStaffException extends BaseException {

    private String msg;

    private int code;

    public ManageStaffException(ManageStaffExceptionEnum manageStaffExceptionEnum) {
        super(manageStaffExceptionEnum.getCode(),manageStaffExceptionEnum.getMsg());
    }

    public ManageStaffException(int code, String msg) {
        super(code, msg);
    }
}
