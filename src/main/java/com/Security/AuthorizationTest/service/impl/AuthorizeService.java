package com.Security.AuthorizationTest.service.impl;

import com.Security.AuthorizationTest.entity.Account;
import com.Security.AuthorizationTest.mapper.AccountMapper;
import jakarta.annotation.Resource;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthorizeService implements UserDetailsService {

    @Resource
    private AccountMapper accountMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Account account = accountMapper.findAccountByName(username);
        if (account == null) throw new UsernameNotFoundException("用户名或密码错误");
        return User
                .withUsername(account.getUsername())
                .password(account.getPassword())
                .roles(account.getRole())
                .build();

    }

}
