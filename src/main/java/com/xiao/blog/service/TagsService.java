package com.xiao.blog.service;

import com.xiao.blog.model.Tags;

import java.util.List;

/**
 * @author wangmx
 * @create 2019-11-30 10:03
 * @Desc
 */
public interface TagsService {

    public int save(Tags label);

    List<Tags> getAll();

    int deleteBatch(List<Integer> ids);
}
