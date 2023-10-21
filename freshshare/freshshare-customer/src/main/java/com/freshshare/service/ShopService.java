package com.freshshare.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.freshshare.entity.Business;
import com.freshshare.request.GetShopAddressRequest;
import com.freshshare.request.ViewShopItemsRequest;
import com.freshshare.request.ViewShopsRequest;

import java.util.Map;

public interface ShopService extends IService<Business> {
    Map<Object,Object> viewShops(ViewShopsRequest viewShopsRequest);

    Map<Object,Object> viewShopItems(ViewShopItemsRequest viewShopItemsRequest);

    Map<Object,Object> getShopAddress(GetShopAddressRequest getShopAddressRequest);
}
