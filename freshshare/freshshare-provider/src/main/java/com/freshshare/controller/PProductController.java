package com.freshshare.controller;

import com.freshshare.controller.Dto.ProductDto;
import com.freshshare.controller.pojo.ProductReq;
import com.freshshare.response.ResponseObj;
import com.freshshare.service.PProductService;
import com.freshshare.util.StpBusinessUtil;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/business/product")
@CrossOrigin
public class PProductController {
    @Resource
    private PProductService PProductService;

    @RequestMapping(value = "/addProduct", method = RequestMethod.POST)
    @ResponseBody
    public ResponseObj addProduct(@RequestBody ProductReq productReq){
        ResponseObj responseObj = new ResponseObj();
        ProductDto productDto = new ProductDto();
        BeanUtils.copyProperties(productReq,productDto);//把入参和目标参数里面的相同属性复制

        Integer tmp= PProductService.insertCommodity(productDto);

        responseObj.setCode(200);
        responseObj.setMsg("insert product successfully "+tmp);
       // responseObj.setData(tmp);
        return  responseObj;





    }

    @RequestMapping(value = "/getAll", method = RequestMethod.POST)//注意我只搜状态为selling  也就是上架的商品
    @ResponseBody
    public ResponseObj getAllProduct(@RequestBody ProductReq productReq){//
        ResponseObj responseObj = new ResponseObj();
        String businessId=StpBusinessUtil.getLoginIdByToken(productReq.getSatokenBusiness()).toString();
        ProductDto productDto = new ProductDto();
        productDto.setBusiness_id(businessId);
        List<Map<String,Object>> tmp= PProductService.selectAllActiveCommodities(productDto);

        responseObj.setCode(200);
        responseObj.setMsg("get all product successfully ");
        responseObj.setData(tmp);
        return  responseObj;





    }
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public ResponseObj updateProductById(@RequestBody ProductReq productReq){
        ResponseObj responseObj = new ResponseObj();
        ProductDto productDto = new ProductDto();

        //todo
        //要不要判断有没有权力修改这个商品 暂时先不写了
        BeanUtils.copyProperties(productReq,productDto);//把入参和目标参数里面的相同属性复制
        String businessId=StpBusinessUtil.getLoginIdByToken(productReq.getSatokenBusiness()).toString();
        productDto.setBusiness_id(businessId);
        Integer tmp= PProductService.updateProduct(productDto);

        responseObj.setCode(200);
        responseObj.setMsg("update product successfully "+tmp);
        responseObj.setData(tmp);
        return  responseObj;





    }
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public ResponseObj deleteProductById(@RequestBody ProductReq productReq){
        ResponseObj responseObj = new ResponseObj();
        ProductDto productDto = new ProductDto();

        //todo
        //要不要判断有没有权力修改这个商品 暂时先不写了
        BeanUtils.copyProperties(productReq,productDto);//把入参和目标参数里面的相同属性复制
//        String businessId=StpBusinessUtil.getLoginIdByToken(productReq.getSatokenBusiness()).toString();
//        productDto.setBusiness_id(businessId);
        Integer tmp= PProductService.deleteProductByCommodityId(productDto);

        responseObj.setCode(200);
        responseObj.setMsg("delete product successfully "+tmp);
        responseObj.setData(tmp);
        return  responseObj;





    }



}
