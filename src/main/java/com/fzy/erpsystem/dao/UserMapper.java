package com.fzy.erpsystem.dao;

import com.fzy.erpsystem.entity.User;
import org.apache.ibatis.annotations.*;

/**
 * @program: UserMapper
 * @description:
 * @author: fzy
 * @date: 2019/05/11 09:03:23
 **/
@Mapper
public interface UserMapper {

    @Insert("INSERT INTO T_USER(user_name,password) VALUES(#{userName},#{password})")
    @Options(useGeneratedKeys = true,keyProperty = "id")
    int insert(User user);


    @Select("select * from T_USER WHERE user_name=#{userName} AND password=#{password}")
    User findUserByUserName(@Param("userName") String userName, @Param("password")String password);

}
