package com.xiao.blog.mapper;

import com.xiao.blog.model.Article;
import com.xiao.blog.model.Label;
import com.xiao.blog.model.Relation;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ArticleMapper {


    int insert(Article record);

    int deleteByPrimaryKey(Integer id);

    int updateByPrimaryKey(Article record);

    Article selectByPrimaryKey(Integer id);

    List<Article> selectAll();

    int insertArticleLabelRelation(List<Relation> relations);
}