package com.freshshare.customer.controller;


import cn.dev33.satoken.annotation.SaCheckRole;
import com.freshshare.customer.request.getOneCustomerRequestParam;
import com.freshshare.customer.request.getSearchedCustomersRequestParam;
import com.freshshare.customer.request.updateCustomerStatusRequestParam;
import com.freshshare.customer.response.ManageCustomerResponse;
import com.freshshare.customer.response.ManageCustomerResponseEnum;
import com.freshshare.customer.service.ManageCustomerService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/staff/manager/customer")
@CrossOrigin
public class ManageCustomerController {

    @Resource
    ManageCustomerService manageCustomerService;

    @SaCheckRole(type = "staff",value = {"manager"})
    @PostMapping("/getAllCustomers")
    public ManageCustomerResponse getAllCustomers(){
        return ManageCustomerResponse.success(ManageCustomerResponseEnum.GET_ALL_CUSTOMER_SUCCESS,
                manageCustomerService.getAllCustomers());
    }

    @SaCheckRole(type = "staff",value = {"manager"})
    @PostMapping("/getOneCustomer")
    public ManageCustomerResponse getOneCustomer(@RequestBody getOneCustomerRequestParam param){
        return ManageCustomerResponse.success(ManageCustomerResponseEnum.GET_ONE_CUSTOMER_SUCCESS,
                manageCustomerService.getOneCustomer(param.getCustomerId()));
    }

    @SaCheckRole(type = "staff",value = {"manager"})
    @PostMapping("/getSearchedCustomers")
    public ManageCustomerResponse getSearchedCustomers(@RequestBody getSearchedCustomersRequestParam param){
        return ManageCustomerResponse.success(ManageCustomerResponseEnum.GET_SEARCHED_CUSTOMER_SUCCESS,
                manageCustomerService.getSearchedCustomers(param.getName(),param.getName()));
    }

    @SaCheckRole(type = "staff",value = {"manager"})
    @PostMapping("/updateCustomerStatus")
    public ManageCustomerResponse updateCustomerStatus(@RequestBody updateCustomerStatusRequestParam param){
        manageCustomerService.updateCustomerStatus(param.getCustomerId(),param.getCustomerStatus());
        return ManageCustomerResponse.success(ManageCustomerResponseEnum.UPDATE_CUSTOMER_STATUS_SUCCESS);
    }


}
