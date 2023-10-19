package com.freshshare.customer.service;

import com.baomidou.mybatisplus.extension.service.IService;

import com.freshshare.customer.request.CustomerLogInRequestParam;
import com.freshshare.customer.request.CustomerSignUpRequestParam;
import com.freshshare.entity.Customer;

import java.util.Map;

public interface CustomerService extends IService<Customer> {

    void signUp(CustomerSignUpRequestParam param);

    Map<Object,Object> logIn(CustomerLogInRequestParam param);

    void logOut();

}
