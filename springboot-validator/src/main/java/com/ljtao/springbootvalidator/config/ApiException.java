package com.ljtao.springbootvalidator.config;

/**
 * @author ljtao3 on 2020/4/23
 * 自定义异常
 */

public class ApiException extends RuntimeException {
    private String code;
    private String msg;

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public ApiException(){
        this("1001","接口错误");
    }
    public ApiException(String msg){
        this("1001",msg);
    }
    public ApiException(String code,String msg){
        super(msg);
        this.code=code;
        this.msg=msg;
    }
}
