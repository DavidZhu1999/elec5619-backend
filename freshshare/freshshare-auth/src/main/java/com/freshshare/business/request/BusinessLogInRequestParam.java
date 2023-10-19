package com.freshshare.business.request;

import lombok.Data;

@Data
public class BusinessLogInRequestParam {

    private String businessUsername;

    private String businessPassword;

    private Boolean rememberMe;
}
