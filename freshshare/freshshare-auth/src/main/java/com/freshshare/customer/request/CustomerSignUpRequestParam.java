package com.freshshare.customer.request;

import lombok.Data;

/**
 * This is the request param for customer log in
 */
@Data
public class CustomerSignUpRequestParam {
    private String customerUsername;

    private String customerEmail;

    private String customerPassword;

    private String customerFirstname;

    private String customerLastname;

    private String customerPhone;

    private String customerAddress;

    private String customerPostcode;

}
