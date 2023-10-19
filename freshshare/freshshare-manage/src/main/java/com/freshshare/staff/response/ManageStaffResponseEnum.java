package com.freshshare.staff.response;

import com.freshshare.response.RespEnum;

public enum ManageStaffResponseEnum implements RespEnum {

    GET_ONE_STAFF_SUCCESS(1000001, "get one staff success"),
    GET_ALL_STAFF_SUCCESS(1000002,"get all staff success" ),
    GET_SEARCHED_STAFF_SUCCESS(1000003, "get searched staff success" ),

    UPDATE_STAFF_STATUS_SUCCESS(1000004, "update staff status success");


    private String msg;

    private int code;

    ManageStaffResponseEnum(int code, String msg){
        this.code = code;
        this.msg = msg;
    }

    ManageStaffResponseEnum(){

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
