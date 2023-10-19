package com.freshshare.customer.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.freshshare.customer.exception.ManageCustomerException;
import com.freshshare.customer.exception.ManageCustomerExceptionEnum;
import com.freshshare.customer.mapper.ManageCustomerMapper;
import com.freshshare.customer.service.ManageCustomerService;
import com.freshshare.entity.Business;
import com.freshshare.entity.Customer;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class ManageCustomerServiceImpl extends ServiceImpl<ManageCustomerMapper, Customer> implements ManageCustomerService {
    @Override
    public Map<Object, Object> getAllCustomers() {
        List<Map<String,Object>> customers = this.listMaps(new LambdaQueryWrapper<Customer>()
                .select(Customer::getCustomerId,Customer::getCustomerEmail,Customer::getCustomerUsername,
                        Customer::getCustomerFirstname,Customer::getCustomerLastname,Customer::getCustomerStatus)
                .orderByDesc(Customer::getCustomerId)
        );
        Map<Object,Object> returnResult = new HashMap<>();
        returnResult.put("customers",customers);
        return returnResult;
    }

    @Override
    public Map<Object, Object> getOneCustomer(String businessId) {
        Map<String, Object> customer = this.getMap(new LambdaQueryWrapper<Customer>()
                .select(Customer::getCustomerId,Customer::getCustomerEmail,Customer::getCustomerAddress)
                .eq(Customer::getCustomerId, businessId)
        );
        if (customer == null){
            throw new ManageCustomerException(ManageCustomerExceptionEnum.GET_ONE_CUSTOMER_ERROR);
        }
        Map<Object,Object> returnResult = new HashMap<>();
        returnResult.put("customer",customer);
        return returnResult;
    }

    @Override
    public Map<Object, Object> getSearchedCustomers(String firstname, String lastname) {
        List<Map<String,Object>> customers = this.listMaps(new LambdaQueryWrapper<Customer>()
                .select(Customer::getCustomerId,Customer::getCustomerEmail,Customer::getCustomerAddress)
                .like(Customer::getCustomerFirstname,firstname)
                .or()
                .like(Customer::getCustomerLastname,lastname)
                .orderByAsc(Customer::getCustomerLastname)
        );
        Map<Object,Object> returnResult = new HashMap<>();
        returnResult.put("customers",customers);
        return returnResult;
    }

    @Override
    public void updateCustomerStatus(String customerId, String customerStatus) {
        Customer customer = this.getOne(new LambdaQueryWrapper<Customer>()
                .eq(Customer::getCustomerId, customerId)
        );
        if (customer == null){
            throw new ManageCustomerException(ManageCustomerExceptionEnum.UPDATE_CUSTOMER_STATUS_ERROR_NOTEXIST);
        }
        try {
            this.update(new LambdaUpdateWrapper<Customer>()
                    .set(Customer::getCustomerStatus,customerStatus)
                    .eq(Customer::getCustomerId,customerId)
            );
        } catch (Exception e) {
            throw new ManageCustomerException(ManageCustomerExceptionEnum.UPDATE_CUSTOMER_STATUS_ERROR);
        }
    }
}
