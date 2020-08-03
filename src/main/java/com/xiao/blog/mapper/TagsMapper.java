package com.xiao.blog.mapper;

import com.xiao.blog.model.Tags;
import com.xiao.blog.vo.TagsVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
@Mapper
public interface TagsMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(Tags record);

    int updateByPrimaryKey(Tags tags);

    List<TagsVO> getTagsVOList(Integer userId);

    List<Tags> getTagsList(Integer userId);

    @Select("select count(id) from blog_tags where name = #{name}")
    Integer exist(Tags tags);


}