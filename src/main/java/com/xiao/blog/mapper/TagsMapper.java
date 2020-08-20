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

    /**
     * 获取文章标签
     * @param id
     * @return
     */
    @Select("select * from blog_tags tags left join blog_article_tags bat on tags.id = bat.tags_id where bat.article_id = #{id}")
    List<Tags> getTagsListByArticle(Integer id);

}