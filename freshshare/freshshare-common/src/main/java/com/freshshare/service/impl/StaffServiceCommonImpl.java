package com.freshshare.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.freshshare.entity.Staff;
import com.freshshare.mapper.StaffMapperCommon;
import com.freshshare.service.StaffServiceCommon;
import org.springframework.stereotype.Service;

@Service
public class StaffServiceCommonImpl extends ServiceImpl<StaffMapperCommon, Staff> implements StaffServiceCommon {
    @Override
    public String getJobId(String staffId) {
        Staff staff = this.getOne(new LambdaQueryWrapper<Staff>()
                .eq(Staff::getStaffId, staffId)
        );
        return staff.getJobId();
    }
}
