package com.freshshare.controller.Dto;

import lombok.Data;

@Data
public class ChangePassword {
    String business_id;
    String oldPassword;
    String newPassword;
    String oldName;
    String newName;

}
