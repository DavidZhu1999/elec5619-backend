package com.freshshare.response;

public enum ReviewResponseEnum implements RespEnum{
    GET_REVIEW_BY_ORDER_ID(2000001, "get one customer success"),
    GET_REVIEWS_BY_BUSINESS_ID(2000002,"get all customer success" ),
    GET_SEARCHED_CUSTOMER_SUCCESS(2000003, "get searched customer success" ),

    UPDATE_CUSTOMER_STATUS_SUCCESS(2000004, "update customer status success"), ADD_REVIEW(2000005, "add successfully");


    private String msg;

    private int code;

    ReviewResponseEnum(int code, String msg){
        this.code = code;
        this.msg = msg;
    }

    ReviewResponseEnum(){

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
