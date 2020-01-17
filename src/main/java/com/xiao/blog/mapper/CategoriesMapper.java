package com.xiao.blog.mapper;

import com.xiao.blog.model.Categories;
import com.xiao.blog.vo.Classify;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CategoriesMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Categories record);

    Categories selectByPrimaryKey(Integer id);

    List<Categories> getCategoriesByField(Categories categories);

    int updateByPrimaryKey(Categories record);


}