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

    /**
     * 查询特定标签下所有博客时使用
     */
    private Integer tagsId;
    public ArticleVO(){}
    public ArticleVO(Article article){
        super.setId(article.getId());
        super.setArticleTitle(article.getArticleTitle());
        super.setArticleDigest(article.getArticleDigest());
        super.setArticleContent(article.getArticleContent());
        super.setArticleHtmlContent(article.getArticleHtmlContent());
        super.setUserId(article.getUserId());
        super.setLikes(article.getLikes());
        //private String articleImage;

        //private String articleUrl;


//        private String createDate;
//
//        private String updateDate;
//
//        private Integer likes;
//
//        private Integer lastArticleId;
//
//        private Integer nextArticleId;
//
//        private Integer top;
//
//        private Integer draft;
//
//        private Integer privacy;
//
//        private Integer categoriesId;
    }

}
