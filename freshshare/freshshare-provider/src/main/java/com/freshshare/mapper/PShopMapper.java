package com.freshshare.mapper;

import com.freshshare.controller.Dto.ShopDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PShopMapper {
 Integer updateShopStateById(ShopDto shopDto);
 Integer updateShopNameById(ShopDto shopDto);
}
