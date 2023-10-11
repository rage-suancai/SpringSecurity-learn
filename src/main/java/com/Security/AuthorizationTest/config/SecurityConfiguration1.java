/**package com.Security.AuthorizationTest.config;

import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration1 {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        return http
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers("/static/**").permitAll();
                    auth.requestMatchers("/").hasAnyRole("user", "admin");
                    auth.anyRequest().hasRole("admin");
                })
                .formLogin(conf -> {
                    conf.loginPage("/login");
                    conf.loginProcessingUrl("/doLogin");
                    conf.defaultSuccessUrl("/");
                    conf.permitAll();

                    conf.usernameParameter("username");
                    conf.passwordParameter("password");
                })
                .rememberMe(conf -> {
                    conf.alwaysRemember(false);
                    conf.rememberMeParameter("remember-me");
                    conf.rememberMeCookieName("xxxx");
                })
                .logout(conf -> {
                    conf.logoutUrl("/doLogout");
                    conf.logoutSuccessUrl("/login");
                    conf.permitAll();
                })
                .csrf(AbstractHttpConfigurer::disable)
                .build();

    }

    @Bean
    public DataSource dataSource() {

        return new PooledDataSource("com.mysql.cj.jdbc.Driver",
                "jdbc:mysql://localhost:3306/test?serverTimezone=Asia/Shanghai",
                "root", "123456");

    }
    @Bean
    public SqlSessionFactoryBean sqlSessionFactoryBean(DataSource dataSource) {

        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource); return bean;

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}**/
