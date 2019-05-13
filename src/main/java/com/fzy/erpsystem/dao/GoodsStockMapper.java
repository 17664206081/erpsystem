package com.fzy.erpsystem.dao;

import com.fzy.erpsystem.entity.GoodsStock;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @program: GoodsStockMapper
 * @description:
 * @author: fzy
 * @date: 2019/05/12 00:03:25
 **/
@Mapper
public interface GoodsStockMapper {

    @Insert("insert into t_goods_stock(goods_name,supplier_name,goods_amt,goods_price,Kc)" +
            " values(#{goodsName},#{supplierName},#{goodsAmt},#{goodsPrice},#{kc})")
    int kc(GoodsStock goodsStock);


    @Select("select * from t_goods_stock where Kc=#{type}")
    List<GoodsStock> findAll(String type);


    @Select("select * from t_goods_stock where Kc=#{type} and id=#{id}")
    List<GoodsStock> search(@Param("type") String type, @Param("id") Long id);

    //@Select("select sum(goods_amt) as goods_amt, Kc ,goods_name, supplier_name from t_goods_stock group by goods_name,Kc,supplier_name")

    @Select("<script>"
    +"select sum(goods_amt) as goods_amt, Kc ,goods_name, supplier_name from t_goods_stock"
    +"<if test= 'goodsName!=null' >"
    + "where goods_name=#{goodsName}"
    + "</if>"
    +"group by goods_name,Kc,supplier_name"
    + "</script>")
    List<GoodsStock> num(@Param("goodsName") String goodsName);


}
