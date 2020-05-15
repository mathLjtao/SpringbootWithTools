package com.ljtao.springbootvalidator.valid;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.HashSet;
import java.util.Set;

/**
 * @author ljtao3 on 2020/5/14
 */
//需要先设置两个泛型，一个是自定义注解类型，一个是想要检验的参数类型
public class ListValueConstraintValidator implements ConstraintValidator<ListValue,String> {
    private Set<String> set=new HashSet();

    @Override
    public void initialize(ListValue constraintAnnotation) {
        String[] value = constraintAnnotation.value();
        for (String s:value){
            set.add(s);
        }
    }

    //返回参数是否合法方法
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return set.contains(value);
    }
}
