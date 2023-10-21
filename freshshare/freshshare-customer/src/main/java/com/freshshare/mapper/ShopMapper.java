package com.freshshare.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.freshshare.entity.Business;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface ShopMapper extends BaseMapper<Business> {

    @Select("SELECT * FROM commodity WHERE business_id = #{businessId} AND (commodity_status = 'selling'" +
            "OR commodity_status = 'sellout')")
    List<Map<String,Object>> selectShopItems(String businessId);
}
