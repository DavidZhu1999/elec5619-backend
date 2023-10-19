package com.freshshare.customer.exception;

import com.freshshare.business.exception.ManageBusinessExceptionEnum;
import com.freshshare.exception.BaseException;

public class ManageCustomerException extends BaseException {

    private String msg;

    private int code;

    public ManageCustomerException(ManageCustomerExceptionEnum manageCustomerExceptionEnum) {
        super(manageCustomerExceptionEnum.getCode(),manageCustomerExceptionEnum.getMsg());
    }

    public ManageCustomerException(int code, String msg) {
        super(code, msg);
    }
}
