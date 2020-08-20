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

    /**
     * 保存博客
     * @param article
     */
    public void save(Article article);

    /**
     * 删除博客
     * @param id
     */
    public void delete(Integer id);

    /**
     * 删除博客
     * @param article
     */
    public void update(Article article);

    /**
     * 获取博客列表
     * @param article
     * @return
     */
    public List<ArticleVO> getArticleList(ArticleVO article);

    /**
     * 根据id获取博客
     * @param userId
     * @return
     */
    public ArticleVO getArticleById(int userId);


}
