package com.freshshare.staff.response;

import com.freshshare.response.ResponseObj;

/**
 * This is the response for staff
 */
public class StaffResponse extends ResponseObj {

    public StaffResponse(StaffResponseEnum staffResponseEnum){
        super(staffResponseEnum.getCode(), staffResponseEnum.getMsg());
    }

    public StaffResponse(StaffResponseEnum staffResponseEnum, Object data){
        super(staffResponseEnum.getCode(), staffResponseEnum.getMsg(), data);

    }

    public static StaffResponse success(StaffResponseEnum staffResponseEnum){
        return new StaffResponse(staffResponseEnum,null);
    }

    public static StaffResponse success(StaffResponseEnum staffResponseEnum, Object data){
        return new StaffResponse(staffResponseEnum, data);
    }
}
