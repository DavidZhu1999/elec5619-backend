package com.freshshare.request;

import lombok.Data;

@Data
public class UpdateIssueStatusRequestParam {

    private String issueId;

    private String issueStatus;

    private String staffId;
}
