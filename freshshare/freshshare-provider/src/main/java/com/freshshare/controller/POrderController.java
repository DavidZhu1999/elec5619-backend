package com.freshshare.controller;


import com.freshshare.controller.Dto.OrderDto;
import com.freshshare.controller.pojo.OrderReq;
import com.freshshare.response.ResponseObj;
import com.freshshare.service.POrderService;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/business/order")
@CrossOrigin
public class POrderController {
    @Resource
    private POrderService POrderService;
    @RequestMapping(value = "/accept", method = RequestMethod.POST)
    @ResponseBody
    public ResponseObj acceptOrder(@RequestBody OrderReq orderReq){
        ResponseObj responseObj = new ResponseObj();
        OrderDto orderDto = new OrderDto();
        BeanUtils.copyProperties(orderReq,orderDto);//把入参和目标参数里面的相同属性复制


        Integer tmp= POrderService.acceptOrder(orderDto);
        if(tmp==404){
            responseObj.setCode(40404);
            responseObj.setMsg("the order status is not  create,can't be changed into pending");
        }
        responseObj.setCode(200);
        responseObj.setMsg("update order successfully "+tmp);

        return  responseObj;
    }


    @RequestMapping(value = "/getAll", method = RequestMethod.POST)
    @ResponseBody
    public ResponseObj getAllOrder(@RequestBody OrderReq orderReq){
        ResponseObj responseObj = new ResponseObj();
        OrderDto orderDto = new OrderDto();
//Return the name of the customer and provider
orderDto.setBusiness_id(orderReq.getBusiness_id());
        //left join


        List<Map<String,Object>> tmp= POrderService.selectAllOrder(orderDto);

        responseObj.setCode(200);
        responseObj.setMsg("update order successfully ");
        responseObj.setData(tmp);

        return  responseObj;
    }
    @RequestMapping(value = "/getOne", method = RequestMethod.POST)
    @ResponseBody
    public ResponseObj getOneOrderDetail(@RequestBody OrderReq orderReq){
        ResponseObj responseObj = new ResponseObj();
        OrderDto orderDto = new OrderDto();
        BeanUtils.copyProperties(orderReq,orderDto);

        List<Map<String,Object>> tmp= POrderService.selectOneOrderByDetail(orderDto);

        responseObj.setCode(200);
        responseObj.setMsg("select order detail successfully ");
        responseObj.setData(tmp);

        return  responseObj;
    }

    @RequestMapping(value = "/cancel", method = RequestMethod.POST)
    @ResponseBody
    public ResponseObj UpdateOneOrderCancel(@RequestBody OrderReq orderReq){
        ResponseObj responseObj = new ResponseObj();
        OrderDto orderDto = new OrderDto();
        BeanUtils.copyProperties(orderReq,orderDto);

        Integer tmp= POrderService.cancelOrder(orderDto);

        responseObj.setCode(200);
        responseObj.setMsg("update order status as canceled successfully ");
        responseObj.setData(tmp);

        return  responseObj;
    }
    @RequestMapping(value = "/complete", method = RequestMethod.POST)
    @ResponseBody
    public ResponseObj UpdateOneOrderComplete(@RequestBody OrderReq orderReq){
        ResponseObj responseObj = new ResponseObj();
        OrderDto orderDto = new OrderDto();
        BeanUtils.copyProperties(orderReq,orderDto);

        Integer tmp= POrderService.completeOrder(orderDto);

        responseObj.setCode(200);
        responseObj.setMsg("update order status as complete successfully ");
        responseObj.setData(tmp);

        return  responseObj;
    }

}
