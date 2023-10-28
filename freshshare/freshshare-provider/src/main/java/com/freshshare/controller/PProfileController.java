package com.freshshare.controller;

import com.freshshare.controller.Dto.*;
import com.freshshare.response.ResponseObj;
import com.freshshare.service.PProfileService;
import com.freshshare.service.PShopService;
import com.freshshare.util.StpBusinessUtil;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/business/profile")
@CrossOrigin
public class PProfileController {
    @Value("${openai.model}")
    private String model;
    @Qualifier("openaiRestTemplate")
    @Autowired
    private RestTemplate restTemplate;

    @Value("${openai.api.url}")
    private String apiUrl;
    @Resource
    private PProfileService PProfileService;
    @Resource
    private PShopService PShopService;
    @RequestMapping(value = "/getOneProfile", method = RequestMethod.POST)
    @ResponseBody
    public ResponseObj getOneProfile(@RequestBody ProfileDto profileDto){
        ResponseObj responseObj = new ResponseObj();


        String businessId= StpBusinessUtil.getLoginIdByToken(profileDto.getSatokenBusiness()).toString();
       profileDto.setBusiness_id(businessId);
        List<Map<String,Object>> tmp= PProfileService.selectProfile(profileDto);

        responseObj.setCode(200);
        responseObj.setMsg("insert product successfully ");
         responseObj.setData(tmp);
        return  responseObj;





    }
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public ResponseObj updateShopName(@RequestBody ChangePassword changePassword){
        ResponseObj responseObj = new ResponseObj();
        //做一个改名字

        ShopDto shopDto = new ShopDto();
        shopDto.setBusiness_id(changePassword.getBusiness_id());
        shopDto.setBusiness_name(changePassword.getNewName());
        PShopService.updateShopNameById(shopDto);

//        String businessId= StpBusinessUtil.getLoginIdByToken(profileDto.getSatokenBusiness()).toString();
//        profileDto.setBusiness_id(businessId);
//        List<Map<String,Object>> tmp=profileService.selectProfile(profileDto);

        responseObj.setCode(200);
        responseObj.setMsg("update password successfully ");

        return  responseObj;





    }



    @ResponseBody
    @PostMapping("/chat")
    public GptResponse chat(@RequestBody GptResponse gptResponse) {//
        // create a request



        ChatRequest request = new ChatRequest(model, gptResponse.getInput());


        // call the API
        ChatResponse response = restTemplate.postForObject(apiUrl, request, ChatResponse.class);

        gptResponse.setOutput(response.getChoices().get(0).getMessage().getContent());

        // return the first response
        return gptResponse;
    }


}
