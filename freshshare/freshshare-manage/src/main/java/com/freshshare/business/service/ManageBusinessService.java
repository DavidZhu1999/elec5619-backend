package com.freshshare.business.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.freshshare.entity.Business;

import java.util.Map;

public interface ManageBusinessService extends IService<Business> {

    Map<Object,Object> getAllBusinesses();

    Map<Object, Object> getOneBusiness(String businessId);

    Map<Object, Object> getSearchedBusinesses(String businessShopname);

    void updateBusinessStatus(String businessId, String businessStatus);

}
