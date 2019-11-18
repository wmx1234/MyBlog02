package com.xiao.blog.model;

import lombok.Data;

import java.util.List;

/**
 * @author wangmx
 * @create 2019-11-18 21:11
 * @Desc
 */
@Data
public class Page<T> {

    private int limit;

    private int page;

    private int code;

    private String msg;

    private long count;//返回数据总条数

    private List<T> data;

}
