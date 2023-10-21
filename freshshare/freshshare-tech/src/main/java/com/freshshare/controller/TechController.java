package com.freshshare.controller;

import com.freshshare.request.AddIssueRequestParam;
import com.freshshare.request.UpdateIssueStatusRequestParam;
import com.freshshare.response.TechResponse;
import com.freshshare.response.TechResponseEnum;
import com.freshshare.service.TechService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tech")
@CrossOrigin
public class TechController {

    @Resource
    private TechService techService;

    @PostMapping("/addIssue")
    public TechResponse addIssue(@RequestBody AddIssueRequestParam param){
        techService.addIssue(param.getOrderId(),param.getIssueContent());
        return TechResponse.success(TechResponseEnum.ADD_TECH);
    }

    @PostMapping("/getAllIssues")
    public TechResponse getAllIssues(){
        return TechResponse.success(TechResponseEnum.GET_ALL_ISSUES, techService.getAllIssues());
    }

    @PostMapping("/updateIssueStatus")
    public TechResponse updateIssueStatus(@RequestBody UpdateIssueStatusRequestParam param){
        techService.updateIssue(param.getIssueId(),param.getIssueStatus());
        return TechResponse.success(TechResponseEnum.UPDATE_ISSUE_STATUS_SUCCESS);
    }

    @PostMapping("/getTodo")
    public TechResponse getTodo(){
        return TechResponse.success(TechResponseEnum.GET_TODO,techService.getTodo());
    }

    @PostMapping("/getDealing")
    public TechResponse getDealing(){
        return TechResponse.success(TechResponseEnum.GET_DEALING,techService.getDealing());
    }

    @PostMapping("/getFinish")
    public TechResponse getFinish(){
        return TechResponse.success(TechResponseEnum.GET_FINISH,techService.getFinish());
    }

    @PostMapping("/getCancel")
    public TechResponse getCancel(){
        return TechResponse.success(TechResponseEnum.GET_CANCEL,techService.getCancel());
    }

}
