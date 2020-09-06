package com.xiao.blog.service;

import cn.hutool.json.JSONObject;
import com.xiao.blog.model.Article;
import com.xiao.blog.pojo.param.Params;
import com.xiao.blog.vo.ArchiveVO;
import com.xiao.blog.vo.ArticleVO;
import com.xiao.blog.vo.ClassifyVO;

import java.util.List;
import java.util.Map;

/**
 * @author wangmx
 * @create 2019-11-30 21:01
 * @Desc
 */
public interface ArticleService {

    /**
     * 保存博客
     * @param articleVO
     */
    public void save(ArticleVO articleVO);

    /**
     * 删除博客
     * @param id
     */
    public void delete(Integer id);

    /**
     * 修改博客
     * @param article
     */
    public void update(Article article);

    /**
     * 获取所有博客列表
     * @return
     */
    public List<ArticleVO> getAllArticles();

    /**
     * 获取博客列表
     * @param article
     * @return
     */
    public List<ArticleVO> getArticleListByField(ArticleVO article);

    /**
     * 获取博客列表
     * @param id
     * @return
     */
    public List<ArticleVO> getArticleListByTagsId(Integer id);

    /**
     * 根据id获取博客
     * @param id
     * @return
     */
    public ArticleVO getArticleById(int id);

    /**
     * 获取置顶博客
     * @return
     */
    public ArticleVO getTopArticle();

    /**
     * 获取归档数据
     * @return
     */
    public List<ArticleVO> archive();

    /**
     * 获取归档页面日历信息
     * @return
     */
    public Map<String,Object> getCalendar();
}
