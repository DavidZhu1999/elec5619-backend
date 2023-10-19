package com.freshshare.business.request;

import lombok.Data;

@Data
public class updateBusinessStatusRequestParam {

    private String businessId;

    private String businessStatus;

}
