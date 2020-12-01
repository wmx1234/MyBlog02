package com.xiao.blog.model;

import lombok.Data;

import java.util.Date;

@Data
public class BoxCategories {

    private int id;

    private int sort;

    private int parentId;

    private String title;

    private String icon;

    private int levels;

    private Date createDate;

    private Date updateDate;
}
