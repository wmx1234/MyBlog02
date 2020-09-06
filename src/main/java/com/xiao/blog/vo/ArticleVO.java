package com.xiao.blog.vo;

import com.xiao.blog.model.*;
import lombok.Data;

import java.util.List;

/**
 * 博客页面展示类
 * @author wangmx
 * @date 2019-12-24 15:45
 * @desc:
 */
@Data
public class ArticleVO extends Article {

    private User user;

    private Article lastArticle;

    private Article nextArticle;

    private Categories categories;

    private List<Tags> tagsList;

    /**
     * 查询特定标签下所有博客时使用
     */
    private Integer tagsId;

    private String year;

    private String month;

    private String day;



    public Article getArticle(){
        return new Article();
    }
}
