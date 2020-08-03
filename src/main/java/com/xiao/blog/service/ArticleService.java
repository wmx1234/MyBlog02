package com.xiao.blog.service;

import com.xiao.blog.model.Article;
import com.xiao.blog.pojo.param.Params;
import com.xiao.blog.vo.ArticleVO;
import com.xiao.blog.vo.ClassifyVO;

import java.util.List;

/**
 * @author wangmx
 * @create 2019-11-30 21:01
 * @Desc
 */
public interface ArticleService {

    public void save(Params params);

    public List<ArticleVO> getArticles(Article article);

    public List<ArticleVO> getArticleByUserId(int userId);

    public List<Article> getArticleList(int userId);

    public List archive(Integer userId);

    public List<ClassifyVO> classify(Integer userId);

    ArticleVO getArticleById(Integer id);
}
