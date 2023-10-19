package com.freshshare.business.exception;

import com.freshshare.exception.BaseException;

public class ManageBusinessException extends BaseException {

    private String msg;

    private int code;


    public ManageBusinessException(ManageBusinessExceptionEnum manageBusinessExceptionEnum) {
        super(manageBusinessExceptionEnum.getCode(),manageBusinessExceptionEnum.getMsg());
    }

    public ManageBusinessException(int code, String msg) {
        super(code, msg);
    }
}
