package com.freshshare.staff.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.freshshare.entity.Staff;

import java.util.Map;

public interface ManageStaffService extends IService<Staff> {

    Map<Object,Object> getAllStaffs();

    Map<Object, Object> getOneStaff(String staffId);

    Map<Object, Object> getSearchedStaffs(String firstname, String lastname);

    void updateStaffStatus(String staffId, String status);
}
