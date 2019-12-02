package com.xiao.blog.model;

import jdk.nashorn.internal.ir.Labels;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class Article implements Serializable {


    private static final long serialVersionUID = 1L;

    private Integer id;

    private String articleAbstract;

    private String articleAuthor;

    private String articleTitle;

    private String createDate;

    private Integer likes;

    private Integer readCount;

    private String updateDate;

    private Integer lastArticleId;

    private Integer nextArticleId;

    private Integer commentCount;

    private Integer istop;

    private Integer editorType;

    private Integer isdraft;

    private Integer isprivacy;

    private String articleContent;

    private String articleHtmlContent;



}