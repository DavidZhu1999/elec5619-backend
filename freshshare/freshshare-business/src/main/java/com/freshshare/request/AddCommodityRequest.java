package com.freshshare.request;

import lombok.Data;

@Data
public class AddCommodityRequest {

    private String businessId;

    private String commodityName;

    private String commodityPrice;

    private String typeId;
}
