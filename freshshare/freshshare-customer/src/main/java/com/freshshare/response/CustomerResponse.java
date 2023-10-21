package com.freshshare.response;

public class CustomerResponse extends ResponseObj{
    public CustomerResponse(CustomerResponseEnum customerResponseEnum) {
        super(customerResponseEnum.getCode(), customerResponseEnum.getMsg());
    }

    public CustomerResponse(CustomerResponseEnum customerResponseEnum, Object data) {
        super(customerResponseEnum.getCode(), customerResponseEnum.getMsg(), data);
    }

    public static CustomerResponse success(CustomerResponseEnum customerResponseEnum){
        return new CustomerResponse(customerResponseEnum,null);
    }

    public static CustomerResponse success(CustomerResponseEnum customerResponseEnum, Object data){
        return new CustomerResponse(customerResponseEnum, data);
    }
}
