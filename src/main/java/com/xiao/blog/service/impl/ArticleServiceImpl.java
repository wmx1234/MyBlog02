package com.xiao.blog.service.impl;

import cn.hutool.core.date.DateUtil;
import com.xiao.blog.mapper.ArticleMapper;
import com.xiao.blog.mapper.RelationMapper;
import com.xiao.blog.model.Article;
import com.xiao.blog.pojo.Relation;
import com.xiao.blog.pojo.param.Params;
import com.xiao.blog.service.ArticleService;
import com.xiao.blog.shiro.ShiroKit;
import com.xiao.blog.util.ArticleUtil;
import com.xiao.blog.vo.ArticleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wangmx
 * @create 2019-11-30 21:02
 * @Desc
 */
@Service("articleService")
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    ArticleMapper articleMapper;

    @Autowired
    RelationMapper relationMapper;

    @Override
    public void save(Params params) {

        Article article = params.getObject("article",Article.class);

        int isExist = articleMapper.articleIsExist(article.getId());

        if(isExist <= 0) insert(params);

        else update(params);

    }

    @Override
    public List<ArticleVO> getArticleByUserId(int userId) {
        Article a =new Article();
        a.setUserId(userId);
        return articleMapper.getArticles(a);

    }


    private void insert(Params params){

        Article article = params.getObject("article",Article.class);

        article.setArticleDigest(ArticleUtil.buildArticleTabloid(article.getArticleHtmlContent()));

        article.setCreateDate(DateUtil.today());

        article.setUserId(ShiroKit.getUser().getId());

        article.setLastArticleId(articleMapper.getLastArticleId());

        articleMapper.insert(article);

        relationMapper.batchInsertArticleLabelRelation(params.getList("labels"));

        //relationMapper.insertArticleCategoriesRelation(params.getObject("categories", Relation.class));
    }

    private void update(Params params){

        Article article = params.getObject("article",Article.class);

        article.setArticleDigest(ArticleUtil.buildArticleTabloid(article.getArticleHtmlContent()));

        article.setUpdateDate(DateUtil.today());

        articleMapper.updateArticleById(article);

        relationMapper.deleteLabelsByArticleId(article.getId());

        relationMapper.batchInsertArticleLabelRelation(params.getList("labels"));

        //relationMapper.deleteCategoriesByArticleId(article.getId());

        //relationMapper.insertArticleCategoriesRelation(params.getObject("categories", Relation.class));
    }
}
