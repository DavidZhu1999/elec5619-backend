package com.freshshare.controller;

import com.freshshare.reponse.BusinessResponse;
import com.freshshare.reponse.BusinessResponseEnum;
import com.freshshare.request.CloseStoreRequest;
import com.freshshare.request.OpenStoreRequest;
import com.freshshare.service.ManageService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/business/manage")
public class ManageController {

    @Resource
    private ManageService manageService;

    @PostMapping("/openStore")
    public BusinessResponse openStore(@RequestBody OpenStoreRequest openStoreRequest){
        manageService.openStore(openStoreRequest);
        return BusinessResponse.success(BusinessResponseEnum.OPEN_STORE_SUCCESS);
    }

    @PostMapping("/closeStore")
    public BusinessResponse closeStore(@RequestBody CloseStoreRequest closeStoreRequest){
        manageService.closeStore(closeStoreRequest);
        return BusinessResponse.success(BusinessResponseEnum.CLOSE_STORE_SUCCESS);
    }
}
