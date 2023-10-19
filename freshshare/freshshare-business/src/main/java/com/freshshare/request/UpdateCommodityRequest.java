package com.freshshare.request;

import lombok.Data;

@Data
public class UpdateCommodityRequest {

    private String commodityId;

    private String commodityName;

    private String commodityPrice;

    private String commodityStatus;
}
