package com.freshshare.business.controller;

import cn.dev33.satoken.annotation.SaCheckRole;
import com.freshshare.business.request.getSearchedBusinessesRequestParam;
import com.freshshare.business.request.updateBusinessStatusRequestParam;
import com.freshshare.business.response.ManageBusinessResponse;
import com.freshshare.business.response.ManageBusinessResponseEnum;
import com.freshshare.business.request.getOneBusinessRequestParam;
import com.freshshare.business.service.ManageBusinessService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/staff/manager/business")
@CrossOrigin
public class ManageBusinessController {

    @Resource
    ManageBusinessService manageBusinessService;

    @SaCheckRole(type = "staff",value = {"manager"})
    @PostMapping("/getAllBusinesses")
    public ManageBusinessResponse getAllBusinesses(){
        return ManageBusinessResponse.success(ManageBusinessResponseEnum.GET_ALL_BUSINESS_SUCCESS,
                manageBusinessService.getAllBusinesses());
    }

    @SaCheckRole(type = "staff",value = {"manager"})
    @PostMapping("/getOneBusiness")
    public ManageBusinessResponse getOneBusiness(@RequestBody getOneBusinessRequestParam param){
        return ManageBusinessResponse.success(ManageBusinessResponseEnum.GET_ONE_BUSINESS_SUCCESS,
                manageBusinessService.getOneBusiness(param.getBusinessId()));
    }

    @SaCheckRole(type = "staff",value = {"manager"})
    @PostMapping("/getSearchedBusinesses")
    public ManageBusinessResponse getSearchedBusinesses(@RequestBody getSearchedBusinessesRequestParam param){
        return ManageBusinessResponse.success(ManageBusinessResponseEnum.GET_SEARCHED_BUSINESS_SUCCESS,
                manageBusinessService.getSearchedBusinesses(param.getBusinessShopname()));
    }

    @SaCheckRole(type = "staff",value = {"manager"})
    @PostMapping("/updateBusinessStatus")
    public ManageBusinessResponse updateBusinessStatus(@RequestBody updateBusinessStatusRequestParam param){
        manageBusinessService.updateBusinessStatus(param.getBusinessId(),param.getBusinessStatus());
        return ManageBusinessResponse.success(ManageBusinessResponseEnum.UPDATE_BUSINESS_STATUS_SUCCESS);
    }
}
