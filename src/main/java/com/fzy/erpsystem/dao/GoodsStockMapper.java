package com.fzy.erpsystem.dao;

import com.fzy.erpsystem.entity.GoodsStock;
import com.fzy.erpsystem.entity.GoodsStockDto;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * @program: GoodsStockMapper
 * @description:
 * @author: fzy
 * @date: 2019/05/12 00:03:25
 **/
@Mapper
public interface GoodsStockMapper {

    @Insert("insert into t_goods_stock(goods_id,goods_name,supplier_id, supplier_name,store_id,store_name,goods_amt,goods_price,Kc)" +
            " values(#{goodsId},#{goodsName},#{supplierId},#{supplierName},#{storeId},#{storeName},#{goodsAmt},#{goodsPrice},#{kc})")
    int kc(GoodsStock goodsStock);


    @Select("select * from t_goods_stock where Kc=#{type}")
    List<GoodsStock> findAll(String type);


    @Select("select * from t_goods_stock where Kc=#{type} and id=#{id}")
    List<GoodsStock> search(@Param("type") String type, @Param("id") Long id);

    //@Select("select sum(goods_amt) as goods_amt, Kc ,goods_name, supplier_name from t_goods_stock group by goods_name,Kc,supplier_name")

    @Select("<script>"
    +"select sum(goods_amt) as goods_amt, kc,goods_id,goods_name,store_id,store_name from t_goods_stock"
    +"<if test= 'goodsName!=null' >"
    + "where goods_name=#{goodsName}"
    + "</if>"
    +"group by kc,goods_id,goods_name,store_id,store_name"
    + "</script>")
    List<GoodsStock> num(@Param("goodsName") String goodsName);


    @Select("select sum(goods_amt) as goods_amt, kc from t_goods_stock where goods_id=#{goodsId} and store_id=#{storeId} group by kc")
    List<GoodsStock> findGoodsStock(@Param("goodsId") Long goodsId,@Param("storeId") Long storeId);

    @Select("<script>"
            +"select * from t_goods_stock where kc=#{type}"
            +"<if test= 'storeName!=null' >"
            + "and store_name=#{storeName}"
            + "</if>"
            +"and buse_date is null"
            + "</script>")
    List<GoodsStock> manage(@Param("type") String type, @Param("storeName") String storeName);

    @Update("update t_goods_stock set buse_date= now() where id=#{id}")
    int yasuo(Long id);



    @Select("<script>"
            +"select * from t_goods_stock where kc=#{type}"
            +"<if test= 'storeId!=null' >"
            + "and store_id=#{storeId}"
            + "</if>"
            +"and buse_date is not null"
            + "</script>")
    List<GoodsStock> report(@Param("type") String type, @Param("storeId") Long storeId);


    @Select("select DATE_FORMAT(buse_date,'%Y-%m-%d') as buse_date, sum(goods_amt*goods_price) as total from t_goods_stock where kc=#{type} and buse_date is not null group by DATE_FORMAT(buse_date,'%Y-%m-%d')")
    List<GoodsStockDto> reportDto(@Param("type") String type);

    @Select("<script>"
            +"select sum(goods_amt * goods_price) as price from t_goods_stock where store_id=#{storeId} and kc='CK'"
            +"<if test= 'goodsId!=null' >"
            + "and goods_id=#{goodsId}"
            + "</if>"
            +"and date_format(buse_date,'%Y-%m-%d')=#{buseDate}"
            + "</script>")
    BigDecimal findBusedDay(@Param("buseDate") String buseDate, @Param("storeId") Long storeId,@Param("goodsId") Long goodsId);


}
