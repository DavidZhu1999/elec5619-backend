package com.freshshare.response;

public enum TechResponseEnum implements RespEnum{
    GET_TECH_BY_ORDER_ID(2000001, "get one customer success"),
    GET_TECHS_BY_BUSINESS_ID(2000002,"get all customer success" ),
    GET_SEARCHED_CUSTOMER_SUCCESS(2000003, "get searched customer success" ),

    UPDATE_CUSTOMER_STATUS_SUCCESS(2000004, "update customer status success"),
    ADD_TECH(2000005, "add successfully"),
    GET_ALL_ISSUES(200006, "get all successfully"),
    UPDATE_ISSUE_STATUS_SUCCESS(200007, "update issue status successfully"),
    GET_TODO(200008,"get all todo issues" ),
    GET_DEALING(200009,"get dealing issues" ),
    GET_FINISH(200010,"get finish issues" );


    private String msg;

    private int code;

    TechResponseEnum(int code, String msg){
        this.code = code;
        this.msg = msg;
    }

    TechResponseEnum(){

    }

    @Override
    public String getMsg() {
        return this.msg;
    }

    @Override
    public int getCode() {
        return this.code;
    }
}
