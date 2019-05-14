package com.fzy.erpsystem.dao;


import com.fzy.erpsystem.entity.Store;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @program: StoreMapper
 * @description:
 * @author: fzy
 * @date: 2019/05/13 19:19:19
 **/
@Mapper
public interface StoreMapper {

    @Insert("insert INTO t_store(name, address, phone) values(#{name},#{address},#{phone})")
    @Options(useGeneratedKeys = true,keyProperty = "id")
    int insert(Store store);


    @Select("select * from t_store")
    List<Store> findAll();


    @Update("update t_store set name=#{name},address=#{address},phone=#{phone} where id=#{id}")
    int update (Store store);


    @Delete("delete from t_store where id=#{id}")
    int delete(Long id);


    @Select("select * from t_store where name like CONCAT('%',#{name},'%')")
    List<Store> search(@Param("name") String name);

    @Select("select * from t_store where id=#{id}")
    Store findOne(Long id);
}
