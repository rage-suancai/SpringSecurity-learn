package com.Security.SecurityConfig.mapper;

import com.Security.SecurityConfig.entity.Account;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    @Select("select * from test where username = #{username}")
    Account findAccountByName(String username);

}
