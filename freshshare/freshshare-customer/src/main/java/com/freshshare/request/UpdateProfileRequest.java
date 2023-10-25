package com.freshshare.request;

import lombok.Data;

@Data
public class UpdateProfileRequest {

    private String customerId;

    private String firstName;

    private String lastName;

    private String phone;
}
