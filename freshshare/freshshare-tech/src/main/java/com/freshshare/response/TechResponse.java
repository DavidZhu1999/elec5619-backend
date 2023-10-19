package com.freshshare.response;

public class TechResponse extends ResponseObj{
    public TechResponse(TechResponseEnum techResponseEnum){
        super(techResponseEnum.getCode(), techResponseEnum.getMsg());
    }

    public TechResponse(TechResponseEnum techResponseEnum, Object data){
        super(techResponseEnum.getCode(), techResponseEnum.getMsg(), data);

    }

    public static TechResponse success(TechResponseEnum techResponseEnum){
        return new TechResponse(techResponseEnum,null);
    }

    public static TechResponse success(TechResponseEnum techResponseEnum, Object data){
        return new TechResponse(techResponseEnum, data);
    }
}
