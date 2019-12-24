package com.xiao.blog.pojo;

import lombok.Data;

import java.util.List;

/**
 * @author wangmx
 * @create 2019-11-24 21:58
 * @Desc
 */
@Data
public class TreeModel {

    private Integer id;

    private String title;

    private String checked;

    private List<TreeModel> children;

}
