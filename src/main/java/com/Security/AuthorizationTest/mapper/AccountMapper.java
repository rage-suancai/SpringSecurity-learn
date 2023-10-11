package com.Security.AuthorizationTest.mapper;

import com.Security.AuthorizationTest.entity.Account;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AccountMapper {

    @Select("select username,password,role from db_user where username = #{username}")
    Account findAccountByName(String username);

}
