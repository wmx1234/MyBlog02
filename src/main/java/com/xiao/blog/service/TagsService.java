package com.xiao.blog.service;

import com.xiao.blog.model.Tags;
import com.xiao.blog.vo.TagsVO;

import java.util.List;

/**
 * @author wangmx
 * @create 2019-11-30 10:03
 * @Desc
 */
public interface TagsService {


    public Tags save(Tags tags);

    /**
     * 删除标签
     * @param id
     * @return
     */
    public int delete(Integer id);

    /**
     * 获取TagsVO列表
     * @return
     */
    List<TagsVO> getTagsVOList();


    List<Tags> getTagsList();

    /**
     * 获取标签数量
     * @return
     */
    int getTagsCount();
}
