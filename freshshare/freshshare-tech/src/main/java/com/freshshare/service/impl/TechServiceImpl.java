package com.freshshare.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.freshshare.entity.Customer;
import com.freshshare.entity.Issue;
import com.freshshare.mapper.TechMapper;
import com.freshshare.service.TechService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TechServiceImpl extends ServiceImpl<TechMapper, Issue> implements TechService {


    @Resource
    TechMapper techMapper;

    @Override
    public void addIssue(String orderId, String issueContent) {
        Issue newIssue = new Issue();
        newIssue.setOrderId(orderId);
        newIssue.setIssueContent(issueContent);
        newIssue.setIssueCreateTime(LocalDateTime.now());
        newIssue.setIssueStatus("created");

        try {
            this.save(newIssue);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Map<Object, Object> getAllIssues() {
        List<Map<String,Object>> issues = this.listMaps();
        Map<Object,Object> result = new HashMap<>();
        result.put("issues",issues);
        return result;
    }

    @Override
    public void updateIssue(String issueId, String issueStatus) {
        Issue issue = this.getById(issueId);
        issue.setIssueStatus(issueStatus);
        try {
            this.updateById(issue);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Map<Object, Object> getTodo() {
        List<Map<String,Object>> customers = techMapper.selectPendingIssuesWithCustomerInfo();

        Map<Object,Object> result = new HashMap<>();
        result.put("issues",customers);
        return result;
    }

    @Override
    public Map<Object, Object> getDealing() {
        List<Map<String,Object>> customers = techMapper.selectDealingIssuesWithCustomerInfo();

        Map<Object,Object> result = new HashMap<>();
        result.put("issues",customers);
        return result;
    }

    @Override
    public Map<Object, Object> getFinish() {
        List<Map<String,Object>> customers = techMapper.selectFinishIssuesWithCustomerInfo();

        Map<Object,Object> result = new HashMap<>();
        result.put("issues",customers);
        return result;
    }


}
