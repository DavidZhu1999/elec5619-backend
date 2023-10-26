package com.freshshare.controller;

import com.freshshare.request.GetShopAddressRequest;
import com.freshshare.request.ViewShopItemsRequest;
import com.freshshare.request.ViewShopsRequest;
import com.freshshare.response.CustomerResponse;
import com.freshshare.response.CustomerResponseEnum;
import com.freshshare.service.ShopService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

/**
 * @description: ShopController
 */
@RestController
@RequestMapping("/customer/shop")
@CrossOrigin
public class ShopController {

    @Resource
    private ShopService shopService;

    /**
     * view shops
     * @param viewShopsRequest
     * @return
     */
    @PostMapping("/viewShops")
    public CustomerResponse viewShops(@RequestBody ViewShopsRequest viewShopsRequest){
        return new CustomerResponse(CustomerResponseEnum.VIEW_SHOPS_SUCCESS,shopService.viewShops(viewShopsRequest));
    }

    /**
     * view shop items
     * @param viewShopItemsRequest
     * @return
     */
    @PostMapping("/viewShopItems")
    public CustomerResponse viewShopItems(@RequestBody ViewShopItemsRequest viewShopItemsRequest){
        return new CustomerResponse(CustomerResponseEnum.VIEW_SHOP_ITEMS_SUCCESS,shopService.viewShopItems(viewShopItemsRequest));
    }

    /**
     * get shop address
     * @param getShopAddressRequest
     * @return
     */
    @PostMapping("/getShopAddress")
    public CustomerResponse getShopAddress(@RequestBody GetShopAddressRequest getShopAddressRequest){
        return new CustomerResponse(CustomerResponseEnum.GET_SHOP_ADDRESS_SUCCESS,shopService.getShopAddress(getShopAddressRequest));
    }

}
