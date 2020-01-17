package com.xiao.blog.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Article implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String articleTitle;

    private String articleDigest;

    private String createDate;

    private String updateDate;

    private Integer likes;

    private Integer readCount;

    private Integer lastArticleId;

    private Integer nextArticleId;

    private Integer top;

    private Integer editorType;

    private Integer draft;

    private Integer privacy;

    private Integer userId;

    private Integer categoriesId;

    private String articleContent;

    private String articleHtmlContent;

    private Integer commentCount;
}