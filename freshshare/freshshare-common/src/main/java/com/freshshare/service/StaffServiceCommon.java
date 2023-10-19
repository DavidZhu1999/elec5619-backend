package com.freshshare.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.freshshare.entity.Staff;

public interface StaffServiceCommon extends IService<Staff> {

    String getJobId (String staffId);

}
