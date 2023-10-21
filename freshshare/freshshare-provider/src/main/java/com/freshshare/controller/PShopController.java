package com.freshshare.controller;


import com.freshshare.controller.Dto.ShopDto;
import com.freshshare.controller.pojo.ShopReq;
import com.freshshare.response.ResponseObj;
import com.freshshare.service.PShopService;
import com.freshshare.util.StpBusinessUtil;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/business")
@CrossOrigin
public class PShopController {
        @Resource
        private PShopService PShopService;
    @RequestMapping(value = "/openOrCloseShop", method = RequestMethod.POST)
    @ResponseBody
    public ResponseObj addSeller(@RequestBody ShopReq shopReq){
        ResponseObj responseObj = new ResponseObj();

        ShopDto shopDto = new ShopDto();
        BeanUtils.copyProperties(shopReq,shopDto);//把入参和目标参数里面的相同属性复制
        //根据token分析出bussiness id
        String businessId= StpBusinessUtil.getLoginIdByToken(shopReq.getSatokenBusiness()).toString();
        shopDto.setBusiness_id(businessId);
        Integer tmp= PShopService.updateShopStateById(shopDto);
        responseObj.setCode(200);
        responseObj.setMsg("business status has been updated ");
        responseObj.setData(1);
        return  responseObj;





    }

}
