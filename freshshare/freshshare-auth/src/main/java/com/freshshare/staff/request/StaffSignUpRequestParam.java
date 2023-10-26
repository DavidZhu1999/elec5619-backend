package com.freshshare.staff.request;

import lombok.Data;

/**
 * This is the request param for staff sign up
 */
@Data
public class StaffSignUpRequestParam {

    private String staffUsername;

    private String staffPassword;

    private String staffEmail;

    private String staffPhone;

    private String staffFirstname;

    private String staffLastname;

}
