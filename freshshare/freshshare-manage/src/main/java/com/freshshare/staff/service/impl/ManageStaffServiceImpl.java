package com.freshshare.staff.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.freshshare.entity.Business;
import com.freshshare.entity.Staff;
import com.freshshare.staff.exception.ManageStaffException;
import com.freshshare.staff.exception.ManageStaffExceptionEnum;
import com.freshshare.staff.mapper.ManageStaffMapper;
import com.freshshare.staff.service.ManageStaffService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ManageStaffServiceImpl extends ServiceImpl<ManageStaffMapper, Staff> implements ManageStaffService {
    @Override
    public Map<Object, Object> getAllStaffs() {
        List<Map<String,Object>> staffs = this.listMaps(new LambdaQueryWrapper<Staff>()
        );
        Map<Object,Object> returnResult = new HashMap<>();
        returnResult.put("staffs",staffs);
        return returnResult;
    }

    @Override
    public Map<Object, Object> getOneStaff(String staffId) {
        Map<String,Object> staff = this.getMap(new LambdaQueryWrapper<Staff>()
                .eq(Staff::getStaffId,staffId)
        );
        if (staff == null){
            throw new ManageStaffException(ManageStaffExceptionEnum.GET_ONE_STAFF_ERROR);
        }
        Map<Object,Object> returnResult = new HashMap<>();
        returnResult.put("staff",staff);
        return returnResult;
    }

    @Override
    public Map<Object, Object> getSearchedStaffs(String firstname, String lastname) {
        List<Map<String,Object>> staffs = this.listMaps(new LambdaQueryWrapper<Staff>()
                .like(Staff::getStaffFirstname,firstname)
                .like(Staff::getStaffLastname,lastname)
                .orderByAsc(Staff::getStaffFirstname)
        );
        Map<Object,Object> returnResult = new HashMap<>();
        returnResult.put("staffs",staffs);
        return returnResult;
    }

    @Override
    public void updateStaffStatus(String staffId, String staffStatus) {
        Staff staff = this.getOne(new LambdaUpdateWrapper<Staff>()
                .eq(Staff::getStaffId,staffId)
        );
        if (staff == null){
            throw new ManageStaffException(ManageStaffExceptionEnum.UPDATE_STAFF_STATUS_ERROR_NOTEXIST);
        }
        try {
            this.update(new LambdaUpdateWrapper<Staff>()
                    .set(Staff::getStaffStatus,staffStatus)
                    .eq(Staff::getStaffId, staffId)
            );
        } catch (Exception e) {
            throw new ManageStaffException(ManageStaffExceptionEnum.UPDATE_STAFF_STATUS_ERROR);
        }
    }
}
