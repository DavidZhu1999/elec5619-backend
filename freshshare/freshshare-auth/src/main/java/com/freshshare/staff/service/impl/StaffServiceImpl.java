package com.freshshare.staff.service.impl;


import cn.dev33.satoken.secure.SaSecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.freshshare.entity.Staff;
import com.freshshare.staff.exception.StaffException;
import com.freshshare.staff.exception.StaffExceptionEnum;
import com.freshshare.staff.mapper.StaffMapper;
import com.freshshare.staff.request.StaffLogInRequestParam;
import com.freshshare.staff.request.StaffSignUpRequestParam;

import com.freshshare.staff.service.StaffService;
import com.freshshare.util.StpStaffUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * This is the service implementation for staff
 */
@Slf4j
@Service
public class StaffServiceImpl extends ServiceImpl<StaffMapper, Staff> implements StaffService {

    @Value("${auth.keys.privateKey}")
    private String privateKey;

    @Value("${auth.keys.publicKey}")
    private String publicKey;

    @Override
    public void signUp(StaffSignUpRequestParam param) {
        Staff staff = this.getOne(new LambdaQueryWrapper<Staff>()
                .eq(Staff::getStaffUsername, param.getStaffUsername())
        );
        if (staff != null) {
            throw new StaffException(StaffExceptionEnum.SIGN_UP_EXIST_USERNAME);
        }
        Staff staff1 = this.getOne(new LambdaQueryWrapper<Staff>()
                .eq(Staff::getStaffEmail, param.getStaffEmail())
        );
        if (staff1 != null) {
            throw new StaffException(StaffExceptionEnum.SIGN_UP_EXIST_EMAIL);
        }
        String ciphertext;
        try {
            ciphertext = SaSecureUtil.rsaEncryptByPublic(publicKey, param.getStaffPassword());
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
        Staff newOne = new Staff();
        newOne.setStaffUsername(param.getStaffUsername());
        newOne.setStaffPassword(ciphertext);
        newOne.setStaffEmail(param.getStaffEmail());
        newOne.setStaffPhone(param.getStaffPhone());
        newOne.setStaffFirstname(param.getStaffFirstname());
        newOne.setStaffLastname(param.getStaffLastname());
        newOne.setJobId("1");
        newOne.setDeptId("1");
        newOne.setStaffStatus("checking");
        newOne.setStaffCreateTime(LocalDateTime.now());
        try {
            this.save(newOne);
            log.info("Staff " + param.getStaffUsername() + " sign up successfully");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Map<Object,Object> logIn(StaffLogInRequestParam param) {
        Staff staff = this.getOne(new LambdaQueryWrapper<Staff>()
                .eq(Staff::getStaffUsername, param.getStaffUsername())
        );

        if (staff == null) {
            throw new StaffException(StaffExceptionEnum.LOG_IN_ERROR_NOT_ACCOUNT);
        }

        if (!staff.getStaffStatus().equals("active")){
            throw new StaffException(StaffExceptionEnum.LOG_IN_ERROR_ACCOUNT_LOCKED);
        }

        try {
            if (SaSecureUtil.rsaDecryptByPrivate(privateKey, staff.getStaffPassword()).equals(param.getStaffPassword())) {
                StpStaffUtil.login(staff.getStaffId(), param.getRememberMe());
                log.info(staff.getStaffUsername() + " LogIn successfully");
            } else {
                throw new StaffException(StaffExceptionEnum.LOG_IN_ERROR_NOT_MATCH);
            }
        } catch (Exception e){
            throw new StaffException(StaffExceptionEnum.LOG_IN_ERROR);
        }
        Map<Object,Object> returnMap = new HashMap<>();
        returnMap.put("satoken",StpStaffUtil.getTokenInfo());
        returnMap.put("rememberMe",param.getRememberMe());

        return returnMap;
    }

    @Override
    public void logOut() {
        try {
            System.out.println("token");
            StpStaffUtil.logout();
        } catch (Exception e) {
            throw new StaffException(StaffExceptionEnum.LOG_OUT_ERROR);
        }
    }
}
