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
    //授权

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

        //开启自动配置的登录功能：如果没有权限，就会跳转到登录页面！
        // /login 请求来到登录页
        // /login?error 重定向到这里表示登录失败
//        http.formLogin()
//                .usernameParameter("username")
//                .passwordParameter("password")
//                .loginPage("/toLogin")
//                .loginProcessingUrl("/login"); // 登陆表单提交请求

        http.csrf().disable();//关闭csrf功能:跨站请求伪造,默认只能通过post方式提交logout请求

        //开启自动配置的注销的功能
        // /logout 注销请求
        // .logoutSuccessUrl("/"); 注销成功来到首页
        http.logout().logoutSuccessUrl("/");

        //记住我
        http.rememberMe().rememberMeParameter("remember");
    }

    //密码编码：PasswordEncoder
    //在SpringSecurity 5.0+ 新增了很多加密的方法
    //认证
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
