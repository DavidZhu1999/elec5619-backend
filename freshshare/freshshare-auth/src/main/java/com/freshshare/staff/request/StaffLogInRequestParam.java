package com.freshshare.staff.request;

import lombok.Data;

@Data
public class StaffLogInRequestParam {

    private String staffUsername;

    private String staffPassword;

    private String rememberMe;

}
