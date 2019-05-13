package com.fzy.erpsystem.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @program: GoodsStock
 * @description:
 * @author: fzy
 * @date: 2019/05/10 11:28:26
 **/
@Data
public class GoodsStock {

    private Long id;

    private String goodsName;

    private BigDecimal goodsAmt;

    private BigDecimal goodsPrice;

    private String supplierName;

    private Kc kc;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date time;

    private Integer status;

}