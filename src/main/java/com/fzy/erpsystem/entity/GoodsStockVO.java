package com.fzy.erpsystem.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.*;

@Data
public class GoodsStockVO {

    private Long storeId;

    private String storeName;

    private Long goodsId;

    private String goodsName;

    private BigDecimal goodsAmt;

    private Long homeId;

    private Kc kc;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" ,locale = "zh", timezone = "GMT+8")
    private Date buseDate;

    private Integer status;

    private String reportTime;

    private String[] stringTime;

    private BigDecimal[] numDate;
}
