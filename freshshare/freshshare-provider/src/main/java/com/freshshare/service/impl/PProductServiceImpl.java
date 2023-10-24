package com.freshshare.service.impl;

import com.freshshare.controller.Dto.ProductDto;
import com.freshshare.mapper.PProductMapper;
import com.freshshare.service.PProductService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class PProductServiceImpl implements PProductService {
    @Resource
    private PProductMapper PProductMapper;
    @Override
    public Integer insertCommodity(ProductDto productDto) {
        UUID uuid = UUID.randomUUID();
        String uuidString = uuid.toString();


        productDto.setCommodity_id(uuidString);

        LocalDateTime currentDateTime = LocalDateTime.now();



        productDto.setCommodity_down_time(currentDateTime);
        productDto.setCommodity_edit_time(currentDateTime);
        productDto.setCommodity_upload_time(currentDateTime);

        return PProductMapper.insertCommodity(productDto);



    }

    @Override
    public   List<Map<String,Object>> selectAllActiveCommodities(ProductDto productDto) {



        return PProductMapper.selectAllActiveCommodities(productDto);
    }

    @Override
    public Integer updateProduct(ProductDto productDto) {

        LocalDateTime currentDateTime = LocalDateTime.now();


        productDto.setCommodity_down_time(currentDateTime);
        productDto.setCommodity_edit_time(currentDateTime);
        productDto.setCommodity_upload_time(currentDateTime);

        return PProductMapper.updateProduct(productDto);
    }

    @Override
    public Integer deleteProductByCommodityId(ProductDto productDto) {

        return PProductMapper.deleteProductByCommodityId(productDto);

    }
}
