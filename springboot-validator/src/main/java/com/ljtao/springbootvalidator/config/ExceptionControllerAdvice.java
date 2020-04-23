package com.ljtao.springbootvalidator.config;

import com.ljtao.springbootvalidator.utils.JsonData;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author ljtao3 on 2020/4/23
 * @ControllerAdvice或@RestControllerAdvice注解，
 * 这个类就配置成全局处理类了。（这个根据你的Controller层用的是@Controller还是@RestController来决定）
 */
@RestControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public JsonData MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e ){
        return JsonData.fail(e.getBindingResult().getAllErrors().get(0).getDefaultMessage());
    }
    @ExceptionHandler(ApiException.class)
    public JsonData ApiExceptionHandler(ApiException e){
        return JsonData.fail(e.getCode()+","+e.getMsg());
    }
}
