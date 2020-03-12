package com.ljtao3.springbootkafka.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ljtao3 on 2020/3/5
 */
@RestController
@RequestMapping("/test")
public class TestRestController {
    /*
        测试是否运行正常
     */
    @GetMapping("/fun1")
    public String fun1(){
        return "fun1 success!";
    }
}
