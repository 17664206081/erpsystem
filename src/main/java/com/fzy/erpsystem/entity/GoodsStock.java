package com.fzy.erpsystem.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

/**
 * @program: GoodsStock
 * @description:
 * @author: fzy
 * @date: 2019/05/10 11:28:26
 **/
@Data
public class GoodsStock {

    private Long id;

    private Long storeId;

    private String storeName;

    private Long goodsId;

    private String goodsName;

    private BigDecimal goodsAmt;

    private BigDecimal goodsPrice;

    private Long supplierId;

    private String supplierName;

    private Kc kc;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" ,locale = "zh", timezone = "GMT+8")
    private Date buseDate;

    private Integer status;

    private Long homeId;

    private String ckName;

   /* @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GoodsStock that = (GoodsStock) o;
        return Objects.equals(storeId, that.storeId) &&
                Objects.equals(goodsId, that.goodsId) &&
                Objects.equals(supplierId, that.supplierId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(storeId, goodsId, supplierId);
    }*/
}

