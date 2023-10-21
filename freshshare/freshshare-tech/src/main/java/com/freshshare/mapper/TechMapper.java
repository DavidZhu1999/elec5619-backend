package com.freshshare.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.freshshare.entity.Customer;
import com.freshshare.entity.Issue;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface TechMapper extends BaseMapper<Issue>{
    @Select("SELECT i.issue_id, i.issue_content,i.issue_status, c.customer_id, c.customer_username, c.customer_email, " +
            "c.customer_firstname, c.customer_lastname " +
            "FROM issue i " +
            "JOIN customer c ON i.customer_id = c.customer_id " +
            "WHERE i.issue_status = 'pending'")
    List<Map<String, Object>> selectPendingIssuesWithCustomerInfo();

    @Select("SELECT i.issue_id, i.issue_content,i.issue_status, c.customer_id, c.customer_username, c.customer_email, " +
            "c.customer_firstname, c.customer_lastname " +
            "FROM issue i " +
            "JOIN customer c ON i.customer_id = c.customer_id " +
            "WHERE i.issue_status = 'dealing'")
    List<Map<String, Object>> selectDealingIssuesWithCustomerInfo();

    @Select("SELECT i.issue_id, i.issue_content,i.issue_status, c.customer_id, c.customer_username, c.customer_email, " +
            "c.customer_firstname, c.customer_lastname " +
            "FROM issue i " +
            "JOIN customer c ON i.customer_id = c.customer_id " +
            "WHERE i.issue_status = 'finish'")
    List<Map<String, Object>> selectFinishIssuesWithCustomerInfo();

    @Select("SELECT i.issue_id, i.issue_content,i.issue_status, c.customer_id, c.customer_username, c.customer_email, " +
            "c.customer_firstname, c.customer_lastname " +
            "FROM issue i " +
            "JOIN customer c ON i.customer_id = c.customer_id " +
            "WHERE i.issue_status = 'cancel'")
    List<Map<String, Object>> selectCancelIssuesWithCustomerInfo();
}
