package com.fzy.erpsystem.dao;

import com.fzy.erpsystem.entity.Supplier;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @program: SupplierMapper
 * @description:
 * @author: fzy
 * @date: 2019/05/11 09:56:49
 **/
@Mapper
public interface SupplierMapper {

    @Insert("insert INTO t_supplier(name, address, phone) values(#{name},#{address},#{phone})")
    @Options(useGeneratedKeys = true,keyProperty = "id")
    int insert(Supplier supplier);


    @Select("select * from t_supplier")
    List<Supplier> findAll();


    @Update("update t_supplier set name=#{name},address=#{address},phone=#{phone} where id=#{id}")
    int update (Supplier supplier);


    @Delete("delete from t_supplier where id=#{id}")
    int delete(Long id);


    @Select("select * from t_supplier where name like CONCAT('%',#{name},'%')")
    List<Supplier> search(@Param("name") String name);

    @Select("select * from t_supplier where id=#{id}")
    Supplier findOne(Long id);

}
