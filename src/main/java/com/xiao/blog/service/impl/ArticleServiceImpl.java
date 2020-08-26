package com.xiao.blog.service.impl;

import cn.hutool.core.date.DateUtil;
import com.xiao.blog.mapper.ArticleMapper;
import com.xiao.blog.mapper.RelationMapper;
import com.xiao.blog.model.Article;
import com.xiao.blog.pojo.param.Params;
import com.xiao.blog.service.ArticleService;
import com.xiao.blog.shiro.ShiroKit;
import com.xiao.blog.util.ArticleUtil;
import com.xiao.blog.vo.ArticleVO;
import com.xiao.blog.vo.ClassifyVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wangmx
 * @create 2019-11-30 21:02
 * @Desc
 */
@Service("articleService")
public class ArticleServiceImpl implements ArticleService {

    @Resource
    ArticleMapper articleMapper;

    @Resource
    RelationMapper relationMapper;

    @Override

    public void save(Params params) {
        //保存博客
        articleMapper.save(params.getObject("article",Article.class));

    }

    @Override
    public void delete(Integer id) {
        articleMapper.delete(id);
    }

    @Override
    public void update(Article article) {

    }

    @Override
    public List<ArticleVO> getArticleListByTagsId(Integer id) {
        return articleMapper.getArticleListByTagsId(id);
    }

    @Override
    public List<ArticleVO> getAllArticles() {
        return articleMapper.getAllArticles();
    }

    @Override
    public ArticleVO getArticleById(int userId) {
        return null;
    }

//    private void insert(Params params){
//
//        Article article = params.getObject("article",Article.class);
//
//        article.setArticleDigest(ArticleUtil.buildArticleTabloid(article.getArticleHtmlContent()));
//
//        article.setCreateDate(DateUtil.today());
//
//        article.setUserId(ShiroKit.getUser().getId());
//
//        article.setLastArticleId(articleMapper.getLastArticleId());
//
//        articleMapper.insert(article);
//
//        relationMapper.batchInsertArticleLabelRelation(params.getList("tags"));
//
//        //relationMapper.insertArticleCategoriesRelation(params.getObject("categories", Relation.class));
//    }
//
//    private void update(Params params){
//
//        Article article = params.getObject("article",Article.class);
//
//        article.setArticleDigest(ArticleUtil.buildArticleTabloid(article.getArticleHtmlContent()));
//
//        article.setUpdateDate(DateUtil.today());
//
//        articleMapper.updateArticleById(article);
//
//        relationMapper.deleteLabelsByArticleId(article.getId());
//
//        relationMapper.batchInsertArticleLabelRelation(params.getList("labels"));
//
//        //relationMapper.deleteCategoriesByArticleId(article.getId());
//
//        //relationMapper.insertArticleCategoriesRelation(params.getObject("categories", Relation.class));
//    }
}
