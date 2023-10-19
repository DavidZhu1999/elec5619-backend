package com.freshshare.customer.response;

import com.freshshare.response.RespEnum;

public enum ManageCustomerResponseEnum implements RespEnum {
    GET_ONE_CUSTOMER_SUCCESS(1000001, "get one customer success"),
    GET_ALL_CUSTOMER_SUCCESS(1000002,"get all customer success" ),
    GET_SEARCHED_CUSTOMER_SUCCESS(1000003, "get searched customer success" ),

    UPDATE_CUSTOMER_STATUS_SUCCESS(1000004, "update customer status success");


    private String msg;

    private int code;

    ManageCustomerResponseEnum(int code, String msg){
        this.code = code;
        this.msg = msg;
    }

    ManageCustomerResponseEnum(){

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
