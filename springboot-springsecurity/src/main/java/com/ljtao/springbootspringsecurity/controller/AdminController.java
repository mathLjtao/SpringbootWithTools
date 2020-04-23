package com.ljtao.springbootspringsecurity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ljtao3 on 2020/4/23
 */
@RestController
@RequestMapping("/admin")
public class AdminController {
    @GetMapping("/hello")
    public String hello(){
        return "admin hello!";
    }
}
