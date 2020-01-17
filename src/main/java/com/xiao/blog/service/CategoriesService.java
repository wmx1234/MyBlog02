package com.xiao.blog.service;

import com.xiao.blog.model.Categories;
import com.xiao.blog.vo.Classify;

import java.util.List;

/**
 * @author wangmx
 * @create 2019-11-30 16:33
 * @Desc
 */
public interface CategoriesService {

    public int save(Categories categories);

    List<Categories> getCategoriesByField(Categories categories);

    /**
     * 分类
     * @param categories
     * @return
     */
    List<Classify> classify(Categories categories);
}
