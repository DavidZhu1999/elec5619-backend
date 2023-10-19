package com.freshshare.customer.request;

import lombok.Data;

@Data
public class updateCustomerStatusRequestParam {

    private String satokenStaff;

    private String customerId;

    private String customerStatus;
}
