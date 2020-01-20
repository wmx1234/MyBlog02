package com.xiao.blog.vo;

import lombok.Data;

import java.util.List;

/**
 * @author wangmx
 * @date 2020-01-20 11:29
 * @desc:
 */
@Data
public class Archive {

    private String createDate;

    private List<ArticleVO> articles;
}
