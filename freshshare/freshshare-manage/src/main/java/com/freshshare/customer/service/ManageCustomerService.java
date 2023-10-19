package com.freshshare.customer.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.freshshare.entity.Customer;

import java.util.Map;

public interface ManageCustomerService extends IService<Customer> {
    Map<Object,Object> getAllCustomers();

    Map<Object, Object> getOneCustomer(String businessId);

    Map<Object, Object> getSearchedCustomers(String firstname, String lastname);

    void updateCustomerStatus(String businessId, String businessStatus);
}
