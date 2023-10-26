package com.freshshare.staff.request;

import lombok.Data;

/**
 * This is the request param for staff log in
 */
@Data
public class StaffLogInRequestParam {

    private String staffUsername;

    private String staffPassword;

    private String rememberMe;

}
