package com.freshshare.business.response;

import com.freshshare.response.ResponseObj;

public class ManageBusinessResponse extends ResponseObj {
    public ManageBusinessResponse(ManageBusinessResponseEnum manageBusinessResponseEnum){
        super(manageBusinessResponseEnum.getCode(), manageBusinessResponseEnum.getMsg());
    }

    public ManageBusinessResponse(ManageBusinessResponseEnum manageBusinessResponseEnum, Object data){
        super(manageBusinessResponseEnum.getCode(), manageBusinessResponseEnum.getMsg(), data);

    }

    public static ManageBusinessResponse success(ManageBusinessResponseEnum manageBusinessResponseEnum){
        return new ManageBusinessResponse(manageBusinessResponseEnum,null);
    }

    public static ManageBusinessResponse success(ManageBusinessResponseEnum manageBusinessResponseEnum, Object data){
        return new ManageBusinessResponse(manageBusinessResponseEnum, data);
    }
}
