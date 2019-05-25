package com.fzy.erpsystem.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @program: GoodsStockDto
 * @description:
 * @author: fzy
 * @date: 2019/05/23 17:55:13
 **/
@Data
public class GoodsStockDto {

    private Long storeId;

    private String storeName;

    private BigDecimal total;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date buseDate;

    private BigDecimal goodsAmt;

    private BigDecimal goodsPrice;

}
