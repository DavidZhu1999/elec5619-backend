package com.freshshare.staff.controller;

import com.freshshare.staff.request.getOneStaffRequestParam;
import com.freshshare.staff.request.getSearchedStaffsReuqestParam;
import com.freshshare.staff.request.updateStaffStatusRequestParam;
import com.freshshare.staff.response.ManageStaffResponse;
import com.freshshare.staff.response.ManageStaffResponseEnum;
import com.freshshare.staff.service.ManageStaffService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/root/manage/staff")
public class ManageStaffController {

    @Resource
    ManageStaffService manageStaffService;

    @PostMapping("/getAllStaffs")
    public ManageStaffResponse getAllStaffs(){
        return ManageStaffResponse.success(ManageStaffResponseEnum.GET_SEARCHED_STAFF_SUCCESS,
                manageStaffService.getAllStaffs());
    }

    @PostMapping("/getOneStaff")
    public ManageStaffResponse getOneStaff(@RequestBody getOneStaffRequestParam param){
        return ManageStaffResponse.success(ManageStaffResponseEnum.GET_ONE_STAFF_SUCCESS,
                manageStaffService.getOneStaff(param.getStaffId()));
    }

    @PostMapping("/getSearchedStaffs")
    public ManageStaffResponse getSearchedStaffs(@RequestBody getSearchedStaffsReuqestParam param){
        return ManageStaffResponse.success(ManageStaffResponseEnum.GET_SEARCHED_STAFF_SUCCESS,
                manageStaffService.getSearchedStaffs(param.getName(),param.getName()));
    }

    @PostMapping("/updateStaffStatus")
    public ManageStaffResponse updateStaffStatus(@RequestBody updateStaffStatusRequestParam param){
        manageStaffService.updateStaffStatus(param.getStaffId(),param.getStaffStatus());
        return ManageStaffResponse.success(ManageStaffResponseEnum.UPDATE_STAFF_STATUS_SUCCESS);
    }

}
