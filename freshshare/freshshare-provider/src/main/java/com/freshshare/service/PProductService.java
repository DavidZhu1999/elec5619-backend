package com.freshshare.service;

import com.freshshare.controller.Dto.ProductDto;

import java.util.List;
import java.util.Map;

public interface PProductService {
    Integer insertCommodity(ProductDto productDto);

 //   @Select("SELECT * from `commodity`")
 List<Map<String,Object>> selectAllActiveCommodities(ProductDto productDto);

    Integer updateProduct(ProductDto productDto);

    Integer deleteProductByCommodityId(ProductDto productDto);
}
