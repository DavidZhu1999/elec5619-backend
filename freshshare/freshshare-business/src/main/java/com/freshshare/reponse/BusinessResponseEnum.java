package com.freshshare.reponse;

import com.freshshare.response.RespEnum;

public enum BusinessResponseEnum implements RespEnum {
    ADD_SUCCESS(100000, "Add commodity successfully"),
    GET_COMMODITY_LIST_SUCCESS(100010, "get commodity list successfully"),
    UPDATE_COMMODITY_SUCCESS(100020, "update commodity successfully"),
    UPDATE_ORDER_SUCCESS(100030,"update order success" ),
    GET_ORDER_LIST_SUCCESS(100040, "get order list success"),
    GET_ONE_ORDER_SUCCESS(100050, "get one order success"),
    OPEN_STORE_SUCCESS(110000, "open store success"),
    CLOSE_STORE_SUCCESS(120000, "close store success");


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
