package com.freshshare.response;

public enum CustomerResponseEnum implements RespEnum {
    VIEW_SHOPS_SUCCESS(100000, "View shops success"),
    VIEW_SHOP_ITEMS_SUCCESS(100010,"view shop items success" ),
    ADD_ORDER_SUCCESS(100020, "add order success"),
    GET_ORDERS_SUCCESS(100030,"get order list successfully" ),
    GET_ORDER_DETAIL_SUCCESS(100040,"get order detail success" ),
    GET_SHOP_ADDRESS_SUCCESS(100050, "get shop address success"),
    GET_ORDER_ADDRESS_SUCCESS(100060, "get order address success"),
    CANCEL_ORDER_SUCCESS(100070,  "cancel order success"),
    COMPLETE_ORDER_SUCCESS(100071, "complete order success");

    private String msg;

    private int code;

    CustomerResponseEnum(int code, String msg) {
        this.msg = msg;
        this.code = code;
    }

    CustomerResponseEnum() {

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