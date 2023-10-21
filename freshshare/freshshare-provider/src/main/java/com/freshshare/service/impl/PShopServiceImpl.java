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


        return PShopMapper.updateShopStateById(shopDto);//返回成功更新的数字

    }
    @Override
    public Integer updateShopNameById(ShopDto shopDto) {
        return PShopMapper.updateShopNameById(shopDto);
    }
}
