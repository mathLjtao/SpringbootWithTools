package com.ljtao3.springbootelasticsearch;

import com.ljtao3.springbootelasticsearch.config.ArticleSearchRepository;
import com.ljtao3.springbootelasticsearch.entity.Article;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
class SpringbootElasticsearchApplicationTests {
    @Autowired
    private ArticleSearchRepository articleSearchRepository;

    //运行后，地址输入：http://192.168.37.129:9200/article_index/_mapping
    @Test
    void contextLoads() {
        System.out.println("演示初始化");
    }

    //运行后，地址输入：http://192.168.37.129:9200/article_index/article/1
    @Test
    public void testSave(){
        Article article=new Article();
        article.setId(1L);
        article.setTitle("elasticsearch教程");
        article.setAbstracts("spring-data-elastichSearch");
        article.setContent("SpringBoot与spring-data-elastichSearch整合");
        article.setPostTime(new Date());
        article.setClickCount(100l);
        articleSearchRepository.save(article);
    }
    //
    @Test
    void testSearch(){

    }
}
