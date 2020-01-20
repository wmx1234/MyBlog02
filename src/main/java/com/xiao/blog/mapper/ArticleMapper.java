package com.xiao.blog.mapper;

import com.xiao.blog.model.Article;
import com.xiao.blog.vo.Archive;
import com.xiao.blog.vo.ArticleVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface ArticleMapper {

    int deleteArticleById(Integer id);

    int insert(Article article);

    int updateArticleById(Article article);

    List<ArticleVO> getArticles(Article article);

    @Select("select count(id) from blog_article where id = #{id}")
    int articleIsExist(Integer id);

    @Select("select max(id) from blog_article")
    int getLastArticleId();

}