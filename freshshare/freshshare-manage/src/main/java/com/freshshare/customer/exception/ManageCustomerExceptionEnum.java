package com.freshshare.customer.exception;

public enum ManageCustomerExceptionEnum {
    GET_ONE_CUSTOMER_ERROR(1000011, "get one customer error"),
    UPDATE_CUSTOMER_STATUS_ERROR_NOTEXIST(1000021, "update customer status error, customerId not exist"),
    UPDATE_CUSTOMER_STATUS_ERROR(1000022, "update customer status error");

    private String msg;

    private int code;


    ManageCustomerExceptionEnum(int code, String msg){
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
