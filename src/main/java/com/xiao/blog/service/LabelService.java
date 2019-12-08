package com.xiao.blog.service;

import com.xiao.blog.model.Label;

import java.util.List;

/**
 * @author wangmx
 * @create 2019-11-30 10:03
 * @Desc
 */
public interface LabelService {

    public int save(Label label);

    List<Label> getAll();

    int deleteBatch(List<Integer> ids);
}
