package com.freshshare.request;

import lombok.Data;

@Data
public class AddIssueRequestParam {

    private String orderId;

    private String issueContent;
}
