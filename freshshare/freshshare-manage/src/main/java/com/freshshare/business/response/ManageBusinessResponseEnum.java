package com.freshshare.business.response;

import com.freshshare.response.RespEnum;

public enum ManageBusinessResponseEnum implements RespEnum {
    GET_ONE_BUSINESS_SUCCESS(1000001, "get one business success"),
    GET_ALL_BUSINESS_SUCCESS(1000002,"get all business success" ),
    GET_SEARCHED_BUSINESS_SUCCESS(1000003, "get searched business success" ),

    UPDATE_BUSINESS_STATUS_SUCCESS(1000004, "update business status success");


    private String msg;

    private int code;

    ManageBusinessResponseEnum(int code, String msg){
        this.code = code;
        this.msg = msg;
    }

    ManageBusinessResponseEnum(){

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
