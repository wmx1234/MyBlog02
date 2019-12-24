package com.xiao.blog.vo;

import com.xiao.blog.model.*;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author wangmx
 * @date 2019-12-24 15:45
 * @desc:
 */
@Data
public class ArticleVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String articleTitle;

    private String articleDigest;

    private String createDate;

    private String updateDate;

    private Integer likes;

    private Integer readCount;

    private Integer top;

    private Integer editorType;

    private Integer draft;

    private Integer privacy;

    private String articleContent;

    private String articleHtmlContent;

    private Article lastArticle;

    private Article nextArticle;

    private User user;

    private Categories categories;

    private List<Tags> tagsList;

    private List<Comment> commentList;

}
