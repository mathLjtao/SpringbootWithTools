package com.ljtao.springbootvalidator.valid;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.NotNull;

import java.lang.annotation.Documented;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

//自定义校验注解

@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = { ListValueConstraintValidator.class})
public @interface ListValue {
    //校验错误返回信息
    String message() default "{com.ljtao.springbootvalidator.valid.ListValue.message}";//信息在ValidationMessages.properties资源文件上

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

    String[] value() default {};
}
