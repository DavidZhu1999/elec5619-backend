package com.freshshare.business.controller;

import cn.dev33.satoken.annotation.SaCheckRole;
import com.freshshare.business.request.BusinessLogInRequestParam;
import com.freshshare.business.request.BusinessSignUpRequestParam;
import com.freshshare.business.response.BusinessResponse;
import com.freshshare.business.response.BusinessResponseEnum;
import com.freshshare.business.service.BusinessService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth/business")
@CrossOrigin
public class BusinessController {

    @Resource
    private BusinessService businessService;

    @PostMapping("/signUp")
    public BusinessResponse signUp(@RequestBody BusinessSignUpRequestParam param) {
        businessService.signUp(param);
        return BusinessResponse.success(BusinessResponseEnum.SIGNUP_SUCCESS);
    }

    @PostMapping("/logIn")
    public BusinessResponse logIn(@RequestBody BusinessLogInRequestParam param) {
        return BusinessResponse.success(BusinessResponseEnum.LOGIN_SUCCESS,businessService.logIn(param));
    }

    @SaCheckRole(type = "business", value = {"business"})
    @PostMapping("/logOut")
    public BusinessResponse logOut() {
        businessService.logOut();
        return BusinessResponse.success(BusinessResponseEnum.LOGOUT_SUCCESS);
    }
}
