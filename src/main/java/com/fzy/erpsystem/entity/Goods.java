package com.fzy.erpsystem.entity;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @program: Goods
 * @description:
 * @author: fzy
 * @date: 2019/05/14 18:58:00
 **/
@Data
public class Goods {

    private Long id;

    private String billNo;

    private String goodsName;

    private Long supplierId;

    private String supplierName;

    private BigDecimal price;

    private BigDecimal stock;

}
