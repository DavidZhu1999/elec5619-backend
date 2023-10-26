package com.freshshare.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.freshshare.entity.Customer;
import com.freshshare.exception.CustomerException;
import com.freshshare.mapper.CustomerProfileMapper;
import com.freshshare.request.UpdateProfileRequest;
import com.freshshare.service.CustomerProfileService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: CustomerProfileServiceImpl
 */
@Service
public class CustomerProfileServiceImpl extends ServiceImpl<CustomerProfileMapper, Customer> implements CustomerProfileService {
    @Override
    public void updateCustomer(UpdateProfileRequest updateProfileRequest) {
        Customer customer = this.getById(updateProfileRequest.getCustomerId());
        customer.setCustomerFirstname(updateProfileRequest.getFirstName());
        customer.setCustomerLastname(updateProfileRequest.getLastName());
        customer.setCustomerPhone(updateProfileRequest.getPhone());

        try {
            this.updateById(customer);
        } catch (Exception e) {
            throw new CustomerException(999999,"Update customer profile failed");
        }
    }

    @Override
    public Map<Object, Object> getCustomerProfile(String customerId) {
        Customer customer = this.getById(customerId);
        Map<Object,Object> result = new HashMap<>();
        result.put("customer",customer);
        return result;
    }
}
