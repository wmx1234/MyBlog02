package com.xiao.blog.repository;

import com.xiao.blog.model.Article;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import java.util.List;


public interface ArticleRepository extends ElasticsearchRepository<Article,Integer> {
    @Query
    List<Article> findArticlesByArticleContentAndArticleTitle(String articleContent,String articleTitle);

    int deleteArticleById(Integer id);

}
