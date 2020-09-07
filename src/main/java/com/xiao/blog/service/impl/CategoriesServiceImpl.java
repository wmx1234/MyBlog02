package com.xiao.blog.service.impl;

import cn.hutool.core.date.DateUtil;
import com.xiao.blog.common.Constant;
import com.xiao.blog.exception.BusinessException;
import com.xiao.blog.exception.assertion.BusinessExceptionAssert;
import com.xiao.blog.mapper.ArticleMapper;
import com.xiao.blog.mapper.CategoriesMapper;
import com.xiao.blog.model.Categories;
import com.xiao.blog.service.CategoriesService;
import com.xiao.blog.shiro.ShiroKit;
import com.xiao.blog.vo.CategoriesVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Random;

/**
 * @author wangmx
 * @create 2019-11-30 16:34
 * @Desc
 */
@Service
public class CategoriesServiceImpl implements CategoriesService {

    @Resource
    CategoriesMapper categoriesMapper;

    @Resource
    ArticleMapper articleMapper;

    @Override
    public int save(Categories categories) throws BusinessException {

        BusinessExceptionAssert.assertCategoriesExist(categories);

        if(categories.getId() != null)
            return update(categories);
        else
            return insert(categories);
    }

    @Override
    public List<Categories> getAllCategories(){
        return categoriesMapper.getAllCategories();
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
    public List<CategoriesVO> getCategoriesVOList() {

        List<CategoriesVO> categoriesVOList = categoriesMapper.getCategoriesVOList();

        categoriesVOList.forEach(categories->{
            categories.setLink("/categories/"+categories.getId());
            categories.setColor(Constant.COLORS[new Random().nextInt(9)]);
        });

        return categoriesVOList;
    }

    @Override
    public int delete(Integer id) {
        return categoriesMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int getArticleCount(){
        return categoriesMapper.getCategoriesCount();
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
