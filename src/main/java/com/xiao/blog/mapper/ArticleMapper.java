package com.xiao.blog.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.xiao.blog.model.Article;
import com.xiao.blog.vo.Archive;
import com.xiao.blog.vo.ArchiveVO;
import com.xiao.blog.vo.ArticleVO;
import com.xiao.blog.vo.ClassifyVO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @author wangmx
 */
@Mapper
public interface ArticleMapper extends BaseMapper<Article>{

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
     * 获取所有博客列表
     * @return
     */
    List<ArticleVO> getAllArticles();

    /**
     * 根据id获取博客
     * @param id
     * @return
     */
    ArticleVO getArticleById(Integer id);

    /**
     * 获取创建日期下的文章数
     * @return
     */
    @Select("select create_date,count(id) count from blog_article where draft = 0 and privacy = 0 group by create_date")
    List<Map> getBlogCountByCreateDate();


    /**
     * 获取最近的博客id
     * @return
     */
    @Select("select id from blog_article order by id desc limit 1")
    int getLastArticleId();

    /**
     * 获取博客列表
     * @param article
     * @return
     */
    List<ArticleVO> getArticleListByField(ArticleVO article);

    /**
     * 获取公开发布文章数量
     * @return
     */
    @Select("select count(id) from blog_article where draft = 0 and privacy = 0")
    int getArticleCount();
}