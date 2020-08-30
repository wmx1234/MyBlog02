package com.xiao.blog.vo;

import lombok.Data;

import java.util.List;

/**
 *
 * @author wangmx
 * @date 2020-01-20 11:29
 * @desc:
 */
@Data
public class ArchiveVO {

    private String year;

    private String month;

    private String day;

    private ArticleVO articleVO;
}
