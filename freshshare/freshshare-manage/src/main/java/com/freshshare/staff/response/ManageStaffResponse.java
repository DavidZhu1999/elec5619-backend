package com.freshshare.staff.response;


import com.freshshare.response.ResponseObj;

public class ManageStaffResponse extends ResponseObj {
    public ManageStaffResponse(ManageStaffResponseEnum manageStaffResponseEnum){
        super(manageStaffResponseEnum.getCode(), manageStaffResponseEnum.getMsg());
    }

    public ManageStaffResponse(ManageStaffResponseEnum manageStaffResponseEnum, Object data){
        super(manageStaffResponseEnum.getCode(), manageStaffResponseEnum.getMsg(), data);

    }

    public static ManageStaffResponse success(ManageStaffResponseEnum manageStaffResponseEnum){
        return new ManageStaffResponse(manageStaffResponseEnum,null);
    }

    public static ManageStaffResponse success(ManageStaffResponseEnum manageStaffResponseEnum, Object data){
        return new ManageStaffResponse(manageStaffResponseEnum, data);
    }
}
