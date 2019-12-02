package com.xiao.blog.service.impl;

import cn.hutool.core.date.DateUtil;
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

    @Override
    public int save(Categories categories) {
        if(categories.getId() != null)
            return update(categories);
        else
            return insert(categories);
    }

    @Override
    public List<Categories> getAll() {
        return categoriesMapper.selectAll(ShiroKit.getUser().getId());
    }

    private int insert(Categories categories){
        categories.setPublishdate(DateUtil.today());
        categories.setUserId(ShiroKit.getUser().getId());
        return categoriesMapper.insert(categories);
    }

    private int update(Categories categories){
        categories.setUpdatedate(DateUtil.today());
        return categoriesMapper.updateByPrimaryKey(categories);
    }
}
