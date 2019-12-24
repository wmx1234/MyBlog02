package com.xiao.blog.mapper;

import com.xiao.blog.model.Tags;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface TagsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Tags record);

    Tags selectByPrimaryKey(Integer id);

    List<Tags> selectAll();

    int updateByPrimaryKey(Tags record);
}