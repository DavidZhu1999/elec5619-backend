package com.freshshare.staff.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.freshshare.entity.Staff;
import com.freshshare.staff.request.StaffLogInRequestParam;
import com.freshshare.staff.request.StaffSignUpRequestParam;


import java.util.Map;

public interface StaffService extends IService<Staff> {

    void signUp(StaffSignUpRequestParam param);

    Map<Object,Object> logIn(StaffLogInRequestParam param);

    void logOut();
}
