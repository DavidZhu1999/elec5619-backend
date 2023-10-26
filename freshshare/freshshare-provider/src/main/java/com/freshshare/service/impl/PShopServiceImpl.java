package com.freshshare.service.impl;

import com.freshshare.controller.Dto.ShopDto;
import com.freshshare.mapper.PShopMapper;
import com.freshshare.service.PShopService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class PShopServiceImpl implements PShopService {


    @Resource
    private PShopMapper PShopMapper;

    @Override
    public Integer updateShopStateById(ShopDto shopDto) {


        return PShopMapper.updateShopStateById(shopDto);//Returns the number of successful updates

    }
    @Override
    public Integer updateShopNameById(ShopDto shopDto) {
        return PShopMapper.updateShopNameById(shopDto);
    }
}
