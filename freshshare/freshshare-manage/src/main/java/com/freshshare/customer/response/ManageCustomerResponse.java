package com.freshshare.customer.response;

import com.freshshare.response.ResponseObj;

public class ManageCustomerResponse extends ResponseObj {
    public ManageCustomerResponse(ManageCustomerResponseEnum manageCustomerResponseEnum){
        super(manageCustomerResponseEnum.getCode(), manageCustomerResponseEnum.getMsg());
    }

    public ManageCustomerResponse(ManageCustomerResponseEnum manageCustomerResponseEnum, Object data){
        super(manageCustomerResponseEnum.getCode(), manageCustomerResponseEnum.getMsg(), data);

    }

    public static ManageCustomerResponse success(ManageCustomerResponseEnum manageCustomerResponseEnum){
        return new ManageCustomerResponse(manageCustomerResponseEnum,null);
    }

    public static ManageCustomerResponse success(ManageCustomerResponseEnum manageCustomerResponseEnum, Object data){
        return new ManageCustomerResponse(manageCustomerResponseEnum, data);
    }
}
