package com.freshshare.staff.controller;

import cn.dev33.satoken.annotation.SaCheckRole;
import com.freshshare.staff.request.StaffLogInRequestParam;
import com.freshshare.staff.request.StaffSignUpRequestParam;
import com.freshshare.staff.response.StaffResponse;
import com.freshshare.staff.response.StaffResponseEnum;
import com.freshshare.staff.service.StaffService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

/**
 * This is the controller for staff
 */
@RestController
@RequestMapping("/auth/staff")
@CrossOrigin
public class StaffController {

    @Resource
    private StaffService staffService;

    /**
     * this is signup method for staff
     * @param param
     * @return
     */
    @PostMapping("/signUp")
    public StaffResponse signUp(@RequestBody StaffSignUpRequestParam param){
        staffService.signUp(param);
        return StaffResponse.success(StaffResponseEnum.SIGNUP_SUCCESS);
    }

    @PostMapping("/logIn")
    public StaffResponse logIn(@RequestBody StaffLogInRequestParam param){
        return StaffResponse.success(StaffResponseEnum.LOGIN_SUCCESS,staffService.logIn(param));
    }

    @SaCheckRole(type = "staff",value = {"staff"})
    @PostMapping("/logOut")
    public StaffResponse logOut(){
        staffService.logOut();
        return StaffResponse.success(StaffResponseEnum.LOGOUT_SUCCESS);
    }

}
