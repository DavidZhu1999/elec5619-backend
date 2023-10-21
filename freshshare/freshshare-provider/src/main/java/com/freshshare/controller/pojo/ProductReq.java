package com.freshshare.controller.pojo;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class ProductReq {
    private String commodity_id;
    private String business_id;
    private String commodity_name;
    private Double commodity_price;
    //  private LocalDateTime commodity_shelf_life;//生命周期 是int还是date 这是个问题
    private Integer commodity_num;
    private Integer commodity_sell_num;
    private Date commodity_upload_time;
    private Date commodity_down_time;
    private Date commodity_edit_time;
    private String commodity_status;
    private  String type_id;
    private String satokenBusiness;
}
