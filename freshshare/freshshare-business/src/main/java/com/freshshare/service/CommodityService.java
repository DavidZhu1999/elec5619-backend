package com.freshshare.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.freshshare.entity.Commodity;
import com.freshshare.request.AddCommodityRequest;
import com.freshshare.request.UpdateCommodityRequest;

import java.util.Map;

public interface CommodityService extends IService<Commodity> {

    void addCommodity(AddCommodityRequest addCommodityRequest);

    Map<Object,Object> getCommodityList(String businessId);

    void updateCommodity(UpdateCommodityRequest updateCommodityRequest);

}
