package com.freshshare.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.freshshare.entity.Order;
import com.freshshare.entity.Review;
import com.freshshare.exception.ReviewException;
import com.freshshare.exception.ReviewExceptionEnum;
import com.freshshare.mapper.ReviewMapper;
import com.freshshare.service.ReviewService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReviewServiceImpl extends ServiceImpl<ReviewMapper, Review> implements ReviewService {

    @Resource
    ReviewMapper reviewMapper;

    @Override
    public Map<Object, Object> getReviewByOrderId(String orderId) {
        Map<String, Object> review = null;
        review = this.getMap(new LambdaQueryWrapper<Review>()
                .eq(Review::getOrderId, orderId)
        );
        if (review == null){
            throw new ReviewException(ReviewExceptionEnum.GET_ONE_REVIEW_ERROR);
        }
        Map<Object,Object> returnResult = new HashMap<>();
        returnResult.put("review",review);
        return returnResult;
    }

    @Override
    public Map<Object, Object> getReviewsByBusinessId(String businessId) {
        List<String> orders = null;
        try {
            orders = reviewMapper.getOrdersByBusinessId(businessId);
            if (orders == null) {
                throw new ReviewException(ReviewExceptionEnum.GET_ONE_REVIEW_ERROR);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        List<Review> reviews;
        try {
            reviews = this.listByIds(orders);
            if (reviews == null) {
                throw new ReviewException(ReviewExceptionEnum.GET_ONE_REVIEW_ERROR);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        Map<Object, Object> returnResult = new HashMap<>();
        returnResult.put("reviews", reviews);
        return returnResult;
    }

    @Override
    public void addReview(String orderId, String reviewContent, Double reviewScore) {
        Review oldReview = this.getOne(new LambdaQueryWrapper<Review>()
                .eq(Review::getOrderId, orderId)
        );
        if (oldReview != null) {
            throw new ReviewException(ReviewExceptionEnum.ADD_REVIEW_ERROR);
        }
        Review review = new Review();
        review.setOrderId(orderId);
        review.setReviewContent(reviewContent);
        review.setReviewScore(reviewScore);
        try {
            this.save(review);
            reviewMapper.updateOrderStatus(orderId);
        } catch (Exception e) {
            throw new ReviewException(ReviewExceptionEnum.ADD_REVIEW_ERROR);
        }
    }
}
