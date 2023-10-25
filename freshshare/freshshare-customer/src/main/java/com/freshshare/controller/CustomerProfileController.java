package com.freshshare.controller;

import com.freshshare.request.GetProfileRequest;
import com.freshshare.request.UpdateProfileRequest;
import com.freshshare.response.CustomerResponse;
import com.freshshare.response.CustomerResponseEnum;
import com.freshshare.service.CustomerProfileService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer/profile")
@CrossOrigin
public class CustomerProfileController {

    @Resource
    private CustomerProfileService customerProfileService;

    @PostMapping("/updateProfile")
    public CustomerResponse updateProfile(@RequestBody UpdateProfileRequest updateProfileRequest){
        customerProfileService.updateCustomer(updateProfileRequest);
        return new CustomerResponse(CustomerResponseEnum.UPDATE_PROFILE_SUCCESS);
    }

    @PostMapping("/getProfile")
    public CustomerResponse getProfile(@RequestBody GetProfileRequest getProfileRequest){
        return new CustomerResponse(CustomerResponseEnum.GET_PROFILE_SUCCESS,customerProfileService.getCustomerProfile(getProfileRequest.getCustomerId()));
    }
}
