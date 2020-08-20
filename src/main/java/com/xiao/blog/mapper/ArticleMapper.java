package com.xiao.blog.mapper;

import com.xiao.blog.model.Article;
import com.xiao.blog.vo.Archive;
import com.xiao.blog.vo.ArticleVO;
import com.xiao.blog.vo.ClassifyVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ArticleMapper {

    /**
     * 新增博客
     * @param article
     * @return
     */
    int save(Article article);

    /**
     * 删除博客
     * @param id
     * @return
     */
    int delete(int id);

    /**
     * 修改博客
     * @param article
     * @return
     */
    int update(Article article);

    /**
     * 获取博客列表
     * @param article
     * @return
     */
    List<ArticleVO> getArticleList(ArticleVO article);

    /**
     * 根据id获取博客
     * @param id
     * @return
     */
    ArticleVO getArticleById(Integer id);
}