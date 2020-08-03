package com.xiao.blog.mapper;

import com.xiao.blog.model.Categories;
import com.xiao.blog.vo.CategoriesVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CategoriesMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(Categories record);

    Categories selectByPrimaryKey(Integer id);

    List<Categories> getCategoriesByField(Categories categories);

    int updateByPrimaryKey(Categories record);

    List<CategoriesVO> getCategoriesVOList(Integer userId);

    @Select("select count(id) from blog_categories where name = #{name}")
    Integer exist(Categories categories);
}