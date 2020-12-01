package com.xiao.blog.mapper;

import com.xiao.blog.model.User;
import com.xiao.blog.vo.BoxCategoriesVO;
import com.xiao.blog.vo.LoginUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoxCategoriesMapper {


    public List<BoxCategoriesVO> getBoxCategories();


}