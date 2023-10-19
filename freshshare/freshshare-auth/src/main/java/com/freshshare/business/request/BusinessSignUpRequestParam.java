package com.freshshare.business.request;

import lombok.Data;

@Data
public class BusinessSignUpRequestParam {

    private String businessUsername;

    private String businessEmail;

    private String businessPassword;

    private String businessShopname;

    private String businessPhone;

    private String businessAddress;

    private String businessPostcode;

    private String businessState;

}
