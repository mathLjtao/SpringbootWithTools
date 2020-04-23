package com.ljtao.springbootvalidator.config;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ljtao.springbootvalidator.utils.JsonData;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @author ljtao3 on 2020/4/23
 * 全局处理相应数据
 */
@RestControllerAdvice("com.ljtao.springbootvalidator.controller")
public class ResponseControllerAdvice implements ResponseBodyAdvice<Object> {
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> aClass) {
        //如果数据本身是JsonData格式就不用处理了
        return !returnType.getGenericParameterType().equals(JsonData.class);
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {

        if(methodParameter.getGenericParameterType().equals(String.class)){
            // 将数据包装在JsonData里后，再转换为json字符串响应给前端.
            //beforeBodyWrite方法里包装数据无法对String类型的数据直接进行强转，所以要进行特殊处理
            String s1 = JSONObject.toJSONString(JsonData.success(o));
            return s1;
        }
        return JsonData.success(o);
    }
}
