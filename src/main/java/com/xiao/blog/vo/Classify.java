package com.xiao.blog.vo;

import com.xiao.blog.model.Article;
import com.xiao.blog.model.Categories;
import lombok.Data;

import java.util.List;

/**
 * @author wangmx
 * @date 2020-01-17 13:58
 * @desc:
 */
@Data
public class Classify extends Categories {

    private List<ArticleVO> articles;

    public Classify(){}

    public Classify(Categories categories,List<ArticleVO> articles){
        super.setId(categories.getId());
        super.setName(categories.getName());
        super.setIcon(categories.getIcon());
        super.setCreateDate(categories.getCreateDate());
        super.setUpdateDate(categories.getUpdateDate());
        super.setUserId(categories.getUserId());
        this.articles = articles;
    }

}
