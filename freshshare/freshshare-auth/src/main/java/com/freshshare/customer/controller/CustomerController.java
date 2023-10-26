package com.freshshare.customer.controller;

import cn.dev33.satoken.annotation.SaCheckRole;
import com.freshshare.customer.request.CustomerLogInRequestParam;
import com.freshshare.customer.request.CustomerSignUpRequestParam;
import com.freshshare.customer.response.CustomerResponse;
import com.freshshare.customer.response.CustomerResponseEnum;
import com.freshshare.customer.service.CustomerService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * This is the controller for customer auth
 */
@Slf4j
@RestController
@RequestMapping("/auth/customer")
@CrossOrigin
public class CustomerController {

    @Resource
    private CustomerService customerService;

    /**
     * This is the sign up method for customer
     * @param param
     * @return
     */
    @PostMapping("/signUp")
    public CustomerResponse signUp(@RequestBody CustomerSignUpRequestParam param) {
        customerService.signUp(param);
        return CustomerResponse.success(CustomerResponseEnum.SIGNUP_SUCCESS);
    }

    /**
     * This is the log in method for customer
     * @param param
     * @return
     */
    @PostMapping("/logIn")
    public CustomerResponse logIn(@RequestBody CustomerLogInRequestParam param) {
        System.out.println(param.getCustomerUsername());
        System.out.println(param.getCustomerPassword());
        return CustomerResponse.success(CustomerResponseEnum.LOGIN_SUCCESS,customerService.logIn(param));
    }


    /**
     * This is the log out method for customer
     * @return
     */
    @SaCheckRole(type = "customer", value = {"customer"})
    @PostMapping("/logOut")
    public CustomerResponse logOut() {
        customerService.logOut();
        return CustomerResponse.success(CustomerResponseEnum.LOGOUT_SUCCESS);
    }

}
