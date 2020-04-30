package com.ljtao3.springbootelasticsearch.config;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.ljtao3.springbootelasticsearch.entity.Article;

/**
 * @author ljtao
 * @date 2020/4/27
 */


//泛型的参数分别是实体类型和主键类型
public interface ArticleSearchRepository extends ElasticsearchRepository<Article, Long>{

}