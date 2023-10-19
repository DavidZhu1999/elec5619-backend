package com.freshshare.controller;

import com.freshshare.reponse.BusinessResponse;
import com.freshshare.reponse.BusinessResponseEnum;
import com.freshshare.request.AddCommodityRequest;
import com.freshshare.request.GetCommodityListByIdRequest;
import com.freshshare.request.UpdateCommodityRequest;
import com.freshshare.service.CommodityService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/business/commodity")
public class CommodityController {

    @Resource
    private CommodityService commodityService;

    @PostMapping("/addCommodity")
    public BusinessResponse addCommodity(@RequestBody AddCommodityRequest addCommodityRequest){
        commodityService.addCommodity(addCommodityRequest);
        return BusinessResponse.success(BusinessResponseEnum.ADD_SUCCESS);
    }

    @PostMapping("/getCommodityListById")
    public BusinessResponse getCommodityListById(@RequestBody GetCommodityListByIdRequest getCommodityListByIdRequest){
        return BusinessResponse.success(BusinessResponseEnum.GET_COMMODITY_LIST_SUCCESS,
                commodityService.getCommodityList(getCommodityListByIdRequest.getBusinessId()));
    }

    @PostMapping("/updateCommodity")
    public BusinessResponse updateCommodity(@RequestBody UpdateCommodityRequest updateCommodityRequest){
        commodityService.updateCommodity(updateCommodityRequest);
        return BusinessResponse.success(BusinessResponseEnum.UPDATE_COMMODITY_SUCCESS);
    }



}
