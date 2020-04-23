package com.ljtao.springbootspringsecurity.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.plugin.liveconnect.SecurityContextHelper;

/**
 * @author ljtao3 on 2020/4/23
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/hello")
    public String hello(){
        return "hello!";
    }
    /*
    获取用户信息
    安全上下文，用户通过Spring Security 的校验之后，验证信息存储在SecurityContext中
     */
    @GetMapping("/info")
    public String userInfo(){
        String result="";
        Object details = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(details instanceof UserDetails){
            result=((UserDetails) details).getUsername();
        }else{
            result=details.toString();
        }
        return "currentUser is "+result;
    }
}
