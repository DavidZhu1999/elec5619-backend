package com.freshshare.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.freshshare.entity.Commodity;
import com.freshshare.exception.BusinessException;
import com.freshshare.exception.BusinessExceptionEnum;
import com.freshshare.mapper.CommodityMapper;
import com.freshshare.request.AddCommodityRequest;
import com.freshshare.request.UpdateCommodityRequest;
import com.freshshare.service.CommodityService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CommodityServiceImpl extends ServiceImpl<CommodityMapper, Commodity> implements CommodityService{
    @Override
    public void addCommodity(AddCommodityRequest addCommodityRequest) {
        try {
            Commodity commodity = new Commodity();
            commodity.setBusinessId(addCommodityRequest.getBusinessId());
            commodity.setCommodityName(addCommodityRequest.getCommodityName());
            commodity.setCommodityPrice(addCommodityRequest.getCommodityPrice());
            commodity.setTypeId(addCommodityRequest.getTypeId());
            commodity.setCommodityStatus("selling");
            this.save(commodity);
        } catch (Exception e) {
            throw new BusinessException(BusinessExceptionEnum.ADD_COMMODITY_ERROR);
        }
    }

    @Override
    public Map<Object, Object> getCommodityList(String businessId) {
        try {
            List<Map<String,Object>> commodityList = this.listMaps(new LambdaQueryWrapper<Commodity>()
                    .eq(Commodity::getBusinessId,businessId)
                    .ne(Commodity::getCommodityStatus,"deleted"));

            Map<Object,Object> resultMap = new HashMap<>();
            resultMap.put("commodityList",commodityList);
            return resultMap;
        } catch (Exception e) {
            throw new BusinessException(BusinessExceptionEnum.GET_COMMODITY_LIST_ERROR);
        }
    }

    @Override
    public void updateCommodity(UpdateCommodityRequest updateCommodityRequest) {
        try {
            this.update(new LambdaUpdateWrapper<Commodity>()
                    .eq(Commodity::getCommodityId,updateCommodityRequest.getCommodityId())
                    .set(Commodity::getCommodityName,updateCommodityRequest.getCommodityName())
                    .set(Commodity::getCommodityPrice,updateCommodityRequest.getCommodityPrice())
                    .set(Commodity::getCommodityStatus,updateCommodityRequest.getCommodityStatus())
            );
        } catch (Exception e) {
            throw new BusinessException(BusinessExceptionEnum.UPDATE_COMMODITY_ERROR);
        }
    }
}
