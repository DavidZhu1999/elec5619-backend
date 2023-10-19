package com.freshshare.business.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.freshshare.business.request.BusinessLogInRequestParam;
import com.freshshare.business.request.BusinessSignUpRequestParam;
import com.freshshare.entity.Business;

import java.util.Map;

public interface BusinessService extends IService<Business> {

    public void signUp(BusinessSignUpRequestParam param);

    public Map<Object,Object> logIn(BusinessLogInRequestParam param);

    public void logOut();

}
