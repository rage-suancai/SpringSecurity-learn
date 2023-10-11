package com.Security.SecurityConfig.mapper;

import com.Security.SecurityConfig.entity.Account;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AccountMapper {

    @Select("select username,password from db_user where username = #{username}")
    Account findAccountByName(String username);

}
