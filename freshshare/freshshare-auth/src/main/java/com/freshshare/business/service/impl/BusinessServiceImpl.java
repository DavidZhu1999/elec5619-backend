package com.freshshare.business.service.impl;

import cn.dev33.satoken.secure.SaSecureUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.freshshare.business.exception.BusinessException;
import com.freshshare.business.exception.BusinessExceptionEnum;
import com.freshshare.business.mapper.BusinessMapper;
import com.freshshare.business.request.BusinessLogInRequestParam;
import com.freshshare.business.request.BusinessSignUpRequestParam;
import com.freshshare.business.service.BusinessService;
import com.freshshare.entity.Business;
import com.freshshare.util.StpBusinessUtil;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.geo.Point;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.*;

/**
 * This is the service implementation for business auth
 */
@Slf4j
@Service
public class BusinessServiceImpl extends ServiceImpl<BusinessMapper, Business> implements BusinessService {

    @Value("${google.api.key}")
    private String API_KEY; // google api key

    @Value("${google.api.url}")
    private String BASE_URL; // google api url

    @Value("${auth.keys.privateKey}")
    private String privateKey; // private key

    @Value("${auth.keys.publicKey}")
    private String publicKey; // public key

    @Resource
    private RedisTemplate<String,Object> redisTemplate;


    /**
     * This is the sign up method for business
     * @param param
     */
    @Override
    public void signUp(BusinessSignUpRequestParam param) {
        Business business = this.getOne(new LambdaQueryWrapper<Business>()
                .eq(Business::getBusinessUsername, param.getBusinessUsername())
        );
        if (business != null) {
            throw new BusinessException(BusinessExceptionEnum.SIGN_UP_EXIST_USERNAME);
        }
        Business business1 = this.getOne(new LambdaQueryWrapper<Business>()
                .eq(Business::getBusinessEmail, param.getBusinessEmail())
        );
        if (business1 != null) {
            throw new BusinessException(BusinessExceptionEnum.SIGN_UP_EXIST_EMAIL);
        }

        String ciphertext;

        try {
            ciphertext = SaSecureUtil.rsaEncryptByPublic(publicKey,param.getBusinessPassword());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        Business newOne = new Business();
        newOne.setBusinessUsername(param.getBusinessUsername());
        newOne.setBusinessEmail(param.getBusinessEmail());
        newOne.setBusinessPassword(ciphertext);
        newOne.setBusinessShopname(param.getBusinessShopname());
        newOne.setBusinessAddress(param.getBusinessAddress());
        newOne.setBusinessPhone(param.getBusinessPhone());
        newOne.setBusinessPostcode(param.getBusinessPostcode());
        newOne.setBusinessState(param.getBusinessState());
        newOne.setBusinessRegisterTime(LocalDateTime.now());
        newOne.setBusinessStatus("active");
        newOne.setBusinessIsOpen(1);

        try {
            URL url = new URL(BASE_URL + param.getBusinessAddress().replace(" ",
                    "%20") + "&key=" + API_KEY);
            try {
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                InputStream is = connection.getInputStream();
                byte[] bytes = new byte[1024];
                int len = 0;
                StringBuilder sb = new StringBuilder();
                while ((len = is.read(bytes)) != -1) {
                    sb.append(new String(bytes, 0, len));
                }
                newOne.setBusinessGeocode(sb.toString());
                is.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }






        try {
            this.save(newOne);
            JSONObject json = JSON.parseObject(newOne.getBusinessGeocode());
            if (json != null) {
                if (json.containsKey("results")) {
                    JSONObject firstResult = json.getJSONArray("results").getJSONObject(0);
                    if (firstResult.containsKey("geometry")) {
                        JSONObject geometry = firstResult.getJSONObject("geometry");
                        if (geometry.containsKey("location")) {
                            JSONObject location = geometry.getJSONObject("location");
                            double latitude = location.getDoubleValue("lat");
                            double longitude = location.getDoubleValue("lng");

                            System.out.println("Latitude: " + latitude);
                            System.out.println("Longitude: " + longitude);
                            redisTemplate.opsForGeo().add("business", new Point(longitude, latitude), newOne.getBusinessId());
                        }
                    }
                }
            }
            log.info(newOne.getBusinessUsername() + " SignUp successfully");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * This is the log in method for business
     * @param param
     * @return result map
     */
    @Override
    public Map<Object,Object> logIn(BusinessLogInRequestParam param) {
        Business business = this.getOne(new LambdaQueryWrapper<Business>()
                .eq(Business::getBusinessUsername, param.getBusinessUsername())
        );

        if (business == null) {
            throw new BusinessException(BusinessExceptionEnum.LOG_IN_ERROR_NOT_ACCOUNT);
        }

        if (!business.getBusinessStatus().equals("active")) {
            throw new BusinessException(BusinessExceptionEnum.LOG_IN_ERROR_ACCOUNT_LOCKED);
        }

        if(SaSecureUtil.rsaDecryptByPrivate(privateKey,business.getBusinessPassword())
                .equals(param.getBusinessPassword())) {
            StpBusinessUtil.login(business.getBusinessId(), param.getRememberMe());
            log.info(business.getBusinessUsername() + " Login successfully");
        }
        else {
            throw new BusinessException(BusinessExceptionEnum.LOG_IN_ERROR_NOT_MATCH);
        }

        Map<Object,Object> returnMap = new HashMap<>();
        returnMap.put("satoken", StpBusinessUtil.getTokenInfo());
        returnMap.put("rememberMe",param.getRememberMe());

        return returnMap;

    }

    /**
     * This is the log out method for business
     */
    @Override
    public void logOut() {
        try {
            StpBusinessUtil.logout();
        } catch (Exception e) {
            throw new BusinessException(BusinessExceptionEnum.LOG_OUT_ERROR);
        }
    }


}
