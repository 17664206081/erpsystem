package com.fzy.erpsystem.entity;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @program: Estimate
 * @description:
 * @author: fzy
 * @date: 2019/05/16 21:42:48
 **/
@Data
public class Estimate {

    private Long storeId;

    private Long goodId;

    private String estimateDate;

    private String consultDate;

    private String week;

    private String goodsName;

    //参考营业额
    private BigDecimal consultBusiness;

    //预估营业额
    private BigDecimal estimateBusiness;

}
