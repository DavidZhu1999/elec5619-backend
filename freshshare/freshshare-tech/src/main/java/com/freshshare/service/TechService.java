package com.freshshare.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.freshshare.entity.Issue;

import java.util.Map;

public interface TechService extends IService<Issue> {

    void addIssue(String orderId, String issueContent);

    Map<Object,Object> getAllIssues();

    void updateIssue(String issueId, String issueStatus);

    Map<Object,Object> getTodo();

    Map<Object,Object> getDealing();

    Map<Object,Object> getFinish();
}
