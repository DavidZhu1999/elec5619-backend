package com.freshshare.staff.exception;

public enum ManageStaffExceptionEnum {
    GET_ONE_STAFF_ERROR(1000011, "get one staff error"),
    UPDATE_STAFF_STATUS_ERROR_NOTEXIST(1000021, "update staff status error, staffId not exist"),
    UPDATE_STAFF_STATUS_ERROR(1000022, "update staff status error");

    private String msg;

    private int code;


    ManageStaffExceptionEnum(int code, String msg){
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
