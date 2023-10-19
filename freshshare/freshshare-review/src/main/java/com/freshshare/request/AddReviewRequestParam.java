package com.freshshare.request;

import lombok.Data;

@Data
public class AddReviewRequestParam {

    private String orderId;

    private String reviewContent;

    private Double reviewScore;

}
