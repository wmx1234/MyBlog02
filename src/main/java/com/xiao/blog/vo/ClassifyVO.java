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
public class ClassifyVO {

    private String categoriesName;

    private List<ArticleVO> articles;

}
