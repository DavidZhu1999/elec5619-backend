package com.freshshare.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.freshshare.entity.Customer;
import com.freshshare.request.UpdateProfileRequest;

import java.util.Map;

public interface CustomerProfileService extends IService<Customer> {

    void updateCustomer(UpdateProfileRequest updateProfileRequest);

    Map<Object,Object> getCustomerProfile(String customerId);
}
