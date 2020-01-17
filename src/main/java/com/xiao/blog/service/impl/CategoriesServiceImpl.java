package com.xiao.blog.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import com.xiao.blog.mapper.ArticleMapper;
import com.xiao.blog.mapper.CategoriesMapper;
import com.xiao.blog.model.Article;
import com.xiao.blog.model.Categories;
import com.xiao.blog.service.ArticleService;
import com.xiao.blog.service.CategoriesService;
import com.xiao.blog.shiro.ShiroKit;
import com.xiao.blog.vo.ArticleVO;
import com.xiao.blog.vo.Classify;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public List<Classify> classify(Categories categories) {

        List<Categories> categoriesList = categoriesMapper.getCategoriesByField(categories);

        List<Classify> classifyList = new ArrayList<Classify>();

        for(Categories c : categoriesList){
            Article article = new Article();
            article.setCategoriesId(c.getId());
            List<ArticleVO> articles = articleMapper.getArticles(article);
            if(CollectionUtil.isNotEmpty(articles)){
                classifyList.add(new Classify(c,articles));
            }

        }
        return classifyList;
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
