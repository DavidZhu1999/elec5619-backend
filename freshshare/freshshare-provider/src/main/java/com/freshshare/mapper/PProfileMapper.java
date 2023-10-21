package com.freshshare.mapper;

import com.freshshare.controller.Dto.ProfileDto;
import com.freshshare.controller.Dto.ShopDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface PProfileMapper {
    List<Map<String,Object>>  selectProfile(ProfileDto profileDto);
    Integer updateShopNameById(ShopDto shopDto);
}
