package com.xiao.blog.model;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;


/**
 * 博客实体类
 * @author 王明晓
 */
@Data
@Document(indexName = "blog",type = "article")
public class Article implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String articleTitle;

    private String articleDigest;

    private String articleContent;

    private String articleHtmlContent;

    private String articleImage;

    private String articleUrl;

    private Integer userId;

    private String createDate;

    private String updateDate;

    private Integer likes;

    private Integer lastArticleId;

    private Integer nextArticleId;

    private Integer top;

    private Integer draft;

    private Integer privacy;

    private Integer categoriesId;

}