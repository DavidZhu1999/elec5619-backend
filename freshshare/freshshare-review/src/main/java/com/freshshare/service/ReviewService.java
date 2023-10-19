package com.freshshare.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.freshshare.entity.Review;

import java.util.Map;

public interface ReviewService extends IService<Review> {

    Map<Object,Object> getReviewByOrderId(String orderId);

    Map<Object,Object> getReviewsByBusinessId(String businessId);

    void addReview(String orderId, String reviewContent, Double reviewScore);

}
