package com.xiao.blog.util;

import com.xiao.blog.mapper.CategoriesMapper;
import com.xiao.blog.mapper.RelationMapper;
import com.xiao.blog.mapper.TagsMapper;
import com.xiao.blog.model.Categories;
import com.xiao.blog.model.Tags;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

/**
 * @author wangmx
 * @create 2019-12-03 20:56
 * @Desc
 */
public class DataBaseUtil {

    @Autowired
    private RelationMapper relationMapper;

    @Autowired
    private TagsMapper tagsMapper;

    @Autowired
    private CategoriesMapper categoriesMapper;

    private static DataBaseUtil dataBaseUtil;



    @PostConstruct
    public void init() {

        dataBaseUtil = this;
        dataBaseUtil.relationMapper = this.relationMapper;
        dataBaseUtil.tagsMapper = this.tagsMapper;
        System.out.println("dataBaseUtil"+dataBaseUtil);
    }


    public static int nextValue(){

        return dataBaseUtil.relationMapper.nextValue();
    }

    public static Integer tagsExist(Tags tags){

        return dataBaseUtil.tagsMapper.exist(tags);
    }

    public static Integer CategoriesExist(Categories categories) {
        return dataBaseUtil.categoriesMapper.exist(categories);
    }
}
