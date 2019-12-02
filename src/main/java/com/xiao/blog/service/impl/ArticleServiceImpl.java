package com.xiao.blog.service.impl;

import com.xiao.blog.mapper.ArticleMapper;
import com.xiao.blog.mapper.RelationMapper;
import com.xiao.blog.model.Article;
import com.xiao.blog.model.Categories;
import com.xiao.blog.model.Params;
import com.xiao.blog.model.Relation;
import com.xiao.blog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wangmx
 * @create 2019-11-30 21:02
 * @Desc
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    ArticleMapper articleMapper;

    @Autowired
    RelationMapper relationMapper;

    @Override
    public void save(Params params) {

        articleMapper.insert(params.getObject("article",Article.class));

        relationMapper.batchInsertArticleLabelRelation(params.getList("labels"));

        relationMapper.insertArticleCategoriesRelation(params.getObject("categories", Relation.class));
    }

}
