package com.ljtao3.springbootelasticsearch.entity;

import com.ljtao3.springbootelasticsearch.config.Field;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.util.Date;

/**
 * @author ljtao
 * @date 2020/4/27
 */
@Document(indexName="article_index",type="article",shards=5,replicas=1,indexStoreType="fs",refreshInterval="-1")
public class Article implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 551589397625941750L;
    @Id
    private Long id;

    /**标题*/
    private String title;
    /**摘要*/
    private String abstracts;
    /**内容*/
    private String content;
    /**发表时间*/
    //@Field(format= DateFormat.date_time,index=FieldIndex.no,store=true,type= FieldType.Object)
    private Date postTime;
    /**点击率*/
    private Long clickCount;

    //setters and getters

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAbstracts() {
        return abstracts;
    }

    public void setAbstracts(String abstracts) {
        this.abstracts = abstracts;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getPostTime() {
        return postTime;
    }

    public void setPostTime(Date postTime) {
        this.postTime = postTime;
    }

    public Long getClickCount() {
        return clickCount;
    }

    public void setClickCount(Long clickCount) {
        this.clickCount = clickCount;
    }

    //toString
}