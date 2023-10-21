package com.freshshare.service;

import com.freshshare.controller.Dto.ShopDto;

public interface PShopService {
    Integer updateShopStateById(ShopDto shopDto);

    Integer updateShopNameById(ShopDto shopDto);
}
