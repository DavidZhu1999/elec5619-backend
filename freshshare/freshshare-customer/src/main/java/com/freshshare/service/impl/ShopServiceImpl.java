package com.freshshare.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.freshshare.entity.Business;
import com.freshshare.exception.CustomerException;
import com.freshshare.exception.CustomerExceptionEnum;
import com.freshshare.mapper.ShopMapper;
import com.freshshare.request.GetShopAddressRequest;
import com.freshshare.request.ViewShopItemsRequest;
import com.freshshare.request.ViewShopsRequest;
import com.freshshare.service.ShopService;
import jakarta.annotation.Resource;
import org.springframework.data.geo.*;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ShopServiceImpl extends ServiceImpl<ShopMapper, Business> implements ShopService {

    @Resource
    private RedisTemplate<String,Object> redisTemplate;

    @Resource
    private ShopMapper shopMapper;

    @Override
    public Map<Object, Object> viewShops(ViewShopsRequest viewShopsRequest) {
        Point point = new Point(viewShopsRequest.getLongitude(), viewShopsRequest.getLatitude());
        Metric metric = RedisGeoCommands.DistanceUnit.METERS;
        Distance distance = new Distance(10000, metric);
        Circle circle = new Circle(point, distance);

        RedisGeoCommands.GeoRadiusCommandArgs args = RedisGeoCommands
                .GeoRadiusCommandArgs
                .newGeoRadiusArgs()
                .includeDistance()
                .includeCoordinates()
                .sortAscending();

        GeoResults<RedisGeoCommands.GeoLocation<Object>> results = redisTemplate.opsForGeo().radius("business", circle, args);
        System.out.println(results);
        List<String> businessIdList = new ArrayList<>();
        for (GeoResult<RedisGeoCommands.GeoLocation<Object>> result: results) {
            businessIdList.add(result.getContent().getName().toString());
        }
        Map<String,Object> map = new HashMap<>();
        List<Business> businessList = null;
        try {
            businessList = this.listByIds(businessIdList);
        } catch (Exception e) {
            throw new CustomerException(CustomerExceptionEnum.VIEW_SHOP_ERROR);
        }
        results.getContent().forEach(geoLocationGeoResult -> {
            System.out.println(geoLocationGeoResult.getContent().getName());
            System.out.println(geoLocationGeoResult.getContent().getPoint());
            System.out.println(geoLocationGeoResult.getDistance().getValue());

            map.put(geoLocationGeoResult.getContent().getName().toString(),geoLocationGeoResult.getDistance().getValue());
        });
        Map<Object,Object> resultMap = new HashMap<>();
        resultMap.put("businessList",businessList);
        resultMap.put("distanceMap",map);
        return resultMap;
    }

    @Override
    public Map<Object, Object> viewShopItems(ViewShopItemsRequest viewShopItemsRequest) {
        List<Map<String,Object>> items = shopMapper.selectShopItems(viewShopItemsRequest.getBusinessId());
        Map<Object,Object> result = new HashMap<>();
        result.put("items",items);
        return result;
    }

    @Override
    public Map<Object, Object> getShopAddress(GetShopAddressRequest getShopAddressRequest) {
        Map<String,Object> address = this.getMap(new LambdaQueryWrapper<Business>()
                .select(Business::getBusinessAddress)
                .eq(Business::getBusinessId,getShopAddressRequest.getBusinessId()));

        Map<Object,Object> result = new HashMap<>();
        result.put("address",address);
        return result;
    }
}
