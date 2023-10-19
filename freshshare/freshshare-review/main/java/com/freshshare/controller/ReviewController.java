package com.freshshare.controller;

import cn.dev33.satoken.annotation.SaCheckRole;
import com.freshshare.request.AddReviewRequestParam;
import com.freshshare.request.GetReviewByOrderIdRequestParam;
import com.freshshare.request.GetReviewsByBusinessIdRequestParam;
import com.freshshare.response.ReviewResponse;
import com.freshshare.response.ReviewResponseEnum;
import com.freshshare.service.ReviewService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/review")
public class ReviewController {

    @Resource
    private ReviewService reviewService;


    @PostMapping("/getReviewByOrderId")
    public ReviewResponse getReviewByOrderId(@RequestBody GetReviewByOrderIdRequestParam param){
        return ReviewResponse.success(ReviewResponseEnum.GET_REVIEW_BY_ORDER_ID,
                reviewService.getReviewByOrderId(param.getOrderId()));
    }

    @PostMapping("/getReviewsByBusinessId")
    public ReviewResponse getReviewsByBusinessId(@RequestBody GetReviewsByBusinessIdRequestParam param){
        return ReviewResponse.success(ReviewResponseEnum.GET_REVIEWS_BY_BUSINESS_ID,
                reviewService.getReviewsByBusinessId(param.getBusinessId()));
    }

    @PostMapping("/addReview")
    public ReviewResponse addReview(@RequestBody AddReviewRequestParam param){
        reviewService.addReview(param.getOrderId(),param.getReviewContent(),param.getReviewScore());
        return ReviewResponse.success(ReviewResponseEnum.ADD_REVIEW);
    }

}
