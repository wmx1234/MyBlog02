package com.xiao.blog.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.xiao.blog.model.Article;
import com.xiao.blog.vo.Archive;
import com.xiao.blog.vo.ArticleVO;
import com.xiao.blog.vo.ClassifyVO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author wangmx
 */
@Mapper
public interface ArticleMapper extends BaseMapper<ArticleVO>{

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
    @Delete("delete from blog_article where id = #{id}")
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
     * 获取所有博客列表
     * @return
     */
    List<ArticleVO> getAllArticles();

    /**
     * 根据标签博客列表
     * @param id
     * @return
     */
    List<ArticleVO> getArticleListByTagsId(Integer id);

    /**
     * 根据id获取博客
     * @param id
     * @return
     */
    ArticleVO getArticleById(Integer id);



}