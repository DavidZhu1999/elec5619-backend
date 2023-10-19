package com.freshshare.customer.request;

import lombok.Data;

@Data
public class CustomerLogInRequestParam {

    private String customerUsername;

    private String customerPassword;

    private Boolean rememberMe;
}
