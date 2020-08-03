package com.xiao.blog.exception.assertion;


import com.xiao.blog.exception.BusinessException;
import com.xiao.blog.mapper.TagsMapper;
import com.xiao.blog.model.Categories;
import com.xiao.blog.model.Tags;
import com.xiao.blog.util.DataBaseUtil;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

/**
 * <p>业务异常断言</p>
 *
 * @author sprainkle
 * @date 2019/5/2
 */
public class BusinessExceptionAssert{


    @Autowired
    TagsMapper tagsMapper;

    private static BusinessExceptionAssert businessExceptionAssert;

    @PostConstruct
    public void init() {

        businessExceptionAssert = this;
        businessExceptionAssert.tagsMapper = this.tagsMapper;
        System.out.println("businessExceptionAssert==========="+businessExceptionAssert);
    }

    /**
     * 判断标签是否存在
     * @param tags
     */
    public static void assertTagsExist(Tags tags){

        if(DataBaseUtil.tagsExist(tags) >= 1){
            throw new BusinessException("该标签已存在");
        }
    }

    public static void assertCategoriesExist(Categories categories){

        if(DataBaseUtil.CategoriesExist(categories) >= 1){
            throw new BusinessException("该分类已存在");
        }
    }
}
