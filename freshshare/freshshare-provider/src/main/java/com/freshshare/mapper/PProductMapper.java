package com.freshshare.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.freshshare.controller.Dto.ProductDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface PProductMapper extends BaseMapper<ProductDto> {
    Integer insertCommodity(ProductDto productDto);


    List<Map<String,Object>> selectAllActiveCommodities(ProductDto productDto);
    Integer updateProduct(ProductDto productDto);
    Integer deleteProductByCommodityId(ProductDto productDto);

}
