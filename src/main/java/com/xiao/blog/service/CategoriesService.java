package com.xiao.blog.service;

import com.xiao.blog.model.Categories;
import com.xiao.blog.vo.CategoriesVO;

import java.util.List;
import java.util.Map;

/**
 * @author wangmx
 * @create 2019-11-30 16:33
 * @Desc
 */
public interface CategoriesService {

    public int save(Categories categories);

    List<Categories> getAllCategories();

    List<Categories> getCategoriesByField(Categories categories);

    /**
     * 获取分类列表
     * @return
     */
    List<CategoriesVO> getCategoriesVOList();

    int delete(Integer id);

    int getArticleCount();
}
