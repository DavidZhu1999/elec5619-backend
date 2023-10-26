package com.freshshare.customer.request;

import lombok.Data;

/**
 * This is the request param for customer log in
 */
@Data
public class CustomerLogInRequestParam {

    private String customerUsername;

    private String customerPassword;

    private Boolean rememberMe;
}
