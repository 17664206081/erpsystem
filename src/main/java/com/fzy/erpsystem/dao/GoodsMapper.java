package com.fzy.erpsystem.dao;

import com.fzy.erpsystem.entity.Goods;
import com.fzy.erpsystem.entity.Supplier;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @program: GoodsMapper
 * @description:
 * @author: fzy
 * @date: 2019/05/14 18:58:31
 **/
@Mapper
public interface GoodsMapper {

    @Insert("insert INTO t_goods(bill_no, goods_name,supplier_id,supplier_name,price,stock) " +
            "values(#{billNo},#{goodsName},#{supplierId},#{supplierName},#{price},#{stock})")
    @Options(useGeneratedKeys = true,keyProperty = "id")
    int save(Goods goods);

    @Select("select * from t_goods where bill_no=#{billNo}")
    Goods findBillNo(String billNo);

    @Select("select * from t_goods")
    List<Goods> findAll();

    @Select("select * from t_goods where goods_name like CONCAT('%',#{goodsName},'%')")
    List<Goods> search(String goodsName);

    @Delete("delete from t_goods where id=#{id}")
    int delete(Long id);

    @Select("select * from t_goods where supplier_id=#{supplierId}")
    List<Goods> findBySupplier(Long supplierId);

    @Select("select * from t_goods where id=#{goodsId}")
    Goods findone(Long goodsId);

}
