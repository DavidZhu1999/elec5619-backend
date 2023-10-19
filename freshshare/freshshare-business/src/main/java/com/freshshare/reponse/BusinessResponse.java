package com.freshshare.reponse;

import com.freshshare.response.ResponseObj;

public class BusinessResponse extends ResponseObj {

    public BusinessResponse(BusinessResponseEnum businessResponseEnum) {
        super(businessResponseEnum.getCode(), businessResponseEnum.getMsg());
    }

    public BusinessResponse(BusinessResponseEnum businessResponseEnum, Object data) {
        super(businessResponseEnum.getCode(), businessResponseEnum.getMsg(), data);
    }

    public static BusinessResponse success(BusinessResponseEnum businessResponseEnum){
        return new BusinessResponse(businessResponseEnum,null);
    }

    public static BusinessResponse success(BusinessResponseEnum businessResponseEnum, Object data){
        return new BusinessResponse(businessResponseEnum, data);
    }
}
