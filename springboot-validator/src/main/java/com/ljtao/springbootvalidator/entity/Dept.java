package com.ljtao.springbootvalidator.entity;

import com.ljtao.springbootvalidator.valid.ListValue;
import lombok.Data;

/**
 * @author ljtao3 on 2020/5/14
 */
@Data
public class Dept {
    private String deptId;
    @ListValue(value = {"开发部","测试部","运维部"})
    private String deptName;
}
