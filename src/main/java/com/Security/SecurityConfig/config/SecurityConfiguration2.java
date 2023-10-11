/**package com.Security.SecurityConfig.config;

import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;

import javax.sql.DataSource;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration2 {

    @Bean
    public UserDetailsService userDetailsService(DataSource dataSource,
                                                 PasswordEncoder encoder) {

        JdbcUserDetailsManager manager = new JdbcUserDetailsManager(dataSource);

        manager.createUser(User
                .withUsername("user")
                .password(encoder.encode("password"))
                .roles("user")
                .build());
        return manager;

    }

    @Bean
    public DataSource dataSource() {

        return new PooledDataSource("com.mysql.cj.jdbc.Driver",
                "jdbc:mysql://localhost:3306/test?serverTimezone=Asia/Shanghai",
                "root", "123456");

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    private AuthenticationManager authenticationManager(UserDetailsManager manager,
                                                        PasswordEncoder encoder) {

        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(manager);
        provider.setPasswordEncoder(encoder); return new ProviderManager(provider);

    }

}**/
