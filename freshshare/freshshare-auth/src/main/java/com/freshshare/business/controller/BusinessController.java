package com.freshshare.business.controller;

import cn.dev33.satoken.annotation.SaCheckRole;
import com.freshshare.business.request.BusinessLogInRequestParam;
import com.freshshare.business.request.BusinessSignUpRequestParam;
import com.freshshare.business.response.BusinessResponse;
import com.freshshare.business.response.BusinessResponseEnum;
import com.freshshare.business.service.BusinessService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

/**
 * This is the controller for business auth
 */

@RestController
@RequestMapping("/auth/business")
@CrossOrigin
public class BusinessController {

    @Resource
    private BusinessService businessService;


    /**
     * This is the sign up method for business
     * @param param
     * @return BusinessResponse
     */
    @PostMapping("/signUp")
    public BusinessResponse signUp(@RequestBody BusinessSignUpRequestParam param) {
        businessService.signUp(param);
        return BusinessResponse.success(BusinessResponseEnum.SIGNUP_SUCCESS);
    }

    /**
     * This is the log in method for business
     * @param param
     * @return
     */
    @PostMapping("/logIn")
    public BusinessResponse logIn(@RequestBody BusinessLogInRequestParam param) {
        return BusinessResponse.success(BusinessResponseEnum.LOGIN_SUCCESS,businessService.logIn(param));
    }

    /**
     * This is the log out method for business
     * need to take the token from the header or the param
     * @return
     */
    @SaCheckRole(type = "business", value = {"business"})
    @PostMapping("/logOut")
    public BusinessResponse logOut() {
        businessService.logOut();
        return BusinessResponse.success(BusinessResponseEnum.LOGOUT_SUCCESS);
    }
}
