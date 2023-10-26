package com.freshshare.business.request;

import lombok.Data;

/**
 * This is the request param for business log in
 */
@Data
public class BusinessLogInRequestParam {

    private String businessUsername;

    private String businessPassword;

    private Boolean rememberMe;
}
