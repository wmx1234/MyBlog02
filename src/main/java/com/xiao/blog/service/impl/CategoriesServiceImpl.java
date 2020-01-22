package com.xiao.blog.service.impl;

import cn.hutool.core.date.DateUtil;
import com.xiao.blog.mapper.ArticleMapper;
import com.xiao.blog.mapper.CategoriesMapper;
import com.xiao.blog.model.Categories;
import com.xiao.blog.service.CategoriesService;
import com.xiao.blog.shiro.ShiroKit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wangmx
 * @create 2019-11-30 16:34
 * @Desc
 */
@Service
public class CategoriesServiceImpl implements CategoriesService {

    @Autowired
    CategoriesMapper categoriesMapper;
    @Autowired
    ArticleMapper articleMapper;
    @Override
    public int save(Categories categories) {
        if(categories.getId() != null)
            return update(categories);
        else
            return insert(categories);
    }


    /**
     * 根据条件查询分类
     * @param categories
     * @return
     */
    @Override
    public List<Categories> getCategoriesByField(Categories categories) {

        return categoriesMapper.getCategoriesByField(categories);
    }

    /**
     * 新增分类
     * @param categories
     * @return
     */
    private int insert(Categories categories){
        categories.setCreateDate(DateUtil.today());
        categories.setUserId(ShiroKit.getUser().getId());
        return categoriesMapper.insert(categories);
    }

    /**
     * 修改分类
     * @param categories
     * @return
     */
    private int update(Categories categories){
        categories.setUpdateDate(DateUtil.today());
        return categoriesMapper.updateByPrimaryKey(categories);
    }
}
