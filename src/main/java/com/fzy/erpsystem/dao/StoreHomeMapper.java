package com.fzy.erpsystem.dao;


import com.fzy.erpsystem.entity.Store;
import com.fzy.erpsystem.entity.StoreHome;
import com.fzy.erpsystem.entity.Supplier;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @program: StoreMapper
 * @description:
 * @author: fzy
 * @date: 2019/05/13 19:19:19
 **/
@Mapper
public interface StoreHomeMapper {

    @Select("select * from t_storehome")
    List<StoreHome> findAll();

    @Insert("insert INTO t_storehome(ck_name) values(#{ckName})")
    @Options(useGeneratedKeys = true,keyProperty = "homeId")
    int insert(StoreHome storeHome);

    @Delete("delete from t_storehome where home_id=#{id}")
    int delete(Long id);

    @Select("select * from t_storehome where ck_name like CONCAT('%',#{name},'%')")
    List<StoreHome> search(@Param("name") String name);

    @Select("select * from t_storehome where home_id=#{id}")
    StoreHome findOne(Long id);

//    @Select("select * from t_storehome where ck_name like CONCAT('%',#{name},'%')")
//    List<StoreHome> search(@Param("name") String name);
}
