package com.ljtao3.springbootelasticsearch.config;

import org.springframework.data.annotation.Persistent;

import java.lang.annotation.*;

/**
 * @author ljtao
 * @date 2020/4/27
 * 在需要建立索引的类上加上@Document注解，即表明这个实体需要进行索引
 */
@Persistent
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface Document {

    String indexName();//索引库的名称，个人建议以项目的名称命名

    String type() default "";//类型，个人建议以实体的名称命名

    short shards() default 5;//默认分区数

    short replicas() default 1;//每个分区默认的备份数

    String refreshInterval() default "1s";//刷新间隔

    String indexStoreType() default "fs";//索引文件存储类型
}