package com.freshshare.util;

import cn.dev33.satoken.stp.StpInterface;
import com.freshshare.service.impl.StaffServiceCommonImpl;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class StpInterfaceImpl implements StpInterface {

    @Resource
    StaffServiceCommonImpl staffServiceCommon;

    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        List<String> list = new ArrayList<String>();
        list.add("user:add");
        return list;
    }


    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        List<String> list = new ArrayList<String>();

        if (loginType.equals("customer")){
            list.add("customer");
            return list;
        } else if (loginType.equals("business")) {
            list.add("business");
            return list;
        } else if (loginType.equals("staff")){
            list.add("staff");
            if (staffServiceCommon.getJobId((String) loginId).equals("1")){
                list.add("manager");
                return list;
            }
            else{
                list.add("tech");
                return list;
            }
        } else {
            list.add("root");
            return list;
        }
    }

}
