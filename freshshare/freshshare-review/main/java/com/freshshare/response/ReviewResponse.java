package com.freshshare.response;

public class ReviewResponse extends ResponseObj{
    public ReviewResponse(ReviewResponseEnum reviewResponseEnum){
        super(reviewResponseEnum.getCode(), reviewResponseEnum.getMsg());
    }

    public ReviewResponse(ReviewResponseEnum reviewResponseEnum, Object data){
        super(reviewResponseEnum.getCode(), reviewResponseEnum.getMsg(), data);

    }

    public static ReviewResponse success(ReviewResponseEnum reviewResponseEnum){
        return new ReviewResponse(reviewResponseEnum,null);
    }

    public static ReviewResponse success(ReviewResponseEnum reviewResponseEnum, Object data){
        return new ReviewResponse(reviewResponseEnum, data);
    }
}
