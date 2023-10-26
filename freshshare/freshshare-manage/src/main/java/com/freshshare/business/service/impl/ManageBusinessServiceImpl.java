package com.freshshare.business.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.freshshare.business.exception.ManageBusinessException;
import com.freshshare.business.exception.ManageBusinessExceptionEnum;
import com.freshshare.business.mapper.ManageBusinessMapper;
import com.freshshare.business.service.ManageBusinessService;
import com.freshshare.entity.Business;


import com.freshshare.util.StpBusinessUtil;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description: ManageBusinessServiceImpl
 */
@Service
public class ManageBusinessServiceImpl extends ServiceImpl<ManageBusinessMapper, Business> implements ManageBusinessService {

    @Resource
    ManageBusinessMapper manageBusinessMapper;

    /**
     * get all businesses
     * @return
     */
    @Override
    public Map<Object,Object> getAllBusinesses() {
        List<Map<String,Object>> businesses = this.listMaps(new LambdaQueryWrapper<Business>()
                .select(Business::getBusinessId,Business::getBusinessEmail,Business::getBusinessUsername,
                        Business::getBusinessStatus,Business::getBusinessShopname)
        );
        Map<Object,Object> returnResult = new HashMap<>();
        returnResult.put("businesses",businesses);
        return returnResult;
    }

    /**
     * get one business detail
     * @param businessId
     * @return
     */
    @Override
    public Map<Object, Object> getOneBusiness(String businessId) {
        Map<String, Object> business = this.getMap(new LambdaQueryWrapper<Business>()
                .select(Business::getBusinessId,Business::getBusinessEmail,Business::getBusinessAddress)
                .eq(Business::getBusinessId, businessId)
        );
        if (business == null){
            throw new ManageBusinessException(ManageBusinessExceptionEnum.GET_ONE_BUSINESS_ERROR);
        }
        Map<Object,Object> returnResult = new HashMap<>();
        returnResult.put("business",business);
        return returnResult;
    }

    /**
     * get searched businesses
     * @param businessShopname
     * @return
     */
    @Override
    public Map<Object, Object> getSearchedBusinesses(String businessShopname) {
        List<Map<String,Object>> businesses = this.listMaps(new LambdaQueryWrapper<Business>()
                .select(Business::getBusinessId,Business::getBusinessEmail,Business::getBusinessAddress)
                .like(Business::getBusinessShopname,businessShopname)
                .orderByAsc(Business::getBusinessShopname)
        );
        Map<Object,Object> returnResult = new HashMap<>();
        returnResult.put("businesses",businesses);
        return returnResult;
    }

    /**
     * update business status
     * @param businessId
     * @param businessStatus
     */
    @Override
    public void updateBusinessStatus(String businessId, String businessStatus) {
        Business business = this.getOne(new LambdaQueryWrapper<Business>()
                .eq(Business::getBusinessId, businessId)
        );
        if (business == null){
            throw new ManageBusinessException(ManageBusinessExceptionEnum.UPDATE_BUSINESS_STATUS_ERROR_NOTEXIST);
        }
        try {
            this.update(new LambdaUpdateWrapper<Business>()
                    .set(Business::getBusinessStatus, businessStatus)
                    .eq(Business::getBusinessId, businessId)
            );
            if (businessStatus.equals("ban")){
                StpBusinessUtil.kickout(businessId);
            }
        } catch (Exception e) {
            throw new ManageBusinessException(ManageBusinessExceptionEnum.UPDATE_BUSINESS_STATUS_ERROR);
        }
    }
}
