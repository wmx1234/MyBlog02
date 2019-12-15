package com.xiao.blog.pojo.request;

import lombok.Data;

import java.util.List;

/**
 * @author wangmx
 * @create 2019-11-18 21:11
 * @Desc
 */
@Data
public class PageRequest<T> extends BaseRequest<T>{

    private int limit;

    private int page;

    private T obj;
}
