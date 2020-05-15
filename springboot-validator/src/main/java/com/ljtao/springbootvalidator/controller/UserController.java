package com.ljtao.springbootvalidator.controller;

import com.ljtao.springbootvalidator.config.ApiException;
import com.ljtao.springbootvalidator.entity.Dept;
import com.ljtao.springbootvalidator.entity.User;
import com.ljtao.springbootvalidator.utils.JsonData;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author ljtao3 on 2020/4/23
 */
@RestController
@RequestMapping("/user")
public class UserController {


    @PostMapping("/add")
    public JsonData addUser(@RequestBody @Valid User user){
        return JsonData.success(user);
    }

    //测试抛出自定义异常
    @GetMapping("testApiEx")
    public JsonData testApiEx(){
        throw new ApiException("apiEx");
    }

    @GetMapping("testResult")
    public String testResult(){
        return "success";
    }
    @GetMapping("testResult2")
    public int testResult2(){
        return 2;
    }


    //测试自定义校验注解
    @PostMapping("/testMyValid")
    public JsonData testMyValid(@RequestBody @Valid Dept dept){
        return JsonData.success(dept);
    }


}
