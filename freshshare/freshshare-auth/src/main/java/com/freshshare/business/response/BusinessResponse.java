package com.freshshare.business.response;

import com.freshshare.response.ResponseObj;

/**
 * This is the response for business auth
 */
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
