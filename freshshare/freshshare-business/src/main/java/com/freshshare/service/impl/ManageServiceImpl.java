package com.freshshare.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.freshshare.entity.Business;
import com.freshshare.exception.BusinessException;
import com.freshshare.exception.BusinessExceptionEnum;
import com.freshshare.mapper.ManageMapper;
import com.freshshare.request.CloseStoreRequest;
import com.freshshare.request.OpenStoreRequest;
import com.freshshare.service.ManageService;
import org.springframework.stereotype.Service;

@Service
public class ManageServiceImpl extends ServiceImpl<ManageMapper, Business> implements ManageService {
    @Override
    public void openStore(OpenStoreRequest openStoreRequest) {
        try {
            this.update(new LambdaUpdateWrapper<Business>()
                    .eq(Business::getBusinessId, openStoreRequest.getBusinessId())
                    .set(Business::getBusinessIsOpen, 1));
        } catch (Exception e) {
            throw new BusinessException(BusinessExceptionEnum.OPEN_STORE_ERROR);
        }
    }

    @Override
    public void closeStore(CloseStoreRequest closeStoreRequest) {
        try {
            this.update(new LambdaUpdateWrapper<Business>()
                    .eq(Business::getBusinessId, closeStoreRequest.getBusinessId())
                    .set(Business::getBusinessIsOpen, 0));
        } catch (Exception e) {
            throw new BusinessException(BusinessExceptionEnum.CLOSE_STORE_ERROR);
        }
    }
}
