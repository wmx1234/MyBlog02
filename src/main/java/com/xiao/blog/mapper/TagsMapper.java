package com.xiao.blog.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.xiao.blog.model.Tags;
import com.xiao.blog.vo.TagsVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 标签
 * @author Wangmx
 * @Date 2020-08-24
 */
@Mapper
public interface TagsMapper extends BaseMapper<Tags> {

    /**
     * 批量插入标签
     * @param tagsList
     * @return
     */
    int batchInsertTags(List<Tags> tagsList);

    int deleteByPrimaryKey(Integer id);


    int updateByPrimaryKey(Tags tags);

    /**
     * 获取页面展示标签
     * @return
     */
    List<TagsVO> getTagsVOList();

    /**
     *
     * @return
     */
    List<Tags> getAllTags();

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