package com.xiao.blog.vo;

import com.xiao.blog.model.*;
import lombok.Data;

import java.util.List;

/**
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

}
