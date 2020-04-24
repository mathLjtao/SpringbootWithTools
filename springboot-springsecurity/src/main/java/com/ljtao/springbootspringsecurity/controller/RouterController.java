package com.ljtao.springbootspringsecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author ljtao
 * @date 2020/4/23
 */
@Controller
@RequestMapping
public class RouterController {


    @RequestMapping({"/","index"})
    public String index(){
        return "index";
    }
}
