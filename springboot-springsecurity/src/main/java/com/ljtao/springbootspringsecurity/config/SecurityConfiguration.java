package com.ljtao.springbootspringsecurity.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author ljtao3 on 2020/4/23
 *
 * springsecurity的配置
 *
 * configure(WebSecurity) 配置Security的filter链
 *

 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    //configure(HttpSecurity) 配置如何通过连接器的保护
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/user/**").hasRole("USER")
                .antMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin().and()
                .httpBasic();
    }


    //configure(AuthenticationManagerBuilder) 配置user-detail服务
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        /*
            这里随意加入两个用户
         */
        auth.inMemoryAuthentication()
                .withUser("admin1") // 管理员，同事具有 ADMIN,USER权限，可以访问所有资源
                .password("{noop}admin1")  //
                .roles("ADMIN", "USER")
                .and()
                .withUser("user1").password("{noop}user1") // 普通用户，只能访问 /user/**
                .roles("USER");
    }
}
