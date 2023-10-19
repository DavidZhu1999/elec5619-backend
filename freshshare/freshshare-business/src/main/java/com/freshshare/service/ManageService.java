package com.freshshare.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.freshshare.entity.Business;
import com.freshshare.request.CloseStoreRequest;
import com.freshshare.request.OpenStoreRequest;

public interface ManageService extends IService<Business> {
    void openStore(OpenStoreRequest openStoreRequest);

    void closeStore(CloseStoreRequest closeStoreRequest);
}
